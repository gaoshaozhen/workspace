package com;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;

import dao.*;
public class AllFriendServlet extends HttpServlet{//获得朋友信息
	User user=new User();;
//	public void init()throws ServletException{
//		
//	}
	//发送所有好友id
	@RequestMapping("/AllFriendServlet")
	public void doGet(HttpServletRequest request,HttpServletResponse response)//  {"all":["id1","id2",...]}	
			throws ServletException,IOException{
		String id=request.getParameter("id");
		if(id==null||id.trim()==""){//检查id
			return;
		}
		String[]str=user.getAllFriends(id);
		StringBuilder builder=new StringBuilder("{\"all\":[");
		for(int i=0;i<str.length;i++){
			if(i>0){
				builder.append(",\""+str[i]+"\"");
			}
			else{
				builder.append("\""+str[i]+"\"");
			}
		}
		builder.append("]}");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.println(builder);
		//System.out.println("getFriend():"+builder);
	}
//	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
//		request.setCharacterEncoding("utf-8");
//	}
}
