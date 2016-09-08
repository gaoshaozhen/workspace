package com;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import data.*;
import dao.*;
@Controller
public class LoginServlet extends HttpServlet{
	InlineIdTime usersInline=null;	
	User userSql=null;
	String loginUrl="";
	String site="";	
//	public void init()throws ServletException{
//		
//	}
	@RequestMapping("/LoginServlet")
	public  void doPost(HttpServletRequest request,   
			HttpServletResponse response)throws  IOException{
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		if(id!=null&&password!=null&&userSql.isUser(id, password)){//验证成功
			if(!usersInline.isInline(id)){//该账号没有重复登录
				System.out.println("Login："+id+"  Login succeed!");
				HttpSession session=request.getSession(true);//创建session
				session.setMaxInactiveInterval(10);
				session.setAttribute("id", id);
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location",site+id);
			}
			else{//该账号重复登录
				System.out.println("Login:second Login!");
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				PrintWriter out=null;
				out=response.getWriter();
				out.println("Login："+"you are Logining secondly!");
				out.println("<a href=\'"+loginUrl+"\'>Login</a><br>");
				out.close();
			}
		}
		else{  //验证失败
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out=null;
			out=response.getWriter();
			out.println("login faild！");
			out.println("<a href=\'"+loginUrl+"\'>Login</a>");
			out.close();
		}				
	}

	private void t(){
		usersInline=InlineIdTime.getInstance();
		userSql=new User();
		String url=getServletContext().getInitParameter("host")
				+getServletContext().getInitParameter("webName")
				+getServletContext().getInitParameter("version");
		loginUrl=url+getServletContext().getInitParameter("loginUrl");
		site=url+getServletContext().getInitParameter("chatUrl")+"?id=";
	}
}
