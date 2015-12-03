/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.model.ApplyDetailBean;
import com.baiwang.banktax.model.ApplyListBean;

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
	public List<ApplyListBean> queryLoanList(Long id);

	/**
	  * @author gkm
	 * @param use 
	  * @Description: 取消申请
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年12月1日 下午6:33:30
	  */
	public int quxiao(Integer id, Long use);

	/**
	  * @author gkm
	  * @Description: 申请列表 查看详情
	  * @param @param id 申请id
	  * @param @param uid 用户id
	  * @param @return  
	  * @return ApplyDetailBean  
	  * @throws
	  * @date 2015年12月2日 下午2:44:27
	  */
	public ApplyDetailBean queryLoanDeatil(Integer id, Long uid);
	/**
	  * @author Administrator
	  * @Description: 订单跟踪
	  * @param @param id
	  * @param @param notes
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年12月3日 下午7:48:18
	  */
	public int updateApplyStatus(Long id,String notes);

}
