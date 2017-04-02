var AppUrl = {	
	// 推荐商品、热卖排行榜商品、最近浏览商品
	getRecommendedProducts : "/mall/data/recommendedProducts.json",	

	// 品牌、商品
	getProducts : "/mall/data/products.json",

	getRegistUrl : window.location.pathname + "?operator=regist",
	getLoginUrl : window.location.pathname + "?operator=login",
	getMemberUrl : window.location.pathname + "?operator=memberIndex",

	// 获得个人信息
	getUserInfoUrl : "/mall/data/user.json",

	// 个人中心
	memberIndex : "/mall/index.html?operator=memberIndex",

	// 管理收货地址
	memberAddress : "/mall/index.html?operator=memberAddress",

	// 订单地址
	memberOrder : "/mall/index.html?operator=memberOrder",		

	// 完善基本资料
	memberInfo : "/mall/index.html?operator=memberInfo"		

};