// 更新购买数量，数量不能为负数
function updateNum(n){
	var num = parseInt($("#product-num").val());
	num += n;
	if (num >= 1) {
		$("#product-num").val(num)
	}
}
// 购买数量加一
function plusNumBind(){
	$("#plus-product").click(function(){
		updateNum(1);
	});
}
// 购买数量减一
function minusNumBind(){
	$("#minus-product").click(function(){
		updateNum(-1);
	});
}
// 更换购买商品规格
function updateStandardBind(){
	var $standard = $("#standard");
	if (typeof $standard[0] == "undefined") {
		return;
	}
	$standard.find("a").click(function(e){
		$standard.find(".active").removeClass("active");
		$(e.target).closest("li").addClass("active");
		$("#product-price").text($(e.target).data("price"));
	});
}

$(function(){
	minusNumBind();
	plusNumBind();
	updateStandardBind();
});