package com;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.*;
import data.Message;
public class GetMessageServlet extends HttpServlet{
	Message message=Message.getInstance();
//	public void init()throws ServletException{
//		
//	}
	@RequestMapping("/GetMessageServlet")
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		String receiverId=request.getParameter("receiverId");
		String senderId=request.getParameter("senderId");
		List list=message.get(receiverId, senderId);
		StringBuilder builder=new StringBuilder("{\"all\":[");
		int size=list.size();
		for(int i=0;i<size;i++){
			if(i>0){
				builder.append(",");
			}
			builder.append("\""+(String)list.get(i)+"\"");
		}
		builder.append("]}");
		response.setCharacterEncoding("utf-8");
		PrintWriter w=response.getWriter();
		w.print(builder);
		//System.out.println("Get:"+builder);
	}
}
