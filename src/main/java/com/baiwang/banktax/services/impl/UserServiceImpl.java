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

    @Override
    public User selectByMobilePhone(String mobilePhone) {
        
        return userDao.selectByMobilePhone(mobilePhone);
        
    }

    @Override
    public User selectByMobilePhoneAndUserPass(User user) {
        
        return userDao.selectByMobilePhoneAndUserPass(user);
        
    }

    @Override
    public void userRegister(User user) {
        
        userDao.userRegister(user);
        
    }

}
