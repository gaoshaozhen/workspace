	var createNote={
		path:"默认记事本",
		addnote:function(area,left,top){//建立新记事标签//屏蔽界面
			var screenWidth=screen.availWidth;
	 		var screenHeight=screen.availHeight;
	   		var outScreen=$("<div>");
			outScreen.css({"background":"#DBD9DD",
						  "position":"fixed",
						  	"width":"100%",
	   						"height":screenHeight+"px",
	   						"left":"0px",
	   						"top":"0px",
	   						"opacity":"0.5",
						  });
			var nei=$("<div>");
			nei.css({"border":" 3px  #2067BD solid",
					"width":"202px",
					  "height":"316px",
					 "position":"absolute",
					 "left":left+"px",
					 "top":top+"px"});
			
			var text=$("<textArea style='width:99%;height:280px;font-size:14px;margin:0px;padding:0px'></textArea>");
			nei.append(text);
			var con=$("<input type='button' value='保存' style='background:blue ;color:white;height: 25px;width: 57px;' >");
			var qu=$("<input type='button' value='取消'  style='background:blue ;color:white;height: 25px;width: 57px;'>");
			nei.append(con);
			nei.append(qu);	
			var conClickNum=0;
			con.click(function(){//保存写下的内容
				conClickNum++;
				if(conClickNum==1){
					var txt=$.trim(text.val());					
					$.ajax({//提交记录的内容
						url:"getFile/note/addNote.jsp",
						type:"post",
						data:{"path":createNote.path,"text":txt},
						cache:false,//不缓存
						async:true,//异步提交
						success:function(message){
							//document.write( typeof message);
							//console.log("++"+); 
							//if(message=="true")
							var res=JSON.parse(message);
							var fileName=res.name;
							var d = new Date();
							d.setTime(res.time);
							createNote.showNewNote(area, txt,fileName,d.toLocaleDateString());
						},
						error:function(message){
							document.write(message.responseText);
						},
						
					});
					outScreen.remove();
				}								
			});	
			var quClickNum=0;
			qu.click(function(){//取消记事，不保存
				quClickNum++;
				if(quClickNum==1){
					outScreen.remove();
				}				  
			});				  
			$("body").append(outScreen.append(nei));    	
		},
		showNewNote:function(area,txt,fileName,bTime){//显示新记事
			var di=$("<div style='width:210px;height:200px;margin:15px;float:left;border:1px black solid;'></div>")
			var txtArea=$("<textArea readonly='readonly' style='border:0px;width:210px;height:200px;margin:0px;padding:0px;'></textArea>");
			txtArea.text(txt);
			di.append(txtArea);
			var fileTime=$("<div style='float:left;width:100px;color:#0EF3EA;'>"+bTime+"</div>");
			var la=$("<div style='float:right;width:38px;color:#0EF3EA;'>删除</div>");
			var po=$("<div style='display:none;height:21px;position:relative;top:-24px;background:#4D4D4D;'></div>");
			po.append(la);
			po.append(fileTime);
			la.css({"cursor":"pointer"});
			la.attr("value",fileName);			
			la.click(function(){//删除该记事
				var uu=createNote.path;
				$.ajax({
					type:"post",
					url:"getFile/note/delNote.jsp",
					data:{"path":"默认记事本","name":la.attr("value")},
					cache:false,
					async:true,
					contentType:"application/x-www-form-urlencoded; charset=utf-8",
					success:function(message){
						alert("删除成功");
						//console.log({"path":createNote.path,"name":la.attr("value")});
						//console.log(createNote.path);
					},
					error:function(message){
						document.write(message.responseText);
					}
				});
				di.remove();
				//console.log(la.attr("value"));
			});
			di.append(po); 
			di.mouseenter(function(){
				po.css({"display":"block"});
			});
			di.mouseleave(function(){
				po.css({"display":"none"});
			});
			$(area).append(di);					
		},
		addNewBook:function(area,name){//添加单个新记事本
			var screenWidth=screen.availWidth;
	 		var screenHeight=screen.availHeight;
	   		var outScreen=$("<div>");
			outScreen.css({"background":"#DBD9DD",
						  "position":"fixed",
						  	"width":"100%",
	   						"height":screenHeight+"px",
	   						"left":"0px",
	   						"top":"0px",
	   						"opacity":"0.5",
						  });
			var nei=$("<div>");
			nei.css({"border":" 3px  #2067BD solid",
					"width":"424px",
					 "height":"148px",
					 "position":"absolute",
					 "left":"200px",
					 "top":"200px"});
			
			var text=$("<input type='text' style='width:80%;height:48px;font-size:14px;margin:25px 0px 0px 40px;padding:0px'></input>");
			nei.append(text);
			var con=$("<input type='button' value='保存' style='background:blue ;color:white;height: 41px;width: 79px; font-size:1em;margin:12px 0px 0px 104px;' >");
			var qu=$("<input type='button' value='取消'  style='background:blue ;color:white;height: 41px;width: 79px; font-size:1em;margin:12px 0px 0px 60px;'>");
			nei.append($("<br/>"));
			nei.append(con);
			nei.append(qu);	
			var conClickNum=0;
			con.click(function(){//保存写下的内容
				conClickNum++;
				if(conClickNum==1){
					var txt=$.trim(text.val());					
					$.ajax({//提交记录的内容
						url:"getFile/note/addNoteBook.jsp",
						type:"post",
						data:{"text":txt},
						cache:false,//不缓存
						async:true,//异步提交
						success:function(message){
							createNote.showBook(area, txt);												
						},
						error:function(message){
							document.write(message.responseText);
						},
						
					});
					outScreen.remove();
				}								
			});	
			var quClickNum=0;
			qu.click(function(){//取消记事，不保存
				quClickNum++;
				if(quClickNum==1){
					outScreen.remove();
				}				  
			});				  
			$("body").append(outScreen.append(nei));    
		},
		showAllNote:function(area){//显示所有记事
			//{"name":"txt"}			
			$.ajax({
				url:"",
				type:"post",
				cache:false,
				async:true,
				success:function(message){
					var alltxts=JSON.parse(message);
					var txts=alltxts.txt;
					var num=txts.length;
					for(var i=0;i<num;i++){//显示所有记事信息
						createNote.showNewNote(area, txts[i]);
					}
				},
				err:function(message){
					
				}
			});							
		},
		showNoteBook:function(area,noteBook){//显示指定记事本 “noteBook”中的记事
			$.ajax({//请求记事本 “noteBook”中的所有 记事
				url:"getFile/note/getDefaultNote.jsp",
				type:"post",
				data:{"name":noteBook},
				cache:false,
				async:true,
				success:function(message){
					//document.writeln(message);					
					var alltxts=JSON.parse(message);
					var texts=alltxts.txts;
					var num=texts.length;
					createNote.path=noteBook;
					$(area).empty();
					$("#container #showFile .control .book-name").remove();
					$("#container #showFile .control").append($("<span class='book-name'>/"+noteBook+"<span>"))
					for(var i=0;i<num;i++){//显示所有记事信息
						var d = new Date();
						d.setTime(texts[i].time);
						createNote.showNewNote(area, texts[i].txt,texts[i].name,d.toLocaleDateString());
					}
				},
				error:function(message){
					document.write(message.responseText);
				}
			});
		},
		showBook:function(area,name){//显示记事本name
			var newtxt=$('<li class="txt" ></li>');
			
			newtxt.append($("<span class='text'>"+name+"</span>"));
			newtxt.css({"cursor":"pointer"});
			var del=$("<div style='color:blue;display:none;float:right;'>删除</div>");
			newtxt.mouseenter(function(){
				del.css({"display":"inline"});				
			}); 
			newtxt.mouseleave(function(){
				del.css({"display":"none"});
			});
			del.click(function(event){//删除该记事本
				event.stopPropagation();
				$.ajax({
					url:"getFile/note/delNoteBook.jsp",
					data:{"path":name},
					type:"post",
					async:true,
					cache:false,
					success:function(message){
						//alert()
						newtxt.remove();
						if(createNote.path==name){
							$("#container #showFile .mainView").empty();							
							createNote.path="默认记事本";
						}
					},
					error:function(message){
						document.write(message.responseText);
					}					
				});				
			});
			if(name!="默认记事本"){
				newtxt.append(del);
			}			
			newtxt.click(function(){
				var m=$.trim(newtxt.find(".text").text());								
				createNote.showNoteBook("#container #showFile .mainView",m);
			});
			$(area).append(newtxt);
		},
		showAllBook:function(area){
			$("#container #showFile .control .book-name").remove();
			$("#container #showFile .control").append($("<span class='book-name'>/<span>"));
			$.ajax({//请求记事本
				url:"getFile/note/getAllNoteBook.jsp",
				type:"post",				
				cache:false,
				async:true,
				success:function(message){
					console.log(message);
					var alltxts=JSON.parse(message);
					var txts=alltxts.books;
					var num=txts.length;
					//createNote.path=noteBook;
					for(var i=0;i<num;i++){//显示所有记事信息
						createNote.showBook(area, txts[i].name);
						
					}
				},
				error:function(message){
					document.write(message.responseText);
				}
			});
		}
	};
	(function(){
		createNote.showAllBook("#container #menu-List .book-list");
		$("#container #menu-List .book-list .txt").css({"cursor":"pointer"});
		
		$("#container #showFile .control .create-note").click(function(){//创建新记事
			createNote.addnote("#container #showFile .mainView",266, 119);
		});
		$("#container #showFile .control .create-book").click(function(){//创建新记事本
			createNote.addNewBook("#container #menu-List .book-list");
		});
		/*$("#container #menu-List .book-list .txt").click(function(){//显示指定记事本的所有记事
			var m=$.trim($(this).find(".text").text());
			console.log(m);
			createNote.showNoteBook("#container #showFile .mainView",m);
		});*/
	})();