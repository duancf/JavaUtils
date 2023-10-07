package main.tool;

import java.math.BigDecimal;

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
     * 判断字符串是否是空白（包含空格+null+""）
     * @param str       字符串
     * @return          true/false
     */
    public static boolean notBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * 去除前后空格
     * @param str   字符串
     * @return      去除前后空格的字符串
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 去除前后空格
     * @param str   字符串
     * @return      去除空格的字符串
     * 注释：（字符串为空，返回null）
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * 去除前后空格
     * @param str   字符串
     * @return      去除空格的字符串
     * 注释：（字符串为null，返回“”）
     */
    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * 截取字符串
     * @param str           字符串
     * @param maxWidth      截取的长度
     * @return              被截取后的字符串
     */
    public static String truncate(String str, int maxWidth) {
        return truncate(str, 0, maxWidth);
    }

    /**
     * 截取字符串
     * @param str       字符串
     * @param offset    开始的位置（0开始）
     * @param maxWidth  截取的长度
     * @return          被截取的字符串
     */
    public static String truncate(String str, int offset, int maxWidth) {
        if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        } else if (maxWidth < 0) {
            throw new IllegalArgumentException("maxWith cannot be negative");
        } else if (str == null) {
            return null;
        } else if (offset > str.length()) {
            return "";
        } else if (str.length() > maxWidth) {
            int ix = offset + maxWidth > str.length() ? str.length() : offset + maxWidth;
            return str.substring(offset, ix);
        } else {
            return str.substring(offset);
        }
    }

    /**
     * 查询子字符串所在位置
     * @param seq           被查字符串
     * @param searchSeq     查找的字符串
     * @return              int
     */
    public static int indexOf(CharSequence seq, CharSequence searchSeq) {
        return seq != null && searchSeq != null ? seq.toString().indexOf(searchSeq.toString(), 0) : -1;
    }

    /**
     * 查询子字符串所在位置
     * @param seq           被查字符串
     * @param searchSeq     查找的字符串
     * @param startPos      开始查询的位置
     * @return              int
     */
    public static int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return seq != null && searchSeq != null ? seq.toString().indexOf(searchSeq.toString(), startPos) : -1;
    }

    /**
     * 查询子字符串最后一个位置
     * @param seq           字符串
     * @param searchSeq     子字符串
     * @return              int
     */
    public static int lastIndexOf(CharSequence seq, CharSequence searchSeq) {
        return seq != null && searchSeq != null ? seq.toString().lastIndexOf(searchSeq.toString(), seq.length()) : -1;
    }

    /**
     * 查询子字符串最后一个位置
     * @param seq           字符串
     * @param searchSeq     子字符串
     * @param startPos      开始位置
     * @return              int
     */
    public static int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return seq != null && searchSeq != null ? seq.toString().lastIndexOf(searchSeq.toString(), startPos) : -1;
    }

    /**
     * 判断是否包含字符串
     * @param seq           字符串
     * @param searchSeq     查询字符串
     * @return              true/flase
     */
    public static boolean contains(CharSequence seq, CharSequence searchSeq) {
        if (seq != null && searchSeq != null) {
            return seq.toString().indexOf(searchSeq.toString(), 0) >= 0;
        } else {
            return false;
        }
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
        System.out.println(trim("  sd f "));
        System.out.println(truncate("sadfsdfg", 3, 4));
        System.out.println(indexOf("sdgdssf", "s"));


    }
}
