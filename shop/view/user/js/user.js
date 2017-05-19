function editWin(user) {
	$("#win").window("open");
	$("#win .id").val(user.id);
	$("#win .username").textbox('setValue',user.username);
	$("#win .password").textbox('setValue',user.password);
	$("#win .mobile").textbox('setValue',user.mobile);
	$("#win .email").textbox('setValue',user.email);
	$("#save-user-btn").on("click", saveUser);
}
function saveUser(){
	var user = {
		id:$("#win .id").val(),
		username: $("#win .username").val(),
		password: $("#win .password").val(),
		mobile: $("#win .mobile").val(),
		email: $("#win .email").val()
	};
	$.ajax({
		url: "/shop/user/updateUser.shtm",
		type: "post",
		data: user,
		error: function(msg) {
			console.error(msg);
			alert("网络出错");
		},
		success: function(msg) {
			if (msg.code > 0) {
				alert("保存成功");
				updateGrid(null)
			} else {
				alert("添加失败");
			}
		}
	});
}

function updateGrid(param) {
	function initTable(msg) {
		$("#user-grid").datagrid({
			columns: [
				[{
					field: "id",
					title: "编号",
					align: "center",
					width: "50"
				}, {
					field: "username",
					title: "名称",
					align: "center",
					width: "80"
				}, {
					field: "password",
					title: "密码",
					align: "center",
					width: "150"
				}, {
					field: "mobile",
					title: "手机",
					align: "center",
					width: "80"
				}, {
					field: "email",
					title: "邮箱",
					align: "center",
					width: "120"
				}, {
					field: "operation",
					title: "操作",
					align: "center",
					width: "30",
					formatter: function(value, row, index) {
						var dom = "<a href='#' onclick='editWin(json)'>修改</a>";
						var d = dom.replace(/json/, JSON.stringify(row));
						return d;
					}
				}]
			],
			data: msg.data
		});
	}
	$.ajax({
		url: "/shop/user/getAllUser.shtm",
		type: "get",
		data: param,
		error: function(msg) {
			console.error(msg);
			alert("网络出错");
		},
		success: function(msg) {
			if (msg.code > 0) {
				initTable(msg);
			} else {
				alert("请登录");
			}
		}
	});
}

function search() {
	var grid = $("#user-grid");
	var options = grid.datagrid('getPager').data("pagination").options;
	var param = {
		"pageSize": options.pageSize
	};
	var name = $.trim($("#search-name").textbox("getValue"));
	if (name.length < 1) {
		alert("请输入搜索内容");
		return;
	}
	param.name = name;
	updateGrid(param);
}

function openAddUserWin() {
	$("#win").window("open");
	$("#save-user-btn").on("click", saveNewUser);
}

function saveNewUser() {
	var user = {
		username: $("#win .username").val(),
		password: $("#win .password").val(),
		mobile: $("#win .mobile").val(),
		email: $("#win .email").val()
	};
	$.ajax({
		url: "/shop/user/addUser.shtm",
		type: "post",
		data: user,
		error: function(msg) {
			console.error(msg);
			alert("网络出错");
		},
		success: function(msg) {
			if (msg.code > 0) {
				alert("添加成功");
				updateGrid(null)
			} else {
				alert("添加失败");
			}
		}
	});
}

function bindEvent() {
	$("#search-btn").on("click", search);
	$("#add-btn").on("click", openAddUserWin);
	$("#refresh-btn").on("click", function() {
		updateGrid(null)
	});
	$("#win").dialog({
		onClose: function() {
			$("#win input").val("");
			$("#save-user-btn").off();
		}
	});
}

function init() {
	bindEvent();
	updateGrid(null);
}
$(init);