package cn.shop.base.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import cn.shop.base.OrderStatus;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.OrderDao;

/**
 * 定时检查订单。
 * 
 * @author shaozhen
 */
public class OrderTask extends HttpServlet
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(OrderTask.class);

    /**
     * 任务执行类。
     * @author shaozhen
     *
     */
    class OrderManagerTask extends TimerTask
    {

        /**
         * 检查未付款的订单，超时判定交易失败。
         */
        private void checkOrderList()
        {
            OrderDao orderDao;
            List<Map<String, Object>> list;
            List<Integer> orderIdList = new ArrayList<Integer>();
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> dbParam = new HashMap<String, Object>();

            map.put("time", System.currentTimeMillis() - 1000 * 60 * 45);
            map.put("orderStatus", OrderStatus.WAIT.getCode());
            orderDao = (OrderDao) SpringContextUtil.getBean("orderDao");
            list = orderDao.getTomeoutOrder(map);
            for (Map<String, Object> temp : list)
            {
                orderIdList.add((Integer) temp.get("order_id"));
            }
            if (!orderIdList.isEmpty())
            {
                logger.info("发现超时订单" + orderIdList.size() + "条");
                dbParam.put("status", OrderStatus.CANCEL.getCode());

                for (Integer orderId : orderIdList)
                {
                    dbParam.put("orderId", orderId);
                    orderDao.updateOrderStatus(dbParam);
                }
            }
            else
            {
                logger.info("未发现超时订单");
            }

        }

        @Override
        public void run()
        {
            // logger.info("定时任务开始执行");
            // logger.info("定时任务结束");
            checkOrderList();
        }

    }

    public OrderTask()
    {
        super();
    }

    @Override
    public void init() throws ServletException
    {
        
        int start = NumberUtils.toInt(this.getInitParameter("start"), -1);
        int interval = Integer.parseInt(this.getInitParameter("interval"));
        Timer timer = new Timer();
        logger.info("任务执行间隔为" + interval + "s");
        if(start < 1)
        {
            logger.info("订单超时检查已关闭");
            return;
        }
        try
        {
            timer.schedule(new OrderManagerTask(), 0, interval * 1000);
        }
        catch (Exception e)
        {
            logger.error("定时任务异常", e);
        }
    }
}
