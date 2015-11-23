package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.WarningInfo;

public interface WarningInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WarningInfo record);

    int insertSelective(WarningInfo record);

    WarningInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WarningInfo record);

    int updateByPrimaryKeyWithBLOBs(WarningInfo record);

    int updateByPrimaryKey(WarningInfo record);
}