package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;

public class UserAttacht implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userid;

    private Long applyId;

    private Long batchId;

    private Date createdtime;

    private String attachNote;

    private Byte attachType;

    private String attachment;

    private String attachurl;

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

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getAttachNote() {
        return attachNote;
    }

    public void setAttachNote(String attachNote) {
        this.attachNote = attachNote == null ? null : attachNote.trim();
    }

    public Byte getAttachType() {
        return attachType;
    }

    public void setAttachType(Byte attachType) {
        this.attachType = attachType;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public String getAttachurl() {
        return attachurl;
    }

    public void setAttachurl(String attachurl) {
        this.attachurl = attachurl == null ? null : attachurl.trim();
    }
}