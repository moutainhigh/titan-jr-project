package com.fangcang.titanjr.web.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fangcang.titanjr.common.enums.OrgCheckResultEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.dto.bean.CheckStatus;
import com.fangcang.titanjr.dto.request.CheckUserRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.CheckUserResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.titanjr.web.util.WebUtil;
import com.fangcang.util.StringUtil;

public class UserLoginInterceptor implements HandlerInterceptor{

	@Autowired
    private TitanFinancialUserService userService;

    @Autowired
    private TitanFinancialOrganService organService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		boolean isLogin = checkIsLogin(request.getSession());
		if(isLogin){//已经登录
			//检查用户状态
			String tfsUserId = (String)request.getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
			CheckUserRequest checkUserRequest = new CheckUserRequest();
			checkUserRequest.setTfsUserId(Integer.valueOf(tfsUserId));
			CheckUserResponse checkUserResponse = userService.checkUser(checkUserRequest);
			if(checkUserResponse.isResult()){//用户和机构状态正常
				return true;
			}else{//状态不正常
				response.sendRedirect(request.getContextPath()+"/ex/user-state.shtml");
				return false;
			}
			 
		}else{//未登录，跳到登陆界面
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
		String tfsUserId = (String)session.getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
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
