
function updateDataGrid(param){
	$("#payment-table").datagrid('loading'); //显示加载信息
	$.ajax({
		type:"post",
		url:"/shop/payment/getPaymentList.shtm",
		data:param,
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			var array = [];
			for(var item in msg.data)
			{
				array.push({
					"name":msg.data[item].name,
					"biref":msg.data[item].biref					
				});
			}
			$("#payment-table").datagrid({
				loadMsg:"加载中",			
				data:array
			});
		}
	});
}

$(".reload-payment").click(function(){	
	updateDataGrid();
	console.info("刷新");
});
$(function(){
	updateDataGrid();
});