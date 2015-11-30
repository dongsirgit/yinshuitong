/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baiwang.banktax.beans.AreaBean;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.services.iface.IidentifyService;
import com.baiwang.banktax.utils.ConfigUtil;

/**
  * @ClassName: IdentyController
  * @Description: 认证
  * @author gkm
  * @date 2015年11月27日 下午2:56:59
  */
@Controller
@RequestMapping("users/identify")
public class IdentifyController {
	
	@Resource
	private IidentifyService service;
	
	/**
	 * 
	  * @author gkm
	  * @Description: 判断是否验证
	  * @param @return  
	  * @return ModelAndView  
	  * @throws
	  * @date 2015年11月30日 上午10:59:21
	 */
	@RequestMapping("identflag")
	@ResponseBody
	public boolean identflag(HttpSession session){
		User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
		//TODO  判断认证产品地区
		
		return user.getIdcardStatus();
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 进入认证页面
	  * @param @return  
	  * @return ModelAndView  
	  * @throws
	  * @date 2015年11月30日 上午11:02:30
	 */
	@RequestMapping("")
	public ModelAndView identify(){
		ArrayList<AreaBean> list = service.getProvince();
		return new ModelAndView("identify/identification").addObject("province", list);
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 根据province id获取城市
	  * @param @param id
	  * @param @return  
	  * @return Map<String,Object>  
	  * @throws
	  * @date 2015年11月30日 上午11:02:59
	 */
	@RequestMapping("getCity")
	@ResponseBody
	public Map<String, Object> identifyCity(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<AreaBean> list = service.getCity(id);
		
		map.put("list", list);
		return map;
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 下一个(认证平台)
	  * @param @param id
	  * @param @return  
	  * @return ModelAndView  
	  * @throws
	  * @date 2015年11月30日 上午11:03:35
	 */
	@RequestMapping("next")
	public ModelAndView identifyNext(Integer id){
		
		ArrayList<AreaBean> list = service.getProvince();
		
		//ModelAndView mv = new ModelAndView("identify/identifycation").addObject("province", list);
		return new ModelAndView("identify/identification").addObject("province", list);
	}
	
	
	
}
