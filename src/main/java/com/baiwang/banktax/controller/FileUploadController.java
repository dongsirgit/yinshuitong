package com.baiwang.banktax.controller;

import java.io.File;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.banktax.beans.Plupload;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.services.iface.IAttachService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.StringUtils;
import com.baiwang.banktax.utils.file.FileUtil;
import com.baiwang.banktax.utils.file.PluploadUtil;

/**
 * @ClassName: FileUploadController
 * @Description: 文件上传
 * @author ldm
 * @date 2015年8月28日 上午11:31:00
 */
@Controller
@RequestMapping("/users/fileUpload")
public class FileUploadController {

	private static final Log logger = LogFactory.getLog(FileUploadController.class);
//	public static final String tempFileDir = ConfigUtil.get("upload.tempDir");
//	private static final String upLoad_path = Constant.JAVA_TEMP_DIR + Constant.FILE_SEPARAROR + tempFileDir;
	@Resource
	private IAttachService attachService;

	/**
	 * @author ldm
	 * @Description: 上传文件
	 * @param @param
	 *            页面传过来的plupload对象(封装上传的文件信息)
	 * @param @param
	 *            request
	 * @param @param
	 *            response
	 * @param @return
	 * @return String
	 * @throws Exception
	 * @throws @date
	 *             
	 */
	@RequestMapping(value = "/upload.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(Plupload plupload, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User)request.getSession().getAttribute(ConfigUtil.getLoginedUserStr());
		Long uid = 0l;
		String account = null;
		if(null != user){
			uid = user.getId();
			account = user.getMobilePhone();
		}
		Map<String, Object> map = new HashMap<>();
		try {
			plupload.setRequest(request);
			String name4save = FileUtil.getUpFileName(uid, FileUtil.getFileExtension(plupload.getName()));
			String dstFilePathAbs = FileUtil.getUpDirPath()[0] + name4save;
			String dstFilePathRel = FileUtil.getUpDirPath()[1] + name4save;
			File dir4SingleFile = new File(FileUtil.getUpDirPath()[0]);
			PluploadUtil.upload(plupload, dir4SingleFile, name4save);
			UserAttacht uaSingle = new UserAttacht();
			uaSingle.setUserid(uid);
			uaSingle.setCreatedtime(new Date());
			uaSingle.setAttachNote(plupload.getName());
			uaSingle.setAttachment(dstFilePathAbs);
			uaSingle.setAttachurl(dstFilePathRel);
			String applyId = request.getParameter("applyId");
			String upfiletype = request.getParameter("upfiletype");
			uaSingle.setApplyId(StringUtils.s2l(applyId));
			uaSingle.setAttachType(StringUtils.s2byte(upfiletype));
			
			UserAttacht ua = attachService.selectByCondition(uaSingle);

			if (null != ua) {
				attachService.deleteByPrimaryKey(ua.getId());
				FileUtil.delFile(ua.getAttachment());
				logger.info("删除文件：" + ua.getAttachment());
			}
			attachService.insert(uaSingle);
			logger.info("操作用户：" + account + ",文件上传绝对路径：" + dstFilePathAbs);
			logger.info("操作用户：" + account + ",文件上传相对路径：" + dstFilePathRel);
			
			if(null != uaSingle && uaSingle.getId()>0){
				map.put("atId", uaSingle.getId());
			}
			return map;

		} catch (SocketTimeoutException e) {
			response.setStatus(500);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print("linkTimeOut");
			out.flush();
			out.close();
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
