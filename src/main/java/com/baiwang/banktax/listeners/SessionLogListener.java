package com.baiwang.banktax.listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;

/**
 * @ClassName: SessionCreateLister
 * @Description: session监听
 * @author zhaowei
 * @date 2015年9月29日 上午9:49:32
 * @Modify By YinHua at 2015年9月29日 上午11:29:32
 */
public class SessionLogListener implements HttpSessionListener {
	private static final Log logger = LogFactory.getLog(SessionLogListener.class);
	private static final String sessionInfo = ConfigUtil.getSessionInfoName();
	IUserService userService;

    public SessionLogListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * 在session销毁时，将其中user信息（若存在）保存（登录时间、登录IP等）
     */
    public void sessionDestroyed(HttpSessionEvent se)  {
         // TODO Auto-generated method stub
    	HttpSession session = se.getSession();
    	if(null == userService)
    		userService =(IUserService)getObjectFromApplication(session.getServletContext(), "userService");
    	logUserInfo(session);
    	
    }

	void logUserInfo(HttpSession session) {
		String typeUser = (String) session.getAttribute("typeUser");
		if (null == typeUser)
			return;
		if ("0".equals(typeUser)) {
			Cuser user = (Cuser) session.getAttribute(sessionInfo);
			logUserInfo(user);
		} else if ("1".equals(typeUser)) {
			Puser user = (Puser) session.getAttribute(sessionInfo);
			logUserInfo(user);
		}
	}

	private void logUserInfo(Cuser user) {
		userService.updateLogById(user);
		logger.info("LogUsr：" + user.getUserName() + "  --LoginAt：" 
				+ user.getLastLogTime() + "  --LogIP：" + user.getLastLogIp());
	}

	private void logUserInfo(Puser user) {
		//TODO
	}

	private Object getObjectFromApplication(ServletContext servletContext,String beanName){  
        ApplicationContext application=WebApplicationContextUtils.getWebApplicationContext(servletContext);  
        return application.getBean(beanName);  
    }  
}
