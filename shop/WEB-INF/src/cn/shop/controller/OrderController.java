package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.base.Order;
import cn.shop.base.OrderStatus;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.OrderDao;
import cn.shop.model.Check;

/**
 * 订单处理类。
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/mall/")
public class OrderController
{
    private static Logger logger = Logger.getLogger(OrderController.class);

    /**
     * 获得用户订单列表
     * 
     * @return
     */
    @RequestMapping(value = "getOrder.shtm")
    public Object getOrderList(ModelMap model,
            @RequestParam Map<String, String> param,
            HttpSession session)
    {
        OrderDao dao;
        List<Map<String, Object>> list;
        OrderStatus orderStatus;
        Map<String, Object> dbParam = new HashMap<String, Object>();

        // 检查是否在线
        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        orderStatus = OrderStatus.get(param.get("orderStatus"));
        dao = (OrderDao) SpringContextUtil.getBean("orderDao");
        if (orderStatus == OrderStatus.UNKNOWN)
        {
            list = dao.getOrderList(dbParam);
        }
        else
        {
            list = dao.getOrderListByStatus(dbParam);
        }

        model.addAttribute("orderList", list);
        model.addAttribute("orderStatus", orderStatus);

        return "mall/v2/order.jsp";
    }

    /**
     * 新增订单
     * <p>
     * 必须要有产品id
     * </p>
     */
    @RequestMapping(value = "addOrder.action")
    @ResponseBody
    public Object addOrder(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        String pruduct_id = param.get("pruduct_id").toString();
        String orderId;
        OrderDao dao;
        ApplicationContext context;

        if (pruduct_id != null)
        {
            result.put("operator", false);
            return result;
        }
        orderId = Order.createProductId();
        param.put("order_id", orderId);
        context = ContextLoader.getCurrentWebApplicationContext();
        dao = (OrderDao) context.getBean("orderDao");
        if (dao.addOrder(param))
        {
            result.put("product_id", orderId);
            result.put("operator", true);
            logger.debug("下单" + param.toString());
        }
        else
        {
            result.put("operator", false);
            logger.debug("下单失败");
        }
        return result;
    }

    /**
     * 删除订单
     */
    @RequestMapping(value = "deleteOrder.action")
    @ResponseBody
    public Object deleteOrder(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        String pruduct_id = param.get("pruduct_id");
        String orderId;
        OrderDao dao;
        ApplicationContext context;

        if (pruduct_id != null)
        {
            result.put("operator", false);
            return result;
        }
        orderId = Order.createProductId();
        param.put("order_id", orderId);
        context = ContextLoader.getCurrentWebApplicationContext();
        dao = (OrderDao) context.getBean("orderDao");
        if (dao.deleteOrder(param))
        {
            result.put("operator", true);
            logger.debug("下单" + param.toString());
        }
        else
        {
            result.put("operator", false);
            logger.debug("下单失败");
        }
        return result;
    }
}
