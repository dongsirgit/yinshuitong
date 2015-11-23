package com.baiwang.banktax.dao;

import java.util.List;

import com.baiwang.banktax.beans.UserAttachtBatch;

public interface UserAttachtBatchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAttachtBatch record);

    int insertSelective(UserAttachtBatch record);

    UserAttachtBatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAttachtBatch record);

    int updateByPrimaryKey(UserAttachtBatch record);
    
    UserAttachtBatch selectByCondition(UserAttachtBatch uab);
    
    List<UserAttachtBatch> selectByApplyId(Long applyid);
}