package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.TaxReport;
import com.baiwang.banktax.beans.TaxReportWithBLOBs;

public interface TaxReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaxReportWithBLOBs record);

    int insertSelective(TaxReportWithBLOBs record);

    TaxReportWithBLOBs selectByPrimaryKey(Long id);
    
    TaxReportWithBLOBs selectByUid(Long uid);

    int updateByPrimaryKeySelective(TaxReportWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TaxReportWithBLOBs record);

    int updateByPrimaryKey(TaxReport record);
}