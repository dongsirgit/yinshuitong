package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Puser;

public interface PuserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Puser record);

    int insertSelective(Puser record);

    Puser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Puser record);

    int updateByPrimaryKey(Puser record);
    void insertRegister(Cuser user);

	Puser selectByNameFromPuser(String user_name);

	int selectByMailFromPuser(String mail);
	Puser selectByNameAndPass(Cuser user);
	
	Puser selectByNameAndMail(Cuser user);
	void updateSelectiveByUsername(Cuser user);
	Puser selectById(long id);
	void updateById(Puser user);
}