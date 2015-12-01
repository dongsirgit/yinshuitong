/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.model;

import java.io.Serializable;

/**
  * @ClassName: ProductSynopsisBean
  * @Description: 产品简介(产品列表页)
  * @author gkm
  * @date 2015年11月25日 上午11:31:18
  */
public class ProductSynopsisBean implements Serializable{

	private static final long serialVersionUID = 4608637254819939119L;
	private Integer id;
	private String pname;//产品名称
	private String relaBank;//关联银行
	private String icoUrl;//银行小图标
	private String sxed;//授信额度
	private String ckll;//参考利率
	private String cptd;//产品特点
	
	public Integer getId() {
	
		return id;
	}
	public void setId(Integer id) {
	
		this.id = id;
	}
	public String getPname() {
	
		return pname;
	}
	public void setPname(String pname) {
	
		this.pname = pname;
	}
	public String getRelaBank() {
	
		return relaBank;
	}
	public void setRelaBank(String relaBank) {
	
		this.relaBank = relaBank;
	}
	public String getIcoUrl() {
	
		return icoUrl;
	}
	public void setIcoUrl(String icoUrl) {
	
		this.icoUrl = icoUrl;
	}
	public String getSxed() {
	
		return sxed;
	}
	public void setSxed(String sxed) {
	
		this.sxed = sxed;
	}
	public String getCkll() {
	
		return ckll;
	}
	public void setCkll(String ckll) {
	
		this.ckll = ckll;
	}
	public String getCptd() {
	
		return cptd;
	}
	public void setCptd(String cptd) {
	
		this.cptd = cptd;
	}
	/**
	  * 创建一个新的实例 ProductSynopsisBean. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  * @param id
	  * @param pname
	  * @param relaBank
	  * @param icoUrl
	  * @param sxed
	  * @param ckll
	  * @param cptd
	  */
	public ProductSynopsisBean(Integer id, String pname, String relaBank, String icoUrl, String sxed, String ckll,
			String cptd) {
		super();
		this.id = id;
		this.pname = pname;
		this.relaBank = relaBank;
		this.icoUrl = icoUrl;
		this.sxed = sxed;
		this.ckll = ckll;
		this.cptd = cptd;
	}
	
	/**
	  * 创建一个新的实例 ProductSynopsisBean. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  */
	public ProductSynopsisBean() {
		super();
	}
	
	
	
	
	
	
}
