/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import com.baiwang.banktax.beans.TaxReport;
import com.baiwang.banktax.beans.TaxReportWithBLOBs;

/**
  * @ClassName: ITaxReportService
  * @Description: TODO
  * @author Administrator
  * @date 2015年12月24日 下午12:01:37
  */
public interface ITaxReportService {

	 int deleteByPrimaryKey(Long id);

	    int insert(TaxReportWithBLOBs record);

	    int insertSelective(TaxReportWithBLOBs record);

	    TaxReportWithBLOBs selectByPrimaryKey(Long id);
	    
	    TaxReportWithBLOBs selectByUid(Long uid);

	    int updateByPrimaryKeySelective(TaxReportWithBLOBs record);

	    int updateByPrimaryKeyWithBLOBs(TaxReportWithBLOBs record);

	    int updateByPrimaryKey(TaxReport record);
}
