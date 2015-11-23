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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.InviteeBean;
import com.baiwang.banktax.beans.InviterAwardBean;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.beans.UserAttachtBatch;
import com.baiwang.banktax.services.iface.IApplyLoanService;
import com.baiwang.banktax.services.iface.IAttachBatchService;
import com.baiwang.banktax.services.iface.IAttachService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.StringUtils;

/**
 * @ClassName: LoanController
 * @Description: 贷款
 * @author gkm
 * @date 2015年8月3日 上午9:44:20
 */
@Controller
@RequestMapping("/users/applyLoan")
public class ApplyLoanController {

	private static final Log logger = LogFactory.getLog(ApplyLoanController.class);

	@Resource
	private IApplyLoanService applyLoanService;
	@Resource
	private IAttachService attachService;
	@Resource
	private IAttachBatchService attachBatchService;

	/**
	 * 
	 * @author gkm
	 * @Description: 用户协议
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年10月19日 下午4:50:34
	 */
	@RequestMapping("/read")
	public String read() {
		return "order/read";
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 提交成功
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年10月19日 下午4:52:58
	 */
	@RequestMapping("/up")
	public String up() {
		return "order/submitSucess";
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 企业申请页1
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年9月6日 下午5:12:42
	 * @version 2.0
	 */
	@RequestMapping("/com1")
	public ModelAndView loanCom(Long id, String type) {
		if (null == id) {
			id = 0l;
		}
		logger.info("贷款 页面2返回1,id为" + id + ",type:" + type + ",跳转下一步页面");
		return new ModelAndView("order/order_company1").addObject("id", id).addObject("type", type);
	}

	/**
	 * 
	 * @author ldm
	 * @Description: 企业经营税务贷-上传证件资料
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年9月6日 下午5:12:42
	 * @version 2.0
	 */
	@RequestMapping("/comUpload")
	public ModelAndView comUpload(Long id, Map<String, Object> map, HttpSession session, HttpServletRequest request) {

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

					if (null != applyLoan) {
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);

						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						Map<String, UserAttachtBatch> map4show = new HashMap<String, UserAttachtBatch>();
						if (null != batchList) {
							for (UserAttachtBatch uabtemp : batchList) {
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
					if (null != applyLoan) {
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);

						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						Map<String, UserAttachtBatch> map4show = new HashMap<String, UserAttachtBatch>();
						if (null != batchList) {
							for (UserAttachtBatch uabtemp : batchList) {
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
		} else {
			logger.info("用户类型异常，非企业用户，非个人用户!");
		}

		logger.info("企业经营税务贷 基本信息保存成功,id为" + id + ",跳转下一步页面");
		return new ModelAndView("order/order_company2").addObject("id", id);
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 企业申请页3
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年9月6日 下午5:12:42
	 * @version 2.0
	 */
	@RequestMapping("/com3")
	public String loanCom3() {
		return "order/order_company3";
	}

	/**
	 * 
	 * @author ldm
	 * @Description: 企业法人贷-上传证件资料
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年9月6日 下午5:12:42
	 * @version 2.0
	 */
	@RequestMapping("/apUpload")
	public ModelAndView apUpload(Long id, Map<String, Object> map, HttpSession session, HttpServletRequest request) {

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
					if (null != applyLoan) {
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);

						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						Map<String, UserAttachtBatch> map4show = new HashMap<String, UserAttachtBatch>();
						if (null != batchList) {
							for (UserAttachtBatch uabtemp : batchList) {
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
					if (null != applyLoan) {
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);

						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						Map<String, UserAttachtBatch> map4show = new HashMap<String, UserAttachtBatch>();
						if (null != batchList) {
							for (UserAttachtBatch uabtemp : batchList) {
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
		} else {
			logger.info("用户类型异常，非企业用户，非个人用户!");
		}

		logger.info("企业法人贷 基本信息保存成功,id为" + id + ",跳转下一步页面");
		return new ModelAndView("order/order_com_ap").addObject("id", id);
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 个人申请页1
	 * @param @return
	 * @return ModelAndView
	 * @throws @date
	 *             2015年9月6日 下午5:12:42
	 * @version 2.0
	 */
	/* @RequestMapping("/per1") */
	public ModelAndView loanPer(Long id, String type) {
		if (null == id) {
			id = 0l;
		}
		logger.info("个人消费贷 页面2返回1,id为" + id + ",type:" + type + ",跳转下一步页面");
		return new ModelAndView("order/order_person1").addObject("id", id).addObject("type", type);
	}

	/**
	 * 
	 * @author ldm
	 * @Description: 个人消费贷-上传资料
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年9月6日 下午5:12:42
	 * @version 2.0
	 */
	/* @RequestMapping("/perUpload") */
	public ModelAndView perUpload(Long id, Map<String, Object> map, HttpSession session, HttpServletRequest request) {
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
					if (null != applyLoan) {
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);

						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						Map<String, UserAttachtBatch> map4show = new HashMap<String, UserAttachtBatch>();
						if (null != batchList) {
							for (UserAttachtBatch uabtemp : batchList) {
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

					if (null != applyLoan) {
						UserAttacht doc = null;
						if (applyLoan.getApplyType() == 2 || applyLoan.getApplyType() == 3) {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("1"));
						} else {
							doc = attachService.selectByType(applyLoan.getId(), Byte.parseByte("3"));
						}
						request.setAttribute("doc", doc);

						List<UserAttachtBatch> batchList = attachBatchService.selectByApplyId(applyLoan.getId());
						Map<String, UserAttachtBatch> map4show = new HashMap<String, UserAttachtBatch>();
						if (null != batchList) {
							for (UserAttachtBatch uabtemp : batchList) {
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
		} else {
			logger.info("用户类型异常，非企业用户，非个人用户!");
		}

		logger.info("个人消费贷 基本信息保存成功,id为" + id + ",跳转下一步页面");
		return new ModelAndView("order/order_person2").addObject("id", id);
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 个人申请页3
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年9月6日 下午5:12:42
	 * @version 2.0
	 */
	/* @RequestMapping("/per3") */
	public String loanPer3() {
		return "order/order_person3";
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 企业贷款 加载页面1
	 * @param @param
	 *            id
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @date
	 *             2015年9月6日 下午5:13:42
	 * @version 2.0
	 */
	@RequestMapping("init_company")
	@ResponseBody
	public Map<String, Object> init_company(Long id, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Cuser user = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
		logger.info("----[Apply Loan By Company]-----init_company------id:" + id);
		if (null != user) {

			Long uid = user.getId();
			logger.info("---[Apply Loan By Company]-----init_company------uid:" + uid);
			ApplyLoan applyLoan = applyLoanService.queryApplyLoan(id, uid);// 基本信息
																			// 贷款背景

			if (null != applyLoan) {
				map.put("status", "1");
				map.put("applyQuota", applyLoan.getApplyQuota());
				map.put("applyTermNum", applyLoan.getApplyTermNum());
				map.put("contactName", applyLoan.getContactName());
				map.put("telephone", applyLoan.getTelephone());
			} else {
				map.put("status", "0");
				logger.info("----[Apply Loan By Company]-----init_company------queryApplyLoan==>null");
			}
		} else {
			logger.info("----[Apply Loan By Company]-----init_company------user==>null");
			map.put("status", "0");
		}

		return map;
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 个人贷款 加载页面1
	 * @param @param
	 *            id
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @date
	 *             2015年9月6日 下午5:13:28
	 * @version 2.0
	 */
	/*
	 * @RequestMapping("init_person")
	 * 
	 * @ResponseBody
	 */
	public Map<String, Object> init_person(Long id, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Puser user = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
		logger.info("----[Apply Loan By Person]-----init_person------id:" + id);
		if (null != user) {

			Long uid = user.getId();
			logger.info("---[Apply Loan By Person]-----init_person------uid:" + uid);
			ApplyLoan applyLoan = applyLoanService.queryApplyLoan(id, uid);// 基本信息
																			// 贷款背景

			if (null != applyLoan) {
				map.put("status", "1");
				map.put("applyQuota", applyLoan.getApplyQuota());
				map.put("applyTermNum", applyLoan.getApplyTermNum());
			} else {
				map.put("status", "0");
			}
		} else {
			map.put("status", "0");
		}

		return map;
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 保存企业贷款申请 下一步
	 * @param @param
	 *            loan
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @date
	 *             2015年9月6日 下午2:37:39
	 * @version 2.0
	 */
	@RequestMapping("/subFirstC")
	@ResponseBody
	public Map<String, Object> submitFirstCompany(ApplyLoan loan, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Cuser user = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
		int result = 0;
		logger.info("----[Apply Loan By Person]-----submitFirstCompany------id:" + loan.getId());
		if (null != user) {
			loan.setUid(user.getId());
			result = applyLoanService.saveLoan(loan);
			logger.info("----[Apply Loan By Person]-----submitFirstCompany------result:" + result);
		}

		map.put("success", result);
		map.put("id", loan.getId());

		return map;
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 保存个人贷款申请2.0
	 * @param @param
	 *            loan
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @date
	 *             2015年9月6日 下午2:37:39
	 * @version 2.0
	 */
	/*
	 * @RequestMapping("/subFirstP")
	 * 
	 * @ResponseBody
	 */
	public Map<String, Object> submitFirstPerson(ApplyLoan loan, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Puser user = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
		int result = 0;
		logger.info("----[Apply Loan By Person]-----submitFirstPerson------id:" + loan.getId());
		if (null != user) {
			loan.setUid(user.getId());
			loan.setContactName(user.getRealname());
			loan.setTelephone(user.getPhonenum());
			result = applyLoanService.saveLoan(loan);
			logger.info("----[Apply Loan By Person]-----submitFirstPerson------result:" + result);
		}

		map.put("success", result);
		map.put("id", loan.getId());

		return map;
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 贷款申请单 提交
	 * @param @param
	 *            id
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @version
	 *             2
	 * @date 2015年10月19日 下午5:19:25
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public Map<String, Object> submit(Long id, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();

		String typeUser = (String) session.getAttribute("typeUser");
		Long uid = (long) 0;
		int result = 0;
		logger.info("---[Apply loan]-----submit------typeUser:" + typeUser);
		if ("0".equals(typeUser)) {
			Cuser cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				uid = cuser.getId();

				map.put("status", "1");
				logger.info("---[Apply loan]-----submit--typeUser:0(company)----用户uid:" + uid);
				if (null != id && id != 0) {
					try {
						result = applyLoanService.submit(id, uid);
					} catch (Exception e) {
						logger.error("---[Apply loan]-----submit----id:" + id + "-----------submit Exception----------"
								+ e.getMessage());
						e.printStackTrace();
					}
				}
			}

		} else if ("1".equals(typeUser)) {
			Puser puser = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != puser) {
				uid = puser.getId();

				map.put("status", "1");
				logger.info("---[Apply loan]-----submit---typeUser:0(person)---用户uid:" + uid);
				if (null != id && id != 0) {
					try {
						result = applyLoanService.submit(id, uid);
					} catch (Exception e) {
						logger.error("---[Apply loan]-----submit----id:" + id + "-----------submit Exception----------"
								+ e.getMessage());
						e.printStackTrace();
					}
				}
			}
		} else {
			map.put("status", "0");
			logger.info("用户类型异常，非企业用户，非个人用户!");
		}

		logger.info("---[Apply loan]-----submit----id:" + id + ",submit result:" + result + "----------------");
		if (result == 1) {
			map.put("success", "1");
		} else {
			map.put("success", "0");
		}

		return map;
	}

	/**
	 * 
	 * @author ldm
	 * @Description: 贷款列表-提交-查询附件是否齐全
	 * @param @param
	 *            id
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @version
	 *             2
	 * @date 2015年10月19日 下午5:22:14
	 */
	@RequestMapping("/checkAtt")
	@ResponseBody
	public Map<String, Object> checkAtt(Long id, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer types = attachService.selectTypesById(id);
		String typeUser = (String) session.getAttribute("typeUser");
		if ("0".equals(typeUser)) {
			if (null != types && types == 4) {
				map.put("success", "1");
			} else {
				map.put("success", "0");
			}
		} else if ("1".equals(typeUser)) {
			if (null != types && types == 2) {
				map.put("success", "1");
			} else {
				map.put("success", "0");
			}
		}
		return map;
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 逻辑删除贷款申请单
	 * @param @param
	 *            id
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @date
	 *             2015年10月19日 下午5:22:52
	 */
	@RequestMapping("/deleteApplyLoan")
	@ResponseBody
	public Map<String, Object> deleteApplyLoan(Long id, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();

		String typeUser = (String) session.getAttribute("typeUser");
		int result = 0;
		logger.info("---[Apply loan]-----deleteApplyLoan-----typeUser:" + typeUser + "----------id:" + id);
		if ("0".equals(typeUser)) {
			Cuser cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				map.put("status", "1");
				if (null != id && id != 0) {
					try {
						result = applyLoanService.deleteApplyLoan(id);
						logger.info("---[Apply loan]-----deleteApplyLoan-----typeUser:0----------id:" + id
								+ "-----userID:" + cuser.getId() + "--result:" + result);
					} catch (Exception e) {
						logger.info("---[Apply loan]-----delete----id:" + id + "-----------delete Exception----------"
								+ e.getMessage());
						e.printStackTrace();
					}
				}
			}
		} else if ("1".equals(typeUser)) {
			Puser puser = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != puser) {
				map.put("status", "1");
				if (null != id && id != 0) {
					try {
						result = applyLoanService.deleteApplyLoan(id);
						logger.info("---[Apply loan]-----deleteApplyLoan-----typeUser:1----------id:" + id
								+ "-----userID:" + puser.getId() + "--result:" + result);
					} catch (Exception e) {
						logger.error("---[Apply loan]-----delete----id:" + id + "-----------delete Exception----------"
								+ e.getMessage());
						e.printStackTrace();
					}
				}
			}
		} else {
			map.put("status", "0");
			logger.info("用户类型异常，非企业用户，非个人用户!");
		}

		logger.info("----[Apply loan]----delete---id:" + id + ",----result:" + result);
		if (result == 1) {
			map.put("success", "1");
		} else {
			map.put("success", "0");
		}

		return map;
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 完善企业信息
	 * @param @param
	 *            request
	 * @param @param
	 *            cuser
	 * @param @param
	 *            puser
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @version
	 *             2.0
	 * @date 2015年10月19日 下午4:49:21
	 */
	@RequestMapping("saveUserInfo")
	@ResponseBody
	public Map<String, Object> saveUserInfo(HttpServletRequest request, Cuser cuser, Puser puser, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();

		String typeUser = (String) session.getAttribute("typeUser");
		int result = 0;
		logger.info("----[Apply loan]----saveUserInfo------typeUser:" + typeUser);
		if ("0".equals(typeUser)) {
			Cuser c_user = null;
			c_user = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				cuser.setId(c_user.getId());
				map.put("status", "1");

				try {
					Cuser cc = applyLoanService.getCuserByCorpName(cuser);
					if (null != cc) {
						result = -2;// 企业名称已存在
					} else {
						cc = applyLoanService.getCuserByTaxSn(cuser);
						if (null != cc) {
							result = -1;// 纳税号已存在
						} else {
							result = applyLoanService.saveUserInfo(cuser);
						}
					}
				} catch (Exception e) {
					logger.error("---[Apply loan]-----saveUserInfo----user.id:" + c_user.getId()
							+ "----save Exception----------" + e.getMessage());
				}

				c_user = applyLoanService.getCuser(c_user.getId());
				String sessionInfo = ConfigUtil.getSessionInfoName();
				session.setAttribute(sessionInfo, c_user);

				logger.info("----[Apply loan]----saveUserInfo------id:" + c_user.getId() + ",result:" + result);

				map.put("success", result);

				return map;
			}
		} else if ("1".equals(typeUser)) {
			Puser p_user = null;
			p_user = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != p_user) {
				puser.setId(p_user.getId());
				map.put("status", "1");

				try {
					Puser cc = applyLoanService.getPuserByIdcard(puser);
					if (null != cc) {
						result = -11;
					} else {
						result = applyLoanService.saveUserInfo(puser);
					}
				} catch (Exception e) {
					logger.error("---[Apply loan]-----saveUserInfo----user.id:" + p_user.getId()
							+ "----save Exception----------" + e.getMessage());
				}
				logger.info("----[Apply loan]----saveUserInfo------id:" + p_user.getId() + ",result:" + result);

				p_user = applyLoanService.getPuser(p_user.getId());
				String sessionInfo = ConfigUtil.getSessionInfoName();
				session.setAttribute(sessionInfo, p_user);

				map.put("success", result);
				return map;
			}
		}

		map.put("status", "0");
		logger.info("----[Apply loan]----saveUserInfo------User is null !!!");
		return map;
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 企业用户查询邀请的人
	 * @param @param
	 *            page
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @date
	 *             2015年10月19日 下午5:28:34
	 */
	@RequestMapping("queryInviter")
	@ResponseBody
	public Map<String, Object> queryInviter(Integer page, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == page || page < 1)
			page = 1;
		int pageSize = 10;
		String typeUser = (String) session.getAttribute("typeUser");
		String success = "0";
		logger.info("----[Apply loan]----queryInviter------typeUser:" + typeUser);
		if ("0".equals(typeUser)) {
			Cuser cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				List<InviteeBean> list = applyLoanService.queryInviter(cuser.getId(), (page - 1) * 10, pageSize);
				success = "1";
				map.put("list", list);
				map.put("inviteCode", StringUtils.inviteCode(cuser.getId()));
				map.put("inviteeTotal", applyLoanService.queryInviterTotal(cuser.getId()));
				map.put("awardTotal", applyLoanService.queryAwardTotal(cuser.getId()));
			}
		}
		map.put("success", success);
		return map;
	}

	/**
	 * 
	 * @author gkm
	 * @Description: 企业用户查询邀请贷款返利的我的奖励
	 * @param @param
	 *            inviteCode
	 * @param @param
	 *            page
	 * @param @param
	 *            session
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @date
	 *             2015年10月12日 下午5:35:58
	 */
	@RequestMapping("queryMyAward")
	@ResponseBody
	public Map<String, Object> queryMyAward(Integer page, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		int pageSize = 10;
		if (null == page || page < 1)
			page = 1;
		String typeUser = (String) session.getAttribute("typeUser");
		logger.info("----[Apply loan]----queryInviter------typeUser:" + typeUser);
		String success = "0";
		if ("0".equals(typeUser)) {
			Cuser cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (null != cuser) {
				List<InviterAwardBean> list = applyLoanService.queryInviterAward(cuser.getId(), (page - 1) * 10,
						pageSize);
				success = "1";
				map.put("list", list);
				map.put("awardTotal", applyLoanService.queryAwardTotal(cuser.getId()));
			}
		}
		map.put("success", success);
		return map;
	}
}
