package com.rying.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理工具类
 * 
 * @author liuj
 * 
 */
public class DateUtil {
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	// 默认的SimpleDateFormat
	private static SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);

	/**
	 * 表示年期间
	 */
	public static final int PERIOD_TYPE_YEAR = 1;
	/**
	 * 表示月期间
	 */
	public static final int PERIOD_TYPE_MONTH = 2;
	/**
	 * 表示天期间
	 */
	public static final int PERIOD_TYPE_DAY = 3;

	private DateUtil() {
	}

	/**
	 * 把一个特定的String解析为Date
	 * 
	 * @param dateValue
	 * @param pattern
	 *            可能为null
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String dateValue, String pattern)
			throws ParseException {
		if (dateValue == null || dateValue.trim().length() == 0) {
			throw new NullPointerException();
		}

		if (pattern != null) {
			sdf = new SimpleDateFormat(pattern);
		}

		return sdf.parse(dateValue);
	}

	/**
	 * 将Date转成"yyyy-MM-dd HH:mm:ss"格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		if (date == null) {
			throw new NullPointerException();
		}
		sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		return sdf.format(date);
	}

	/**
	 * 把一个Date格式化为特定的String
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			throw new NullPointerException();
		}

		if (pattern != null) {
			sdf = new SimpleDateFormat(pattern);
		}
		return sdf.format(date);
	}

	/**
	 * 取某天的开始，即xxxx-xx-xx 00:00:00时刻
	 * 
	 * @param obj
	 * @return
	 */
	public static Date getDayBegin(Date obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		Calendar ca = new GregorianCalendar();
		ca.setTime(obj);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}

	/**
	 * 取某天的结束，即xxxx-xx-xx 23:59:59时刻
	 * 
	 * @param obj
	 * @return
	 */
	public static Date getDayEnd(Date obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		Calendar ca = new GregorianCalendar();
		ca.setTime(obj);
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		return ca.getTime();
	}

	/**
	 * 取某一天同时刻的下一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		if (date == null) {
			throw new NullPointerException();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * 取某一天同时刻的上一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPrevDay(Date date) {
		if (date == null) {
			throw new NullPointerException();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}

	public static Date scrollDay(Date obj, int scrollNum) {
		if (obj == null) {
			throw new NullPointerException();
		}
		// 日历的创建
		Calendar ca = new GregorianCalendar();
		ca.setTime(obj);
		ca.add(Calendar.DAY_OF_YEAR, scrollNum);
		return ca.getTime();
	}

	public static Date scrollMonth(Date obj, int scrollNum) {
		if (obj == null) {
			throw new NullPointerException();
		}
		Calendar ca = new GregorianCalendar();
		ca.setTime(obj);
		ca.add(Calendar.MONTH, scrollNum);
		return ca.getTime();
	}

	/**
	 * 获得期间开始时间
	 * 
	 * @param periodType
	 *            年度：1;月度：2;日：3
	 * @param selectedDate
	 *            日期
	 * @return
	 */
	public static Date getPeriodStartDate(int periodType, Date selectedDate) {
		if (selectedDate == null) {
			throw new NullPointerException();
		}

		Calendar ca = new GregorianCalendar();
		ca.setTime(selectedDate);
		if (PERIOD_TYPE_YEAR == periodType) {
			ca.set(Calendar.MONTH, 0);
			ca.set(Calendar.DAY_OF_MONTH, 1);
		} else if (PERIOD_TYPE_MONTH == periodType) {
			ca.set(Calendar.DAY_OF_MONTH, 1);
		}
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);

		return ca.getTime();
	}

	/**
	 * 获得期间结束时间
	 * 
	 * @param periodType
	 *            年度：1;月度：2;日：3
	 * @param selectedDate
	 *            日期
	 * @return
	 */
	public static Date getPeriodEndDate(int periodType, Date selectedDate) {
		if (selectedDate == null) {
			throw new NullPointerException();
		}

		Calendar ca = new GregorianCalendar();
		ca.setTime(selectedDate);
		if (PERIOD_TYPE_YEAR == periodType) {
			ca.set(Calendar.MONTH, 11);
			ca.set(Calendar.DAY_OF_MONTH,
					ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else if (PERIOD_TYPE_MONTH == periodType) {
			ca.set(Calendar.DAY_OF_MONTH,
					ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		return ca.getTime();
	}

	/**
	 * 是否第一个期间，如月度时是第一月，按日时是第一月的第一天
	 * 
	 * @param periodType
	 * @param selectedDate
	 * @return
	 */
	public static boolean isPeriodBegin(int periodType, Date selectedDate) {
		if (selectedDate == null) {
			throw new NullPointerException();
		}

		Calendar ca = new GregorianCalendar();
		ca.setTime(selectedDate);
		if (PERIOD_TYPE_MONTH == periodType) {
			if (ca.get(Calendar.MONTH) == 0)
				return true;
		} else if (PERIOD_TYPE_DAY == periodType) {
			if (ca.get(Calendar.MONTH) == 0
					&& ca.get(Calendar.DAY_OF_MONTH) == 1)
				return true;
		}

		return false;
	}

	/**
	 * 当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	public static void print(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
	}
}
