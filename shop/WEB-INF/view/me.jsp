<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<title>个人中心</title>
	</head>
	<body>
		<header class="identity">
			<div class="col-xs-12">
				<a href="#" >
					<span class="glyphicon glyphicon-cog"></span>
				</a>
			</div>
			<img src="/${initParam.webName}/sto/image/me.jpg" width="56px" height="56px"/>
		</header>
		<article>
			<div class="panel panel-default">
				<div class="panel-body">
					<a href="" class="col-xs-3">
						<center>
							<div class="box">待付款</div>
						</center>
					</a>
					<a href="" class="col-xs-3">
						<center>
							<div class="box">待收货</div>
						</center>
					</a>
					<a href="" class="col-xs-3">
						<center>
							<div class="box">待评价</div>
						</center>
					</a>
					<a href="" class="col-xs-3">
						<center>
							<div class="box">返修/退换</div>
						</center>
					</a>
				</div>
			</div>
			<div class="panel default-pabel">
				<div class="panel-body">
					<a href="" class="col-xs-3">
						<center>
							<div class="box">我的订单</div>
						</center>
					</a>
					<a href="" class="col-xs-3">
						<center>
							<div class="box">客户服务</div>
						</center>
					</a>
				</div>
			</div>
		</article>
		<div class="container"> 
    <div id="example" class="modal fade" style="display: none;background: white;"> 
        <div class="modal-header"> 
            <a class="close" data-dismiss="modal">×</a>
            <h3>这是一个模态框标题</h3> 
        </div> 
        <div class="modal-body"> 
            <h4>模态框中的文本</h4> 
        </div> 
        <div class="modal-footer"> 
            <a href="#" class="btn btn-success">唤醒活动</a> 
            <a href="#" class="btn" data-dismiss="modal">关闭</a> 
        </div> 
    </div> 
</div> 
		<!-- <%@ include file="footer.jsp"%> -->
	</body>
	<link rel="stylesheet" type="text/css" href="/${initParam.webName}/store/bootstrap/css/bootstrap.min.css">
	<script type="text/javascript" src="/${initParam.webName}/store/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/${initParam.webName}/store/bootstrap/js/bootstrap.min.js"></script> 
	<link rel="stylesheet" type="text/css" href="/${initParam.webName}/sto/css/footer.css"> 
	<link rel="stylesheet" type="text/css" href="/${initParam.webName}/sto/css/me.css"> 
	<style type="text/css">
		body{
			background: #A89C9C;
		}
	</style>
	<script type="text/javascript">
		jQuery("#example").modal();
	</script>
</html>