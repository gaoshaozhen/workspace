package cn.shop.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ShopDao
{
    static Logger log = Logger.getLogger(ShopDao.class);

    public List<Map<String, Object>> getGoodsInfo(Map<String, Object> map)
    {
        List<Map<String, Object>> list;
        Map<String, Object> info;

        info = new HashMap<String, Object>();
        list = new ArrayList<Map<String, Object>>();
        info.put("goodsId", map.get("goodsId"));
        list.add(info);
        return list;
    }

    /**
     * 根据商品id获得价格信息 params Map
     * 
     * @param map
     * @return <Map<String, Object>>
     */
    public List<Map<String, Object>> getGood(Map<String, Object> map)
    {
        List<Map<String, Object>> list = null;
        Map<String, Object> info = null;
        String goodsId = null;

        goodsId = (String) map.get("goodsId");
        if (goodsId == null)
        {
            log.info("警告：缺少参数");
            return null;
        }
        else
        {
            info = new HashMap<String, Object>();
            info.put("price", 100);
            info.put("goodsParam", "商品参数");
            list = new ArrayList<Map<String, Object>>();
            return list;
        }
    }
}
