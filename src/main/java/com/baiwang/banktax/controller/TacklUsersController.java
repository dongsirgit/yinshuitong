/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.services.iface.IPuserService;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.Constant;

/**
  * @ClassName: TacklUsersController
  * @Description: 请求拦截类
  * @author 张衡
  * @date 2015年8月5日 下午4:54:34
  */
@Controller
@RequestMapping("/users")
public class TacklUsersController {

	private static final Log logger = LogFactory.getLog(TacklUsersController.class);
	private static final String sessionInfo=ConfigUtil.getSessionInfoName();
	@Resource
	private IUserService userService;
	
	@Resource
	private IPuserService puserService;
	
	/**
	 * 
	  * @Description 登陆成功后，转发到登陆后界面。
	  * @param session HttpSession 
	  * @param request HttpServletRequest
	  * @return String
	 */
	@RequestMapping("/loginSuccess")
	public String loginSuccess(HttpSession session,HttpServletRequest request){
		String reqPath=(String) session.getAttribute("path");
		if(null!=reqPath&&reqPath.contains("users/applyLoan")){
			return "forward:/basic/productList";
		}else {
			return "forward:/users/init/userInfo";
		}
	}
	
	/**
	 * 
	  * @Description 用户退出
      * @param session HttpSession 
      * @param request HttpServletRequest
	  * @return String
	 */
	@RequestMapping("/exit")
	public String  forwardLogin(HttpSession session,HttpServletRequest request){
		session.invalidate();
		return "../../index";
	}
	
	/**
	 * 
	  * @author 张衡
	  * @Description 修改密码成功后，销毁session。返回到登陆界面
      * @param session HttpSession 
      * @param req HttpServletRequest
	  * @return String
	  * @date 2015年8月20日 上午10:58:19
	 */
	@RequestMapping("/forwardLogin")
	public String modifyPassToLogin(HttpSession session,HttpServletRequest req){
		session.invalidate();
		req.setAttribute("notePass", "请用新密码登录");
		return "../login";
	}
	
	/**
	 * 
	  * @author 张衡
	  * @Description 用户修改密码
	  * @param userPass String
	  * @param passWord String
	  * @param session HttpSession
	  * @return String
	  * @date 2015年8月14日 上午9:51:35
	 */
	@RequestMapping(value="/modifyPass",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyPass(String userPass,String passWord,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		if(null==userPass||null==passWord||"".equals(userPass)||"".equals(passWord)){
			result.put("flag", Constant.PASSWORD_ERROR);
			return result;
		}
		String typeUser=(String) session.getAttribute("typeUser");
		String correctpass=null;
		Cuser user=null;
		Puser puser=null;
		
		//根据id查数据库得到正确的密码
		if("0".equals(typeUser)){
			 user=(Cuser) session.getAttribute(sessionInfo);
			Long id=user.getId();
			Cuser use=userService.selectById(id);
			correctpass=use.getUserPass();
		}else{
			puser=(Puser) session.getAttribute(sessionInfo);
			Long id=puser.getId();
		    puser=puserService.selectById(id);
			correctpass=puser.getUserPass();
		}
		//修改密码的时候，根据0或1来判断是企业用户还是个人用户。
		if(!correctpass.equalsIgnoreCase((userPass))){
			logger.info("修改密码时，输入原密码错误");
			result.put("flag", Constant.PASSWORD_ERROR);
			return result;
		}else{
			//当原密码正确的时候，修改成前台传过来的新密码
			if("0".equals(typeUser)){
    			user.setUserPass(passWord);
    			userService.updatePassById(user);
			}else{
				puser.setUserPass(passWord);
				puserService.updateById(puser);
			}
            result.put("flag", Constant.SUCCESS);
			return result;
		}
	}
	
	/**
	 * 
	  * @author 张衡
	  * @Description 转发到用户修改密码
	  * @return String
	  * @date 2015年8月14日 上午9:51:35
	 */
	@RequestMapping("/forwardPass")
	public String forwardPass(){
		return "user/modifypass";
	}

}
