package com.baiwang.banktax.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.Constant;
import com.baiwang.banktax.utils.IPUtil;
import com.baiwang.banktax.utils.StringUtils;
import com.baiwang.banktax.utils.message.SendMsgUtils;

/**
 * 负责用户登录,注册,找回密码,及表单验证等的控制类
 * 
  * @ClassName: UserController
  * @author liujingui
  * @date 2015年11月25日 下午2:28:57
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final String loginedUserStr = ConfigUtil.getLoginedUserStr();
    private static final Log logger = LogFactory.getLog(UserController.class);
    
    @Resource
    private IUserService userService;
    
    /**
     * 跳转至注册页面
     * 
      * @author liujingui
      * @return String  
      * @date 2015年11月24日 下午4:42:23
     */
    @RequestMapping("/forwardRegist")
    public String forwardRegist() {
        return "../regist";
    }
    
    /**
     * 跳转至登录页面
     * 
      * @author liujingui
      * @param session HttpSession
      * @return String  
      * @date 2015年11月24日 下午4:42:23
     */
    @RequestMapping("/forwardLogin")
    public String forwardLogin(HttpSession session) {
        User user = (User) session.getAttribute(loginedUserStr);
        if (null == user) {
            return "../login";
        } else {
            //return "../../index";
            return "../login";
        }
    }
    
    /**
     * 跳转至找回密码页
     * 
      * @author liujingui
      * @return String
      * @date 2015年11月25日 下午3:27:38
     */
    @RequestMapping("/findPwd")
    public String findPwd() {
        return "user/findpwd";
    }
    
    /**
     * 跳转至找回密码时的修改密码页
     * 
      * @author liujingui
      * @return String
      * @date 2015年11月25日 下午3:27:38
     */
    @RequestMapping("/findPwd_reset")
    public String findPwd_reset(HttpSession session) {
        String mobilePhone = (String)session.getAttribute("mobilePhone");
        if(StringUtils.isBlank(mobilePhone)){
            //手机号未验证时,让其返回
            return "user/findpwd";
        }
        return "user/findpwd_reset";
    }
    
    /**
     * 跳转至找回密码的成功页面
     * 
      * @author liujingui
      * @return String
      * @date 2015年11月25日 下午3:27:38
     */
    @RequestMapping("/findPwd_success")
    public String findPwd_success(HttpSession session) {
        String mobilePhone = (String)session.getAttribute("mobilePhone");
        if(StringUtils.isBlank(mobilePhone)){
            //手机号未验证时,让其返回
            return "user/findpwd";
        }
        session.removeAttribute("mobilePhone");
        return "user/findpwd_success";
    }
    
    /**
     * 执行登录
     * 
      * @author liujingui
      * @param user 包含手机号和加密后密码的用户对象
      * @param session
      * @return int 
      * @date 2015年11月25日 下午2:07:13
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public int login(User user, HttpServletRequest request,HttpSession session){
        if(user != null && !StringUtils.hasBlank(user.getMobilePhone(),user.getUserPass())){
            User loginedUser = userService.selectByMobilePhoneAndUserPass(user);
            if(null == loginedUser){
                return Constant.USER_LOGIN_ERROR;
            }else{
                //更新登录时间及IP
                loginedUser.setLastLogIp(IPUtil.getIpAddr(request));
                loginedUser.setLastLogTime(new Date(System.currentTimeMillis()));
                userService.updateByPhoneSelective(loginedUser);
                loginedUser = userService.selectByMobilePhone(loginedUser.getMobilePhone());
                session.setAttribute(loginedUserStr, loginedUser);
                logger.info("登录成功: "+user.getMobilePhone());
                return Constant.SUCCESS;
            }
        }
        logger.error("参数异常");
        return Constant.USER_PARAMETER_MISS;
    }
    
    /**
     * 执行注册
     * 
      * @author liujingui
      * @param user 前台传入的用户填写的信息
      * @return String  
      * @date 2015年11月24日 下午4:44:00
     */
    @RequestMapping("/register")
    public String register(User user, HttpServletRequest request,String mobilePhone){
        if(user != null && !StringUtils.hasBlank(user.getMobilePhone(),user.getUserPass())){
            try{
                user.setRegIp(IPUtil.getIpAddr(request));
                userService.userRegister(user);
                User loginedUser = userService.selectByMobilePhone(mobilePhone);
                request.getSession().setAttribute(loginedUserStr, loginedUser);
                logger.info("注册成功,手机号: "+user.getMobilePhone());
                return "user/registsuc";
            }catch(Exception e){
                logger.error("注册时发生异常！");
                e.printStackTrace();
            }
        }
        return "../regist";
    }
    
    /**
     * 检查手机号的唯一性
     * 
      * @author liujingui
      * @param mobilePhone
      * @return int
      * @date 2015年11月24日 下午5:03:11
     */
    @RequestMapping(value = "/checkMobilePhone")
    @ResponseBody
    public int checkMobilePhone(String mobilePhone){
        if(StringUtils.isNotBlank(mobilePhone)){
            User user=userService.selectByMobilePhone(mobilePhone);
            if(user == null){
                return Constant.SUCCESS;
            }else{
                return Constant.USER_MOBILEPHONE_EXIST;
            }
        }
        logger.error("检查手机号时,参数异常！");
        return Constant.USER_PARAMETER_MISS;
    }
    
    /**
     * 发送手机验证码
     * 
      * @author liujingui
      * @param mobilePhone
      * @return int
      * @date 2015年11月24日 下午5:03:11
     */
    @RequestMapping(value = "/sendPhoneCode")
    @ResponseBody
    public int sendPhoneCode(String mobilePhone,HttpSession session){
        if(StringUtils.isNotBlank(mobilePhone)){
            String code=SendMsgUtils.random();
            String result="";
//            try {
                //result=SendMsgUtils.SendMsg(mobilePhone, code);
                result="0";
//            } catch (UnsupportedEncodingException | MalformedURLException e) {
//                e.printStackTrace();
//            }
            if("0".equals(result)){
                logger.info("手机验证码发送成功==>"+code);
                session.setAttribute(mobilePhone, code);
            //    return Constant.SUCCESS;
                return Integer.parseInt(code);
            }else if("5".equals(result)){
                //手机号不存在
                return Constant.USER_PHONENUM_ERROR;
            }else{
                //服务异常,如欠费等
                logger.error("发送手机验证码时,服务异常");
                return Constant.UNKNOWN_ERROR;
            }
        }
        logger.error("发送手机验证码时,参数异常");
        return Constant.USER_PARAMETER_MISS;
    }
    
    /**
     * 检查手机验证码是否输入正确
     * 
      * @author liujingui
      * @param mobilePhone
      * @return int
      * @date 2015年11月24日 下午5:03:11
     */
    @RequestMapping(value = "/checkPhoneOwner")
    @ResponseBody
    public int checkPhoneOwner(String phoneCode,String mobilePhone,HttpSession session){
        String realCode = (String)session.getAttribute(mobilePhone);
        if(StringUtils.hasBlank(realCode,phoneCode) || !realCode.equals(phoneCode)){
            return Constant.USER_PARAMETER_MISS;
        }else{
            session.setAttribute("mobilePhone", mobilePhone);
            return Constant.SUCCESS;
        }
    }
    
    /**
     * 执行找回密码时的修改密码
     * 
      * @author liujingui
      * @param userPass
      * @param session
      * @return int
      * @date 2015年11月25日 下午6:31:48
     */
    @RequestMapping(value = "/changePwd")
    @ResponseBody
    public int changePwd(String userPass,HttpSession session){
        if(StringUtils.isBlank(userPass)){
            return Constant.USER_PARAMETER_MISS;
        }
        String mobilePhone = (String)session.getAttribute("mobilePhone");
        if(StringUtils.isBlank(mobilePhone)){
            //未通过第一步手机验证码验证
            return Constant.USER_PHONENUM_ERROR;
        }
        try{
            userService.updatePwdByMobilePhone(mobilePhone, userPass);
            return Constant.SUCCESS;
        }catch(Exception e){
            logger.error("变更密码时,发生异常");
            e.printStackTrace();
        }
        return Constant.UNKNOWN_ERROR;
    }
}
