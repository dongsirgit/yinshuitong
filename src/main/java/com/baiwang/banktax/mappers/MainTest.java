/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.mappers;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.StringUtils;

/**
  * @ClassName: MainTest
  * @Description: TODO
  * @author Yinhua
  * @date 2015年9月25日 下午5:44:43
  */
public class MainTest {
	private static final String sessionInfo = ConfigUtil.getLoginedUserStr();
	
	@Resource
	private IUserService userService;
	
	void logUserInfo(HttpSession session) {
			User user = (User) session.getAttribute(sessionInfo);
			logUserInfo(user);
	}
	
	private void logUserInfo(User user) {
		//userService.updateLogById(user);
	}
	
	public static void main(String[] args) {
		long st = System.currentTimeMillis();
		for (int i = 1010000; i < 1050000; i++)
			System.out.println(i + "VCode: " + StringUtils.inviteCode(i));
		System.out.println("Cost: "+(System.currentTimeMillis()-st)+"ms");
	}
}
