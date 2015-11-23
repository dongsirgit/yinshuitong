package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
  * @ClassName: UserMessage
  * @Description: 用户站内短信
  * @author 张衡
  * @date 2015年7月31日 下午2:31:25
 */
public class UserMessage implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date createdtime;

    private Long uidTo;

    private Long uidFrom;

    private String content;

    private Boolean msgStatus;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Long getUidTo() {
        return uidTo;
    }

    public void setUidTo(Long uidTo) {
        this.uidTo = uidTo;
    }

    public Long getUidFrom() {
        return uidFrom;
    }

    public void setUidFrom(Long uidFrom) {
        this.uidFrom = uidFrom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Boolean msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}