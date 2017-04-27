function login(){
	var param = {
		"username":$("#user-form .username").val(),
		"password":$("#user-form .password").val(),
	}
	console.info(param);
	if (param.username.length < 4 && param.password.length < 4) {
		alert("请输入有效信息");
		return;
	}else{
		$("#user-form").submit();
	}
}