
function updateGird(){
	$("#goodslist-grid").datagrid({
		columns:[[
			{field:"goods_id",title:"商品号",align:"center",width:"50"},
			{field:"name",title:"名称",align:"center",width:"50"},
			{field:"sn",title:"商品号",align:"center",width:"50"},
			{field:"market_enable",title:"是否上架",align:"center",width:"50"},
			{field:"cost",title:"成本",align:"center",width:"50"},
			{field:"maktprice",title:"市场价",align:"center",width:"50"},
			{field:"price",title:"销售价",align:"center",width:"50"},
			{field:"point",title:"积分",align:"center",width:"50"},
			{field:"operation",title:"操作",align:"center",width:"50",
				formatter:function(value,row,index){
					var dom = "<a href='javascript:void'>详细信息</a>";

					return dom;
				}
			},

		]]
	});
}

function init(){
	updateGird();
}

$(init);