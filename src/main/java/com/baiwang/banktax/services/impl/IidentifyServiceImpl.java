/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baiwang.banktax.beans.AreaBean;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.dao.AreaBeanMapper;
import com.baiwang.banktax.dao.UserMapper;
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
	
	@Resource
    private UserMapper userDao;
	
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
	
	/**
	  * @author gkm
	  * @Description: 实名认证选择地区下一部(认证平台)
	  * @param @param id
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月30日 下午1:31:53
	  */
	@Transactional
	public AreaBean getVerifyType(User user,Integer id){
		
		if(!user.getIdcardStatus()){
			int su = dao.updateArea(user.getId(), id);//没有认证,更新user表的字段
			System.out.println("实名认证选择地区下一部 更新user表vrf_areaid字段,结果:"+su);
		}
		
		return dao.getVerifyType(id);
	}
	
	
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
	public int plat2(String corpName, String taxSn, String idcard, Long id){
		
		//TODO 接口认证
		
		return dao.updateUser(corpName, taxSn, idcard, id);
	}
	
	/**
	  * @author gkm
	  * @Description: 认证成功后 把User加入到session中
	  * @param @param id
	  * @param @return  
	  * @return Object  
	  * @throws
	  * @date 2015年11月30日 下午4:12:55
	  */
	public User selectById(Long id){
		return userDao.selectById(id);
	}
	
	
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
	public String getAreaFlag(Integer id, int vrfAreaid){
		int count = dao.getAreaFlag(id, vrfAreaid);
		if(count>0){
			return "success";
		}
		return "fail";
	}
	
	
	
}
