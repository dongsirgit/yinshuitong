package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.MuserLog;

public interface MuserLogMapper {
    int insert(MuserLog record);

    int insertSelective(MuserLog record);
}