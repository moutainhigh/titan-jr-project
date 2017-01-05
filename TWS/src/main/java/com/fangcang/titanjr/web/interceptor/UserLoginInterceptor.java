package com.fangcang.titanjr.web.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.titanjr.web.util.WebUtil;
import com.fangcang.util.StringUtil;

public class UserLoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		boolean isLogin = checkIsLogin(request.getSession());
		if(isLogin){
			return true;
		}else{
			String returnUrl = WebUtil.getForwordUrl(request);
			response.sendRedirect(request.getContextPath()+"/ex/login.shtml?returnUrl="+URLEncoder.encode(returnUrl, "utf-8"));
		}
		return false;
	}
	/**
	 * 判断是否已经登录
	 * @param session
	 * @return
	 */
	public boolean checkIsLogin(HttpSession session){
		String tfsUserId = (String)session.getAttribute(WebConstant.TWS_SESSION_TFS_USER_ID);
		if(StringUtil.isValidString(tfsUserId)){
			return true;
		}
		return false;
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
