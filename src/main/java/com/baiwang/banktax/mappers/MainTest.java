/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.mappers;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.services.iface.IPuserService;
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
	private static final String sessionInfo = ConfigUtil.getSessionInfoName();
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IPuserService puserService;
	
	void logUserInfo(HttpSession session) {
		String typeUser = (String) session.getAttribute("typeUser");
		if (null == typeUser)
			return;
		if ("0".equals(typeUser)) {
			Cuser user = (Cuser) session.getAttribute(sessionInfo);
			logUserInfo(user);
		} else if ("1".equals(typeUser)) {
			Puser user = (Puser) session.getAttribute(sessionInfo);
			logUserInfo(user);
		}
	}
	
	private void logUserInfo(Cuser user) {
		userService.updateLogById(user);
	}

	private void logUserInfo(Puser user) {
	}
	
	public static void main(String[] args) {
		long st = System.currentTimeMillis();
		for (int i = 1010000; i < 1050000; i++)
			System.out.println(i + "VCode: " + StringUtils.inviteCode(i));
		System.out.println("Cost: "+(System.currentTimeMillis()-st)+"ms");
	}
}
