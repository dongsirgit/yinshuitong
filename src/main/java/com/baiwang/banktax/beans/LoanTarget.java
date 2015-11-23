package com.baiwang.banktax.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
  * @ClassName: LoanTarget
  * @Description: 贷款产品潜在目标
  * @author 张衡
  * @date 2015年7月31日 下午2:29:37
 */
public class LoanTarget implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userid;

    private Integer productId;

    private Integer score;

    private Date createdtime;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}