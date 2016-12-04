package cn.plate.modelAndView;


public class SignInMV
{
    // private Logger log = Logger.getLogger(SignInMV.class);
    // ModelAndView mv = null;
    // UserBean userBean = null;
    // HttpServletRequest request = null;
    // HttpServletResponse response = null;
    // UserService userService = null;
    //
    // public void setUserService(UserService userService)
    // {
    // this.userService = userService;
    // }
    //
    // public void setRequest(HttpServletRequest request)
    // {
    // this.request = request;
    // }
    //
    // public void setUserBean(UserBean userBean)
    // {
    // this.userBean = userBean;
    // }
    //
    // private void deal()
    // {
    // if (userService.isUser(userBean))
    // {
    // mv = new ModelAndView();
    // mv.addObject(userBean);
    // log.info(userBean.getUserId() + "通过验证");
    // HttpSession session = request.getSession(true);
    // session.setAttribute("userId", userBean.getUserId());
    // mv.setViewName("redirect:/service/init");
    // log.info(userBean.getUserId() + "登录成功");
    // }
    // else
    // {
    // log.info("登录失败");
    // mv.setViewName("redirect:/login/sign");// 重定向到登录页面
    // }
    // }
    //
    // public ModelAndView getMV()
    // {
    // deal();
    // return mv;
    // }
}
