package cn.shop.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.shop.annotation.AuthCheck;
import cn.shop.base.Configuration;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.security.TokenManager;

public class AuthCheckInteceptor extends HandlerInterceptorAdapter
{
    Logger logger = Logger.getLogger(this.getClass());

    /**
     * 普通安全检查。
     * 
     * @return
     */
    private boolean checkLogin()
    {

        return true;
    }

    /**
     * json安全检查。
     * 
     * @return
     */
    private boolean checkJson(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        boolean isValid = false;
        for (Cookie cookie : cookies)
        {
            if (cookie.getName().equals("token"))
            {
                String token = cookie.getValue();

                if (token != null)
                {
                    Configuration configuration = (Configuration) SpringContextUtil
                            .getBean("configuration");
                    isValid = TokenManager.isValid(token,
                            configuration.get("key"));
                }
                break;
            }
        }
        return isValid;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception
    {
        if(handler instanceof HandlerMethod == false)
        {
            return true;
        }
        HandlerMethod methodHandler = (HandlerMethod) handler;
        
        AuthCheck auth = methodHandler
                .getMethodAnnotation(AuthCheck.class);
        if (auth != null && auth.login())
        {
            if (auth.json())
            {
                if (!checkJson(request))
                {
                    StringBuilder sb = new StringBuilder();
                    PrintWriter out = response.getWriter();

                    sb.append("{\"code\":").append(auth.code()).append("}");
                    out.print(sb);
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                if (!checkLogin())
                {
                    String loginTarget = auth.loginTarge();
                    if (loginTarget != null)
                    {
                        response.sendRedirect(loginTarget);
                        return false;
                    }
                    else
                    {
                        logger.warn("未定义登录路径");
                        return false;
                    }
                }
                else
                {
                    return true;
                }
            }
        }

        logger.debug("拦截成功");
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception
    {
        // System.out.println("postHandle");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        // System.out.println("afterCompletion");
    }

}
