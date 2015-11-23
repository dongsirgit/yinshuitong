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
	public final static int SUCCESS = 0;//表示找回密码时，验证成功
	public final static int USER_NAME_ERROR = 1;//登陆的时候，用户名错误
	public final static int PASSWORD_ERROR = 2;//登陆的时候密码错误
	public final static int IMAGE_CODE_ERROR = 3;//验证码错误
	public final static int USER_NAME_SUCCESS = 4;//用户名可以使用
	public final static int USER_NAME_EXIST = 5;//注册的时候，用户名已经存在
	public final static int USER_IDCARD_ERROR = 11;//注册的时候，身份证号输入错误
	public final static int USER_INVITECODE_ERROR = 12;//注册的时候，输入的邀请码有误
	public final static int TAX_NAME_EXIST =6;//税号已经存在
	public final static int MAIL_ERROR =7;//找回密码的时候，填写的邮箱信息错误
	public final static int REGIST_MAIL_ERROR =8;//注册的时候，填写的邮箱信息错误
	public final static int REGIST_MAIL_SUCCESS =9;//注册密码的时候，填写的邮箱信息正确
	public final static int RETRIEVE_PASS_LATER=10;//找回密码的时候，服务器发送邮件没开，提示稍后再试
	
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
