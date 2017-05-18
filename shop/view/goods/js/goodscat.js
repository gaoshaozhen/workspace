var goodsCatList = null;
function isNewName(name){
	var is = false;
	if (goodsCatList != null) {
		for (var i = 0,len = goodsCatList.length; i < len; i++) {
			if (name == goodsCatList[i].name) {
				is=true;
			}
		}
		return is;
	}else{
		alert("网络出错，请重试");
		return is;
	}
}

function openAddWindow(){
	$("#add-cat-window").window("open");
}
function openOperationWinDow(row){
	$("#operation-window").window("open");
	$("#goods-cat-id").val(row.cat_id);
}
function showParent(data){
	var parentDom = "";
	for(var i = 0, len = data.length; i < len; i++){
		parentDom += "<option value='"+data[i].cat_id+"'>" + data[i].name + "</option>";
	}	
	$("#parent-cat").html(parentDom);
}
function showType(){
	function op(msg){
		console.debug(msg);
		var dom = "";
		for(var i = 0, len = msg.data.length;i < len; i++){
			dom += "<option value='" 
				+ msg.data[i].type_id 
				+ "'>"
				+ msg.data[i].name
				+ "</option>";
		}
		$("#type").html(dom);
	}
	$.ajax({
		url:"/shop/type/getTypeList.shtm",
		type:"post",
		error:function(msg){
			console.error(msg);
			alert("网络出错，请重试");
		},
		success:function(msg){
			op(msg);
		}
	});
}

function loadGrid(){
	function showData(msg){
		console.debug(msg);
		goodsCatList = msg.catList;
		showParent(msg.catList);
		for(var i = 0, len = msg.catList.length; i < len; i++){
			if(msg.catList[i].parent_id > 0){
				for(var j = 0; j < len; j++){
					if (msg.catList[j].cat_id == msg.catList[i].parent_id) {
						msg.catList[i].parent_name = msg.catList[j].name;
					}
				}	
			}
		}
		$("#goods-cat-table").datagrid({
			columns:[[
				{field:"cat_id",title:"分类ID",align:"center",width:20},
				{field:"name",title:"分类名",align:"center",width:50},
				{field:"parent_name",title:"上级分类",align:"center",width:50},
				{field:"type_name",title:"类型",align:"center",width:50},
				{field:"warn_num",title:"库存预警数",align:"center",width:50,
					formatter:function(value,row,index){
						var dom;
						if (row.warn_num == undefined || row.warn_num < 1) {
							dom = "未设置";
						}else{
							dom = row.warn_num;
						}
						return dom;
					}
				},
				{field:"operation",title:"操作",align:"center",width:30,
					formatter:function(value,row,index){
						var dom = "<a href='javascript:openOperationWinDow("
								+ JSON.stringify(row)
								+")'>操作</a>";
						return dom;
					}
				}
			]],			
			data:msg.catList			
		});
	}

	$.ajax({
		url:"/shop/mall/getGoodsCatList.shtm",
		type:"get",
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			showData(msg);
		}
	});
}
function submitNewCat(){
	var catName = $.trim($("#cat-name").val());
	var parentId = $("#parent-cat").val();
	var typeId = $("#type").val();
	var formData = new FormData();

	formData.append("catName", catName);
	formData.append("parentId", parentId);
	formData.append("typeId", typeId);

	$.ajax({
		url:"",
		type:post,
		data:formData,
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			alert("操作成功");
		}
	});
}
(function(){
	function bindEvent(){
		$("#add-cat-btn").on("click",openAddWindow);
		$("#redresh-grid").on("click",loadGrid);
		$("submit-cat").on("click",submitNewCat);
	}
	function init(){
		loadGrid();
		bindEvent();
		showType();
	}
	$(init);	
})();
