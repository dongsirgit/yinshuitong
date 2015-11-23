package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.Cuser;
/**
 * 

  * @ClassName: IUserMapper

  * @Description: 用户模块接口

  * @author 张衡

  * @date 2015年7月24日 上午8:59:43

  *
 */
public interface IUserMapper {
    
    public void insertSelective(Cuser user);
    
    public void deleteById(long id);
    
//    public void updateByIdSelective(Cuser user);
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