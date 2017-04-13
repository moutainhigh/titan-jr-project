package com.fangcang.titanjr.web.interceptor;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.util.WebConstant;

/**
 * 金融访问权限拦截器
 * @author luoqinglong
 * @2016年7月14日
 * @version 1.1
 */
public class AccessPermissionInterceptor  implements HandlerInterceptor {
	@Resource
	private TitanFinancialUserService userService;
	@Resource
	private TitanFinancialOrganService organService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Integer isadmin = 0;
		String tfsUserId = (String)session.getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		if(tfsUserId!=null){
			UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
			userInfoQueryRequest.setTfsUserId(Integer.valueOf(tfsUserId));
			UserInfoPageResponse userInfoPage = userService.queryUserInfoPage(userInfoQueryRequest);
			TitanUser titanUser =userInfoPage.getTitanUserPaginationSupport().getItemList().get(0);
			if(titanUser.getStatus()==TitanUserEnum.Status.FREEZE.getKey()){
				setMsg(request, response, "当前用户已冻结，请联系管理员");
				return false;
			}
			if(titanUser.getStatus()==TitanUserEnum.Status.NOT_AVAILABLE.getKey()){
				setMsg(request, response, "当前用户已注销，请联系管理员");
				return false;
			}
			isadmin = titanUser.getIsadmin();
		}
		
		if(handler instanceof HandlerMethod){//是请求方法才进行判断
			HandlerMethod method = (HandlerMethod)handler;
			AccessPermission accessPermission = method.getMethod().getAnnotation(AccessPermission.class);
			//默认无注解的方法全部限制访问
			if(accessPermission==null){
				setMsg(request, response, "这是一个bug,请开发同事给该方法添加权限注解");
				return false;
			}
			String[] allownRoleCode = accessPermission.allowRoleCode();

			//无权限限制访问
			if(excludeRole(allownRoleCode)){
				return true;
			}
			/****************** 以下需要访问权限的业务 **********************/
			//是否开通了金融账户
			if(hasRegister(request, response)==false){
				return false;
			}
			//是否拥有该方法的访问权限
			List<RoleDTO> tfsRoleDTOList = (List<RoleDTO>)session.getAttribute(WebConstant.SESSION_KEY_JR_ROLE_LIST);
			String hasPermission = isAllow(allownRoleCode, tfsRoleDTOList,isadmin);
			if(hasPermission.equals("no")){
				setMsg(request, response, "当前用户没有权限访问，请联系管理员");
				return false;
			}
			if(hasPermission.equals("no_admin")){
				setMsg(request, response, "只有管理员才能访问该功能，请联系管理员");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 商家是否已经开通金融账号
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private boolean hasRegister(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		return true;
	}
	
	/**
	 * 返回错误信息或者页面
	 * @param request
	 * @param response
	 * @param msg
	 * @throws ServletException
	 * @throws IOException
	 */
	private void setMsg(HttpServletRequest request,HttpServletResponse response,String msg) throws ServletException, IOException{
		request.setAttribute("errormsg", msg);
		String with = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(with)){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}else{
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}
	/***
	 * 业务方法是否没有无权限限制注解
	 */
	private boolean excludeRole(String[] function){
		for(String roleCode : function){
			if(CommonConstant.ROLECODE_NO_LIMIT.equals(roleCode)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 是否允许访问，判断是否含有权限
	 * @param function 方法访问需要的权限
	 * @param roleList 用户拥有的权限
	 * @param isadmin 是否为admin
	 * @return
	 */
	private String isAllow(String[] functionArray,List<RoleDTO> roleList,int isadmin){
		if(roleList==null||roleList.size()==0){
			return "no";//无权限
		}
		for(String function : functionArray){
			//管理员权限判断
			if(function.equals(CommonConstant.ROLECODE_ADMIN)&&isadmin==0){
				return "no_admin";//无管理员权限
			}
			if(function.equals(CommonConstant.ROLECODE_ADMIN)&&isadmin==1){
				return "yes";
			}
			for(RoleDTO itemDto : roleList){
				if(itemDto.getRoleCode().equals(function)){
					return "yes";
				}
			}
		}
		return "no";
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