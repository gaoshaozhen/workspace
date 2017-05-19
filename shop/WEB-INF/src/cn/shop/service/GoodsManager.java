package cn.shop.service;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.shop.base.IdManager;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.GoodsDao;
import cn.shop.dao.ProductDao;

public class GoodsManager
{
    /**
     * 添加新商品
     * @param goods
     * @param user
     * @return
     */
    public boolean addGoods(Map<String, Object> goods)
    {
        boolean success = true;
        Integer have_spec = (Integer) goods.get("haveSpec");
        Map<String, Object> goodsDbParam = new HashMap<String, Object>();
        Map<String, Object> productDbParam = new HashMap<String, Object>();
        Map<String, Object> goodsDetail = new HashMap<String, Object>();        
        GoodsDao goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        ProductDao productDao = (ProductDao) SpringContextUtil
                .getBean("productDao");
        String sn = IdManager.createSn();
        int goodsId;        

        // 获取数据自增的goodsId
        goods.put("sn", sn);
        goodsDao.addGoods(goods);
        productDbParam.put("name", goods.get("name"));
        goodsDbParam.put("sn", sn);
        goodsDetail = goodsDao.getOneGoodsBySn(goodsDbParam);
        goodsId = (Integer) goodsDetail.get("goods_id");
        if (have_spec == null || have_spec < 1)
        {            
            productDbParam.put("goodsId", goodsId);
            productDbParam.put("sn", sn);
            productDbParam.put("cost", goods.get("cost"));
            productDbParam.put("weight", goods.get("weight"));
        }
        else
        {         
            productDbParam.put("goodsId", goodsId);
            JSONArray specs = JSONArray.fromObject((String) goods.get("specs"));            
            for (int i = 0, size = specs.size(); i < size; i++)
            {
                JSONObject json = specs.getJSONObject(i);
                productDbParam.put("sn", String.format("%s-%d", sn, i + 1));
                productDbParam.put("price", json.getDouble("price"));
                productDbParam.put("specs", json.getString("specs"));
                productDbParam.put("cost", json.getDouble("cost"));
                productDbParam.put("weight", json.getDouble("cost"));
                productDbParam.put("store", 0);
            }
        }
        productDao.addProduct(productDbParam);
        return success;
    }
}
