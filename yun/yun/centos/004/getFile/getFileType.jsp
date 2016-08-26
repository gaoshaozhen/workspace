<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
	String filePath=request.getParameter("url"); 
	//System.out.println(filePath);
	String p=request.getRealPath("/");
	File file=new File(p+"/"+filePath);//数据根文件夹
	//System.out.println(file.getPath());
	if(file.exists())
	{ 
		//System.out.println("存在");
	}
	else
	{
		//System.out.println("不存在");
	}	
	File []files=file.listFiles();
	StringBuffer allFileName=new StringBuffer("{");
	StringBuffer fileStr=new StringBuffer("\"file\":[");
	StringBuffer folderStr=new StringBuffer("\"folder\":[");
	int num=files.length;
	int folderNum=0;
	int fileNum=0;
	if(num>0)
	{
		if(num==1)
		{
			if(files[0].isFile())
			{
				fileStr.append("{\"name\":\""+files[0].getName()+"\"}");
			}
			else
			{
				folderStr.append("{\"name\":\""+files[0].getName()+"\"}");
			}
		}
		else
		{
			for(int i=0;i<num;i++)
			{
				if(files[i].isDirectory() )//文件夹
				{
					folderNum++;
					if(folderNum > 1)
					{
						folderStr.append(",{\"name\":\""+files[i].getName()+"\"}");
					}
					else
					{
						folderStr.append("{\"name\":\""+files[i].getName()+"\"}");
					}									
				}
				else//文件
				{
					fileNum++;
					if(fileNum > 1)
					{
						fileStr.append(",{\"name\":\""+files[i].getName()+"\"}");
					}
					else
					{
						fileStr.append("{\"name\":\""+files[i].getName()+"\"}");
					}		
				}
			}		
		}
			
	}		
	allFileName.append(folderStr.toString()+"],"+fileStr.toString()+"]}");
	out.println(allFileName.toString());
	//System.out.println(new Date());
%>