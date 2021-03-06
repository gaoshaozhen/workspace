package cn.plate.controller;

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
import cn.plate.pojo.UserPojo;
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

    // 获得所有用户信息
    @RequestMapping(value = "/getAllUser")
    @ResponseBody
    public String getAllUser()
    {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        List<Map<String, Object>> list = door.getAllUser();
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

    // 添加新用户
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public String addUser(UserPojo userPojo)
    {
        JSONObject json = new JSONObject();
        if (userPojo.getUsername() == null || userPojo.getPassword() == null
                || userPojo.getPowerId() < 0)
        {
            json.put("result", "fail");
        }
        else
        {
            if (door.addUser(userPojo) > 0)
            {
                json.put("result", "success");
            }
            else
            {
                json.put("result", "fail");
            }
        }
        return json.toString();
    }

    // 删除用户
    @RequestMapping(value = "deleteUser")
    @ResponseBody
    public String deleteUser(UserPojo userPojo)
    {
        JSONObject json = new JSONObject();
        if (userPojo.getUsername() == null)
        {
            json.put("result", "fail");
        }
        else
        {
            if (door.delete(userPojo) > 0)
            {
                json.put("result", "success");
            }
        }
        return json.toString();
    }

    // 更新用户信息
    @RequestMapping(value = "updateUser")
    @ResponseBody
    public String updateUser(UserPojo userPojo)
    {
        JSONObject json = new JSONObject();
        if (userPojo.getUsername() == null)
        {
            json.put("result", "fail");
        }
        else
        {
            if (door.delete(userPojo) > 0)
            {
                json.put("result", "success");
            }
        }
        return json.toString();
    }
}
