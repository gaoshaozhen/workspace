<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html>
	<head>
		<title>首页</title>
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/mallIndex.css">
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/search_cat.css">
		<script type="text/javascript" src="/shop/sto/js/extends.js"></script>
	</head>
	<body>
		<div class="pack">
			<div class="col-xs-9 clear-md">
				<center><b>全部商品列表</b></center>
				<ul class="box-group">
					<c:forEach items="${productFirstList}" var="product">
						<li class="box-item">
							<a href="/shop/mall/goods.shtm?goodsId=${product.goods_id}">
								<img alt="${product.name}" src="http://javashop3.javamall.com.cn/statics/attachment/zftgoods/201205212338027742.jpg">
							</a>
							<div class="info">
								<h6><a href="/shop/mall/goods.shtm?goodsId=${product.goods_id}" title="${product.name}">${product.name}</a></h6>
								<div>
									<h6>市场价：<strike>${product.price}</strike></h6>
									<p>商城价：<strike class="cost">${product.cost}</strike></p>
								</div>	
							</div>
						</li>
					</c:forEach>
				</ul>		
				<c:if test="${totalPage > 1}">
					<div class="text-center">
						<ul class="pagination">
							<c:forEach var="i" varStatus="" begin="1" end="${totalPage}">
								<li <c:if test="${pageNumber == i}">class="active"</c:if>><a  href="/shop/mall/search_cat.shtm?pageSize=${pageSize}&pageNumber=${i}&catId=${catId}">${i}</a></li>	
							</c:forEach>					    
						</ul>
					</div>						
				</c:if>
			</div>
			<div class="col-xs-3 clear-md">
				<%@include file="../decorator/aside.jsp"%>	
			</div>
		</div>
	</body>
</html>