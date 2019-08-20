package com.hcl.fundtransfer.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtil {

	public final static long ONE_SECOND = 1000;
	public final static long SECONDS = 60;

	public final static long ONE_MINUTE = ONE_SECOND * 60;
	public final static long MINUTES = 60;

	public final static long ONE_HOUR = ONE_MINUTE * 60;
	public final static long HOURS = 24;

	public final static long ONE_DAY = ONE_HOUR * 24;

	public final static String EXPANDTIME_MILLI_FORMATTER = "EEE MMM dd, yyyy HH:mm:ss";

	/**
	 * This method is to return the name of the month based on the index
	 * 
	 * @param index
	 * @return
	 */
	public static String getMonth(int index) {
		String month = "";
		switch (index) {
		case 0:
			month = "January";
			break;
		case 1:
			month = "February";
			break;
		case 2:
			month = "March";
			break;
		case 3:
			month = "April";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "June";
			break;
		case 6:
			month = "July";
			break;
		case 7:
			month = "August";
			break;
		case 8:
			month = "September";
			break;
		case 9:
			month = "October";
			break;
		case 10:
			month = "November";
			break;
		case 11:
			month = "December";
			break;
		default:
			break;
		}
		return month;
	}

	/**
	 * This method is to return the name of the month based on the index
	 * 
	 * @param index
	 * @return
	 */
	public static int getMonth(String monthName) {
		if (monthName.equalsIgnoreCase("January"))
			return 0;
		else if (monthName.equalsIgnoreCase("February"))
			return 1;
		else if (monthName.equalsIgnoreCase("March"))
			return 2;
		else if (monthName.equalsIgnoreCase("April"))
			return 3;
		else if (monthName.equalsIgnoreCase("May"))
			return 4;
		else if (monthName.equalsIgnoreCase("June"))
			return 5;
		else if (monthName.equalsIgnoreCase("July"))
			return 6;
		else if (monthName.equalsIgnoreCase("August"))
			return 7;
		else if (monthName.equalsIgnoreCase("September"))
			return 8;
		else if (monthName.equalsIgnoreCase("October"))
			return 9;
		else if (monthName.equalsIgnoreCase("November"))
			return 10;
		else if (monthName.equalsIgnoreCase("December"))
			return 11;
		return -1;
	}

	/**
	 * This method is to convert the duration in long to hh mm ss format
	 * 
	 * @param duration
	 * @return
	 */
	public static String millisToShortDHMS(long duration) {
		String res = "";
		duration /= ONE_SECOND;
		int seconds = (int) (duration % SECONDS);
		duration /= SECONDS;
		int minutes = (int) (duration % MINUTES);
		duration /= MINUTES;
		int hours = (int) (duration % HOURS);

		int days = (int) (duration / HOURS);
		// int days = (int) (duration / HOURS);
		// 1d 17h 18m 15s
		// if (days == 0) {
		res = String.format("%dd %dh %dm %ds", days, hours, minutes, seconds);
		// } else {
		// res = String.format("%dd%02d:%02d:%02d", days, hours, minutes,
		// seconds);
		// }
		return res;
	}

	public static String convertDate(String date, String inputFormat, String outPutFormat) {
		ThreadSafeSimpleDateFormat inFormat = new ThreadSafeSimpleDateFormat(inputFormat);
		ThreadSafeSimpleDateFormat outFormat = new ThreadSafeSimpleDateFormat(outPutFormat);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(inFormat.parse(date));
			return outFormat.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static float Round(float Rval, int Rpl) {
		float p = (float) Math.pow(10, Rpl);
		Rval = Rval * p;
		float tmp = Math.round(Rval);
		return (float) tmp / p;
	}

	public static double Round(double Rval, int Rpl) {
		double p = (double) Math.pow(10, Rpl);
		Rval = Rval * p;
		float tmp = Math.round(Rval);
		return (double) tmp / p;
	}

	/**
	 * This method takes the format and the date object and gives the string in the
	 * given format
	 * 
	 * @param format
	 * @param cal
	 * @return
	 */
	public static String getStringFromCal(String format, Calendar cal) {
		if (cal == null)
			return "";
		ThreadSafeSimpleDateFormat sdfFormat = new ThreadSafeSimpleDateFormat(format);
		String resultedText = sdfFormat.format(cal);
		return resultedText;
	}

	public static String getStringFromCalWithTimezone(String format, Calendar cal) {
		if (cal == null)
			return "";
		ThreadSafeSimpleDateFormat sdfFormat = new ThreadSafeSimpleDateFormat(format, cal.getTimeZone());
		String resultedText = sdfFormat.format(cal);
		return resultedText;
	}

	public static Calendar getCalFromString(String format, String date) {
		if (date == null)
			return Calendar.getInstance();

		ThreadSafeSimpleDateFormat sdfFormat = new ThreadSafeSimpleDateFormat(format);
		Calendar resultedCalender = Calendar.getInstance();

		try {
			resultedCalender.setTime(sdfFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultedCalender;
	}

	public static Calendar getCalFromString(String format, String date, String timeZone) {
		if (date == null)
			return Calendar.getInstance();

		ThreadSafeSimpleDateFormat sdfFormat = new ThreadSafeSimpleDateFormat(format, TimeZone.getTimeZone(timeZone));
		Calendar resultedCalender = Calendar.getInstance();

		try {
			resultedCalender.setTime(sdfFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultedCalender;
	}

	public static Calendar setDate(Calendar actual, Calendar instance) {
		if (actual == null)
			return null;
		actual.set(Calendar.DAY_OF_MONTH, instance.get(Calendar.DAY_OF_MONTH));
		actual.set(Calendar.MONTH, instance.get(Calendar.MONTH));
		actual.set(Calendar.YEAR, instance.get(Calendar.YEAR));
		actual.set(Calendar.DAY_OF_YEAR, instance.get(Calendar.DAY_OF_YEAR));
		return actual;
	}

	public static int getDifference(Calendar from, Calendar to) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		// Set the date for both of the calendar instance
		cal1.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH));
		cal2.set(to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.get(Calendar.DAY_OF_MONTH));

		// Get the represented date in milliseconds
		long milis1 = cal1.getTimeInMillis();
		long milis2 = cal2.getTimeInMillis();

		// Calculate difference in milliseconds
		long diff = milis2 - milis1;

		// Calculate difference in seconds
		long diffSeconds = diff / 1000;

		// Calculate difference in minutes
		long diffMinutes = diff / (60 * 1000);

		// Calculate difference in hours
		long diffHours = diff / (60 * 60 * 1000);

		// Calculate difference in days
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return (int) diffDays;
	}

	public static int getDifferenceInMinutes(Calendar from, Calendar to) {

		// Get the represented date in milliseconds
		long milis1 = from.getTimeInMillis();
		long milis2 = to.getTimeInMillis();

		// Calculate difference in milliseconds
		long diff = milis2 - milis1;

		// Calculate difference in seconds
		long diffSeconds = diff / 1000;

		// Calculate difference in minutes
		long diffMinutes = diff / (60 * 1000);

		return (int) diffMinutes;
	}

	public static long getDifferenceInMillis(Calendar from, Calendar to) {
		long currentTime = 0;
		long pastTime = 0;
		if (from != null)
			currentTime = from.getTimeInMillis();
		if (to != null)
			pastTime = to.getTimeInMillis();
		return currentTime - pastTime;
	}

	public static List<String> getDaysBetweenDates(Calendar from, Calendar to) {
		List<String> days = new ArrayList<String>();
		while (from.before(to)) {
			days.add(getStringFromCal("yyyy-MM-dd", to));
			to.add(Calendar.DATE, -1);
		}
		days.add(getStringFromCal("yyyy-MM-dd", to));
		return days;
	}

	public static Calendar getWeekStartDate(Calendar date) {
		Calendar temp = Calendar.getInstance();
		temp.setTimeInMillis(date.getTimeInMillis());
		temp.add(Calendar.DATE, -7);
		int week = date.get(Calendar.WEEK_OF_YEAR);

		while (temp.before(date)) {
			if (temp.get(Calendar.WEEK_OF_YEAR) == week)
				break;
			temp.add(Calendar.DATE, 1);

		}

		return temp;
	}

	public static Calendar getWeekEndDate(Calendar date) {
		Calendar temp = Calendar.getInstance();

		temp.setTimeInMillis(date.getTimeInMillis());
		temp.add(Calendar.DATE, 7);
		int week = date.get(Calendar.WEEK_OF_YEAR);

		while (temp.after(date)) {
			if (temp.get(Calendar.WEEK_OF_YEAR) == week)
				break;
			temp.add(Calendar.DATE, -1);

		}

		return temp;
	}

	/**
	 * This method is to convert the input string(date) to data query-able date
	 * format tail is for hh:mm:ss
	 * 
	 * @param inputTimeString
	 * @param tail
	 * @return
	 */
	public static String getDataTimeStamp(String inputTimeString, String tail) {
		// if length of input string is greater than 10 then it is assumed to be
		// in yyyy-MM-dd HH:mm:ss format
		if (inputTimeString.length() > 10)
			return inputTimeString;

		return convertDate("MM/dd/yyyy", "yyyy-MM-dd", inputTimeString).concat(tail);
	}

	public static String getDefaultTime(Calendar time) {
		if (time == null)
			time = Calendar.getInstance();
		return getStringFromCal("dd-MM-yyyy HH:mm:ss", time);
	}
}

class ThreadSafeSimpleDateFormat {

	private DateFormat df;

	public ThreadSafeSimpleDateFormat(String format) {
		this.df = new SimpleDateFormat(format);
	}

	public ThreadSafeSimpleDateFormat(String format, TimeZone timeZone) {
		this.df = new SimpleDateFormat(format);
		this.df.setTimeZone(timeZone);
	}

	public synchronized String format(Calendar date) {
		return df.format(date.getTime());
	}

	public synchronized String format(Date date) {
		return df.format(date);
	}

	public synchronized Date parse(String string) throws ParseException {
		return df.parse(string);
	}

}
