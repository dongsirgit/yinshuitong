package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
  * @ClassName: WarningInfo
  * @Description: 产品的警告信息表
  * @author 张衡
  * @date 2015年7月31日 下午2:31:45
 */
public class WarningInfo implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long applyId;

    private Byte infoType;

    private String sourceWebsite;

    private String title;

    private String publishTime;

    private String sourceUrl;

    private Date fetchTime;

    private Integer muserid;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Byte getInfoType() {
        return infoType;
    }

    public void setInfoType(Byte infoType) {
        this.infoType = infoType;
    }

    public String getSourceWebsite() {
        return sourceWebsite;
    }

    public void setSourceWebsite(String sourceWebsite) {
        this.sourceWebsite = sourceWebsite == null ? null : sourceWebsite.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    public Date getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
    }

    public Integer getMuserid() {
        return muserid;
    }

    public void setMuserid(Integer muserid) {
        this.muserid = muserid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}