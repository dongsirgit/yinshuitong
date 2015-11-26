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
 * 负责管理用户信息,税务信息等的控制类
 * 
  * @ClassName: UserHolderController
  * @author liujingui
  * @date 2015年11月25日 下午2:31:52
 */
@Controller
@RequestMapping("/users")
public class UserInfoController {

    private static final String loginedUserStr = ConfigUtil.getLoginedUserStr();
    private static final Log logger = LogFactory.getLog(UserController.class);
    
    /**
     * 跳转至用户个人中心相关的各个页面
     * 
      * @author liujingui
      * @param page
      * @param session
      * @param map
      * @return String
      * @date 2015年11月25日 下午2:42:49
     */
    @RequestMapping("/init/{page}")
    public String init(@PathVariable String page, HttpSession session, Map<String, Object> map) {
        
        logger.info("开始访问页面:  " + page + ".jsp");
        return "user/" + page;
        
    }
}
