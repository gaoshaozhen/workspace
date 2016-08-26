<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
	String path=request.getRealPath("/");
	File f=new File(path+"/myData/note");
	System.out.println(f.getPath());
	File []  files =f.listFiles();
	int number=files.length;
	StringBuffer names=new StringBuffer("{\"books\":[{\"name\":\"默认记事本\"}");
	for(int i=0;i<number;i++){			
		if(files[i].isDirectory()){//是目录文件
			String str=files[i].getName();
			if(!str.equals("默认记事本")){
				names.append(",{\"name\":\""+str+"\"}");
			}			
		}				
	}	
	names.append("]}");
	out.println(names.toString());
%>
