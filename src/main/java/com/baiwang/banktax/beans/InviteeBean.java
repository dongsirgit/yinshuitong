/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
  * @ClassName: InviterBean
  * @Description: 邀请的人bean
  * @author gkm
  * @date 2015年10月12日 上午11:45:27
  */
public class InviteeBean implements Serializable{
	
	private static final long serialVersionUID = -8206336896675494835L;

	private Long id;
	
	private String userName;
	
	private String regTime;
	
	private String sucLoan;
	//贷款金额（实际发放的）
	private String approveQuota;
	//贷款日期（终审通过时间）
	private String judgeTime;

	public String getApproveQuota() {
	
		return approveQuota;
	}

	public void setApproveQuota(Integer approveQuota) {
		DecimalFormat   fmt   =   new   DecimalFormat("##,###,###,###,##0.00");
		this.approveQuota =  fmt.format(approveQuota);
	}

	public String getJudgeTime() {
	
		return judgeTime;
	}

	public void setJudgeTime(Date judgeTime) {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		this.judgeTime = sf.format(judgeTime);;
	}

	public Long getId() {
	
		return id;
	}

	public void setId(Long id) {
	
		this.id = id;
	}

	public String getUserName() {
	
		return userName;
	}

	public void setUserName(String userName) {
		
		this.userName = userName.substring(0, 4)+"****";
	}

	public String getRegTime() {
	
		return regTime;
	}

	public void setRegTime(Date regTime) {
	
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.regTime = sf.format(regTime);;
	}

	public String getSucLoan() {
	
		return sucLoan;
	}

	public void setSucLoan(String sucLoan) {
	
		this.sucLoan = sucLoan;
	}
	
	
	
}
