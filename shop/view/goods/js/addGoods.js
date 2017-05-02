var selectCatId;
function getChildren(data, cat_id){
	var array = [];
	for(var item in data){
		if(data[item].parent_id == cat_id){
			array.push(data[item]);
		}
	}	
	return array;
}

function showList(data){
	var $fDom = $("<li class='handle cat-item'></li>");
	var $sDom = $("<li class='handle cat-item'></li>");
	$sDom.click(function(e){
		var cat_id = $(this).data("cat_id");
		var name = $(this).data("name");
		selectCatId = cat_id;
		var array = getChildren(data, cat_id);
		// console.info("second:" + cat_id);
		$("#thirdList").empty();
		updateCatInfo(null, name, null);
		for(var item in array){
			var $dom = $sDom.clone(false);
			$dom.data("cat_id", array[item].cat_id);			
			$dom.data("name", array[item].name);			
			$dom.click(function(){
				updateCatInfo(null, null, $(this).data("name"));		
				selectCatId = $(this).data("cat_id");
			});
			$dom.text(array[item].name);
			$("#thirdList").append($dom);
		}		
	});

	$fDom.click(function(e){
		var cat_id = $(this).data("cat_id");
		var name = $(this).data("name");
		selectCatId = cat_id;
		var array = getChildren(data, cat_id);
		$("#secondList").empty();
		$("#thirdList").empty();
		updateCatInfo(name, null, null);
		for(var item in array){
			var $dom = $sDom.clone(true);
			$dom.data("cat_id", array[item].cat_id);						
			$dom.data("name", array[item].name);			
			$dom.text(array[item].name);
			$("#secondList").append($dom);
		}		
	});

	for(var item in data){
		if (data[item].parent_id < 1) {
			var $dom = $fDom.clone(true);
			$dom.text(data[item].name);
			$dom.data("cat_id", data[item].cat_id);			
			$dom.data("name", data[item].name);			
			$("#firstList").append($dom);							
		}		
	}
}

function firstListLoad(){
	$.ajax({
		url:"/shop/mall/getGoodsCatList.shtm",
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			showList(msg.catList);
		}
	});
}

function initEdit(){
	bkLib.onDomLoaded(function() {          
          new nicEditor({
          	fullPanel : true,
          	iconsPath :"/shop/store/nicEdit/nicEditorIcons.gif"
          }).panelInstance('detail',{hasPanel : true});
     });
}

function getEditContent(){

}

function updateCatInfo(f, s, t){	
	
	// console.info(new Array(f,s,t));
	// console.info($("#first-cat")[0]);
	if (f != null) {
		$("#first-cat").text(f);
		$("#second-cat").empty();
		$("#third-cat").empty();
	}else if(s != null){
		$("#second-cat").html(" &gt; " + s);
		$("#third-cat").empty();
	}else if(t!= null){
		$("#third-cat").html(" &gt; " + t);
	}
}
function bindEvent(){
	$("#next-btn").click(function(){
		var firstCat = $("#first-cat").text();
		var secondCat = $("#second-cat").text();
		var thirdCat = $("#third-cat").text();
		var catInfo = firstCat +　secondCat　+ thirdCat;; 
		
		console.info(catInfo);
		if (catInfo.length < 3) {
			alert("请选择分类")
			return;
		}else{
			$("#cat-info-selected").val(catInfo);
		}

		$("#param-panel").show();
		$("#cat-panel").hide();
	});
	$("#previous-btn").click(function(){
		$("#param-panel").hide();
		$("#cat-panel").show();
	});
}
function initGoodsInfo(typeId){
	function init(data){
		
	}
	$.ajax({
		url:"shop/type/getTypeByTypeId.shtm"
		data:{"typeId":typeId},
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			init(msg);
		}
	});
}
$(function(){
	firstListLoad();
	initEdit();
	bindEvent();
});