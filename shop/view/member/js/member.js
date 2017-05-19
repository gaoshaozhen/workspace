function updateGrid(param){
	function initTable(msg){
		$("#member-grid").datagrid({
			columns:[[
				{field:"member_id",title:"会员编号",align:"center",width:"50"},
				{field:"uname",title:"名称",align:"center",width:"80"},
				{field:"sex",title:"性别",align:"center",width:"80",
					formatter:function(value,row,index){
						if (row.sex == 1) {
							return "男";
						}else if (row.sex == 0) {
							return "女";
						}else{
							return "";
						}
					}
				},
				{field:"point",title:"积分",align:"center",width:"30"},
				{field:"email",title:"邮箱",align:"center",width:"30"}
			]],
			data:msg.data
		});
	}
	$.ajax({
		url:"/shop/user/getMember.shtm",
		type:"get",
		data:param,
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			if (msg.code > 0) {
				initTable(msg);
			}else{
				alert("请登录");
			}
		}
	});
}
function search(){
  	var grid = $("#member-grid");
 	var options = grid.datagrid('getPager').data("pagination").options;
 	var param = {
 		"pageSize" : options.pageSize
 	};
 	var name = $.trim($("#search-name").textbox("getValue"));
 	if (name.length < 1) {
 		alert("请输入搜索内容");
 		return;
 	}
 	param.name = name;
 	updateGrid(param);
}

function bindEvent(){
	$("#search-btn").on("click", search);
}
function init(){
	bindEvent();
	updateGrid(null);
}
$(init);