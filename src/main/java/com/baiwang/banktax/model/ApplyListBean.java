/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.model;

import java.util.Date;

/**
  * @ClassName: ApplyListBean
  * @Description: 申请列表bean
  * @author gkm
  * @date 2015年12月1日 下午5:02:56
  */
public class ApplyListBean {

	private Long id;
	
	private Integer applyQuota;
	
	private Date applyTime;
	
	private Integer prodId;
	
	private String pname;
	
	private String relaBank;
	
	private Short applyStatus;
	
	private Integer preQuota;
    
    private Integer approveQuota;

	public Long getId() {
	
		return id;
	}

	public void setId(Long id) {
	
		this.id = id;
	}

	public Integer getApplyQuota() {
	
		return applyQuota;
	}

	public void setApplyQuota(Integer applyQuota) {
	
		this.applyQuota = applyQuota;
	}

	public Date getApplyTime() {
	
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
	
		this.applyTime = applyTime;
	}

	public Integer getProdId() {
	
		return prodId;
	}

	public void setProdId(Integer prodId) {
	
		this.prodId = prodId;
	}

	public String getPname() {
	
		return pname;
	}

	public void setPname(String pname) {
	
		this.pname = pname;
	}

	public String getRelaBank() {
	
		return relaBank;
	}

	public void setRelaBank(String relaBank) {
	
		this.relaBank = relaBank;
	}

	public Short getApplyStatus() {
	
		return applyStatus;
	}

	public void setApplyStatus(Short applyStatus) {
	
		this.applyStatus = applyStatus;
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
	
    
    
    
	
}
