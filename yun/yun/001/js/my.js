	var my={
		nowPath:"data",		
		showAllFile:function(path){//返回路径 path 下所有文件
			var showfiles=this.showFiles;
			$.ajax({
				url:"getFile/getFileType.jsp",
				type:"post",
				data:{url:path},
				cache:false,
				async:true,
				success:function(allFileName){					
					showfiles(allFileName);					
				},
				error:function(message){
					document.write(message.responseText);
				}
			});	
		},
		showFiles:function(allFileName){	
			var allFiles=JSON.parse(allFileName);
			var folders=allFiles.folder;
			var files=allFiles.file;
			var allFilesNum=allFiles.length;
			var foldersNum=folders.length;//文件夹数目
			var filesNum=files.length;//文件数目
			var areaFile=$("#showFile .file-folder");
			var childs=areaFile.children();
			var num=childs.length;
			if(num>0){
				areaFile.html("");
			}			
			var gUl=$("<ul></ul>",{"class":"gUl"});
			areaFile.append(gUl);			
			for(var i=0;i<foldersNum;i++){	//列出文件夹				
				var gLi=$("<li></li>");
				var gDiv=$("<div></div>",{"class":"im"});
				var im=$("<img></img>",{src:'image/folder.gif'});			
				var gspan=$("<span></span>",{"class":"foldertest"});
				var box=$("<input/>",{"class":"myCheck","type":"checkbox"});
				gspan.html(folders[i].name);
				gDiv.append(im);
				gLi.append(box);
				gLi.append(gDiv);
				gLi.append(gspan);
				gUl.append(gLi);
			}
			for(var i=0;i<filesNum;i++){	//列出文件			
				var gLi=$("<li></li>");
				var gDiv=$("<div></div>",{"class":"im"});
				var im=$("<img></img>",{src:'image/ww.gif'});			
				var gspan=$("<span></span>",{"class":"foldertest"});
				var box=$("<input/>",{"class":"myCheck","type":"checkbox"});
				gspan.html(files[i].name);
				gDiv.append(im);
				gLi.append(box);
				gLi.append(gDiv);
				gLi.append(gspan);
				gUl.append(gLi);
			}
			var fileName=gUl.find("li");
			var LiNum=fileName.length;
			for(var i=0;i<LiNum;i++){//绑定文件夹双击事件
				var m=$(fileName[i]);
				if(m.find("img").attr("src")=="image/folder.gif"){
					m.dblclick(function(e){
						var mm=$(e.target);
						var ms=mm.find(".foldertest");					
						var p=my.nowPath+"/"+ms.html();	
						my.nowPath=p;
						my.showAllFile(p);						
					});
				}
				
			}
			var con=$("#container #showFile .left-right");
			console.log(my.nowPath=="data");
			if(my.nowPath==("data")){
				con.html("<span>全部文件</span>");						
			}
			else{
				var bac=$("<div></div>",{"class":"back"});
				bac.click(function(){
					var d=my.nowPath;
		    	  	var n=d.length;				    	  	
		    	  	n=d.lastIndexOf("/");
		    	  	d=d.substring(0,n);	
		    	  	my.nowPath=d;
					my.showAllFile(d);							
				});
				bac.html("<b>返回上一级</b>");						
				con.html(bac);					
			}
			console.log("path= "+my.nowPath+" "+new Date().toLocaleString());
		},
		addNewFile:function(area){//新建文件夹
			var nowFileUrl=this.nowPath;//当前文件路径
			var nowFirstFile=$(area).first();
			var newFile=$("<li></li>");
			var gDiv=$("<div></div>",{"class":"im"});
			var im=$("<img></img>",{src:'image/folder.gif'});			
			var gspan=$("<span></span>",{"class":"foldertest"});
			var inp=$("<input type='text' value='新建文件夹'>");
			var box=$("<input/>",{"class":"myCheck","type":"checkbox"});
			gDiv.append(im);	
			newFile.append(box);
			newFile.append(gDiv);
			newFile.append(inp);
			nowFirstFile.before(newFile);
			inp[0].focus();
			inp.blur(function(){
				var newName=inp.val();
				var myLi=$(area);
				var num=myLi.length;				
				var pp;
				var text;
				var sameNameNum=0;
				for(var i=1;i<num;i++){
					pp=$(myLi[i]);
					text=pp.find("span").text();
					if(text==newName){
						alert("文件名与已存在文件名重名！");	
						sameNameNum++;						
					}					
				}	
				if(sameNameNum==0){
					gspan.html(newName);
				}
				else{
					 newFile.remove();
					return;
				}				
				inp.remove();
				newFile.append(gspan);	
				$.ajax({
					url:"getFile/addFolder.jsp",
					type:"post",
					data:{"fileName":newName,"url":my.nowPath},
					cache:false,
					async:true,
					success:function(){					
						my.showAllFile(my.nowPath);					
					},
					error:function(message){
						//console.log("错误"+message);
						document.write(message.responseText);
					}
				});
			});
		},
		test:function(){}		
	};
	(function(){
		//var murl=location.href;
		//var mysp=murl.indexOf("?");
		//var can=mysp.substring(mysp+1,murl.length);
		//var pa=path.split("&");
		//console.log(ps[1]);
		//alert( decodeURI(ps[1]));
		//console.log(ps.length);
		//if(pa==1){
			my.showAllFile(my.nowPath);
		//}
		//else{
		//	my.showAllFile(pa);
		//}
		$("#container #showFile .control .up .addNewFile").click(function(){my.addNewFile("#container #showFile .file-folder .gUl li");});
		$("#container #showFile .control .up .downLoad").click(function(){
			var checks=$("#container #showFile .file-folder").find("li .myCheck");
			var len=checks.length;
			var ch;			
			for(var i=0;i<len;i++){//依次下载选中文件
				if(checks[i].checked&&$(checks[i]).next().find("img").attr("src")!="image/folder.gif"){
					
					ch=$(checks[i]).nextAll()[1].innerHTML;
					var p="../"+my.nowPath+"/"+ch;//获得源文件路径
					$("<a></a>",{"type":"checkbox","href":p,"download":""})[0].click();
				}
			}	
		});	
		$("#container #showFile .control .up .file").change(function(){//上传文件
			var form1=$("<form></form>",{
    	  			"action":"getFile/addFile.jsp",
    	  			"method":"post",
    	  			"enctype":"multipart/form-data",
    	  		});
			var pp= $("<input/>",{"type":"text","value":my.nowPath,"name":"path"});
			form1.append(pp);
			form1.append($("#container #showFile .control .up .file"));
			form1.submit();
			//alert("as");
		});
	})();
	