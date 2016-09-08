package console;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import data.*;
public class Console extends HttpServlet{
	InlineIdTime time=InlineIdTime.getInstance();
//	public void init()throws ServletException{
//		time=InlineIdTime.getInstance(); 
//	}
	@RequestMapping("/ConsoleServlet")
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		response.setCharacterEncoding("utf-8");
		HashMap has=time.getLog();
		StringBuilder build=new StringBuilder("{\"all\":[");
		Set<?> set=has.keySet();
		Iterator<?> iter=set.iterator();
		int n=0;
		while(iter.hasNext()){
			String str=(String)iter.next();
			long[] lon=(long[])has.get(str);
			long []t={lon[0],lon[1]};
			if(n>0){
				build.append(",");
			}
			n++;
			build.append("{\"id\":\""+str+"\",\"from\":"+lon[0]+",\"end\":"+lon[1]+"}");
		}
		build.append("]}");
		PrintWriter w=response.getWriter();
		w.println(build);
	}
}
