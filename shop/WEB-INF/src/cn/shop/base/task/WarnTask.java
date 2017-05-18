package cn.shop.base.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import cn.shop.base.tree.TreeNode;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.GoodsCatDao;
import cn.shop.dao.ProductDao;
import cn.shop.dao.StoreDao;

/**
 * 定时检查订单。
 * 
 * @author shaozhen
 */
public class WarnTask extends HttpServlet
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(WarnTask.class);

    private final String param = "interval";

    /**
     * 任务执行类。
     * 
     * @author shaozhen
     */
    class OrderManagerTask extends TimerTask
    {

        /**
         * 检查未付款的订单，超时判定交易失败。
         */
        private void checkStore()
        {
            StoreDao storeDao = (StoreDao)SpringContextUtil.getBean("storeDao");
            GoodsCatDao catDao = (GoodsCatDao)SpringContextUtil.getBean("GoodsDao");
            ProductDao productDao = (ProductDao)SpringContextUtil.getBean("productDao");
            List<Map<String, Object>> warn = storeDao.getAllWarn();
//            List<Map<String, Object>> StoreList = productDao.getStore
            
            
            List<Integer> catIds = new ArrayList<Integer>();
            List<Integer> goodsIds = new ArrayList<Integer>();            
            List<Integer> warnGoodsIds = new ArrayList<Integer>();
            List<Map<String, Object>> goodsCatList;
            List<Map<String, Object>> goodsList;
            TreeNode treeNode = new TreeNode();
            for(Map<String, Object> map : warn)
            {
                if(map.get("cat_id") != null)
                {
                    catIds.add((Integer)map.get("cat_id"));
                }
                if(map.get("goods_id") != null)
                {
                    goodsIds.add((Integer)map.get("goodds_id"));
                }
            }
            goodsCatList = catDao.getGoodsCat(new HashMap<String, Object>());                        
        }

        @Override
        public void run()
        {
            // logger.info("定时任务开始执行");
            // logger.info("定时任务结束");
            checkStore();
        }

    }

    public WarnTask()
    {
        super();
    }

    @Override
    public void init() throws ServletException
    {
        int interval = Integer.parseInt(this.getInitParameter("interval"));
        Timer timer = new Timer();
        int h, m, s;        
        String perStr = this.getInitParameter("off");
        if(perStr != null)
        {
            boolean per = new Boolean(perStr);
            if(per)
            {
                logger.info("库存预警未开启");
                return;
            }
            else
            {
                logger.info("库存预警已开启");
            }
        }
        
        h = interval % 3600;
        m = interval % 60;
        s = interval - m * 3600 - m * 60;
        logger.info("任务执行间隔:" + String.format("%02d:%02d:%02d", h, m, s));
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
