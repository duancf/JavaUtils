package main.tool;

/**
 * 字符串操作
 * @author duancf
 * @version 1.0
 * @date created in 2023年08月09日 10:36
 * @since 1.0
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str   参数字符串
     * @return      true/false
     */
    public static Boolean isEmpty(CharSequence str){
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否为空
     * @param str   参数字符串
     * @return      true/false
     */
    public static Boolean notEmpty(CharSequence str){
        return !isEmpty(str);
    }

    /**
     * 判断是否存在空值（null、""）
     * @param param     参数数组
     * @return          true/false
     */
    public static Boolean anyEmpty(CharSequence... param){
        if(EmptyUtil.isEmpty(param)){
            return false;
        }
        for (CharSequence charSequence : param) {
            if (notEmpty(charSequence)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否是空白（包含空格+null+""）
     * @param str       字符串
     * @return          true/false
     */
    public static boolean isBlank(CharSequence str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 忽略大小写判断两字符串是否匹配
     * @param str1      字符串1
     * @param str2      字符串2
     * @return          true/false
     */
    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        if (str1 != null && str2 != null) {
            if (str1 == str2) {
                return true;
            } else {
                return str1.length() == str2.length() && regionMatches(str1, true, 0, str2, 0, str1.length());
            }
        } else {
            return str1 == str2;
        }
    }

    private static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {
        if (cs instanceof String && substring instanceof String) {
            return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
        } else {
            int index1 = thisStart;
            int index2 = start;
            int tmpLen = length;
            int srcLen = cs.length() - thisStart;
            int otherLen = substring.length() - start;
            if (thisStart >= 0 && start >= 0 && length >= 0) {
                if (srcLen >= length && otherLen >= length) {
                    while(tmpLen-- > 0) {
                        char c1 = cs.charAt(index1++);
                        char c2 = substring.charAt(index2++);
                        if (c1 != c2) {
                            if (!ignoreCase) {
                                return false;
                            }

                            if (Character.toUpperCase(c1) != Character.toUpperCase(c2) && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                                return false;
                            }
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 比较两字符串大小
     * @param str1      字符串1
     * @param str2      字符串2
     * @return          str1>str2：>0  str1=str2：=0  str1<str2：<0
     */
    public static int compare(String str1, String str2) {
        return compare(str1, str2, true);
    }

    private static int compare(String str1, String str2, boolean nullIsLess) {
        if (str1 == str2) {
            return 0;
        } else if (str1 == null) {
            return nullIsLess ? -1 : 1;
        } else if (str2 == null) {
            return nullIsLess ? 1 : -1;
        } else {
            return str1.compareTo(str2);
        }
    }

    /**
     * 忽略大小写比较两字符串大小
     * @param str1      字符串1
     * @param str2      字符串2
     * @return          str1>str2：>0  str1=str2：=0  str1<str2：<0
     */
    public static int compareIgnoreCase(String str1, String str2) {
        return compareIgnoreCase(str1, str2, true);
    }

    private static int compareIgnoreCase(String str1, String str2, boolean nullIsLess) {
        if (str1 == str2) {
            return 0;
        } else if (str1 == null) {
            return nullIsLess ? -1 : 1;
        } else if (str2 == null) {
            return nullIsLess ? 1 : -1;
        } else {
            return str1.compareToIgnoreCase(str2);
        }
    }

    public static void main(String[] args) {
        System.out.println(anyEmpty("1", "aff", "2", "", ""));
        System.out.println(equalsIgnoreCase("add", "ADD"));
        System.out.println(compare("fadd", "dadd"));
        System.out.println(isBlank("  1"));
    }
}
