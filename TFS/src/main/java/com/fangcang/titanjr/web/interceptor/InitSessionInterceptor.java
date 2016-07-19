package com.fangcang.titanjr.web.interceptor;

import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.security.acegi.user.UserWithDomain;
import com.fangcang.security.domain.User;
import com.fangcang.session.UserInfo;
import com.fangcang.titanjr.common.enums.FinancialRoleEnum;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.user.RoleWrapper;
import com.fangcang.titanjr.web.user.UserWrapper;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.titanjr.web.util.TranslateUtil;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 此拦截器会将cas传过来的用户的角色信息
 * 只处理未登录时候的登录以及用户设置操作
 * Created by zhaoshan on 2016/4/18.
 */
public class InitSessionInterceptor implements HandlerInterceptor {

    private static final Log log = LogFactory.getLog(InitSessionInterceptor.class);

    @Autowired
    private HessianProxyBeanFactory hessianProxyBeanFactory;

    @Autowired
    private TitanFinancialUserService titanFinancialUserService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        MerchantFacade merchantFacade = hessianProxyBeanFactory.getHessianProxyBean(MerchantFacade.class,
                ProxyFactoryConstants.merchantServerUrl + "merchantFacade");
        // 第一步：用户登录身份的拦截判定
        // 当登录用户和金服来源都为空时候进入判定逻辑
        System.out.println((UserWrapper)session.getAttribute("onlineRoleUser"));
        System.out.println((String)session.getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE));
        if (session.getAttribute("onlineRoleUser") == null || session.getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE) == null) {
            if (session.getAttribute(WebConstant.SESSION_KEY_LOGIN_USER) != null && session.getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_ROLE) == null) {
                //房仓商家系统用户组装判定
                UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.SESSION_KEY_LOGIN_USER);
                UserWrapper roleUser = null;
                SecurityContext ctx = SecurityContextHolder.getContext();
                if (null != ctx) {
                    Authentication auth = ctx.getAuthentication();
                    if (null != auth) {
                        Object principal = auth.getPrincipal();
                        if (principal instanceof UserWithDomain) {
                            User loginUser = ((UserWithDomain) principal).getDomainUser();
                            if (null != loginUser && null != loginUser.getLoginName() && !loginUser.getLoginName().equals("anonymousUser")) {
                                roleUser = (UserWrapper) session.getAttribute("onlineRoleUser");
                                if (null == roleUser || !loginUser.getLoginName().equals(roleUser.getLoginName())) {
                                    roleUser = TranslateUtil.translateUser(loginUser);
                                    session.setAttribute("onlineRoleUser", roleUser);
                                }
                            }
                        }
                    }
                }
                session.setAttribute(WebConstant.SESSION_KEY_LOGIN_IS_ADMIN, roleUser.getAdmin());//是否管理员
                session.setAttribute(WebConstant.SESSION_KEY_LOGIN_USER_LOGINNAME, roleUser.getLoginName());//用户登录名
                session.setAttribute(WebConstant.SESSION_KEY_LOGIN_USER_NAME, roleUser.getName());//用户名
                session.setAttribute(WebConstant.SESSION_KEY_LOGIN_USER_ID, roleUser.getId());//用户Id，必须
                //session.setAttribute("LOGIN_USER_PASSWORD",roleUser.getPassword());
                RoleWrapper userRole = new RoleWrapper();
                userRole.setRoleList(userInfo.getRoles());
                session.setAttribute(WebConstant.SESSION_KEY_LOGIN_USER_ROLE, userRole);//登录用户角色
                MerchantDetailQueryDTO queryDTO = new MerchantDetailQueryDTO();
                queryDTO.setMerchantCode(userInfo.getMerchantCode());
                MerchantResponseDTO response = merchantFacade.queryMerchantDetail(queryDTO);
                if (response != null) {
                    session.setAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_ID, response.getMerchantId());//当前商家id
                    session.setAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE, response.getMerchantCode());//当前商家编码
                    session.setAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME, response.getCompany());//当前商家名称
                    session.setAttribute(WebConstant.SESSION_KEY_CURRENT_THEME, response.getTheme());//商家主题
                    session.setAttribute(WebConstant.SESSION_KEY_CURRENT_LogoUrl, response.getLogoUrl());//商家logo地址
                }

                //当来源为2时候，需要判定是否绑定
                //JR_RESOURCE === 标示登录来源
                if (response.getMerchantCode().equals(RSInvokeConstant.defaultMerchant)) {
                	//从金服官网登录
                    session.setAttribute(WebConstant.SESSION_KEY_JR_RESOURCE, WebConstant.SESSION_KEY_JR_RESOURCE_1_TFS);
                } else {
                	//从Saas登录
                    session.setAttribute(WebConstant.SESSION_KEY_JR_RESOURCE, WebConstant.SESSION_KEY_JR_RESOURCE_2_SAAS);
                }

                //参数说明：若是SAAS登录，来源为2，SaaS登录名作为request的登录名，
                //来源为1时，saas登录名作为request中的绑定用户名查询
                UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
                if (WebConstant.SESSION_KEY_JR_RESOURCE_2_SAAS.equals(session.getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE))) {
                    userInfoQueryRequest.setBindLoginName(roleUser.getLoginName());//SAAS商家的用户名
                } else {
                    userInfoQueryRequest.setUserLoginName(roleUser.getLoginName());//SAAS商家用户名也是金服的 用户名
                }
                //TODO 若账号冻结，此处逻辑有问题，查不出不一定是未绑定
              //JR_BIND_STATUS标示是否绑定，在登录来源为2时判定是否绑定
                UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
                if (CollectionUtils.isNotEmpty(userInfoResponse.getUserInfoDTOList())) {
                    if (session.getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE).equals(WebConstant.SESSION_KEY_JR_RESOURCE_2_SAAS)) {//表明已经绑定成功
                        session.setAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS, "1");
                    }

                    UserInfoDTO userInfoDTO = userInfoResponse.getUserInfoDTOList().get(0);
                    
                    if(userInfoDTO.getRoleDTOList()!=null&&userInfoDTO.getRoleDTOList().size()>0){
                    	List<RoleDTO> activeRoleList = new ArrayList<RoleDTO>();
                    	for(RoleDTO item : userInfoDTO.getRoleDTOList()){
                    		if(item.getIsActive()==1){
                    			activeRoleList.add(item);
                    		}
                    	}
                    	session.setAttribute(WebConstant.SESSION_KEY_JR_ROLE_LIST, activeRoleList);//金服用户角色列表
                    }
                    
                    session.setAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME, userInfoDTO.getUserLoginName());//金服用户登录名
                    session.setAttribute(WebConstant.SESSION_KEY_JR_USERID, userInfoDTO.getUserId());//金服机构id标示
                    session.setAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID, userInfoDTO.getTfsUserId());//金服用户名
                    //如果包含系统运营员，判定当前地址
                    if (containsRole(userInfoDTO.getRoleDTOList(), FinancialRoleEnum.OPERATION.roleCode)) {
                        session.setAttribute(WebConstant.SESSION_KEY_JR_RESOURCE, WebConstant.SESSION_KEY_JR_RESOURCE_3_ADMIN);
                    }
                    //将金服所有角色设置进去
                    for (FinancialRoleEnum roleEnum : FinancialRoleEnum.values()) {
                        if (containsRole(userInfoDTO.getRoleDTOList(), roleEnum.roleCode)) {
                            session.setAttribute("JR_" + roleEnum.roleCode, "1");
                        }
                    }
                } else {//当查询不到时，判定来源是否为2
                    if (session.getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE).equals(WebConstant.SESSION_KEY_JR_RESOURCE_2_SAAS)) {
                        //当bindStatus为0时，JR_ROLE_LIST JR_LOGIN_UESRNAME JR_USERID JR_TFS_USERID 以及权限标示全部为空
                        session.setAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS, "0");
                    } else {//若不是saas登录进来，一定能查到，查不到需重新登录
                        log.error("用户信息设置有误，请检查后重新登录。");
                        httpServletResponse.sendRedirect("/TFS/j_acegi_logout?wait=y");
                        return false;
                    }
                }
            } else {
                log.error("未获取到当前用户或者用户会话过期，重新登录");
                httpServletResponse.sendRedirect("/TFS/j_acegi_logout?wait=y");
                return false;
            }
        }

        //第二步：根据访问地址进行权限判定
        //1.判定金服运营员
        if (httpServletRequest.getRequestURL().indexOf("/admin") >0) {
	        if (!session.getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE).equals(WebConstant.SESSION_KEY_JR_RESOURCE_3_ADMIN)) {//所有金服后台管理以admin进去
	                //转到无权限页面
	            	//httpServletResponse.sendRedirect("/TFS/accessDenied.jsp");
	        	log.info("该用户没有权限访问，登录用户SESSION_KEY_JR_USERID："+session.getAttribute(WebConstant.SESSION_KEY_JR_USERID));
	            httpServletRequest.getRequestDispatcher("/accessDenied.jsp").forward(httpServletRequest, httpServletResponse);
	            return false;
	        }
        }
        //2.判定金服官网用户
        if (session.getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE).equals(WebConstant.SESSION_KEY_JR_RESOURCE_1_TFS)) {//金服官网页面以merchant进去
            if (httpServletRequest.getRequestURL().indexOf("/merchant") < 0) {
                //转到无权限页面
            }
        }
        log.info("session中信息正常，直接返回");
        return true;
    }
    /**
     * 判断登录来源
     */
    private void setJrResource(){
    	//TODO 
    	
    }
    /**
     * 判定是否包含角色code。
     */
    private boolean containsRole(List<RoleDTO> roleDTOList, String roleCode) {
        for (RoleDTO roleDTO : roleDTOList) {
            if (roleCode.equals(roleDTO.getRoleCode())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public void setHessianProxyBeanFactory(HessianProxyBeanFactory hessianProxyBeanFactory) {
        this.hessianProxyBeanFactory = hessianProxyBeanFactory;
    }
}
