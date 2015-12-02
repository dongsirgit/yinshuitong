package com.baiwang.banktax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baiwang.banktax.beans.UserAttacht;

public interface UserAttachtMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByBatchId(Long batchId);

    int insert(UserAttacht record);

    int insertSelective(UserAttacht record);

    UserAttacht selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(UserAttacht record);

    int updateByPrimaryKey(UserAttacht record);
    
    int updateApplyIdByPK(UserAttacht record);
    
    UserAttacht selectByCondition(UserAttacht ua);
    
    UserAttacht selectByType(@Param("applyid")Long applyid,@Param("attachType")Byte attachType);
    
    Integer selectTypesById(Long applyId);
    
    List<UserAttacht> selectByBatchId(@Param("batchId")Long batchId,@Param("uid")Long uid);
    
    List<UserAttacht> selectByIdType(@Param("applyid")Long applyid,@Param("attachType")Byte attachType,@Param("uid")Long uid);
    
    UserAttacht selectById(@Param("id")Long id,@Param("uid")Long uid);
    
    
}