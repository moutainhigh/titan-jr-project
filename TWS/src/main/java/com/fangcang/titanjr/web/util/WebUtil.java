package com.fangcang.titanjr.web.util;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.common.lang3.StringUtils;

public class WebUtil {
	/***
	 * 获取请求过来的url
	 * @param request
	 * @return
	 */
	public static String getForwordUrl(HttpServletRequest request){
		String forwordUrl = "";
		if (request.getMethod().equalsIgnoreCase("post")) {
			forwordUrl = request.getHeader("referer");
		} else {
			forwordUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
			if (StringUtils.isNotEmpty(request.getQueryString())) {
				forwordUrl += "?" + request.getQueryString();
			}
		} 
		return forwordUrl;
	}
}
