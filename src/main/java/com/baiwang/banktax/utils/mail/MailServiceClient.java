package com.baiwang.banktax.utils.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.baiwang.banktax.utils.ConfigUtil;

import java.io.File;
import java.util.LinkedHashMap;
import javax.mail.internet.MimeMessage;

/**
 * 
  * @ClassName: MailServiceClient
  * @Description: 发送邮件服务
  * @author gkm
  * @date 2015年8月5日 下午1:20:46
 */
public class MailServiceClient extends JavaMailSenderImpl{

	
	/**
	 * 
	  * @author gkm
	  * @Description: 发送普通文本邮件
	  * @param @param to 多个收件人
	  * @param @param subject 主题
	  * @param @param text 邮件内容
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年10月29日 下午2:01:29
	 */
	public  String sendEmail(String[] to, String subject, String text){
		return sendEmail(to, subject, text, "");
	}
	//单个收件人
	public  String sendEmail(String to, String subject, String text){
		return sendEmail(new String[]{to}, subject, text, "");
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 发送普通文本邮件
	  * @param @param to 多个收件人
	  * @param @param subject 主题
	  * @param @param text 邮件内容
	  * @param @param cc 抄送
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年10月29日 下午2:01:29
	 */
	public  String sendEmail(String[] to, String subject, String text, String cc){
		return sendEmail(to, subject, text, cc,false);
	}
	//单个收件人
	public  String sendEmail(String to, String subject, String text, String cc){
		return sendEmail(new String[]{to}, subject, text, cc,false);
	}
	/**
	 * 
	  * @author gkm
	  * @Description: 发送邮件,抄送功能
	  * @param @param to 多个收件人
	  * @param @param subject 主题
	  * @param @param text 邮件内容
	  * @param @param cc 抄送
	  * @param @param html 是否为html格式,true-->发送html,false--->普通文本
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年10月29日 下午2:01:29
	 */
	public  String sendEmail(String[] to, String subject, String text, String cc,boolean html){
		return sendEmail(to, subject, text, cc, html, null);
	}
	//单个收件人
	public  String sendEmail(String to, String subject, String text, String cc,boolean html){
		return sendEmail(new String[]{to}, subject, text, cc, html, null);
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 发送邮件,可带附件,抄送功能
	  * @param @param to 收件人
	  * @param @param subject 主题
	  * @param @param text 邮件内容
	  * @param @param cc 抄送
	  * @param @param html 是否为html格式,true-->发送html,false--->普通文本
	  * @param @param fujian 附件<"名称.后缀",附件File>
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年10月29日 下午2:01:29
	 */
	public  String sendEmail(String[] to, String subject, String text, String cc,boolean html, LinkedHashMap<String,File> fujian){
		try{
			MimeMessage mailMessage = this.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
			
			helper.setTo(to);
			helper.setFrom(ConfigUtil.get("mail.from"),ConfigUtil.get("mail.from.name"));
			helper.setSubject(subject);
			helper.setText(text,html);//true-->发送html,false--->普通文本
			if(null != cc && !"".equals(cc)){
				helper.setCc(cc);
			}
			if(null != fujian && fujian.size()>0){
				for(String key : fujian.keySet()){
					helper.addAttachment(key, fujian.get(key));
				}
			}
		
			this.send(mailMessage);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "fail";
	}
	//单个收件人
	public  String sendEmail(String to, String subject, String text, String cc,boolean html, LinkedHashMap<String,File> fujian){
		return  sendEmail(new String[]{to}, subject, text, cc, html, fujian);
	}
	
	
	/**
	 * 
	  * @author gkm
	  * @Description: 发送简单邮件(文本)
	  * @param @param to 收件人邮箱地址(1个)
	  * @param @param subject 主题
	  * @param @param text 邮件内容
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年10月29日 上午10:07:51
	 */
	public String sendSimpleMail(String to, String subject, String text){
		return sendSimpleMail(new String[]{to},  subject,  text);
	}
	
	/**
	 * 
	  * @author gkm
	  * @Description: 发送简单邮件(文本)
	  * @param @param to 收件人邮箱地址(可多个)
	  * @param @param subject 主题
	  * @param @param text 邮件内容
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2015年10月29日 上午10:07:51
	 */
	public String sendSimpleMail(String to[], String subject, String text){
		try{
			SimpleMailMessage smm = new SimpleMailMessage();
			smm.setFrom(ConfigUtil.get("mail.from"));
			smm.setTo(to);
			smm.setSubject(subject);
			smm.setText(text);
		
			this.send(smm);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "fail";
	}
	
	
	
}
