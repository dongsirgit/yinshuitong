/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
  * @ClassName: SenderMsg
  * @Description: 面向短信接口封装方法
  * @author zhaowei
  * @date 2015年9月30日 上午9:44:26
  */
public class MsgSender {

	private static final Log logger = LogFactory.getLog(MsgSender.class);
	
	/***
	 * 
	  * @author zhaowei
	  * @Description: 短信发送接口
	  * @param @param userid 企业ID，为空不需要
	  * @param @param account 用户帐号
	  * @param @param password 用户账号对应的密码  
	  * @param @param mobile 多个号码之间用半角逗号隔开
	  * @param @param content 短信的内容，内容需要UTF-8编码
	  * @param @param postUrl 接口地址
	  * @param @param sendTime 为空表示立即发送，定时发送格式2010-10-24 09:08:10
	  * @return String  
	  * @throws MalformedURLException 
	  * @date 2015年9月30日 上午10:18:10
	 */
	public static String Sms(String userid,String account,String password,String mobile,String content,String postUrl,String sendTime) throws MalformedURLException{
		
		if(null ==postUrl||"".equals(postUrl)){
			logger.error("接口地址不能为空!");
			throw new java.net.MalformedURLException("接口地址不能为空!");
		}
		StringBuffer postData = new StringBuffer();
		postData.append("&userid="+userid+"&account="+account+"&password="+password+"&mobile="+mobile+"&content="+content+"&sendTime="+sendTime);
		
		String result = "";
		
		BufferedReader in =null;
		try {
			// 发送POST请求
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(postData.toString());
			out.flush();
			out.close();

			// 获取响应状态
			if (conn.getResponseCode() == 200) {
				logger.info(conn.getResponseCode());
				// 获取响应内容体
				String line ="";
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				while ((line = in.readLine()) != null) {
					result += line;
				}
				logger.info("短信接口返回信息："+"\n"+result);
			}
			
		} catch (IOException e) {
			e.printStackTrace(System.out);
			logger.error("发送短信发生异常" +"\n"+ e);
		}finally {
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					
				logger.error("流关闭失败:"+"\n"+e.getMessage());
					
				}
			}
		}
		return result;
		
	}
	
	public static void  main(String[] args) throws MalformedURLException{
		System.out.println(Sms("", "lcs8836", "152468", "13718099648", "%E6%82%A8%E7%9A%84%E9%AA%8C%E8%AF%81%E7%A0%81%E4%B8%BA%EF%BC%9A123456%EF%BC%8C%E5%A6%82%E9%9D%9E%E6%9C%AC%E4%BA%BA%E6%93%8D%E4%BD%9C%E8%AF%B7%E5%BF%BD%E7%95%A5%E3%80%82%E3%80%90%E7%99%BE%E6%9C%9B%E9%87%91%E8%9E%8D%E3%80%91", "http://sms.chanzor.com:8001/sms.aspx?action=send", ""));
	}
}
