package com.baiwang.banktax.services.iface;

import com.baiwang.banktax.beans.User;

public interface IUserService {
    
    void userRegister(User user);
    
    User selectByMobilePhone(String mobilePhone);
    User selectByMobilePhoneAndUserPass(User user);

}
