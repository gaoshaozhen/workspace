package cn.plate.modelAndView;

import org.springframework.web.servlet.ModelAndView;

public class LoginMV
{
    ModelAndView mv;

    public LoginMV()
    {
        mv = new ModelAndView();
    }

    public ModelAndView getModelAndView()
    {
        deal();
        return mv;
    }

    private void deal()
    {
        mv.setViewName("login");
    }
}
