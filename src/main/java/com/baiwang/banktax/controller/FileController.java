/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.services.iface.IAttachService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.StringUtils;

/**
 * @ClassName: FileController
 * @Description: 文件操作
 * @author ldm
 * @date 2015年9月2日 下午4:56:28
 */
@Controller
@RequestMapping("/users/file")
public class FileController {
	private static final Log logger = LogFactory.getLog(FileController.class);
	@Resource
	private IAttachService attachService;


	/**
	 * @author ldm
	 * @Description: 根据附件ID查看图片
	 * @param @param
	 *            id
	 * @param @param
	 *            request
	 * @return String
	 * @throws @date
	 *             2015年9月9日 下午2:36:36
	 */
	@RequestMapping("/showPicById")
	public String showPicByid(String id, HttpServletRequest request) {
		Long uid = getUid(request);
		if(null != id && id.trim().length()>0){
			UserAttacht attach = attachService.selectById(Long.parseLong(id),uid);
			List<UserAttacht> attachList = new ArrayList<UserAttacht>();
			attachList.add(attach);
			request.setAttribute("attachList", attachList);
		}
		return "/order/showPics";
	}

	/**
	 * @author ldm
	 * @Description: 根据贷款ID和附件type查看图片
	 * @param @param
	 *            id
	 * @param @param
	 *            request
	 * @return String
	 * @throws @date
	 *             2015年9月9日 下午2:36:36
	 */
	@RequestMapping("/showPicByIdType")
	public String showPicByIdType(String id, String attType, HttpServletRequest request) {
		Long uid = getUid(request);
		if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(attType)) {
			List<UserAttacht> attachList = attachService.selectByIdType(Long.parseLong(id), Byte.parseByte(attType),uid);
			request.setAttribute("attachList", attachList);
		}
		return "/order/showPics";
	}

	/***
	 * @author ldm
	 * @Description: 根据附件ID下载文档
	 * @param @param
	 *            id
	 * @param @param
	 *            request
	 * @param @param
	 *            response
	 * @return String
	 * @throws @date
	 *             2015年9月9日 下午2:37:18
	 */
	@RequestMapping("/download")
	public String download(String id, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		// response.setHeader("Content-Disposition", "attachment;fileName="+
		// fileName);
		UserAttacht attach = attachService.selectByPrimaryKey(Long.parseLong(id));
		if (null == attach) {
			logger.error("下载的文档不存在!");
			return null;
		}
		String filename = null;
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				filename = URLEncoder.encode(attach.getAttachNote(), "UTF-8");
			} else {
				filename = new String(attach.getAttachNote().getBytes("UTF-8"), "ISO8859-1");
			}
			response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

			inputStream = new FileInputStream(new File(attach.getAttachment()));

			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

		} catch (FileNotFoundException e) {
			logger.error("下载失败！文件不存在" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("下载失败！" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		// 返回值要注意，要不然就出现下面这句错误！
		// java+getOutputStream() has already been called for this response
		return null;
	}

	/***
	 * @author ldm
	 * @Description: 根据贷款ID和附件type下载文档
	 * @param @param
	 *            id
	 * @param @param
	 *            request
	 * @param @param
	 *            response
	 * @return String
	 * @throws @date
	 *             2015年9月9日 下午2:37:18
	 */
	@RequestMapping("/downloadByIdType")
	public String downloadByIdType(String id, String attType, HttpServletRequest request,
			HttpServletResponse response) {
		Long uid = getUid(request);
		if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(attType)) {
			List<UserAttacht> attachList = attachService.selectByIdType(Long.parseLong(id), Byte.parseByte(attType),uid);
			if (null != attachList && attachList.size() == 1) {
				download(attachList.get(0).getId().toString(), request, response);
			}
		}
		return null;
	}

	/**
	  * @author ldm
	  * @Description: 获取用户ID公用方法
	  * @param @param request
	  * @param @return  
	  * @return Long  
	  * @throws
	  * @date 2015年9月18日 上午9:52:54
	  */
	private Long getUid(HttpServletRequest request){
		Long uid = 0l;
		User cuser = (User) request.getSession().getAttribute(ConfigUtil.getLoginedUserStr());
		if (null != cuser) {
			uid = cuser.getId();
		}
		return uid;
	}
}
