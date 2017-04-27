package cn.shop.base.task;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * 定时检查订单。
 * 
 * @author shaozhen
 */
public class OrderTask extends HttpServlet
{
    Logger logger = Logger.getLogger(this.getClass());

    class OrderManagerTask extends TimerTask
    {

        @Override
        public void run()
        {
            // logger.info("定时任务开始执行");
            // logger.info("定时任务结束");
        }

    }
    public OrderTask()
    {
        super();
    }

    @Override
    public void init() throws ServletException
    {
        int interval = Integer.parseInt(this.getInitParameter("interval"));
        Timer timer = new Timer();
        logger.info("任务执行间隔为" + interval + "s");
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
