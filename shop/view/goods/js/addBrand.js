;(function(){
	var bindEvent = function(){
		$("#submit-brand-btn").click(function(){
			var info = $("#brand-form").serialize();
			console.info("添加");
			$.ajax({
				type:"get",
				data:info,
				url:"/shop/brand/addBrand.action",
				error:function(msg){
					console.error("添加错误");
				},
				success:function(msg){
					console.info("添加成功");
				}
			});
		});
		$(".show").click(function(){
			$("#win").window("open");
		});
	};
	$(".reload-brand").click(function(){
		$.ajax({
				type:"get",
				data:{},
				url:"/shop/brand/getBrand.action",
				error:function(msg){
					console.error("添加错误");
				},
				success:function(msg){

					$("#brand-table").datagrid({
						data: msg
					});
				}
			});
	});
	var init = function(){
		bindEvent();
		console.info("初始化完成");
	};

	$(function(){
		init();
	});
})(jQuery);