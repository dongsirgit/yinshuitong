/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: ImageUtil
 * @Description: 图片的剪切,压缩
 * @author gkm
 * @date 2015年9月15日 下午4:24:44
 */
public class ImageCutUtil {

    private static final Log log = LogFactory.getLog(ImageCutUtil.class);
    
    private static String DEFAULT_THUMB_PREVFIX = "thumb_";
    private static String DEFAULT_CUT_PREVFIX = "cut_";
    
    /**
     * 
      * @author gkm
      * @Description: 根据原图与裁切size截取局部图片
      * @param @param oldImg 源图片路径 E:\TestM\abc.jpg
      * @param @param newImg 新图片文件夹路径  E:\TestM\
      * @param @param x 截取源图片位置 x坐标
      * @param @param y 截取源图片位置 y坐标
      * @param @param width 截取宽度
      * @param @param height 截取高度
      * @param @return  
      * @return String  
      * @throws
      * @date 2015年9月17日 下午2:47:00
     */
    public static String cutImage(String oldImg, String newImg , int x, int y, int width, int height){
    	File file_old = new File(oldImg);
        if(file_old.exists()){
        	
        	java.awt.Rectangle rect = new java.awt.Rectangle(x, y, width, height);
            java.io.FileInputStream fis = null;
            ImageInputStream iis = null;
            OutputStream output = null;
            String newName = "";
            try {
                fis = new FileInputStream(file_old);
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
                String suffix = null;
                // 获取图片后缀
                if(file_old.getName().indexOf(".") > -1) {
                    suffix = file_old.getName().substring(file_old.getName().lastIndexOf(".") + 1);
                }
                // 类型和图片后缀全部小写，然后判断后缀是否合法
                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
                    log.info("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return "Error_suffix";
                }
                
                // 将FileInputStream 转换为ImageInputStream
                iis = ImageIO.createImageInputStream(fis);
                // 根据图片类型获取该种类型的ImageReader
                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis,true);
                ImageReadParam param = reader.getDefaultReadParam();
                param.setSourceRegion(rect);
                BufferedImage bi = reader.read(0, param);
                
                //新图片路径
                File file_new = new File(newImg);
                if(!file_new.exists()){
                	log.info("make new file { '"+newImg+"' }");
                	file_new.mkdirs();
                }	
                String p = file_new.getPath();
                if(!file_new.isDirectory())
                	p = file_new.getParent();
                if(!p.endsWith(File.separator))
                	p = p + File.separator;
                newName = p + DEFAULT_CUT_PREVFIX + "_" + new java.util.Date().getTime() + "_" + file_old.getName();
                output = new FileOutputStream(newName);
                
                ImageIO.write(bi, suffix, output);
            }catch(Exception e){
                e.printStackTrace();
                return "Error";
            }finally{
                try {
                	if(iis != null) iis.close();
                    if(fis != null) fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error_IOException";
                }
            }
            
            return newName;
        }else {
            log.warn("the src image is not exist.");
            return "Error_NotOldFile";
        }
    }
    
    /**
     * 
      * @author gkm
      * @Description: 根据图片路径生成  缩略图
      * @param oldImg 原图片路径
      * @param newImg 产生新图片文件夹路径
      * @param w 宽度
      * @param h 高度
      * @param force 是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
      * @param @return  
      * @return String  
      * @throws
      * @date 2015年9月17日 上午10:11:24
     */
    public static String thumbnailImage(String oldImg, String newImg , int w, int h, boolean force){
    	File file_old = new File(oldImg);
        if(file_old.exists()){
        	OutputStream output = null;
        	String newName = "";
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
                String suffix = null;
                // 获取图片后缀
                if(file_old.getName().indexOf(".") > -1) {
                    suffix = file_old.getName().substring(file_old.getName().lastIndexOf(".") + 1);//jpg
                }
                // 类型和图片后缀全部小写，然后判断后缀是否合法
                if(null == suffix || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return "Error_suffix";
                }
                log.debug("target image's size, width:"+w+", height:"+h+".");
                Image img = ImageIO.read(file_old);
                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                if(!force){
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                            log.debug("change image's height, width:"+w+", height:"+h+".");
                        }
                    }else{
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                            log.debug("change image's width, width:"+w+", height:"+h+".");
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                
                //新图片路径
                File file_new = new File(newImg);
                if(!file_new.exists()){
                	log.info("thumbnailImage make new file { '"+newImg+"' }");
                	file_new.mkdirs();
                }	
                String p = file_new.getPath();
                if(!file_new.isDirectory())
                	p = file_new.getParent();
                if(!p.endsWith(File.separator))
                	p = p + File.separator;
                newName = p + DEFAULT_THUMB_PREVFIX + "_" + new java.util.Date().getTime() + "_" + file_old.getName();
                output = new FileOutputStream(newName);
                
                ImageIO.write(bi, suffix, output);
            }catch(Exception e) {
               log.error("generate thumbnail image failed.",e);
               return "Error";
            }finally{
                try{
                    if(null != output)
                    	output.close();
                }catch(Exception e) {
                	log.error("OutputStream close exception....");
                    e.printStackTrace();
                    return "Error_IOException";
                }
            }
            return newName;
        }else{
            log.warn("the src image is not exist.");
            return "Error_NotOldFile";
        }
    }
    
    
    
    public static void main(String[] args) {
        //String aaa = cutImage("E:/TestMark/72.png","E:/TestMark/", 0, 0, 860, 1024);//图片剪切
    	String aaa = thumbnailImage("E:/TestMark/72.png","E:/TestMark/", 150, 100, false);
        
        System.out.println("return :"+aaa);
    }

}