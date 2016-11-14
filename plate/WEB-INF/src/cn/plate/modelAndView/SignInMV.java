package cn.plate.modelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import cn.plate.javaBean.UserBean;
import cn.plate.service.UserService;

public class SignInMV
{
    private Logger log = Logger.getLogger(SignInMV.class);
    ModelAndView mv = null;
    UserBean userBean = null;
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    UserService userService = null;

    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public void setUserBean(UserBean userBean)
    {
        this.userBean = userBean;
    }

    private void deal()
    {
        if (userService.isUser(userBean))
        {
            mv = new ModelAndView();
            mv.addObject(userBean);
            log.info(userBean.getUserId() + "通过验证");
            HttpSession session = request.getSession();
            session.setAttribute("userBean", userBean);
            mv.setViewName("redirect:/service/init");
            log.info(userBean.getUserId() + "登录成功");
        }
        else
        {
            log.info("登录失败");
            mv.setViewName("redirect:/login/sign");
        }
    }

    public ModelAndView getMV()
    {
        deal();
        return mv;
    }
}
