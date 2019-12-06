package com.android.zxb.engine.util.date;

import android.annotation.SuppressLint;

import com.android.zxb.engine.util.helper.Slog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings("ALL")
@SuppressLint("SimpleDateFormat")
public class DateUtils {

    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return calendar;
    }

    /**
     * 获取当前时间
     */
    public static String currentDateTime() {
        return currentDateTime(DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentDateTime() {
        return currentDateTime(DateStyle.YYYY_MM_DD_HH_MM_SS_EN_);
    }

    /**
     * 获取当前时间
     */
    public static Calendar currentDataTime() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar;
    }

    /**
     * 系统当前时间
     * 秒
     */
    public static long systemCurrentDateTime() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间
     *
     * @param dateStyle 格式
     */
    public static String currentDateTime(String dateStyle) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateStyle);
        return formatter.format(new Date());
    }

    /**
     * 获取时间unique时间戳
     */
    public static long getTimeMillis(int year, int month, int day) {
        Calendar calendar = getCalendar();
        calendar.set(year, month - 1, day);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取时间unique时间戳
     */
    public static long getTimeMillis(int year, int month, int day, int hour, int minute) {
        Calendar calendar = getCalendar();
        calendar.set(year, month, day - 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取时间unique时间戳
     */
    public static long getTimeMillis(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = getCalendar();
        calendar.set(year, month - 1, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取时间unique时间戳
     */
    public static long getTimeMillis(String time) {
        Calendar calendar = getCalendar();
        SimpleDateFormat format = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_EN);
        try {
            Date date = format.parse(time);
            calendar.setTime(date);
            return calendar.getTimeInMillis() / 1000;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 返回当前年份
     */
    public static int getYear() {
        Calendar calendar = getCalendar();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回当前月份
     */
    public static int getMonth() {
        Calendar calendar = getCalendar();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回当前日
     */
    public static int getDay() {
        Calendar calendar = getCalendar();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回一周内的天数
     */
    public static int getDayOfWeek() {
        Calendar calendar = getCalendar();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 通过实践获取是时间为当周第几天
     */
    public static int getDayOfWeebFromSecond(long time) {
        Calendar calendar = getCalendar();
        calendar.setTimeInMillis(time * 1000);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    /**
     * 格式化时间
     *
     * @param date  时间
     * @param style 格式
     * @return 格式化结果
     */
    public static String format(Date date, String style) {
        SimpleDateFormat format = new SimpleDateFormat(style);
        return format.format(date);
    }

    /**
     * 今天零点的时间
     */
    public static Date startOfDate(Date date) {
        long time = date.getTime() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        // 今天零点零分零秒的毫秒数
        return new Date(time);
    }

    /**
     * 第一个时间是不是在第二个事件之前
     */
    public static boolean before(Date date, Date now) {
        if (date.before(now)) {
            return true;
        } else {
            long between = date.getTime() - now.getTime();

            if (between > (24 * 3600000)) {
                return false;
            }
            return true;
        }
    }

    /**
     * 今天23：59：59的时间
     */
    public static Date endOfDate(Date date) {
        long time = date.getTime() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset() +
                24 * 60 * 60 * 1000 - 1;
        return new Date(time);
    }

    /**
     * 格式化timestamp格式的时间，以秒为单位
     *
     * @param timestamp 秒
     * @param dateStyle 格式
     * @return 格式化结果
     */
    public static String formatSecondTimestamp(long timestamp, String dateStyle) {
        Calendar c = getCalendar();
        c.setTimeInMillis(timestamp * 1000);
        return format(c.getTime(), dateStyle);
    }

    /**
     * 根据时间戳，格式化日期，当天只返回时间，其他返回日期
     *
     * @param timestamp 秒
     * @return 格式化的时间
     */
    public static String getCurrentTimeIFCurrentDayOrDate(long timestamp) {
        Calendar now = getCalendar();
        Calendar c = getCalendar();
        c.setTimeInMillis(timestamp * 1000);
        if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                && c.get(Calendar.DATE) == now.get(Calendar.DATE)) {
            return format(c.getTime(), DateStyle.HH_MM);
        }
        return format(c.getTime(), DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取yyyy-MM-dd格式日期
     */
    public static String getDateFormatYYYYMMDD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DateStyle.YYYY_MM_DD);
        return format.format(date);
    }

    /**
     * 获取指定格式日期
     */
    public static String getDateFormatByStyle(String style, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(style);
        return format.format(date);
    }


    /**
     * 把一个日期格式的字符串转化为日期
     */
    public static Date parseStringToDate(String dateStr, String style) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(style);
        return format.parse(dateStr);
    }

    /**
     * 将一个时间转换为提示性字符串
     *
     * @param timeStamp 秒值
     */
    public static String convertTimeToFormat(long timeStamp) {
        int current = (int) (System.currentTimeMillis() / 1000 - timeStamp);
        Calendar calendar = getCalendar();
        String today = format(calendar.getTime(), "yyyy-MM-dd");
        String currentYear = format(calendar.getTime(), "yyyy");
        calendar.setTimeInMillis(timeStamp * 1000);
        String publishDay = format(calendar.getTime(), "yyyy-MM-dd");
        String publishYear = format(calendar.getTime(), "yyyy");
        String publishMonth = format(calendar.getTime(), "MM-dd HH:mm");
        if (current < 60) {
            return "刚刚";
        } else if (current < 3600) {
            if (today.equals(publishDay)) {
                return current / 60 + "分钟前";
            } else {
                return publishMonth;
            }
        } else if (current < 86400) {
            if (today.equals(publishDay)) {
                return current / 3600 + "小时前";
            } else {
                return publishMonth;
            }
        } else if (current < 31536000) {
            if (publishYear.equals(currentYear)) {
                return publishMonth;
            } else {
                return publishDay;
            }
        } else {
            return publishDay;
        }
    }

    /**
     * 将一个时间转换为年月
     */
    public static String convertTimeToMonth(long timeStamp) {
        Calendar calendar = getCalendar();
        String thisMonth = format(calendar.getTime(), "yyyy-M");
        calendar.setTimeInMillis(timeStamp * 1000);
        String publishMonth = format(calendar.getTime(), "yyyy-M");
        if (thisMonth.equals(publishMonth)) {
            return "本月";
        } else {
            return publishMonth;
        }
    }

    /**
     * 转换时间到日
     */
    public static String covertTimeToDay(long timeStamp) {
        Calendar calendar = getCalendar();
        calendar.setTimeInMillis(timeStamp * 1000);
        String publishMonth = format(calendar.getTime(), "yyyy/M/d");
        return publishMonth;
    }

    /**
     * 转换时间到HH:mm
     */
    public static String covertTimeToMinute(long timeStamp) {
        Calendar calendar = getCalendar();
        calendar.setTimeInMillis(timeStamp * 1000);
    /*    int hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = (hour + 16) % 24;
        int minute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.HOUR_OF_DAY, hour);*/
        String publishMonth = format(calendar.getTime(), "HH:mm");
        return publishMonth;
    }

    /**
     * 将一个日期转换为提示性时间字符串
     *
     * @param date 日期
     */
    public static String formatDateTime(Date date) {
        String text;
        long dateTime = date.getTime();
        if (isSameDay(dateTime)) {
            Calendar calendar = GregorianCalendar.getInstance();
            if (inOneMinute(dateTime, calendar.getTimeInMillis())) {
                return "刚刚";
            } else if (inOneHour(dateTime, calendar.getTimeInMillis())) {
                return String.format("%d分钟之前", Math.abs(dateTime - calendar.getTimeInMillis()) / 60000);
            } else {
                calendar.setTime(date);
                text = "HH:mm";
//                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
//                if (hourOfDay > 17) {
//                    text = "晚上 hh:mm";
//                } else if (hourOfDay >= 0 && hourOfDay <= 6) {
//                    text = "凌晨 hh:mm";
//                } else if (hourOfDay > 11 && hourOfDay <= 17) {
//                    text = "下午 hh:mm";
//                } else {
//                    text = "上午 hh:mm";
//                }
            }
        } else if (isYesterday(dateTime)) {
            text = "昨天 HH:mm";
        } else if (isSameYear(dateTime)) {
            text = "M-d HH:mm";
        } else {
            text = "yyyy-M-d HH:mm";
        }

        // 注意，如果使用android.text.format.DateFormat这个工具类，在API 17之前它只支持adEhkMmszy
        return new SimpleDateFormat(text, Locale.CHINA).format(date);
    }

    /**
     * 时间间隔是否在1min内
     *
     * @param time1 毫秒
     * @param time2 毫秒
     */
    public static boolean inOneMinute(long time1, long time2) {
        return Math.abs(time1 - time2) < 60000;
    }

    /**
     * 时间间隔是否在1h内
     *
     * @param time1 毫秒
     * @param time2 毫秒
     */
    public static boolean inOneHour(long time1, long time2) {
        return Math.abs(time1 - time2) < 3600000;
    }

    /**
     * 是否是同一天
     *
     * @param time 毫秒
     */
    public static boolean isSameDay(long time) {
        long startTime = floorDay(Calendar.getInstance()).getTimeInMillis();
        long endTime = ceilDay(Calendar.getInstance()).getTimeInMillis();
        return time > startTime && time < endTime;
    }

    /**
     * 是否是同一天
     *
     * @param time 毫秒
     */
    public static boolean isSameDayInSecond(long time) {
        long startTime = floorDay(Calendar.getInstance()).getTimeInMillis() / 1000;
        long endTime = ceilDay(Calendar.getInstance()).getTimeInMillis() / 1000;
        return time > startTime && time < endTime;
    }

    /**
     * 是否是同一天
     *
     * @param time 毫秒
     */
    public static boolean isSameDayInSecond(long time, long time2) {
        Calendar calendar = getCalendar();
        Calendar calendar2 = getCalendar();
        calendar.setTimeInMillis(time * 1000);
        calendar2.setTimeInMillis(time2 * 1000);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        if (year != year2) return false;
        if (month != month2) return false;
        if (day != day2) return false;
        return true;
    }

    /**
     * 是否是同一天
     *
     * @param time 毫秒
     */
    public static boolean isAfterTodaySecond(long time) {
        Calendar calendar = getCalendar();
        int tHour = calendar.get(Calendar.HOUR_OF_DAY);
        int tMinute = calendar.get(Calendar.MINUTE);
        calendar.setTimeInMillis(time * 1000);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (year > getYear()) return true;
        if (month > getMonth()) return true;
        if (day > getDay()) return true;
        return false;
    }

    /**
     * 是否是昨天
     *
     * @param time 毫秒
     * @param day  是否是周围几天,-1表示昨天,0表示今天,1表示明天
     */
    public static boolean isAroundrdayInSecond(long time, int day) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        startCal.add(Calendar.DAY_OF_MONTH, day);
        long startTime = startCal.getTimeInMillis() / 1000;

        Calendar endCal;
        endCal = ceilDay(Calendar.getInstance());
        endCal.add(Calendar.DAY_OF_MONTH, day);
        long endTime = endCal.getTimeInMillis() / 1000;
        return time > startTime && time < endTime;
    }

    /**
     * 是否是今天之前
     *
     * @param time 毫秒
     */
    public static boolean isBeforeNow(long time) {
        return time <= System.currentTimeMillis() / 1000;
    }

    /**
     * 是否是现在之后
     *
     * @param time 毫秒
     */
    public static boolean isAfterNow(long time) {
        return time >= System.currentTimeMillis() / 1000;
    }

    /**
     * 光对比时间是否在今天时间之后
     */
    public static boolean isAfterNowByCompareTime(long time) {
        Calendar calendar = getCalendar();
        int tHour = calendar.get(Calendar.HOUR_OF_DAY);
        int tMinute = calendar.get(Calendar.MINUTE);
        calendar.setTimeInMillis(time * 1000);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Slog.i(DateUtils.class, "tHour:" + tHour);
        Slog.i(DateUtils.class, "tMinute:" + tMinute);
        Slog.i(DateUtils.class, "hour:" + hour);
        Slog.i(DateUtils.class, "minute:" + minute);
        if (tHour < hour) {
            return true;
        } else if (tHour == hour) {
            if (tMinute <= minute) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是昨天
     *
     * @param time 毫秒
     * @param day  是否是周围几天,-1表示昨天,0表示今天,1表示明天
     */
    public static boolean isAroundrday(long time, int day) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        startCal.add(Calendar.DAY_OF_MONTH, day);
        long startTime = startCal.getTimeInMillis();

        Calendar endCal;
        endCal = ceilDay(Calendar.getInstance());
        endCal.add(Calendar.DAY_OF_MONTH, day);
        long endTime = endCal.getTimeInMillis();
        return time > startTime && time < endTime;
    }

    /**
     * 是否是昨天
     *
     * @param time 毫秒
     */
    public static boolean isYesterday(long time) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        startCal.add(Calendar.DAY_OF_MONTH, -1);
        long startTime = startCal.getTimeInMillis();

        Calendar endCal;
        endCal = ceilDay(Calendar.getInstance());
        endCal.add(Calendar.DAY_OF_MONTH, -1);
        long endTime = endCal.getTimeInMillis();
        return time > startTime && time < endTime;
    }

    /**
     * 是否是同一年
     *
     * @param time 毫秒
     */
    public static boolean isSameYear(long time) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        startCal.set(Calendar.MONTH, Calendar.JANUARY);
        startCal.set(Calendar.DAY_OF_MONTH, 1);
        return time >= startCal.getTimeInMillis();
    }

    public static Calendar floorDay(Calendar startCal) {
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        return startCal;
    }

    public static Calendar ceilDay(Calendar endCal) {
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        return endCal;
    }

    /**
     * 转换为时长
     *
     * @param duration 毫秒
     * @return
     */
    public static String timeParse(long duration) {
        String time = "";
        long minute = duration / 60000;
        long seconds = duration % 60000;
        long second = Math.round((float) seconds / 1000);
        if (minute < 10) {
            time += "0";
        }
        time += minute + ":";
        if (second < 10) {
            time += "0";
        }
        time += second;
        return time;
    }
}
