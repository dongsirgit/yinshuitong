/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.dao.ApplyLoanMapper;
import com.baiwang.banktax.services.iface.ILoanListService;

/**
  * @ClassName: LoanListServiceImpl
  * @Description: TODO
  * @author Administrator
  * @date 2015年8月5日 下午5:45:10
  */
@Service
public class LoanListServiceImpl implements ILoanListService {

	@Resource
	private ApplyLoanMapper dao;
	/**
	  * @Description: 查询用户贷款列表
	  * @param @param uid
	  * @param @return  
	  * @return List<ApplyLoan>  
	  * @throws
	  */
	public List<ApplyLoan> queryLoanList(Long uid){
		return dao.queryLoanList(uid);
	}
	
	/**
	  * @Description: 贷款申请列表删除一个申请
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  */
	public int delApplyLoan(Long id){
		return dao.delApplyLoan(id);
	}

}
