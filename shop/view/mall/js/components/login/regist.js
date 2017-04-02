var RegistBox = React.createClass({	
	render:function(){
		var loginUrl = AppUrl.getLoginUrl;
		console.info(loginUrl);
		return (
			<div className="row">
				<div className="col-md-6 text-center">
					<p>请注册 欢迎注册会员！1分钟额按成注册，您可以获赠28元优惠券</p>
					<table className="margin-auto">
						<tr>
							<td>用户名：</td>
							<td><input type="text" name="username"/></td>							
						</tr>
						<tr><td></td><td>4-20个字符,中英文数字组成</td></tr>
						<tr>
							<td>注册邮箱：</td>
							<td><input type="email" name="username"/></td>
						</tr>						
						<tr><td></td><td>邮箱将用于登录和取回密码</td></tr>
						<tr>
							<td>密码：</td>
							<td><input type="password" name="username"/></td>
						</tr>
						<tr><td></td><td>6--16个英文字母或数字</td></tr>
						<tr>
							<td>确认密码：</td>
							<td><input type="password" name="username"/></td>
						</tr>
						<tr><td></td><td>请再次输入密码</td></tr>
					</table>
					<button>注册新用户</button>
				</div>
				<div className="col-md-6 text-center">
					<p>已有账号？请直接登录</p>
					<p><a href={loginUrl}>立即登录</a></p>
				</div>
			</div>
		);
	}
});