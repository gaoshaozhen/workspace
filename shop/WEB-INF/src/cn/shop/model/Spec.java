package cn.shop.model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Spec
{
    public  static JSONObject getSpecJson()
    {
        JSONObject json = new JSONObject();
        json.put("sn", "");
        json.put("cost", 0);
        json.put("goods_id", 0);
        json.put("name", "");
        json.put("specs", "");
        json.put("weight", 0);
        json.put("specList", new JSONArray());
        json.put("goodsLvPrices", new JSONArray());
        json.put("product_id", 0);
        json.put("price", 0);
        json.put("store", 0);
        json.put("specsvIdJson", "[]");        
        return json;
    }    
}
