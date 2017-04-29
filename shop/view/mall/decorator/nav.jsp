<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="nav-menu-list">
	<div class="navbar navbar-default" role="navigation">
		<div class="container-fluid">		
			<ul class="nav navbar-nav">
				<c:forEach items="${siteMenuList}" var="firstMenu">
					<c:if test="${firstMenu.parentid lt 1}">						
						<li>
							<a href="${firstMenu.url}" <c:if test="${firstMenu.url != '/shop/mall/index.shtm'}">data-toggle="dropdown" </c:if>>
								${firstMenu.name}<c:if test="${firstMenu.hasChild > 0}"><b class="caret"></b></c:if>
							</a>
							<c:if test="${firstMenu.hasChild > 0}">
								<ul class="dropdown-menu">
									<c:forEach items="${siteMenuList}" var="secondMenu">
										<c:if test="${secondMenu.parentid == firstMenu.menuid}">
											<li><a href="${secondMenu.url}">${secondMenu.name}</a></li>	
										</c:if>
									</c:forEach>
								</ul>
							</c:if>
						</li>	
					</c:if>					
				</c:forEach>
			</ul>
		</div>
	</div>
</nav>

