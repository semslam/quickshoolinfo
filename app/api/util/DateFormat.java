package api.util;





import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ibrahim Olanrewaju S on 11/05/2017.
 */
public class DateFormat extends DateTime {

    public static String getDateFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    public static String getDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static Date getFormatToDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateFormat.format(new Date()));
        }catch (Exception e){
            e.getMessage();
        }
        return date;
    }

    public static Date getFormatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = null;
        try {
            dates =  dateFormat.parse(dateFormat.format(date));
        }catch (Exception e){
            e.getMessage();
        }
        return dates;
    }

    public static String getDateAndMounthFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy");
        return dateFormat.format(date);
    }

    public static String getDateAndMounthFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy");
        return dateFormat.format(new Date());
    }


    public static Date getFormatDateAndMounth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, MM dd yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateFormat.format(new Date()));
        }catch (Exception e){
            e.getMessage();
        }
        return date;
    }

    public static Date getFormatDateAndMounth(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy");
        Date dates = null;
        try {
            dates =  dateFormat.parse(dateFormat.format(date));
        }catch (Exception e){
            e.getMessage();
        }
        return dates;
    }

    public static String getDateAndTimeFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String getDateAndTimeFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }


    public static Date getFormatDateAndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateFormat.format(new Date()));
        }catch (Exception e){
            e.getMessage();
        }
        return date;
    }

    public static Date getFormatDateAndTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dates = null;
        try {
            dates =  dateFormat.parse(dateFormat.format(date));
        }catch (Exception e){
            e.getMessage();
        }
        return dates;
    }

    public static String getTimeFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static String getTimeFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a");
        return dateFormat.format(date);

    }

    public static Date getFormatTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateFormat.format(new Date()));
        }catch (Exception e){
            e.getMessage();
        }
        return date;
    }

    public static Date getFormatTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date dates = null;
        try {
            dates =  dateFormat.parse(dateFormat.format(date));
        }catch (Exception e){
            e.getMessage();
        }
        return dates;
    }

    public static Calendar parseDate(String date) {
        String dt = date.split("T")[0] + " " + date.split("T")[1];
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = format.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar;
    }

    public static Date timeParser(String time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date d = null;
        try {
            d = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Calendar parseDateOnly(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar;
    }

    public static String getCalendarMonth(int month) {
        String str = "Jan<<>>Feb<<>>Mar<<>>Apr<<>>May<<>>Jun<<>>Jul<<>>Aug<<>>Sep<<>>Oct<<>>Nov<<>>Dec";
        String[] split = str.split("<<>>");
        return split[month];
    }

    public static int getPreviousYear() {
        Calendar prevYear = Calendar.getInstance();
        prevYear.add(Calendar.YEAR, -1);
        return prevYear.get(Calendar.YEAR);
    }

    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static long getDifferenceDays(Date currentDate, Date previousDate) {
        long diff = 0;
        if(currentDate != null && previousDate != null){
            diff = previousDate.getTime() - currentDate.getTime();
        }else{
            return diff;
        }
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}
