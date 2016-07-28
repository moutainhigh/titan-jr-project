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

import com.fangcang.titanjr.common.enums.OrgCheckResultEnum;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.bean.CheckStatus;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
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
		Integer tfsUserId = (Integer)session.getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		if(tfsUserId!=null){
			UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
			userInfoQueryRequest.setTfsUserId(tfsUserId);
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
			//是否有访问权限限制
			if(accessPermission==null){//无
				accessPermission = method.getBean().getClass().getAnnotation(AccessPermission.class);
				if(accessPermission==null){//类上也无权限限制
					return true;
				}
			}
			String[] allownRoleCode = accessPermission.allowRoleCode();
			if(allownRoleCode.length==0){//不需要特殊访问权限
				return true;
			}
			//无权限限制
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
		String merchantCode = (String)request.getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
		Integer isadmin = (Integer)request.getSession().getAttribute(WebConstant.SESSION_KEY_LOGIN_IS_ADMIN);
		FinancialOrganQueryRequest financialOrganQueryRequest = new FinancialOrganQueryRequest();
		financialOrganQueryRequest.setMerchantcode(merchantCode);
		FinancialOrganResponse financialOrganResponse = organService.queryFinancialOrgan(financialOrganQueryRequest);
		
		if(financialOrganResponse.getFinancialOrganDTO()!=null){
			FinancialOrganDTO financialOrganDTO = financialOrganResponse.getFinancialOrganDTO();
			CheckStatus checkStatus = financialOrganDTO.getCheckStatus();
			if(!OrgCheckResultEnum.PASS.getResultkey().equals(checkStatus.getCheckResultKey())){
				setMsg(request, response, "开通申请正在审核中，请等待客服审核");
				return false;
			}
			return true;
		}else{//暂未开通
			if(isadmin!=null&&isadmin==1){
				setMsg(request, response, "当前用户还没有开通该功能，请先开通泰坦金融");
			}else{
				setMsg(request, response, "当前用户还没有开通该功能，请联系管理员开通");
			}
			return false;
		}
		
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
			return "no";
		}
		for(String function : functionArray){
			//管理员权限判断
			if(function.equals(CommonConstant.ROLECODE_ADMIN)&&isadmin==0){
				return "no_admin";
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
