package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
  * @ClassName: CuserTax
  * @Description: 用户的税务数据表，基于数据形式与接口，待定
  * @author 张衡
  * @date 2015年7月31日 下午2:29:17
 */
public class CuserTax implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userid;

    private String receiptCode;

    private String receiptNum;

    private Date receiptTime;

    private String taxSnTo;

    private String taxSnFrom;

    private BigDecimal sumMoney;

    private BigDecimal sumTax;

    private Date addtime;

    private String datail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode == null ? null : receiptCode.trim();
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum == null ? null : receiptNum.trim();
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getTaxSnTo() {
        return taxSnTo;
    }

    public void setTaxSnTo(String taxSnTo) {
        this.taxSnTo = taxSnTo == null ? null : taxSnTo.trim();
    }

    public String getTaxSnFrom() {
        return taxSnFrom;
    }

    public void setTaxSnFrom(String taxSnFrom) {
        this.taxSnFrom = taxSnFrom == null ? null : taxSnFrom.trim();
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public BigDecimal getSumTax() {
        return sumTax;
    }

    public void setSumTax(BigDecimal sumTax) {
        this.sumTax = sumTax;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getDatail() {
        return datail;
    }

    public void setDatail(String datail) {
        this.datail = datail == null ? null : datail.trim();
    }
}