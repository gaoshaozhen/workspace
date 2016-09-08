var chat={
	user:{
		id:"",
		/*
			"id1",boolean
			"id2",boolean
			.
			.
			.
		*/		
		friend:[]
	},
	//"id1",[{identity:"sender","message":message},{identity:"reveiver","message":message}]
	//"id2",[{identity:"sender","message":message},{identity:"reveiver","message":message}]
	message:[],
	talk:{
		id:"",
		ing:false,
	},
	/*
		init()
		任务1：从url中取得账号id，
		任务2：获得所有好友id,并保存	（地址：/AllFriendServlet）
		任务3：与服务器保持会话，定时发送id以及朋友id，获得朋友在线情况，并更新数据		(地址：/InlineServlet)
	*/
	init:function(){
		var m_url=window.location.href;
		//m_url="as";
		var parameters=m_url.slice(m_url.indexOf("?")+1);
//		console.log(parameters);
		chat.user.id=parameters.slice(parameters.indexOf("=")+1);//获得账号id
//		console.log(chat.user.id+"：登录");
		var task3=function(){//任务3
			var time=setInterval(function(){
				var friendId="";
				var x;
				for(x in chat.user.friend){//#id1#id2...
					if(chat.user.friend[x]!=null)
					friendId+="#"+x;
				}
				if(friendId==""){}
				$.ajax({  
					url:"http://192.168.8.7:8080/MyQQ4/InlineServlet",
					data:{"friend":friendId},
					type:"get",
					cache:false,
					async:true,
					success:function(message){/*成功获得朋友在线情况   {"all":[
																				{"id1":id,"isInline",boolean}
																				,{"id2":id,"isInline",boolean}...
																				]}*/
						//console.log("message="+message);																				
						var json=JSON.parse(message);
						var arr=json.all;
						for( var i=0;i<arr.length;i++){
							if(chat.user.friend[arr[i].id]!=null){
								chat.user.friend[arr[i].id]=arr[i].isInline;//更新朋友在线情况	
							}
						}
					},
					error:function(message){
						document.write(message.responseText);
					}
				});
			},2000);
		};
		$.ajax({  //任务2
			url:"http://192.168.8.7:8080/MyQQ4/AllFriendServlet",
			data:{"id":chat.user.id},
			type:"get",
			cache:false,
			async:true,
			success:function(message){//成功获得所有朋友id   {"all":["id1","id2",...]}				
				var json=JSON.parse(message);
				var allF=json.all;
				//console.log(allF);
				var len=allF.length;
				for(var i=0;i<len;i++){
					chat.user.friend[allF[i]]=false;//[id,boolean]
				}
				//console.log(chat.user.friend);
				task3();//开始任务3
			},
			error:function(message){
				document.write(message.responseText);
			}
		});
	},
	/*
		任务：添加新朋友，成功则更新本地数据	（地址：/AddFriendServlet）
	*/
	addFriend:function(id){
		$.ajax({
			url:"http://192.168.8.7:8080/MyQQ4/AddFriendServlet",
			data:{"id":chat.user.id,"friend":id},
			type:"get",
			cache:false,
			async:true,
			success:function(message){//{"success":boolean}
				var json=JSON.parse(message);
				var x=json.success;
				if (x==true) {
					chat.user.friend[id]=false;
					alert("添加成功！");
				}
				else{
					alert("添加失败！");
				}
			},
			error:function(message){
				document.write(message.responseText);
			}
		});
	},
	/*
		任务：删除指定朋友，成功则更新本地数据	（地址：/DeleteFriendServlet）
	*/
	deleteFriend:function(id){
		$.ajax({
			url:"http://192.168.8.7:8080/MyQQ4/DeleteFriendServlet",
			data:{"id":chat.user.id,"friend":id},
			type:"get",
			cache:false,
			async:true,
			success:function(message){//{"success":boolean}
				var json=JSON.parse(message);
				var x=json.success;
				if (x==true) {
					delete chat.user.friend[id];
					//alert("删除成功！");
				}
				else{
					alert("删除朋友失败！");
				}
			},
			error:function(message){
				document.write(message.responseText);
			}
		});
	},
	/*
		任务：检查是否有新消息，有则提示   		（地址：/CheckNewMessageServlet）
	*/
	checkNewMessage:function(){
		var all=null;
		$.ajax({
			url:"http://192.168.8.7:8080/MyQQ4/CheckNewMessageServlet",
			data:{"id":chat.user.id},
			type:"get",
			cache:false,
			async:true,
			success:function(message){//{all:[id1,number...]}
				var json=JSON.parse(message);
				all=json.all;
			},
			error:function(message){
				document.write(message.responseText);
			}
		});
		return all;
	},
	/*
		任务：指定id若有新消息则取回			（地址：/GetMessageServlet）
	*/
	receive:function(id){
		console.log("开始接受消息：id="+id);
		var time=setInterval(function(){
			$.ajax({
				url:"http://192.168.8.7:8080/MyQQ4/GetMessageServlet",
				data:{"senderId":id,"receiverId":chat.user.id},
				type:"get",
				cache:false,
				async:true,
				success:function(message){//{all:[m1,m2,m3]}
				//console.log("chat.receive():"+message);
					var json=JSON.parse(message);
					var m=json.all;
					if(chat.message[id]==null){
						chat.message[id]=[];
						console.log("增加消息记录"+id);
					}
					var len=m.length;
					for(var i=0;i<len;i++){
						var json={"identity":"receiver","message":m[i]};
						//console.log(chat.message[x]);
						chat.message[id].push(json);
					}
					
					//console.log("receive():chat.message["+id+"]:"+chat.message[id]);	

				},
				error:function(message){
					document.write(message.responseText);
				}
			});
		},500);
		return time;
	},
	/*
		任务：向指定id发送消息，				（地址：/SetMessageServlet）
	*/
	send:function(){

	},
	UI:{
		mainContainer:{//主面板
			header:$("#wrap .main-container  .header"),
			panel:$("#wrap .main-container  .panel"),
			talk:$("#wrap .main-container .footer .talk"),
			friends:$("#wrap .main-container  .footer .friends"),
			find:$("#wrap .main-container  .footer .find"),
			set:$("#wrap .main-container  .footer .set"),
		},//聊天面板
		container:{
			chatContainer:$("#wrap .container .chat-panel"),
			header:$("#wrap .container .chat-panel .header"),
			panel:$("#wrap .container .chat-panel .panel-body"),
			text:$("#wrap .container .chat-panel .panel-footer .text"),
			send:$("#wrap .container .chat-panel .panel-footer .send"),
		},
		//time:null,
		mainContainerTimer:{
			footerTime:null
		},
		talkTime:{
			time1:null,
			time2:null,
			time3:null,
		},

		footerClick:function(){//绑定主面板显示
			chat.UI.mainContainer.talk.click(function(){//显示消息提示
				chat.UI.mainContainer.header.html("会话");
				if(chat.UI.mainContainerTimer.footerTime!=null)
					clearInterval(chat.UI.mainContainerTimer.footerTime);
				chat.UI.mainContainerTimer.footerTime=null;
				chat.UI.mainContainer.panel.empty();//清空显示面板
				var ul=$("<ul>");
				chat.UI.mainContainer.panel.append(ul);
				var updateUl=function(){
					$.ajax({
						url:"http://192.168.8.7:8080/MyQQ4/CheckNewMessageServlet",
						data:{"id":chat.user.id},
						type:"get",
						cache:false,
						async:true,
						success:function(message){//{all:[id:id1,newMessageNum,id:id2,newMessageNum,...]}
							var json=JSON.parse(message);
							arr=json.all;
							var x;
							for(x in arr){
								//if(arr[x].newMessageNum>0){//有新消息
									var temp;
									var allLi=ul.find("li");
									var len=allLi.length;
									var fin=false;
									for(var i=0;i<len;i++){
										if($(allLi[i]).attr("value")==arr[x].id){//已经存在提示记录，进行更新
											var spanMessage=$(allLi[i]).find(".number");
											spanMessage.html(arr[x].newMessageNum);
											fin=true;
											break;
										}
									}
									if(fin==true){
										return;
									}
									else{//没有提示记录，添加
										var spanId=$("<span class='ID'>");
										spanId.css({
											"margin-left":"10px",
											"color":"black"
										})
										spanId.html(arr[x].id);
										var spanMessage=$("<span class='number'>");
										spanMessage.css({
											"float":"right",
											"color":"red",
											"margin-right": "24px"
										});
										spanMessage.html(arr[x].newMessageNum);
										var li=$("<li class='messageList'>");
										li.attr("value",arr[x].id);
										li.css({
											"height":"40px",
											"borderBottom":"1px solid black",
											"line-height":"40px",
											"font-weight":"bold",
											"cursor":"pointer",
											"background":"#FCEBA6"
										});
										li.append(spanId);
										li.append(spanMessage);
										ul.append(li);
										li.click(function(){
											chat.UI.talking($(this).attr("value"));
											$(this).css({
												"background":"white"
											});
											$(this).find(".number").css({
												"color":"#0EB8EA"
											});
										});
									}
							}
						},
						error:function(message){
							document.write(message.responseText);
						}
					});
				};
				updateUl();
				chat.UI.time=setInterval(function(){
					updateUl();
				},1000)
			});
			chat.UI.mainContainer.talk.click();
			chat.UI.mainContainer.friends.click(function(){//显示联系人
				chat.UI.mainContainer.header.html("联系人");
				if(chat.UI.mainContainerTimer.footerTime!=null)
					clearInterval(chat.UI.mainContainerTimer.footerTime);
				chat.UI.mainContainerTimer.footerTime=null;
				chat.UI.mainContainer.panel.empty();//清空显示面板
				var ul=$("<ul>");
				chat.UI.mainContainer.panel.append(ul);
				var showFriend=function(){
					var x;
					for(x in chat.user.friend){
						var spanId=$("<span class='Id'>"+x+"<span>");
						var spanInline=$("<span class='isInline'></span>");
						spanInline.css({"float":"rigth"});
						var li=$("<li>");
						li.attr("value",x);
						li.css({
								"height":"30px",
								"line-height":"30px",
								"borderBottom":"1px solid black",
								"cursor":"pointer",
								"background":"white"
							});
						li.append(spanId);
						li.append(spanInline);
						ul.append(li);
						li.click(function(){
							chat.UI.talking($(this).attr("value"));
						});
						li.mouseover(function(){
							$(this).css({
								"background":"#F5E7B2"
							});
						});
						li.mouseout(function(){
							$(this).css({
								"background":"white"
							});
						});
						
					}
				};
				var updateUl=function(){
					var x;
					var lis=ul.find("li");
					var len=lis.length;
					for(x in chat.user.friend){
						for(var j=0;j<len;j++){
							if($(lis[j]).attr("value")==x){
								if(chat.user.friend[x]){//在线
									$(lis[j]).find(".isInline").text("在线");
								}
								else{//离线
									$(lis[j]).find(".isInline").text("离线");
								}
							}
						}
					}
				};
				showFriend();
				updateUl();
				chat.UI.time=setInterval(function(){
					updateUl();
				},1100);		
			});
			chat.UI.mainContainer.find.click(function(){//显示发现
				chat.UI.mainContainer.header.html("发现");
				if(chat.UI.mainContainerTimer.footerTime!=null)
					clearInterval(chat.UI.mainContainerTimer.footerTime);
				chat.UI.mainContainerTimer.footerTime=null;
				chat.UI.mainContainer.panel.empty();//清空显示面板			
				var idInput=$("<input>");
				var btn=$("<input type='button' value='添加好友'>");
				btn.click(function(){
					var newId=idInput.val().trim();
					chat.addFriend(newId);
				});
				chat.UI.mainContainer.panel.append(idInput);
				chat.UI.mainContainer.panel.append(btn);
			});
			chat.UI.mainContainer.set.click(function(){//显示设置
				chat.UI.mainContainer.header.html("设置");
				if(chat.UI.mainContainerTimer.footerTime!=null)
					clearInterval(chat.UI.mainContainerTimer.footerTime);
				chat.UI.mainContainerTimer.footerTime=null;
				chat.UI.mainContainer.panel.empty();//清空显示面板	
				var ul=$("<ul>");
				chat.UI.mainContainer.panel.append(ul);
				var li=$("<li>");
				li.css({
						"height":"30px",
						"line-height":"30px",
						"borderBottom":"1px solid black",
						"cursor":"pointer",
						"background":"white"
					});
				li.text("管理好友");
				ul.append(li);
				li.click(function(){
					manage();
				});
				var manage=function(){
					chat.UI.mainContainer.panel.empty();//清空显示面板	
					var ul=$("<ul>");
					chat.UI.mainContainer.panel.append(ul);
					var x;
					for(x in chat.user.friend){
						var spanId=$("<span class='Id'>"+x+"<span>");
						var spanDelete=$("<span class='delete'>");
						spanDelete.attr("value",x);
						spanDelete.text("删除好友");
						spanDelete.css({
							"float":"right",
							"color":"red"
						});
						var li=$("<li>");
						li.attr("value",x);
						li.css({
								"height":"30px",
								"line-height":"30px",
								"borderBottom":"1px solid black",
								"cursor":"pointer",
								"background":"white"
							});
						li.append(spanId);
						li.append(spanDelete);
						ul.append(li);
						spanDelete.click(function(){
							var con=confirm("你确定要删除好友 "+$(this).attr("value")+" 吗？");
							if(con){
								chat.deleteFriend($(this).attr("value"));
								$(this).parent("li").remove();	
							}
						});
					}
					
				};
			});
		},
		send:function(id){
			chat.UI.container.text.keypress(function(){
				if(event.keyCode==13){  
     				chat.UI.container.send.click();
    			}  
			});
			chat.UI.container.send.click(function(){
				var mess=chat.UI.container.text.val();
				chat.UI.container.text.val("");	
				if(mess.trim()==""){//未输入任何信息
					return;
				}
				
					// encodeURIComponent
				$.ajax({
					url:"http://192.168.8.7:8080/MyQQ4/SetMessageServlet",
					data:{"senderId":chat.user.id,"receiverId":id,"message":mess},
					type:"post",
					cache:false,
					async:true,
					contentType:"application/x-www-form-urlencoded; charset=utf-8",
					success:function(){
						//console.log("senderId："+chat.user.id+"   receiverId："+id+"   message："+mess+"  发送成功");
					},
					error:function(message){
						document.write(message.responseText);
					}
				});
				var x;
				var temp=false;
				if(chat.message[id]==null){
					chat.message[id]=[];
				}
				var json={"identity":"sender","message":mess};
				chat.message[id].push(json);

				console.log(chat.message[id]);
				/*var div=$("<div class='message'>");
				div.text(mess);
				chat.UI.container.panel.append(div);
				*/
			});
		},
		showMessage:function(id){
			//console.log("开始检查记录");
			var position=0;
			var time=setInterval(function(){
				if(chat.message[id]==null){
				//	console.log("showMessage()：空记录");
					return;
				}
				var temp=chat.message[id];
			//	console.log("showMessage(): message="+temp);
				var len=temp.length;
				for(var i=position;i<len;i++){
					position=len;
				//	console.log("showMessage:():position"+position);
					var div;
					if(temp[i].identity=="sender"){
						div=$("<div class='sendedMessage'>");
					}
					else{
						div=$("<div class='receivedMessage'>");
					}
					div.text(temp[i].message);
					chat.UI.container.panel.append(div);
					chat.UI.container.panel[0].scrollTop = chat.UI.container.panel[0].scrollHeight;
				}
			},300);
			return time;
		},
		talking:function(id){//开始对话或查看记录
			if(chat.UI.talkTime.time1!=null){
				clearInterval(chat.UI.talkTime.time1);
				chat.UI.talkTime.time1=null;
			}
			if(chat.UI.talkTime.time2!=null){
				clearInterval(chat.UI.talkTime.time2);
				chat.UI.talkTime.time2=null;
			}
			if(chat.UI.talkTime.time3!=null){
				clearInterval(chat.UI.talkTime.time3);
				chat.UI.talkTime.time3=null;
			}			
			chat.UI.container.send.unbind("click");
			(chat.UI.container.header).empty();
			(chat.UI.container.panel).empty();
			(chat.UI.container.text).empty();
			chat.UI.container.chatContainer.css({"display":"block"});
			var talkId=$("<span>");
			talkId.html(id);
			var off=$("<input class='offBtn' type='button' value='关闭'>");
			chat.UI.container.header.append(off);
			chat.UI.container.header.append(talkId);
			off.click(function(){//关闭本面板
				if(chat.UI.talkTime.time1!=null){
					clearInterval(chat.UI.talkTime.time1);
					chat.UI.talkTime.time1=null;
				}
				if(chat.UI.talkTime.time2!=null){
					clearInterval(chat.UI.talkTime.time2);
					chat.UI.talkTime.time2=null;
				}
				if(chat.UI.talkTime.time3!=null){
					clearInterval(chat.UI.talkTime.time3);
					chat.UI.talkTime.time3=null;
				}
				chat.UI.container.send.unbind("click");
				chat.UI.container.panel.empty();
				chat.UI.container.text.val("");
				chat.UI.container.chatContainer.css({"display":"none"});
			});
			chat.UI.talkTime.time1 =chat.receive(id);
			chat.UI.send(id);
			chat.UI.talkTime.time2=chat.UI.showMessage(id);
		}
	},
};
(function(){
	chat.init();
	chat.UI.footerClick();
})();