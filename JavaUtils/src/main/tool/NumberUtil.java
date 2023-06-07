package main.tool;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>数字相关工具类</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author duancf
 * @version 1.0
 * @date Created in 2023年06月02日 17:59
 * @since 1.0
 */
public class NumberUtil {

    /**
     * 获取不固定参数数量最大值
     * @param arr
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T maxValue(T ... arr){
        if(EmptyUtil.isEmpty(arr)){
            return null;
        }
        return Arrays.stream(arr).max((t1, t2) -> t1.compareTo(t2)).orElse(null);
    }

    /**
     * 获取不固定参数数量最小值
     * @param arr
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T minValue(T ... arr){
        if(EmptyUtil.isEmpty(arr)){
            return null;
        }
        return Arrays.stream(arr).max((t1, t2) -> t2.compareTo(t1)).orElse(null);
    }

    /**
     * 获取集合中最大值
     * @param collection
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T collectionMaxValue(Collection<T> collection){
        if(EmptyUtil.isEmpty(collection)){
            return null;
        }
        return collection.stream().max((t1, t2) -> t1.compareTo(t2)).orElse(null);
    }

    /**
     * 获取集合中最小值
     * @param collection
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T collectionMinValue(Collection<T> collection){
        if(EmptyUtil.isEmpty(collection)){
            return null;
        }
        return collection.stream().max((t1, t2) -> t2.compareTo(t1)).orElse(null);
    }

    /**
     * 集合做加法
     * @param collection
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T collectionAdd(Collection<T> collection){
        try{
            return (T) collection.stream().map(c -> new BigDecimal(c.toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
        }catch (Exception e){
            System.out.println("【collectionAdd】集合做加法异常！e=" + e);
        }
        return null;
    }

    public static void main(String[] args) {
        List<Double> integerList = Arrays.asList(1.113432545, 2.0, 3.1, 4.4, 4.5);
        Set<Double> integerSet = new HashSet<Double>(integerList);
        //System.out.println(maxValue("1", "d","200"));
        //System.out.println(minValue("1", "d","200"));
        //System.out.println(collectionMinValue(integerSet));
        System.out.println(collectionAdd(integerSet));
    }
}
