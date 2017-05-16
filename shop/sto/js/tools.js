function getRadio($array){
	for(var i = 0, length = $array.length; i< length; i++){
		if ($array[i].checked) {
			return $array[i].value;
		}
	}
	return null;
}
function trim(str){
	return str.replace(/(^\s*)|(\s$)/g,"");
}

function arrayToJson(array){
	var json={};
	for(var i = 0,len = array.length; i < len; i++){
		json[array[i].name] = array[i].value;
	}
	return json;
}