//获取新规格设置
function getNewStandard(){
	var form = $("#standard-form").serializeArray(); 
	var standard = {};
	for (var i = 0, size = form.length; i< size; i++) {
		standard[form[i].name] = form[i].value;
	}
	return standard;
}
function updateStandardList(standard){
	var node = "<tr>";
	node += "<td><input type='text' value=\"" + standard.standardName + "\"/></td>";
	if (parseInt(standard.type) == 0) {
		node += '<td><input type="text" value=\"' + standard.remark + '\"/></td>';
	}
	else{
		node += '<td><input type="file" /></td>';	
	}
	node += '<td><span class="fa fa-remove"></span></td>';
	$("#new-standard-list").append(node);
}

;(function($){
	var em = {};
	var bindEvent = function(){
		em.$newStandardList.click(function(){
			updateStandardList(getNewStandard());
		});
	};
	var init = function(){

		 bindEvent();
		console.info("初始化完成||" + Math.floor(Math.random() *　1000));		
	};
	$(function(){
		em = {
			$newStandardList : $("#add-standard-btn")
		};
		init();
	});
})(jQuery);