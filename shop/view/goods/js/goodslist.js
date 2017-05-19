function openWin(goods){
	console.info(goods);
	var params = JSON.parse(goods.params);
	console.info(params);
	$("#win").window("open");
	var dom = "";
	for(var i = 0, len = params.length; i < len; i++){
		var paramList = params[i].paramList;
		for(var j = 0, size = paramList.length; j < size; j++){
			dom += "<tr><th>" + paramList[j].name + "</th>";
			dom	+= "<td><input value='" + paramList[j].value + "'></td></tr>";
		}
	}
	$("#params").html(dom);
	$("#goods-id-info").val(goods.goods_id);
	$("#base .name").textbox('setValue',goods.name);
	$("#base .cost").textbox('setValue',goods.cost);
	$("#base .price").textbox('setValue',goods.price);
	$("#base .point").textbox('setValue',goods.point);
	$("#intro-cont").html(goods.intro);
	$("#meta-keywords").val(goods.meta_keywords);
	$("#meta-description").val(goods.meta_description);

}
function updateGird(netParam){
	function initGrid(msg){
		$("#goodslist-grid").datagrid({
			columns:[[
				{field:"goods_id",title:"商品号",align:"center",width:"50"},
				{field:"name",title:"名称",align:"center",width:"150"},
				{field:"market_enable",title:"是否上架",align:"center",width:"60",
					formatter:function(value,row,index){
						if (row.market_enable > 0) {
							return "是";
						}else{
							return "否";
						}
					}
				},
				{field:"cost",title:"成本",align:"center",width:"50"},
				{field:"maktprice",title:"市场价",align:"center",width:"50"},
				{field:"price",title:"销售价",align:"center",width:"50"},
				{field:"point",title:"积分",align:"center",width:"50"},
				{field:"operation",title:"操作",align:"center",width:"50",
					formatter:function(value,row,index){
						var json = JSON.stringify(row);
						var dom = "<a href='javascript:openWin(json)'>详细信息</a>";
						return dom.replace(/json/,json);
					}
				},

			]],
			data:msg.data
		});
		var p = $('#goodslist-grid').datagrid('getPager');
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
	        	updateGird(param);
	            console.info(pageNumber + "||" +　pageSize);
	        }
		});
	}
	$.ajax({
		url:"/shop/mall/getGoods.shtm",
		type:"get",
		data:netParam,
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			initGrid(msg);
		}
	});
}
function search(){
  	var grid = $('#goodslist-grid');
 	var options = grid.datagrid('getPager').data("pagination").options;
 	var param = {
 		"pageSize" : options.pageSize
 	};
 	var name = $.trim($("#search-name").textbox("getValue"));
 	console.info(name);
 	if (name.length < 1) {
 		alert("请输入搜索内容");
 		return;
 	}
 	param.name = name;
 	updateGird(param);
}
function saveBase(){
	var base = {
		goodsId:$("#goods-id-info").val(),
		name:$("#base .name").textbox("getValue"),
		mktEnable:$("#base .mkt-enable").val(),
		cost:$("#base .cost").val(),
		price:$("#base .price").val(),
		point:$("#base .point").val()
	};
	$.ajax({
		url:"/shop/mall/updateGoodsBase.shtm",
		type:"post",
		data:base,
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			if (msg.code > 0) {
				alert("保存成功");
			}else{
				alert("请登录");
			}
		}
	});
	console.info(base);
}
function saveIntro(){
	var d = {
		goodsId:$("#goods-id-info").val(),
		intro:$("#intro-edit .nicEdit-main").html()
	};
	$.ajax({
		url:"/shop/mall/updateGoodsIntro.shtm",
		type:"post",
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			if (msg.code > 0) {
				alert("保存成功");
			}else{
				alert("请登录");
			}
		}
	});
}
function bindEvent(){
	$("#refresh-btn").on("click", function(){updateGird(null)});
	$(".edit-box .edit-btn").on("click", function(){
		$(".edit-box .edit-btn").removeClass("inline-active");
		$(".edit-box .intro-cont").removeClass("block-active");
		$(".edit-box .back-btn").addClass("inline-active");
		$(".edit-box .intro-box").addClass("block-active");
		$(".intro-save-box .intro-save-btn").addClass("active");
	});
	$(".edit-box .back-btn").on("click", function(){
		$(".edit-box .edit-btn").addClass("inline-active");
		$(".edit-box .intro-cont").addClass("block-active");
		$(".edit-box .back-btn").removeClass("inline-active");
		$(".edit-box .intro-box").removeClass("block-active");
		$(".intro-save-box.intro-save-btn").removeClass("active");
	});
	$("#save-base-btn").on("click", saveBase);
	$("#search-btn").on("click", search);
}
function getEditContent() {
	return $("#intro-edit .nicEdit-main").html();
}

function init(){
	bindEvent();
	updateGird();
	bkLib.onDomLoaded(function() {
		new nicEditor({
			fullPanel: true,
			iconsPath: "/shop/store/nicEdit/nicEditorIcons.gif"
		}).panelInstance('intro-edit', {
			hasPanel: true
		});
	});
}
$(init);