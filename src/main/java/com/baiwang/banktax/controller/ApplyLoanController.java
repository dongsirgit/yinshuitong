/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
  * @ClassName: ApplyLoanController
  * @Description: 申请贷款
  * @author ldm
  * @date 2015年11月30日 下午4:57:20
  */

@Controller
@RequestMapping("users/applyloan")
public class ApplyLoanController {
	
	@RequestMapping("toLoan")
	public String toLoan(){
		return "order/loan_apply";
	}
	
	@RequestMapping("loansub")
	public String submitLoan( ){
		
		
		return null;
	}

}
