<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="name.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>服务</title>
	<link rel="stylesheet" type="text/css" href="${webPath}/static/css/display/display.css">
	<link rel="stylesheet" type="text/css" href="${webPath}/static/css/display/dis.css">
</head>
<body>
	<div id="wrap">
		<div id="header">
			<span class="plate">财务平台</span>
		</div>
		<div id="left-side">
			<div class="order-center-box">
				<div class="order-center">订单中心</div>
				<div class="order-list">
					<ul>
					<li>商品订单</li>
					<li>服务订单</li>
				</ul>	
				</div>	
			</div>
		</div>
		<div id="content"></div>
		<div id="bottom"></div>
	</div>
	<div class="send-msg" id="ee">
		test
	</div>
	<div id="cover"></div>
</body>
	<script type="text/javascript">
		var data={
			webPath:"${webPath}"
		};		
	</script>
	<script type="text/javascript" src="${webPath}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${webPath}/static/js/display.js"></script>
</html>