package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间日期处理类
 * <p>
 * Title: 公路监测
 * </p>
 * <p>
 * Description: 公路车辆智能监测系统
 * </p>
 * <p>
 * Copyright: 
 * </p>
 * <p>
 * Company: 风能组
 * </p>
 * 
 * @author 胡如根
 * @version 1.0
 */
public class DateUtil {
	
//	public static Logger logger=Logger.getLogger(DateUtil.class);
	
	public DateUtil() {
	}

	public static String format(Calendar calendar, String dateFormat) {
		if (calendar == null)
			return null;
		return new SimpleDateFormat(dateFormat).format(calendar.getTime());
	}

	public static String format(Date date, String dateFormat) {
		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat).format(date);
	}

	/**
	 * 字符转时间
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static Date parse(String dateString, String dateFormat) {
		try {
			return new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (ParseException ex) {
			return null;
		}
	}

	// default yyyy-MM-dd HH:mm:ss
	public static Date parse(String dataString) {
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return parse(formatDate(dataString), dateFormat);
	}

	/**
	 * 将只有年份或者 年－月的日期格式化成为8位整数
	 * 
	 * @param in_date
	 *            String
	 * @return int
	 */
	public static int FormatDate(String in_date) {
		// 2002-1-8, 2002-1 and 2000 的数据转化为8位整数
		String[] ymd = in_date.split("-");
		int y = 0, m = 0, d = 0;
		y = Integer.valueOf(ymd[0]).intValue();

		if (ymd.length > 1) {
			m = Integer.valueOf(ymd[1]).intValue();
		}

		if (ymd.length == 3) {
			d = Integer.valueOf(ymd[2]).intValue();
		}

		// System.out.println("输入" + in_date + " 输出 " + (y * 10000 + m * 100 +
		// d));
		return (y * 10000 + m * 100 + d);
	}

	/**
	 * 2000 02 00 都是八位输入
	 * 
	 * @param in_date
	 *            int
	 * @return int
	 */
	public static int DaysOfMonth(long in_date) {
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		long y = in_date / 10000;
		int m = (int) in_date / 100 % 100;
		boolean bLoopYear = (y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0));
		if (bLoopYear) {
			days[1] = 29;
		}
		return days[m - 1];
	}

	/**
	 * 得到当前的14位数的日期
	 * 
	 * @return long
	 */
	public static long now() {
		java.text.DateFormat df = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss");
		return Long.parseLong(df.format(new java.util.Date()));
	}

	public static String getDate(Date date, String format) {
		String s;
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		s = df.format(date);
		return s;
	}

	public static String getDate(String format) {
		return getDate(null, "yyyyMMdd");
	}

	public static String getDate(Date date) {
		return getDate(date, "yyyyMMdd");
	}

	public static String getDate() {
		return getDate((Date) null);
	}

	public static String getTime() {
		return getTime(null);
	}

	public static String getTime(Date date) {
		return getDate(date, "HHmmss");
	}

	public static String getHour(Date date) {
		return getDate(date, "HH");
	}

	public static String getMinute(Date date) {
		return getDate(date, "mm");
	}

	public static String getDateTime() {
		return getDateTime(null);
	}

	public static String getDateTime(Date date) {
		return getDate(date, "yyyyMMddHHmmss");
	}

	public static String getDateTime2() {
		return getDateTime2(null);
	}

	public static String getDateTime2(Date date) {
		return getDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static java.sql.Date toSqlDate(java.util.Date utilDate) {
		if (utilDate == null)
			return null;
		try {

			java.sql.Date _date = new java.sql.Date(utilDate.getTime());
			return _date;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String getShortDateNow() {

		return getDate(new Date(), "yyyy-MM-dd");
	}

	public static String getDayOfWeekCn() {
		/*
		// if(p_Date == null){
		Date p_Date = new Date();
		// }
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int day = p_Date.getDay();
		*/
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_WEEK) -1;
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return weeks[day];
	}

	/**
	 * 两时间差，以秒为单位
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long timeInterval(Date d1, Date d2) {

		long ms = Long.MAX_VALUE;
		if (d1 != null && d2 != null) {
			ms = d2.getTime() - d1.getTime();
			ms = ms / 1000;
		}

		// java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");

		// System.out.println("d1=" + df.format(d1) + ",d2=" + df.format(d2) + "
		// 返回=" + ms);

		return ms;
	}

	public static String timeIntervalStr(Date start, Date end) {

		// 这里计算
		String res = "";
		long second = timeInterval(start, end);
		long interval = second;

		if (second > 3600) {
			res = second / 3600 + ":";
			second = interval - second / 3600 * 3600;
		} else {
			res = "00:";
		}
		if (second > 60) {
			res += second / 60 + ":";
			second = second - second / 60 * 60;
		} else {
			res += "00:";
		}
		if (second < 0)
			second = 0;
		res += second + " ";

		return DateUtil.formatDate(res);

	}

	/**
	 * 日期增加分钟
	 * 
	 * @param p_Basedate
	 * @param addminut
	 * @return
	 */
	public static java.sql.Timestamp dateadd(java.util.Date p_Basedate,
			int addminute) {
		java.util.GregorianCalendar geo = new GregorianCalendar();
		geo.setTime(p_Basedate);
		geo.add(java.util.Calendar.MINUTE, addminute);

		java.sql.Timestamp res = new java.sql.Timestamp(geo.getTime().getTime());
		// System.out.println("输入： " + p_Basedate.getTime());
		// System.out.println("输出： " + res.getTime());

		return res;
	}

	public static java.sql.Timestamp dateadd(int addminute) {
		return dateadd(new java.util.Date(), addminute);
	}

	/**
	 * 
	 * Function: 两个时间日期之间的间隔，返回秒，如after时间在current时间前 ， 则返回负数 Description:
	 * 比较两个Timestamp大小
	 * 
	 * @return: Author:曾建辉 Date:2006-9-19 Others:
	 */
	public static long compareToTimestamp(Date current, Date after) {
		int second = 0;
		if (current == null) {
			current = new Timestamp(System.currentTimeMillis());
		}
		if (after == null) {
			after = new Timestamp(System.currentTimeMillis());
		}
		long currentInt = current.getTime();
		long afterInt = after.getTime();
		second = (int) (afterInt - currentInt) / (1000);
		return second;
	}

	/**
	 * 
	 * Function: 把yyyy-MM-dd HH:mm:ss字符转为HHmmss字符格式 ，
	 * 
	 * Description:
	 * 
	 * @return: Author:曾建辉 Date:2007-5-8 Others:
	 */
	public static String YYYYMMDDHHMMSSToHHMMSS(String str) {
		SimpleDateFormat sdf_Date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String string = "";
		try {
			if (str.equals("") || str == null) {
				string = "";
			} else {

				string = sdf.format(sdf_Date.parse(str));
			}
		} catch (Exception e) {
//			logger.error("error", e);
			e.printStackTrace();
		}
		return string;
	}

	public static String tranMillisecond(long msecond) {

		long interval = msecond / 1000;
		// 这里计算
		String res = "";
		long second = interval;
		if (second > 3600) {
			res = second / 3600 + ":";
			second = interval - second / 3600 * 3600;
		} else {
			res = "00:";
		}
		if (second > 60) {
			res += second / 60 + ":";
			second = second - second / 60 * 60;
		} else {
			res += "00:";
		}
		if (second < 0)
			second = 0;
		res += second + " ";

		return DateUtil.formatDate(res);

	}

	// 将日期的年月日格式化成为yyyy-mm-dd HH:mm:hh.0.0 的形式,主要就是补零
	/**
	 * Function: 将未格式化的日期和时间格式化为 YY-MM-DD HH:MM:SS 例如: 2001-1-2 4:9:8.0.0 ->
	 * 2001-01-02 04:09:08 2001-1-2 4:9 -> 2001-01-02 04:09:00 2001-1-2 ->
	 * 2001-01-02 4:9:8.0.0 -> 04:09:08 Description: 格式化日期和时间
	 * 
	 * @param p_Date
	 * @return
	 */
	public static String formatDate(String p_Date) {
		String ymdArray[], hmsArray[];
		String strYmd = "";
		String strHms = "";
		int pos;

		if (p_Date == null)
			return p_Date;

		// 去除两端空格
		p_Date = p_Date.trim();

		// 去除.号后面的数字
		pos = p_Date.indexOf(".");
		if (pos > -1) {
			p_Date = p_Date.substring(0, pos);
		}

		// 日期格式化处理
		pos = p_Date.indexOf("-");
		if (pos > -1) {
			ymdArray = p_Date.split("-");

			// 截取年月日中的"日"字符串
			if (ymdArray.length == 3) {
				int posDay = ymdArray[2].indexOf(" ");
				if (posDay > -1) {
					ymdArray[2] = ymdArray[2].substring(0, posDay);
				}
			}

			// 将1位数格式化为2位数
			for (int i = 0; i < ymdArray.length; i++) {
				if (ymdArray[i].length() < 2) {
					ymdArray[i] = 0 + ymdArray[i];
				}
			}

			// 格式化年月日
			if (ymdArray.length == 1) {
				strYmd = ymdArray[0] + "-01-01 ";
			} else if (ymdArray.length == 2) {
				strYmd = ymdArray[0] + "-" + ymdArray[1] + "-01 ";
			} else {
				strYmd = ymdArray[0] + "-" + ymdArray[1] + "-" + ymdArray[2]
						+ " ";
			}
		}

		// 时间格式化处理
		pos = p_Date.indexOf(":");
		if (pos > -1) {
			hmsArray = p_Date.split(":");

			// 截取时分秒中的"时"字符串
			int posHour = hmsArray[0].indexOf(" ");
			if (posHour > -1) {
				hmsArray[0] = hmsArray[0].substring(posHour + 1);
			}

			// 将1位数格式化为2位数
			for (int i = 0; i < hmsArray.length; i++) {
				if (hmsArray[i].length() < 2) {
					hmsArray[i] = 0 + hmsArray[i];
				}
			}

			// 格式化时分秒
			if (hmsArray.length == 1) {
				strHms = hmsArray[0] + ":00:00";
			} else if (hmsArray.length == 2) {
				strHms = hmsArray[0] + ":" + hmsArray[1] + ":00";
			} else {
				strHms = hmsArray[0] + ":" + hmsArray[1] + ":" + hmsArray[2];
			}
		}

		if (strYmd == "" && strHms == "") {
			return p_Date;
		} else {

			return (strYmd + strHms).trim();
		}
	}

	public static Date convert_String_To_Date(String dateString,
			String formatString) throws Exception {
		Date dateObject = null;
		if (null != dateString && !"".equals(dateString)) {
			SimpleDateFormat target = new SimpleDateFormat(formatString);
			dateObject = target.parse(dateString);
		}
		return dateObject;
	}

	public static String convert_Date_To_String(Date dateObject,
			String formatString) {
		if (dateObject == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(dateObject);
	}

	public static String LongToStr14(long lngDate) {
		StringBuffer sb = new StringBuffer();
		long yy = (lngDate / 10000000000l);
		sb.append(yy + "-");

		long mm = (lngDate % 10000000000l / 100000000);
		if (mm < 10) {
			sb.append("0");
		}
		sb.append(mm + "-");

		long dd = (lngDate % 100000000 / 1000000);
		if (dd < 10) {
			sb.append("0");
		}
		sb.append(dd + " ");

		long h = (lngDate % 1000000 / 10000);
		if (h < 10) {
			sb.append("0");
		}
		sb.append(h + ":");

		long m = (lngDate % 10000 / 100);
		if (m < 10) {
			sb.append("0");
		}
		sb.append(m + ":");

		long s = (lngDate % 100 / 1);
		if (s < 10) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	public static String formatHhmm(String hhmm) {
		String str4 = hhmm;
		if (hhmm.length() < 4) {
			String zero = "";
			for (int i = 0; i < 4 - hhmm.length(); i++) {
				zero += "0";
			}
			str4 = zero + hhmm;
		}
		if (hhmm.length() > 4) {
			str4 = hhmm.substring(0, 4);
		}

		return str4.substring(0, 2) + ":" + str4.substring(2);
	}

	public static int compareDate(Date dateOne, Date dateTwo)  {
		int boo = 0;

		if (null != dateOne && null != dateTwo) {
			if (dateOne.getTime() - dateTwo.getTime() > 0)
				boo = 1;
			else if (dateOne.getTime() - dateTwo.getTime() == 0) {
				boo = 0;
			} else if (dateOne.getTime() - dateTwo.getTime() < 0) {
				boo = -1;
			}
		}
		return boo;
	}
	
	public static String[] continuousDateArray(String beginDate, String endDate) throws Exception{
		String[] result = null;
		int days = (int)((parse(endDate, "yyyy-MM-dd").getTime()-parse(beginDate, "yyyy-MM-dd").getTime())/(1000*86400));
		result = new String[days+1];
		result[0] = beginDate;
		Calendar temp = Calendar.getInstance();
		temp.setTime(parse(beginDate, "yyyy-MM-dd"));
		for(int i = 1 ; i < days ; i++){
			temp.add(Calendar.DATE, 1);
			result[i] = format(temp.getTime(),"yyyy-MM-dd");
		}
		result[days] = endDate;		
		return result;
	}
}
