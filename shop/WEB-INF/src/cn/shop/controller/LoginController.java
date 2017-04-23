package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.dao.UserDao;
import cn.shop.model.LoginInfo;

/**
 * 登录处理
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/sign/")
public class LoginController
{
    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * 获得用户列表
     * 
     * @return
     */
    @RequestMapping(value = "login.action")
    @ResponseBody
    public Object getUser(HttpServletRequest request,
            @RequestParam Map<String, Object> param,
            HttpSession session)
    {
        Set<String> set = param.keySet();
        Map<String, Object> map = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        List list;

        logger.debug("查询用户信息");
        for (String key : set)
        {
            logger.debug(key + ":" + param.get(key));
        }
        UserDao userDao = (UserDao) context.getBean("userDao");
        list = (List) userDao.getUser(param);
        if (list != null && list.size() > 0)
        {
            session = request.getSession(true);
            session.setAttribute("user", list.get(0));
            map.put("result", true);
        }
        else
        {
            map.put("result", false);
        }

        return map;
    }

    /**
     * 登录处理。
     * 
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "in.shtm")
    public String signIn(@RequestParam Map<String, String> param,
            HttpSession session)
    {
        LoginInfo loginInfo = new LoginInfo();
        String username;
        String password;

        username = param.get("username");
        password = param.get("password");

        if (username == null || password == null) // 必要参数不能为空
        {
            return "redirect:/login.shtm";
        }

        session.setAttribute("loginInfo", loginInfo);
        return "";
    }

    /**
     * 登录处理。
     * 
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "login.shtm")
    public String login(@RequestParam Map<String, String> param,
            HttpSession session)
    {

        return "";
    }


    /**
     * 登出处理。
     * 
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "out.shtm")
    public String signOut(@RequestParam Map<String, String> param,
            HttpSession session)
    {
        session.removeAttribute("loginInfo");
        return "redirect:/login.shtm";
    }
}
