<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="name.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>登录</title>
</head>
<body>
	<form action="${webPath}/login/signIn" method="post">
		<table>
			<tr>
				<td>id</td>
				<td><input type="text" name="id"></input></td>
			</tr>
			<tr>
				<td>password</td>
				<td><input type="password" name="password"></input></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="登录"></input>
					<input type="button" value="注册" style="float: right;"></input>
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>