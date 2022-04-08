package api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
/**
 * Created by Ibrahim Olanrewaju Abdulsemiu DEV on 22/12/2016.
 */
public class DateTime {
    public static final String formatterTime = "yyyy-MM-dd HH:mm:ss";
    public static final String formatterMin = "yyyy-MM-dd HH:mm";
    public static final String formatterMinNoYear = "MM-dd HH:mm";
    public static final String formatterCNTime = "yyyy年MM月dd日 HH:mm:ss";
    public static final String formatterDay = "yyyy-MM-dd";
    public static final String formatterDayddlMMlyy = "dd/MM/yy";
    public static final String formatterDaydd_MMM_yyyy = "dd-MMM-yyyy";    //Locale.ENGLISH
    public static final String formattersimpleDay = "yyyyMMdd";
    public static final String formattersimpleMonth = "yyyyMM";
    public static final String formatterMonth = "yyyy-MM";
    public static final String formatterTimeStamp = "yyyyMMddHHmmss";

    public static final String formatterYear = "yyyy";
    public static final String formatterOnlyMonth = "MM";
    public static final String formatterOnlyDay = "dd";
    public static final String formatterOnlyHour = "HH";
    public static final String formatterOnlyMinute = "mm";
    public static final String formatterDayInWeek = "EEE";
    public static final String formatterCNDate = "yyyy年MM月dd日";
    public static final String formatterYearMonth = "yyyy年M月";
    public static final String formatterDayMonth = "MM月dd日";

    public static final long oneDay = 1000 * 3600 * 24;


    public static Date parseTime(String time) {
        try {
            return new SimpleDateFormat(formatterTime).parse(time);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDay(String time) {
        try {
            return new SimpleDateFormat(formatterDay).parse(time);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDaydd_MMM_yyyy(String time) {
        try {
            return new SimpleDateFormat(formatterDaydd_MMM_yyyy, Locale.ENGLISH).parse(time);
        } catch (Exception e) {
            return null;
        }
    }

    public static String timeDir() {
        return new SimpleDateFormat(formattersimpleMonth).format(new Date());
    }

    public static String getTimeStampStr() {
        return new SimpleDateFormat(formatterTimeStamp).format(new Date());
    }

    public static String timeStampStr(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(formatterTimeStamp).format(date);
    }

    public static Date dateFromToday(int days) {
        return getDateLater(todayBegin(), days);
    }

    public static Date todayBegin() {
        return getDayBegin(new Date());
    }

    public static String today() {
        return new SimpleDateFormat(formatterDay).format(new Date());
    }

    public static String cnToday() {
        return new SimpleDateFormat(formatterCNDate).format(new Date());
    }

    public static String now() {
        return new SimpleDateFormat(formatterTime).format(new Date());
    }

    public static String formatDay(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(formatterDay).format(date);
    }

    public static String formatTime(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(formatterTime).format(date);
    }

    public static Date getDayBegin(Date day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(day.getTime());
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        return c.getTime();
    }

    public static Date getMonthBegin(Date day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(day.getTime());
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getYearBegin(Date day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(day.getTime());
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, 1);
        return c.getTime();
    }

    public static Date nextYear(Date now) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(now.getTime());
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }

    public static Date afterYear(int years) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.YEAR, years);
        return c.getTime();
    }

    public static Date nextMonth(Date now) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    public static Date getMonthEnd(Date now) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(now.getTime());
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static Date nextDay(Date day) {
        return new Date(day.getTime() + oneDay);
    }

    public static int getYear(Date now) {
        int result = 0;
        try {
            result = Integer.parseInt(new SimpleDateFormat(formatterYear).format(now));
        } catch (Exception e) {
        }
        return result;
    }

    public static int getMonth(Date now) {
        int result = 0;
        try {
            result = Integer.parseInt(new SimpleDateFormat(formatterOnlyMonth).format(now));
        } catch (Exception e) {
        }
        return result;
    }

    public static int getDay(Date now) {
        int result = 0;
        try {
            result = Integer.parseInt(new SimpleDateFormat(formatterOnlyDay).format(now));
        } catch (Exception e) {
        }
        return result;
    }

    public static int getHour(Date now) {
        int result = 0;
        try {
            result = Integer.parseInt(new SimpleDateFormat(formatterOnlyHour).format(now));
        } catch (Exception e) {
        }
        return result;
    }

    public static int getMinute(Date now) {
        int result = 0;
        try {
            result = Integer.parseInt(new SimpleDateFormat(formatterOnlyMinute).format(now));
        } catch (Exception e) {
        }
        return result;
    }

    public static int dayInWeek(Date now) {
        if (now == null) return 0;
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static Date getDateLater(Date now, int n) {
        return new Date(now.getTime() + oneDay * n);
    }

    public static int getOff(Date begin, Date end) {
        return (int) ((getDayBegin(end).getTime() - getDayBegin(begin).getTime()) / oneDay);
    }

    public static int getOff(String begin, String end) {
        return getOff(parseDay(begin), parseDay(end));
    }

    public static String changeStrToStr(String dateStr, SimpleDateFormat from, SimpleDateFormat to) {
        String result = null;
        try {
            Date date = from.parse(dateStr);
            result = to.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static Date plusDays(Date date,int days){
        Calendar   calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,days);
        return calendar.getTime();   
    }
}