<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="def-color web-header">
    <div class="row header-operator">
        <div class="col-md-6">
            <c:if test="${memberInfo == null}">
                <a href="/shop/mall/login.shtm">登录</a>|
                <a href="/shop/mall/register.shtm">注册</a>    
            </c:if>
            <c:if test="${memberInfo != null}">
                您好：${memberInfo.username}
                <a href="/shop/mall/member_index.shtm">[会员中心]</a>
                <a href="/shop/sign/out.shtm">[退出]</a>                                
            </c:if>
        </div>  
        <div class="col-md-6 text-right">
            <a href="/shop/mall/cart.shtm">购物车</a>|
            <a href="#">订单查询</a>|
            <a href="#">帮助中心</a>
        </div>
    </div>
    <div class="row web-header-info">
        <div class="col-xs-6 web-name">网店系统</div>
        <div class="text-right">
            <!-- 正品保障 价格优势 货到付款 30天退换 -->
            <img src="/shop/sto/image/sycn.jpg">
        </div>
    </div>
</header>