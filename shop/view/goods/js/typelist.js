// 获取过滤条件
function getParam()
{
	var grid = $('#type-table');  
	var options = grid.datagrid('getPager').data("pagination").options;  
	var curr = options.pageNumber;  
	var total = options.total;  
	var max = Math.ceil(total/options.pageSize); 
	var param = {
		"pageSize" : options.pageSize,
		"pageNumber":options.pageNumber
	};
	// console.info(options);
	return param;
}

function updateDataGrid(param){
	$("#type-table").datagrid('loading'); //显示加载信息
	$.ajax({
		type:"post",
		url:"/shop/type/getTypeList.shtm",
		data:param,
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			var array = [];
			for(var item in msg.data)
			{
				var params = msg.data[item].params;
				var props = msg.data[item].props;
				if (typeof params != "undefined" && params != null ) {
					msg.data[item].params = params.toString();
				}
				if (typeof props != "undefined" && props != null ) {
					// console.info(typeof props);
					msg.data[item].props = props.toString();	
				}
				array.push(msg.data[item]);
			}
			$("#type-table").datagrid({
				loadMsg:"加载中",			
				data:array
			});
			var p = $('#type-table').datagrid('getPager');
			$(p).pagination({
			    total:msg.total,
			    pageNumber:msg.page,
			    showRefresh:false,
			    beforePageText: '第',//页数文本框前显示的汉字 
			    afterPageText: '页 共 {pages} 页',
			    displayMsg: '共{total}条数据',
			    onChangePageSize:function(){  
		           // alert('pagesize changed');  
		        },  
		        onSelectPage:function(pageNumber,pageSize){ 
		        　　var param = {
		        		"pageNumber":pageNumber,
		        		"pageSize":pageSize
		        	}; 
		        	updateDataGrid(param);
		            console.info(pageNumber + "||" +　pageSize);
		        }  
			});
		}
	});
}

$(".reload-type").click(function(){
	var param = getParam();
	if (param.pageNumber < 1) {
		param.pageNumber = 1;
	}
	updateDataGrid(param);
	// console.info("刷新");
});