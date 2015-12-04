/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baiwang.banktax.beans.Product;
import com.baiwang.banktax.dao.ProductMapper;
import com.baiwang.banktax.model.ProductDetailBean;
import com.baiwang.banktax.model.ProductSynopsisBean;
import com.baiwang.banktax.services.iface.IProductService;

/**
  * @ClassName: ProductServiceImpl
  * @Description: 产品ServiceImpl层
  * @author gkm
  * @date 2015年11月25日 上午10:00:06
  */
@Service
public class ProductServiceImpl implements IProductService {

	//private static final Log log = LogFactory.getLog(ProductServiceImpl.class);
	@Resource
	private ProductMapper dao;
	/**
	  * @author gkm
	  * @Description: 获取产品列表
	  * @param @return  
	  * @return List<ProductSynopsisBean>  
	  * @throws
	  * @date 2015年11月25日 上午11:47:52
	  */
	public List<ProductSynopsisBean> getproList(Integer id){
		return transf(dao.getproList(id));
	}
	/**
	 * 
	  * @author gkm
	  * @Description: 把Product的titleInfo转换成列表页需要的ProductSynopsisBean
	  * @param @param listp
	  * @param @return  
	  * @return List<ProductSynopsisBean>  
	  * @throws
	  * @date 2015年11月25日 下午1:43:48
	 */
	public List<ProductSynopsisBean> transf(List<Product> listp){
		
		//log.info("||||||||Product List:"+JSON.toJSONString(listp));
		
		if(null != listp && listp.size()>0){
			List<ProductSynopsisBean> list = new ArrayList<ProductSynopsisBean>();
			for(int i = 0; i < listp.size(); i++){
				String[] split = listp.get(i).getTitleInfo().split(";");
				if(null != split && split.length == 3){
					list.add(new ProductSynopsisBean(listp.get(i).getId(), listp.get(i).getPname(),
							listp.get(i).getRelaBank(), listp.get(i).getIcoUrl(), 
							split[0],split[1],split[2]));
				}else
					continue;
			}
			return list;
		}
		
		return null;
	}
	
	/**
	  * @author gkm
	  * @Description: 产品详情
	  * @param @param id
	  * @param @return  
	  * @return ProductDetailBean  
	  * @throws
	  * @date 2015年11月25日 下午6:14:34
	  */
	public ProductDetailBean getDetail(Integer id) throws Exception{
		Product product = dao.selectByPrimaryKey(id);
		
		//ProductDetailBean bean =trafDetail(product);
		if(null != product){
			System.out.println("=="+product.getDescript());
			ProductDetailBean bean = JSON.parseObject(product.getDescript() , ProductDetailBean.class);
			bean.setId(id);
			bean.setRelaBank(product.getRelaBank());
			bean.setLogoUrl(product.getLogoUrl());
			bean.setPname(product.getPname());
			return bean;
		}
		
		return null;
	}
	
	
	public int save(ProductDetailBean bean){
		String det = JSON.toJSONString(bean);
		//System.out.println("JSONtoString:"+det);
		return dao.save(det);
	}
}
