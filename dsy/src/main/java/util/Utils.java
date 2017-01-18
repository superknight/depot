package util;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
 
/**
 * <p>题目: 公有方法类</p>
 * <p>描述: 提供一系列常用的公有方法</p>
 * <p>版权所有: Copyright (c) 2016</p>
 *
 * @version 1.0
 */


public class Utils {

	static Logger logger=Logger.getLogger(Utils.class);
			
  /**
   *日期格式包含年月日，如：2003-10-01
   */
  public final static String YEAR_MOUTH_DAY = "yyyy-MM-dd";
  /**
   *紧凑型日期格式包含年月日，如：20031001
   */
  public final static String YEAR_MOUTH_DAY_C = "yyyyMMdd";
  /**
   *日期格式包含年、月、日、小时、分钟、秒、毫秒，如：2003-10-01 10:20:15:200
   */
  public final static String YEAR_TO_MSECOND = "yyyy-MM-dd HH:mm:ss:SSS";
  /**
   *紧凑型日期格式包含年、月、日、小时、分钟、秒、毫秒，如：20031001102015200
   */
  public final static String YEAR_TO_MSECOND_C = "yyyyMMddHHmmssSSS";
  /**
   *紧凑型日期格式包含年、月、日、小时、分钟、秒,如：20031001102015
   */
  public final static String YEAR_TO_SECOND_C = "yyyyMMddHHmmss";
  /**
   *日期格式包含年月，如：2003-10
   */
  public final static String YEAR_MOUTH = "yyyy-MM";
  /**
   *紧凑型日期格式包含年月，如：200310
   */
  public final static String YEAR_MOUTH_C = "yyyyMM";

  /**
   *日期格式包含年、月、日，如：2003-10-01
   */
  public final static String SIMPLE_DATE = "yyyy-MM-dd";
  /**
   *日期格式包含年、月、日，如：2003-10-01 12:02:02
   */
  public final static String SIMPLE_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

  public final static String SIMPLE_DATE_MONTH = "yyyy-MM";
  /**
   * 私有构造函数，禁止用户实例化.
   */
  private Utils() {
    super();
  }
  
  /**
   * 对中文进行GBK转码.
   *
   * @param strSrc 要转码的内容
   * @return 转码后的内容
   */
  public static String encodeGbk(String strSrc) {

    if (strSrc == null) {
      return "";
    }
    try {
  
      strSrc = new String(strSrc.trim().getBytes(
          "ISO-8859-1"), "GBK");
    }
    catch (IOException IOe) {
    	logger.error("error", IOe);
    }

    return strSrc.trim();
  }

  /**
   * 字符串转码
   * @param a_str 字符串值
   * @param a_orig: 如 "ISO-8859-1"
   * @param a_dest: 如 "GB2312"
   * @return 转码后的值
   * @throws UnsupportedEncodingException
   */
  public static String convertEncode(String a_str, String a_orig,
                                     String a_dest) throws
      UnsupportedEncodingException {
    byte[] temp = a_str.getBytes(a_orig);
    return new String(temp, a_dest);
  }

  /**
   * 将字符串以char分割, 如:
   * 字符串 "1,2,3,4,5" 被字符','分割成含字符串"1","2","3","4","5"的String数组
   * @param a_str 被分隔的字符串
   * @param a_ch 分隔符
   * @return
   */
  public static String[] split(String a_str, char a_ch) {
    char[] splitter = {
        a_ch};
    return split(a_str, new String(splitter));
  }

  /**
   * 将字符串以String分割, 如:
   * 字符串 "1,2,3,4,5" 被字符串","分割成含字符串"1","2","3","4","5"的String数组
   * @param a_str 被分隔的字符串
   * @param a_ch 分隔字符串
   * @return
   */
  public static String[] split(String a_str, String a_ch) {
    Vector<String> ret = new Vector<String>();
    int pos = 0;
    while (a_str.length() > 0) {
      String tmp = null;
      pos = a_str.indexOf(a_ch);
      if (pos != -1) { // still has splitter
        tmp = a_str.substring(0, pos);
        a_str = a_str.substring(pos + a_ch.length());
      }
      else {
        tmp = a_str;
        a_str = "";
      }
      ret.add(tmp);
    }

    // 将Vector中数据导入数组
    int count = ret.size();
    String[] results = new String[count];
    for (int i = 0; i < count; i++) {
      results[i] = (String) ret.elementAt(i);
    }
    return results;
  }

  /**
   * 将字符串拆分为ArrayList格式.
   *
   * @param strRoleIdList 要拆分的内容
   * @param strDelim 分隔符
   * @return 拆分为ArrayList格式后的内容
   */
  public static ArrayList<?> toArrayList(String strSrc, String strDelim) {
    ArrayList<Object> alList = new ArrayList<Object>();
    if (strSrc == null || strSrc.trim().length() == 0) {
      return alList;
    }

    StringTokenizer token = new StringTokenizer(strSrc, strDelim);
    while (token.hasMoreElements()) {
      alList.add(token.nextElement());
    }

    return alList;
  }

  /**
   * 合并两个List，重复元素只保存一份.
   *
   * @param list1 要合并的第一个List
   * @param list2 要合并的第二个List
   */
  @SuppressWarnings("all")
  public static List<?> combineTwoLists(List<?> list1, List list2) {
    for (int i = 0; i < list1.size(); i++) {
      if (!list2.contains(list1.get(i))) {
        list2.add(list1.get(i));
      }
    }

    return list2;
  }

  /**
   * 取得当前系统时间.
   *
   * @return Timestamp 当前系统时间
   * @param strFormat 取得的系统时间的表示格式，默认为“yyyy-MM-dd HH:mm:ss”
   */
  public static Timestamp getCurrentTime(String strFormat) {
    if (strFormat == null || strFormat.trim().equals("")) {
      strFormat = "yyyy-MM-dd HH:mm:ss";
    }
    // 取得当前时间
    java.text.SimpleDateFormat formater =   new java.text.SimpleDateFormat(strFormat);
    java.util.Date date = new java.util.Date();
    String dateString = formater.format(date);

    return java.sql.Timestamp.valueOf(dateString);
  }

  /**
   * 取得当前系统时间.
   *
   * @return Timestamp 以“yyyy-MM-dd HH:mm:ss”格式表示的当前系统时间
   */
  public static Timestamp getCurrentTime() {
    return getCurrentTime(null);
  }

  /**
   * 将字符型数字转为Date类型
   * @param value  要转换的字符
   * @return       Date类型的日期
   */
  public static java.util.Date stringToDate(String value) {
    try {
      if (value == null || value.equals("")) {
        return null;
      }
      if (value.length() <= 17) {
        for (; value.length() < 17;) {
          value += "0";
        }
      }
      else {
        value = value.substring(0, 16);
      }
      SimpleDateFormat _formatdate = new SimpleDateFormat(
          YEAR_TO_MSECOND_C,
          Locale.getDefault());
      java.util.Date _date = _formatdate.parse(value);
      return _date;
    }
    catch (Exception ex) {
    	logger.error("error", ex);
      return null;
    }
  }

  /**
   * 将字符型数字转为Date类型
   * @param value  要转换的字符
   * @param DateTypeString  日期格式
   * @return       Date类型的日期
   */
  public static java.util.Date stringToDate(String value,
                                            String DateTypeString) {
    try {
      if (value == null || value.equals("")) {
        return null;
      }
      if (value.length() <= 17) {
        for (; value.length() < 17;) {
          value += "0";
        }
      }
      else {
        value = value.substring(0, 16);
      }
      SimpleDateFormat formatdate = new SimpleDateFormat(DateTypeString,
          Locale.getDefault());
      java.util.Date date = formatdate.parse(value);
      return date;
    }
    catch (Exception ex) {
    	logger.error("error", ex);
      return null;
    }
  }

  /**
   * 将字符串转换成Timestamp类型, 能够识别的格式包括:
   * yyyy-mm-dd hh:mm:ss.fffffffff
   * yyyy-mm-dd hh:mm:ss
   * yyyy-mm-dd hh:mm
   * yyyy-mm-dd
   * @param a_s 字符串的时间值
   * @return
   * @throws IllegalArgumentException
   */
  public static Timestamp getTimestamp(String a_s) throws
      IllegalArgumentException {

    // s should be in yyyy-mm-dd hh:mm:ss.fffffffff format

    try {
      // assuming yyyy-mm-dd hh:mm:ss.fffffffff
      return Timestamp.valueOf(a_s);
    }
    catch (IllegalArgumentException e) {
    }
    try {
      // assuming yyyy-mm-dd hh:mm:ss
      return Timestamp.valueOf(a_s + ".00000000");
    }
    catch (IllegalArgumentException e) {
    }
    try {
      // assuming yyyy-mm-dd hh:mm
      return Timestamp.valueOf(a_s + ":00.000000000");
    }
    catch (IllegalArgumentException e) {
    }
    try {
      // assuming yyyy-mm-dd
      return Timestamp.valueOf(a_s + " 00:00:00.000000000");
    }
    catch (IllegalArgumentException e) {
    }

    throw new IllegalArgumentException(a_s +
                                       " is not in valid Timestamp format.");
  }

  /**
   * 把包含日期值转换为字符串
   * @param date 日期（日期+时间）
   * @param type 输出类型
   * @return     字符串
   */
  public static String DateTimeToString(java.util.Date date, String type) {
    String DateString = "";
    if (date == null) {
      DateString = "";
    }
    else {
      SimpleDateFormat formatDate = new SimpleDateFormat(type,
          Locale.getDefault());
      DateString = formatDate.format(date);
    }
    return DateString;
  }

  /**
   * 判断是否一个有效日期（包括闰年）
   * @param year 年
   * @param month 月
   * @param day 日
   * @return
   */
  public static boolean checkValidDate(String year, String month, String day) {
    Calendar cal = checkDateValid(year, month, day);
    if (cal == null) {
      return false;
    }
    int newYear = cal.get(Calendar.YEAR);
    if (newYear != Integer.parseInt(year)) {
      return false;
    }
    int newMonth = cal.get(Calendar.MONTH) + 1;
    if (newMonth != Integer.parseInt(month)) {
      return false;
    }
    int newDay = cal.get(Calendar.DATE);
    if (newDay != Integer.parseInt(day)) {
      return false;
    }

    return true;
  }

  private static Calendar checkDateValid(String year, String month,
                                         String day) {
    String dateString = "";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
        Locale.getDefault());
    dateString = year + "-" + month + "-" + day;
    Calendar c = Calendar.getInstance();
    try {
      c.setTime(formatter.parse(dateString));
    }
    catch (ParseException e) {
      //e.printStackTrace();
      c = null;
    }

    return c;
  }

  /**
   * 计算字符串是否为空, 空是指: null 或 空串 或 全是空格的字符串
   * @param a_value
   * @return
   */
  public static boolean isEmpty(String a_value) {
    if (a_value == null || a_value.trim().equals("")) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * 获得一个类的名字，不含包名的
   * @param a_class
   * @return
   */
  public static String getAClassName(Class<?> a_class) {
    if (a_class.getPackage() == null) {
      return a_class.getName();
    }
    else {
      String packageName = a_class.getPackage().getName();
      String className = a_class.getName();
      return className.substring(packageName.length() + 1);
    }
  }

  /**
   * 储存SESSION
   * @param FieldName SESSOIN名
   * @param Value     SESSION值
   * @param request   HttpServletRequest
   */
  public static void setSession(String FieldName, String Value,
                                HttpServletRequest request) {
    try {
      HttpSession session = request.getSession();
      session.setAttribute(FieldName, Value);
    }
    catch (Exception ex) {
    	logger.error("error", ex);
    }

  }

  /**
   * 储存SESSION
   * @param FieldName SESSOIN名
   * @param Value     SESSION值
   * @param request   HttpServletRequest
   */
  public static void setSession(String FieldName, Object Value,
                                HttpServletRequest request) {
    try {
      HttpSession session = request.getSession();
      session.setAttribute(FieldName, Value);
    }
    catch (Exception ex) {
    	logger.error("error", ex);
    }

  }

  /**
   * 得到SEESION值
   * @param FieldName SESSOIN名
   * @param DefaultValue 默认值
   * @param request HttpServletRequest
   * @return
   */
  public static String getSession(String FieldName, String DefaultValue,
                                  HttpServletRequest request) {
    try {
      HttpSession session = request.getSession();
      String value = (String) session.getAttribute(FieldName);
      if (value == null) {
        value = DefaultValue;
      }
      return value;
    }
    catch (Exception ex) {
      logger.error("error", ex);
      return DefaultValue;
    }

  }

  /**
   * 得到SEESION值
   * @param FieldName SESSOIN名
   * @param DefaultValue 默认值
   * @param request HttpServletRequest
   * @return
   */
  public static Object getSession(String FieldName, Object DefaultValue,
                                  HttpServletRequest request) {
    try {
      HttpSession session = request.getSession();
      Object value = session.getAttribute(FieldName);
      if (value == null) {
        value = DefaultValue;
      }
      return value;
    }
    catch (Exception ex) {
    	logger.error("error", ex);
      return DefaultValue;
    }

  }

  public static String trimString(String Value) {
    return Value == null ? "" : Value.trim();
  }



  
  /**
   * 对int写数据库进行处理.
   *
   * @param order 要写入的字段在PreparedStatement中的序号
   * @param strSrc 要写入数据库的值
   * @param stmt PreparedStatement对象
   * @throws SQLException 数据库操作出错时抛出
   */
  public static void intToDB2(int index, String strValue,
                              PreparedStatement stmt) throws SQLException {
    if (strValue == null || strValue.trim().equals("")) {
      stmt.setNull(index, java.sql.Types.INTEGER);
    }
    else {

      int iValue = 0;
      try {
        iValue = Integer.valueOf(strValue).intValue();
      }
      catch (Exception e) {
        throw new SQLException(e.getMessage());
      }
      stmt.setInt(index, iValue);
    }
  }

  /**
   * 对float写数据库进行处理.
   *
   * @param order 要写入的字段在PreparedStatement中的序号
   * @param strSrc 要写入数据库的值
   * @param stmt PreparedStatement对象
   * @throws SQLException 数据库操作出错时抛出
   */
  public static void floatToDB2(int index, String strValue,
                                PreparedStatement stmt) throws SQLException {
    if (strValue == null || strValue.trim().equals("")) {
      stmt.setNull(index, java.sql.Types.FLOAT);
    }
    else {
      float fValue = 0.0f;
      try {
        fValue = Float.valueOf(strValue).floatValue();
      }
      catch (Exception e) {
        throw new SQLException(e.getMessage());
      }
      stmt.setFloat(index, fValue);
    }
  }

  /**
   * 对TimeStamp写数据库进行处理.
   *
   * @param order 要写入的字段在PreparedStatement中的序号
   * @param strSrc 要写入数据库的值
   * @param strFormat TimeStamp的格式字符串
   * @param stmt PreparedStatement对象
   * @throws SQLException 数据库操作出错时抛出
   */
  public static void timestampToDB2(int index, String strValue,
                                    String strFormat, PreparedStatement stmt) throws
      SQLException {
    if (strValue == null || strValue.trim().equals("")) {
      stmt.setNull(index, java.sql.Types.TIMESTAMP);
    }
    else {
      try {
        stmt.setTimestamp(index, Utils.toTimestamp(strValue, strFormat));
      }
      catch (ParseException Pe) {
        throw new SQLException(Pe.getMessage());
      }
    }
  }

  /**
   * 处理SQL条件中的单引号问题，单引号转换为双引号
   * @param value 需要转化的字符串
   * @return 转化后的字符串
   */
  public static String HandleQuotes(String value) {
    return value.replace('\'', '\"');
  }

  /**
   * 将String类型转换为int类型
   * @param value  要转换的字符串
   * @return       int类型，当字符串为空时返回0
   */
  public static int stringToInt(String value) {
    int _result = 0;
    if (value != null && !value.equals("")) {
      _result = Integer.parseInt(value);
    }
    return _result;
  }

  /**
   * 将String类型转换为int类型
   * @param value  要转换的字符串
   * @return       int类型，当字符串为空时返回0
   */
  public static double stringToDouble(String value) {
    double _result = 0;
    if (value != null && !value.equals("")) {
      _result = Double.parseDouble(value);
    }
    return _result;
  }

  /**
   * 将指定格式的日期/时间字符串转换成Timestamp格式.
   *
   * @param strDateTime 日期/时间字符串
   * @param strFormat 日期/时间格式
   * @return Timestamp 类型的日期/时间
   * version 1.0
   * version 1.0.1 由杨建军修改 修改原因:添加strDateTime为NULL时候的判断
   */
  public static Timestamp toTimestamp(String strDateTime, String strFormat) throws
      ParseException {
    if (strDateTime == null) { //add by yangjj <version 1.0.1>
      return null; //add by yangjj <version 1.0.1>
    }
    SimpleDateFormat formater = new SimpleDateFormat(strFormat);
    String dateString = formater.format(formater.parse(strDateTime));
    return Timestamp.valueOf(dateString + " 00:00:00");
  }

  /**
   * 将指定格式的日期/时间字符串转换成Date格式
   * @param strDate    String类型，日期字符
   * @param strFormat  String类型，格式
   * @return           Date类型
   */
  public static java.sql.Date toDate(String strDate, String strFormat) {
    try {
      if (strDate == null || strDate.equals("")) {
        return null;
      }
      else {
        SimpleDateFormat _formatdate = new SimpleDateFormat(strFormat,
            Locale.getDefault());
        java.sql.Date _date = new java.sql.Date( (_formatdate.parse(
            strDate)).getTime());
        return _date;
      }
    }
    catch (Exception ex) {
    	logger.error("error", ex);
      return null;
    }
  }

  /**
   * 将指定格式的日期/时间字符串转换成Date格式
   * @param strDate    String类型，日期字符
   * @param strFormat  String类型，格式
   * @return           Date类型
   * @throws java.lang.Exception
   */
  public static java.util.Date toUtilDate(String strDate, String strFormat) {
    try {
      if (strDate == null || strDate.equals("")) {
        return null;
      }
      else {
        SimpleDateFormat _formatdate = new SimpleDateFormat(strFormat,
            Locale.getDefault());
        java.util.Date _date = new java.util.Date( (_formatdate.parse(
            strDate)).getTime());
        return _date;
      }
    }
    catch (Exception ex) {
    	logger.error("error", ex);
      return null;
      //throw new UnCheckedException();

    }
  }

  /**
   * 将指定格式的‘YYYY-MM-DD’字符串转换成Date格式
   * @param strDate    String类型，日期字符
   * @param strFormat  String类型，格式
   * @return           Date类型
   * @throws java.lang.Exception
   */
  public static java.util.Date toSimpleDate(String strDate) throws
      Exception {
    try {
      if (strDate == null || strDate.equals("")) {
        return null;
      }
      else { //pattern中年日为大写，月为小写:yyyy-MM-dd
        SimpleDateFormat _formatdate = new SimpleDateFormat("yyyy-MM-dd",
            Locale.getDefault());
        java.util.Date _date = new java.util.Date( (_formatdate.parse(
            strDate)).getTime());
        return _date;
      }
    }
    catch (Exception ex) {

      throw ex;

    }
  }

  /**
   * 检查 string b是否包含string a
   * @param    String类型
   * @param    String类型
   * @return   boolean
   */

  public static boolean isInclude(String str, String model) {
    boolean revalue = false;
    int len = str.length();

    String[] FSTRING2 = {
        model};
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < FSTRING2.length; j++) {
        if (str.indexOf(FSTRING2[j]) < 0) {
          revalue = false;
        }
        else {
          revalue = true;
          break;
        }
      }
    }
    return revalue;
  }

  /**
   * 处理Memo类型字段在HTML的显示，空格转换为&nbsp;，回车转换为<br>
   * @param value Memo类型字段的值
   * @return
   */
  public static String HandleMemo(String value) {
    if (value == null) {
      return null;
    }
    // value = value.replaceAll(" ", "&nbsp;");
    // value = value.replaceAll("\r\n", "<br>");//it's the method for jdk1.4
    value = replace(value, " ", "&nbsp;");
    value = replace(value, "\r\n", "<br>");

    return value;
  }

  /**
   * 字符串替换
   * @param    String类型
   * @param    String类型
   * @param    String类型
   * @return   String类型
   */

  public static String replace(String src, String oldstr, String newstr) {

    if (src == null) {
      return null;
    }

    StringBuffer dest = new StringBuffer("");

    int len = oldstr.length();
    int srclen = src.length();
    int pos = 0;
    int oldpos = 0;

    while ( (pos = src.indexOf(oldstr, oldpos)) >= 0) {
      dest.append(src.substring(oldpos, pos));
      dest.append(newstr);
      oldpos = pos + len;
    }

    if (oldpos < srclen) {
      dest.append(src.substring(oldpos, srclen));
    }
    return dest.toString();
  }

  /**
   * 在PreparedStatement中，向INT字段写入NULL值
   * @param stmt
   * @param index
   * @param value
   * @throws SQLException
   */
  public static void SetIntValue(PreparedStatement stmt, int index,
                                 String value) throws SQLException {
    intToDB2(index, value, stmt);
  }

  /**
   * 在PreparedStatement中，向Double字段写入值.
   * @param stmt
   * @param index
   * @param value
   * @throws SQLException
   */

  public static void SetDoubleValue(PreparedStatement stmt, int index,
                                    String strValue) throws SQLException {
    if (strValue != null && strValue.trim().equals("")) {
      strValue = null;
    }

    if (strValue == null) {
      stmt.setNull(index, java.sql.Types.DOUBLE);
    }
    else {

      double iValue = 0;
      try {
        iValue = Double.valueOf(strValue).doubleValue();
      }
      catch (Exception e) {
        throw new SQLException(e.getMessage());
      }
      stmt.setDouble(index, iValue);
    }

  }
  

  /**
   * 把Timestamp的变量转换为SQL语句条件中的值
   * @param Timestamp
   * @return
   */
  public static String getDBDateFieldValue(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    java.util.Date date = new java.util.Date();
    date.setTime(timestamp.getTime());
    return DateTimeToString(date, YEAR_TO_MSECOND_C);
  }

  /**
   * 把java.util.Date的变量转换为SQL语句条件中的值
   * @param Timestamp
   * @return
   */
  public static String getDBDateFieldValue(java.util.Date date) {
    if (date == null) {
      return null;
    }
    return DateTimeToString(date, YEAR_TO_MSECOND_C);
  }

  /**
   * 替换匹配的第一个字符串
   * @param Value 源字符串
   * @param matchString 匹配的字符串
   * @param replaceString 要替换的字符串
   * @return
   */
  public static String replaceFirst(String Value, String matchString,
                                    String replaceString) {
    int pos = Value.indexOf(matchString);
    int len = matchString.length();
    if (pos < 0) {
      return Value;
    }
    String firstString = Value.substring(0, pos);
    String endString = Value.substring(pos + len);
    return firstString + replaceString + endString;

  }

  /**
   * 转换日期字符串，如“2003-10-1”-》“2003-10-01”
   * @param DateString
   * @return
   */
  public static String HandleDateStringWith0(String DateString) {
    try {
      SimpleDateFormat _formatdate = new SimpleDateFormat(SIMPLE_DATE,
          Locale.getDefault());
      java.util.Date _date = _formatdate.parse(DateString);
      return DateTimeToString(_date, SIMPLE_DATE);

    }
    catch (Exception ex) {
      logger.error("error", ex);
      return DateString;
    }

  }


  /**
   * 得到一个字符串的真正长度，如果字符串包含汉字，则一个汉字当作两个字符
   * @param Value  字符串
   * @return       字符串真正长度
   */
  public static int getTrueSizeFromString(String Value) {
    if (Value == null) {
      return 0;
    }
    int intLen = Value.getBytes().length;
    return intLen;

  }

  /**
   * 将一对象转换为字符串
   * @param obj  对象
   * @return       字符串
   */
  public static String getString(Object obj) {
    if (obj == null) {
      return "";
    }
    return obj.toString().trim();

  }

  /**
   * 在日期字符串中取出年的个位数部分
   * @param date  yyyy-MM-dd格式的日期字符串对象
   * @return       字符串
   */
  public static String getYearLast(String date) {
    if (date == null || date.trim().equals("")) {
      return "";
    }
    String year = "";
    String dates[] = date.split("-");
    if (dates.length == 3) {
      year = dates[0]; //取年部分
      if (year.length() > 1) { //取最后一位
        year = year.substring(year.length() - 1, year.length());
      }
    }
    return year;
  }

  /**
   * 在日期字符串中取出年的部分
   * @param date  yyyy-MM-dd格式的日期字符串对象
   * @return       字符串
   */
  public static String getYear(String date) {
    if (date == null || date.trim().equals("")) {
      return "";
    }
    String year = "";
    String dates[] = date.split("-");
    if (dates.length == 3) {
      year = dates[0]; //取年部分

    }
    return year;
  }

  /**
   * 在日期字符串中取出月部分
   * @param date  yyyy-MM-dd格式的日期字符串对象
   * @return       字符串
   */
  public static String getMonth(String date) {
    if (date == null || date.trim().equals("")) {
      return "";
    }
    String month = "";
    String dates[] = date.split("-");
    if (dates.length == 3) {
      month = dates[1]; //取月部分
    }
    return month;
  }

  /**
   * 在日期字符串中取出日部分
   * @param date  yyyy-MM-dd格式的日期字符串对象
   * @return       字符串
   */
  public static String getDay(String date) {
    if (date == null || date.trim().equals("")) {
      return "";
    }
    String day = "";
    String dates[] = date.split("-");
    if (dates.length == 3) {
      day = dates[2]; //取日部分
    }
    return day;
  }

  //取得所有String类型的域
	public static String getFormFields(Class<?> clazz) {
		Field[] fiels = clazz.getDeclaredFields();
		StringBuffer formFields = new StringBuffer("");
		for (int i = 0; i < fiels.length; i++) {
			if (!fiels[i].getType().getName().equals("java.lang.String")) {
				continue;
			}
			if (i > 0) {
				formFields.append(",");
			}
			formFields.append(fiels[i].getName());
		}
		return formFields.toString();
	}
	
	public static String snatchWeb(String p_Weburl) throws Exception {
		String sCurrentLine = "";
		//String return_msg = "";

		InputStream l_urlStream;

		//FileWriter fw;

		String sTotalString = "";
		try {
			URL l_url = new URL(p_Weburl);
			java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();
			l_connection.connect();
			l_urlStream = l_connection.getInputStream();
			java.io.InputStreamReader read = new InputStreamReader(l_urlStream, "GBK");
			java.io.BufferedReader l_reader = new java.io.BufferedReader(read);

			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine;
			}
		} catch (MalformedURLException e) {
			logger.error("error", e);
			throw new Exception("异常 MalformedURLException");
		} catch (UnsupportedEncodingException e) {
			logger.error("error", e);
			throw new Exception("异常 UnsupportedEncodingException");
		} catch (IOException e) {
			logger.error("error", e);
			throw new Exception("异常 UnsupportedEncodingException");
		}

		return sTotalString;
	}
	//数组list不能用这个方法..
	public static String toJsonString(Object object){
		if(object instanceof String[][]){
			JSONArray jarray = JSONArray.fromObject(object);
			return jarray.toString();
		}
		JSONObject json = JSONObject.fromObject(object);
		return json.toString();
	}
	
	//对象拷贝
	public static void fieldCopy(Object from,Object to){		 
		Class<? extends Object> clazzTo  = to.getClass();
		Field[] fldTo = clazzTo.getDeclaredFields();
		List<String> listTo = new java.util.Vector<String>();
		for(Field fl : fldTo){
			listTo.add(fl.getName());
		}
		Class<? extends Object> clazzFrom = from.getClass();
		Field[] fldFrom = clazzFrom.getDeclaredFields();
		for(int i=0;i<fldFrom.length;i++){
			String propertyName = (fldFrom[i].getName());
			if(listTo.contains(propertyName)){
				listTo.remove(propertyName);
				String getMethodName = "get" + propertyName.substring(0,1).toUpperCase() + propertyName.substring(1);
				String setMethodName = "set" + propertyName.substring(0,1).toUpperCase() + propertyName.substring(1); 
				try {
					@SuppressWarnings("all")
					Method getMethod = clazzFrom.getDeclaredMethod(getMethodName, null);
					@SuppressWarnings("all")
					Object object = getMethod.invoke(from, null);
					try{
						Method setMethod = clazzTo.getDeclaredMethod(setMethodName, fldFrom[i].getType());
						setMethod.invoke(to, object);
					}catch(NoSuchMethodException ee){
						
					}
				} catch (Exception e) {					
					logger.error("error", e);
				}  
			}
		}
		
	}
	
    public static String filterSqlStr(String p_Str){
    	if(p_Str==null){
    		return "";
    	}else{
    		String str = p_Str.replaceAll("'", "''");
    		str = str.trim();
    		return str;
    	}
    }
    
	public static String readAll(String filepath) {
		java.io.InputStream in = null;
		in = Utils.class.getResourceAsStream(filepath);

		if (in == null) {
			try {
				in = new java.io.FileInputStream(new java.io.File(filepath));
			} catch (Exception e) {
				logger.error("error", e);
			}
		}

		if (in != null) {
			try {
				int len = in.available();
				byte[] content = new byte[len];
				in.read(content);
				in.close();
				return new String(content, "gb2312");
			} catch (Exception e) {

			}
		}
		return null;
	}
	/**
	 * 根据16进制的RGB计算得到Color对象
	 * @param p_Rgb
	 * @return
	 */
	public static Color getColor(String p_Rgb){
		if(p_Rgb == null){
			return new Color(0);
		}else if(p_Rgb.length()!=6){
			return new Color(0);
		}
		
		int iRR = hexTran(p_Rgb.charAt(0))*16 +  hexTran(p_Rgb.charAt(1));
		int iGG = hexTran(p_Rgb.charAt(2))*16 +  hexTran(p_Rgb.charAt(3));
		int iBB = hexTran(p_Rgb.charAt(4))*16 +  hexTran(p_Rgb.charAt(5));
		
		return new Color(iRR,iGG,iBB);
	} 
	

	
	public static int hexTran(char p_Hex){
		int res = 15;
		if(p_Hex >= 'A' && p_Hex<='E'){
			res = (int) p_Hex - (int)'A' + 10;
		}else if (p_Hex >= 'a' && p_Hex<='e'){
			res = (int) p_Hex - (int)'a' + 10;
		}else if(p_Hex>='0' && p_Hex<='9'){
			res = (int) p_Hex - (int)'0';
		}
		return res;
	}
	
	/**
	 * 得到中文字符串
	 * @param p_Str String
	 * @return String
	 */
	public static String toGBChar(String p_Str) {
		try {
			byte[] dbbyte1 = p_Str.getBytes("iso-8859-1");
			return new String(dbbyte1);
		} catch (UnsupportedEncodingException ex) {
			logger.error("error", ex);
			return null;
		}
	}
	
	/**
	 * sql相关的特殊字符过虑
	 * @param p_Str
	 * @return
	 */
	public static String sqlFilter(String p_Str){
		return p_Str.replaceAll("'", "\'");
	}
	
	/**
	 * 两边加点东西
	 * @param p_Str
	 * @return
	 */
	public static String sqlStr(String p_Str){
		return "'" + sqlFilter(p_Str) +"'";
	}
	
	public static void mkdir(String dirpath){
		java.io.File file = new java.io.File(dirpath);
		if(file.exists()){
			return ;
		}
		else {
			String createDirname = dirpath;
			if(dirpath.length() -dirpath.lastIndexOf(".")==4){
				int rightSlash = dirpath.lastIndexOf("/");
				if(rightSlash>-1)
					createDirname = dirpath.substring(0,rightSlash);
				else{
					createDirname = null;
				}
			}
			if(createDirname!=null){
				file = new java.io.File(createDirname);
				if(!file.exists()){
					file.mkdirs();
				}
			}
		}
	}
	
	/**
	 * 从request里拷贝数据到object,只支持基本数据类型以及基本数据类型的数组(多选框时有用).
	 * @param object
	 * @param request
	 */
	public static void setObjectValue(Object object, HttpServletRequest request){
		@SuppressWarnings("unchecked")
		Map<String,String[]> map=request.getParameterMap();
		try {
			Field[] field = object.getClass().getDeclaredFields();
			String fieldName = null;
			for (int i = 0; i < field.length; i++) {
				fieldName = field[i].getName();
				if (!map.isEmpty()) {
					Class<?> type = null;
					Object value = null;
					String[] values = null;
					// if the map contain the key,invoke the method
					if (map.containsKey(fieldName)) {
						values = map.get(fieldName);
						type = field[i].getType();
						field[i].setAccessible(true);
						if (type.equals(Character.class)) {
							value = Character
									.valueOf(values[0].toCharArray()[0]);
						} else if (type.equals(String.class)) {
							value = values[0];
						} else if (type.equals(Byte.class)) {
							value = Byte.valueOf(values[0]);
						} else if (type.equals(Short.class)) {
							value = Short.valueOf(values[0]);
						} else if (type.equals(Integer.class)) {
							value = Integer.valueOf(values[0]);
						} else if (type.equals(Long.class)) {
							value = Long.valueOf(values[0]);
						} else if (type.equals(Float.class)) {
							value = Float.valueOf(values[0]);
						} else if (type.equals(Double.class)) {
							value = Double.valueOf(values[0]);
						} else if (type.equals(Boolean.class)) {
							value = Boolean.valueOf(values[0]);
						} else if (type.equals(java.util.Date.class)) {
							if(values[0].equals("")){
								value=null;
							}else{
								value = DateUtil.parse(values[0], "yyyy-MM-dd HH:mm:ss");
							}
						} else if (type.equals(char.class)) {
							value = values[0].toCharArray()[0];
						} else if (type.equals(byte.class)) {
							value = Byte.parseByte(values[0]);
						} else if (type.equals(short.class)) {
							value = Short.parseShort(values[0]);
						} else if (type.equals(int.class)) {
							value = Integer.parseInt(values[0]);
						} else if (type.equals(long.class)) {
							value = Long.parseLong(values[0]);
						} else if (type.equals(float.class)) {
							value = Float.parseFloat(values[0]);
						} else if (type.equals(double.class)) {
							value = Double.parseDouble(values[0]);
						} else if (type.equals(boolean.class)) {
							value = Boolean.parseBoolean(values[0]);
						} else if (type.equals(Byte[].class)) {
							Byte[] array = new Byte[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Byte.valueOf(values[j]);
							}
							value = array;
						} else if (type.equals(Character[].class)) {
							Character[] array = new Character[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Character.valueOf(values[j]
										.toCharArray()[0]);
							}
							value = array;
						} else if (type.equals(String[].class)) {
							value = values;
						} else if (type.equals(Short[].class)) {
							Short[] array = new Short[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Short.valueOf(values[j]);
							}
							value = array;
						} else if (type.equals(Integer[].class)) {
							Integer[] array = new Integer[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Integer.valueOf(values[j]);
							}
							value = array;
						} else if (type.equals(Long[].class)) {
							Long[] array = new Long[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Long.valueOf(values[j]);
							}
							value = array;
						} else if (type.equals(Float[].class)) {
							Float[] array = new Float[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Float.valueOf(values[j]);
							}
							value = array;
						} else if (type.equals(Double[].class)) {
							Double[] array = new Double[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Double.valueOf(values[j]);
							}
							value = array;
						} else if (type.equals(Boolean[].class)) {
							Boolean[] array = new Boolean[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Boolean.valueOf(values[j]);
							}
							value = array;
						} else if (type.equals(byte[].class)) {
							byte[] array = new byte[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Byte.parseByte(values[j]);
							}
							value = array;
						} else if (type.equals(char[].class)) {
							char[] array = new char[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = values[j].toCharArray()[0];
							}
							value = array;
						} else if (type.equals(short[].class)) {
							short[] array = new short[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Short.parseShort(values[j]);
							}
							value = array;
						} else if (type.equals(int[].class)) {
							int[] array = new int[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Integer.parseInt(values[j]);
							}
							value = array;
						} else if (type.equals(long[].class)) {
							long[] array = new long[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Long.parseLong(values[j]);
							}
							value = array;
						} else if (type.equals(float[].class)) {
							float[] array = new float[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Float.parseFloat(values[j]);
							}
							value = array;
						} else if (type.equals(double[].class)) {
							double[] array = new double[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Double.parseDouble(values[j]);
							}
							value = array;
						} else if (type.equals(boolean[].class)) {
							boolean[] array = new boolean[values.length];
							for (int j = 0; j < values.length; j++) {
								array[j] = Boolean.parseBoolean(values[j]);
							}
							value = array;
						}
						field[i].set(object, value);
					}
				}
			}
		} catch (IllegalArgumentException e) {
			logger.error("error", e);
		} catch (IllegalAccessException e) {
			logger.error("error", e);
		}
	}
	private static String[] zeros={"","0","00","000","0000","00000","000000","000000"};
	
	public static String fillZerosLeft(String str, int length) {
		if (str == null)
			return null;
		else if (str.length() < length) {
			return (zeros[length - str.length()] + str);
		} else {
			return str;
		}
	}
	
//	public static void main(String[] args) throws Exception{
//		
//		//String html = snatchWeb("http://192.168.222.11:9111");
//		//System.out.println(html);
//		OrderNumber od = new OrderNumber();
//		String str = toJsonString(od);
//		System.out.println(str);
//		str = toJsonString(od);
//		System.out.println(str);		
//	}
	
	public static String toSqlDay(String field,String day){
		StringBuffer sb = new StringBuffer(1024);
		sb.append( field +" >=  to_date('"+day+"','yyyy-mm-dd')");
		sb.append(" and " +field+ "<=to_date('"+day+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ");
		return sb.toString();
	}
	
	public static String tranListToJson(List<?> dataList,String[] fields){
		
		String[][] strings = tranListToArray(dataList,fields);
		
		return toJsonString(strings);
	}
	/**
	 * 
	 * @param dataList
	 * @param fields
	 * @return
	 */
	public static String[][] tranListToArray(List<?> dataList,String[] fields){
		if(dataList==null || fields==null) return null;
		String[][] dataArray = new String[dataList.size()][fields.length];
		
		//得到get的方法那些...
		//根据属性名称得到,get方法名称
		Class<? extends Object> domainClass = null;
		Method[] accessField = new Method[fields.length];
		
		if(dataList.size()!=0){
			domainClass = dataList.get(0).getClass();
			Method[] methods = domainClass.getDeclaredMethods();
			for(int i=0;i<fields.length;i++){
				for(int j=0;j<methods.length;j++){
					//System.out.println(methods[j].getName());
				   if(methods[j].getName().equalsIgnoreCase("get" + fields[i])){
					   accessField[i] = methods[j];
					   break;
				   }	
				}
			}		
		}
		//获取数据
		for(int i=0;i<dataList.size();i++){
			Object obj = dataList.get(i);
			for(int j=0;j<accessField.length;j++){
				try {
					Object vl = accessField[j].invoke(obj, (Object[])null);
					if(vl!=null){
						if(vl instanceof java.util.Date){
							dataArray[i][j] = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS",(java.util.Date)vl);
						}else{
							dataArray[i][j] = vl.toString();
						}	
					}
					
				} catch (Exception e) {					
					logger.error("error", e);
				}  
			}
		}
		
		return dataArray;
	}
	
	/**
	 * <p>Method: list to json</p>
	 * @param <T>
	 * @param list
	 * @return String
	 */
	public static <T extends Object> String listToJson(List<T> list) {
		StringBuffer string=new StringBuffer();
		StringBuffer sb = new StringBuffer();
		try {
			int index=0;
			int lastIndex=list.size()-1;
			for (T object : list) {
				if(object!=null){
					sb.append(objectToJson(object));
				}
				if(index<lastIndex){
					sb.append(",");
				}
				//count the list size
				index++;
			}
		} catch (IllegalArgumentException e) {
			logger.error("error", e);
		}
		string.append("[");
		string.append(sb.toString());
		string.append("]");
		return string.toString();
	}
	
	/**
	 * <p>Method: object to json string</p>
	 * @param object
	 * @return json string
	 */
	public static String objectToJson(Object object){
		StringBuffer objectJson = new StringBuffer();
		try {
			if(object!=null){
				objectJson.append("{");
				Method[] methods=object.getClass().getMethods();
				Object value=null;
				for (Method method : methods) {
					String methodName=method.getName();
					if(methodName.startsWith("get")&&methodName.length()>3&&!methodName.equals("getClass")){
						String fieldName=methodName.substring(3,4).toLowerCase()+methodName.substring(4);
						value=method.invoke(object,new Object[]{});
						objectJson.append("'"+fieldName+"':"+"'"+value+"',");
					}
				}
				int totalLength=objectJson.toString().length();
				if(totalLength>1){
					objectJson.delete(totalLength-1,totalLength);
				}
				objectJson.append("}");
			}
		} catch (IllegalArgumentException e) {
			logger.error("error", e);
		} catch (IllegalAccessException e) {
			logger.error("error", e);
		} catch (InvocationTargetException e) {
			logger.error("error", e);
		}
		return objectJson.toString();
	}
	
	public static void saveToFile(String filepath, String content) throws Exception {
		File temp = new File(filepath);
		DataOutputStream outs = new DataOutputStream(new FileOutputStream(temp));
		outs.write(content.getBytes());
		outs.close();
	}

	public static void appendToFile(String filepath, String content) throws Exception {
		File temp = new File(filepath);
		DataOutputStream outs = new DataOutputStream(new FileOutputStream(temp, true));
		outs.write(content.getBytes());
		outs.close();
	}
	
	/**
	 * 把传入的HHMM格式的字符串得到要应的Calendar格式
	 * @param time
	 * @param flag 是否加1天标记
	 * @return
	 */
	public static Calendar datestrHHmmToCalendar(String time, int flag){
		Calendar newtime = new GregorianCalendar();
		newtime.clear();
		
		if(flag == 1){
			newtime.set(Calendar.DATE, newtime.get(Calendar.DATE)+ 1); //夜班车的结束时间是第二天的时间，日期要加1			
		}
		
		int ch = ':';
		if(time.indexOf(ch) > 0){
			newtime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
			newtime.set(Calendar.MINUTE, Integer.parseInt(time.substring(3)));			
		}else{
			newtime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
			newtime.set(Calendar.MINUTE, Integer.parseInt(time.substring(2)));
		}
		return newtime;
	}
	
	/**
	 * 把传入的Calendar格式得到相应的字符串
	 * @param time
	 * @return
	 */	
	public static String calendarToDatestrHHmm(Calendar time){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String returntime = sdf.format(time.getTime());
		return returntime;		
	}
	
	public static double round(double num,int scale){
		return round(num,scale,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 提供精确的小数位四舍五入处理
	 */
	public static double round(double num, int scale, int roundType) {
		if (scale < 0) {
		throw new IllegalArgumentException();
		}
		BigDecimal b = new BigDecimal(Double.toString(num));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, roundType).doubleValue();
	}	

//	public static void main(String[] args){
//		double num = 5.312;
//		System.out.println(Utils.round(num, 1, BigDecimal.ROUND_UP));
//		System.out.println(Utils.round(num, 1, BigDecimal.ROUND_DOWN));
//		System.out.println(Utils.round(num, 1, BigDecimal.ROUND_HALF_UP));
//		System.out.println(Utils.round(num, 1, BigDecimal.ROUND_HALF_DOWN));
//		System.out.println(Utils.round(num, 1, BigDecimal.ROUND_HALF_EVEN));
//	}
	
	public static String HtmlTdFormat(String strs, String split, int columnNum){
		String[] tmp = strs.split(split);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < tmp.length ; i++){
			if(i%columnNum==0){
				sb.append("<br>");
			}else{
				sb.append(tmp[i]).append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 合并数组
	 * @param aArray
	 * @param bArray
	 * @return
	 */
	public static String[] arrayConcat(final String[] aArray,final String[] bArray){
		String[] resultArray=null;
		if(aArray!=null&&bArray!=null){
			resultArray=new String[aArray.length+bArray.length];
			int index=0;
			for(String string:aArray){
				resultArray[index]=string;
				index++;
			}
			for(String string:bArray){
				resultArray[index]=string;
				index++;
			}
		}else if(aArray!=null&&bArray==null){
			resultArray=aArray;
		}else if(aArray==null&&bArray!=null){
			resultArray=bArray;
		}
		return resultArray;
	}

	
	/**
	 * 获取按默认值初始化的数组
	 * @param array
	 * @param defaultValue
	 * @return String[]
	 */
	public static String[] initialStringArray(String[] array,String defaultValue){
		if(array!=null){
			for(int i=0;i<array.length;i++){
				array[i]=defaultValue!=null?defaultValue:"";
			}
		}
		return array;
	}	
	/**
	 * double 
	 * @param val
	 * @param precision
	 * @return
	 */
	public static Double roundDouble(double val, int precision) {   
		  Double ret = null;   
		        try {   
		           double factor = Math.pow(10, precision);   
		           ret = Math.floor(val * factor + 0.5) / factor;   
		        } catch (Exception e) {   
		        	logger.error("error", e);  
		        }   
		       return ret;   
		    }
	
	/**
	 * 根据长度，补足设定的字符(从后补)
	 * @param code
	 * @param number
	 * @param scode
	 * @return
	 */
	public static  String SupplementStr(String code,int number,String scode){
		
		String retcode ="";
		retcode = code;
		int lengt =0;
		lengt = code.length();
		if(number>=lengt){
			for(int i=0;i<(number -lengt );i++){
				retcode = retcode+scode;
			}
			
		}
		
		
		return retcode;
	}
	
	/**
	 * 根据长度，补足设定的字符(从前补)
	 * @param code
	 * @param number
	 * @param scode
	 * @return
	 */
	
public  static String SupplementStrBefore(String code,int number,String scode){
		
		String retcode ="";
		retcode = code;
		int lengt =0;
		lengt = code.length();
		if(number>=lengt){
			for(int i=0;i<(number -lengt );i++){
				retcode = scode+retcode;
			}
			
		}
		
		
		return retcode;
	}

/**
 * 小写转大写
 * @param str
 * @return
 */
public static String convertString(String str){
    String upStr = str.toUpperCase();   
    //String lowStr = str.toLowerCase();   
    StringBuffer buf = new StringBuffer(str.length());   
    for(int i=0;i<str.length();i++)
    {   
//       if(str.charAt(i)==upStr.charAt(i))
//       {   
//           buf.append(lowStr.charAt(i));   
//       }
//      else
      {
          buf.append(upStr.charAt(i));   
       }   
     }   
     return   buf.toString();   
  }
}
