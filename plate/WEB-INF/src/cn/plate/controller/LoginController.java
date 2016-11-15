package cn.plate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.gsz.tools.ContextTool;
import cn.plate.javaBean.UserBean;
import cn.plate.modelAndView.LoginMV;
import cn.plate.modelAndView.SignInMV;

@Controller
@RequestMapping(value = "/login")
public class LoginController
{
    private static Logger log = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/sign")
    public ModelAndView login(HttpServletRequest request)
    {
        log.info("gsz-" + request.getRemoteAddr() + "请求登陆");
        LoginMV login = new LoginMV();
        return login.getMV();
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView checkUser(UserBean userBean, HttpServletRequest request)
    {
        log.info(userBean.getUserId() + "\t" + userBean.getPassword() + "\t"
                + "请求登录");
        SignInMV mv = (SignInMV) ContextTool.getBean("signInMV");
        mv.setUserBean(userBean);
        mv.setRequest(request);
        return mv.getMV();
    }

    @RequestMapping(value = "*")
    public void test(HttpServletRequest request, HttpServletResponse response)
    {
        PrintWriter w = null;
        try
        {
            w = response.getWriter();
            w.print("404");

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            if (w != null)
                w.close();
        }

    }
}
