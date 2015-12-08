/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import java.util.Map;

/**
  * @ClassName: ITaxDataService
  * @Description: TODO
  * @author Administrator
  * @date 2015年12月3日 下午1:22:36
  */
public interface ITaxDataService {
	
	public Map<String,Object> reseiveComBasicData(Map<String, Object> map);
	public Map<String,Object> reseiveComTaxData(Map<String, Object> map);

}
