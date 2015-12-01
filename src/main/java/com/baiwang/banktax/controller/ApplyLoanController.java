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
import com.baiwang.banktax.services.iface.IApplyLoanService;
import com.baiwang.banktax.utils.ConfigUtil;

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
	
	@RequestMapping("toLoan")
	public String toLoan(){
		return "order/loan_apply";
	}
	
	@RequestMapping("loansub")
	public String submitLoan(ApplyLoan applyLoan,HttpServletRequest requset){
		User user = (User) requset.getSession().getAttribute(ConfigUtil.getLoginedUserStr());
		applyLoan.setUid(user.getId());
		applyLoan.setApplyStatus((short)100);
		int result = applyloanService.insertSelective(applyLoan);
		if(result==1){
			logger.info("操作用户："+user.getMobilePhone()+";申请信息："+applyLoan.toString());
			return "order/submitSucess";
		}else{
			requset.setAttribute("err_msg", "提交失败！");
			return "redirect:order/loan_apply";
		}
	}

}
