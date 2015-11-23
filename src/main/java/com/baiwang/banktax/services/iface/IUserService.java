package com.baiwang.banktax.services.iface;

import com.baiwang.banktax.beans.Cuser;

public interface IUserService {
	public void insertSelective(Cuser user);
	
	public void deleteById(long id);
	
//	public void updateByIdSelective(Cuser user);
	public void updatePassById(Cuser user);
	public void updateLogById(Cuser user);
	
    public int selectMaxUid();
    public int selectByMail(String mail);
    public Cuser selectUserByMail(String mail);
	public Cuser selectByPrimaryKey(String user_name);
    public Cuser selectByNameAndMail(Cuser user);
    public Cuser selectById(long id);
    public Cuser selectByNameAndPass(Cuser user);  

}
