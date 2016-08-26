<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String filePath=request.getParameter("text");
	String path=request.getRealPath("/");
	File file=new File(path+"myData\\note\\"+filePath);
	try{
		file.mkdir();		
	}
	catch(SecurityException e){
		
		out.println(e.getStackTrace());
	}

%>
