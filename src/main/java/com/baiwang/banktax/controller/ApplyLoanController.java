/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.services.iface.IApplyLoanService;
import com.baiwang.banktax.services.iface.IAttachService;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.StringUtils;

import sun.swing.StringUIClientPropertyKey;

/**
  * @ClassName: ApplyLoanController
  * @Description: 申请贷款
  * @author ldm
  * @date 2015年11月30日 下午4:57:20
  */

@Controller
@RequestMapping("users/applyloan")
public class ApplyLoanController {
	
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@Resource
	IApplyLoanService applyloanService;
	@Resource
	IAttachService attachService;
	
	@RequestMapping("toLoan")
	public String toLoan(){
		return "order/loan_apply";
	}
	
	@RequestMapping("loansub")
	public String submitLoan(ApplyLoan applyLoan,HttpServletRequest requset){
		try{
			User user = (User) requset.getSession().getAttribute(ConfigUtil.getLoginedUserStr());
			applyLoan.setUid(user.getId());
			applyLoan.setApplyStatus((short)100);
			applyloanService.insertSelective(applyLoan);
			System.out.println("本次生成贷款ID："+applyLoan.getId());
			UserAttacht ua = new UserAttacht();
			ua.setApplyId(applyLoan.getId());
			ua.setId(StringUtils.s2l(requset.getParameter("yyzz_atid")));
			attachService.updateApplyIdByPK(ua);
			System.out.println("更新附件ID："+ua.getId());
			ua.setId(StringUtils.s2l(requset.getParameter("sqs_atid")));
			attachService.updateApplyIdByPK(ua);
			System.out.println("更新附件ID："+ua.getId());
			logger.info("贷款申请提交成功！操作用户："+user.getMobilePhone()+";贷款信息："+applyLoan.toString());
			return "order/submitSucess";
		}catch(Exception e){
			requset.setAttribute("err_msg", "提交失败！请重新操作！");
			logger.error(e);
			return "order/loan_apply";
		}
	}

}
