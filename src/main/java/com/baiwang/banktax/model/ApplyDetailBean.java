/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baiwang.banktax.beans.UserAttacht;
import com.baiwang.banktax.utils.NumberToCN;

/**
  * @ClassName: ApplyDetailBean
  * @Description: 申请详情
  * @author gkm
  * @date 2015年12月2日 下午2:15:24
  */
public class ApplyDetailBean {
 
	private Long id;
	
	private Short applyStatus;
	private Date applyTime;
	private Integer preQuota;
	private Integer approveQuota;
	private String statusNote;
	private Integer prodId;
	private String relaBank;
	private String pname;
	private String corpName;
	private String taxSn;
	private Integer applyQuota;
	private String applyQuotaDX;//大写
	private Integer applyTerm;
	private String contactName;
	private String telephone;
	private String applyNote;
	
	private List<UserAttacht> attaList;
	
    
	public Long getId() {
	
		return id;
	}
	public void setId(Long id) {
	
		this.id = id;
	}
	public Short getApplyStatus() {
	
		return applyStatus;
	}
	public void setApplyStatus(Short applyStatus) {
	
		this.applyStatus = applyStatus;
	}
	public Date getApplyTime() {
	
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
	
		this.applyTime = applyTime;
	}
	public Integer getPreQuota() {
	
		return preQuota;
	}
	public void setPreQuota(Integer preQuota) {
	
		this.preQuota = preQuota;
	}
	public Integer getApproveQuota() {
	
		return approveQuota;
	}
	public void setApproveQuota(Integer approveQuota) {
	
		this.approveQuota = approveQuota;
	}
	public String getStatusNote() {
	
		return statusNote;
	}
	public void setStatusNote(String statusNote) {
	
		this.statusNote = statusNote;
	}
	public Integer getProdId() {
	
		return prodId;
	}
	public void setProdId(Integer prodId) {
	
		this.prodId = prodId;
	}
	public String getRelaBank() {
	
		return relaBank;
	}
	public void setRelaBank(String relaBank) {
	
		this.relaBank = relaBank;
	}
	public String getPname() {
	
		return pname;
	}
	public void setPname(String pname) {
	
		this.pname = pname;
	}
	public String getCorpName() {
	
		return corpName;
	}
	public void setCorpName(String corpName) {
	
		this.corpName = corpName;
	}
	public String getTaxSn() {
	
		return taxSn;
	}
	public void setTaxSn(String taxSn) {
	
		this.taxSn = taxSn;
	}
	public Integer getApplyQuota() {
	
		return applyQuota;
	}
	public void setApplyQuota(Integer applyQuota) {
	
		this.applyQuota = applyQuota;
		
		BigDecimal numberOfMoney = new BigDecimal(applyQuota*10000);
		String applyQuotaDX = NumberToCN.number2CNMontrayUnit(numberOfMoney);
		this.applyQuotaDX = applyQuotaDX;
	}
	public String getApplyQuotaDX() {
		
		return applyQuotaDX;
	}
	/*public void setApplyQuotaDX(String applyQuotaDX) {
	
		this.applyQuotaDX = applyQuotaDX;
	}*/
	
	public Integer getApplyTerm() {
	
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
	
		this.applyTerm = applyTerm;
	}
	public String getContactName() {
	
		return contactName;
	}
	public void setContactName(String contactName) {
	
		this.contactName = contactName;
	}
	public String getTelephone() {
	
		return telephone;
	}
	public void setTelephone(String telephone) {
	
		this.telephone = telephone;
	}
	public String getApplyNote() {
	
		return applyNote;
	}
	public void setApplyNote(String applyNote) {
	
		this.applyNote = applyNote;
	}
	public List<UserAttacht> getAttaList() {
	
		return attaList;
	}
	public void setAttaList(List<UserAttacht> attaList) {
	
		this.attaList = attaList;
	}
	
	
	
	
	
	
	
	
    
	
	
}
