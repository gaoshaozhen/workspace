<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%
	
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String bookName=request.getParameter("name");	
	if(bookName!=null){		
		String bookPath=request.getRealPath("/")+"myData\\note\\"+bookName;
		//System.out.println(bookPath);
		File file=new File(bookPath);
		if((!file.exists())||(!file.isDirectory())){//目录不存在
			System.out.println("目录："+file.getName()+"不存在");
		}
		else {//文件存在且是一个目录文件
			File[] notes=file.listFiles();
			int number= notes.length;
			BufferedReader re;
			StringBuffer text;
			String str;
			StringBuffer message=new StringBuffer("{\"txts\":[");
			if(number>0){
				
				for(int i=0;i<number;i++){
					try{
						text=new StringBuffer();
						re=new BufferedReader(new FileReader(notes[i]));
						while((str=re.readLine())!=null){//读取单个文件内容
							text.append(str);
						}
						re.close();
						if(i>0){
							message.append(",{\"txt\":\""+text.toString()+"\""+",\"name\":\""+notes[i].getName()+"\",\"time\":\""+notes[i].lastModified()+"\"}");
						}
						else{
							message.append("{\"txt\":\""+text.toString()+"\""+",\"name\":\""+notes[i].getName()+"\",\"time\":\""+notes[i].lastModified()+"\"}");
						}
						
					}
					catch(IOException e){
						e.printStackTrace();
					}											
				}				
			}
			message.append("]}");
			out.println(message);
		}
	}
	else{
		System.out.println("没有该记事本！");
	}
	//System.out.println(request.getRealPath("/")+bookName);
%>
