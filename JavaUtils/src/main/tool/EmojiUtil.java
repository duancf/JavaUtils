package main.tool;

import java.util.regex.Matcher;
/**
 * @author duancf
 * @version 1.0
 * @date created in 2023年08月23日 11:13
 * @since 1.0
 */
public class EmojiUtil {

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source        原字符串
     * @param replaceStr    替换的字符
     * @return              新字符串
     */
    public static String replaceEmojiStr(String source, String replaceStr) {
        if (StringUtil.notBlank(source)) {

            Matcher emojiMatcher = PatternUtil.matcher(source, PatternUtil.Regex.EMOJI_REGEX);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("");
                return source;
            }
            return source;
        }
        return source;
    }

    /**
     * 判断是否含有emoji表情符
     *
     * @param source    原参数
     * @return          true/false
     */
    public static boolean checkIsEmoji(String source) {
        //最后再使用自定义正则来判断
        if (StringUtil.notBlank(source)) {
            Matcher emojiMatcher = PatternUtil.matcher(source, PatternUtil.Regex.EMOJI_REGEX);
            if (emojiMatcher.find()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "测试😒😒😒😊😊😂测试";
        System.out.println(replaceEmojiStr(str, null));
        System.out.println(checkIsEmoji(str));
    }
}
