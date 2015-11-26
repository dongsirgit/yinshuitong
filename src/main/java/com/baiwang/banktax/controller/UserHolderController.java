package com.baiwang.banktax.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.utils.ConfigUtil;

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
    
    /**
     * 登录成功后,页面跳转至产品列表页或个人信息页
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
    

}
