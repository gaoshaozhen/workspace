var LoginBox = React.createClass({	
	getInitialState : function(){
		return {
			username : "",
			password : ""
		};
	},

	setUsername : function(event){
		console.info(event.target.value);
		this.setState({username : event.target.value});
	},

	setPassword : function(event){
		console.info(event.target.value);
		this.setState({password : event.target.value});
	}, 

	login : function(){
		AppUtil.login(this.state.username, this.state.password, null, function(msg){
			window.location.href=AppUrl.getMemberUrl;
			// console.info(AppUrl);
		});
	},
	render:function(){
		var registUrl = AppUrl.getRegistUrl;
		var loginUrl = AppUrl.getLoginUrl;
		return (
			<div className="row">
				<div className="col-md-6 text-center">
					<p>请登录 已注册用户从这里登录</p>
					<table className="margin-auto">
						<tr>
							<td>账号：</td>
							<td><input type="text" name="username" onChange={this.setUsername}/></td>
 						</tr>
						<tr>
							<td>密码：</td>
							<td><input type="password" name="username" onChange={this.setPassword}/></td>
						</tr>						
					</table>
					<button onClick={this.login}>登录</button><a href={registUrl}>免费注册</a>
				</div>
				<div className="col-md-6 text-center">
					<p>为什么选择我们？</p>
					<p>正品保障 假一赔十</p>
					<p>订单满百全国免费配送</p>
					<p>30天超长退换承诺</p>
					<p>海量产品自由淘</p>
				</div>
			</div>
		);
	}
});