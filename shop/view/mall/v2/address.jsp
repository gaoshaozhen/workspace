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
						  	<li class="active"><a href="/shop/mall/address.shtm">收货地址管理</a></li>
						  	<li><a href="/shop/mall/addAddress.shtm">新增收货地址</a></li>
						</ul>
						<table class="table table-bordered table-hover table-striped table-condensed">
							<thead>
								<th>收货人</th>
								<th>地址</th>
								<th>手机</th>
								<th>邮编</th>
								<th>默认</th>
								<th>操作</th>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(addressList) > 0}">
										<c:forEach items="${addressList}" var="address" varStatus="i">
												<tr>
													<td>${address.name}</td>
													<td>${address.addr}</td>
													<td>${address.mobile}</td>
													<td>${address.zip}</td>
													<td>${address.def_addr == 0 ? "否" : "是"}</td>
													<td><a href="">修改</a>|<a href="/shop/mall/deleteAddress.shtm?addressId=${address.addr_id}">删除</a></td>
												</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										您尚无订单
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</article>
		</div>
	</body>
</html>