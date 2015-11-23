package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.ApplyLoanGuaranty;

public interface ApplyLoanGuarantyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApplyLoanGuaranty record);

    int insertSelective(ApplyLoanGuaranty record);

    ApplyLoanGuaranty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApplyLoanGuaranty record);

    int updateByPrimaryKey(ApplyLoanGuaranty record);
}