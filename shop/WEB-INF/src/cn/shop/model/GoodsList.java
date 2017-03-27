package cn.shop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GoodsList
{
    Map<String, Goods> goodsMap = new HashMap<String, Goods>();

    public String addGoods(Goods goods)
    {
        synchronized (GoodsList.class)
        {
            UUID uuid = UUID.randomUUID();
            String goodsId = uuid.toString();
            Long createTime = System.currentTimeMillis();
            goods.setGoodsId(goodsId);
            goods.setCreateTime(createTime);
            goodsMap.put(goodsId, goods);
            return goodsId;
        }
    }

    public Goods getGoods(String goodsId)
    {
        synchronized (GoodsList.class)
        {
            return goodsMap.get(goodsId);
        }
    }

    public Map<String, Goods> getAllGoods()
    {
        synchronized (GoodsList.class)
        {
            return goodsMap;
        }
    }
}
