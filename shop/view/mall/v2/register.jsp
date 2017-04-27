<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>注册</title>
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/global.css">
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/register.css">
		<script type="text/javascript" src="/shop/sto/js/register.js"></script>
	</head>
	<body>
		<div class="row login-box">
			<div class="col-xs-6">
				<p><b>请注册</b></p>
				<form id="register-form" action="/shop/mall/registerId.shtm" method="post">
					<input type="text" name="userClassification" value="2" hidden="true">
					<table class="box-td">
						<tr>
							<td>用户名：</td>
							<td><input type="text" class="username" name="username"/></td>
						</tr>
						<tr>
							<td>注册邮箱：</td>
							<td>
								<input type="text" class="email" name="email"/>		
							</td>
						</tr>					
						<tr>
							<td>密码：</td>
							<td>
								<input type="password" class="password" name="password"/>
							</td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td>
								<input type="password" class="password" name="password"/>
							</td>
						</tr>
					</table>
				</form>
				<button id="login-btn" onclick="register()">注册</button>
				<div class="fen"></div>
			</div>
			<div class="col-xs-6">
				<a href="/shop/mall/login.shtm"></a>			
			</div>
		</div>
	</body>
</html>