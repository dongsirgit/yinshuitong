package com.baiwang.banktax.services.iface;

import java.util.List;

import com.baiwang.banktax.beans.UserAttacht;

public interface IAttachService {
	
	int deleteByPrimaryKey(Long id);
	
	int deleteByBatchId(Long batchId);

    int insert(UserAttacht record);

    int insertSelective(UserAttacht record);

    UserAttacht selectByPrimaryKey(Long id);
    
    UserAttacht selectByCondition(UserAttacht ua);
    
    List<UserAttacht> selectByBatchId(Long batchId,Long uid);

    int updateByPrimaryKeySelective(UserAttacht record);

    int updateByPrimaryKey(UserAttacht record);
    
    int updateApplyIdByPK(UserAttacht record);
    
    UserAttacht selectByType(Long applyid, Byte attachType);
    
    List<UserAttacht> selectByIdType(Long applyid, Byte attachType,Long uid);
    
    UserAttacht selectById(Long id,Long uid);
    
    Integer selectTypesById(Long applyId);
    
    

}
