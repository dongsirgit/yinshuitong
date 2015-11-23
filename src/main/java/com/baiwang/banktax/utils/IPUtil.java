/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils;

import javax.servlet.http.HttpServletRequest;

/**
  * @ClassName: IPUtil
  * @Description: 获取IP地址
  * @author lisy
  * @date 2015年8月20日 上午9:15:56
  */
public class IPUtil {
	
//	private static final Log logger = LogFactory.getLog(IPUtil.class);

	/**
	 * @Modify by Yinhua
	 * @Description: 目前已经找到在‘http代理转发的’情况下，正常获取RemoteAddr的tomcat配置方法，
	 *               不再需要获取request中的额外属性
	 * @param @param  request
	 * @return String
	 * @date 2015年9月2日 上午11:15:56
	 */
	public static String getIpAddr(HttpServletRequest request) {
//		String ip = request.getHeader("X-Forwarded-For");
//		if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		if(null == ip || ip.length() == 0) {
//			ip = "无法获取访问IP地址";
//		} else if("0:0:0:0:0:0:0:1".equals(ip) || "localhost".equalsIgnoreCase(ip)) {
//			try {
//				ip = InetAddress.getLocalHost().getHostAddress();
//			} catch (UnknownHostException unknownhostexception) {
//				logger.error("IPUtil.getIpAddr获取IP失败：" + unknownhostexception);
//			}
//		}
//		if(ip.split(",").length > 1) {
//			ip = ip.split(",")[0];
//		}
//		return ip;
		return request.getRemoteAddr();
	}

}
