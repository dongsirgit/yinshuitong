package com.baiwang.banktax.services.iface;

import java.util.List;

import com.baiwang.banktax.beans.UserAttachtBatch;

public interface IAttachBatchService {
	
	 void deleteByPrimaryKey(Long id);

	    int insert(UserAttachtBatch record);

	    int insertSelective(UserAttachtBatch record);

	    UserAttachtBatch selectByPrimaryKey(Long id);
	    
	    //根据贷款ID和附件类型查询批次表
	    UserAttachtBatch selectByCondition(UserAttachtBatch uab);

	    int updateByPrimaryKeySelective(UserAttachtBatch record);

	    int updateByPrimaryKey(UserAttachtBatch record);
	    public List<UserAttachtBatch> selectByApplyId(Long applyid);

}
