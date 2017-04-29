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
				<div class="col-xs-2 active-bac">1.我的购物车</div>
				<div class="col-xs-2 def_">2.信息核对单</div>
				<div class="col-xs-2 def_">3.成功提交订单</div>
			</header>
			<table class="table">
				<thead>
					<th>图片</th>
					<th>商品名称</th>
					<th>商品积分</th>
					<th>价格</th>
					<th>数量</th>
					<th>小计</th>
					<th>删除</th>
				</thead>
				<tbody>
					<tr>
						<td><img src="#"></td>
						<td>名称</td>
						<td>0</td>
						<td>￥0</td>
						<td>0</td>
						<td>0</td>
						<td><a href="">删除</a></td>
					</tr>
				</tbody>
			</table>
		</div>					
	</body>
</html>