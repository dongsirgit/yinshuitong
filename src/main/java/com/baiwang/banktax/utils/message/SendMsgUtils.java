/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils.message;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.baiwang.banktax.utils.ConfigUtil;

/**
  * @ClassName: SenderMsgUtils
  * @Description: 发送短信对外接口
  * @author 赵威
  * @date 2015年10月8日 上午9:09:07
  */
public class SendMsgUtils {

	private static final Log logger = LogFactory.getLog(SendMsgUtils.class);
	
	private static String msgAccount =  ConfigUtil.get("msg.account");
	
	private static String msgPassword = ConfigUtil.get("msg.password");
	
	private static String msgPostUrl =  ConfigUtil.get("msg.postUrl");
	
	private static String msgTemplate = ConfigUtil.get("msg.template");
	
	/**
	  * @author zhaowei
	  * @Description: 给指定号码发送验证码
	  * @param @param mobile
	  * @param @param code
	  * @param @return 
	  *  "0" -- 发送成功 
	  *  "1" -- 额度不足
	  *  "2" -- 密码为空
	  *  "3" -- 用户名为空
	  *  "4" -- 手机号码为空
	  *  "5" -- 错误的手机号码
	  *  "6" -- 内容为空
	  *  "7" -- 需要签名
	  *  "8" -- 其他错误
	  * @param @throws UnsupportedEncodingException
	  * @param @throws MalformedURLException  
	  * @return String  
	  * @throws
	  * @date 2015年10月8日 上午10:01:15
	 */
	public static String SendMsg(String mobile,String code) throws UnsupportedEncodingException, MalformedURLException{
		
		String content = java.net.URLEncoder.encode(msgTemplate.replaceAll("@", code), "utf-8");
		String result = MsgSender.Sms("", msgAccount, msgPassword, mobile, content, msgPostUrl, "");
		
		HashMap<String, String> temp = XmlToMap(result);
		String returnstatus = temp.get("returnstatus");
		String message = temp.get("message");
		if("Success".equals(returnstatus)){
			return "0";
		}else{
			if("额度不足".equals(message)){
				return "1";
			}else if("密码为空".equals(message)){
				return "2";
			}else if("用户名为空".equals(message)){
				return "3";
			}else if("手机号码为空".equals(message)){
				return "4";
			}else if("错误的手机号码".equals(message)){
				return "5";
			}else if("内容为空".equals(message)){
				return "6";
			}else if("需要签名".equals(message)){
				return "7";
			}else{
				return "8";
			}
		}
	}
	
	/**
	  * @author zhaowei
	  * @Description: 将短信返回的xml字符串封装成map
	  * @param @param xml
	  * @return HashMap<String,String>  
	  * @throws
	  * @date 2015年8月6日 上午11:32:12
	 */
	private static HashMap<String, String> XmlToMap(String xml) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		InputSource source = null;
		StringReader reader = null;
		try {
			builder = factory.newDocumentBuilder();
			reader = new StringReader(xml);
			source = new InputSource(reader);// 使用字符流创建新的输入源
			doc = builder.parse(source);
			Node root = doc.getFirstChild();
			NodeList nodelist = root.getChildNodes();
			for (int j = 0; j < nodelist.getLength(); j++) {
				Node node = nodelist.item(j);
				if (node.getNodeName().equals("returnstatus")) {
					resultMap.put("returnstatus", node.getTextContent());
				} else if (node.getNodeName().equals("message")) {
					resultMap.put("message", node.getTextContent());
				}
			}
		} catch (Exception e) {
			logger.error("xml转换map失败"+ e);
			return null;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return resultMap;
	}
	/**
	  * @author zhaowei
	  * @Description: 生成六位验证码
	  * @return String  
	  * @throws
	  * @date 2015年10月8日 上午10:09:22
	 */
	public static String random() {
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		// 6位验证码
		for (int i = 0; i < 6; i++) {
			code.append(String.valueOf(random.nextInt(10)));
		}

		return code.toString();
	}
	
	public static void main(String[] args){
		try {
			System.out.println(SendMsg("13718099648","123123"));
		} catch (UnsupportedEncodingException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (MalformedURLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
