<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<title>首页</title>
	</head>
	<body>
		<div class="input-group">
		    <input type="text" class="form-control input-lg">
		    <span class="input-group-addon btn btn-primary">搜索</span>
		</div>
		<div class="imghead" style="height: 200px;width: 100%;background: green">
			
		</div>
		<div class="panel default-panel">
			<div class="panel">
				<a href="/${initParam.webName}/shop/buy?goodsName=商品名&goodsId=123" style="display: inline-block;">
					<img width="50px" height="60px" src="/${initParam.webName}/sto/image/shop.gif">
					<center>商品1</center>	
				</a>
				<a href="" style="display: inline-block;">
					<img width="50px" height="60px" src="/${initParam.webName}/sto/image/shop.gif">
					<center>商品2</center>	
				</a>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</body>
	<link rel="stylesheet" type="text/css" href="/${initParam.webName}/store/bootstrap/css/bootstrap.min.css">
	<script type="text/javascript" src="/${initParam.webName}/store/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/${initParam.webName}/store/bootstrap/js/bootstrap.min.js"></script> 
	<link rel="stylesheet" type="text/css" href="/${initParam.webName}/sto/css/footer.css"> 
	<script type="text/javascript">
		
	</script>
</html>