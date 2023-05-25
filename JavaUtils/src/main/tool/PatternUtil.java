package main.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>正则表达式</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author duancf
 * @version 1.0
 * @date Created in 2023年05月22日 09:55
 * @since 1.0
 */
public class PatternUtil {

    public static Pattern getPattern(String regex) {
        try {
            if (regex == null) {
                throw new Exception("regex为空");
            }
            return Pattern.compile(regex);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 检验字符串是否匹配
     * @param str       需要校验的字符串
     * @param regex     检验的格式
     * @return          true/false
     */
    public static Boolean matches(String str, String regex){
        try{
            Pattern pattern = getPattern(regex);
            return pattern.matcher(str).matches();
        }catch (Exception e){
            System.out.println("【matches】正则判断报错！e=" + e.getMessage());
            return false;
        }
    }

    /**
     * 检验数字是否匹配
     * @param str       需要校验的字符串
     * @param regex     检验的格式
     * @param n         位数
     * @return          true/false
     */
    public static Boolean numberNMatches(String str, String regex, Integer n){
        try{
            String regexStr = String.format(regex, n);
            Pattern pattern = getPattern(regexStr);
            return pattern.matcher(str).matches();
        }catch (Exception e){
            System.out.println("【matches】正则判断报错！e=" + e.getMessage());
            return false;
        }
    }

    /**
     * 获取符合正则的子字符串
     * @param text
     * @param regex
     * @return
     */
    public static List<String> getRegexStrFromString(String text, String regex) {
        Pattern pattern = getPattern(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> matchStrList = new ArrayList<>();
        while (matcher.find()) {
            matchStrList.add(matcher.group());
        }
        return matchStrList;
    }

    class Regex{

        /**
         * 数字正则
         */
        public static final String NUMBER_REGEX = "^[0-9]*$";

        /**
         * N位数字正则
         */
        public static final String NUMBER_N_REGEX = "^\\d{%s}$";

        /**
         * 中文字符正则
         */
        public static final String CHINESE_ZH_CHAR_REGEX = "[\\u4E00-\\u9FA5]";

        /**
         * 整数或小数正则
         */
        public static final String NUMBER_DECIMAL_REGEX = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";

        /**
         * 车牌号正则
         */
        public static final String CAR_PLATE_REGEX = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}";

        /**
         * 微信号正则，6至20位，以字母开头，字母，数字，减号，下划线
         */
        public static final String WE_CHAT_REGEX = "^[a-zA-Z][-_a-zA-Z0-9]{5,19}";

        /**
         * QQ号正则，5至10位
         */
        public static final String QQ_REGEX = "^[1-9][0-9]{4,9}";

        /**
         * 密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
         */
        public static final String PASSWORD_COMPLEX_REGEX = "^.*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*";

        /**
         * 用户名正则，4到16位（字母，数字，下划线，减号）
         */
        public static final String USER_NAME_COMPLEX_REGEX = "^[a-zA-Z0-9_-]{4,16}";

        /**
         * 邮箱正则，必须包含@，后半部分包含.
         */
        public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";


    }

    public static void main(String[] args) {
        System.out.println(matches("我", Regex.CHINESE_ZH_CHAR_REGEX));
        System.out.println(numberNMatches("12", Regex.NUMBER_N_REGEX, 1));
        System.out.println(matches("京B20000", Regex.CAR_PLATE_REGEX));
        System.out.println(matches("B20000", Regex.WE_CHAT_REGEX));
        System.out.println(matches("20000000000", Regex.QQ_REGEX));
        System.out.println(matches("Password123#", Regex.PASSWORD_COMPLEX_REGEX));
        System.out.println(matches("name123", Regex.USER_NAME_COMPLEX_REGEX));
        System.out.println(matches("Password123qq.com", Regex.EMAIL_REGEX));
        System.out.println(matches("1234", Regex.NUMBER_REGEX));
    }
}
