package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.UserLog;

public interface UserLogMapper {
    int insert(UserLog record);

    int insertSelective(UserLog record);
}