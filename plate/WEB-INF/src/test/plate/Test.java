package test.plate;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.PropertyConfigurator;

public class Test
{
    // private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args)
    {
        PropertyConfigurator.configure("WEB-INF/conf/log4j.properties");
        JSONObject json = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("12", 2132);
        map1.put("34", "sdc");
        map.put("a", new Integer(23));
        map.put("b", "sd");
        map.put("qq", map1);
        json.putAll(map);

        JSONArray array = new JSONArray();
        array.add(map1);
        System.out.println(array.toString());
    }
}
