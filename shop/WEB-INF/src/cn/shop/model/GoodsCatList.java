package cn.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class GoodsCatList
{
    List<Map<String, Object>> catList;

    public GoodsCatList(List<Map<String, Object>> list)
    {
        catList = list;

    }

    public List<Map<String, Object>> getFirst()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> map : catList)
        {
            if ((Integer) map.get("parent_id") < 1)
            {
                list.add(map);
            }
        }
        return list;
    }

    public List<Map<String, Object>> getChildren(int id)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> map : catList)
        {
            if ((Integer) map.get("parent_id") == 1)
            {
                list.add(map);
            }
        }
        return list;
    }

    public JSONObject getJson()
    {
        JSONObject json = new JSONObject();
        json.put("list", getFirst());

        return json;
    }

    public Map<String, List<Map<String, Object>>> getList()
    {
        Map<String, List<Map<String, Object>>> cat = new HashMap<String, List<Map<String, Object>>>();
        List<Map<String, Object>> firstList = getFirst();
        cat.put("date", firstList);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        cat.put("children", childList);
        // 获取第二级分类
        for (Map<String, Object> map : firstList)
        {
            int catId = (Integer) map.get("cat_id");

            for (Map<String, Object> temp : catList)
            {

            }
        }
        return cat;
    }
}
