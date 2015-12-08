/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import java.util.Map;

import com.baiwang.banktax.services.iface.ITaxDataService;

/**
  * @ClassName: ITaxDataServiceImpl
  * @Description: 税务数据模拟接口
  * @author Administrator
  * @date 2015年12月3日 下午1:31:26
  */
public class ITaxDataServiceImpl implements ITaxDataService {

	/**
	  * <p>Title: reseiveComBasicData</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.baiwang.banktax.services.iface.ITaxDataService#reseiveComBasicData()
	  */
	@Override
	public Map<String, Object> reseiveComBasicData(Map<String, Object> map) {
		String taxid = (String) map.get("taxid");//纳税识别号
		if("123456789012345678".equals(taxid)){
			
		}

		return null;

	}

	/**
	  * <p>Title: reseiveComTaxData</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.baiwang.banktax.services.iface.ITaxDataService#reseiveComTaxData()
	  */
	@Override
	public Map<String, Object> reseiveComTaxData(Map<String, Object> map) {

		return null;

	}

}
