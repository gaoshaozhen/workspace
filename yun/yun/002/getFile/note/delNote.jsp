<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	
	//System.out.println(request.getContentType());
	request.setCharacterEncoding("utf-8");
	String filePath=request.getParameter("path");
	String name=request.getParameter("name");
	//System.out.println(filePath.toString());
	String path=request.getRealPath("/");
	File file=new File(path+"myData\\note\\"+filePath+"\\"+name);
	//System.out.println("=="+file.getPath());
	if(file.exists()){
		try{
			file.delete();
		}
		catch(SecurityException e){
			out.equals(e.getMessage());
		}
	}
	else
	{
		System.out.println("文件不存在");
	}

%>
