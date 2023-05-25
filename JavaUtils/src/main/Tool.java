package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static Date test(Date date, int amount){
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        // getInstance() returns a new object, so this method is thread safe.
        final Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
    }


    public static void main(String[] args) {
        String text = "-3.4免1.5个月租金2 4df455";
        System.out.println(text.replaceAll("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])", "%s"));
        System.out.println(test(new Date(), 2).getTime());
    }
}
