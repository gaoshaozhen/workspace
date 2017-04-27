package cn.shop.base.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil
{
    public static JSONObject getJSONObject(String json)
    {
        JSONObject jsonObject = JSONObject.fromObject(json);

        return jsonObject;
    }

    public static JSONArray getJSONArray(String json)
    {
        JSONArray jsonArray = JSONArray.fromObject(json);

        return jsonArray;
    }
}
