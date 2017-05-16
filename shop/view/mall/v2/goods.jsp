<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html>
	<head>
		<meta name="keywords" content="${goodsDetail.meta_keywords}" />
		<meta name="description" content="${goodsDetail.meta_description}" />
		<title>${goodsDetail.name}-${siteInfo.title}</title>
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/mallIndex.css">
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/search_cat.css">
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/goods.css">
		<script type="text/javascript" src="/shop/sto/js/extends.js"></script>
		<script type="text/javascript" src="/shop/sto/js/goods.js"></script>
	</head>
	<body>
		<div class="pack">
			<div class="col-xs-9 clear-md">
				<div class="row">
					<div class="base col-xs-5">相册</div>
					<div class="base col-xs-7">
						<!-- 商品基本信息 -->
						<div class="summary">
							<ul>
								<h5><b>${goodsDetail.name}</b></h5>
								<li class="sn"><span>商品货号： ${productList.get(0).sn}</span></li>
								<li class="price">
									<span>销售价：</span><b id="product-price">${productList.get(0).price}</b> 
								</li>
								<li class="mkt_price">
									<span>市场价：</span> <em>${goodsDetail.mktprice}</em>
								</li>																
								<li class="point">
									<span>赠送积分：<strong>${goodsDetail.point}</strong></span> 
								</li>	
								<li>
									<c:if test="${fn:length(productList) > 1}">
										<span>规格：</span>
										<ul class="pagination" id="standard">
											<c:forEach var="product" items="${productList}" varStatus="i">
												<li class='handle <c:if test="${i.index == 0}">active</c:if>' >
													<a data-price="${product.price}">${product.specs}</a>
												</li>	
											</c:forEach>
										</ul>
									</c:if>
								</li>
							</ul>
						</div>	   
						<form action="/shop/mall/addCart.shtm" id="goodsform" method="post">									
							<div class="choose">
								<div class="rer_quantity">
									<label>订购数量：</label>
									<div class="Numinput" id="num">
										<i class="fa fa-minus-square fa-2x handle" id="minus-product"></i>
										<input id="product-num" type="text" value="1" oldvalue="1" size="5" name="num" autocomplete="off" style="vertical-align: super;text-align: center;" readonly="true">
										<i class="fa fa-plus-square fa-2x handle" id="plus-product"></i>
									</div>								
								</div>
								<div class="btn_box">	
									<input type="hidden" name="productId" value="${productList.get(0).product_id}">
									<input type="hidden" name="itemtype" value="4">											 	
									<div class="btn">	
										<c:if test="${goodsDetail.store > 0}">
											<input type="submit" class="buynow_btn" value="立即购买">		
										</c:if>
										<c:if test="${goodsDetail.store <= 0}">
											<label style="color: red">暂时缺货</label>
										</c:if>
									</div>	
									<div class="btn">
										<input type="button" class="addtocart_btn" value="加入购物车" id="addbutton" style="display:none">	
									</div>
								</div>	
							</div>
							<div style="clear:both;"></div>
						</form>
						<!--分享-->
						<div class="rer_partake">
							<div id="ckepop">
								<span class="jiathis_txt">分享到：</span>
								<a class="jiathis_button_qzone" title="分享到QQ空间"><span class="jiathis_txt jtico jtico_qzone"></span></a>
								<a class="jiathis_button_tsina" title="分享到微博"><span class="jiathis_txt jtico jtico_tsina"></span></a>
								<a class="jiathis_button_renren" title="分享到人人网"><span class="jiathis_txt jtico jtico_renren"></span></a>
								<a class="jiathis_button_kaixin001" title="分享到开心网"><span class="jiathis_txt jtico jtico_kaixin001"></span></a>
								<a class="jiathis_button_tqq" title="分享到腾讯微博"><span class="jiathis_txt jtico jtico_tqq"></span></a>
								<a class="jiathis_button_tsohu"></a>
								<a class="jiathis_button_t163"></a>
								<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
							</div>							
							<a href="javascript:;" class="favorite" goodsid="262">加入收藏</a>
						</div>
						<!--分享结束-->
					</div>
				</div>
				<div class="row">
					<div class="rer_intro">
						<h2>商品介绍</h2>
					</div>
					<div class="rer_para" style="display: none;">
						<!-- 商品属性 -->
						<table class="table table-bordered table-condensed">
							<caption class="text-left">商品属性</caption>
							<tbody>								
								<tr>
									<th>产地：</th>
									<td>3</td>
								</tr>
								<tr>
									<th>包装：</th>
									<td>礼盒装</td>
								</tr>									
							</tbody>
						</table>
						<!-- 基本参数 -->
						<table class="table table-bordered table-condensed">
							<caption class="text-left">基本参数</caption>
							<tbody>
								<tr class="def-font">
									<th class="shadow-color">重量</th>
									<td>100g</td>
								</tr>
							</tbody>
						</table>
					</div>

					<section>
						${goodsDetail.intro}
					</section>
				</div>
			</div>
			<div class="col-xs-3 clear-md">
				<%@include file="../decorator/aside.jsp"%>	
			</div>
		</div>
		<script type="text/javascript">
			var jiathis_config = {
				     url:"http://localhost:8080/javashop//goods-262.html",
				     title:"思妍丽夏日美白护理套餐",
				     pic:"http://javashop3.javamall.com.cn/statics/attachment/zftgoods/201205212338027742_small.jpg"
			}
		</script>
		<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script><script type="text/javascript" src="http://v2.jiathis.com/code/plugin.client.js" charset="utf-8"></script><script type="text/javascript" src="http://v2.jiathis.com/code/plugin.client.js" charset="utf-8"></script>		
	</body>
</html>