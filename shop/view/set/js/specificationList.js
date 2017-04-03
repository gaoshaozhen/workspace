;(function(){
	var bindEvent = function(){
		// 刷新表格数据
		$(".reload-specification").click(function(){
			$.ajax({
				type:"get",
				url:"/shop/spec/getSpec.action",
				error:function(msg){
					console.error("网络异常。。。");
				},
				success:function(msg){
					$("#specification-table").datagrid({						
						columns:[[

							{field:'ck',checkbox:true,width:80, align:'center'},
						    {field:'spec_id',title:'规格ID',width:80, align:'center'},
						    {field:'spec_name',title:'规格名',width:80, align:'center'},
						    {field:'spec_type',title:'类型',width:80, align:'center',
								formatter: function(value,row,index){
									if (value > 0){
										return "文字";
									} else {
										return "图片";
									}
								}},
						    {field:'spec_meno',title:'备注',width:80, align:'center'},						    
						]],
						data:msg
					});		
					// console.info(msg);
				}
			});			
		});
		// 打开添加新规格窗口
		$(".add-specification").click(function(){
			$("#win .info").empty();
			$("#win").window("open");
		});
		// 保存新规格
		$("#submit-spec-btn").click(function(){
			$("#win .info").show();
			$("#win .info").text("保存中...");
			var param = $("#spec-form").serialize();
			$.ajax({
				type:"get",				
				url:"/shop/spec/addSpec.action",
				data:param,
				error:function(msg){
					$("#win .info").text("保存失败...");
				},
				success:function(msg){
					if (msg.operator) {
						$("#win .info").text("保存成功...");
						setTimeout(function(){
							$("#win").window("close");	
							$(".reload-specification").click();
						}, 3000);	
					}else{
						$("#win .info").text("保存失败...");		
					}
				}
			});
		});

		// 删除规格
		$(".delete-specification").click(function(){
			console.info("删除");
			var spec_ids = [];
			var rows = $('#specification-table').datagrid('getSelections');
			for (var i = 0, len = rows.length; i < len; i++){
				spec_ids.push(rows[i].spec_id);
			}
			
			if (spec_ids.length < 1) {
				alert("请选择要删除的规格");
				return;
			}
			$.ajax({
				type:"get",
				url:"/shop/spec/deleteSpec.action",
				data:{"spec_ids":spec_ids.toString()},
				error:function(msg){
					alert("删除失败");
				},
				success:function(msg){
					if (msg.operator) {
						alert("删除成功");
					}else{
						alert("删除失败");
					}
				}
			});
		});
	};
	var init = function(){
		bindEvent();
		$(".reload-specification").click();
	};
	$(function(){init();});
})(jQuery);