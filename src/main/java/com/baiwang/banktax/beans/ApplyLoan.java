package com.baiwang.banktax.beans;

import java.util.Date;

public class ApplyLoan {
    private Long id;

    private Long uid;

    private String serialNum;

    private Byte applyType;

    private Integer prodId;

    private Integer applyTerm;

    private Byte applyTermType;

    private Integer applyQuota;

    private String applyNote;

    private Date applyTime;

    private Short applyStatus;

    private String statusNote;

    private String contactName;

    private String telephone;

    private Boolean isRealcharge;

    private Byte apChargeterm;

    private Boolean apMarry;

    private String bank;

    private String apEducation;

    private String bankname;

    private String bankAccount;

    private Integer approveMuserid;

    private String loancard;

    private Integer warningSum;

    private Byte warningLater;

    private Byte warningEarly;

    private Integer preQuota;

    private Integer approveQuota;

    private Date loanDate;

    private Date loanDateEnd;

    private Date modifyTime;

    private Boolean isdel;

    private Date createdtime;

    private Boolean isPub;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public Byte getApplyType() {
        return applyType;
    }

    public void setApplyType(Byte applyType) {
        this.applyType = applyType;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getApplyTerm() {
        return applyTerm;
    }

    public void setApplyTerm(Integer applyTerm) {
        this.applyTerm = applyTerm;
    }

    public Byte getApplyTermType() {
        return applyTermType;
    }

    public void setApplyTermType(Byte applyTermType) {
        this.applyTermType = applyTermType;
    }

    public Integer getApplyQuota() {
        return applyQuota;
    }

    public void setApplyQuota(Integer applyQuota) {
        this.applyQuota = applyQuota;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote == null ? null : applyNote.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Short getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Short applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote == null ? null : statusNote.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Boolean getIsRealcharge() {
        return isRealcharge;
    }

    public void setIsRealcharge(Boolean isRealcharge) {
        this.isRealcharge = isRealcharge;
    }

    public Byte getApChargeterm() {
        return apChargeterm;
    }

    public void setApChargeterm(Byte apChargeterm) {
        this.apChargeterm = apChargeterm;
    }

    public Boolean getApMarry() {
        return apMarry;
    }

    public void setApMarry(Boolean apMarry) {
        this.apMarry = apMarry;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getApEducation() {
        return apEducation;
    }

    public void setApEducation(String apEducation) {
        this.apEducation = apEducation == null ? null : apEducation.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public Integer getApproveMuserid() {
        return approveMuserid;
    }

    public void setApproveMuserid(Integer approveMuserid) {
        this.approveMuserid = approveMuserid;
    }

    public String getLoancard() {
        return loancard;
    }

    public void setLoancard(String loancard) {
        this.loancard = loancard == null ? null : loancard.trim();
    }

    public Integer getWarningSum() {
        return warningSum;
    }

    public void setWarningSum(Integer warningSum) {
        this.warningSum = warningSum;
    }

    public Byte getWarningLater() {
        return warningLater;
    }

    public void setWarningLater(Byte warningLater) {
        this.warningLater = warningLater;
    }

    public Byte getWarningEarly() {
        return warningEarly;
    }

    public void setWarningEarly(Byte warningEarly) {
        this.warningEarly = warningEarly;
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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getLoanDateEnd() {
        return loanDateEnd;
    }

    public void setLoanDateEnd(Date loanDateEnd) {
        this.loanDateEnd = loanDateEnd;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Boolean getIsPub() {
        return isPub;
    }

    public void setIsPub(Boolean isPub) {
        this.isPub = isPub;
    }
}