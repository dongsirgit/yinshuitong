/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.dao.PuserMapper;
import com.baiwang.banktax.services.iface.IPuserService;

/**
  * @ClassName: PuserServiceImpl
  * @Description: TODO
  * @author：张衡
  * @date 2015年9月6日 上午10:43:40
  */

@Service("puserService")
public class PuserServiceImpl implements IPuserService{
	
	@Resource
	private PuserMapper puserDao;

	/**
	  * <p>Title: insertRegister</p>
	  * <p>Description: </p>
	  * @param user
	  * @see com.baiwang.banktax.services.iface.IPuserService#insertRegister(com.baiwang.banktax.beans.Cuser)
	  */
	@Override
	public void insertRegister(Cuser user) {
		
		puserDao.insertRegister(user);
		
	}
	
	/**
	  * <p>Title: selectByNameFromPuser</p>
	  * <p>Description: </p>
	  * @param name
	  * @return
	  * @see com.baiwang.banktax.services.iface.IUserService#selectByNameFromPuser(java.lang.String)
	  */
	@Override
	public Puser selectByNameFromPuser(String user_name) {
		
		Puser puser=puserDao.selectByNameFromPuser(user_name);
		return puser;
		
	}
	
	/**
	  * <p>Title: selectByMailFromPuser</p>
	  * <p>Description: </p>
	  * @param mail
	  * @return
	  * @see com.baiwang.banktax.services.iface.IUserService#selectByMailFromPuser(java.lang.String)
	  */
	@Override
	public int selectByMailFromPuser(String mail) {
		int a=puserDao.selectByMailFromPuser(mail);
		return a;
		
	}

	/**
	  * <p>Title: selectByNameAndPass</p>
	  * <p>Description: </p>
	  * @param user
	  * @return
	  * @see com.baiwang.banktax.services.iface.IPuserService#selectByNameAndPass(com.baiwang.banktax.beans.Cuser)
	  */
	@Override
	public Puser selectByNameAndPass(Cuser user) {
		
		Puser use=puserDao.selectByNameAndPass(user);
		return use;
		
	}

	/**
	  * <p>Title: selectByNameAndMail</p>
	  * <p>Description: </p>
	  * @param user
	  * @return
	  * @see com.baiwang.banktax.services.iface.IPuserService#selectByNameAndMail(com.baiwang.banktax.beans.Cuser)
	  */
	@Override
	public Puser selectByNameAndMail(Cuser user) {
		
		Puser use=puserDao.selectByNameAndMail(user);
		return use;
		
	}

	/**
	  * <p>Title: updateSelectiveByUsername</p>
	  * <p>Description: </p>
	  * @param user
	  * @see com.baiwang.banktax.services.iface.IPuserService#updateSelectiveByUsername(com.baiwang.banktax.beans.Cuser)
	  */
	@Override
	public void updateSelectiveByUsername(Cuser user) {
		
	puserDao.updateSelectiveByUsername(user);
		
	}

	/**
	  * <p>Title: selectById</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see com.baiwang.banktax.services.iface.IPuserService#selectById(long)
	  */
	@Override
	public Puser selectById(long id) {
		
	Puser user=puserDao.selectById(id);
		return user;
		
	}

	/**
	  * <p>Title: updateById</p>
	  * <p>Description: </p>
	  * @param user
	  * @see com.baiwang.banktax.services.iface.IPuserService#updateById(com.baiwang.banktax.beans.Puser)
	  */
	@Override
	public void updateById(Puser user) {
		
		puserDao.updateById(user);
		
	}
}
