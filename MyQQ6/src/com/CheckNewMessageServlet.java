package com;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;

import data.*;
@Controller
public class CheckNewMessageServlet extends HttpServlet{
	private Message message =Message.getInstance();
//	public void init()throws ServletException{
//		
//	}
	@RequestMapping("/CheckNewMessageServlet")
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		String id=request.getParameter("id");
		Map map=message.checkNewMessageNum(id);
		StringBuilder builder=new StringBuilder("{\"all\":[");
		if(map!=null){
			Set set=map.keySet();
			Iterator iter=set.iterator();
			int p=0;
			while(iter.hasNext()){
				String str=(String)iter.next();
				int n=(Integer)map.get(str);
				if(p>0){
					builder.append(",{\"id\":\""+str+"\",\"newMessageNum\":"+n+"}");
				}
				else{
					builder.append("{\"id\":\""+str+"\",\"newMessageNum\":"+n+"}");
				}
				p++;
			}
		}
		builder.append("]}");
		PrintWriter w=response.getWriter();
		w.print(builder);
	}
}
