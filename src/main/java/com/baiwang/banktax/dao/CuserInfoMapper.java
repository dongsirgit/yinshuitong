package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.CuserInfo;

public interface CuserInfoMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(CuserInfo record);

    int insertSelective(CuserInfo record);

    CuserInfo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(CuserInfo record);

    int updateByPrimaryKey(CuserInfo record);
}