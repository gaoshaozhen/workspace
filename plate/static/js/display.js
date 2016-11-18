
var display={
		
};

var displayUI={

	showOrderList : function(){//显示订单列表
		jQuery("#left-side .order-list").css("display","block");
	},

	hideOrderList : function(){
		jQuery("#left-side .order-list").css("display","none");
	},

	init : function(){

	}
};

jQuery().ready(function(){
	jQuery("#left-side .order-center").click(function(){
		// if(jQuery("#left-side .order-list").css("display")=="block"){
		// 	displayUI.hideOrderList();
		// }
		// else{
		// 	displayUI.showOrderList();		
		// }
		jQuery("#left-side .order-list").toggle();
		console.log("开/关");
	});	
});
  // window.open("test.html","","width=200,height=200");