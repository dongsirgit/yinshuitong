/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.baiwang.banktax.model.ProductDetailBean;
import com.baiwang.banktax.model.ProductSynopsisBean;
import com.baiwang.banktax.services.iface.IProductService;
import com.baiwang.banktax.utils.StringUtils;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
  * @ClassName: ProductController
  * @Description: 产品列表
  * @author gkm
  * @date 2015年11月24日 下午6:33:56
  */
@Controller
//@RequestMapping("uses/product")
public class ProductController {

	private static final Log logger = LogFactory.getLog(ProductController.class);
	
	@Resource
	private IProductService service;
	
	/**
	 * 
	  * @author gkm
	  * @Description: 跳转列表页
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月25日 下午4:47:51
	 */
	@RequestMapping("/product/list")
	public String loanList() {
		return "order/productList";
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 加载产品列表
	  * @param @return  
	  * @return Map<String,Object>  
	  * @throws
	  * @date 2015年11月26日 上午11:39:52
	 */
	@RequestMapping("getproList")
	@ResponseBody
	public Map<String,Object> getproList(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<ProductSynopsisBean> list = service.getproList();
		map.put("list", list);
		return map;
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 跳转详情页 (不用)
	  * @param @param id
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月25日 下午5:57:05
	 */
	@RequestMapping("/product/detail2")
	public String detail2(String id) {
		if(StringUtils.isBlank(id)){
			logger.warn("----产品详情ProductController.detail()----id为null....");
			return "redirect:/product/list";
		}
		logger.info("-------proInfo:id="+id);
		try {
			String ii = new String(Base64.decode(id));
			System.out.println("base64转换:"+ii);
			
			Integer.parseInt(new String(Base64.decode(id)));
			
		} catch (Base64DecodingException e) {
			logger.error("----产品详情ProductController.detail()----id转换失败....id:"+id);
			e.printStackTrace();
			return "redirect:/product/list";
		}
		
		return "order/productDetail";
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 跳转详情页 
	  * @param @param id
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年11月25日 下午5:57:05
	 */
	@RequestMapping("/product/detail")
	public ModelAndView detail(Integer id) {
		if(null == id || id == 0){
			logger.error("----产品详情ProductController.detail()....id为null或0");
			return new ModelAndView("redirect:/product/list");
		}
		logger.info("----产品详情ProductController.detail()....id:"+id);
		ProductDetailBean detail = null;
		try {
			detail = service.getDetail(id);
			ModelAndView mv = new ModelAndView("order/productDetail").addObject("detail",detail);
			return mv;
		} catch (Exception e) {
			logger.error("----产品详情ProductController.detail()....异常");
			e.printStackTrace();
			return new ModelAndView("redirect:/product/list");
		}
		
	}
	
	/*@RequestMapping("/product/getDetail")
	@ResponseBody
	public Map getDetail(Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		ProductDetailBean detail;
		try {
			detail = service.getDetail(id);
			map.put("detail", detail);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return map;
	}*/
	
	
	
	/*//@RequestMapping("/users/identify")
	@RequestMapping("users/identify")
	public String identify() {
		return "order/identification";
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//产品详情维护
	@RequestMapping("/detail")
	public String detail() {
		return "order/m_detail";
	}
	@RequestMapping("/product/saveDetail")
	@ResponseBody
	public String saveDetail(ProductDetailBean bean) {
		int su = service.save(bean);
		System.out.println("succ:"+su);
		return "success";
	}
	//维护列表字段
	@RequestMapping("/prolist")
	public String manage_list() {
		return "order/m_list";
	}
	
	
	
	
	
	
	
	
	
}
