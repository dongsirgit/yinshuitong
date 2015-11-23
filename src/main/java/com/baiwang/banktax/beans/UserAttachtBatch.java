package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;

public class UserAttachtBatch implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userid;

    private Long applyId;

    private Date createdtime;

    private String batchNote;

    private Byte batchType;

    private String batchName;

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

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getBatchNote() {
        return batchNote;
    }

    public void setBatchNote(String batchNote) {
        this.batchNote = batchNote == null ? null : batchNote.trim();
    }

    public Byte getBatchType() {
        return batchType;
    }

    public void setBatchType(Byte batchType) {
        this.batchType = batchType;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName == null ? null : batchName.trim();
    }
}