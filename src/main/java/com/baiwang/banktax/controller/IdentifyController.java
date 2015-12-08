/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baiwang.banktax.beans.AreaBean;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.services.iface.IidentifyService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.StringUtils;

/**
  * @ClassName: IdentyController
  * @Description: 认证
  * @author gkm
  * @date 2015年11月27日 下午2:56:59
  */
@Controller
@RequestMapping("users/identify")
public class IdentifyController {
	
	private static final Log logger = LogFactory.getLog(IdentifyController.class);
	
	@Resource
	private IidentifyService service;
	
	/**
	 * 
	  * @author gkm
	  * @Description: 产品申请   判断是否验证
	  * @param @param id 产品id
	  * @param @param session
	  * @param @return  
	  * @return Map<String,Object>  
	  * @throws
	  * @date 2015年11月30日 上午10:59:21
	 */
	@RequestMapping("/identflag")
	@ResponseBody
	public Map<String, Object> identflag(Integer id, HttpSession session){
		
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("产品申请--------申请产品id:"+id);
		User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
		//TODO  判断认证产品地区
		Byte TaxVerify = user.getTaxVerify();
		map.put("TaxVerify", TaxVerify);//是否认证
		if(TaxVerify == 4){//认证通过
			//判断产品地区和认证开通地区
			String area = service.getAreaFlag(id, user.getVrfAreaid());
			map.put("areaflag", area);
			
			//判断有无申请
			int appflag = service.queryApplyFlag(id, user.getId());
			map.put("appflag", appflag);
			
		}
		
		return map;
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
	@RequestMapping("/getCity")
	@ResponseBody
	public Map<String, Object> identifyCity(Integer id, HttpSession session){
		User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
		logger.info("实名认证  加载城市----用户id:" + user.getId()+"------选择的省份id:"+id);
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<AreaBean> list = service.getCity(id);
		
		map.put("list", list);
		return map;
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 实名认证选择地区下一部(认证平台)
	  * @param @param id
	  * @param @return  
	  * @return ModelAndView  
	  * @throws
	  * @date 2015年11月30日 上午11:03:35
	 */
	@RequestMapping("/next")
	public ModelAndView identifyNext(Integer id, String verifyUrl, HttpSession session){
		User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
		logger.info("实名认证 选择省份 下一步----用户id:" + user.getId()+"------选择的城市id:"+id);
		
		AreaBean area = service.getVerifyType(user, id);
		logger.info("实名认证 选择省份 下一步----用户id:" + user.getId()+"------认证类型:"+area.getVerifyType());
		if("0".equals(area.getVerifyType())){
			return new ModelAndView("identify/identify_platform1").addObject("province", area.getAname())
					.addObject("id", session.getId()).addObject("verifyUrl", verifyUrl);
		}else if("1".equals(area.getVerifyType())){
			return new ModelAndView("identify/identify_platform2");
		}else if("2".equals(area.getVerifyType())){
			return new ModelAndView("identify/identify_platform3");
		}
		
		return new ModelAndView("identify/identify_platform1");
	}
	
	
	
	/**
	 * 
	 * @author gkm
	 * @Description: 税局认证 成功跳转
	 * @param @return  
	 * @return String  
	 * @throws
	 * @date 2015年11月30日 下午3:33:51
	 */
	@RequestMapping("/taxResult")
	@ResponseBody
	public Map<String, Object> taxResult(HttpSession session){
		User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
		logger.info("---税局认证结果----用户id为:"+user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "-1";
		/*if(4 == user.getTaxVerify()){//认证过
			map.put("result", result);
			return map;
		}*/
		//TODO 接收税局数据,放入session
		result = "5588";
		
		map.put("result", result);//税局返回结果
		int success = service.plat2("税局公司", "9999991111111", "11111196969111111", user.getId());//更新企业信息
		map.put("success", success);
		
		session.setAttribute(ConfigUtil.getLoginedUserStr(), service.selectById(user.getId()));
		logger.info("---税局认证结果--用户id为:"+user.getId()+"---结果:"+result+",更新用户数据结果:"+success);
		return map;
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 税局认证 成功跳转
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月30日 下午3:33:51
	 */
	@RequestMapping("/success")
	public String success(){
		return "identify/identify_success";
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 平台2的认证
	  * @param @param corpName
	  * @param @param taxSn
	  * @param @param idcard
	  * @param @param session
	  * @param @return  
	  * @return Map  
	  * @throws
	  * @date 2015年11月30日 下午3:43:54
	 */
	@RequestMapping("plat2")
	@ResponseBody
	public Map<String, Object> plat2(String corpName, String taxSn, String idcard, HttpSession session){
		User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
		logger.info("实名认证 平台2 去认证----用户id:" + user.getId()+"--企业名称:"+corpName+",纳税号:"+taxSn+",法人身份证号:"+idcard);
		//TODO　平台2去认证
		
		Map<String, Object> map = new HashMap<String, Object>();
		int success = service.plat2(corpName, taxSn, idcard, user.getId());
		
		map.put("success", success);
		logger.info("实名认证 平台2去认证----用户id:" + user.getId()+",认证结果:"+success);
		session.setAttribute(ConfigUtil.getLoginedUserStr(), service.selectById(user.getId()));
		return map;
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 平台2的认证
	  * @param @param corpName
	  * @param @param taxSn
	  * @param @param idcard
	  * @param @param session
	  * @param @return  
	  * @return Map  
	  * @throws
	  * @date 2015年11月30日 下午3:43:54
	 */
	@RequestMapping("plat3")
	@ResponseBody
	public Map<String, Object> plat3(String corpName, String taxSn, String code, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
		String imageCode = (String) session.getAttribute("imageCode");
		logger.info("实名认证 平台3 去认证----用户id:" + user.getId()+"--企业名称:"+corpName+",纳税号:"+taxSn);
		int success = 0;
        if (!"".equals(StringUtils.getString(imageCode))) {//校验验证码
        	if(StringUtils.getString(code).equals(imageCode)){//校验验证码
        		//TODO 平台3 认证
        		if(null != user.getTaxVerify() && user.getTaxVerify()>0 && user.getTaxVerify()<4){//认证中
        			success = -3;
        		}else
        			success = service.plat3(corpName, taxSn, "333333000000", user.getId());
	        		session.setAttribute(ConfigUtil.getLoginedUserStr(), service.selectById(user.getId()));
        	}else{
        		success = -2;//验证码错误
        	}
        }else{
        	success = -1;
        }
        logger.info("实名认证 平台3 去认证----用户id:" + user.getId()+",认证结果:"+success);
		map.put("success", success);
		return map;
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 平台3认证 成功跳转
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月30日 下午3:33:51
	 */
	@RequestMapping("/succes")
	public String success3(){
		return "identify/identify_success3";
	}
	/**
	 * 
	  * @author ldm
	  * @Description: 跳转数据授权协议
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月30日 下午3:33:51
	 */
	@RequestMapping("/toProtocol")
	public String toProtocol(){
		return "identify/protocol";
	}
	
	
	
	
}
