package cn.plate.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.plate.pojo.UserPojo;
import cn.plate.service.Door;
import cn.plate.service.model.UserModel;

@Controller
@RequestMapping(value = "/login")
public class LoginController
{
    private static Logger log = Logger.getLogger(LoginController.class);
    static WebApplicationContext webContext;
    static Door door;
    static
    {
        webContext = ContextLoader.getCurrentWebApplicationContext();
        door = (Door) webContext.getBean("door");
    }

    // 登录页面
    @RequestMapping(value = "/**")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        log.info("gsz-" + request.getRemoteAddr() + "请求登陆页面");
        // LoginMV login = new LoginMV();

        return "login/login";
    }

    // 账号登录验证
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public void checkUser(UserPojo user, HttpServletRequest rq,
            HttpServletResponse rp)
    {
        UserModel userInfo = (UserModel) door.getUser(user);
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = new JSONObject();

        if (userInfo != null)
        {
            HttpSession session = rq.getSession(true);
            session.setAttribute("user", user);
            map.put("result", "success");
            log.info("登录者:" + user.getUsername() + "验证通过");
        }
        else
        {
            map.put("result", "fail");
            log.info("登录者:" + user.getUsername() + "验证失败");
        }
        json.putAll(map);
        log.info("登录者:" + user.getUsername());
        PrintWriter w = null;
        try
        {
            w = rp.getWriter();
            w.print(json.toString());
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        } finally
        {
            if (w != null)
            {
                w.close();
            }
        }
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(HttpServletResponse rp)
    {

        ApplicationContext context = null;

        WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();

        ServletContext servletContext = webApplicationContext
                .getServletContext();
        PrintWriter w = null;
        try
        {
            w = rp.getWriter();
            w.println("test");
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
        } finally
        {
            if (w != null)
            {
                // w.close();
            }
        }
        return "{\"we\":\"ww\"}";
    }
}
