package com.baiwang.banktax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.model.ApplyListBean;

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
	List<ApplyListBean> queryLoanList(Long id);

	/**
	  * @author Administrator
	 * @param user 
	  * @Description: TODO
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年12月1日 下午6:34:15
	  */
	int quxiao(@Param("id")Integer id, @Param("userId")Long user);
}