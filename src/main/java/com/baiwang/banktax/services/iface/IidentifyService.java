/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import java.util.ArrayList;

import com.baiwang.banktax.beans.AreaBean;
import com.baiwang.banktax.beans.User;

/**
  * @ClassName: IidentifyService
  * @Description: 认证
  * @author gkm
  * @date 2015年11月27日 下午3:15:26
  */
public interface IidentifyService {

	/**
	  * @author gkm
	  * @Description: 查询认证省份
	  * @param @return  
	  * @return ArrayList<AreaBean>  
	  * @throws
	  * @date 2015年11月27日 下午3:52:37
	  */
	ArrayList<AreaBean> getProvince();

	/**
	  * @author gkm
	  * @Description: 查询认证城市
	  * @param @param id
	  * @param @return  
	  * @return ArrayList<AreaBean>  
	  * @throws
	  * @date 2015年11月27日 下午5:52:37
	  */
	ArrayList<AreaBean> getCity(Integer id);

	/**
	  * @author gkm
	 * @param user 
	  * @Description: 实名认证选择地区下一步(认证平台)
	  * @param @param id
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月30日 下午1:31:53
	  */
	AreaBean getVerifyType(User user, Integer id);

	/**
	  * @author gkm
	  * @Description: 平台2的认证
	  * @param @param corpName
	  * @param @param taxSn
	  * @param @param idcard
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年11月30日 下午3:43:47
	  */
	int plat2(User user);
	
	/**
	  * @author gkm
	  * @Description: 平台3的认证
	  * @param @param corpName
	  * @param @param taxSn
	  * @param @param idcard
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年11月30日 下午3:43:47
	  */
	int plat3(String corpName, String taxSn, String idcard, Long id);

	/**
	  * @author gkm
	  * @Description: 认证成功后 把User加入到session中
	  * @param @param id
	  * @param @return  
	  * @return Object  
	  * @throws
	  * @date 2015年11月30日 下午4:12:55
	  */
	User selectById(Long id);

	/**
	  * @author gkm
	  * @Description: 验证产品地区 和认证地区 是否可用
	  * @param @param id 产品id
	  * @param @param vrfAreaid 认证地区id
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月30日 下午7:08:24
	  */
	String getAreaFlag(Integer id, int vrfAreaid);

	/**
	  * @author gkm
	 * @param id2 
	  * @Description: 判断有无申请
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年12月1日 下午2:44:38
	  */
	int queryApplyFlag(Integer id, Long uid);

	
	
	
	
}
