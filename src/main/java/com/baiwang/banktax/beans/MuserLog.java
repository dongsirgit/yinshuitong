package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
  * @ClassName: MuserLog
  * @Description: 客服用户操作日志记录表
  * @author 张衡
  * @date 2015年7月31日 下午2:30:11
 */
public class MuserLog implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer muserid;

    private String info;

    private Date createdtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMuserid() {
        return muserid;
    }

    public void setMuserid(Integer muserid) {
        this.muserid = muserid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}