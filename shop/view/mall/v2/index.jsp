<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<title>首页</title>
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/mallIndex.css">
		<script type="text/javascript" src="/shop/sto/js/extends.js"></script>
	</head>
	<body>
		<div class="pack">
			<div class="col-xs-9 clear-md">
				<div class="col-md-3 class-list">
					<center><b>全部商品列表</b></center>
					<ul>
						<c:forEach var="cat" items="${catList}">
							<c:if test="${cat.parent_id < 1}">
								<!-- 一级分类 -->
								<li class='list-item <c:if test="${cat.haveChild == true}">have-child</c:if>'>
									<a href="#">${cat.name}</a>
									<!-- 二级分类 -->
									<div class="child" style="display:none">
										<ul>
											<c:forEach var="cat2" items="${catList}">
												<c:if test="${cat2.parent_id == cat.cat_id}">
													<li>
														<a href="">${cat2.name}</a>:
														<!-- 三级分类 -->
														<c:forEach var="cat3" items="${catList}">
															<c:if test="${cat3.parent_id == cat2.cat_id}">
																|<a href="">${cat3.name}</a>
															</c:if>
														</c:forEach>
														<!-- 三级分类 -->
													</li>
												</c:if>
											</c:forEach>
										</ul>
									</div>
									<!--end  二级分类 -->
								</li>
								<!-- 一级分类 -->
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-xs-3 clear-md">
				<%@include file="../decorator/aside.jsp"%>	
			</div>
		</div>
	</body>
</html>