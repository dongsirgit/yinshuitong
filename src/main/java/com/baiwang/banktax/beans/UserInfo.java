package com.baiwang.banktax.beans;

import java.util.Date;

public class UserInfo {
    private Integer uid;

    private String businessLicence;

    private Byte businessLicenceVerify;

    private String orgCode;

    private Byte orgCodeVerify;

    private Date updatetime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence == null ? null : businessLicence.trim();
    }

    public Byte getBusinessLicenceVerify() {
        return businessLicenceVerify;
    }

    public void setBusinessLicenceVerify(Byte businessLicenceVerify) {
        this.businessLicenceVerify = businessLicenceVerify;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Byte getOrgCodeVerify() {
        return orgCodeVerify;
    }

    public void setOrgCodeVerify(Byte orgCodeVerify) {
        this.orgCodeVerify = orgCodeVerify;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}