<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>会员中心-${siteInfo.title}</title>
		<link rel="stylesheet" type="text/css" href="/shop/store/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/member_index.css">
	</head>
	<body>
		<div class="row">
			<aside class="col-md-2 clear-pa aside-center">
				<%@ include file="include/aside.jsp"%>
			</aside>			
			<article class="col-md-10 ">
				<div class="panel panel-default">
					<div class="panel-body">
						<p>您好,${memberInfo.username},欢迎回来</p>	
						<ul>
							<li>会员编号：${memberInfo.memberId}</li>
							<li>等级：${memberInfo.memberLv.name}[<a href="">如何升级</a>]等级积分：${memberInfo.point}</li>
							<li>消费积分：${memberInfo.mp}</li>
							<li>邮箱：${memberInfo.email}</li>
						</ul>
					</div>
				</div>
			</article>
		</div>
		
	</body>
</html>