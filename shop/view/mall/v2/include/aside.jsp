<%@ page contentType="text/html; charset=utf-8" %>

<div class="panel-default panel">
	<div class="panel-body">
		<h5>
			<span class="fa fa-1x fa-user-circle"></span>
			<a href="/shop/mall/member_index.shtm">会员中心首页</a>
		</h5>
		<div class="box-inline"><span class="fa fa-2x fa-address-card-o"></span></div>
		<div class="box-inline">
			<p>${memberInfo.username}</p>
			<p>会员等级：${memberInfo.memberLv.name}</p>
		</div>
		<section>
			<div><span class="fa fa-jpy"></span>交易管理</div>
			<ul>
				<li><a href="/shop/mall/getOrder.shtm">我的订单</a></li>
				<li>我的优惠券</li>
				<li>退换货申请</li>
				<li><a href="/shop/mall/address.shtm">收货地址</a></li>
			</ul>
		</section>
		<section>
			<div><span class="fa fa-star-half-empty"></span>收藏夹</div>
			<ul>
				<li>商品收藏</li>
				<li>缺货登记</li>
			</ul>
		</section>				
		<section>
			<div><span class="fa fa-weixin"></span>评论管理</div>
			<ul>
				<li>我的评论</li>
				<li>我的咨询</li>
			</ul>
		</section>
		<section>
			<div><span class="fa fa-user"></span>账号管理</div>
			<ul>
				<li><a href="">完善基本资料</a></li>
				<li>我的积分</li>
				<li>修改密码</li>
			</ul>
		</section>
	</div>
</div>
