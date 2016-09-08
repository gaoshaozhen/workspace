package com;
import javax.servlet.*;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import dao.*;
@Controller 
public class AddFriendServlet extends HttpServlet{
	private User user=new User();
//	public void init()throws ServletException{
//		
//	}
	@RequestMapping("/AddFriendServlet")
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		String id=request.getParameter("id");
		String friend=request.getParameter("friend");
		StringBuilder builder=new StringBuilder("{\"success\":");
		if(user.existId(friend)==true){//检查id是否存在
			if(user.addFriends(id, friend)==true){//添加成功
				builder.append("true}");
			}
			else{//添加失败
				builder.append("false}");
			}
		}
		else{
			builder.append("false}");
		}
		PrintWriter out=response.getWriter();
		out.print(builder);
	}
}
