<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
	<head>
		<meta name="keywords" content="${goodsDetail.meta_keywords}" />
		<meta name="description" content="${goodsDetail.meta_description}" />
		<title>${goodsDetail.name}-${siteInfo.title}</title>
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/goods.css">
		<script type="text/javascript" src="/shop/sto/js/goods.js"></script>
	</head>
	<body>
		<div class="pack">
			<div class="col-xs-9 clear-md">
				<div class="row">
					<div class="base col-xs-5">
						<div class="picture-box">
							<img class="img-box" src="/shop/imageManager/image.shtm?fileId=${goodsDetail.image_default}" alt="${goodsDetail.name}">
						</div>
						<div class="mutil-picture-box">
							<!-- 轮播（Carousel）项目 -->
							<c:forTokens items="${goodsDetail.image_file}" delims="," var="path">
								<div class="min-picture-box">
									<img class="img-box" src="/shop/imageManager/image.shtm?fileId=${path}" alt="图">
								</div>
							</c:forTokens>
						</div>
					</div>
					<div class="base col-xs-7">
						<!-- 商品基本信息 -->
						<div class="summary">
							<ul>
								<h5><b>${goodsDetail.name}</b></h5>
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
										<i class="num-btn fa fa-minus-square fa-2x handle" id="minus-product"></i>
										<input id="product-num" type="text" value="1" oldvalue="1" size="5" name="num" autocomplete="off" style="vertical-align: super;text-align: center;" readonly="true">
										<i class="num-btn fa fa-plus-square fa-2x handle" id="plus-product"></i>
									</div>
								</div>
								<div class="btn_box">
									<input type="hidden" name="productId" value="${productList.get(0).product_id}">
									<input type="hidden" name="itemtype" value="4">
									<div class="btn">
										<c:if test="${goodsDetail.store > 0}">
											<input type="submit" class="buynow_btn btn btn-info" value="立即购买">
										</c:if>
										<c:if test="${goodsDetail.store <= 0}">
											<label style="color: red">暂时缺货</label>
										</c:if>
									</div>
									<div class="btn">
										<input type="button" class="addtocart_btn btn btn-warning" value="加入购物车" id="addbutton">
									</div>
								</div>
							</div>
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
					<div class="rer_para">
						<h4>商品介绍</h4>
						<!-- 商品属性 -->
						<table class="table table-bordered table-condensed">
							<tbody>
								<tr><th colspan="2">商品属性</th></tr>
								<c:choose>
									<c:when test="${goodsDetail.have_parm > 0 && goodsDetail.paramsObject != null}">
										<c:forEach items="${goodsDetail.paramsObject}" var="params">
											<tr><th colspan="2">${params.name}</th></tr>
											<c:forEach items="${params.paramList}" var="list">
												<tr>
													<th>${list.name}</th>
													<td>${list.value}</td>
												</tr>
											</c:forEach>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr><th colspan="2">无</th></tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					<!-- 商品介绍 -->
					<section>
						${goodsDetail.intro}
					</section>
					<!-- 商品评价 -->
					<section>
						<header class="goods-intro-header">
							<h4>商品评价咨询</h4>
						</header>
						<article>
							<section>
								<h4 class="goods-intro">商品综合得分</h4>
							<span>
								<c:forEach var="i" begin="1" end="${6}">
									<i class='fa fa-star<c:if test="${i >= goodsDetail.grade}">-o</c:if>'></i>
								</c:forEach>
								<em>${goodsDetail.grade}</em>
							</span>
							</section>
							<hr>
							<!-- 评论 -->
							<section>
								<ul>
									<c:choose>
										<c:when test="${false}">

										</c:when>
										<c:otherwise>
											<li>暂无评论，抢沙发，赢取更多积分。</li>
										</c:otherwise>
									</c:choose>
								</ul>
								<div class="row"><span class="pull-right">共0条记录1/0</span></div>
							</section>
							<section class="panel panel-default">
								<div class="panel-body">
									<div class="col-xs-6">
										<div>
											<span>给该商品打分</span>
											<span>
												<i class="fa fa-star-o"></i>
												<i class="fa fa-star-o"></i>
												<i class="fa fa-star-o"></i>
												<i class="fa fa-star-o"></i>
												<i class="fa fa-star-o"></i>
											</span>
										</div>
										<div>
											<h4>为该商品评论</h4>
											<textarea class="comments-content"></textarea>
											<button class="btn btn-primary">提交</button>
										</div>
									</div>
									<div class="col-xs-6">
										<center>评论规则</center>
										<ul>
											<li>1、成功订购本商品的会员即可发表对该商品的评论</li>
											<li>2、本商品的首次评论可额外获得50个积分</li>
											<li>3、针对本商品成功发表文字评论可获得10个积分</li>
										</ul>
									</div>
								</div>
							</section>
						</article>
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