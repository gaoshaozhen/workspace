	var chat={	
			friendId:[],//朋友id
			friendNum:0,//朋友数量
			allMessage:[],//接收到的所有消息  [id,message]
			messageNumber:0,//接收消息总数		
			id:"",  //用户id
			chattingId:"",//当前聊天朋友id
			inline:false,
			init:function(){
				var hr=$.trim(window.location.href);
				var myId=hr. substring(hr.indexOf("id=")+3);
				chat.id=$.trim(myId);//获得id
				//this.addFriend(myId);//增加朋友
				this.receive();
				this.logining();
				$("#wrap .main-container .footer .find").click(function(){
					var panel=$("#wrap .main-container .panel");
					panel.empty();
					var input=$("<input type='text'/>");
					var btn=$("<input>",{"type":"button","value":"chat"});
					panel.empty();
					panel.append(input);
					panel.append(btn);
					btn.click(function(){
						var searchId=$.trim(input.val());//当前与输入id对话
						if(searchId==chat.id){alert("不能与自己对话");return;}//不能与自己对话
						$.ajax({//检查对话id是否在线，离线则不对话
							url:"IdLoginConditionServlet",
							data:{"id":searchId},
							type:"get",
							cache:false,
							async:true,
							success:function(message){//开始对话
								message=$.trim(message);
								if(message=="1"){
									chat.chattingId=searchId;
									var messageView=$("#wrap .container .chat-panel");
									var messageHeader=$("#wrap .container .chat-panel .header");
									var mess=$("#wrap .container .chat-panel .panel-body");
									var text=$("#wrap .container .chat-panel .panel-footer .text");
									var receiveChat=chat.showNowMessage(chat.chattingId,mess);
									messageHeader.empty();//清空聊天显示信息
									mess.empty();
									text.val("");
									var sendBtn=$("#wrap .container .chat-panel .panel-footer .send");
									messageView.css({"display":"block"});
									messageHeader.append("<span>"+searchId+"</span>");//显示朋友id
									var closeBtn=$("<input type='button' value='关闭'>");
									messageHeader.append(closeBtn);//添加关闭按钮
									closeBtn.click(function(){
										messageHeader.empty();//清空聊天显示信息
										mess.empty();
										text.val("");
										clearInterval(receiveChat);
										messageView.css({"display":"none"});
										
									});
									sendBtn.click(function(){
										var sendText=$.trim(text.val());
										if(sendText!=""){
											chat.sendMessage(sendText,mess);
											text.val("");
										}
									});
										
								}else{
									alert("该id未上线");
								}
							},
							error:function(message){
								document.write(message.responseText);
							}
						});
					});
				});
			},
			logining:function(){//与服务器保持通话，保持登录状态,每2s发送一次
				var time=setInterval(function(){
					$.ajax({
						url:"loginingServlet",
						data:{"id":chat.id},
						type:"get",
						cache:false,
						async:true,
						success:function(){
						},
						error:function(message){
							document.write(message.responseText);
						}
					});
				},1000*4);
			},
			addFriend:function(id){//增加朋友
				this.friendId[this.friendNum]=id;
				this.friendNum++;
			},
			sendMessage:function(text,panel){	//发送消息			
				var receivedId=chat.chattingId;
						$.ajax({
						url:"transmitServlet",
						type:"post",
						data:{"id":chat.id,"friends":chat.chattingId,"message":text},//发送者id+接受者id+消息
						cache:false,
						async:true,
						success:function(message){
							if(message!="0"){
								console.log("向"+chat.chattingId+"发送成功");
							}
							else{
								console.log("发送失败");
							}
						},
						error:function(message){
							document.write(message.responseText);
						}
					});					
				var t=$("<div class='txt'></div>");	
				t.text(text);
				panel.append(t);
				panel.append($("<div style='width:100%;height:3px;'></div>"));
				panel[0].scrollTop =panel[0].scrollHeight;
			},
			showNowMessage:function(talkId,panel){//显示当下聊天接收消息
				var position=0;
				var time=setInterval(function(){
					for(var i=position;i<chat.messageNumber;i++){
						if(talkId==chat.allMessage[i][0]){
							var te=chat.allMessage[i][1];
							var t=$("<div class='message'></div>");	
							t.text(te);
							panel.append(t);
							panel.append($("<div style='width:100%;height:3px;'></div>"));
							panel[0].scrollTop =panel[0].scrollHeight;
						}
					}
					position=chat.messageNumber;
				},110);
				return time;
			},
			receive:function(){//接收所有朋友消息	,存入chat.allMessage[id,message]			
				var time=setInterval(function(){
					$.ajax({
						url:"transmitServlet",
						type:"get",
						data:{"id":chat.id},
						cache:false,
						async:true,
						success:function(message){//{"id":"message"}
							if($.trim(message)!="0"){
								var mess=JSON.parse(message);
								var m=mess.message;
								var num=m.length;
								var sendId;
								for(var i=0;i<num;i++){									
									var newMess=m[i];
									var sendId=$.trim(newMess.sendId);
									var message=$.trim(newMess.message);
											chat.allMessage[chat.messageNumber]=[sendId,message];//id+message
											console.log(chat.allMessage[chat.messageNumber]+":接收成功"+chat.messageNumber);
											chat.messageNumber++;//总消息数目加一
								}
							}
						},
						error:function(message){
							document.write(message.responseText);
						}
					});
				},500);
			},
	};
	(function(){
		chat.init();	
	})();