package com.baiwang.banktax.beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
  * @ClassName: CuserRebate
  * @Description: 邀请返利表
  * @author gkm
  * @date 2015年10月8日 下午5:42:50
 */
public class CuserRebate {
    private Long applyId;

    private Long inviterId;

    private Long uid;

    private Integer muserid;

    private BigDecimal rebate;

    private Byte rebateStatus;

    private Date rebateTime;

    private Date createdtime;

    private String remark;

    private Boolean isdel;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getMuserid() {
        return muserid;
    }

    public void setMuserid(Integer muserid) {
        this.muserid = muserid;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public Byte getRebateStatus() {
        return rebateStatus;
    }

    public void setRebateStatus(Byte rebateStatus) {
        this.rebateStatus = rebateStatus;
    }

    public Date getRebateTime() {
        return rebateTime;
    }

    public void setRebateTime(Date rebateTime) {
        this.rebateTime = rebateTime;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }
}