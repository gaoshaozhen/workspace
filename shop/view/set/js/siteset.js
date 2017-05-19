
function saveSite(){
	var param = {
		siteName:$("#site-name").textbox("getValue"),
		keywords:$("#keywords").textbox("getValue"),
		description:$("#descript").textbox("getValue"),
		copyright:$("#copyright").textbox("getValue");
		icp:$("#icp").textbox("getValue");
	};
	$.ajax({
		url:"/shop/site.shtm",
		type:"post",
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			alert("保存成功");
		}
	});
}
function initSite(){
	$.ajax({
		url:"/shop/site/getSite.shtm",
		type:"get",
		error:function(msg){
			console.error(msg);
		},
		success:function(msg){
			if (msg.code > 0) {
				$("#site-name").textbox("setValue", msg.info.sitename);
				$("#keywords").textbox("setValue",msg.info.keywords);
				$("#descript").textbox("setValue",msg.info.descript);
				$("#copyright").textbox("setValue",msg.info.copyright);
				$("#icp").textbox("setValue",msg.info.icp);
			}else{
				alert("请登录");
			}
		}
	});
}

function bindEvent(){
	$("#save-site").on("click", saveSite);
}
function init(){
	bindEvent();
	initSite();
}
$(init);