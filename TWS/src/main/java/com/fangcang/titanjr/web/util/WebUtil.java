package com.fangcang.titanjr.web.util;

import javax.servlet.http.HttpServletRequest;

import com.fangcang.util.StringUtils;


public class WebUtil {
	/***
	 * 获取请求过来的url
	 * @param request
	 * @return
	 */
	public static String getForwardUrl(HttpServletRequest request){
		String forwordUrl = "";
		if (request.getMethod().equalsIgnoreCase("post")) {
			forwordUrl = request.getHeader("referer");
		} else {
			forwordUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
			if (StringUtils.isValidString(request.getQueryString())) {
				forwordUrl += "?" + request.getQueryString();
			}
		} 
		return forwordUrl;
	}
}
