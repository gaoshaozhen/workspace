<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<meta charset="utf-8"/>
		<title>购买商品</title>
	</head>
	<body>
		<a href="/${initParam.webName}/index/index">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>
		<img src="" alt="商品图片" height="200px" width="100%">
		<p>${goodsName}</p>
		<p style="color: red">￥${goodsPrice}</p>
		<hr/>
		<div class="panel default-panel goods-param">
			<div class="panel-body setNumber">
				<div style="border: 1px solid #BFB4B4;width: auto">
					<span>数量&nbsp;&nbsp;</span>	
					<span class="box glyphicon glyphicon-minus" onclick="buy.minus()"></span>
					<input class="goods-number" type="text" style="width: 3em;border: 0"value = "1" readonly="true"></input>
					<span class="box glyphicon glyphicon-plus" onclick="buy.plus()"></span>
				</div>
			</div>
		</div>
		<div class="panel default-panel">
			<div class="panel-body">
				<h5>详情</h5>
				<p>${goodsParam}</p>
			</div>
		</div>
		<footer class="buybtn col-xs-12">
			<span class="buybtn-box col-xs-6" style="background: red;">
				<center>加入购物车</center>
			</span>
			<span class="buybtn-box col-xs-6" style="background: red;">
				<center>立即购买</center>
			</span>
		</footer>
	</body>
	<%@ include file="store.jsp"%>
	<style type="text/css">
		.setNumber .box{
			height: 100%;
			width: 16px;
			background: #BEB7B7;
		}
		footer.buybtn {
			position:fixed;
			left:0;
			bottom:0;
			color: white;
		}
		footer .buybtn-box{
			height: 38px;
			line-height: 38px;
			border: 1px solid #BEB7B7
		}
	</style>
	<script type="text/javascript">
		var buy = {
			plus : function(){
				console.debug("加1");
				buy.goods.goodsNumber++;
				jQuery(".goods-param .goods-number").val(buy.goods.goodsNumber);
			},
			minus : function(){
				console.debug("减1");
				if(buy.goods.goodsNumber > 1){
					buy.goods.goodsNumber--;
					jQuery(".goods-param .goods-number").val(buy.goods.goodsNumber);
				}
			},
			goods : {"goodsNumber" : 1},
			addToCart : function(){
				var number
				var data = {"goodsId" : "${goodsId}",
							"goodsNumber" : buy.goods.goodsNumber};
				var back = function(msg){ // 请求加入购物车后的回调函数

				};

				buy.net.requestAddToCart(data,back);
			},
			net : {
				requestAddToCart : function(data,  back){
					jQuery.get("",
						data,
						function(msg, dataStatus, xhr){
							back(msg);
						});
				}
			}
		};
	</script>
</html>