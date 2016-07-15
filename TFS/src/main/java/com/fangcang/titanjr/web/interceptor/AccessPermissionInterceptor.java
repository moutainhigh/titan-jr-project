package com.fangcang.titanjr.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.util.WebConstant;

/**
 * 金融访问权限拦截器
 * @author luoqinglong
 * @2016年7月14日
 */
public class AccessPermissionInterceptor  implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Integer isadmin = (Integer)session.getAttribute(WebConstant.SESSION_KEY_LOGIN_IS_ADMIN);
		System.out.println("是否是SAAS管理员-----"+isadmin);
		if(handler instanceof HandlerMethod){//是请求方法才进行判断
			HandlerMethod method = (HandlerMethod)handler;
			System.out.println("类注解-------"+method.getBean().getClass().getAnnotation(AccessPermission.class));
			AccessPermission accessPermission = method.getMethod().getAnnotation(AccessPermission.class);
			//是否有访问权限限制
			if(accessPermission==null){//无
				accessPermission = method.getBean().getClass().getAnnotation(AccessPermission.class);
				if(accessPermission==null){//类上也无权限限制
					return true;
				}
			}
			
			String[] allownRoleId = accessPermission.allownRoleCode();
			//是否需要访问权限
			if(allownRoleId.length==0){//不需要特殊访问权限
				return true;
			}
			//需要访问权限的业务
			//TODO 是否开通了金融账户
			
			
			
			//TODO 是否拥有该方法的访问权限
			List<RoleDTO> tfsRoleDTOList = (List<RoleDTO>)session.getAttribute(WebConstant.SESSION_KEY_JR_ROLE_LIST);
			
			boolean hasPermission = isAllown(allownRoleId, tfsRoleDTOList);
			if(!hasPermission){
				System.out.println("=============+没有访问权限");
				return true;
			}
			
		}
		System.out.println("=============+可以访问");
		return true;
	}
	/**
	 * 是否允许访问
	 * @return
	 */
	private boolean isAllown(String[] function,List<RoleDTO> roleList){
		if(roleList==null||roleList.size()==0){
			return false;
		}
		for(String roleCode : function){
			for(RoleDTO itemDto : roleList){
				if(itemDto.getRoleCode().equals(roleCode)){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
