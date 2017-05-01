<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html>
	<head>
		<title>首页</title>
	</head>
	<body>
		<div class="pack">			
			<header class="row">
				<div class="col-xs-2 ">我的购物车</div>
				<div class="col-xs-2 ">1.我的购物车</div>
				<div class="col-xs-2 active-bac">2.信息核对单</div>
				<div class="col-xs-2 def_">3.成功提交订单</div>
			</header>
			<article class="row">
				<p>订单提交成功,<a href="/shop/mall/order.shtm">查看订单</a></p>	
			</article>			
			
		</div>					
	</body>
</html>