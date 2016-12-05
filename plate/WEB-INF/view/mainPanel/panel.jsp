<%@ page language="java" pageEncoding="utf-8"%>
<%
	String webName = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>商城管理系统</title>
</head>
<body>
	<!--主题-->
	<header>
		<div id="system-header" class="col-xs-12">
			<h1>商城管理系统</h1>
		</div>
	</header>
	<!-- 左侧菜单栏 -->
	<aside>
		<div id="menu" class="col-xs-1 panel panel-default">
			<div class="panel-heading point manage-user" onclick="home.allUserInfo()">
        		用户管理
    		</div>
			<ul>
				<li></li>
			</ul>
		</div>
	</aside>

	<!-- 主要内容 -->
	<article>
		<div id="content" class="col-xs-11"></div>
	</article>

	<!-- 页脚 -->
	<footer>
		<div id="system-footer" class="col-xs-12">
			<hr/>
			<center>高少振</center>	
		</div>
	</footer>
</body>
<link rel="stylesheet" type="text/css" href="<%=webName%>/static/css/home.css">
<link rel="stylesheet" type="text/css" href="<%=webName%>/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=webName%>/static/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=webName%>/static/js/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	#system-header{
		width: 100%;
		height: 60px;
		background-color: blue;
	}
	#menu{
		height: 550px;
		overflow: auto;
		background-color: #E6E6E6;
		padding-left: 0;
		padding-right: 0;
	}
	#content{
		/*height: 900px;*/
		background-color:#F3F3F3; 
		overflow-x: auto;
		color: black;
	}
	#system-footer{
		position: relative;
		bottom: 0;
		width: 100%;
	}	
</style>
<script type="text/javascript" src="<%=webName%>/static/js/net.js"></script>
<script type="text/javascript" src="<%=webName%>/static/js/home.js"></script>
<script type="text/javascript">
	var global={
		webName : "<%=webName%>"
	}
</script>
</html>