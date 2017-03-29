var Util = {
	// 获取url参数
	getUrlParam : function(name){
		var str = window.location.search.substring(1);
		var params = str.split("&");
		var value = null;
		for (var i in params) {
			var p = params[i].split("=");
			if (name == p[0]) {
				value = p[1];
			}
		}
		return value;
	},

	getAllParams : function(){
		var str = window.location.search.substring(1);
		var map = [];
		// console.info("str=" + str);
		var params = str.split("&");
		// console.info(params);

		for (var i in params) {
			if (params[i].length < 1) {
				params.splice(i, 1);
				continue;
			}
			var p = params[i].split("=");
			map.push({"key" : p[0], "value" : p[1]});			
		}
		return map;
	},

	// 获取当前url，添加或更新参数
	getUpUrl : function(key, value){
		var url = window.location.href;		
		var str = window.location.search.substring(1);
		var map = [];
		var params = str.split("&");
		for (var i in params) {
			if (params[i].length < 1) {
				params.splice(i, 1);
				continue;
			}

			var p = params[i].split("=");
			if (key == p[0]) {
				map.push({"key" : p[0], "value" : name});
			}
			else{
				map.push({"key" : p[0], "value" : p[1]});				
			}			
		}
		if (map.length < 1) {
			if (url.indexOf("?") < 0) {
				return url + "?" + key + "=" + name;
			}
			else{
				return url + key + "=" +  name;	
			}
		}
		else{

		}

	}

};


