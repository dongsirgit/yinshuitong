package com.baiwang.banktax.dao;

import java.util.List;

import com.baiwang.banktax.beans.ApplyLoan;

public interface ApplyLoanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApplyLoan record);

    int insertSelective(ApplyLoan record);

    ApplyLoan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApplyLoan record);

    int updateByPrimaryKey(ApplyLoan record);

	/**
	  * @author gkm
	  * @Description: 查询申请列表
	  * @param @param id
	  * @param @return  
	  * @return List<ApplyLoan>  
	  * @throws
	  * @date 2015年12月1日 下午4:13:24
	  */
	List<ApplyLoan> queryLoanList(Long id);
}