/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.beans.UserAttachtBatch;
import com.baiwang.banktax.services.iface.IApplyLoanService;
import com.baiwang.banktax.services.iface.IAttachBatchService;
import com.baiwang.banktax.services.iface.IAttachService;
import com.baiwang.banktax.services.iface.ILoanListService;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;

/**
 * @ClassName: LoanListController
 * @Description: 前台贷款申请列表
 * @author ldm
 * @date 2015年8月26日 下午2:56:42
 */
@Controller
@RequestMapping("/users/loanList")
public class LoanListController {

	@Resource
	private ILoanListService service;
	@Resource
	private IApplyLoanService applyLoanService;
	@Resource
	private IUserService userService;
	@Resource
	private IAttachService attachService;
	@Resource
	private IAttachBatchService attachBatchService;

	private static final Log logger = LogFactory.getLog(LoanListController.class);

	/**
	 * @author ldm
	 * @Description: 前台贷款列表页（进入个人中心的初始页）
	 * @param @param
	 *            session
	 * @param @param
	 *            map
	 * @return String
	 * @throws @date
	 *             2015年8月26日 下午2:51:45
	 */
	@RequestMapping("/initpage")
	public String initpage(HttpSession session, Map<String, Object> map) {
		String typeUser = (String) session.getAttribute("typeUser");
		Long uid = (long) 0;
		if ("0".equals(typeUser)) {
			Cuser cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				uid = cuser.getId();
			}
		} else if ("1".equals(typeUser)) {
			Puser puser = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != puser) {
				uid = puser.getId();
			}
		} else {
			logger.info("用户类型异常，非企业用户，非个人用户!");
		}

		List<ApplyLoan> list = service.queryLoanList(uid);
		map.put("list", list);

		return "order/loanApplyList";
	}

	/**
	 * @author ldm
	 * @Description: 贷款列表->点击贷款金额->预览贷款信息
	 * @param @param
	 *            id
	 * @param @param
	 *            map
	 * @param @param
	 *            session
	 * @return String
	 * @throws @date
	 *             2015年8月26日 下午2:51:55
	 */
	@RequestMapping("showInfo")
	public String showApplyInfo(Long id, Map<String, Object> map, HttpSession session, HttpServletRequest request) {

		String typeUser = (String) session.getAttribute("typeUser");

		Long uid = (long) 0;
		if ("0".equals(typeUser)) {// 企业用户
			Cuser cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				uid = cuser.getId();
				if (id != null && id != 0) {
					ApplyLoan applyLoan = applyLoanService.queryApplyLoan4show(id, uid);
					map.put("applyLoan", applyLoan);
					map.put("cuser", cuser);
					logger.info("用户名：" + cuser.getUserName() + "--操作：查看贷款信息详情--查询的贷款编号：" + id);
					if(null!=applyLoan){
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);
						
						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						Map<String, UserAttachtBatch> map4show = new HashMap<String, UserAttachtBatch>();
						if(null!=batchList){
							for(UserAttachtBatch uabtemp:batchList){
								map4show.put(uabtemp.getBatchType().toString(), uabtemp);
							}
						}
						request.setAttribute("attachMap", map4show);
						request.setAttribute("applyLoan", applyLoan);
					}
				}
			} else {
				logger.info("用户未登陆!");
			}

		} else if ("1".equals(typeUser)) {// 个人用户
			Puser puser = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != puser) {
				uid = puser.getId();
				if (id != null && id != 0) {
					ApplyLoan applyLoan = applyLoanService.queryApplyLoan4show(id, uid);
					map.put("applyLoan", applyLoan);
					map.put("puser", puser);
					logger.info("用户名：" + puser.getUserName() + "--操作：查看贷款信息详情--查询的贷款编号：" + id);
					
					if(null!=applyLoan){
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);
						
						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						request.setAttribute("batchList", batchList);
						request.setAttribute("applyLoan", applyLoan);
					}
				}
			} else {
				logger.info("用户未登陆!");
			}
		} else {
			logger.info("用户类型异常，非企业用户，非个人用户!");
		}

		return "order/loanApplyInfo";
	}

	/**
	 * @author ldm
	 * @Description: 贷款列表->点击贷款金额->修改贷款信息
	 * @param @param
	 *            id
	 * @param @param
	 *            map
	 * @param @param
	 *            session
	 * @return String
	 * @throws @date
	 *             2015年8月26日 下午2:51:55
	 */
	@RequestMapping("reviseInfo")
	public String reviweApplyInfo(Long id, Map<String, Object> map, HttpSession session, HttpServletRequest request) {
		String typeUser = (String) session.getAttribute("typeUser");

		Long uid = (long) 0;
		if ("0".equals(typeUser)) {// 企业用户
			Cuser cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				uid = cuser.getId();
				if (id != null && id != 0) {
					ApplyLoan applyLoan = applyLoanService.queryApplyLoan4show(id, uid);
					map.put("applyLoan", applyLoan);
					map.put("cuser", cuser);
					logger.info("用户名：" + cuser.getUserName() + "--操作：查看贷款信息详情--查询的贷款编号：" + id);
					
					if(null!=applyLoan){
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);
						
						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						Map<String, UserAttachtBatch> map4show = new HashMap<String, UserAttachtBatch>();
						if(null!=batchList){
							for(UserAttachtBatch uabtemp:batchList){
								map4show.put(uabtemp.getBatchType().toString(), uabtemp);
							}
						}
						request.setAttribute("attachMap", map4show);
						request.setAttribute("applyLoan", applyLoan);
					}
				}
			} else {
				logger.info("用户未登陆!");
			}
		} else if ("1".equals(typeUser)) {// 个人用户
			Puser puser = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != puser) {
				uid = puser.getId();
				if (id != null && id != 0) {
					ApplyLoan applyLoan = applyLoanService.queryApplyLoan4show(id, uid);
					map.put("applyLoan", applyLoan);
					logger.info("用户名：" + puser.getUserName() + "--操作：查看贷款信息详情--查询的贷款编号：" + id);
					
					if(null!=applyLoan){
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);
						
						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						request.setAttribute("batchList", batchList);
						request.setAttribute("applyLoan", applyLoan);
					}
				}
			} else {
				logger.info("用户未登陆!");
			}
		}
		return "order/loan_revise";

	}
}
