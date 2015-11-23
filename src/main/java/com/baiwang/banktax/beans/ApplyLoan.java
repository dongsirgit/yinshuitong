package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * 
  * @ClassName: ApplyLoan
  * @Description: 用户抵押信息表
  * @author 张衡
  * 修改 gkm  添加字段重新生成
  * @date 2015年8月3日 下午9:28:19
 */
public class ApplyLoan implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long uid;

    private Byte applyType;

    private Date applyTime;

    private Integer applyTerm;

    private Byte applyTermType;

    private Integer applyTermNum;

    private String apEducation;

    private Boolean apMarry;

    private Byte apChargeterm;

    private Boolean isRealcharge;

    private Integer applyQuota;

    private String applyNote;

    private String contactName;

    private String telephone;

    private String bank;

    private String bankname;

    private String bankAccount;

    private Integer prodLoanId;

    private Date modifyTime;

    private Integer serviceOrgId;

    private String loancard;

    private Byte approveStatus;

    private Date approveTime;

    private Integer approveMuserid;

    private Date loanDateEnd;

    private Integer warningSum;

    private Boolean hasPropertyright;

    private String approveNote;

    private String judgeNote;

    private Date judgeTime;

    private Integer judgeMuserid;

    private Byte warningLater;

    private Byte warningEarly;

    private Integer approveQuota;

    private Date loanDate;

    private Boolean loanGuaranty;

    private Boolean isPub;

    private Boolean isdel;

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

    public Byte getApplyType() {
        return applyType;
    }

    public void setApplyType(Byte applyType) {
        this.applyType = applyType;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

    public Integer getApplyTermNum() {
        return applyTermNum;
    }

    public void setApplyTermNum(Integer applyTermNum) {
        this.applyTermNum = applyTermNum;
    }

    public String getApEducation() {
        return apEducation;
    }

    public void setApEducation(String apEducation) {
        this.apEducation = apEducation == null ? null : apEducation.trim();
    }

    public Boolean getApMarry() {
        return apMarry;
    }

    public void setApMarry(Boolean apMarry) {
        this.apMarry = apMarry;
    }

    public Byte getApChargeterm() {
        return apChargeterm;
    }

    public void setApChargeterm(Byte apChargeterm) {
        this.apChargeterm = apChargeterm;
    }

    public Boolean getIsRealcharge() {
        return isRealcharge;
    }

    public void setIsRealcharge(Boolean isRealcharge) {
        this.isRealcharge = isRealcharge;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
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

    public Integer getProdLoanId() {
        return prodLoanId;
    }

    public void setProdLoanId(Integer prodLoanId) {
        this.prodLoanId = prodLoanId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getServiceOrgId() {
        return serviceOrgId;
    }

    public void setServiceOrgId(Integer serviceOrgId) {
        this.serviceOrgId = serviceOrgId;
    }

    public String getLoancard() {
        return loancard;
    }

    public void setLoancard(String loancard) {
        this.loancard = loancard == null ? null : loancard.trim();
    }

    public Byte getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Byte approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Integer getApproveMuserid() {
        return approveMuserid;
    }

    public void setApproveMuserid(Integer approveMuserid) {
        this.approveMuserid = approveMuserid;
    }

    public Date getLoanDateEnd() {
        return loanDateEnd;
    }

    public void setLoanDateEnd(Date loanDateEnd) {
        this.loanDateEnd = loanDateEnd;
    }

    public Integer getWarningSum() {
        return warningSum;
    }

    public void setWarningSum(Integer warningSum) {
        this.warningSum = warningSum;
    }

    public Boolean getHasPropertyright() {
        return hasPropertyright;
    }

    public void setHasPropertyright(Boolean hasPropertyright) {
        this.hasPropertyright = hasPropertyright;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote == null ? null : approveNote.trim();
    }

    public String getJudgeNote() {
        return judgeNote;
    }

    public void setJudgeNote(String judgeNote) {
        this.judgeNote = judgeNote == null ? null : judgeNote.trim();
    }

    public Date getJudgeTime() {
        return judgeTime;
    }

    public void setJudgeTime(Date judgeTime) {
        this.judgeTime = judgeTime;
    }

    public Integer getJudgeMuserid() {
        return judgeMuserid;
    }

    public void setJudgeMuserid(Integer judgeMuserid) {
        this.judgeMuserid = judgeMuserid;
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

    public Boolean getLoanGuaranty() {
        return loanGuaranty;
    }

    public void setLoanGuaranty(Boolean loanGuaranty) {
        this.loanGuaranty = loanGuaranty;
    }

    public Boolean getIsPub() {
        return isPub;
    }

    public void setIsPub(Boolean isPub) {
        this.isPub = isPub;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }
}