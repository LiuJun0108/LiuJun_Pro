$.extend($.fn.validatebox.defaults.rules, {
	phone : {
		validator : function(value, param) {
			var reg = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
			var r = value.match(reg);
			if (r == null) {
				return false;
			} else {
				return true;
			}
		},
		message : '请输入有效有手机号码'
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	/* 必须和某个字段相等 */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '两次密码不一致'
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	IDCard : {
		validator : function(value, param) {
			return isIdCardNo(value);
		},
		message : '请输入有效有身份证号码'
	}
});

/** ***************************************************************************************************** */
$.ajaxSetup({
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		//$.messager.alert('系统提示', '系统未知错误', 'error');
	}
});
/**
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 * 
 */
var onLoadError = {
	onLoadError : function(XMLHttpRequest) {
		// 通过XMLHttpRequest取得响应头,sessionstatus
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
		if (sessionstatus == 'timeout') {
			parent.$.messager.alert('系统提示', '对不起！您没有登陆或登陆超时，请重新登陆', 'error');
		} else if (sessionstatus == 'noAuthority') {
			$.messager.alert('系统提示', '您没有访问此功能的权限！请联系管理员给你赋予相应权限', 'error');
		} else {
			$.messager.alert('系统提示', '系统未知错误', 'error');
		}
	}
};
$.extend($.fn.datagrid.defaults, onLoadError);
$.extend($.fn.treegrid.defaults, onLoadError);
$.extend($.fn.tree.defaults, onLoadError);
$.extend($.fn.combogrid.defaults, onLoadError);
// $.extend($.fn.combobox.defaults, onLoadError);
// $.extend($.fn.form.defaults, onLoadError);

/** ***************************************************************************************************** */

function isIdCardNo(num) {
	var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
			2, 1);
	var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3",
			"2");
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
		return false;
	}
	// check and set value
	for (i = 0; i < intStrLen; i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}
	if (intStrLen == 18) {
		// check date
		var date8 = idNumber.substring(6, 14);
		if (isDate8(date8) == false) {
			return false;
		}
		// calculate the sum of the products
		for (i = 0; i < 17; i++) {
			lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = parityBit[lngProduct % 11];
		// check last digit
		if (varArray[17] != intCheckDigit) {
			return false;
		}
	} else { // length is 15
		// check date
		var date6 = idNumber.substring(6, 12);
		if (isDate6(date6) == false) {
			return false;
		}
	}
	return true;
}

function isDate6(sDate) {
	if (!/^[0-9]{6}$/.test(sDate)) {
		return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	if (year < 1700 || year > 2500)
		return false
	if (month < 1 || month > 12)
		return false
	return true
}

function isDate8(sDate) {
	if (!/^[0-9]{8}$/.test(sDate)) {
		return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	day = sDate.substring(6, 8);
	var iaMonthDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ]
	if (year < 1700 || year > 2500)
		return false
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		iaMonthDays[1] = 29;
	if (month < 1 || month > 12)
		return false
	if (day < 1 || day > iaMonthDays[month - 1])
		return false
	return true
}

function formatTimestamp(timestamp) {
	var d = new Date(timestamp);

	var year = d.getYear();
	var month = d.getMonth() + 1;
	var date = d.getDate();
	var hour = d.getHours();
	var minute = d.getMinutes();
	var second = d.getSeconds();
	return year + "-" + month + "-" + date + "   " + hour + ":" + minute + ":"
			+ second;
}