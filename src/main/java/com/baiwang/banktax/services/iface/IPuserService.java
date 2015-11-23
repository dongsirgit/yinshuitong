/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Puser;

/**
  * @ClassName: IPuserService
  * @Description: TODO
  * @author：张衡
  * @date 2015年9月6日 上午10:42:59
  */
public interface IPuserService {
	void insertRegister(Cuser user);
	public Puser selectByNameFromPuser(String user_name);
	public int selectByMailFromPuser(String mail);
	public Puser selectByNameAndPass(Cuser user);
	public Puser selectByNameAndMail(Cuser user);
	public void updateSelectiveByUsername(Cuser user);
	public Puser selectById(long id);
	public void updateById(Puser user);
}
