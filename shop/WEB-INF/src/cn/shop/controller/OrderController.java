package cn.shop.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.base.Order;
import cn.shop.base.OrderStatus;
import cn.shop.base.PayStatus;
import cn.shop.base.util.Default;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.GoodsDao;
import cn.shop.dao.MemberDao;
import cn.shop.dao.OrderDao;
import cn.shop.dao.ProductDao;
import cn.shop.model.Check;
import cn.shop.model.Page;

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
            @RequestParam Map<String, String> param, HttpSession session)
    {
        int memberId;
        OrderDao dao;
        // OrderItemsDao orderItemsDao;
        List<Map<String, Object>> list;
        OrderStatus orderStatus;
        Map<String, Object> dbParam = new HashMap<String, Object>();

        // 检查是否在线
        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        memberId = Check.getMemberInfo(session).getMemberId();
        orderStatus = OrderStatus.get(param.get("orderStatus"));
        dao = (OrderDao) SpringContextUtil.getBean("orderDao");
        dbParam.put("memberId", memberId);
        if (orderStatus != OrderStatus.UNKNOWN)
        {
            dbParam.put("status", orderStatus.getCode());
        }
        list = dao.getAllOrderByParamAndMemberId(dbParam);
        for (Map<String, Object> map : list)
        {
            String createTimeStr = Default.toString(map.get("create_time"),
                    null);
            Long createTime = NumberUtils.toLong(createTimeStr, -1);
            if (createTime < 0)
            {
                map.put("createDate", null);
            }
            else
            {
                Date date = new Date(createTime);
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");

                map.put("createDateStr", format.format(date));
                map.put("createDate", date);
            }
            map.put("orderStatusDesc",
                    OrderStatus.get(Default.toString(map.get("status"), null))
                            .getDesc());
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

    @RequestMapping(value = "getAllOrders.shtm")
    @ResponseBody
    public Object getOrders(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        int pageSize;
        int pageNumber;
        int start, num;
        MemberDao memberDao;
        BigInteger startTime, endTime;
        Map<String, Object> dbParam = new HashMap<String, Object>();
        OrderDao dao;
        List<Map<String, Object>> list;
        OrderStatus orderStatus;
        Map<String, Object> result = new HashMap<String, Object>();
        if (param.get("startTime") != null || param.get("endTime") != null)
        {
            String[] pattern = new String[] { "dd/MM/yyyy" };
            try
            {
                if (param.get("startTime") != null)
                {
                    Date startDate = DateUtils.parseDate(
                            param.get("startTime"), pattern);
                    startTime = BigInteger.valueOf(startDate.getTime());
                    dbParam.put("startTime", startTime);
                }
                if (param.get("startTime") != null)
                {
                    Date endDate = DateUtils.parseDate(param.get("endTime"),
                            pattern);

                    endTime = BigInteger.valueOf(endDate.getTime());
                    dbParam.put("endTime", endTime);
                }
            }
            catch (Exception e)
            {
                logger.error(e);
            }
        }
        pageSize = NumberUtils.toInt(param.get("pageSize"), 100);
        pageNumber = NumberUtils.toInt(param.get("pageNumber"), 1);
        start = Page.getStartNum(pageSize, pageNumber);
        num = pageSize;
        dbParam.put("start", start);
        dbParam.put("num", num);
        orderStatus = OrderStatus.get(param.get("status"));
        dao = (OrderDao) SpringContextUtil.getBean("orderDao");
        if (orderStatus != OrderStatus.UNKNOWN)
        {
            dbParam.put("status", orderStatus.getCode());
        }
        list = dao.getAllOrderByParam(dbParam);
        for (Map<String, Object> map : list)
        {
            String createTimeStr = Default.toString(map.get("create_time"),
                    null);
            Long createTime = NumberUtils.toLong(createTimeStr, -1);
            Integer payStatusCode = (Integer) map.get("pay_status");
            Integer orderStatusCodeItem = (Integer) map.get("status");
            PayStatus payStatus = payStatusCode == null ? PayStatus.UNKNOWN
                    : PayStatus.get(payStatusCode);
            OrderStatus orderStatusItem = orderStatusCodeItem == null ? OrderStatus.UNKNOWN
                    : OrderStatus.get(orderStatusCodeItem);
            map.put("payStatusDesc", payStatus.getDesc());
            map.put("orderStatusDesc", orderStatusItem.getDesc());
            if (createTime < 0)
            {
                map.put("createDate", null);
            }
            else
            {
                Date date = new Date(createTime);
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");

                map.put("createDateStr", format.format(date));
                map.put("createDate", date);
            }
        }
        result.put("total", dao.getTotalOfAllOrderByParam(dbParam));
        result.put("data", list);
        return result;
    }

    @RequestMapping(value = "getOrderStatus.shtm", method = { RequestMethod.POST })
    @ResponseBody
    public Object getOrderStatus()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (OrderStatus t : OrderStatus.values())
        {
            Map<String, Object> map = new HashMap<String, Object>();

            if (t.getCode() >= 0)
            {
                map.put("id", t.getCode());
                map.put("text", t.getDesc());
            }
            else
            {
                map.put("id", t.getCode());
                map.put("text", "所有");
                map.put("selected", true);
            }

            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "getOrderDetailByOrderId.shtm", method = { RequestMethod.GET })
    @ResponseBody
    public Object getOrderDetailByOrderId(
            @RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();

        Map<String, Object> orderDetail = null;
        Map<String, Object> goodsDetail = null;
        Map<String, Object> productDetail = null;
        Map<String, Object> memberDetail = null;
        OrderDao orderDao = (OrderDao) SpringContextUtil.getBean("orderDao");
        GoodsDao goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        ProductDao productDao = (ProductDao) SpringContextUtil
                .getBean("productDao");
        MemberDao memberDao = (MemberDao) SpringContextUtil
                .getBean("memberDao");
        int orderId = NumberUtils.toInt(param.get("orderId"), -1);
        if (orderId < 0)
        {
            result.put("code", 1);
            result.put("desc", "订单id参数缺失");
            return result;
        }
        dbParam.put("orderId", orderId);
        orderDetail = orderDao.getOneOrderById(dbParam);
        if (orderDetail != null)
        {
            String sn = (String) orderDetail.get("sn");
            int memberId = (Integer) orderDetail.get("member_id");
            Map<String, Object> productParam = new HashMap<String, Object>();
            Map<String, Object> memberParam = new HashMap<String, Object>();

            memberParam.put("memberId", memberId);
            productParam.put("sn", sn);
            productDetail = productDao.getOneProductDetailBySn(productParam);
            memberDetail = memberDao.getOneMemberById(memberParam);
            if (productDetail != null)
            {
                int goodsId = (Integer) productDetail.get("goods_id");
                Map<String, Object> goodsParam = new HashMap<String, Object>();
                goodsParam.put("goodsId", goodsId);
                goodsDetail = goodsDao.getOneGoodsByGoodsId(goodsParam);
            }
            else
            {
                logger.warn("未查询到指定产品");
            }
        }
        else
        {
            logger.warn("未查询到指定订单");
        }

        result.put("orderDetail", orderDetail);
        result.put("productDetail", productDetail);
        result.put("goodsDetail", goodsDetail);
        result.put("memberDetail", memberDetail);
        return result;
    }

    /**
     * 更新订单状态。
     * 
     * @param param
     * @return
     */
    @RequestMapping(value = "updateOrderStatus.shtm", method = { RequestMethod.GET })
    @ResponseBody
    public Object preparingGoods(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        OrderDao orderDao = (OrderDao) SpringContextUtil.getBean("orderDao");
        int orderId = NumberUtils.toInt(param.get("orderId"), -1);
        OrderStatus status = OrderStatus.get(param.get("statusCode"));
        Map<String, Object> orderLog = new HashMap<String, Object>();
        StringBuilder message = new StringBuilder();

        if (orderId < 0 && status != OrderStatus.UNKNOWN)
        {
            result.put("code", 1);
            result.put("desc", "订单id参数缺失");
            return result;
        }
        dbParam.put("orderId", orderId);
        dbParam.put("status", status.getCode());
        orderDao.updateOrderStatus(dbParam);
        // 当确认付款时要同时修改订单状态为“已确认付款”，付款状态修改为已确认
        if (status == OrderStatus.DISTRIBUTING)
        {
            orderDao.updatePayStatusByOrderId(dbParam);
            ;
        }
        result.put("code", 1);
        orderLog.put("orderId", orderId);
        
        message.append(
                DateFormatUtils.format(System.currentTimeMillis(),
                        "yyyy-MM-dd HH:mm-ss")).append("修改订单:").append(orderId)
                .append("为").append(status.getDesc());
        orderLog.put("message", message.toString());
        orderDao.addOrderLog(orderLog);
        return result;
    }

    @RequestMapping(value = "orderLog.shtm")
    @ResponseBody
    public Object getOrderLog(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        int orderId = NumberUtils.toInt(param.get("orderId"), -1);
        OrderDao orderDao;
        List<Map<String, Object>> orderLog;

        if (orderId < 0)
        {
            result.put("orderLog", new ArrayList<String>());
            return result;
        }
        dbParam.put("orderId", orderId);
        orderDao = (OrderDao) SpringContextUtil.getBean("orderDao");
        orderLog = orderDao.getOrderLog(dbParam);
        result.put("orderLog", orderLog);
        result.put("orderId", orderId);
        return result;
    }
}
