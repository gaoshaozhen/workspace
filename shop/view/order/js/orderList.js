function clearParam(){
	$("#endTime").textbox('setValue','')
	$("#startTime").textbox('setValue','')
}

function getOrderLog(orderId, succCall, errorCall){
	$.ajax({
		url:"/shop/mall/orderLog.shtm",
		type:"get",
		data:{"orderId":orderId},
		error:function(msg){
			console.error(msg);
			alert("网络出错");
			if (errorCall != null) {
				errorCall(msg);
			}
		},
		success:function(msg){
			if (succCall != null) {
				succCall(msg);
			}
		}
	});
}

function showOrderLog(msg){
	// console.debug(msg);
	var dom = "";
	if(msg.orderLog.length < 1){
		dom += "<li>暂无日志信息</li>";		
	}else{
		for(var item in msg.orderLog){
			dom += "<li>" + msg.orderLog[item].message + "</li>";
		}	
	}		
	$("#win .order-log-list").html(dom);
}

// 打开订单详细信息弹出框
function openWin(row) {	
	getOrderLog(row.order_id, showOrderLog, null);
	var rowCatch = row;
	$("#win").window("open");	
	// 更新订单状态
	function updateOrderStatus(orderId, orderStatusCode, succCall, errorCall){
		$.ajax({
			url:"/shop/mall/updateOrderStatus.shtm",
			type:"get",
			data:{"orderId":orderId,"statusCode":orderStatusCode},
			error:function(msg){
				alert("网络出错");
				console.error(msg);
				if (errorCall != null) {
					errorCall(msg)();
				}
			},
			success:function(msg){
				if (succCall != null) {
					succCall(msg);
				}
			}
		});
	}

	function updateOrderStatusOperation($dom, orderId, orderStatus){		
		$dom.addClass("active");
		$dom.data("orderId", orderId);
		$dom.on("click", function(){
			// console.debug(orderStatus);
			updateOrderStatus(orderId, 
				orderStatus.code,
				function(){alert("操作成功");getOrderLog(orderId, showOrderLog, null);},
				null);
			$dom.off();
			$dom.removeClass("active");			
			openWin(rowCatch);
		})
	}

	// 加载指定订单基本信息
	function initBaseInfoOfOrder(data) {
		console.debug(data);
		$("#operation-group .operation-btn").removeClass("active");
		$("#operation-group .operation-btn").off();
		console.debug("data.orderDetail.status=" + data.orderDetail.status);
		if (data.orderDetail.status == 0) {//等待付款

		}else if (data.orderDetail.status == 1) {//已付款等待确认			
			updateOrderStatusOperation($("#win .confirm-pay"),
				data.orderDetail.order_id, 
				orderStatus.PAYED);
		}else if (data.orderDetail.status == 2) {//已确认付款，可配货		
			updateOrderStatusOperation($("#win .preparing-goods"),
				data.orderDetail.order_id, 
				orderStatus.DISTRIBUTING);
		}else if (data.orderDetail.status == 3) {//配货中，可发货
			updateOrderStatusOperation($("#win .sending-goods"),
				data.orderDetail.order_id, 
				orderStatus.DELIVERED);
		}else if (data.orderDetail.status == 4) {//已发货

		}else if (data.orderDetail.status == 5) {//已取消

		}else if (data.orderDetail.status == 6) {//成功

		}else{
			alert("系统出错，请通知管理员");
			return;
		}

		$("#win .paymoney-info").text(data.orderDetail.paymoney);
		// console.debug(data);
		$("#win .order-id-info").text(data.orderDetail.order_id);
		$("#win .goods-id-info").text(data.goodsDetail.goods_id);
		$("#win .goods-name-info").text(data.goodsDetail.name);
		$("#win .price-info").text(data.productDetail.price);

		$("#win .goods_amount").text(data.orderDetail.goods_amount);
		$("#win .order_amount").text(data.orderDetail.order_amount);
		$("#win .weight-info").text(data.orderDetail.weight);
		$("#win .gainedpoint-info").text(data.orderDetail.gainedpoint);

		if (data.memberDetail != null) {
			$("#win .member-name").text(data.memberDetail.uname);
			$("#win .member-email").text(data.memberDetail.email);
			$("#win .member-mobile").text(data.memberDetail.mobile);	
		}

		$("#win .ship-name").text(data.orderDetail.ship_name);
		$("#win .ship-email").text(data.orderDetail.ship_email);
		$("#win .ship-mobile").text(data.orderDetail.ship_mobile);

	}
	$.ajax({		
		url:"/shop/mall/getOrderDetailByOrderId.shtm",
		type:"get",
		data:{"orderId":row.order_id},
		error:function(msg){
			console.error(msg);
			alert("网络出错");
		},
		success:function(msg){
			initBaseInfoOfOrder(msg);
		}
	});

}


// 获取过滤条件
function getParam() {
	var grid = $('#order-table');
	var options = grid.datagrid('getPager').data("pagination").options;
	var curr = options.pageNumber;
	var total = options.total;
	var max = Math.ceil(total / options.pageSize);

	var param = {
		"pageSize": options.pageSize,
		"pageNumber": options.pageNumber,		
	};
	// console.info(options);
	return param;
}

function refreshGrid(param) {
	// $("#order-table").datagrid('loading'); //显示加载信息
	function loadGrid(array){
		$("#order-table").datagrid({
			columns:[[				
				{field:"order_id", title:"编号", align:"center", width:50},
				{field:"createDateStr", title:"下单日期", align:"center", width:80},
				{field:"orderStatusDesc", title:"订单状态", align:"center", width:50},
				{field:"paymoney", title:"订单总额", align:"center", width:50},
				{field:"uname", title:"收货人", align:"center", width:50},
				{field:"payStatusDesc", title:"付款状态", align:"center", width:50},
				{field:"payment_name", title:"支付方式", align:"center", width:50},
				{field:"pay_status", title:"支付确认", width:50,hidden:true},
				{field:'operation',title:'操作', width:50,align:"center",
					formatter: function(value,row,index){
						var json = JSON.stringify(row);
						var dom = "<a href='javascript:openWin("+json+");' >操作</a>";
						return dom;						
					}
				}
			]],
			data: array
		});
		
	}	
	$.ajax({
		type: "post",
		url: "/shop/mall/getAllOrders.shtm",
		data: param,
		error: function(msg) {
			console.error(msg);
			alert("网络出错");
		},
		success: function(msg) {

			// console.debug(msg);
			// return;
			var array = [];

			for (var item in msg.data) {
			// 	var dom = "<a href='javascript:openWin();' >操作</a>";
			// 	msg.data[item].operation = dom;
				array.push(msg.data[item]);
			}
			loadGrid(array);			
			var p = $('#order-table').datagrid('getPager');
			$(p).pagination({
				total: msg.total,
				pageNumber: msg.page,
				showRefresh: false,
				beforePageText: '第', //页数文本框前显示的汉字 
				afterPageText: '页 共 {pages} 页',
				displayMsg: '共{total}条数据',
				onChangePageSize: function() {
					// alert('pagesize changed');  
				},
				onSelectPage: function(pageNumber, pageSize) {　　
					var param = {
						"pageNumber": pageNumber,
						"pageSize": pageSize
					};
					updateDataGrid(param);
					console.info(pageNumber + "||" + 　pageSize);
				}
			});
		}
	});
}

function bindEvent() {
	$("#search-btn").click(function(){
		var param = getParam();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var orderStatusCode = $("#order-status").combobox('getValue');
		if(startTime.length > 3){
			param.startTime = startTime;
		}
		if (endTime.length > 3) {
			param.endTime = endTime;
		}
		console.debug("==" + orderStatusCode);
		param.status = orderStatusCode;
		refreshGrid(param);	
	});
	$("#clear-btn").click(clearParam);
	
	
}

function init() {
	refreshGrid(getParam());
	bindEvent();
}

$(init);