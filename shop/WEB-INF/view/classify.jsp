<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<title></title>
	</head>
	<body>
		<aside class="panel default col-xs-3 side">
			<ul class="panel-body menu side">
				<li class="classify-list" onclick="classify.setClassfy(this)">家用电器</li>
				<li class="classify-list" onclick="classify.setClassfy(this)">电脑办公</li>
				<li class="classify-list" onclick="classify.setClassfy(this)">手机数码</li>
			</ul>
		</aside>
		<article class="content col-xs-9" style="padding: 0">
			
		</article>
		<%@ include file="footer.jsp"%>
	</body>
	<link rel="stylesheet" type="text/css" href="/${initParam.webName}/store/bootstrap/css/bootstrap.min.css">
	<script type="text/javascript" src="/${initParam.webName}/store/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/${initParam.webName}/store/bootstrap/js/bootstrap.min.js"></script> 
	<link rel="stylesheet" type="text/css" href="/${initParam.webName}/sto/css/footer.css"> 
	<style type="text/css">
		body{
			background: #A89C9C;
		}
		.side{
			padding: 0;
		}
		.classify-list{
			width: 100%;
			height: 40px;
			cursor: pointer;
			text-align: center;
		}
		.classify-list:hover{
			background: #A89C9C;
		}
		.menu{
			height: 100%;
		}
	</style>
	<script type="text/javascript">
		var classify = {
			setClassfy : function(name){
				var n = jQuery(name).text();
				jQuery.post("/${initParam.webName}/classify/fy",
						{"name" : n},
						function(data, textStatus, jqXHR){
							console.log(data);
							jQuery(".content").html(data);
						},
						"html");
			}
		};
	</script>
</html>