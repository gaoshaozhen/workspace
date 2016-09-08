package webFilter;
//import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.InlineIdTime;
//import java.util.*;
// 实现 Filter 类
@Controller
public class WebFilter   {
	InlineIdTime users=null;
	String loginUrl="";
   public void  init(FilterConfig config) 
                         throws ServletException{ 
	   users=InlineIdTime.getInstance();
	   loginUrl=config.getServletContext().getInitParameter("host")
				+config.getServletContext().getInitParameter("webName")
				+config.getServletContext().getInitParameter("version")
				+config.getServletContext().getInitParameter("loginUrl");
   }
   @RequestMapping("/*")
public void  doFilter(ServletRequest req,ServletResponse res,FilterChain chain) {
	   HttpServletRequest  request = (HttpServletRequest)req;
	   HttpServletResponse  response=(HttpServletResponse)res;
	   String sourse=request.getRequestURI().trim();
	  // System.out.println("::"+sourse);
	   if(sourse.indexOf("/qq/lib")!=-1
		||sourse.indexOf("/qq/login")!=-1
		||sourse.indexOf("/qq/region")!=-1
		||sourse.indexOf("console")!=-1
		||sourse.indexOf("LoginServlet")!=-1
		||sourse.indexOf("RegionServlet")!=-1){  //该项url不检查session
		  // System.out.println(sourse);
		   return ;
	   }
	   HttpSession session=((HttpServletRequest)request).getSession(false);
		if(session==null){//未登陆，返回登录页面
			response.setContentType("text/html");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location",loginUrl);
		}
		else{//已登录			
			String id=(String) session.getAttribute("id");
			if(id!=null){
				long from=0;
				long end=0;
				from=session.getCreationTime();//登录时间
				end=System.currentTimeMillis();//最后一次访问时间
				users.update(id, from,end);//更新在线状态
				// 把请求传回过滤链
				return;
			}
		}
   }	
}