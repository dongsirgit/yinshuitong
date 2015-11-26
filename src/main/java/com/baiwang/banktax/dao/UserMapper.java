package com.baiwang.banktax.dao;

import org.apache.ibatis.annotations.Param;

import com.baiwang.banktax.beans.User;

public interface UserMapper {

    void userRegister(User user);
    int insertSelective(User record);
    
    int updateByIdSelective(User record);
    void updatePwdByMobilePhone(@Param(value="mobilePhone")String mobilePhone,@Param(value="userPass")String userPass);
    
    User selectById(Long id);
    User selectByMobilePhone(String mobilePhone);
    User selectByMobilePhoneAndUserPass(User user);
    
}