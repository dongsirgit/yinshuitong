package com.baiwang.banktax.controller;

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
import com.baiwang.banktax.utils.StringUtils;

/**
 * 负责用户登录后,跳转页面,修改密码等行为的控制类
 * 
  * @ClassName: UserHolderController
  * @author liujingui
  * @date 2015年11月25日 下午2:31:52
 */
@Controller
@RequestMapping("/users")
public class UserHolderController {

    private static final String loginedUserStr = ConfigUtil.getLoginedUserStr();
    private static final Log logger = LogFactory.getLog(UserController.class);
    
    @Resource
    private IUserService userService;
    
    /**
     * <p>登录成功后,根据认证状态和贷款申请状态,选择跳转页面:</p>
     * <li>未认证,跳转到首页;</li>
     * <li>已认证(未贷款，或贷款申请处于完成状态),跳转到贷款产品列表;</li>
     * <li>已认证(有贷款申请正在进行中),跳转到‘个人中心’;</li>
     * 
      * @author liujingui
      * @param session
      * @param request
      * @return String
      * @date 2015年11月25日 下午2:33:55
     */
    @RequestMapping("/loginSuccess")
    public String loginSuccess(HttpSession session,HttpServletRequest request){
        User loginedUser=(User)session.getAttribute(loginedUserStr);
        byte isVerified=loginedUser.getTaxVerify();
        if(isVerified == 0){
            return "../../index";
        }
        return null;
    }
    
    /**
     * 退出登录
     * 
      * @author liujingui
      * @param session
      * @param request
      * @return String
      * @date 2015年11月25日 下午2:33:55
     */
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "../../index";
    }
    
    /**
     * 跳转至修改密码页
     * 
      * @author liujingui
      * @return String 
      * @date 2015年11月26日 下午5:01:38
     */
    @RequestMapping("/changePwd")
    public String goToChangePwd(){
        return "user/userInfo_changePwd";
    }
    
    /**
     * 修改密码时,先检查旧密码
     * 
      * @author liujingui
      * @param userPwd
      * @return int  
      * @date 2015年11月26日 下午6:26:22
     */
    @RequestMapping("/checkOldPwd")
    @ResponseBody
    public int checkOldPwd(String userPwd,HttpSession session){
        User user=(User)session.getAttribute(loginedUserStr);
        if(null == user || StringUtils.hasBlank(user.getUserPass(),userPwd)){
            logger.error("修改密码前的检查旧密码时,参数异常！");
            return Constant.USER_PARAMETER_MISS;
        }else if(userPwd.equals(user.getUserPass())){
            return Constant.SUCCESS;
        }else{
            return Constant.USER_OLDPWD_ERROR;
        }
    }
    
    /**
     * 执行登录状态下的修改密码
     * 
      * @author liujingui
      * @param userPwd 新密码
      * @param session
      * @return int  
      * @date 2015年11月27日 下午2:23:48
     */
    @RequestMapping("/changePwdOnline")
    @ResponseBody
    public int changePwdOnline(String userPwd,HttpSession session){
        User loginedUser = (User)session.getAttribute(loginedUserStr);
        if(null != loginedUser && !StringUtils.hasBlank(loginedUser.getMobilePhone(),userPwd)){
            try{
                userService.updatePwdByMobilePhone(loginedUser.getMobilePhone(), userPwd);
                //更新session
                loginedUser.setUserPass(userPwd);
                session.setAttribute(loginedUserStr, loginedUser);
                return Constant.SUCCESS;
            }catch(Exception e){
                logger.error("修改密码时,发生异常");
                e.printStackTrace();
            }
        }
        return Constant.USER_PARAMETER_MISS;
    }
}
