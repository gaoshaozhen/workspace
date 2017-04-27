(function(){
	function initChild(){
		$(".have-child").on("mouseenter", function(e){
			$(".have-child .child").hide();
			$(e.target).find('.child').show();
			console.log("mouseover");
		});
		$(".have-child").on("mouseleave ", function(e){			
			$(e.target).find('.child').hide();
			console.log("mouseout");
		});
	}	
	function init(){
		initChild();
	}
	$(function(){
		init();
		console.info("初始换成功");
		console.info($(".have-child")[0]);
	});	
})();