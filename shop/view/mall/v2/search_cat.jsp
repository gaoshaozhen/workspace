<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
	<head>
		<title>列表-${siteInfo.title}</title>
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/mallIndex.css">
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/search_cat.css">
		<script type="text/javascript" src="/shop/sto/js/extends.js"></script>
	</head>
	<body>
		<div class="pack">
			<div class="col-xs-9 clear-md">
				<center><b>全部商品列表</b></center>
				<ul class="box-group">
					<c:forEach items="${goodsList}" var="goods">
						<li class="box-item">
							<a href="/shop/mall/goods.shtm?goodsId=${goods.goods_id}">
								<img alt="${goods.name}"
									src="/shop/imageManager/image.shtm?fileId=${goods.image_default}">
							</a>
							<div class="info">
								<h6><a href="/shop/mall/goods.shtm?goodsId=${goods.goods_id}" title="${product.name}">${goods.name}</a></h6>
								<div>
									<h6>市场价：<strike>${goods.price}</strike></h6>
									<p>商城价：<strong class="cost">${goods.cost}</strong></p>
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