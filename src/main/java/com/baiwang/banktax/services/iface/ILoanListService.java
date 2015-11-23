/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import java.util.List;

import com.baiwang.banktax.beans.ApplyLoan;

/**
  * @ClassName: ILoanListService
  * @Description: 贷款列表 service接口
  * @author gkm
  * @date 2015年8月5日 下午5:37:26
  */
public interface ILoanListService {

	/**
	  * @Description: 查询用户贷款列表
	  * @param @param uid
	  * @param @return  
	  * @return List<ApplyLoan>  
	  * @throws
	  */
	List<ApplyLoan> queryLoanList(Long uid);

	/**
	  * @Description: 贷款申请列表删除一个申请
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  */
	int delApplyLoan(Long id);

}
