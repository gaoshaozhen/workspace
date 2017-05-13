package cn.shop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.base.Configuration;
import cn.shop.base.UserClassification;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.MemberDao;
import cn.shop.dao.UserDao;
import cn.shop.model.LoginInfo;
import cn.shop.model.MemberInfo;
import cn.shop.security.TokenManager;

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
            @RequestParam Map<String, Object> param, HttpSession session)
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
    @RequestMapping(value = "in.shtm", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        LoginInfo loginInfo;
        MemberInfo memberInfo = null;
        String username;
        String password;
        UserClassification userClassification;
        UserDao userDao;
        MemberDao memberDao;
        List<?> list;
        Map<String, Object> dbParam = new HashMap<String, Object>();
        Cookie cookie;
        username = param.get("username");
        password = param.get("password");
        userClassification = UserClassification.get(param
                .get("userClassification"));
        if (username == null || password == null
                || userClassification == UserClassification.UNKNOWN) // 必要参数不能为空，重定向为登陆页
        {
            logger.debug("缺少参数");
            switch (userClassification)
            {
                case ADMIN:
                    return "redirect:/view/login.html";
                case MEMBER:
                    return "redirect:/mall/login.shtm";
                default:
                    return "redirect:/view/login.html";
            }
        }
        session.setAttribute("userClassification", userClassification);
        // 管理员登陆处理
        if (userClassification == UserClassification.ADMIN)
        {
            dbParam.put("username", username);
            dbParam.put("password", password);

            userDao = (UserDao) SpringContextUtil.getBean("userDao");
            list = (List<?>) userDao.getUser(dbParam);
            if (list == null || list.size() < 1)
            {
                return "redirect:/view/login.html";
            }
            else
            {
                loginInfo = new LoginInfo();
                loginInfo.setParam((Map<String, Object>) list.get(0));
                session.setAttribute("loginInfo", loginInfo);
            }
            TokenManager tokenManager = new TokenManager();
            Configuration configuration = (Configuration) SpringContextUtil
                    .getBean("configuration");
            Map<String, Object> claims = new HashMap<String, Object>();
            String token = tokenManager.createJWTString("user", new Date(),
                    configuration.get("key"),
                    String.valueOf(loginInfo.getUserId()), claims);
            cookie = new Cookie("token", token);
            cookie.setPath("/shop/");
            cookie.setMaxAge(3600000);
            response.addCookie(cookie);
            return "redirect:/view/index.html"; // 登录成功
        }
        // 会员登录处理
        else
        {
            dbParam.put("uname", username);
            dbParam.put("password", password);

            memberDao = (MemberDao) SpringContextUtil.getBean("memberDao");
            list = (List<?>) memberDao.getMember(dbParam);
            if (list == null || list.size() < 1)
            {
                return "redirect:/mall/login.shtm";
            }
            else
            {
                memberInfo = new MemberInfo();
                memberInfo.setUserClassification(userClassification);
                memberInfo.setParam((Map<String, Object>) list.get(0));
                session.setAttribute("memberInfo", memberInfo);
                TokenManager tokenManager = new TokenManager();
                Configuration configuration = (Configuration) SpringContextUtil
                        .getBean("configuration");
                Map<String, Object> claims = new HashMap<String, Object>();
                String token = tokenManager.createJWTString("user", new Date(),
                        configuration.get("key"),
                        String.valueOf(memberInfo.getMemberId()), claims);
                cookie = new Cookie("token", token);
                cookie.setPath("/shop/");
                cookie.setMaxAge(3600000);
                response.addCookie(cookie);
                return "redirect:/mall/member_index.shtm"; // 登录成功
            }
        }
    }

    /**
     * 登录处理。
     * 
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "login.shtm123")
    public String login(@RequestParam Map<String, String> param,
            HttpSession session)
    {
        logger.debug("进入登录页面");

        return "login";
    }

    /**
     * 登出处理,移除sessino中的身份标识。
     * 
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "out.shtm")
    public String signOut(HttpServletRequest request,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        UserClassification UserClassification = (UserClassification) session
                .getAttribute("userClassification");

        switch (UserClassification)
        {
            case ADMIN:
                session = request.getSession(true);
                return "redirect:/view/login.html";
            case MEMBER:
                session = request.getSession(true);
                return "redirect:/mall/login.shtm";
            default:
                session = request.getSession(true);
                return "redirect:/view/login.html";
        }
    }
}
