package main.tool;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>时间工具类</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author duancf
 * @version 1.0
 * @date Created in 2023年05月31日 14:18
 * @since 1.0
 */
public class DateUtil {

    /**
     * Date转换为字符串
     * @param pattern   格式类型
     * @param date      日期
     * @return          字符串
     */
    public static String formatDate(Date date, String pattern){
        String dateStr = null;
        try {
            dateStr = getDateFormat(pattern).format(date);
        }catch (Exception e){

        }
        return dateStr;
    }

    /**
     * LocalDateTime转换为字符串
     * @param pattern   格式类型
     * @param time      日期
     * @return          字符串
     */
    public static String formatLocalDateTime(LocalDateTime time, String pattern){
        String timeStr = null;
        try {
            timeStr = getLocalDateTimeFormat(pattern).format(time);
        }catch (Exception e){

        }
        return timeStr;
    }

    /**
     * 时间格式转换 LocalDateTime -> Date
     */
    public static Date localDateTimeToDate(LocalDateTime time){
        Date date = null;
        if(time != null) {
            try {
                date = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
            }catch (Exception e){
                System.out.println("【localDateTimeToDate】转换错误。e=" + e);
            }
        }
        return date;
    }

    /**
     * 增加years年
     * @param date      日期
     * @param years     年数
     * @return          日期
     */
    public static Date addYear(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * 增加month月
     * @param date      日期
     * @param month     月数
     * @return          日期
     */
    public static Date addMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    /**
     * 增加day天
     * @param date      日期
     * @param day       天数
     * @return          日期
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 增加minutes分钟
     * @param date      日期
     * @param minutes   分钟
     * @return          日期
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 得到上年年份字符串 格式（yyyy）
     */
    public static String getLastYear() {
        Date years = addYear(new Date(), -1);
        return formatDate(years, DateStyle.YYYY.value);
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), DateStyle.MM.value);
    }

    /**
     * 得到上月月份字符串 格式（MM）
     */
    public static String getLastMonth() {
        Date months = addMonth(new Date(), -1);
        return formatDate(months, DateStyle.MM.value);
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), DateStyle.DD.value);
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), DateStyle.WEEK.value);
    }

    /**
     * 获取指定日期的星期
     */
    public static String getDateWeek(Date date) {
        return formatDate(date, DateStyle.WEEK.value);
    }

    /**
     * 获取两个日期之间的天数
     * param before
     * param after
     * return
     */
    public static Long getDaysOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取两个日期之间的小时
     * param before
     * param after
     * return
     */
    public static Long getHoursOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60);
    }

    /**
     * 指定具体年
     */
    public static Date setYear(Date date,int year){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 指定具体月
     * 超过最大月数，则向后延
     */
    public static Date setMonth(Date date,int month){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, month-1);
        return cal.getTime();
    }

    /**
     * 指定具体天
     * 超过最大天数，则向后延
     */
    public static Date setDay(Date date,int day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 指定具体小时
     * 超过最大小时，则向后延
     */
    public static Date setHours(Date date,int hours){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 指定具体分钟
     * 超过最大分钟，则向后延
     */
    public static Date setMinutes(Date date,int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 指定具体秒
     * 超过最大秒，则向后延
     */
    public static Date setSecond(Date date,int second){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 指定具体毫秒
     * 超过最大毫秒，则向后延
     */
    public static Date setMillSecond(Date date,int milliSecond){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, milliSecond);
        return cal.getTime();
    }

    private static SimpleDateFormat getDateFormat(String pattern) {
        if (pattern == null) {
            return new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS_DOT.value);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat;
    }

    private static DateTimeFormatter getLocalDateTimeFormat(String pattern){
        if (pattern == null) {
            return DateTimeFormatter.ofPattern(DateStyle.YYYY_MM_DD_HH_MM_SS_DOT.value);
        }
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern(pattern);
        return simpleDateFormat;
    }

    public enum DateStyle {
        YYYY("yyyy"),
        YYYY_MM("yyyy-MM"),
        YYYY_MM_EN("yyyy/MM"),
        YYYY_MM_CN("yyyy年MM月"),
        YYYY_MM_DD_CN("yyyy年MM月dd日"),
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYYMMDD("yyyyMMdd"),
        YYYY_MM_DD_EN("yyyy/MM/dd"),
        YYYYMMDDPOINT("yyyy.MM.dd"),
        YYYY_MM_DD_HH("yyyy-MM-dd HH"),
        YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
        YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),
        YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD_HH_MM_SS_DOT("yyyy.MM.dd HH:mm:ss"),
        YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),
        YYYY_MM_DD_T_HH_MM_SS_Z("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
        YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),
        YYYYMMDDHHMMSS("yyyyMMddHHmmss"),

        MM("MM"),
        MM_DD("MM-dd"),
        MM_DD_EN("MM/dd"),
        MM_DD_CN("MM月dd日"),
        MM_DD_HH_MM("MM-dd HH:mm"),
        MM_DD_HH_MM_EN("MM/dd HH:mm"),
        MM_DD_HH_MM_CN("MM月dd日 HH:mm"),
        MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),
        MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),
        MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"),

        DD("dd"),

        HH_MM("HH:mm"),
        HH_MM_SS("HH:mm:ss"),

        WEEK("E"),
        ;

        private final String value;

        DateStyle(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static Date getCurrentZeroPointDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        //System.out.println("dateToString:" + dateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_CN.value));
        //ystem.out.println("getLastYear:" + getLastYear());
        //System.out.println("getMonth:" + getMonth());
        //System.out.println("getLastMonth:" + getLastMonth());
        //System.out.println("getWeek:" + getWeek());
        //System.out.println("getDaysOfTwoDate:" + getDaysOfTwoDate(addYear(new Date(), -1), new Date()));
        //System.out.println("getHoursOfTwoDate:" + getHoursOfTwoDate(addYear(new Date(), -1), new Date()));
        //System.out.println("addMinutes:" + addMinutes(new Date(), 10));
        //System.out.println("setHours:" + setHours(new Date(), 10));
        //System.out.println("setDay:" + setDay(new Date(), 32));
        //System.out.println("setMonth:" + formatDate(setMonth(new Date(), 7), DateStyle.YYYY_MM_DD_HH_MM_SS_DOT.value));
        //System.out.println("setYear:" + formatDate(setYear(new Date(), 20000), DateStyle.YYYY_MM_DD_HH_MM_SS_DOT.value));
        //System.out.println("setSecond:" + formatDate(setMillSecond(new Date(), 2000), DateStyle.YYYY_MM_DD_T_HH_MM_SS_Z.value));
        System.out.println(formatDate(getCurrentZeroPointDate(), DateStyle.YYYY_MM_DD_HH_MM_SS_DOT.value));
    }
}
