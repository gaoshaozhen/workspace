;(function(){
	var bindEvent = function(){
		// 添加品牌
		$("#submit-brand-btn").click(function(){
			var info = $("#brand-form").serialize();
			$("#win .info").show();
			$("#win .info").text("保存中。。。");
			$.ajax({
				type:"get",
				data:info,
				url:"/shop/brand/addBrand.action",
				error:function(msg){
					$("#win .info").text("网络异常，请重试。。。");
					console.error("添加错误");
				},
				success:function(msg){
					if (msg.operator) {
						$("#win .info").text("成功");
						setTimeout(function(){
							$("#win .info").hide();
							$("#win").window("close");			
						}, 3000);
						console.info("添加成功");	
					}
					else{
						$("#win .info").text("添加失败");
						console.info("添加成功");	
					}
				}
			});
		});
		// 打开弹出框
		$(".show").click(function(){
			$("#win").window("open");
		});
		// 刷新表格数据
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
							loadMsg:"加载中",
							data: msg
						});
					}
				});	
		});
		$(".delete-brand").click(function(){			
			var rows = $('#brand-table').datagrid('getSelections');
			var brand_ids = [];
			for (var i = 0, length = rows.length; i < length; i++ ){
				brand_ids.push(rows[i].brand_id);
			}
			if (brand_ids.length < 1) {
				alert("请选择要删除的品牌");
				return;
			}
			else{
				$.ajax({
					type : "get",
					url : "/shop/brand/deleteBrand.action",
					data:{"brand_ids" : brand_ids.toString()},
					error : function(msg){
						console.error("网络异常，请重试。。。");;
						alert("删除失败");
					},
					success : function(msg){
						if (msg.operator) {
							alert("删除失败");
						}else{
							alert("删除成功");
							setTimeout(function(){
								$(".reload-brand").click();
							}, 3000);
						}
					}
				});
			}
			console.info(rows);
			console.info(brand_ids);
		});
	};

	var init = function(){
		bindEvent();
		$(".reload-brand").click();
		console.info("初始化完成");
	};

	$(function(){
		init();
	});
})(jQuery);