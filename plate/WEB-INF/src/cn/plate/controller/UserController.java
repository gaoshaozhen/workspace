package cn.plate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.plate.javaBean.UserBean;
import cn.plate.service.Door;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    private Logger log = Logger.getLogger(UserController.class);
    static WebApplicationContext webContext;
    static Door door;
    static
    {
        webContext = ContextLoader.getCurrentWebApplicationContext();
        door = (Door) webContext.getBean("door");
    }

    /**
     * @param userBean
     * @param response
     */
    @RequestMapping(value = "/createUser")
    public void createUser(UserBean userBean, HttpServletResponse response)
    {
        // PrintWriter w = null;
        // try
        // {
        // w = response.getWriter();
        // UserService userService = ContextTool.getBean("userService",
        // UserService.class);
        // if (userBean.getUserId() == null && userBean.getPassword() == null)
        // {
        // w.println(false);
        // }
        // else
        // {
        // w.print(userService.addUser(userBean));
        // }
        // }
        // catch (IOException e)
        // {
        // log.error(e.getLocalizedMessage());
        // } finally
        // {
        // if (w != null)
        // {
        // w.close();
        // }
        // }
    }

    @RequestMapping(value = "/getAllUser")
    @ResponseBody
    public String getAllUser()
    {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        Map<String, Object> user = new HashMap<String, Object>();

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        List<Map> list = door.getAllUser();
        if (list != null)
        {
            for (Map<String, Object> userInfo : list)
            {
                array.add(userInfo);
            }
        }

        json.put("list", array);
        log.info("返回结果" + json.toString());
        return json.toString();
    }
}
