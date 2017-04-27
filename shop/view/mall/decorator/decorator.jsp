<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>
		<sitemesh:write property='title' />		
	</title>
	<meta charset="utf-8">
	<style type="text/css">
		*,body,html{
			margin: 0;
			padding: 0;			
		}
	</style>
	<link rel="stylesheet" type="text/css" href="/shop/store/bootstrap/css/bootstrap.min.css">	
	<script type="text/javascript" src="/shop/store/bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript" src="/shop/store/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/shop/sto/css/index.css">
	<sitemesh:write property='head' />
</head>
<body>
	<div id="wrapper" class="container">
		<%@include file="header.jsp"%>
		<%@include file="nav.jsp"%>						
		<sitemesh:write property='body' />
		<%@include file="footer.jsp"%>	
	</div>
</body>
</html>