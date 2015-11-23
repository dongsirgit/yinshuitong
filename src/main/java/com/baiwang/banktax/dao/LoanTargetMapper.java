package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.LoanTarget;

public interface LoanTargetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoanTarget record);

    int insertSelective(LoanTarget record);

    LoanTarget selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoanTarget record);

    int updateByPrimaryKey(LoanTarget record);
}