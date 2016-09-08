package com;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;

import dao.*;
public class RegionServlet{	
	User user=new User();
//	public void init()throws ServletException{//读取新id初始值		
//		user=new User();
//	}
	@RequestMapping("/RegionServlet")
	public  void doPost(HttpServletRequest request,   
						HttpServletResponse response)throws ServletException, IOException{//注册新id
		response.setHeader("Content-type","text/html;charset=UTF-8");		
		PrintWriter out=response.getWriter();		
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String password=request.getParameter("password");		
		String newId=user.addUser(name, password);
		if(newId!=null){
			out.println("{\"id\":"+"\""+newId+"\""
					+",\"name\":"+"\""+name+"\""
					+",\"password\":"+"\""+password+"\""+"}");
		}
		else{
			out.println("注册失败");
		}
	}
//	public  void doGet(HttpServletRequest request,//注册新id
//			HttpServletResponse response)throws ServletException, IOException{				
//			/*response.setHeader("Content-type","text/html;charset=UTF-8");	
//			PrintWriter out=response.getWriter();
//			out.println("region");*/		
//	}	

}
