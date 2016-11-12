package cn.plate.modelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import cn.plate.javaBean.User;

public class ServiceModelAndView
{
    ModelAndView mv;
    HttpServletRequest request;

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    private void deal()
    {
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            mv = new ModelAndView("display");
            System.out.println("未登录");
        } else
        {
            User user = (User) session.getAttribute("user");
            if (user == null)
            {
                System.out.println("未登录用户");
                mv = new ModelAndView("redirect:/login");
            } else
            {
                mv = new ModelAndView("display");
            }
        }
    }

    public ModelAndView getMv()
    {
        deal();
        return mv;
    }
}
