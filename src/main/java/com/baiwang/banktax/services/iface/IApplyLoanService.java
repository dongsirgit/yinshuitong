/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import java.util.List;

import com.baiwang.banktax.beans.ApplyLoan;

/**
  * @ClassName: IApplyLoanService
  * @Description: TODO
  * @author Administrator
  * @date 2015年12月1日 上午11:09:32
  */
public interface IApplyLoanService {
	
	public int deleteByPrimaryKey(Long id);

	public int insert(ApplyLoan record);

	public int insertSelective(ApplyLoan record);

	public ApplyLoan selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(ApplyLoan record);

	public int updateByPrimaryKey(ApplyLoan record);

	/**
	  * @author gkm
	  * @Description: 查询申请列表
	  * @param @param id
	  * @param @return  
	  * @return List<ApplyLoan>  
	  * @throws
	  * @date 2015年12月1日 下午4:10:59
	  */
	public List<ApplyLoan> queryLoanList(Long id);

}
