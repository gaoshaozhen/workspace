
function updateTable(){
	$("#payment-table").datagrid('loading'); //显示加载信息	
	$.ajax({
		type:"post",
		url:"/shop/smtp/getSmtpInfo.shtm",
		error:function(msg){

		},
		success:function(msg){
			var array = [];
			// if (msg.data.length > 0) {
				array.push({
					"id":msg.id,
					"host":msg.host,
					"username":msg.username,
					"password":msg.password,
					"last_send_time":msg.last_send_time,
					"send_count":msg.send_count,
					"max_count":msg.max_count,
					"mail_from":msg.mail_from,
				});
			// }
			$("#smtp-table").datagrid({
				idField:'id',
				columns:[[
					{field:"id",title:"id",width:50,align:"center"},
					{field:"host",title:"主机",width:50,align:"center"},
					{field:"username",title:"用户名",width:50,align:"center"},
					{field:"password",title:"密码",width:50,align:"center"},
					{field:"last_send_time",title:"最后发送时间",align:"center",width:50},
					{field:"send_count",title:"已发送数目",width:50,align:"center"},
					{field:"max_count",title:"最大发送数目",width:50,align:"center"},
					{field:"mail_from",title:"地址",width:50,align:"center"},
					{
						field:"operator",title:"操作",width:50,align:"center",
							formatter:function(value,row,index){
								if (typeof row.editing == "undefined") {
									row.editing = false;
									console.info("拓展row.editing");
								}
								console.info(row);
								console.info(row.editing);
								if (row.editing) {
									console.log("编辑中 row="+row +" index="+index );
									var s = '<a href="#" onclick="saverow('+index+')">Save</a>';
									var c = '<a href="#" onclick="cancelrow('+index+')">Cancel</a>';
									return s+c;
								}else{
									console.log("不在编辑 row="+row +" index="+index );
									var e = '<a href="#" onclick="editrow('+index+')">Edit</a> ';
									var d = '<a href="#" onclick="deleterow('+index+')">Delete</a>';
									return e+d;
								}

							}
					}
				]],
				
				// onBeforeEdit:function(index,rowData){
				// 	console.info(index);
				// 	console.info(rowData);
				// 	rowData.editing = true;
				// 	updateActions(index);
				// },
				// onAfterEdit:function(index,row){
				// 	row.editing = false;
				// 	updateActions(index);
				// },
				// onCancelEdit:function(index,row){
				// 	row.editing = false;
				// 	updateActions(index);
				// },
				data:array,
			});
		}
	});
}

$(function(){
	updateTable();	
});

function updateActions(index){
	$('#smtp-table').datagrid('updateRow',{
		index: index,
		row:{}
	});

	console.info("updateActions" + index);
	// editrow(index);
}
function getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
// 开始编辑指定行
function editrow(target){
	console.log("开始编辑指定行 " + target);
	$('#smtp-table').datagrid('beginEdit', getRowIndex(target));
}
// 删除指定行
function deleterow(target){
	$.messager.confirm('Confirm','Are you sure?',function(r){
		if (r){
			$('#smtp-table').datagrid('deleteRow', getRowIndex(target));
		}
	});
}
// 保存指定行
function saverow(target){
	$('#smtp-table').datagrid('endEdit', getRowIndex(target));
}
// 取消编辑行
function cancelrow(target){
	$('#smtp-table').datagrid('cancelEdit', getRowIndex(target));
}