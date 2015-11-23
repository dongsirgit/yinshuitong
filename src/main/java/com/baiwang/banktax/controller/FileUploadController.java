package com.baiwang.banktax.controller;

import java.io.File;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Plupload;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.beans.UserAttachtBatch;
import com.baiwang.banktax.services.iface.IAttachBatchService;
import com.baiwang.banktax.services.iface.IAttachService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.Constant;
import com.baiwang.banktax.utils.StringUtils;
import com.baiwang.banktax.utils.file.FileUtil;
import com.baiwang.banktax.utils.file.PluploadUtil;
import com.baiwang.banktax.utils.file.ZipUtil;

/**
 * @ClassName: FileUploadController
 * @Description: 文件上传
 * @author ldm
 * @date 2015年8月28日 上午11:31:00
 */
@Controller
@RequestMapping("/users/fileUpload")
public class FileUploadController {

	public static final String tempFileDir = ConfigUtil.get("upload.tempDir");
	private static final Log logger = LogFactory.getLog(FileUploadController.class);
	private static final String upLoad_path = Constant.JAVA_TEMP_DIR + Constant.FILE_SEPARAROR + tempFileDir;
	@Resource
	private IAttachService attachService;
	@Resource
	private IAttachBatchService attachBatchService;

	/**
	 * @author ldm
	 * @Description: 跳转上传页面
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年9月1日 下午2:15:10
	 */
	@RequestMapping("/toUpload")
	public String toUpload() {
		return "upload/upload";
	}

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
	 *             2015年9月1日 下午4:43:16
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(Plupload plupload, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ------------------------测试用：上传开始时间
		long startMili = System.currentTimeMillis();
		Cuser cuser = null;
		Puser puser = null;
		String typeUser = (String) request.getSession().getAttribute("typeUser");
		Long uid = (long) 0;
		String userName = "";
		if ("0".equals(typeUser)) {
			cuser = (Cuser) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				uid = cuser.getId();
				userName = cuser.getUserName();
			}
		} else if ("1".equals(typeUser)) {
			puser = (Puser) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
			if (null != puser) {
				uid = puser.getId();
				userName = puser.getUserName();
			}
		}

		plupload.setRequest(request);
		String zipFlag = request.getParameter("zipFlag");
		try {
			if ("isZip".equals(zipFlag)) {
				File dir4zipFile = new File(upLoad_path);
				// 上传文件 fileName4uz:上传自动生成的新文件名,为解压操作提供文件名
				String fileName4uz = PluploadUtil.upload(uid, plupload, dir4zipFile);
				// 判断文件是否上传成功（被分成块的文件是否全部上传完成）
				if (PluploadUtil.isUploadFinish(plupload)) {
					// ----------------------------测试用：上传结束时间
					long endMili = System.currentTimeMillis();
					logger.info("\n---上传文件：" + plupload.getName() + "\n---存储位置：" + dir4zipFile + "\n---上传用时:"
							+ (endMili - startMili) + "微秒");

					String srcFilePath = dir4zipFile + Constant.FILE_SEPARAROR + fileName4uz;
					List<String[]> paths = ZipUtil.unZipFiles(srcFilePath, FileUtil.getUpDirPath()[0],
							FileUtil.getUpDirPath()[1], true, uid);
					if (null == paths) {
						response.setStatus(500);
						response.setCharacterEncoding("utf-8");
						PrintWriter out = response.getWriter();
						out.print("failToUnZip");
						out.flush();
						out.close();
						return ;
					}

					// 写入附件批次表
					UserAttachtBatch uab = new UserAttachtBatch();
					uab.setBatchName(plupload.getName());
					uab.setCreatedtime(new Date());
					uab.setUserid(uid);
					String applyId = request.getParameter("applyId");
					String upfiletype = request.getParameter("upfiletype");
					uab.setApplyId(StringUtils.s2l(applyId));
					uab.setBatchType(StringUtils.s2byte(upfiletype));
					
					UserAttachtBatch re = attachBatchService.selectByCondition(uab);

					if (null != re) {
						List<UserAttacht> att4del = attachService.selectByBatchId(re.getId(),uid);
						attachBatchService.deleteByPrimaryKey(re.getId());
						for (UserAttacht ua : att4del) {
							FileUtil.delFile(ua.getAttachment());
							logger.info("删除文件：" + ua.getAttachment());
						}
					}
					attachBatchService.insert(uab);
					// 写入附件表
					for (String[] temp : paths) {
						UserAttacht ua = new UserAttacht();
						ua.setUserid(uid);
						ua.setCreatedtime(new Date());
						ua.setAttachment(temp[0]);// 绝对路径
						ua.setAttachurl(temp[1]);// 相对路径
						ua.setBatchId(uab.getId());
						ua.setApplyId(StringUtils.s2l(applyId));
						ua.setAttachType(StringUtils.s2byte(upfiletype));
						attachService.insert(ua);
					}

					logger.info("解压源文件路径：" + srcFilePath);
					logger.info("解压目标路径：" + FileUtil.getUpDirPath()[0]);
				}
			} else if("docf".equals(zipFlag)) {// 单个文件上传（非zip文件）
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
				
				UserAttacht re1 = attachService.selectByCondition(uaSingle);

				if (null != re1) {
					attachService.deleteByPrimaryKey(re1.getId());
					FileUtil.delFile(re1.getAttachment());
					logger.info("删除文件：" + re1.getAttachment());
				}
				attachService.insert(uaSingle);
				logger.info("操作用户：" + userName + ",单个文件上传绝对路径：" + dstFilePathAbs);
				logger.info("操作用户：" + userName + ",单个文件上传相对路径：" + dstFilePathRel);
			}

		} catch (SocketTimeoutException e) {
			response.setStatus(500);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print("linkTimeOut");
			out.flush();
			out.close();
			logger.error(e);
			return ;
		} catch (Exception e) {
			logger.error(e);
		}

	}
}
