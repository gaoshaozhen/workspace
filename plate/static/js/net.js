var net={
	userInfolist:{},
	user:{},
	
	getAllUser : function(back){
		jQuery.ajax({
			type : "post",
			url : global.webName + "/user/getAllUser",
			dataType : "json",
			error : function(msg){
				console.log("error"+msg);
				result = msg;
			},
			success : function(msg){
				result = msg;
				back(msg);				
			}
		});
	},

	addUser : function(userinfo,back){
		jQuery.ajax({
			type : "post",
			url : global.webName + "/user/addUser",
			data : userinfo,
			dataType : "json",
			error : function(msg){
				back(msg);
			},
			success : function(msg){
				if(msg.result == "success"){
					back("success");	
				}
				else{
					back("failed");		
				}
			}
		});
	}

}