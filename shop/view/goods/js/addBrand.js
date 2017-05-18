(function(){
	"use strict";
})();

var briefEdit = {
	init:function(){
		bkLib.onDomLoaded(function() {          
	        new nicEditor({
	         	fullPanel : true,
	          	iconsPath :"/shop/store/nicEdit/nicEditorIcons.gif"
	        }).panelInstance('brief',{hasPanel : true});
	    });
	},
	getContent:function(){
		return $("#brief-edit .nicEdit-main").html();
	},
	clear:function(){
		$("#brief-edit .nicEdit-main").html("");	
	}
};
// 打开弹出框
function openEditWin(info, add){	
	if (add) {
		$("#add-brand-btn").show();
	}else {
		$("#win .brand-id-info").show();		
		$("#submit-brand-btn").show();
		$("#win .brandId").val(info.brand_id);
	}	
	$("#win").window("open");
}
// 添加新品牌
function addBrand(param){
	$.ajax({
		type:"get",
		data:param,
		url:"/shop/brand/addBrand.action",
		error:function(msg){
			$("#win .info").text("网络异常，请重试。。。");
			console.error("网络错误");
		},
		success:function(msg){
			if (msg.operator) {
				// $("#win .info").text("成功");
				// setTimeout(function(){
				// 	$("#win .info").hide();
				// 	$("#win").window("close");			
				// }, 3000);				
				console.info("添加成功");	
				alert("添加成功");
			}
			else{
				$("#win .info").text("添加失败");
				console.info("添加成功");	
			}
		}
	});
}
// 保存品牌编辑信息
function saveBrand(param){
	var formData = new FormData();	
	var param = getBrandParam();
	for(var item in param){
		if (param[item] != undefined) {
			formData.append(item, param[item])	
		}		
	}
	console.debug(param);
	// return;
	$.ajax({
		type:"post",
		data:formData,
		url:"/shop/brand/updateBrand.shtm",
		 contentType: false,  
          processData: false, 
		error:function(msg){
			alert("网络出错");
			console.error(msg);
		},
		success:function(msg){
			console.debug(msg);
		}
	});
}
// 更新表格内容
var updateGrid = function(){
	$("#brand-table").datagrid('loading'); //显示加载信息
	function initGird(msg){
		$("#brand-table").datagrid({							
			columns:[[
				{field:"ck",checkbox:"true"},
				{field:"brand_id",title:"品牌ID",align:"center",width:10},
				{field:"name",title:"品牌名称",align:"center",width:20},
				{field:"url",title:"网址",align:"center",width:20},
				{field:"logo",title:"logo",align:"center",width:10,
					formatter:function(value,row,index){
						var dom = "<img src='/shop/imageManager/image.shtm?fileId=url' width='25px'></img>"
						return dom.replace(/url/, row.logo);
					}
				},
				{field:"brief",title:"简介",align:"center",width:20},					
				{field:"disabled",title:"启用",align:"center",width:10,
					formatter:function(value, row, index){
						if (row.disabled < 1) {
							return "启用";
						}else{
							return "禁用";
						}
					}
				},
				{field:"edit",title:"编辑",align:"center",width:10,
					formatter:function(value, row, index){
						var dom = "<a href='javascript:openEditWin(json, false)'>编辑</a>";
						return dom.replace(/json/,JSON.stringify(row));						
					}
				}
			]],
			data: msg
		});
	}
	$.ajax({
			type:"get",					
			url:"/shop/brand/getBrand.action",
			error:function(msg){
				console.error("添加错误");
			},
			success:function(msg){
				initGird(msg);					
			}
		});	
};
// 清空弹出框编辑内容
function clearWin(){
	var file = $("#win .logo");
	$("#win .brand-id-info").hide();
	$("#win .name").val("");
	$("#win .url").val("");
	file.after(file.clone());
	file.remove();
	$("#win .name").val();	
	briefEdit.clear();
	$("#submit-brand-btn").hide();	
	$("#add-brand-btn").hide();	
}
function getBrandParam(){
	var param = {
		brandId:$("#win .brandId").val(),
		name:$("#win .name").val(),
		url:$("#win .url").val(),
		logo:$("#win .logo")[0].files[0],
		brief:briefEdit.getContent(),
		disabled:$("#win .disabled").val()
	}
	return param;
}

;(function(){		
	var bindEvent = function(){
		
		$("#submit-brand-btn").click(saveBrand);
		$("#win").dialog({  
		    onClose: function () {  
				clearWin();	        		       
		    }  
		});  

		// 添加品牌
		$("#add-brand-btn").click(function(){
			var formData = new FormData();	
			var param = getBrandParam();
			for(var item in param){				
				if (param[item] != undefined) {
					formData.append(item, param[item])	
				}else{
					console.debug("failed end");
				}				
			}
			console.debug(param);
			$("#win .info").show();
			$("#win .info").text("保存中。。。");
			$.ajax({
				url:"/shop/brand/addBrand.action",
				type:"post",
				data:formData,				
				contentType: false,  
          		processData: false, 
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
			openEditWin(null,true);			
		});
		// 刷新表格数据
		$(".reload-brand").on("click", updateGrid);		
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
		briefEdit.init();		
		console.info("初始化完成");
	};
	$(function(){
		init();
	});
})(jQuery);