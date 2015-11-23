package com.baiwang.banktax.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.UserAttachtBatch;
import com.baiwang.banktax.dao.UserAttachtBatchMapper;
import com.baiwang.banktax.dao.UserAttachtMapper;
import com.baiwang.banktax.services.iface.IAttachBatchService;

@Service
public class IAttachBatchServiceImpl implements IAttachBatchService {

	@Resource
	private UserAttachtBatchMapper userAttachBatchDao;
	@Resource
	private UserAttachtMapper userAttachDao;
	@Override
	public void deleteByPrimaryKey(Long id) {
		userAttachBatchDao.deleteByPrimaryKey(id);
		userAttachDao.deleteByBatchId(id);
	}

	@Override
	public int insert(UserAttachtBatch record) {
		
		return userAttachBatchDao.insert(record);
		
	}

	@Override
	public int insertSelective(UserAttachtBatch record) {
		
		return userAttachBatchDao.insertSelective(record);
		
	}

	@Override
	public UserAttachtBatch selectByPrimaryKey(Long id) {
		
		return userAttachBatchDao.selectByPrimaryKey(id);
		
	}

	@Override
	public int updateByPrimaryKeySelective(UserAttachtBatch record) {
		
		return userAttachBatchDao.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public int updateByPrimaryKey(UserAttachtBatch record) {
		
		return userAttachBatchDao.updateByPrimaryKey(record);
		
	}

	@Override
	public UserAttachtBatch selectByCondition(UserAttachtBatch uab) {
		
		return userAttachBatchDao.selectByCondition(uab);
		
	}

	@Override
	public List<UserAttachtBatch> selectByApplyId(Long applyid) {
		
		return userAttachBatchDao.selectByApplyId(applyid);
		
	}
	

}
