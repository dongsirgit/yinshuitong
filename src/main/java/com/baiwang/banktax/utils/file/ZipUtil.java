/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baiwang.banktax.utils.Constant;

/**
 * @ClassName: ZipUtil
 * @Description: zip解压工具类
 * @author lisy
 * @date 2015年8月27日 下午2:29:00
 */
public class ZipUtil {
	private static final Log logger = LogFactory.getLog(ZipUtil.class);

	/**
	 * 解压到指定目录
	 * 
	 * @param zipPath 源zip文件路径
	 * @param descDirAbs绝对路径
	 * @param descDirRel相对路径
	 * @author isea533
	 * @throws IOException 
	 */
	public static List<String[]> unZipFiles(String zipPath, String descDirAbs,String descDirRel, boolean includeZipFileName,long uid) throws Exception{
		return unZipFiles(new File(zipPath), descDirAbs,descDirRel, includeZipFileName, uid);
	}

	/**
	  * @author Administrator
	  * @Description: 解压文件到指定目录
	  * @param @param zipFile
	  * @param @param descDirAbs 绝对路径
	  * @param @param descDirRel 相对路径
	  * @param @param includeZipFileName
	  * @param @param uid
	  * @param @return  
	  * @return List<String[]>  
	 * @throws IOException 
	  * @throws
	  * @date 2015年9月9日 下午2:51:13
	  */
	@SuppressWarnings("rawtypes")
	public static List<String[]> unZipFiles(File zipFile, String descDirAbs, String descDirRel,boolean includeZipFileName,long uid ) throws Exception {
		List<String[]> filePaths = new ArrayList<String[]>();//保存解压出的各文件的路径
		FileUtil.verifyDir(descDirAbs);
		ZipFile zip = null;
		try {
			zip = new ZipFile(zipFile,Charset.forName("GBK"));
			int sortNumber=0;
			for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();

				// 判断文件全路径是否为文件夹,如果是,跳过
				if (entry.isDirectory()) {
					continue;
				}
				String zipEntryName= FileUtil.getUpFileName(uid, FileUtil.getFileExtension(entry.getName()), sortNumber);
				String outPathAbs = (descDirAbs + zipEntryName).replaceAll("\\*", Constant.FILE_SEPARAROR);
				String outPathRel = (descDirRel + zipEntryName).replaceAll("\\*", "/");
				// 输出文件路径信息
				logger.debug(outPathAbs);
				//logger.info(outPath);
				InputStream in = null;
				OutputStream out = null;
				try {
					in = zip.getInputStream(entry);
					out = new FileOutputStream(outPathAbs);
					byte[] buf1 = new byte[1024];
					int len;
					while ((len = in.read(buf1)) > 0) {
						out.write(buf1, 0, len);
					}
					sortNumber++;
				} catch (IOException e) {
					throw e;
				} finally {
					try {
						if (null != in) {
							in.close();
						}
						if (null != out) {
							out.close();
						}
					} catch (IOException e) {
						throw e;
					}
				}
				filePaths.add(new String[] { outPathAbs, outPathRel });
			}
		} catch (IOException e1) {
			logger.error("zip文件解压错误！");
			return null;
		} finally {
			try {
				if (null != zip) {
					zip.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		
		logger.debug("******************解压完毕********************");
		return filePaths;
	}

//	public static void main(String[] args) {
//		String zipFilePath = "D:/Firefox_V40.0.0.5697_setup.1439347708.zip";
//		File zipFile = new File(zipFilePath);
//		String path = "d:/ldm";
//		unZipFiles(zipFile, path, true);
//	}
}
