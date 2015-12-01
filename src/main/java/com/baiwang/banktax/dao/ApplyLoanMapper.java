package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.ApplyLoan;

public interface ApplyLoanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApplyLoan record);

    int insertSelective(ApplyLoan record);

    ApplyLoan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApplyLoan record);

    int updateByPrimaryKey(ApplyLoan record);
}