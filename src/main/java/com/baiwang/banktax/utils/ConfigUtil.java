package com.baiwang.banktax.utils;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: ConfigUtil
 * @Description: 项目参数工具类
 * @author lisy
 * @date 2015年8月4日 下午4:23:25
 */
public class ConfigUtil {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(ConfigUtil.class);
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	/**
	 * 通过键获取值
	 * 
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}
}
