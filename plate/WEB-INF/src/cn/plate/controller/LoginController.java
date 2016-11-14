package cn.plate.controller;

import javax.servlet.http.HttpServletRequest;

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
    public ModelAndView login()
    {
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

}
