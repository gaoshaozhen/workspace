package cn.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.base.Order;
import cn.shop.dao.OrderDao;

/**
 * 订单处理类。
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/order/")
public class OrderController
{
    private static Logger logger = Logger.getLogger(OrderController.class);

    /**
     * 获得订单列表
     * 
     * @return
     */
    @RequestMapping(value = "getOrder.action")
    @ResponseBody
    public Object getOrder(@RequestParam Map<String, String> param)
    {
        OrderDao dao;
        ApplicationContext context;

        context = ContextLoader.getCurrentWebApplicationContext();
        dao = (OrderDao) context.getBean("orderDao");

        return dao.getOrder(param);
    }

    /**
     * 新增订单
     * <p>
     * 必须要有产品id
     * </p>
     */
    @RequestMapping(value = "addOrder.action")
    @ResponseBody
    public Object addOrder(@RequestParam Map<String, String> param)
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
