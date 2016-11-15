package cn.plate.modelAndView;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class LoginMV
{
    Logger log = Logger.getLogger(LoginMV.class);
    ModelAndView mv;

    public LoginMV()
    {
        mv = new ModelAndView();
    }

    public ModelAndView getMV()
    {
        deal();
        return mv;
    }

    private void deal()
    {
        mv.setViewName("login");
    }
}
