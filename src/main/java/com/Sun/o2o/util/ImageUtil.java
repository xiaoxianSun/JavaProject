package com.Sun.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将CommonsMultipartFile转化成File,因为将文件转换成CommonsMultipartFile比较困难,所以之后改用了file
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile = new File(cFile.getOriginalFilename());
        try {

            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;

    }

    /**
     * CommonsMultipartFile:Spring自带的文件流处理对象
     * 处理缩略图,并返回新生成图片的相对值路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(File thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        logger.debug("BasePath is:" + basePath);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(thumbnail).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(0.8f).toFile(dest);
        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     * @param cFile
     * @return
     */
    private static String getFileExtension(File cFile) {
        String originalFileName = cFile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名,当前年月日小时分钟+五位随机数
     * @return
     */
    private static String getRandomFileName(){
        int randnum = r.nextInt(89999)+10000;
        String nowTimeStr = simpleDateFormat.format(new Date());
        return nowTimeStr + randnum;
    }


    public static void main(String[] args) throws IOException {

        Thumbnails.of(new File("/home/sxx/albania1.jpg"))
                .size(200,200).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(0.8f).toFile("/home/sxx/newalbania1.jpg");
    }
}
