/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: BasicController
 * @Description: 转发不被拦截的页面的
 * @author gkm
 * @date 2015年8月14日 下午4:57:29
 */
@Controller
@RequestMapping("/basic")
public class BasicController {

	/**
	 * 
	 * @Description: 页头 @param @return @return String @throws
	 */
	@RequestMapping("/head")
	public String head() {
		return "base/head";
	}

	/**
	 * 
	 * @Description: 产品列表页 @param @return @return String @throws
	 */
	@RequestMapping("/productList")
	public String loanList() {
		return "order/productList";
	}

	/**
	 * 
	 * @Description: 关于我们 @param @return @return String @throws
	 */
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		return "base/aboutUs";
	}

	/**
	 * 
	 * @Description: 页脚 @param @return @return String @throws
	 */
	@RequestMapping("/footer")
	public String footer() {
		return "base/footer";
	}

	/**
	 * @author ldm
	 * @Description: 查看首页新闻
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年8月27日 下午3:41:31
	 */
	@RequestMapping("/news")
	public String news(String news_flag, Map<String, Object> map) {
		map.put("news_flag", news_flag);
		return "news/news";
	}

	/**
	 * @author ldm
	 * @Description: 更多新闻
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2015年8月27日 下午3:41:31
	 */
	@RequestMapping("/newsMore")
	public String newsMore() {
		return "news/newsMore";
	}
}
