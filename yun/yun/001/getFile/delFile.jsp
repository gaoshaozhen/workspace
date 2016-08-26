<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
	//获得即将建立的文件名和路径
	String folderName=request.getParameter("fileName");
	String folderUrl=request.getParameter("url");
	System.out.println(folderName);
	String rootPath = request.getContextPath();//项目路径名
	String fileRoot=request.getRealPath("/");//数据根文件在服务器中绝对路径
	String dataRoot=fileRoot+"/data";//数据文件夹服务器绝对路径
	File data=new File(dataRoot);
	try
	{
		if(!data.exists())
		{
			data.mkdirs();
		}
		else
		{
			File newFile=new File(fileRoot+"/"+folderUrl+"/"+folderName);
			if(!newFile.exists())
			{
				newFile.mkdir();
				//out.println("1");//创建成功
			}
		}	
	}
	catch(Exception e)
	{	
		e.printStackTrace();
		//out.println("创建失败");	
		//System.out.prinln(e.toString());//创建失败
	}	
%>
