var selectCatId;
var selectedTypeId;
var typeDetail;

// 将序列化获得数组转化为json对象
function arrayToJson(array) {
	var json = {};
	for (var i = 0, length = array.length; i < length; i++) {
		json[array[i].name] = array[i].value;
	}
	return json;
}

function submitForm() {
	var formData = new FormData();
	// 相册
	formData.append("picture1", $("#picture1")[0].files[0]);
	formData.append("picture2", $("#picture2")[0].files[0]);
	formData.append("picture3", $("#picture3")[0].files[0]);
	formData.append("picture4", $("#picture4")[0].files[0]);
	// 基本信息
	formData.append("name", $("#goods-name").val());
	formData.append("catId", selectCatId);
	formData.append("typeId", selectedTypeId);
	formData.append("marketEnable", getRadio($("#marketEnable input")));
	formData.append("mktPrice", $("#mktPrice").val());
	formData.append("point", $("#point").val());
	formData.append("intro", getEditContent());
	// 规格
	formData.append("price", $("#price").val());
	formData.append("cost", $("#cost").val());
	formData.append("weight", $("#weight").val());
	// 参数
	console.debug("==");
	console.debug(typeDetail);
	if (typeDetail.have_parm > 0) {
		for (var i = 0, len = typeDetail.params.length; i < len; i++) {
			for (var j = 0, listLen = typeDetail.params[i].paramList.length; j < listLen; j++) {
				var domName = "#param-edit .group-" + i + " .item-" + j;
				typeDetail.params[i].paramList[j].value = $(domName).val();
			}
		}
		console.debug(typeDetail.params);
		formData.append("params", JSON.stringify(typeDetail.params));
	}
	// 品牌
	if (typeDetail.join_brand > 0) {
		formData.append("brandId", $("#brand-edit .brand").val());
	}
	// SEO
	formData.append("metaKeywords", $("#meta-keywords").val());
	formData.append("metaDescription", $("#meta-description").val());
	// 标签
	formData.append("tag", getRadio($("#edit-tag input")));
	$.ajax({
		url: "/shop/mall/addGoods.shtm",
		type: 'POST',
		data: formData,
		// 告诉jQuery不要去处理发送的数据
		processData: false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType: false,
		beforeSend: function() {
			console.log("正在进行，请稍候");
		},
		success: function(responseStr) {
			if (responseStr.code > 0) {
				console.log("成功" + responseStr);
				alert("添加成功");
			} else {
				console.log("失败");
			}
		},
		error: function(responseStr) {
			console.error(responseStr);
		}
	});
}

function getChildren(data, cat_id) {
	var array = [];
	for (var item in data) {
		if (data[item].parent_id == cat_id) {
			array.push(data[item]);
		}
	}
	return array;
}

function showList(data) {
	var $fDom = $("<li class='handle cat-item'></li>");
	var $sDom = $("<li class='handle cat-item'></li>");
	// 二级分类点击事件
	$sDom.click(function(e) {
		var cat_id = $(this).data("cat_id");
		var name = $(this).data("name");
		selectCatId = cat_id;
		selectedTypeId = $(this).data("type_id")
		var array = getChildren(data, cat_id);
		// console.info("second:" + cat_id);
		$("#thirdList").empty();
		updateCatInfo(null, name, null);
		// 添加三级分类
		for (var item in array) {
			var $dom = $sDom.clone(false);
			$dom.data("cat_id", array[item].cat_id);
			$dom.data("name", array[item].name);
			$dom.data("type_id", array[item].type_id);
			$dom.click(function() {
				updateCatInfo(null, null, $(this).data("name"));
				selectCatId = $(this).data("cat_id");
				selectedTypeId = $(this).data("type_id")
			});
			$dom.text(array[item].name);
			$("#thirdList").append($dom);
		}
	});

	$fDom.click(function(e) {
		var cat_id = $(this).data("cat_id");
		var name = $(this).data("name");
		selectCatId = cat_id;
		selectedTypeId = $(this).data("type_id")
		var array = getChildren(data, cat_id);
		$("#secondList").empty();
		$("#thirdList").empty();
		updateCatInfo(name, null, null);
		// 添加二级分类
		for (var item in array) {
			var $dom = $sDom.clone(true);
			$dom.data("cat_id", array[item].cat_id);
			$dom.data("name", array[item].name);
			$dom.data("type_id", array[item].type_id);
			$dom.text(array[item].name);
			$("#secondList").append($dom);
		}
	});

	// 添加一级分类
	for (var item in data) {
		if (data[item].parent_id < 1) {
			var $dom = $fDom.clone(true);
			$dom.text(data[item].name);
			$dom.data("cat_id", data[item].cat_id);
			$dom.data("name", data[item].name);
			$dom.data("type_id", data[item].type_id);
			$("#firstList").append($dom);
		}
	}
}

function firstListLoad() {
	$.ajax({
		url: "/shop/mall/getGoodsCatList.shtm",
		error: function(msg) {
			console.error(msg);
			alert("网络出错");
		},
		success: function(msg) {
			showList(msg.catList);
		}
	});
}

function initEdit() {
	bkLib.onDomLoaded(function() {
		new nicEditor({
			fullPanel: true,
			iconsPath: "/shop/store/nicEdit/nicEditorIcons.gif"
		}).panelInstance('intro', {
			hasPanel: true
		});
	});
}

function getEditContent() {
	return $("#brief-edit .nicEdit-main").html();
}

function updateCatInfo(f, s, t) {
	// console.info(new Array(f,s,t));
	// console.info($("#first-cat")[0]);
	if (f != null) {
		$("#first-cat").text(f);
		$("#second-cat").empty();
		$("#third-cat").empty();
	} else if (s != null) {
		$("#second-cat").html(" &gt; " + s);
		$("#third-cat").empty();
	} else if (t != null) {
		$("#third-cat").html(" &gt; " + t);
	}
}

function bindEvent() {
	$("#submit-form").click(submitForm);
	// 开始编辑商品参数
	$("#next-btn").click(function() {
		var firstCat = $("#first-cat").text();
		var secondCat = $("#second-cat").text();
		var thirdCat = $("#third-cat").text();
		var catInfo = firstCat + 　secondCat　 + thirdCat;;
		console.info(catInfo);
		if (catInfo.length < 3) {
			alert("请选择分类")
			return;
		} else {
			initGoodsInfo(selectedTypeId);
			$("#cat-info-selected").val(catInfo);
		}
		$("#param-panel").show();
		$("#cat-panel").hide();
	});
	// 返回编辑商品分类
	$("#previous-btn").click(function() {
		$("#param-panel").hide();
		$("#cat-panel").show();
	});
}
// 初始化标签
function initTab() {
	function init(data) {
		console.info(data);
		var dom = "<li><label><input name='tag' type='radio' checked='true' value='-1'>无</label></li>";;
		for (var item in data.result) {
			dom += "<li><label><input name='tag' type='radio' value=\"" + data.result[item].tag_id + "\">" + data.result[item].tag_name + "</label></li>";
		}
		$("#edit-tag").html($(dom));
	}
	$.ajax({
		url: "/shop/tag/getTag.shtm",
		type: "get",
		error: function(msg) {
			console.error(msg);
			alert("网络出错");
		},
		success: function(msg) {
			init(msg);
		}
	});
}

function initGoodsInfo(typeId) {
	console.info("typeId:" + typeId);

	function init(data) {
		typeDetail = data;
		//初始化标签
		initTab();
		// 初始化参数
		if (data.have_parm > 0) {
			console.info("初始化参数");
			var dom = "";
			// console.info(data.params[0]);
			// console.info(data.params[0].paramList);
			for (var r in data.params) {
				dom += "<table class=\"group-" + r + "\">"
				for (var item in data.params[r].paramList) {
					dom += "<tr>";
					dom += "<th>" + data.params[r].paramList[item].name + "</th>";
					dom += "<td><input class='item-" + item + "' type='text' /></td>";
					dom += "</tr>";
				}
				dom += "</table>"
			}
			// console.debug(dom);
			$("#param-edit").append($(dom));
		} else {
			console.info("商品不包含参数");
		}
		// 初始化属性
		if (data.have_prop > 0) {
			var props = data.props;
			var propDom = "";
			for (var it in props) {
				propDom += "<tr>";
				propDom += "<th>" + props[it].name + "</th>";
				// 选项
				propDom += "<td>";
				if (props[it].type == 3) {
					propDom += "<select class='item-" + it + "'>";
					for (var rt in props[it].optionAr) {
						propDom += "<option value='" + props[it].optionAr[rt] + "'>" + props[it].optionAr[rt] + "</option>";
					}
					propDom += "</select>";
				} else if (props[it].type == 1) {
					propDom += "<input type='text' class='item-" + it + "'/>";
				}
				propDom += "</td>";
				propDom += "</tr>";
			}
			$("#prop-edit").html($(propDom));
			console.debug("初始化属性" + propDom);

		} else {
			console.debug("该商品不包括属性")
		}
		if (data.join_brand > 0) {
			var $brandDom = "<th>品牌</th><td><select class='brand'>";
			for (var item in data.brand_list) {
				$brandDom += "<option value='" + data.brand_list[item].brand_id + "''>" + 　data.brand_list[item].name + "</option>";
			}
			$brandDom += "</select></td>";
			$("#brand-edit").html($brandDom);
		} else {
			console.debug("该商品不包含品牌")
		}

	}
	$.ajax({
		url: "/shop/type/getTypeByTypeId.shtm",
		data: {
			"typeId": typeId
		},
		error: function(msg) {
			console.error(msg);
			alert("网络出错");
		},
		success: function(msg) {
			init(msg);
		}
	});
}

$(function() {
	firstListLoad();
	initEdit();
	bindEvent();
});