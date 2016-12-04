<%@ page language="java" pageEncoding="utf-8"%>
<%
	String webName = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>登录</title>
</head>
<body>
	<form action="${webPath}/login/signIn" method="post" id="loginForm">
		<table>
			<tr>
				<td>userId</td>
				<td><input type="text" name="username"></input></td>
			</tr>
			<tr>
				<td>password</td>
				<td><input type="password" name="password"></input></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="button" value="登录" onclick="login.submitLoginForm()"></input>
					<input type="button" value="注册" style="float: right;"></input>
				</td>
			</tr>
		</table>
		
	</form>
</body>
<script type="text/javascript" src="<%=webName%>/static/js/jquery/jquery.min.js"></script>
<script type="text/javascript">
	var login={
		submitLoginForm : function(){
			var form = jQuery("#loginForm").serialize();
			$.ajax({
               type: "post",
               url: "<%=webName%>/login/loginIn",
               error: function(){alert("提交失败");},
               data: form,
               dataType:"json",
               timeout: 5000,
               success: function(msg)
               {
               		console.log(msg);
               		if(msg.result=="success"){
               			window.location="<%=webName%>/panel/"
               		}
               		else{
               			alert("登录失败");
               		}
               		
               }
            });
		}
	}
</script>
</html>