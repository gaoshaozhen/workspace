<%@ page language="java" pageEncoding="utf-8"%>
<%
	String webName = request.getContextPath();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name ="viewport" content ="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no"> 
		<title></title>
		<sitemesh:write property='title' />
		<sitemesh:write property='head' />
	</head>
	<body>
		<sitemesh:write property='body' />
	</body>
	<link rel="stylesheet" type="text/css" href="<%=webName%>/static/js/bootstrap/css/bootstrap.min.css">
	<script type="text/javascript" src="<%=webName%>/static/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=webName%>/static/js/bootstrap/js/bootstrap.min.js"></script>
</html>