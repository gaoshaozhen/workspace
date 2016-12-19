var home = {
	refresh : function(){
		jQuery("#content").html();
	},
	createHide : function(w, h){
		var width = w + "px";
		var height = h + "px";
		var marginLeft = window.innerWidth/2 - w/2;
		var marginTop = window.innerHeight/2 - h/2;
		if(marginLeft < 0){
			marginLeft = "0px";
		}
		else{
			marginLeft = marginLeft + "px";	
		}
		if(marginTop < 0){
			marginTop = "0px";
		}
		else{
			marginTop = marginTop + "px";	
		}
		var hi = jQuery("<div></div");
		hi.css({
			"position" : "fixed",
			"width" : "100%",
			"height" : "100%",
			"background-color" : "#C9C1C1",
			"opacity" : "0.5"
		});
		var win = jQuery("<div></div>");
		win.css({
			"position" : "fixed",
			"width" : width,
			"height" : height,
			"margin-left" : marginLeft,
			"margin-top" : marginTop,
			"background-color" : "white",
			"overflow" : "auto"
		});
		jQuery("body").append(hi);
		jQuery("body").append(win);
		return new Array(hi, win);
	},
	addUser : function(){
		var win = home.createHide(312,188);
		var tableStr = "<table class='add-user' style='margin:24px'>"
						+"<tr>"
						+"<td><label>username：</label></td>"//用户名
						+"<td><input type='text' class='username'></input></td>"
						+"</tr>"

						+"<tr>"
						+"<td><label>password：</label></td>"//密码输入
						+"<td><input type='password' class='password'></input></td>"
						+"</tr>"

						+"<tr>"
						+"<td><label>password：</label></td>"//确认密码
						+"<td><input type='password' class='password1'></input></td>"
						+"</tr>"

						+"<tr>"
						+"<td><label>power：</label></td>"//权限分配
						+"<td><input type='text' class='powerId'></input></td>"
						+"</tr>"

						+"<tr>"
						+"<td></td>"
						+"<td><input class='submit' type='button' value='button' style='float:left'>"
						+"<input type='button' value='cancel' style='float:right'></td>"
						+"</tr>"
						+"</table>";
		var errorDiv = jQuery("<div style='color:red'></div>");
		var table = jQuery(tableStr);
		var submitInfo = function(){
				var username = table.find(".username").val().trim();
				var password = table.find(".password").val().trim();
				var password1 = table.find(".password1").val().trim();
				var powerId = table.find(".powerId").val().trim();
				if (username == "") {
					errorDiv.text("用户名不能为空");
					return;
				}
				if(password == "" || password1 == ""){
					errorDiv.text("密码不能为空");	
					return;
				}
				else if(password != password1){
					errorDiv.text("密码不一致");	
					return;
				}
				if(powerId == ""){
					errorDiv.text("请输入权限");	
					return;	
				}
				var back = function(result){

					home.refresh();
					home.allUserInfo();
					errorDiv.text(result);
					jQuery(win[0]).remove();
					jQuery(win[1]).remove();
				};
				net.addUser({
					"username" : username,
					"password" : password,
					"powerId" : powerId
				},back);
		};

		table.find(".submit").click(function(){
			submitInfo();
		});
		jQuery(win[1]).append(table);
		jQuery(win[1]).append(errorDiv);
	},
	allUserInfo : function(){
		if (jQuery("#content").find(".userInfoTable").length > 0) {
			return;
		}
		// console.log(jQuery("#content").find(".userInfoTable").length);
		var back = function(dataSource){
			var createTable = function(){
				var table = jQuery("<table class='table userInfoTable'></table>")
				var thead = jQuery("<thead></thead>");
				var tbody = jQuery("<tbody></tbody>");
				var getRowElement = function(array){
					var tr = jQuery("<tr></tr>")
					for(var temp in array){
						tr.append("<td>"+array[temp]+"</td>");
					}
					return tr;
				}
				var col = new Array("userId","username","password","powerId");
				// 添加表头
				table.append(getRowElement(col));			
				table.append(jQuery("<caption>用户信息<center><input class='add-user' type='button' value='addUser' onclick='home.addUser()'></center></caption>"));
				table.append(thead);
				// 表格数据
				var list = dataSource.list;
				for(var item in list){
					var ud = "<input class='update-userInfo' type='button' value='update'></input>"
							+"&nbsp;&nbsp;<input class='delete-userInfo' type='button' value='delete'></input>";
					var row = new Array(list[item].userId,
										list[item].username,
										list[item].password,
										list[item].powerId,
										ud);
					tbody.append(getRowElement(row));
				}
				table.append(tbody);
				jQuery("#content").append(table);
			};

			createTable();
			
		};
		net.getAllUser(back);
	}
}
// home.createHide(400,400);