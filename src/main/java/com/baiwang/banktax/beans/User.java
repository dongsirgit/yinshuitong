package com.baiwang.banktax.beans;

import java.util.Date;

public class User {
    private Long id;

    private String mobilePhone;

    private String userName;

    private String userPass;

    private Byte userType;

    private String corpName;

    private String taxSn;
    
    private String licenseRegnum;

	private String logopath;

    private String apName;

    private String mail;

    private Boolean mailStatus;

    private String mailLink;

    private Boolean isvip;

    private Boolean islock;

    private String lockMess;

    private Integer lockMuserid;

    private String idcard;

    private String idcardFront;

    private String idcardBack;

    private Boolean phoneStatus;

    private Boolean idcardStatus;

    private String qqRelation;

    private String address;

    private Boolean videoStatus;

    private Boolean sceneStatuse;

    private Byte taxVerify;

    private String taxUsername;

    private String taxPass;

    private Date lastLogTime;

    private Date updateTime;

    private Date regTime;

    private String lastLogIp;

    private String regIp;

    private String updateIp;

    private String areaClass;

    private String industryClass;

    private Byte taxClass;

    private Long inviteUid;

    private String remark;

    private int vrfAreaid;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobilePhone() {
    
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
    
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName == null ? null : corpName.trim();
    }

    public String getTaxSn() {
        return taxSn;
    }

    public String getLicenseRegnum() {
	
		return licenseRegnum;
	}

	public void setLicenseRegnum(String licenseRegnum) {
	
		this.licenseRegnum = licenseRegnum;
	}

    public void setTaxSn(String taxSn) {
        this.taxSn = taxSn == null ? null : taxSn.trim();
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath == null ? null : logopath.trim();
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName == null ? null : apName.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public Boolean getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(Boolean mailStatus) {
        this.mailStatus = mailStatus;
    }

    public String getMailLink() {
        return mailLink;
    }

    public void setMailLink(String mailLink) {
        this.mailLink = mailLink == null ? null : mailLink.trim();
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public String getLockMess() {
        return lockMess;
    }

    public void setLockMess(String lockMess) {
        this.lockMess = lockMess == null ? null : lockMess.trim();
    }

    public Integer getLockMuserid() {
        return lockMuserid;
    }

    public void setLockMuserid(Integer lockMuserid) {
        this.lockMuserid = lockMuserid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIdcardFront() {
        return idcardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront == null ? null : idcardFront.trim();
    }

    public String getIdcardBack() {
        return idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack == null ? null : idcardBack.trim();
    }

    public Boolean getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(Boolean phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public Boolean getIdcardStatus() {
        return idcardStatus;
    }

    public void setIdcardStatus(Boolean idcardStatus) {
        this.idcardStatus = idcardStatus;
    }

    public String getQqRelation() {
        return qqRelation;
    }

    public void setQqRelation(String qqRelation) {
        this.qqRelation = qqRelation == null ? null : qqRelation.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(Boolean videoStatus) {
        this.videoStatus = videoStatus;
    }

    public Boolean getSceneStatuse() {
        return sceneStatuse;
    }

    public void setSceneStatuse(Boolean sceneStatuse) {
        this.sceneStatuse = sceneStatuse;
    }

    public Byte getTaxVerify() {
        return taxVerify;
    }

    public void setTaxVerify(Byte taxVerify) {
        this.taxVerify = taxVerify;
    }

    public String getTaxUsername() {
        return taxUsername;
    }

    public void setTaxUsername(String taxUsername) {
        this.taxUsername = taxUsername == null ? null : taxUsername.trim();
    }

    public String getTaxPass() {
        return taxPass;
    }

    public void setTaxPass(String taxPass) {
        this.taxPass = taxPass == null ? null : taxPass.trim();
    }

    public Date getLastLogTime() {
        return lastLogTime;
    }

    public void setLastLogTime(Date lastLogTime) {
        this.lastLogTime = lastLogTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getLastLogIp() {
        return lastLogIp;
    }

    public void setLastLogIp(String lastLogIp) {
        this.lastLogIp = lastLogIp == null ? null : lastLogIp.trim();
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp == null ? null : updateIp.trim();
    }

    public String getAreaClass() {
        return areaClass;
    }

    public void setAreaClass(String areaClass) {
        this.areaClass = areaClass == null ? null : areaClass.trim();
    }

    public String getIndustryClass() {
        return industryClass;
    }

    public void setIndustryClass(String industryClass) {
        this.industryClass = industryClass == null ? null : industryClass.trim();
    }

    public Byte getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(Byte taxClass) {
        this.taxClass = taxClass;
    }

    public Long getInviteUid() {
        return inviteUid;
    }

    public void setInviteUid(Long inviteUid) {
        this.inviteUid = inviteUid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public int getVrfAreaid() {
    
        return vrfAreaid;
    }

    public void setVrfAreaid(int vrfAreaid) {
    
        this.vrfAreaid = vrfAreaid;
    }
}