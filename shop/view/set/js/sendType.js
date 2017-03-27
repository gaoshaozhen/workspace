(function($) {
	var init = function(){
		$('#tt').datagrid({
		    // url:'datagrid_data.json',		    
		    data: [
				{type:'圆通速递',
				 supportValue:'否',
				 cashOnDelivery:'否',
				 operator:'<a class="fa fa-edit" href="setFee.html"></a> '},
				{type:'宅急送', 
				 supportValue:'否',
				 cashOnDelivery:'否',
				 operator:'<a class="fa fa-edit" href="setFee.html"></a> '}
			]
		});
	};
	$(function(){
		init();
	});
})(jQuery);