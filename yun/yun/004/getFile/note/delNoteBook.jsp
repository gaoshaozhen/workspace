<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
	class deleteAllFile{
		public void deleteFiles(File file)throws SecurityException{
			if(file.isFile()){
				file.delete();
			}
			else{
				File [] fs=file.listFiles();
				int number =fs.length;
				if(number==0){
					file.delete();
				}
				else{
					for(int i=0;i<number;i++){
						deleteFiles(fs[i]);
					}
					file.delete();
				}				
			}
		}
	}
	//request.setCharacterEncoding("utf-8");
	String filePath=request.getParameter("path");
	//String name=request.getParameter("name");
	//System.out.println(filePath.toString());
	String path=request.getRealPath("/");
	File file=new File(path+"myData/note/"+filePath);
	//System.out.println("=="+file.getPath());
	if(file.exists()){
		try{
			new deleteAllFile().deleteFiles(file);
			//file.delete();
			//System.out.println("删除成功");	
		}
		catch(SecurityException e){
			out.equals(e.getMessage());
		}
	}
	else
	{
		//System.out.println("文件不存在");
	}
%>
