/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;   
import java.awt.Image;   
import java.awt.RenderingHints;   
import java.awt.image.BufferedImage;   
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
  * @ClassName: ImageMarkUtil
  * @Description: 图片加水印(图片/文字)
  * @author gkm
  * @date 2015年9月16日 下午2:20:17
 */
public class ImageMarkUtil{
	
	private static String DEFAULT_MARKIMAGE_PREVFIX = "markImage_";
	private static String DEFAULT_MARKTEXT_PREVFIX = "markText_";
	private static String DEFAULT_MARKICON_PREVFIX = "markIcon_";
  
	private static final Log log = LogFactory.getLog(ImageMarkUtil.class);

	/**
     * 
      * @author gkm
      * @Description: 图片加水印图片
      * @param oldImg 源图片路径
      * @param newImg 加水印图片后图片保存文件夹路径
      * @param markImg 水印图片
      * @param x x坐标
      * @param y y坐标 
      * @return void  
      * @throws
      * @date 2015年9月16日 下午3:06:28
     */
    public final static String markImage(String oldImg, String newImg, String markImg, int x, int y) {
    	File file_old = new File(oldImg);
        if(file_old.exists()){
	    	FileOutputStream out = null;
	    	String newName = "";
	        try {
	        	log.info("######方法markImage,源文件("+oldImg+")加水印图片("+markImg+")形成新文件("+newImg+")开始...");
	            //目标文件
	            Image src_oldImg = ImageIO.read(file_old);
	            int wideth_oldImg = src_oldImg.getWidth(null);
	            int height_oldImg = src_oldImg.getHeight(null);
	            
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
	            ImageInputStream iis = ImageIO.createImageInputStream(new FileInputStream(file_old));
	            ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis,true);
                ImageReadParam param = reader.getDefaultReadParam();
	            BufferedImage image =reader.read(0, param);
//	            BufferedImage image = new BufferedImage(wideth_oldImg, height_oldImg, BufferedImage.TYPE_INT_RGB);
	            
	            Graphics g = image.createGraphics();
	            g.drawImage(src_oldImg, 0, 0, wideth_oldImg, height_oldImg, null);
	 
	            //水印文件
	            File _fileMarkImg = new File(markImg);
	            Image src_MarkImg = ImageIO.read(_fileMarkImg);
	            int wideth_MarkImg = src_MarkImg.getWidth(null);
	            int height_MarkImg = src_MarkImg.getHeight(null);
	            //!设置水印图片加载的位置!
	//            g.drawImage(src_biao, (wideth-wideth_biao)/2,(height-height_biao)/2, wideth_biao, height_biao, null);
	            g.drawImage(src_MarkImg, x, y, wideth_MarkImg, height_MarkImg, null);
	            //水印文件结束
	            g.dispose();
	            
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
                newName = p + DEFAULT_MARKIMAGE_PREVFIX + "_" + new java.util.Date().getTime() + "_" + file_old.getName();
                out = new FileOutputStream(newName);
	            
	            /*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	            encoder.encode(image);*/
                ImageIO.write(image, suffix, out);
	            log.info("######方法markImage,源文件("+oldImg+")加水印图片("+markImg+")形成新文件("+newImg+")完成...");
	            
	            return newName;
	        }catch(Exception e){
	        	log.info("######方法markImage,源文件("+oldImg+")加水印图片("+markImg+")形成新文件("+newImg+")异常...");
	            e.printStackTrace();
	            return "Error";
	        }finally{
	            try{
	                if(null != out)
	                	out.close();
	            }catch(Exception e) {
	            	log.info("######方法markImage,源文件("+oldImg+")加水印图片("+markImg+")形成新文件("+newImg+")输出流关闭异常...");
	                e.printStackTrace();
	                return "Error_IOException";
	            }
	        }
        }else {
            log.warn("the src oldImg is not exist.");
            return "Error_NotOldFile";
        }
    }
    
    /**
     * 
      * @author gkm
      * @Description: 图片加水印文字
      * @param  oldImg 源图片路径
      * @param  newImg 加水印文字后图片保存文件夹路径
      * @param  markText 水印文字
      * @param  textFontName 字体名
      * @param  textStyle 字体样式  0普通,1普通加粗,2斜体,3斜体加粗
      * @param  textColor 字体颜色(如Color.BLACK)
      * @param  textSize 字体大小
      * @param  x x坐标
      * @param  y y坐标
      * @return void  
      * @throws
      * @date 2015年9月16日 下午12:04:51
     */
    public static String markText(String oldImg, String newImg, String markText, String textFontName, 
            int textStyle, Color textColor, int textSize/*, int x,int y*/){
    	File file_old = new File(oldImg);
        if(file_old.exists()){
	    	FileOutputStream out = null;
	    	String newName = "";
	    	try{
	    		log.info("######方法markText,源文件("+oldImg+")加水印文字("+markText+")形成新文件("+newImg+")开始...");
	            Image src = ImageIO.read(file_old);
	            int wideth = src.getWidth(null);
	            int height = src.getHeight(null);
	            
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
	            ImageInputStream iis = ImageIO.createImageInputStream(new FileInputStream(file_old));
	            ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis,true);
                ImageReadParam param = reader.getDefaultReadParam();
	            BufferedImage image =reader.read(0, param);
//	            BufferedImage image = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);
	            
	            Graphics g = image.createGraphics();
	            g.drawImage(src, 0, 0, null);
	             
	            g.setColor(textColor);
	            if(height>600)
	            	textSize = height/30;
	            g.setFont(new Font(textFontName, textStyle, textSize));
	 
	            //!设置水印文字加载的位置!
	//          g.drawString(pressText, wideth - fontSize - x, height - fontSize/2 - y/2);
	//          g.drawString(pressText, x, fontSize+y);
	            //右下角
	            g.drawString(markText, wideth - textSize*markText.length()-5, height - textSize/2);
	            g.dispose();
	            
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
                newName = p + DEFAULT_MARKTEXT_PREVFIX + "_" + new java.util.Date().getTime() + "_" + file_old.getName();
                out = new FileOutputStream(newName);
	            
	            /*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	            encoder.encode(image);*/
                ImageIO.write(image, suffix, out);
	            log.info("######方法markText,源文件("+oldImg+")加水印文字("+markText+")形成新文件("+newImg+")完成...");
	        }catch(Exception e){
	        	log.info("######方法markText,源文件("+oldImg+")加水印文字("+markText+")形成新文件("+newImg+")异常...");
	        	e.printStackTrace();
	        	return "Error";
	        }finally{
	            try{
	                if (null != out)
	                	out.close();
	            }catch(Exception e) {
	            	log.info("######方法markText,源文件("+oldImg+")加水印文字("+markText+")形成新文件("+newImg+")关闭输出流失败...");
	                e.printStackTrace();
	                return "Error_IOException";
	            }
	        }
	    	return newName;
        }else {
            log.warn("the src oldImg is not exist.");
            return "Error_NotOldFile";
        }
    }
	
	/**
     * 
      * @author gkm
      * @Description: 给图片添加水印图片
      * @param oldImg 源图片路径
      * @param newImg  加水印图片后图片文件夹路径
      * @param markImg  水印图片路径
      * @param x x坐标
      * @param y y坐标
      * @return void  
      * @throws
      * @date 2015年9月16日 下午3:57:25
     */
    public static String markImageByIcon(String oldImg, String newImg, String markImg, Integer x, Integer y) {
        return markImageByIcon(oldImg, newImg, markImg, x, y, null);
    }

    /**
     * 
      * @author gkm
      * @Description: 给图片添加水印、可设置水印图片旋转角度
      * @param oldImg 源图片路径
      * @param newImg  加水印图片后图片保存文件夹
      * @param markImg  水印图片路径
      * @param x x坐标
      * @param y y坐标
      * @param degree 水印图片旋转角度 
      * @return void  
      * @throws
      * @date 2015年9月16日 下午3:57:25
     */
    public static String markImageByIcon(String oldImg, String newImg, String markImg, Integer x, Integer y, Integer degree) {
    	File file_old = new File(oldImg);
        if(file_old.exists()){
	        OutputStream out = null;
	        String newName = "";
	        try{
	        	log.info("######方法markImageByIcon,源文件("+oldImg+")加水印图片("+markImg+")开始...");
	            Image src_oldImg = ImageIO.read(file_old);
	            BufferedImage buffImg = new BufferedImage(src_oldImg.getWidth(null),src_oldImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
	
	            // 得到画笔对象
	//          Graphics g = buffImg.createGraphics();
	            Graphics2D g = buffImg.createGraphics();
	            // 设置对线段的锯齿状边缘处理 
	            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	            
	            g.drawImage(src_oldImg/*.getScaledInstance(src_oldImg.getWidth(null), src_oldImg.getHeight(null), Image.SCALE_SMOOTH)*/, 0, 0, null);
	
	            if (null != degree) {
	            	log.info("######方法markImageByIcon,源文件("+oldImg+")加水印图片("+markImg+")旋转角度("+degree+")");
	                // 设置水印旋转
	                g.rotate(Math.toRadians(degree),(double)buffImg.getWidth()/2, (double)buffImg.getHeight()/2);
	            }
	            float alpha = 0.5f; // 透明度0-1之间,如0.5
	            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
	
	            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度 
	            ImageIcon imgIcon = new ImageIcon(markImg);
	            Image img_MarkImg = imgIcon.getImage();
	  
	            //!设置水印图片加载的位置!
//	            g.drawImage(img_MarkImg, src_oldImg.getWidth(null)-img_MarkImg.getWidth(null)-5, src_oldImg.getHeight(null)-img_MarkImg.getHeight(null)-5, null);
	            g.drawImage(img_MarkImg, x, y, null);
	            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
	            g.dispose();
	
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
                newName = p + DEFAULT_MARKICON_PREVFIX + "_" + new java.util.Date().getTime() + "_" + file_old.getName();
                out = new FileOutputStream(newName);
	            
	            // 生成图片
	            ImageIO.write(buffImg, "JPG", out);
	            log.error("######方法markImageByIcon,源文件("+oldImg+")加水印图片("+markImg+")形成新文件("+newName+")完成...");
	        }catch (Exception e) {
	        	log.info("######方法markImageByIcon,源文件("+oldImg+")加水印图片("+markImg+")形成新文件("+newImg+")异常...");
	            e.printStackTrace();
	            return "Error";
	        }finally{
	            try{
	                if(null != out)
	                	out.close();
	            }catch(Exception e) {
	            	log.info("######方法markImageByIcon,源文件("+oldImg+")加水印图片("+markImg+")形成新文件("+newImg+")关闭输出流异常...");
	                e.printStackTrace();
	                return "Error_IOException";
	            }
	        }
	        
	        return newName;
        }else {
            log.warn("the src oldImg is not exist.");
            return "Error_NotOldFile";
        }
    }
    
    
    public static void main(String[] args) {   
        String oldImg = "E:\\TestMark/a.png";
//        String markImg = "E:/TestMark/mark.png";
        String newImg = "E:/TestMark/";
//        String newImg2 = "E:/TestMark/";
        // 给图片添加水印
//      String reaa = ImageMarkUtil.markImageByIcon(oldImg, newImg, markImg,100,200,45);
        // 给图片添加水印,水印旋转-45
//      String reaa = ImageMarkUtil.markImageByIcon(oldImg, newImg2, markImg,0,0, 0);
        
        // 给图片添加图片水印
//        String reaa = markImage(oldImg,newImg,markImg, 200, 200);
        // 给图片添加文字水印
        String reaa = markText(oldImg, newImg,"@高山流水@", "微软雅黑", 1, Color.BLACK, 20);
      
      System.out.println("return:"+reaa);
    }
    
    
    
    
}