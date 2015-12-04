/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.model.ProductSynopsisBean;
import com.baiwang.banktax.services.iface.IApplyLoanService;
import com.baiwang.banktax.services.iface.IAttachService;
import com.baiwang.banktax.services.iface.IProductService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.DateUtils;
import com.baiwang.banktax.utils.StringUtils;

/**
  * @ClassName: ApplyLoanController
  * @Description: 贷款相关控制类
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
	@Resource
	IProductService productService;
	
	/**
	  * @author ldm
	  * @Description: 跳转贷款申请页面
	  * @param @param id
	  * @param @param request
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年12月4日 下午6:53:13
	  */
	@RequestMapping("toLoan")
	public String toLoan(Integer id,HttpServletRequest request){
		List<ProductSynopsisBean> list = productService.getproList(id);
		if(null != list && list.size()==1){
			request.setAttribute("prosyn", list.get(0));
		}
		request.setAttribute("proid",id);
		return "order/loan_apply";
	}
	
	/**
	  * @author ldm
	  * @Description: 贷款提交
	  * @param @param applyLoan
	  * @param @param requset
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年12月4日 下午6:53:34
	  */
	@RequestMapping("loansub")
	public String submitLoan(ApplyLoan applyLoan,HttpServletRequest requset){
		try{
			User user = (User) requset.getSession().getAttribute(ConfigUtil.getLoginedUserStr());
			applyLoan.setUid(user.getId());
			applyLoan.setSerialNum(StringUtils.generSerialNum());//订单流水号
			applyLoan.setApplyStatus((short)100);//更新订单状态为审核中
			applyLoan.setStatusNote(DateUtils.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss")+"          您的贷款申请已提交，系统正在审核中");//更新状态文本
			applyloanService.insertSelective(applyLoan); 
			//更新用户附件表中已上传的附件对应的贷款ID
			UserAttacht ua = new UserAttacht();
			ua.setApplyId(applyLoan.getId());
			ua.setId(StringUtils.s2l(requset.getParameter("yyzz_atid")));
			attachService.updateApplyIdByPK(ua);
			ua.setId(StringUtils.s2l(requset.getParameter("sqs_atid")));
			attachService.updateApplyIdByPK(ua);
			logger.info("贷款申请提交成功！操作用户："+user.getMobilePhone()+";贷款信息："+applyLoan.toString());
			return "order/submitSucess";
		}catch(Exception e){
			requset.setAttribute("err_msg", "提交失败！请重新操作！");
			logger.error(e);
			return "order/loan_apply";
		}
	}

}
