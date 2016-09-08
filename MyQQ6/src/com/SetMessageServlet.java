package com;
import java.io.*;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import data.Message;
public class SetMessageServlet extends HttpServlet{
	private Message message=null;
	public void init()throws ServletException{
		message=Message.getInstance();
	}
	@RequestMapping(params ="/SetMessageServlet",method=RequestMethod.GET)
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
			request.setCharacterEncoding("utf-8");
			String receiverId=request.getParameter("receiverId");
			String senderId=request.getParameter("senderId");
			String m=request.getParameter("message");
			if(receiverId==null||senderId==null||m.trim()==""||message==null){
				return;
			}
			System.out.println("senderId:"+senderId+"\treceiverId"+receiverId+"\tmessage"+m);
			message.set(senderId, receiverId, m);
			
	}
	@RequestMapping(params ="/SetMessageServlet",method=RequestMethod.POST)
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
			request.setCharacterEncoding("utf-8");
			String receiverId=request.getParameter("receiverId");
			String senderId=request.getParameter("senderId");
			String m=request.getParameter("message");
			if(receiverId==null||senderId==null||m.trim()==""||message==null){
				return;
			}
			System.out.println("senderId:"+senderId+"\treceiverId"+receiverId+"\tmessage"+m);
			message.set(senderId, receiverId, m);
			
	}
}
