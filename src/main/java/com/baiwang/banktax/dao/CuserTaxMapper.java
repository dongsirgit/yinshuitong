package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.CuserTax;

public interface CuserTaxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CuserTax record);

    int insertSelective(CuserTax record);

    CuserTax selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CuserTax record);

    int updateByPrimaryKey(CuserTax record);
}