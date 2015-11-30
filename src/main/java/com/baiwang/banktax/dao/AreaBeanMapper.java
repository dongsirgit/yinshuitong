package com.baiwang.banktax.dao;

import java.util.ArrayList;

import com.baiwang.banktax.beans.AreaBean;

public interface AreaBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaBean record);

    int insertSelective(AreaBean record);

    AreaBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaBean record);

    int updateByPrimaryKey(AreaBean record);

	/**
	  * @author gkm
	  * @Description: 查询认证省份
	  * @param @return  
	  * @return ArrayList<AreaBean>  
	  * @throws
	  * @date 2015年11月27日 下午3:54:53
	  */
	ArrayList<AreaBean> getProvince();

	/**
	  * @author gkm
	  * @Description: 查询认证城市
	  * @param @param id
	  * @param @return  
	  * @return ArrayList<AreaBean>  
	  * @throws
	  * @date 2015年11月27日 下午5:53:29
	  */
	ArrayList<AreaBean> getCity(Integer id);
}