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
 * 负责用户登录后,跳转页面,权限验证,修改密码等行为的控制类
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
        String reqPath=(String) session.getAttribute("path");
        if(null != reqPath && reqPath.contains("users/applyLoan")){
            return "forward:/basic/productList";
        }else {
            return "forward:/users/init/userInfo";
        }
    }
    
    /**
     * 跳转至用户相关的各个页面
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
        if ("userInfo_applyList".equals(page)) {
            String typeUser = (String) session.getAttribute("typeUser");
            User user = (User) session.getAttribute(loginedUserStr);
            
            //List<ApplyLoan> list = service.queryLoanList(uid);
            //map.put("list", list);
        }
        logger.info("开始访问页面:  " + page + ".jsp");
        return "user/" + page;
    }
}
