package com.baiwang.banktax.utils;

import java.util.Date;
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
	 * 获得登录用户的索引
	 * 
	 * @return
	 */
	public static final String getLoginedUserStr() {
		return bundle.getString("loginedUserStr");
	}
	
	/**
	 * 获得贷款申请状态码对应的跟踪信息-文字说明
	 * 
	 * @return
	 */
	public static final String getLoanStatusNotes(int status) {
		return DateUtils.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss          ")+bundle.getString("note_"+status)+"<br/>";
	}
	
    /**
     * 获得返回处理结果码的索引
     * 
     * @return
     */
    public static final String getFlagStr() {
        return bundle.getString("flagStr");
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
