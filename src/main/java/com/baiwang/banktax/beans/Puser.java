package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;

public class Puser implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private String userPass;

    private String payPass;

    private String corpName;

    private String logopath;

    private String mail;

    private Boolean mailStatus;

    private String mailLink;

    private Long inviteUid;

    private Boolean isvip;

    private Boolean islock;

    private String lockMess;

    private Integer lockMuserid;

    private String idcard;

    private String idcardZ;

    private String idcardF;

    private String realname;

    private String phonenum;

    private Boolean phoneStatus;

    private Boolean idcardStatus;

    private String qqRelation;

    private String address;

    private Boolean videoStatus;

    private Boolean sceneStatuse;

    private String weiboRelation;

    private Date lastLogTime;

    private String lastLogIp;

    private Date regTime;

    private String regIp;

    private Date updateTime;

    private String updateIp;

    private String remark;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getPayPass() {
        return payPass;
    }

    public void setPayPass(String payPass) {
        this.payPass = payPass == null ? null : payPass.trim();
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName == null ? null : corpName.trim();
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath == null ? null : logopath.trim();
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

    public Long getInviteUid() {
        return inviteUid;
    }

    public void setInviteUid(Long inviteUid) {
        this.inviteUid = inviteUid;
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

    public String getIdcardZ() {
        return idcardZ;
    }

    public void setIdcardZ(String idcardZ) {
        this.idcardZ = idcardZ == null ? null : idcardZ.trim();
    }

    public String getIdcardF() {
        return idcardF;
    }

    public void setIdcardF(String idcardF) {
        this.idcardF = idcardF == null ? null : idcardF.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
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

    public String getWeiboRelation() {
        return weiboRelation;
    }

    public void setWeiboRelation(String weiboRelation) {
        this.weiboRelation = weiboRelation == null ? null : weiboRelation.trim();
    }

    public Date getLastLogTime() {
        return lastLogTime;
    }

    public void setLastLogTime(Date lastLogTime) {
        this.lastLogTime = lastLogTime;
    }

    public String getLastLogIp() {
        return lastLogIp;
    }

    public void setLastLogIp(String lastLogIp) {
        this.lastLogIp = lastLogIp == null ? null : lastLogIp.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp == null ? null : updateIp.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}