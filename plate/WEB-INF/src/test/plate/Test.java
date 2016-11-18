package test.plate;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.PropertyConfigurator;

public class Test
{
    // private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args)
    {
        PropertyConfigurator.configure("WEB-INF/conf/log4j.properties");
        // Dao dao;
        // dao = WebContext.getBean("dao", Dao.class);
        JSONObject json = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", new Integer(23));
        map.put("b", "sd");
        json.putAll(map);
        System.out.println(json.toString());
    }
}
