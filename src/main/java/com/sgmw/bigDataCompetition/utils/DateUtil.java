package com.sgmw.bigDataCompetition.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 描述:存放一些与时间有关的方法类
 *
 * @outhor WeiTongMing
 * @create 2020-02-11 20:24
 */
public class DateUtil {

    /**
     * 得到几天后的时间
     *
     * @param day 天数
     * @return
     */
    public static Date getDateBefore(int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * simple
     *
     * @param date   date
     * @param format format .eg:yyyy-MM-dd
     * @return
     */
    public static String getDayStringByDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String dateStr(Date date, String formatStr) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static String dateStrYYYYMMDD(Date date) {
        return dateStr(date, "yyyyMMdd");
    }

    public static String dateStrYYYYMM(Date date) {
        return dateStr(date, "yyyyMM");
    }

    public static String dateStrYYYY_MM(Date date) {
        return dateStr(date, "yyyy-MM");
    }

    public static String dateStrYYYY_MM_DD(Date date) {
        return dateStr(date, "yyyy-MM-dd");
    }

    public static String dateStrYYYYMMDDHHMMSS(Date date) {
        return dateStr(date, "yyyyMMddHHmmss");
    }

    public static String dateStrYYYYMMDDHHMMSS2(Date date) {
        return dateStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date dateFormat(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(dateStrYYYYMMDDHHMMSS(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date dateFormat(String dateStr, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date dateYYYYMMDD(Date date) {
        return dateFormat(date, "yyyyMMdd");
    }

    public static Date dateYYYY_MM_DD(Date date) {
        return dateFormat(date, "yyyy-MM-dd");
    }

    public static Date dateYYYY_MM(Date date) {
        return dateFormat(date, "yyyy-MM");
    }

    /**
     * 时间差，不满一天算零天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        if (date1 == null || date2 == null) {
            return 0;
        }
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        //算上当天
        return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L)) + 1;
    }

    /**
     * 获取当天的开始时间
     *
     * @return Date
     */
    public static Date getNowDayStart() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return Date
     */
    public static Date getNowDayEnd() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某一天前后结束时间
     *
     * @param severalDays 几天后
     * @return Date
     */
    public static Date getSeveralDaysEnd(int severalDays) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, severalDays);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某一月前后结束时间
     *
     * @param date   日期
     * @param months 几个月，负数往前，正数往后
     * @return Date
     */
    public static Date getSeveralMonthsEnd(Date date, int months) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某一天前后开始时间
     *
     * @param severalDays 几天前
     * @return Date
     */
    public static Date getSeveralDaysStart(int severalDays) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, severalDays);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某一天的开始时间
     *
     * @param date 时间
     * @return Date
     */
    public static Date getOneDayStart(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某一天的结束时间
     *
     * @param date 时间
     * @return Date
     */
    public static Date getOneDayEnd(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某时间前后时间几分钟
     *
     * @param date 时间
     * @param mins 前后多少f分，正数往后，负数往前
     * @return Date
     */
    public static Date getRallMin(Date date, int mins) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, mins);
        return calendar.getTime();
    }

    /**
     * 获取某一天前后时间
     *
     * @param date 时间
     * @param days 前后多少天，正数往后，负数往前
     * @return Date
     */
    public static Date getRallDate(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 获取某个日期前后几个月的月初时间
     *
     * @param date  时间
     * @param month 正值为往后，负值为往前，0为当前月
     * @return
     */
    public static Date getMonthStart(Date date, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某个日期前后几个月的月末时间
     *
     * @param date  时间
     * @param month 正值为往后，负值为往前，0为当前月
     * @return
     */
    public static Date getMonthEnd(Date date, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}

