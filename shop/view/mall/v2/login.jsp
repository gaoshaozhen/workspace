<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>登录-${siteInfo.title}</title>
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/login.css">
		<script type="text/javascript" src="/shop/sto/js/login.js"></script>
	</head>
	<body>
		<div class="row login-box">
			<div class="col-xs-6">
				<p><b>已注册用户从这里登录</b></p>
				<form id="user-form" action="/shop/sign/in.shtm" method="post">
					<input type="text" name="userClassification" value="2" hidden="true">
					<table class="box-td">
						<tr>
							<td>账号：</td>
							<td><input type="text" class="username" name="username"/></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td>
								<input type="password" class="password" name="password"/>
								<a href="">忘记密码</a>
							</td>
						</tr>					
					</table>
				</form>
				<button id="login-btn" onclick="login()">登录</button>
				<div class="fen"></div>
			</div>
			<div class="col-xs-6">
				<p>正品保障 假一赔十</p>
				<p>订单满百全国免费配送</p>
				<p>30天超长退换承诺</p>
				<p>海量产品自由淘</p>				
			</div>
		</div>
	</body>
</html>