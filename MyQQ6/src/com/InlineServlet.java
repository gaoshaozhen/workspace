package com;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;

import data.*;
public class InlineServlet extends HttpServlet{
	private InlineIdTime inline=null;
	public void init()throws ServletException{
		inline=InlineIdTime.getInstance();
	}
	/*
	 * {"id":id,"friend":"id1#id2..."},
	 *  {"all":[
				{"id1":id,"isInline",boolean}
				,{"id2":id,"isInline",boolean}...
			]}*/
	@RequestMapping("/InlineServle")
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws IOException{
//			String id=request.getParameter("id");
			String str=request.getParameter("friend");
			PrintWriter out=response.getWriter();
			StringBuilder builder=new StringBuilder("{\"all\":[");
			if(str.indexOf("#")==-1){
				builder.append("]}");
				out.println(builder);
				return;
			}
			String[]f=str.split("#");
			
			for(int i=0;i<f.length;i++){
				if(i>0){
					builder.append(",");
				}
				if(inline.isInline(f[i])){//在线
					builder.append("{\"id\":\""+f[i]+"\",\"isInline\":true}");
				}
				else{//离线
					builder.append("{\"id\":\""+f[i]+"\",\"isInline\":false}");
				}
			}
			builder.append("]}");
			out.println(builder);
	}
}
