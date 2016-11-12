package cn.plate.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.plate.javaBean.ResAndRep;
import cn.plate.javaBean.User;
import cn.plate.modelAndView.LoginMV;
import cn.plate.modelAndView.SignInModelandView;

@Controller
public class LoginController
{
    private static Logger log = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        LoginMV login = new LoginMV();
        return login.getModelAndView();
    }

    @RequestMapping(value = "/login/signIn", method = RequestMethod.POST)
    public ModelAndView checkUser(User user, HttpServletRequest request)
    {
        log.info(user.getId() + "\t" + user.getPassword());
        SignInModelandView s = new SignInModelandView();
        ResAndRep tp = new ResAndRep();
        tp.setRequest(request);
        return new SignInModelandView().getModelAndView(user, tp);
    }

}
