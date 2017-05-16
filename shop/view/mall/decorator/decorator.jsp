<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>
		<sitemesh:write property='title' />		
	</title>	
	<meta charset="utf-8">

	<meta name="keywords" content="${siteInfo.keywords}" />

	<link rel="stylesheet" type="text/css" href="/shop/store/bootstrap/css/bootstrap.min.css">		
	<link rel="stylesheet" type="text/css" href="/shop/store/font-awesome-4.7.0/css/font-awesome.min.css">
	<script type="text/javascript" src="/shop/store/bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript" src="/shop/store/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/shop/sto/css/index.css">
	<link rel="stylesheet" type="text/css" href="/shop/sto/css/global.css">
	<sitemesh:write property='head' />
</head>
<body>
	<div id="wrapper" class="container">
		<%@include file="header.jsp"%>
		<%@include file="nav.jsp"%>						
		<div class="row"><sitemesh:write property='body' /></div>
		<%@include file="footer.jsp"%>	
	</div>
</body>
</html>