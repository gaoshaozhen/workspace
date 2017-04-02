var AppUtil = {
	login : function(username, password, errFun, sucFun){
		// $.ajax({
		// 	type : "get",
		// 	url : AppUrl.getLoginUrl,
		// 	data : {"username" : username, "password" : password},
		// 	error : function(msg){
		// 		if (errFun != null) {
		// 			errFun(msg);
		// 		}
		// 	},
		// 	success : function(msg){
		// 		if (sucFun != null) {
		// 			sucFun(msg);
		// 		}
		// 	}
		// });
		sucFun(null);
	},

	getUserName : function(fun){
		$.get(AppUrl.getUserInfoUrl, null, function(msg){
			if (fun != null) {
				fun(msg.username);
			}
		});
	}	
};