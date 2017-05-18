package cn.shop.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class Prop
{
    private static List<Map<String, Object>> toList(String json) throws Exception
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        JSONArray array = JSONArray.fromObject(json);
        for(int i = 0, size = array.size(); i < size; i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            JSONObject jsonObject = array.getJSONObject(i);
            map.put("datatype", jsonObject.get("dataType"));
            map.put("hidden", jsonObject.get("hidden"));
            map.put("name", jsonObject.get("name"));
            map.put("options", jsonObject.get("options"));
            map.put("required", jsonObject.get("required"));
            map.put("type", jsonObject.get("type"));
            map.put("unit", jsonObject.get("unit"));
            map.put("valStr", jsonObject.get("valStr"));
            map.put("value", jsonObject.get("value"));
            map.put("valueList", jsonObject.get("valueList"));
            
            JSONArray nums = jsonObject.getJSONArray("nums");
            List<Integer> numsList = new ArrayList<Integer>();
            for(int j = 0, numsLen = nums.size(); j < numsLen; j++)
            {
                numsList.add(nums.getInt(j));
                
            }
            map.put("nums", numsList);
            JSONArray optionAr = jsonObject.getJSONArray("optionAr");
            List<String> optionArList = new ArrayList<String>();
            for(int j = 0, numsLen = nums.size(); j < numsLen; j++)
            {
                optionArList.add(optionAr.getString(j));
                
            }
            map.put("optionAr", optionArList);
            
            JSONArray optionMap = jsonObject.getJSONArray("optionMap");
            List<Map<String, Object>> optionMapList = new ArrayList<Map<String, Object>>();
            for(int j = 0, optionMapLen = optionMap.size(); j < optionMapLen; j++)
            {
                JSONObject optionMapItem = optionMap.getJSONObject(j);
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("num", optionMapItem.get("num"));
                item.put("selected", optionMapItem.get("selected"));
                item.put("url", optionMapItem.get("url"));
                item.put("name", optionMapItem.get("name"));
                optionMapList.add(item);
                
            }
            map.put("optionMap", optionMapList);
            result.add(map);
        }                                       
        return result;
    }
    
    public static List<Map<String, Object>> getList(String json)
    {        
        if(json == null)
        {
            return null;
        }
        List<Map<String, Object>> result = null;
        try
        {
            result = toList(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
}
