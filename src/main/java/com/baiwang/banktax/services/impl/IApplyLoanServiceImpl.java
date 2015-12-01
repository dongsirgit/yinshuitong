/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.dao.ApplyLoanMapper;
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

}
