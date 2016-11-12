<%@ page language="java"  pageEncoding="utf-8"%>
<% String base=request.getScheme()
			+"://"
			+request.getServerName()
			+":"
			+request.getServerPort()
			+request.getContextPath()
			+"/"; 
			pageContext.setAttribute("base",base);
%>
<%
	request.setAttribute("webPath",request.getContextPath());
%>