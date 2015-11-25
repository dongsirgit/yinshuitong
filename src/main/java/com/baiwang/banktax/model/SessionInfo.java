package com.baiwang.banktax.model;

import com.baiwang.banktax.beans.User;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用
  * @Description: TODO
  * @author lisy
  * @date 2015年8月4日 下午4:03:57
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return user.getUserName();
	}

}
