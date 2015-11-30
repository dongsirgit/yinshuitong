/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import java.util.ArrayList;

import com.baiwang.banktax.beans.AreaBean;

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

	
	
	
	
}
