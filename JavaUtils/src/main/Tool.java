package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author duancf
 * @version 1.0
 * @date Created in 2023年05月19日 11:35
 * @since 1.0
 */
public class Tool {

    public static Date addYear(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(1, years);
        return cal.getTime();
    }
    public static Date formatStringToDate(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;

        try {
            date = sdf.parse(time);
        } catch (ParseException var5) {

        }

        return date;
    }
    public static Date addDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        return cal.getTime();
    }


    public static void main(String[] args) {
        System.out.println(addDate(addYear(formatStringToDate("2023-05-22", "yyyy-MM-dd"), 1), -1));
        System.out.println("124");
    }
}
