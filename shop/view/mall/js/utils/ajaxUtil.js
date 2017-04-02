var AjaxUtil = {
	getUserInfo : function(errorFun, fun){
		$.ajax({
			type : "get",
			url : AppUrl.getUserInfoUrl,
			error : function(msg){
				if (errorFun != null) {
					fun(errorFun);
				}
			},
			success : function(msg){
				if (fun != null) {
					fun(msg);
				}
			}
		});
	}
};