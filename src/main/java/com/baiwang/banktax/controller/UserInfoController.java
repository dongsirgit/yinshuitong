package com.baiwang.banktax.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.baiwang.banktax.beans.TaxReportWithBLOBs;
import com.baiwang.banktax.beans.User;
import com.baiwang.banktax.model.ApplyDetailBean;
import com.baiwang.banktax.model.ApplyListBean;
import com.baiwang.banktax.services.iface.IApplyLoanService;
import com.baiwang.banktax.services.iface.ITaxReportService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.PDFUtil;

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

    //private static final String loginedUserStr = ConfigUtil.getLoginedUserStr();
    private static final Log logger = LogFactory.getLog(UserController.class);
    
    @Resource
    private IApplyLoanService service;
    @Resource
    private ITaxReportService taxService;
    
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
    	if("userInfo_account".equals(page)){
    		List<ApplyListBean> list = service.queryLoanList(user.getId());
    		map.put("list", list);
    	}
    	
        logger.info("开始访问页面:  " + page + ".jsp");
        return "user/" + page;
    }
    
    /**
     * 
      * @author gkm
      * @Description: 贷款列表 取消操作
      * @param @param id
      * @param @param session
      * @param @return  
      * @return int  
      * @throws
      * @date 2015年12月2日 下午1:29:19
     */
    @RequestMapping("/loan/quxiao")
    @ResponseBody
    public int quxiao(Long id, HttpSession session){
    	int success = service.updateApplyStatus(id, ConfigUtil.getLoanStatusNotes(200), (short)200);
    	return success;
    }
    
    @RequestMapping("/loan/detail")
    public String loanDetail(Integer id, HttpSession session, Map<String, Object> map){
    	if(null == id){
    		return "redirect:users/init/userInfo_account";
    	}
    	User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
    	logger.info("-----UserInfoController.loanDetail-------用户:"+user.getId()+",获取订单id:"+id);
    	
    	
		ApplyDetailBean detail = service.queryLoanDeatil(id,user.getId());
		if(null == detail){
			return "redirect:users/init/userInfo_account";
		}
		map.put("detail", detail);
    	
    	return "user/loanDetail";
    }
    
    @RequestMapping("/generPDF")
    @ResponseBody
    public Map<String, Object> generPDF(HttpSession session){
    	User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
    	Long uid = user.getId();
    	Map<String, Object> map = new HashMap<>();
    	TaxReportWithBLOBs report = taxService.selectByUid(77l);
    	if(null != report){
    		try {
    			String path = PDFUtil.generatePDF(uid,report);
    			logger.info("生成PDF路径："+path);
    			map.put("PDFpath", path);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return map;
    }
}
