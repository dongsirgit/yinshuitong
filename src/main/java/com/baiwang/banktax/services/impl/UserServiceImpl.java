package com.baiwang.banktax.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.dao.UserMapper;
import com.baiwang.banktax.services.iface.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
    
    @Resource
    private UserMapper userDao;

    /**
     * 
      * <p>Title: selectByMobilePhone</p>
      * <p>Description: 检查手机号是否存在</p>
      * @param mobilePhone
      * @return
      * @see com.baiwang.banktax.services.iface.IUserService#selectByMobilePhone(java.lang.String)
     */
    @Override
    public User selectByMobilePhone(String mobilePhone) {
        
        return userDao.selectByMobilePhone(mobilePhone);
        
    }

    /**
     * 
      * <p>Title: selectByMobilePhoneAndUserPass</p>
      * <p>Description: 用户登录</p>
      * @param user
      * @return
      * @see com.baiwang.banktax.services.iface.IUserService#selectByMobilePhoneAndUserPass(com.baiwang.banktax.beans.User)
     */
    @Override
    public User selectByMobilePhoneAndUserPass(User user) {
        
        return userDao.selectByMobilePhoneAndUserPass(user);
        
    }

    /**
     * 
      * <p>Title: userRegister</p>
      * <p>Description: 注册用户</p>
      * @param user
      * @see com.baiwang.banktax.services.iface.IUserService#userRegister(com.baiwang.banktax.beans.User)
     */
    @Override
    public void userRegister(User user) {
        
        userDao.userRegister(user);
        
    }

    /**
     * 
      * <p>Title: updatePwdByMobilePhone</p>
      * <p>Description: 根据手机号修改密码</p>
      * @param mobilePhone
      * @param userPass
      * @see com.baiwang.banktax.services.iface.IUserService#updatePwdByMobilePhone(java.lang.String, java.lang.String)
     */
    @Override
    public void updatePwdByMobilePhone(String mobilePhone, String userPass) {
        
        userDao.updatePwdByMobilePhone(mobilePhone, userPass);
        
    }

    @Override
    public void updateByPhoneSelective(User user) {
        
        userDao.updateByPhoneSelective(user);
        
    }

}
