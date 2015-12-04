package com.baiwang.banktax.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

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

	/**
	  * @author gkm
	  * @Description: 实名认证选择地区下一部(认证平台)
	  * @param @param id
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月30日 下午1:33:08
	  */
	AreaBean getVerifyType(@Param("id")Integer id);

	/**
	  * @author gkm
	 * @param long1 
	  * @Description: 用户没有实名认证,更新idcard_status
	  * @param @param id  
	  * @return void  
	  * @throws
	  * @date 2015年11月30日 下午1:52:39
	  */
	int updateArea(@Param("id")Long id, @Param("vrfAreaid")Integer vrfAreaid);

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
	  * @date 2015年11月30日 下午3:46:04
	  */
	int updateUser(@Param("corpName")String corpName, @Param("taxSn")String taxSn, 
			@Param("idcard")String idcard, @Param("id")Long id, @Param("taxVerify")String taxVerify);

	/**
	  * @author gkm
	  * @Description: 验证产品地区 和认证地区 是否可用
	  * @param @param id
	  * @param @param vrfAreaid
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年11月30日 下午7:14:17
	  */
	int getAreaFlag(@Param("id")Integer id, @Param("vrfAreaid")int vrfAreaid);

	/**
	  * @author gkm
	  * @Description: 查询纳税号的认证次数
	  * @param @param taxSn
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年12月1日 上午10:27:33
	  */
	int selectUserByTaxSn(@Param("taxSn")String taxSn);

	/**
	  * @author gkm
	 * @param id2 
	  * @Description: 判断用户有无申请
	  * @param @param id
	  * @param @return  
	  * @return List<ApplyLoan>  
	  * @throws
	  * @date 2015年12月1日 下午2:48:23
	  */
	Integer queryLoan(@Param("pid")Integer pid, @Param("uid")Long id);

	/**
	  * @author gkm
	  * @Description: 认证 查询城市,先判断是否为产品省
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年12月3日 下午1:49:28
	  */
	int getIfProvince(Integer id);

	/**
	  * @author gkm
	  * @Description: 认证  产品地区 为省份 查询城市
	  * @param @param id
	  * @param @return  
	  * @return ArrayList<AreaBean>  
	  * @throws
	  * @date 2015年12月3日 下午1:55:57
	  */
	ArrayList<AreaBean> getCityFromArea(Integer id);
	
	
	
	
	
	
	
}