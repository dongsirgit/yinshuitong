package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.User;

public interface UserMapper {

    void userRegister(User user);
    int insertSelective(User record);
    
    int updateByIdSelective(User record);
    
    User selectById(Long id);
    User selectByMobilePhone(String mobilePhone);
    User selectByMobilePhoneAndUserPass(User user);
    
}