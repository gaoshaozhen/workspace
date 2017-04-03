function addTab(title, url){
	if ($('#page-content').tabs('exists', title)){
		$('#page-content').tabs('select', title);		    
		console.info("已存在");
	} 
	else {		
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%"></iframe>';
		$('#page-content').tabs('add',{
			title:title,
			content:content,
			closable:true,			
		});
	}
}