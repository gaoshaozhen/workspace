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
			<header>
				<div class="col-xs-2 ">我的购物车</div>
				<div class="col-xs-2 ">1.我的购物车</div>
				<div class="col-xs-2 active-bac">2.信息核对单</div>
				<div class="col-xs-2 def_">3.成功提交订单</div>
			</header>
			<table class="table">
				<tbody>
					<tr>
						<th>地址</th>
						<td><input type="text" name="addr" value="${defaultAddress.addr}"></td>
					</tr>
					<tr>
						<th>收货人</th>
						<td>${defaultAddress.name}</td>
					</tr>
					<tr>
						<th>手机</th>
						<td><input type="text" name="mobile" value="${defaultAddress.mobile}"></td>
					</tr>
				</tbody>
			</table>
			<form action="/shop/mall/addOrder.shtm">
				<input type="text" name="cardIds" value="${cartIds}" hidden="true">
				<button>提交订单</button>
			</form>
		</div>					
	</body>
</html>