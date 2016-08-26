	var menu={
		test:function(){
			var s=$("#container #control ")
		},
		showAllPicture:function(area){
			var pictureArea=$(area);
			pictureArea.html("");
			var createP=function(url,name){
				$("#container #showFile .control .files-control").css({"display":"none"});
				var di="<div style=' width: 100px; height:100px; background: url("+url+");background-size: cover;'> </div>";
				var pic=$(di);
				var nn=$("<div style='overflow:hidden;'></div>");
				nn.html(name);
				var pp=$("<div style='width:100px;height:120px;margin:20px;float:left;'></div>");
				pp.append(pic);
				pp.append(nn);
				pictureArea.append(pp);
			} 					
			$.ajax({
				url:"getFile/getAllPicture.jsp",
				type:"post",
				cache:false,
				async:true,
				success:function(allFileName){					
					//createP();
					var tt=JSON.parse(allFileName);
					console.log(tt);
					var file=tt.file;
					var num=file.length;
					for(var i=0;i<num;i++){
						createP(file[i].url,file[i].name);
					}
				},
				error:function(message){
					document.write(message.responseText);
				}
			});
		},
		showAllDocument:function(area){
			$.ajax({
				url:"getFile/getAllDocument.jsp",
				type:"post",
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
		createFilesListLi:function(name,icon){
			var ListLi;
			
			return ListLi;
		}
	};
	(function(){
		menu.test();
		var m=$("#container #menu-List .file-Search .search");
		m.mouseover(function(){
			m.css({"cursor":"pointer"});			
		});
		m.click(function(){
			var menuSelected=$(this).text();
			switch(menuSelected){
				case "全部文件":window.location.href=window.location.href;break;
				case "图片":menu.showAllPicture("#container #showFile .file-folder");	break;
				case "文档":break;
				case "视频":break;
				case "种子":break;
				case "音乐":break;
				case "其他":break;
				case "我的分享":break;
				case "回收站":break;
				default:break;
			}
		});
	})();