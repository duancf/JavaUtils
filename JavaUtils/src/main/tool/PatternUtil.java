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

        /**
         * 手机号码正则
         * 中国电信号段
         *
         * 133、153、173、177、180、181、189、190、191、193、199
         *
         * 中国联通号段
         *
         * 130、131、132、145、155、156、166、167、171、175、176、185、186、196
         *
         * 中国移动号段
         *
         * 134(0-8)、135、136、137、138、139、1440、147、148、150、151、152、157、158、159、172、178、182、183、184、187、188、195、197、198
         *
         * 中国广电号段
         *
         * 192
         *
         * 其他号段
         *
         * 14号段部分为上网卡专属号段：中国联通145，中国移动147，中国电信149
         *
         * 虚拟运营商：
         *
         * 电信：1700、1701、1702、162
         * 移动：1703、1705、1706、165
         * 联通：1704、1707、1708、1709、171、167
         * 卫星通信：1349、174
         * 物联网：140、141、144、146、148
         */
        public static final String PHONE_REGEX = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

        /**
         * 身份证号正则 15|18
         *
         * 地址码长6位
         * 以数字1-9开头
         * 后5位为0-9的数字
         *
         *
         * 年份码长4位
         * 以数字18，19或20开头
         * 剩余两位为0-9的数字
         *
         *
         * 月份码长2位
         * 第一位数字为0，第二位数字为1-9
         * 或者第一位数字为1，第二位数字为0-2
         *
         *
         * 日期码长2位
         * 第一位数字为0-2，第二位数字为1-9
         * 或者是10，20，30，31
         *
         *
         * 顺序码长3位
         * 顺序码是数字
         *
         *
         * 校验码长1位
         * 可以是数字，字母x或字母X
         */
        public static final String ID_CARD_REGEX = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

        /**
         * 中国邮政编码正则
         * 6位
         */
        public static final String POSTAL_CODE_REGEX = "^[1-9]\\d{5}$";
    }

    public static void main(String[] args) {
//        System.out.println(matches("我", Regex.CHINESE_ZH_CHAR_REGEX));
//        System.out.println(numberNMatches("12", Regex.NUMBER_N_REGEX, 1));
//        System.out.println(matches("京B20000", Regex.CAR_PLATE_REGEX));
//        System.out.println(matches("B20000", Regex.WE_CHAT_REGEX));
//        System.out.println(matches("20000000000", Regex.QQ_REGEX));
//        System.out.println(matches("Password123#", Regex.PASSWORD_COMPLEX_REGEX));
//        System.out.println(matches("name123", Regex.USER_NAME_COMPLEX_REGEX));
//        System.out.println(matches("Password123qq.com", Regex.EMAIL_REGEX));
//        System.out.println(matches("1234", Regex.NUMBER_REGEX));
//        System.out.println("手机号校验：" + matches("12513286813", Regex.PHONE_REGEX));
//        System.out.println("身份证号校验：" + matches("4127261999111111111", Regex.ID_CARD_REGEX));
        System.out.println("邮政编码校验：" + matches("412726", Regex.POSTAL_CODE_REGEX));
    }
}
