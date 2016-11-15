package cn.plate.modelAndView;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public class ServiceMV
{
    ModelAndView mv;
    HttpServletRequest request;

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    private void deal()
    {
        mv = new ModelAndView("display");
    }

    public ModelAndView getMv()
    {
        deal();
        return mv;
    }
}
