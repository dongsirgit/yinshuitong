/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import java.util.List;

import com.baiwang.banktax.model.ProductDetailBean;
import com.baiwang.banktax.model.ProductSynopsisBean;

/**
  * @ClassName: IProductService
  * @Description: 产品Service
  * @author gkm
  * @date 2015年11月25日 上午9:57:33
  */
public interface IProductService {

	/**
	  * @author gkm
	  * @Description: 获取产品列表
	  * @param @return  
	  * @return List<ProductSynopsisBean>  
	  * @throws
	  * @date 2015年11月25日 上午11:47:52
	  */
	List<ProductSynopsisBean> getproList(Integer id);

	/**
	  * @author gkm
	  * @Description: 产品详情
	  * @param @param id
	  * @param @return  
	  * @return ProductDetailBean  
	 * @throws Exception 
	  * @throws
	  * @date 2015年11月25日 下午6:14:34
	  */
	ProductDetailBean getDetail(Integer id) throws Exception;

	
	
	
	
	
	int save(ProductDetailBean bean);

	

}
