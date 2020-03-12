package com.Sun.o2o.util;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    //为什么不使用项目的路径作为根目录?因为在项目运行过程中生成的图片最后会被删除.
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projectdev/image/";
        }else{
            basePath = "/home/sxx/image/";
        }

        basePath = basePath.replace("/",seperator);
        return basePath;
    }


    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/",seperator);
    }
}
