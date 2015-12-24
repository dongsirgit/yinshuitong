/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.TaxReport;
import com.baiwang.banktax.beans.TaxReportWithBLOBs;
import com.baiwang.banktax.dao.TaxReportMapper;
import com.baiwang.banktax.services.iface.ITaxReportService;

/**
  * @ClassName: ITaxReportServiceImpl
  * @Description: TODO
  * @author Administrator
  * @date 2015年12月24日 下午12:56:55
  */
@Service
public class ITaxReportServiceImpl implements ITaxReportService {
	
	@Resource
	TaxReportMapper dao ;

	public int deleteByPrimaryKey(Long id) {

		return 0;

	}

	@Override
	public int insert(TaxReportWithBLOBs record) {

		return 0;

	}

	@Override
	public int insertSelective(TaxReportWithBLOBs record) {

		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public TaxReportWithBLOBs selectByPrimaryKey(Long id) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public int updateByPrimaryKeySelective(TaxReportWithBLOBs record) {

		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(TaxReportWithBLOBs record) {

		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public int updateByPrimaryKey(TaxReport record) {

		// TODO Auto-generated method stub
		return 0;

	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param uid
	  * @param @return  
	  * @throws
	  * @date 2015年12月24日 下午1:06:26
	  */
	@Override
	public TaxReportWithBLOBs selectByUid(Long uid) {
		return dao.selectByUid(uid);
	}

}
