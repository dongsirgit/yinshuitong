package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.Muser;

public interface MuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Muser record);
    
    int insertSelective(Muser record);

    Muser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Muser record);

    int updateByPrimaryKey(Muser record);
}