package com.ryan.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ryan.exception.DateFormatException;

public class DateUtil {
    /** 采用Singleton设计模式而具有的唯一实例 */
    private static DateUtil instance = new DateUtil();
 
    /** 格式化器存储器 */
    private Map formats;
 
    private DateUtil() {
        resetFormats();
    }
 
    /**
     * 通过缺省日期格式得到的工具类实例
     *
     * @return <code>DateUtilities</code>
     */
    public static DateUtil getInstance() {
        return instance;
    }
    /** Reset the supported formats to the default set. */
    public void resetFormats() {
        formats = new HashMap();
        // alternative formats
        formats.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // alternative formats
        formats.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
        // XPDL examples format
        formats.put("MM/dd/yyyy HH:mm:ss a", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a"));
        // alternative formats
        formats.put("yyyy-MM-dd HH:mm:ss a", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a"));
        // ISO formats
        formats.put("yyyy-MM-dd'T'HH:mm:ss'Z'", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        formats.put("yyyy-MM-dd'T'HH:mm:ssZ", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
        formats.put("yyyy-MM-dd'T'HH:mm:ssz", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"));
    }
    /**
     * 对日期进行格式化 格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     *            需格式化的日期
     * @return 格式化后的字符串
     */
    public String format(Date date) {
        Object obj = formats.get("yyyy-MM-dd HH:mm:ss");
        if (obj == null) {
            obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return ((DateFormat) obj).format(date);
    }
 
    /**
     * 解析字符串到日期型
     *
     * @param dateString
     *            日期字符串
     * @return 返回日期型对象
     */
    public Date parse(String dateString) {
        Iterator iter = formats.values().iterator();
        while (iter.hasNext()) {
            try {
                return ((DateFormat) iter.next()).parse(dateString);
            } catch (ParseException e) {
                // do nothing
            }
        }
        return null;
    }
 
    /**
     * 解析字符串为日期类型，如果解析失败并且不抛出异常，那么返回为null； 如果抛出异常，那么抛出DateFormatException。
     *
     * @param dateStr
     *            需解析的日期字符串
     * @param isThrowException
     *            是否允许抛出异常
     * @return <code>Date</code>
     * @throws DateFormatException
     */
    public Date parse(String dateStr, boolean isThrowException) throws DateFormatException {
        Date date = parse(dateStr);
        if (date == null && isThrowException) {
            throw new DateFormatException("Date Format Error:" + dateStr);
        }
        return date;
    }
 
    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;
    public static final long YEAR = DAY * 365;
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
 
    /**
     * 解析日期
     *
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return
     */
    public static Date parse(String date, String pattern) {
        Date resultDate = null;
        try {
            resultDate = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            log.error(e);
        }
        return resultDate;
    }
    /**
     * 解析日期 yyyy-MM-dd
     *
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return
     */
    public static Date parseSimple(String date) {
        Date resultDate = null;
        try {
            resultDate = YYYY_MM_DD.parse(date);
        } catch (ParseException e) {
            log.error(e);
        }
        return resultDate;
    }
 
    /**
     * 格式化日期字符串
     *
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
//    /**
//     * 格式化日期字符串
//     *
//     * @param date
//     *            日期
//     * @param pattern
//     *            日期格式
//     * @return
//     */
//    public static String formatLunar(Date date) {
//        return new Lunar(date).toString();
//    }
    /**
     * 取得当前日期
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
    /**
     * @param offsetYear
     * @return 当前时间 + offsetYear
     */
    public static Timestamp getTimestampExpiredYear(int offsetYear) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, offsetYear);
        return new Timestamp(now.getTime().getTime());
    }
    /**
     * @param offsetMonth
     * @return 当前时间 + offsetMonth
     */
    public static Timestamp getCurrentTimestampExpiredMonth(int offsetMonth) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, offsetMonth);
        return new Timestamp(now.getTime().getTime());
    }
    /**
     * @param offsetDay
     * @return 当前时间 + offsetDay
     */
    public static Timestamp getCurrentTimestampExpiredDay(int offsetDay) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, offsetDay);
        return new Timestamp(now.getTime().getTime());
    }
    /**
     * @param offsetSecond
     * @return 当前时间 + offsetSecond
     */
    public static Timestamp getCurrentTimestampExpiredSecond(int offsetSecond) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, offsetSecond);
        return new Timestamp(now.getTime().getTime());
    }
    /**
     * @param offsetDay
     * @return 指定时间 + offsetDay
     */
    public static Timestamp getTimestampExpiredDay(Date givenDate, int offsetDay) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.DATE, offsetDay);
        return new Timestamp(date.getTime().getTime());
    }
 
    /**
     * 实现ORACLE中ADD_MONTHS函数功能
     *
     * @param offsetMonth
     * @return 指定时间 + offsetMonth
     */
    public static Timestamp getTimestampExpiredMonth(Date givenDate, int offsetMonth) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.MONTH, offsetMonth);
        return new Timestamp(date.getTime().getTime());
    }
    /**
     * @param offsetSecond
     * @return 指定时间 + offsetSecond
     */
    public static Timestamp getTimestampExpiredSecond(Date givenDate, int offsetSecond) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.SECOND, offsetSecond);
        return new Timestamp(date.getTime().getTime());
    }
    /**
     * @param offsetSecond
     * @return 指定时间 + offsetSecond
     */
    public static Timestamp getTimestampExpiredHour(Date givenDate, int offsetHour) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.HOUR, offsetHour);
        return new Timestamp(date.getTime().getTime());
    }
    /**
     * @return 当前日期 yyyy-MM-dd
     */
    public static String getCurrentDay() {
        return DateUtil.format(new Date(), "yyyy-MM-dd");
    }
    /**
     * @return 当前的时间戳 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTime() {
        return DateUtil.getInstance().format(new Date());
    }
    
    public static Date now() {
        return new Date();
    }
    
    /**
     * @return 给出指定日期的月份的第一天
     */
    public static Date getMonthFirstDay(Date givenDate) {
        Date date = DateUtil.parse(DateUtil.format(givenDate, "yyyy-MM"), "yyyy-MM");
        return date;
    }
    /**
     * @return 给出指定日期的月份的最后一天
     */
    public static Date getMonthLastDay(Date givenDate) {
        Date firstDay = getMonthFirstDay(givenDate);
        Date lastMonthFirstDay = DateUtil.getTimestampExpiredMonth(firstDay, 1);
        Date lastDay = DateUtil.getTimestampExpiredDay(lastMonthFirstDay, -1);
        return lastDay;
    }
    
}
