package com.baiwang.banktax.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baiwang.banktax.utils.message.SendMsgUtils;


/***
  * @ClassName: SendMsgController
  * @Description: 发送短信控制器
  * @author zhaowei
  * @date 2015年8月4日 下午5:42:10
 */
@Controller
@RequestMapping("/sendMsg")
public class SendMsgController {

	private static final Log logger = LogFactory.getLog(SendMsgController.class);
	
	/**
	  * @author zhaowei
	  * @Description: 进入发送短信测试页面
	  * @return String  
	  * @throws
	  * @date 2015年8月4日 下午3:38:36
	 */
	@RequestMapping("")
	public String toIndex() {
		return "/sendMsg";
	}
	
	/**
	 * @throws UnsupportedEncodingException * 
	  * @Description: 向输入的手机号发送验证码
	  * @param @param phone
	  * @param @param request
	  * @param @param response
	  * @param @throws HttpException
	  * @param @throws IOException  
	  * @return ModelAndView  
	  * @throws
	 */
	@RequestMapping(value="/sendCode",produces="application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView sendCodeMsg(String mobile, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		
		String code = SendMsgUtils.random();
		logger.info("验证码为：" + code);
		

		String result = "";
		try {
			result = SendMsgUtils.SendMsg(mobile, code);
		} catch (MalformedURLException e) {
			logger.error("短信接口调用失败："+e.getMessage());
		}
		if ("0".equals(result)) {
			logger.info("验证码发送成功！");
			request.getSession().setAttribute(mobile, code);
		} else {
			logger.info("验证码发送失败！请稍后重试");
		}
		return new ModelAndView();

	}
}
