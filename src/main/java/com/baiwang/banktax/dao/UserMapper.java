package com.baiwang.banktax.dao;

import org.apache.ibatis.annotations.Param;

import com.baiwang.banktax.beans.User;

public interface UserMapper {

    void userRegister(User user);
    int insertSelective(User record);
    
    void updateByPhoneSelective(User user);
    void updatePwdByMobilePhone(@Param(value="mobilePhone")String mobilePhone,@Param(value="userPass")String userPass);
    
    User selectById(Long id);
    User selectByMobilePhone(String mobilePhone);
    User selectByMobilePhoneAndUserPass(User user);
    
}