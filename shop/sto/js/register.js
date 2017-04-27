function register(){
	var param={
		"username":$("#register-form .username").val(),
		"password":$("#register-form .password").val(),
		"email":$("#register-form .email").val(),
	}
	for (var item in param){
		if (param[item].length < 3) {
			alert("请输入有效信息");
			return;
		}
	}
	$("#register-form").submit();
}