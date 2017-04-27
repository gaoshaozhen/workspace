<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn" %>
<!DOCTYPE html>
<html>
	<head>
		<title>会员中心</title>
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
					 <div class="panel-heading">
				        <h3 class="panel-title">我的订单</h3>
				    </div>
					<div class="panel-body">
						<ul class="nav nav-pills">
						  	<li><a href="/shop/mall/getOrder.shtm">所有订单</a></li>
						  	<li><a href="/shop/mall/getOrder.shtm?orderStatus=0">等待付款</a></li>
						  	<li><a href="/shop/mall/getOrder.shtm?orderStatus=1">已付款待确认</a></li>
						  	<li><a href="/shop/mall/getOrder.shtm?orderStatus=2">已付款</a></li>
						  	<li><a href="/shop/mall/getOrder.shtm?orderStatus=3">配货中</a></li>
						  	<li><a href="/shop/mall/getOrder.shtm?orderStatus=4">已发货</a></li>
						  	<li><a href="/shop/mall/getOrder.shtm?orderStatus=5">已取消</a></li>
						  	<li><a href="/shop/mall/getOrder.shtm?orderStatus=3">已成功</a></li>
						</ul>
						<c:choose>
							<c:when test="${fn:length(orderList) > 0}">
								<c:forEach items="${orderList}" var="order" varStatus="i">
										${order}			
								</c:forEach>
							</c:when>
							<c:otherwise>
								您尚无订单
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</article>
		</div>
	</body>
</html>