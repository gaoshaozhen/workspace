package cn.shop.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.SiteMenuDao;

public class MenuLoadInterceptor extends HandlerInterceptorAdapter
{
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception
    {

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
        SiteMenuDao dao = (SiteMenuDao) SpringContextUtil
                .getBean("siteMenuDao");
        List<Map<String, Object>> list = dao.getAllSiteMenuList();
        Vector<Integer> vector = new Vector<Integer>();
        
        for (Map<String, Object> map : list)
        {
            if ((Integer) map.get("parentid") > 0)
            {
                vector.add((Integer) map.get("parentid"));
            }
            
        }
        for (Map<String, Object> map : list)
        {
            int menuId = (Integer)map.get("menuid");
            
            if(vector.contains(menuId))
            {
                map.put("hasChild", 1);
            }
            else
            {
                map.put("hasChild", 0);
            }
        }
        modelAndView.addObject("siteMenuList", list);
        request.setAttribute("siteMenuList", list);
        logger.debug("添加菜单栏数据成功");
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
