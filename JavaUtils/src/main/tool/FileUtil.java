package main.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件工具类
 * @author duancf
 * @version 1.0
 * @date created in 2023年08月01日 11:09
 * @since 1.0
 */
public class FileUtil {

    /**
     * 获取某文件夹下下一级所有文件名称（包含文件夹）
     * @param path      文件夹路径
     * @return          文件名称集合
     */
    public static List<String> getFileNameList(String path){
        return getFileNameList(path, null);
    }

    /**
     * 获取某文件下文件夹和文件名称
     * @param path      文件路径
     * @param isFile    null:文件夹+文件，true：文件夹（eg：下载），false：文件（eg：QR.jpg）
     * @return
     */
    public static List<String> getFileNameList(String path, Boolean isFile){
        File file = new File(path);
        if(!file.exists()){
            System.out.println("路径不存在，path=" + path);
            return new ArrayList<>();
        }
        File[] files = file.listFiles();
        if(isFile == null){
            return Arrays.stream(files).map(f -> f.getName()).collect(Collectors.toList());
        }else if(isFile){
            return Arrays.stream(files).filter(f -> f.isDirectory()).map(f -> f.getName()).collect(Collectors.toList());
        }else{
            return Arrays.stream(files).filter(f -> !f.isDirectory()).map(f -> f.getName()).collect(Collectors.toList());
        }
    }

    /**
     * 生成一个临时文件
     * @param path          文件路径
     * @param fileName      文件名称
     * @param fileType      文件类型
     * @return              文件（生成的文件名称=fileName+随机数） eg:temp3697293002015269726.txt
     */
    public static File createTempFile(String path, String fileName, String fileType){
        try{
            File tempFile = File.createTempFile(fileName, fileType, new File(path));
            return tempFile;
        }catch (Exception e){
            System.out.println("创建失败！e=" + e.getMessage());
        }
        return null;
    }

    /**
     * 创建文件
     * @param path          文件路径
     * @param fileName      文件名称
     * @param fileType      文件类型
     * @return              文件
     */
    public static File createFile(String path, String fileName, String fileType){
        if(StringUtil.anyEmpty(path, fileName, fileType)){
            System.out.println("参数错误！");
            return null;
        }
        String filePath = path + fileName + "." + fileType;
        File file = new File(filePath);
        if(file.exists()){
            System.out.println("文件已存在！");
            return null;
        }
        try {
            boolean success = file.createNewFile();
            if(success){
                return file;
            }
        }catch (Exception e){
            System.out.println("创建失败！e=" + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("======================测试文件工具类====================");
        System.out.println(getFileNameList("d://", false));
        File temFile = createTempFile("d://", "duan", ".txt");
        System.out.println(temFile.getAbsolutePath());
    }
}
