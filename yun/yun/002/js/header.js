	var header={
		
	};
	(function(){		
		var nv=$("#container .menu .navs");
		for(var i=0;i<nv.length;i++){
			if($(nv[i]).text()=="网盘"||"记事本"){//							
				$(nv[i]).css({"cursor":"pointer"});				
				$(nv[i]).click(function(){
					var dis=$("#container .menu .new-href").css("display");
					if(dis=="none"){
						$("#container .menu .new-href").css({"display":"block"});
						var ss=$("#container .menu .new-href").children();
						
						for(var j=0;j<ss.length;j++){
							
							switch(j){
							case 0:$(ss[j]).click(function(){
										window.location.href="up.html";
									});break;
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:$(ss[j]).click(function(){
										window.location.href="note.html";
									});break; 
							case 8:
							case 9:
							default:break;
						}
						}
						
					}
					else{
						$("#container .menu .new-href").css({"display":"none"});
					}					
				});			
			}
		}		
	})();