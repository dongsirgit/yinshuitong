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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.InviteeBean;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.services.iface.IApplyLoanService;
import com.baiwang.banktax.services.iface.ILoanListService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.StringUtils;

/**
 * @ClassName: UserInfoController
 * @Description: 个人中心
 * @author ldm
 * @date 2015年8月28日 下午5:13:13
 */
@Controller
@RequestMapping("/users")
public class UserInfoController {

	private static final Log logger = LogFactory.getLog(UserInfoController.class);

	@Resource
	private ILoanListService service;
	@Resource
	private IApplyLoanService applyLoanService;

	@RequestMapping("/init/{page}")
	public String init(@PathVariable String page, HttpSession session, Map<String, Object> map) {
		if ("userInfo_applyList".equals(page)) {
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
		}

		logger.info("--------访问页面：" + page + ".jsp");
		return "user/" + page;
	}

	/**
	 * @author ldm
	 * @Description: 根据邀请人id 查询其邀请的好友申请成功的所有贷款
	 * @param @return
	 * @return Map<String,Object>
	 * @throws @date
	 *             2015年10月12日 下午4:39:26
	 */
	@RequestMapping("il4jf")
	@ResponseBody
	public Map<String, Object> queryLoansByIvtId(HttpSession session, HttpServletRequest req) {
		String par = req.getParameter("page");
		int page = StringUtils.s2i(par);
		if (page <= 0)
			page = 1;
		String success = "0";
		Map<String, Object> map = new HashMap<String, Object>();
		Cuser cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (null != cuser) {
			int pageSize = 10;
			List<InviteeBean> list = applyLoanService.queryLoansByIvtId(cuser.getId(), (page - 1) * 10, pageSize);
			int count = applyLoanService.queryLoansByIvtIdCount(cuser.getId());
			map.put("vloanList", list);
			map.put("loanRecordTotal", count);
			success = "1";
			logger.info("--------queryLoansByIvtId-----操作用户:" + cuser.getUserName() + "------page:" + page + "-----");
		}
		map.put("success", success);
		return map;
	}
}
