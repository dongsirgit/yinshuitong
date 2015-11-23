package com.baiwang.banktax.dao;

import java.util.List;

import com.baiwang.banktax.beans.ProductLoan;

public interface ProductLoanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductLoan record);

    int insertSelective(ProductLoan record);

    ProductLoan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductLoan record);

    int updateByPrimaryKey(ProductLoan record);
    
    List<ProductLoan> selectByBank(String bank);
    
}