/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.model;

/**
  * @ClassName: ShortMessage
  * @Description: 短信实体类
  * @author zhaowei
  * @date 2015年8月4日 上午9:01:02
  */
public class ShortMessage {

	private String userid; //企业号，默认为空
	
	private String account; //接口账户
	
	private String password; //密码
	
	private String mobile; 
	
	private String content;
	
	private String template; //发送内容模板，不匹配无法发送
	
	private String sendTime; 
	
	private String postUrl; //接口地址
	
	public String getPostUrl() {
	
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
	
		this.postUrl = postUrl;
	}

	public String getUserid() {
	
		return userid;
	}

	public void setUserid(String userid) {
	
		this.userid = userid;
	}

	public String getAccount() {
	
		return account;
	}

	public void setAccount(String account) {
	
		this.account = account;
	}

	public String getPassword() {
	
		return password;
	}

	public void setPassword(String password) {
	
		this.password = password;
	}

	public String getMobile() {
	
		return mobile;
	}

	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}

	public String getContent() {
	
		return content;
	}

	public void setContent(String content) {
	
		this.content = content;
	}

	public String getSendTime() {
	
		return sendTime;
	}

	public void setSendTime(String sendTime) {
	
		this.sendTime = sendTime;
	}

	public String getTemplate() {
	
		return template;
	}

	public void setTemplate(String template) {
	
		this.template = template;
	}

}
