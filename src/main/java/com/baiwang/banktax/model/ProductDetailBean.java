/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
  * @ClassName: ProductInfoBean
  * @Description: 产品详情(产品详情页)
  * @author gkm
  * @date 2015年11月25日 上午11:21:31
  */
public class ProductDetailBean implements Serializable{

	private static final long serialVersionUID = -974668586719625676L;
	private Integer id;
	private String relaBank;//关联银行
	private String logoUrl;//银行大图标
	private String pname;//产品名称
	private String ktdq;
	private String cpsm;
	private String cpys;
	private String dkdx;
	private String sbdx;
	private String sbtj;
	private String sbcl;
	private String sblc;
	private String dkfl;
	
	public Integer getId() {
	
		return id;
	}
	public void setId(Integer id) {
	
		this.id = id;
	}
	public String getRelaBank() {
	
		return relaBank;
	}
	public void setRelaBank(String relaBank) {
	
		this.relaBank = relaBank;
	}
	public String getLogoUrl() {
	
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
	
		this.logoUrl = logoUrl;
	}
	public String getPname() {
	
		return pname;
	}
	public void setPname(String pname) {
	
		this.pname = pname;
	}
	public String getKtdq() {
	
		return ktdq;
	}
	public void setKtdq(String ktdq) {
	
		this.ktdq = ktdq;
	}
	public String getCpsm() {
	
		return cpsm;
	}
	public void setCpsm(String cpsm) {
	
		this.cpsm = cpsm;
	}
	public String getCpys() {
	
		return cpys;
	}
	public void setCpys(String cpys) {
	
		this.cpys = cpys;
	}
	public String getDkdx() {
	
		return dkdx;
	}
	public void setDkdx(String dkdx) {
	
		this.dkdx = dkdx;
	}
	public String getSbdx() {
	
		return sbdx;
	}
	public void setSbdx(String sbdx) {
	
		this.sbdx = sbdx;
	}
	public String getSbtj() {
	
		return sbtj;
	}
	public void setSbtj(String sbtj) {
	
		this.sbtj = sbtj;
	}
	public String getSbcl() {
	
		return sbcl;
	}
	public void setSbcl(String sbcl) {
	
		this.sbcl = sbcl;
	}
	public String getSblc() {
	
		return sblc;
	}
	public void setSblc(String sblc) {
	
		this.sblc = sblc;
	}
	public String getDkfl() {
	
		return dkfl;
	}
	public void setDkfl(String dkfl) {
	
		this.dkfl = dkfl;
	}
	/**
	  * 创建一个新的实例 ProductDetailBean. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  * @param ktdq
	  * @param cpsm
	  * @param cpys
	  * @param dkdx
	  * @param sbdx
	  * @param sbtj
	  * @param sbcl
	  * @param sblc
	  * @param dkfl
	  */
	public ProductDetailBean(String ktdq, String cpsm, String cpys, String dkdx, String sbtj, String sbcl,
			String sblc, String dkfl) {
		super();
		this.ktdq = ktdq;
		this.cpsm = cpsm;
		this.cpys = cpys;
		this.dkdx = dkdx;
		this.sbtj = sbtj;
		this.sbcl = sbcl;
		this.sblc = sblc;
		this.dkfl = dkfl;
	}
	
	
	
	/**
	  * 创建一个新的实例 ProductDetailBean. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  */
	public ProductDetailBean() {
		super();
	}
	public static void main(String[] args) {
		ProductDetailBean b = new ProductDetailBean("深圳市、杭州市、江苏省", 
				"\"税银互动，携手共与；以税定信，服务微众。\"深圳建行携手深圳国税、微众税银共同推出无抵押、无担保小微企业在线融资产品，实时测算预授信额度，全流程在线办理，为您实现互联网金融的\"e\"路\"e\"贷！", 
				"（1）纳税记录良好，上一年度增值税加营业税额在20-200万之间；（2）销售情况良好，上一年度销售收入大于等于500万元；（3）所属行业非房地产、餐饮、娱乐业；（4）企业主信用记录良好，近1年内逾期或欠息在30天(含)以内的次数累计不超过6次，且不存在逾期或欠息60天(不含)以上的信用记录；（5）企业信用记录良好，无贷款逾期、欠息、垫款、无重大法律诉讼纠纷，无环保违规等实质不良记录", 
				"（1）以税定信，最高信用额度500万元；（2）全流程在线办理，纸质资料齐全后5个工作日内放款；（3）免抵押、免担保；（4）期限一年，随借随还；（5）按月付息，到期还本", 
				"第一步：注册登录并同意授权协议；第二步：填写贷款需求；第三步：提交申请；第四步：授信额度到账", 
				"申请人有效身份证件、企业或个体营业执照、企业或个体税务登记证", 
				"第一步：注册登录并同意授权协议；第二步：填写贷款需求",
				"分期期数 1 期 2 期 3 期 4 期 5 期 6 期 7 期 8 期 9 期 10 期 11 期 12 期;分期手续费率 1.25% 1.88% 2.5% 3.13% 3.75% 4.38% 5% 5.63% 6.25% 6.88% 7.5% 8.13%");
		
		System.out.println(JSON.toJSONString(b));
		//{"cpsm":"\"税银互动，携手共与；以税定信，服务微众。\"深圳建行携手深圳国税、微众税银共同推出无抵押、无担保小微企业在线融资产品，实时测算预授信额度，全流程在线办理，为您实现互联网金融的\"e\"路\"e\"贷！","cpys":"（1）纳税记录良好，上一年度增值税加营业税额在20-200万之间；（2）销售情况良好，上一年度销售收入大于等于500万元；（3）所属行业非房地产、餐饮、娱乐业；（4）企业主信用记录良好，近1年内逾期或欠息在30天(含)以内的次数累计不超过6次，且不存在逾期或欠息60天(不含)以上的信用记录；（5）企业信用记录良好，无贷款逾期、欠息、垫款、无重大法律诉讼纠纷，无环保违规等实质不良记录","dkdx":"（1）以税定信，最高信用额度500万元；（2）全流程在线办理，纸质资料齐全后5个工作日内放款；（3）免抵押、免担保；（4）期限一年，随借随还；（5）按月付息，到期还本","dkfl":"分期期数 1 期 2 期 3 期 4 期 5 期 6 期 7 期 8 期 9 期 10 期 11 期 12 期;分期手续费率 1.25% 1.88% 2.5% 3.13% 3.75% 4.38% 5% 5.63% 6.25% 6.88% 7.5% 8.13%","ktdq":"深圳市、杭州市、江苏省","sbcl":"申请人有效身份证件、企业或个体营业执照、企业或个体税务登记证","sblc":"第一步：注册登录并同意授权协议；第二步：填写贷款需求","sbtj":"第一步：注册登录并同意授权协议；第二步：填写贷款需求；第三步：提交申请；第四步：授信额度到账"}

		
	}
	
	
	
	
	
	
}
