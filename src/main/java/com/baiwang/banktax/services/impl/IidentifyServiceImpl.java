/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.AreaBean;
import com.baiwang.banktax.dao.AreaBeanMapper;
import com.baiwang.banktax.services.iface.IidentifyService;

/**
  * @ClassName: IidentifyServiceImpl
  * @Description: 认证
  * @author gkm
  * @date 2015年11月27日 下午3:16:19
  */
@Service
public class IidentifyServiceImpl implements IidentifyService{

	@Resource
	private AreaBeanMapper dao;
	
	
	/**
	  * @author gkm
	  * @Description: 查询认证省份
	  * @param @return  
	  * @return ArrayList<AreaBean>  
	  * @throws
	  * @date 2015年11月27日 下午3:52:37
	  */
	public ArrayList<AreaBean> getProvince(){
		return dao.getProvince();
	}
	
	/**
	  * @author gkm
	  * @Description: 查询认证城市
	  * @param @param id
	  * @param @return  
	  * @return ArrayList<AreaBean>  
	  * @throws
	  * @date 2015年11月27日 下午5:52:37
	  */
	public ArrayList<AreaBean> getCity(Integer id){
		return dao.getCity(id);
	}
	
	
	
}
