/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils.file;

import java.io.File;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.Constant;
import com.baiwang.banktax.utils.DateUtils;

/**
 * @ClassName: FileUtil
 * @Description: 文件处理问题
 * @author lisy
 * @date 2015年8月20日 上午10:36:35
 */
public class FileUtil {
	static final String UPLOAD_SAVEPATH = Constant.WEBROOT_PATH + Constant.FILE_SEPARAROR
			+ ConfigUtil.get("upload.rootdir.name") + Constant.FILE_SEPARAROR;
	static final String UPLOAD_ContextRootPath = "/" + ConfigUtil.get("upload.rootdir.name") + "/";
	static final String UPLOAD_UserFileDir = ConfigUtil.get("upload.usrdir.name") + Constant.FILE_SEPARAROR;
	static final String UPLOAD_AllowFileType[] = ConfigUtil.get("upload.file.allowtype").split(";");

	private static Hashtable<String, Boolean> dirMap = new Hashtable<String, Boolean>();
	private static final Log logger = LogFactory.getLog(FileUtil.class);

	/**
	 * 
	  * @author Yinhua
	  * @Description: 验证需要上传保存的文件格式是否被允许
	  * @param @param sufix 文件扩展名
	  * @param @return  是否被允许
	  * @return boolean  
	  * @throws
	 */
	public static final boolean verifyUploadFileType(String sufix) {
		boolean re = false;
		if (null == sufix || sufix.length() == 0)
			return re;
		for (String type : UPLOAD_AllowFileType) {
			if (type.endsWith(sufix)) {
				re = true;
				break;
			}
		}
		return re;
	}
	/**
	 * 
	  * @author 殷华
	  * @Description: 获取上传证明文件的绝对存储路径与相对路径
	  * @param @return
	  * @return String[] 返回字符串数组中，第一个[0]为绝对路径，第二个[1]为相对路径
	  * @throws
	 */
	public static final String[] getUpDirPath() {
		String dayStr = DateUtils.getDate(DateUtils.YMD);
		String targetDIR = UPLOAD_SAVEPATH + dayStr;
		verifyDir(targetDIR);
		return new String[] { targetDIR + Constant.FILE_SEPARAROR,
				UPLOAD_ContextRootPath + dayStr + "/" };
	}

	/**
	 * 
	  * @author 殷华
	  * @Description: 获取上传用户文件（和用户直接相关）的绝对存储路径与相对路径
	  * @param @return
	  * @return String[] 返回字符串数组中，第一个[0]为绝对路径，第二个[1]为相对路径
	  * @throws
	 */
	public static final String[] getUpUserProfileDirPath() {
		String monthStr = DateUtils.getDate(DateUtils.YM);
		String usrUpPath = UPLOAD_UserFileDir + monthStr;
		String targetDIR = UPLOAD_SAVEPATH + usrUpPath;
		verifyDir(targetDIR);
		return new String[] { targetDIR + Constant.FILE_SEPARAROR,
				UPLOAD_ContextRootPath + usrUpPath + "/" };
	}

/**
 * 	
  * @author Yinhua
  * @Description: 若是ZIP包批量上传证明文件，确定解压后的各个文件名，使用该方法
  * @param @param userid 用户ID
  * @param @param sufix 文件扩展名
  * @param @param sortNumber 序号
  * @param @return  
  * @return String  
  * @throws
 */
	public static final String getUpFileName(long userid, String sufix, int sortNumber) {
		return userid + "_" + System.currentTimeMillis() + "_" + sortNumber + "." + sufix;
	}

	/**
	 * 	
	  * @author Yinhua
	  * @Description: 生成上传文件的新文件名方法
	  * @param @param userid 用户ID
	  * @param @param sufix 文件扩展名
	  * @param @return  
	  * @return String  
	  * @throws
	 */
	public static final String getUpFileName(long userid, String sufix) {
		return userid + "_" + System.currentTimeMillis() + "." + sufix;
	}
	
	/**
	 * 
	  * @author Yinhua
	  * @Description: 验证目录是否存在，不存在便创建
	  * @param @param dirPath  
	  * @return void  
	  * @throws
	 */	
	public static void verifyDir(String dirPath) {
		try {
			if (null == dirMap.get(dirPath)) {
				File f = new File(dirPath);// 获得文件对象
				if (!f.exists())// 如果路径不存在,则创建
					f.mkdirs();
			}
			dirMap.put(dirPath, true);
		} catch (Exception e) {
			logger.error("Fatal：创建目录错误.path=" + dirPath + e.getMessage());
		}
	}
	
	/**
	  * @author ldm
	  * @Description: 文件移动
	  * @param @param srcFile
	  * @param @param destPath
	  * @param @return  
	  * @return boolean  
	  * @throws
	  * @date 2015年9月8日 上午9:46:49
	  */
	public static boolean Move(String srcPath, String destPath,String newFileName) {
		// Destination directory
		verifyDir(destPath);
		File dir = new File(destPath);
		File srcFile = new File(srcPath);
		// Move file to new directory
//		boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));
		boolean success = srcFile.renameTo(new File(dir, newFileName));
		return success;
	}
	
	/**
	  * @author ldm
	  * @Description: 获取文件扩展名
	  * @param @param file
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年9月8日 上午10:00:46
	  */
	public static String getFileExtension(String fileName) {
//		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}
	/**
	 * @author ldm
	 * @Description: 删除文件
	 * @param @param dir-包含文件名的绝对路径
	 * @param @return  
	 * @return String  
	 * @throws
	 * @date 2015年9月8日 上午10:00:46
	 */
	
	public static void delFile(String dir){
		File file = new File(dir);
		file.delete();
	}
}
