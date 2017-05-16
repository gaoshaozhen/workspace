<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("cartList");
	StringBuffer str = new StringBuffer();
	int count = 0;
	for(Map<String, Object> map : list)
	{
		if(count++ >0)
		{
			str.append(",");
		}
		str.append(map.get("cart_id").toString());
	}	
	
	request.setAttribute("cartIdsStr", str);
%>
<!DOCTYPE html>
<html>
	<head>
		<title>购物车-${siteInfo.title}</title>
	</head>
	<body>
		<div class="pack">			
			<header>
				<div class="col-xs-2 ">我的购物车</div>
				<div class="col-xs-2 active-bac">1.我的购物车</div>
				<div class="col-xs-2 def_">2.信息核对单</div>
				<div class="col-xs-2 def_">3.成功提交订单</div>
			</header>
			<table class="table">
				<thead>
					<th>图片</th>
					<th>商品名称</th>					
					<th>价格</th>
					<th>数量</th>
					<th>小计</th>
					<th>删除</th>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(cartList) > 0}">
							<c:set var="cartIds" value="?as" />
							<c:forEach items="${cartList}" var="cart" varStatus="i">								
								<tr>
									<td><img src="#"></td>
									<td>${cart.name}</td>
									<td>${cart.price}</td>
									<td>${cart.num}</td>
									<td>${cart.price * cart.num}</td>
									<td><a href="/shop/mall/deleteCart.shtm?cartId=${cart.cart_id}">删除</a></td>
								</tr>								
							</c:forEach>		
						</c:when>
						<c:otherwise>
							<tr>
								<td>您的购物车空空如也</td>
								<td><a href="/shop/mall/index.shtm">去购物</a></td>
							</tr>
						</c:otherwise>
					</c:choose>					
				</tbody>
			</table>
			<c:if test="${fn:length(cartList) > 0}">
				<a href="/shop/mall/checkout.shtm?cartIds=${cartIdsStr}">结算</a>	
			</c:if>
		</div>					
	</body>
</html>