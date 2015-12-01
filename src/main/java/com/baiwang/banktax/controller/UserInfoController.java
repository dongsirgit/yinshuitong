package com.baiwang.banktax.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.model.ApplyListBean;
import com.baiwang.banktax.services.iface.IApplyLoanService;
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
    
    @Resource
    private IApplyLoanService service;
    
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
    	User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
    	logger.info("-----UserInfoController.init-------用户:"+user.getId()+",访问页面:"+page);
    	
    	List<ApplyListBean> list = service.queryLoanList(user.getId());
		map.put("list", list);
    	
        logger.info("开始访问页面:  " + page + ".jsp");
        return "user/" + page;
    }
    
    
    @RequestMapping("/loan/quxiao")
    @ResponseBody
    public int quxiao(Integer id, HttpSession session){
    	User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
    	int success = service.quxiao(id, user.getId());
    	return success;
    }
    
    
    
}
