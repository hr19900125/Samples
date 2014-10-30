package com.ryan.java.util.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
	private static final long MillihourPerDay = 3600000L;
	private static final long MillisecondPerMinute = 60000L;
	private static final long MillisecondsPerDay = 86400000L;

	public static final Date string2DateTime2(String stringDate)
			throws ParseException {
		if (stringDate == null) {
			return null;
		}
		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return ymdhmsFormat.parse(stringDate);
	}

	public static final String hmsFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat hmsFormat = new SimpleDateFormat("HH:mm:ss");
		return hmsFormat.format(date);
	}

	public static final String hmFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat hmFormat = new SimpleDateFormat("HH:mm");
		return hmFormat.format(date);
	}

	public static final String ymdwFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat ymdwFormat = DateFormat.getDateInstance(0);
		return ymdwFormat.format(date);
	}

	public static final String ymdhmsFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ymdhmsFormat.format(date);
	}

	public static final String ymdFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		return ymdFormat.format(date);
	}

	public static final String ymdhmFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat ymdhmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return ymdhmFormat.format(date);
	}

	public static final Date ymdString2Date(String ymdStringDate)
			throws ParseException {
		if (ymdStringDate == null) {
			return null;
		}

		DateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		return ymdFormat.parse(ymdStringDate);
	}

	public static final Date ymString2Date(String ymStringDate)
			throws ParseException {
		if (ymStringDate == null) {
			return null;
		}

		DateFormat ymFormat = new SimpleDateFormat("yyyy-MM");
		return ymFormat.parse(ymStringDate);
	}

	public static final Date hmString2DateTime(String hmStringDate)
			throws ParseException {
		if (hmStringDate == null) {
			return null;
		}

		DateFormat hmFormat = new SimpleDateFormat("HH:mm");
		return hmFormat.parse(hmStringDate);
	}

	public static String getDiffStringDate(Date dt, int diff) {
		Calendar ca = Calendar.getInstance();

		if (dt == null) {
			ca.setTime(new Date());
		} else {
			ca.setTime(dt);
		}

		ca.add(5, diff);

		return ymdFormat(ca.getTime());
	}

	public static String getDiffStringDateAll(Date dt, int diff) {
		Calendar ca = Calendar.getInstance();

		if (dt == null) {
			ca.setTime(new Date());
		} else {
			ca.setTime(dt);
		}

		ca.add(5, diff);

		return ymdhmsFormat(ca.getTime());
	}

	public static boolean checkTime(String statTime) {
		if (statTime.length() > 8) {
			return false;
		}

		String[] timeArray = statTime.split(":");

		if (timeArray.length != 3) {
			return false;
		}

		for (int i = 0; i < timeArray.length; i++) {
			String tmpStr = timeArray[i];
			try {
				Integer tmpInt = new Integer(tmpStr);

				if (i == 0) {
					if ((tmpInt.intValue() > 23) || (tmpInt.intValue() < 0)) {
						return false;
					}

				} else if ((tmpInt.intValue() > 59) || (tmpInt.intValue() < 0)) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	public static Date string2DateTime(String date, String time) {
		if (time.length() > 8) {
			try {
				return ymdhmsString2DateTime(date);
			} catch (ParseException e) {
				return null;
			}
		}

		int colonNums = time.split(":").length;

		if (colonNums == 0) {
			time = time + ":00:00";
		} else if (colonNums == 1) {
			time = time + ":00";
		}
		try {
			return ymdhmsString2DateTime(date + " " + time);
		} catch (ParseException e) {
			try {
				return ymdhmsString2DateTime(date);
			} catch (ParseException e1) {
			}
		}
		return null;
	}

	public static final Date ymdhmsString2DateTime(String ymdhmsStringDate)
			throws ParseException {
		if (ymdhmsStringDate == null) {
			return null;
		}

		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ymdhmsFormat.parse(ymdhmsStringDate);
	}

	public static final Long ymdhmsString2DateLong(String stringDate)
			throws ParseException {
		Date d = ymdhmsString2DateTime(stringDate);

		if (d == null) {
			return null;
		}

		return new Long(d.getTime());
	}

	public static boolean compareStringDateTime(String planTime,
			String reallyTime) throws ParseException {
		Date dt1 = ymdhmsString2DateTime(planTime);
		Date dt2 = ymdhmsString2DateTime(reallyTime);

		return dt1.after(dt2);
	}

	public static final Date dayMinus(Date d, int intev) {
		long dl = d.getTime();

		return new Date(dl - intev * 86400000L);
	}

	public static final Date increaseDate(Date aDate, int days) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(aDate);
		cal.add(5, days);
		return cal.getTime();
	}

	public static final Date increaseHourDate(Date d, int intev) {
		if (d == null) {
			d = new Date();
		}

		long dl = d.getTime();

		return new Date(dl + intev * 3600000L);
	}

	public static final Date increaseMinuteDate(Date d, int intev) {
		if (d == null) {
			d = new Date();
		}

		long dl = d.getTime();

		return new Date(dl + intev * 60000L);
	}

	public static final Date getQuarterFirst() {
		Calendar cal = Calendar.getInstance();

		int m = cal.get(2);
		int n = 0 - m % 3;

		cal.add(2, n);
		cal.set(5, 1);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);

		Date first = cal.getTime();

		return first;
	}

	public static final int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(1);
	}

	public static final int getYear(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(1);
	}

	public static final int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(2) + 1;
	}

	public static final int getMonth(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(2) + 1;
	}

	public static final int getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(5);
	}

	public static final int getDate(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(5);
	}

	public static final int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(11);
	}

	public static final int getHour(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(11);
	}

	public static final Date zerolizedTime(Date fullDate) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(fullDate);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return cal.getTime();
	}

	public static String getCurrentMonthEndDate() {
		Calendar ca = Calendar.getInstance();

		ca.setTime(new Date());
		ca.set(11, 23);
		ca.set(12, 59);
		ca.set(13, 59);
		ca.set(5, 1);
		ca.add(2, 1);
		ca.add(5, -1);

		Date lastDate = new Date(ca.getTime().getTime());

		return ymdFormat(lastDate);
	}

	public static String getCurrentMonthStartDate() {
		Calendar ca = Calendar.getInstance();

		ca.setTime(new Date());
		ca.set(11, 0);
		ca.set(12, 0);
		ca.set(13, 0);
		ca.set(5, 1);

		Date firstDate = ca.getTime();

		return ymdFormat(firstDate);
	}

	public static final int yearIntev(Date d1, Date d2) {
		if ((d1 == null) || (d2 == null)) {
			return -1;
		}

		int intev = 0;
		try {
			DateFormat yFormat = new SimpleDateFormat("yyyy");
			int s1 = Integer.parseInt(yFormat.format(d1));
			int s2 = Integer.parseInt(yFormat.format(d2));
			intev = s2 - s1;
		} catch (Exception localException) {
		}

		return intev;
	}

	public static final boolean isPeriod(Date start, Date end) {
		return (start.getTime() <= System.currentTimeMillis())
				&& (end.getTime() >= System.currentTimeMillis());
	}

	public static Date toGMT(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c = toGMT(c);
		return c.getTime();
	}

	public static Calendar toGMT(Calendar cal) {
		Calendar cal1 = (Calendar) cal.clone();
		TimeZone tzSave = cal.getTimeZone();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal1.set(cal.get(1), cal.get(2), cal.get(5), cal.get(11), cal.get(12),
				cal.get(13));
		cal1.set(14, cal.get(14));
		cal.setTimeZone(tzSave);
		cal.getTime();
		return cal1;
	}
}
