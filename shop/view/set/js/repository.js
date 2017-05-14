function openWin(productDetail){
	$("#win").window("open");
	$("#product-id").val(productDetail.product_id);
}

function updateStore(){
	var param = {
		productId:$("#product-id").val().replace(/\s/g, ""),
		inStore:$("#in-store").val().replace(/\s/g, ""),
		outStore:$("#out-store").val().replace(/\s/g,""),
	};
	var re = /^[0-9]+$/;
	var ref = /[^]/;
	if (param.inStore.length < 1 && param.outStore.length < 1) {
		alert("请输入有效数字");
		return;
	}else{
		if (re.test(param.outStore)) {
			param.outStore  = "-" + param.outStore;		
		}	
	}	
	$.ajax({
		url:"/shop/product/updateStore.shtm",
		type:"get",
		data:param,
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			alert("保存成功");
			$("#win").window("close");
			initDataGrid({pagesize:10,pageNumber:1});
		}
	});
}

function initDataGrid(param){

	if (param == null) {
		param = {
			pageSize:10,
			pageNumber:1
		};
	}
	if (param.name == undefined) {
		param.name=$("#search-name").val();
	}
	function initTable(msg){
		$("#product-table").datagrid({			
			columns:[[
				{field:"sn",align:"center",title:"商品编号"},
				{field:"name",align:"center",title:"商品名称"},
				{field:"cat_name",align:"center",title:"分类"},
				{field:"product_id",align:"center",title:"产品id",hidden:true},
				{field:"market_enable",align:"center",title:"上架",
					formatter: function(value,row,index){						
						var dom;
						if (row.market_enable > 0) {
							dom = "是";
						}else{
							dom = "否";
						}						
						return dom;						
					}
				},
				{field:"store",align:"center",title:"库存"},
				{field:'operation',title:'操作', width:50,align:"center",
					formatter: function(value,row,index){						
						var json = JSON.stringify(row);
						var dom = "<a href='javascript:openWin("+json+");' >操作</a>";
						return dom;						
					}
				}
			]],
			data:msg.data
		});
		var p = $('#product-table').datagrid('getPager');
			$(p).pagination({
			    total:msg.total,
			    pageNumber:msg.pageNumber,
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
		        	 initDataGrid(param);
		        }  
			});
	}
	$.ajax({
		url:"/shop/product/getProduct.shtm",
		type:"get",
		data:param,
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			console.debug(msg);
			initTable(msg);
		}
	});
}

function bindEvent(){
	$("#save-btn").on("click", updateStore);
	$("#search").on("click",function(){initDataGrid(null);});
}

function init(){	
	initDataGrid(null);
	bindEvent();
}

$(init);
