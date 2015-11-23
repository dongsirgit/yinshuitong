/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
  * @ClassName: InviterAwardBean
  * @Description: 邀请者奖励
  * @author Administrator
  * @date 2015年10月12日 下午2:42:53
  */
public class InviterAwardBean implements Serializable{

	private static final long serialVersionUID = -4221660421818149770L;
	
	private String userName;
	
	private String approveQuota;
	
	private String award;
	
	private String awardStatus;

	public String getUserName() {
	
		return userName;
	}

	public void setUserName(String userName) {
	
		this.userName = userName.substring(0, 4)+"****";
	}

	public String getApproveQuota() {
	
		return approveQuota;
	}

	public void setApproveQuota(Double approveQuota) {
		DecimalFormat fmt = new DecimalFormat("#,###,###,##0.00");
		this.approveQuota = fmt.format(approveQuota)+"元";
	}

	public String getAward() {
	
		return award;
	}

	public void setAward(Double award) {
		DecimalFormat fmt = new DecimalFormat("#,###,###,##0.00");
		this.award = fmt.format(award)+"元";
	}

	public String getAwardStatus() {
	
		return awardStatus;
	}

	public void setAwardStatus(String awardStatus) {
	
		this.awardStatus = awardStatus;
	}
	
	
	
}
