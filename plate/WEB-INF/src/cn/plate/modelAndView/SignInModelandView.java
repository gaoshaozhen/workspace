package cn.plate.modelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import cn.plate.javaBean.ResAndRep;
import cn.plate.javaBean.User;
import cn.plate.service.Service;

public class SignInModelandView
{
    private Logger log = Logger.getLogger(SignInModelandView.class);
    ModelAndView mv;
    User user;
    ResAndRep tp;
    HttpServletRequest request;
    HttpServletResponse response;
    Service service = new Service();;

    public SignInModelandView()
    {
    }

    public ModelAndView getModelAndView(User user, ResAndRep tp)
    {
        mv = new ModelAndView();
        this.user = user;
        this.tp = tp;
        deal();
        return mv;
    }

    private void deal()
    {
        if (service.isUser(user))
        {
            mv.addObject(user);
            log.info(user.getId() + "登录成功");
            HttpSession session = tp.getRequest().getSession();
            session.setAttribute("user", user);
            mv.setViewName("redirect:/service/init");
        }
        else
        {
            log.info("登录失败");
            mv.setViewName("redirect:/login");
        }
    }
}
