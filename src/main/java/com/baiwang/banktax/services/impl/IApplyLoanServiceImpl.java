/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.dao.ApplyLoanMapper;
import com.baiwang.banktax.model.ApplyDetailBean;
import com.baiwang.banktax.model.ApplyListBean;
import com.baiwang.banktax.services.iface.IApplyLoanService;

/**
  * @ClassName: IApplyLoanService
  * @Description: TODO
  * @author Administrator
  * @date 2015年12月1日 上午11:12:19
  */
@Service
public class IApplyLoanServiceImpl implements IApplyLoanService {
	@Resource
	ApplyLoanMapper dao;

	/**
	  * <p>Title: deleteByPrimaryKey</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see com.baiwang.banktax.services.iface.IApplyLoanService#deleteByPrimaryKey(java.lang.Long)
	  */
	@Override
	public int deleteByPrimaryKey(Long id) {

		return 0;

	}

	/**
	  * <p>Title: insert</p>
	  * <p>Description: </p>
	  * @param record
	  * @return
	  * @see com.baiwang.banktax.services.iface.IApplyLoanService#insert(com.baiwang.banktax.beans.ApplyLoan)
	  */
	@Override
	public int insert(ApplyLoan record) {

		// TODO Auto-generated method stub
		return 0;

	}

	/**
	  * <p>Title: insertSelective</p>
	  * <p>Description: </p>
	  * @param record
	  * @return
	  * @see com.baiwang.banktax.services.iface.IApplyLoanService#insertSelective(com.baiwang.banktax.beans.ApplyLoan)
	  */
	@Override
	public int insertSelective(ApplyLoan record) {
		return dao.insertSelective(record);
	}

	/**
	  * <p>Title: selectByPrimaryKey</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see com.baiwang.banktax.services.iface.IApplyLoanService#selectByPrimaryKey(java.lang.Long)
	  */
	@Override
	public ApplyLoan selectByPrimaryKey(Long id) {

		// TODO Auto-generated method stub
		return null;

	}

	/**
	  * <p>Title: updateByPrimaryKeySelective</p>
	  * <p>Description: </p>
	  * @param record
	  * @return
	  * @see com.baiwang.banktax.services.iface.IApplyLoanService#updateByPrimaryKeySelective(com.baiwang.banktax.beans.ApplyLoan)
	  */
	@Override
	public int updateByPrimaryKeySelective(ApplyLoan record) {

		// TODO Auto-generated method stub
		return 0;

	}

	/**
	  * <p>Title: updateByPrimaryKey</p>
	  * <p>Description: </p>
	  * @param record
	  * @return
	  * @see com.baiwang.banktax.services.iface.IApplyLoanService#updateByPrimaryKey(com.baiwang.banktax.beans.ApplyLoan)
	  */
	@Override
	public int updateByPrimaryKey(ApplyLoan record) {

		// TODO Auto-generated method stub
		return 0;

	}
	
	
	/**
	  * @author gkm
	  * @Description: 查询申请列表
	  * @param @param id
	  * @param @return  
	  * @return List<ApplyLoan>  
	  * @throws
	  * @date 2015年12月1日 下午4:10:59
	  */
	public List<ApplyListBean> queryLoanList(Long id){
		return dao.queryLoanList(id);
	}
	
	/**
	  * @author gkm
	  * @Description: 取消申请
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年12月1日 下午6:33:30
	  */
	public int quxiao(Integer id, Long user){
		return dao.quxiao(id, user);
	}

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
	public ApplyDetailBean queryLoanDeatil(Integer id, Long uid){
		
		ApplyDetailBean bean = dao.queryLoanDeatil(id, uid);
	
		return bean;
	}

	/**
	  * @author ldm
	  * @Description: 更新贷款状态
	  * @param @param id 贷款id
	  * @param @param notes 更新文本
	  * @param @return  
	  * @return ApplyDetailBean  
	  * @throws
	  * @date 2015年12月2日 下午2:44:27
	  */
	public int updateApplyStatus(Long id, String notes,Short status) {
		return dao.updateApplyStatus(id, notes,status);
	}

}
