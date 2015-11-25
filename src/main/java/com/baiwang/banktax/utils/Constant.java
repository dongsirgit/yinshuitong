/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils;

/**
  * @ClassName: Constant
  * @Description: TODO
  * @author 张衡
  * @date 2015年8月3日 下午3:14:19
  */
public class Constant {
	public final static int SUCCESS = 0;//表示成功状态
	public final static int USER_MOBILEPHONE_EXIST = 1;//手机号已存在
	public final static int USER_PHONECODE_ERROR = 2;//手机验证码输入错误
	public final static int USER_PHONENUM_ERROR = 3;//发送手机验证码时,手机号不存在
	public final static int USER_LOGIN_ERROR = 4;//登录失败
	
	public final static int USER_PARAMETER_MISS =15;//前台参数未接收到
	//0~15预留给登录与注册模块
	public final static int UNKNOWN_ERROR=16;
	
	
//-----以下三个属性支持上传文件的路径定位-------------
	//目录分隔符（unix类系统为/ win类系统为\）
	public final static String FILE_SEPARAROR=System.getProperty("file.separator");
	//JAVA进程temp文件夹（系统文件夹）
	public final static String JAVA_TEMP_DIR=System.getProperty("java.io.tmpdir");
	//该Web应用的运行路径（不包括末尾的目录分隔符,在Filter被初始化）
	public static String WEBAPP_PATH;
	//Web应用的根路径，即某Web应用的上一级路径（不包括末尾的目录分隔符,在Filter被初始化）
	public static String WEBROOT_PATH;
}
