<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>

<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	 String name=request.getParameter("path");//路径
	 String text=request.getParameter("text");//内容
	 long time=System.currentTimeMillis();
	 String filePath=request.getRealPath("/")+"myData/note/"+name+"/"+time +".txt";
	// System.out.println(filePath);
	 File file=new File(filePath);
	 if(!file.exists()){
	 	try{
	 		FileOutputStream ou=new FileOutputStream(file);
	 		ou.write(text.getBytes());
	 		ou.close();
	 		out.println("{\"name\":\""+file.getName()+"\",\"time\":\""+file.lastModified()+"\"}");
	 		//System.out.println("=="+file.getPath());
	 	}
	 	catch(IOException e){
	 		e.printStackTrace();
	 		out.println(e.getMessage());
	 	}
	 }
%>
