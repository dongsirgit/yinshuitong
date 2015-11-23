package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
  * @ClassName: ProductLoan
  * @Description: 根据产品设计增加产品类型
  * @modifyby Yinhua
  * @date 2015年8月6日 上午11:08:15
 */
public class ProductLoan implements Serializable{
   
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String pname;
    
    private String relaBank;

    private Byte status;
    
    private Byte loanType;

	private String descript;

    private String loanRateHigh;

    private String loanRateLow;

    private String loanRate;

    private Integer muserid;

    private Integer verifyMuserid;

    private Date createdtime;

    private Date modifyTime;

    private Date verifyTime;

    private String contact;

    private Integer loanLimitLow;

    private Integer loanLimitTop;

    private Integer clientSum;

    private Integer sortValue;

    private Byte needguaranty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getRelaBank() {
        return relaBank;
    }

    public void setRelaBank(String relaBank) {
        this.relaBank = relaBank == null ? null : relaBank.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

	public Byte getLoanType() {
		return loanType;
	}

	public void setLoanType(Byte loanType) {
		this.loanType = loanType;
	}

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    public String getLoanRateHigh() {
        return loanRateHigh;
    }

    public void setLoanRateHigh(String loanRateHigh) {
        this.loanRateHigh = loanRateHigh == null ? null : loanRateHigh.trim();
    }

    public String getLoanRateLow() {
        return loanRateLow;
    }

    public void setLoanRateLow(String loanRateLow) {
        this.loanRateLow = loanRateLow == null ? null : loanRateLow.trim();
    }

    public String getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(String loanRate) {
        this.loanRate = loanRate == null ? null : loanRate.trim();
    }

    public Integer getMuserid() {
        return muserid;
    }

    public void setMuserid(Integer muserid) {
        this.muserid = muserid;
    }

    public Integer getVerifyMuserid() {
        return verifyMuserid;
    }

    public void setVerifyMuserid(Integer verifyMuserid) {
        this.verifyMuserid = verifyMuserid;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Integer getLoanLimitLow() {
        return loanLimitLow;
    }

    public void setLoanLimitLow(Integer loanLimitLow) {
        this.loanLimitLow = loanLimitLow;
    }

    public Integer getLoanLimitTop() {
        return loanLimitTop;
    }

    public void setLoanLimitTop(Integer loanLimitTop) {
        this.loanLimitTop = loanLimitTop;
    }

    public Integer getClientSum() {
        return clientSum;
    }

    public void setClientSum(Integer clientSum) {
        this.clientSum = clientSum;
    }

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }

    public Byte getNeedguaranty() {
        return needguaranty;
    }

    public void setNeedguaranty(Byte needguaranty) {
        this.needguaranty = needguaranty;
    }
}