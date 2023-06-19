package main;


import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        //System.out.println("hello world");
        List<String> list = Arrays.asList("A、设备有故障，影响正常使用（0~5分）", "B、出现小故障，但及时解决不影响使用（6~8分）",
                "C、正常使用，没有任何问题（9~10分）");
        System.out.println(getStr(list));
    }

    public static String getStr(List<String> list){
        StringBuilder sb = new StringBuilder();
        for (String s : list){
            sb.append(s).append("##");
        }
        if(sb.length() > 0){
            return sb.substring(0,sb.length()-2);
        }
        return sb.toString();

    }
}
