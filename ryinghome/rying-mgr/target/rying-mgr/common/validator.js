//判断是不是null或字符串("")
function isNullOrEmptyString(obj) {
	if (isNullOrUnderdefined(obj))
		return false;
	if (isStringType(obj)) {
		return obj.length == 0;
	}
	return false;
}

// 判断是不是为null或underdefined类型
function isNullOrUnderdefined(obj) {
	return (obj == null || obj == undefined);
}

// 判断变量是不是字符串类型
function isStringType(obj) {
	return typeof obj === "string";
}

// trim() 方法
String.prototype.trim = function() {  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
} 