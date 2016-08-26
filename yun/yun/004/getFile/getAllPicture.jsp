<%@ page language="java" import="java.util.*,java.io.*,javax.imageio.ImageIO,java.awt.image.BufferedImage,java.Exception.*" pageEncoding="UTF-8"%>
<%
	String p=request.getRealPath("/");
	String dataStr=p+"/data";
	File file=new File(dataStr);	
	class get{
		StringBuffer str=new StringBuffer("{\"file\":[");
		int num=0;
		public void getPicture(File f,String url){
			BufferedImage bi=null;
			if(f.isFile()){//是具体文件
				try{
					bi = ImageIO.read(f);
				}
				catch (IOException e){
					e.printStackTrace();
				}				
				if(bi == null){  //不是图片					
			  	 	//System.out.println(f.getPath()+"此文件不为图片文件");
				}
				else{//是图片						
					if(num==0){
						str.append("{\"name\":\""+f.getName()+"\",\"url\":\""+url+"\"}");
					}
					else{
						str.append(",{\"name\":\""+f.getName()+"\",\"url\":\""+url+"\"}");
					}					
					num++;
				}
			}
			else{  //是文件夹
				File []fs=f.listFiles(); 
				if(fs.length>0){//内部仍含有文件或文件夹
					for(int i=0;i<fs.length;i++){													
						getPicture(fs[i],url+"/"+fs[i].getName());
					}
				}				
			}						
		}
		public String getNames(){
			str.append("]}");
			return str.toString();
		}	
	}	
	get g=new get();
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String fh=basePath+"/data";	
	g.getPicture(file,fh);
	out.println(g.getNames());
%>
