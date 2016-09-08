package com;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import dao.*;
@Controller
public class DeleteFriendServlet extends HttpServlet{
	private User user=new User();
//	public void init()throws ServletException{
//		
//	}
	@RequestMapping("/DeleteFriendServlet")
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		String id=request.getParameter("id");
		String friend=request.getParameter("friend");
		StringBuilder builder=new StringBuilder("{\"success\":");
		if(user.deleteFriend(id, friend)==true){
			builder.append("true}");
		}
		else{
			builder.append("false}");
		}
		PrintWriter out=response.getWriter();
		out.print(builder);
	}
}
