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

    /**
     * 集合做乘法
     * @param collection
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T collectionMultiply(Collection<T> collection){
        try{
            return (T) collection.stream().map(c -> new BigDecimal(c.toString())).reduce(BigDecimal.ZERO, BigDecimal::multiply);
        }catch (Exception e){
            System.out.println("【collectionMultiply】集合做乘法异常！e=" + e);
        }
        return null;
    }

    /**
     * 两数做除法
     * @param dividend  被除数
     * @param divisor   除数
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T collectionDivide(T dividend, T divisor){
        try{
            BigDecimal dividendBig = new BigDecimal(dividend.toString());
            BigDecimal divisorBig = new BigDecimal(divisor.toString());

            return (T) dividendBig.divide(divisorBig);
        }catch (Exception e){
            System.out.println("【collectionDivide】集合做除法异常！e=" + e);
        }
        return null;
    }

    /**
     * 两数做减法
     * @param num1
     * @param num2
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T collectionSubtract(T num1, T num2){
        try{
            BigDecimal num1Big = new BigDecimal(num1.toString());
            BigDecimal num2Big = new BigDecimal(num2.toString());

            return (T) num1Big.subtract(num2Big);
        }catch (Exception e){
            System.out.println("【collectionSubtract】集合做减法法异常！e=" + e);
        }
        return null;
    }


    public static void main(String[] args) {
        List<Double> integerList = Arrays.asList(1.113432545, 2.0, 3.1, 4.4, 4.5);
        Set<Double> integerSet = new HashSet<Double>(integerList);
        //System.out.println(maxValue("1", "d","200"));
        //System.out.println(minValue("1", "d","200"));
        //System.out.println(collectionMinValue(integerSet));
        //System.out.println(collectionAdd(integerSet));
        System.out.println(collectionMultiply(integerSet));
        //System.out.println(collectionDivide(1.01, 2));
        //System.out.println(collectionSubtract(1.01, 2));



    }
}
