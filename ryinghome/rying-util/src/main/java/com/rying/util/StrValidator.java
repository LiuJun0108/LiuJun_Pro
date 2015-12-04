package com.rying.util;

public class StrValidator {

	/**
	 * 判断字符串是不是为空(null 或者 "");
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		}

		if (str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotNullAndEmpty(String str) {
		return !isNullOrEmpty(str);
	}

	/**
	 * 是否为非负数字
	 * 
	 * @param number
	 */
	public static boolean isNumber(String number) {
		String regEx = "^[1-9]\\d*$";
		return regExMatches(regEx, number);
	}

	/**
	 * 判断字符串长度范围
	 * 
	 * @param str
	 *            目标字符串
	 * @param minLength
	 *            最小长度
	 * @param maxLength
	 *            最大长度
	 * @return
	 */
	public static boolean rangeLength(String str, int minLength, int maxLength) {
		if (isNullOrEmpty(str))
			throw new NullPointerException();

		int _length = str.length();
		int _minLength = minLength;
		int _maxLength = maxLength;

		if (_minLength < 0)
			_minLength = 0;
		if (_maxLength < 0)
			_maxLength = 0;

		if (_minLength > _maxLength)
			throw new IllegalArgumentException();

		if (_length >= _minLength && _length <= _maxLength)
			return true;
		return false;
	}

	/**
	 * 正则表达式判断
	 * 
	 * @param regEx
	 * @param str
	 * @return
	 */
	public static boolean regExMatches(String regEx, String str) {
		return str.matches(regEx);
	}

}
