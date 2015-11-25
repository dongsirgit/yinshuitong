package com.baiwang.banktax.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.Constant;

/**
 * @ClassName: SessionFilter
 * @Description: 判断是否登录
 * @author lisy
 * @date 2015年8月4日 下午12:00:37
 */
@SuppressWarnings("unused")
public class SessionFilter implements Filter {
    
    private static final String loginedUserStr = ConfigUtil.getLoginedUserStr();
	private static final Log logger = LogFactory.getLog(SessionFilter.class);

	private List<String> list = new ArrayList<String>();

	public void init(FilterConfig filterConfig) throws ServletException {
		// 初始化需要拦截的文件夹
		// String include = filterConfig.getInitParameter("include");
		// if (!StringUtils.isBlank(include)) {
		// StringTokenizer st = new StringTokenizer(include, ",");
		// list.clear();
		// while (st.hasMoreTokens()) {
		// list.add(st.nextToken());
		// }
		// }

		//获取本WEB应用的相关路径（应用所在路径与上一级路径），并存到常量类中
		Constant.WEBAPP_PATH = filterConfig.getServletContext().getRealPath("");
		logger.info("WEBAPP_PATH: " + Constant.WEBAPP_PATH);
		int i = Constant.WEBAPP_PATH.lastIndexOf(Constant.FILE_SEPARAROR);
		Constant.WEBROOT_PATH = Constant.WEBAPP_PATH.substring(0, i);
		logger.info("WEBROOT_PATH: " + Constant.WEBROOT_PATH);
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		/*String servletPath = request.getServletPath();
		String path = request.getRequestURI();*/

		if (null == request.getSession().getAttribute(loginedUserStr)) {// session不存在需要拦截
			if((request.getHeader("x-requested-with")!= null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))//ajax异步请求  
					|| (null!=request.getContentType() && request.getContentType().contains("multipart/form-data"))){//上传-ajax同步请求
				logger.info("-------url:"+request.getRequestURI()+",session timeout");
				response.setStatus(500);
                PrintWriter printWriter = response.getWriter();  
                printWriter.print("timeOut");
                printWriter.flush();
                printWriter.close();
            }else{
            	logger.info("您还没有登录或登录已超时，请重新登录!");
    			request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
    			String reqPath=request.getRequestURI();
    			request.getSession().setAttribute("path", reqPath);
    			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

}
