package com.baiwang.banktax.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.dao.UserAttachtMapper;
import com.baiwang.banktax.services.iface.IAttachService;

@Service
public class IAttachServiceImpl implements IAttachService {
	
	@Resource
	private UserAttachtMapper userAttachDao;
	
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return userAttachDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public int insert(UserAttacht record) {
		
		return userAttachDao.insert(record);
		
	}

	@Override
	public int insertSelective(UserAttacht record) {
		
		return userAttachDao.insertSelective(record);
		
	}

	@Override
	public UserAttacht selectByPrimaryKey(Long id) {
		
		return userAttachDao.selectByPrimaryKey(id);
		
	}

	@Override
	public int updateByPrimaryKeySelective(UserAttacht record) {
		
		return userAttachDao.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public int updateByPrimaryKey(UserAttacht record) {
		
		return userAttachDao.updateByPrimaryKey(record);
		
	}

	@Override
	public UserAttacht selectByCondition(UserAttacht ua) {
		
		return userAttachDao.selectByCondition(ua);
		
	}

	@Override
	public int deleteByBatchId(Long batchId) {
		
		return userAttachDao.deleteByBatchId(batchId);
		
	}

	@Override
	public List<UserAttacht> selectByBatchId(Long batchId,Long uid) {
		
		return userAttachDao.selectByBatchId(batchId,uid);
		
	}

	@Override
	public UserAttacht selectByType(Long applyid, Byte attachType) {
		
		return userAttachDao.selectByType(applyid, attachType);
		
	}

	@Override
	public UserAttacht selectById(Long id,Long uid) {
		
		return userAttachDao.selectById(id,uid);
		
	}

	@Override
	public List<UserAttacht> selectByIdType(Long applyid, Byte attachType,Long uid) {
		return (List<UserAttacht>) userAttachDao.selectByIdType(applyid,attachType,uid);
	}

	@Override
	public Integer selectTypesById(Long applyId) {
		
		return userAttachDao.selectTypesById(applyId);
		
	}
	

}
