function getRadio($array){
	for(var i = 0, length = $array.length; i< length; i++){
		if ($array[i].checked) {
			return $array[i].value;
		}
	}
	return null;
}