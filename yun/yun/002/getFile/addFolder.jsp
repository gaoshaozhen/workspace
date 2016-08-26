<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//String
	//System.out.println("="+path); 
	//System.out.println("====="); 
	String p=request.getRealPath("/");
	String getPath=request.getParameter("url");
	String name=request.getParameter("fileName");
	File file=new File(p+"/"+getPath+"/"+name);
	System.out.println("="+file.getPath());
	try{
		if(file.exists()){
			out.println("文件夹创建失败！");
		}
		else{
			file.mkdir();
			//System.out.println("succeed");
		}
	}
	catch(Exception e){
		e.printStackTrace();
		//e.printStackTrace()
		out.println("文件夹创建失败！");
	}
	//System.out.println(file.getPath());
	//System.out.println(file.getAbsolutePath());
%>
