/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baiwang.banktax.beans.AreaBean;
import com.baiwang.banktax.services.iface.IidentifyService;

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
	
	@RequestMapping("")
	public ModelAndView identify(){
		
		ArrayList<AreaBean> list = service.getProvince();
		
		//ModelAndView mv = new ModelAndView("identify/identifycation").addObject("province", list);
		return new ModelAndView("identify/identification").addObject("province", list);
	}
	
	/*@RequestMapping("getProvince")
	@ResponseBody
	public Map identifyProvince(){
		Map map = new HashMap();
		ArrayList<AreaBean> list = service.getProvince();
		
		map.put("list", list);
		return map;
	}*/
	@RequestMapping("getCity")
	@ResponseBody
	public Map<String, Object> identifyCity(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<AreaBean> list = service.getCity(id);
		
		map.put("list", list);
		return map;
	}
	
	
	@RequestMapping("next")
	public ModelAndView identifyNext(Integer id){
		
		ArrayList<AreaBean> list = service.getProvince();
		
		//ModelAndView mv = new ModelAndView("identify/identifycation").addObject("province", list);
		return new ModelAndView("identify/identification").addObject("province", list);
	}
	
	
	
}
