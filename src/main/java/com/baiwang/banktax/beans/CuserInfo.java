package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
  * @ClassName: CuserInfo
  * @Description: 需要审查或对比的用户个维度信息
  * @author 张衡
  * @date 2015年7月31日 下午2:28:53
 */
public class CuserInfo implements Serializable{
    
    private static final long serialVersionUID = 1L;

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