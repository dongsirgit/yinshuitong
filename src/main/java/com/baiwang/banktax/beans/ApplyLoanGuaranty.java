package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
  * @ClassName: ApplyLoanGuaranty
  * @Description: 贷款抵押信息
  * @author gkm
  * @date 2015年8月5日 下午4:14:12
 */
public class ApplyLoanGuaranty implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer applyLoanId;

    private String descript;

    private Integer guarantyValue;

    private String guaranty;

    private Byte guarantyType;

    private Date createdtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getApplyLoanId() {
        return applyLoanId;
    }

    public void setApplyLoanId(Integer applyLoanId) {
        this.applyLoanId = applyLoanId;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    public Integer getGuarantyValue() {
        return guarantyValue;
    }

    public void setGuarantyValue(Integer guarantyValue) {
        this.guarantyValue = guarantyValue;
    }

    public String getGuaranty() {
        return guaranty;
    }

    public void setGuaranty(String guaranty) {
        this.guaranty = guaranty == null ? null : guaranty.trim();
    }

    public Byte getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(Byte guarantyType) {
        this.guarantyType = guarantyType;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}