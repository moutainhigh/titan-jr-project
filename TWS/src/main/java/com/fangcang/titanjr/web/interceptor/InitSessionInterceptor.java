package com.fangcang.titanjr.web.interceptor;

import com.fangcang.titanjr.common.enums.FinancialRoleEnum;
import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.util.WebConstant;

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
    private TitanFinancialUserService titanFinancialUserService;

    @Autowired
    private TitanFinancialOrganService organService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();

        session.setAttribute(WebConstant.SESSION_KEY_JR_RESOURCE, WebConstant.SESSION_KEY_JR_RESOURCE_1_TFS);
        //参数说明
        UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
        userInfoQueryRequest.setUserId("TJM10000090");
//        UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
//        if (CollectionUtils.isNotEmpty(userInfoResponse.getUserInfoDTOList())) {
//            UserInfoDTO userInfoDTO = userInfoResponse.getUserInfoDTOList().get(0);
//
//            session.setAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME, userInfoDTO.getUserLoginName());//金服用户登录名
//            session.setAttribute(WebConstant.SESSION_KEY_JR_USERID, userInfoDTO.getUserId());//金服机构id标示
//            session.setAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID, userInfoDTO.getTfsUserId());//金服用户名
//            //如果包含系统运营员，判定当前地址
//            if (containsRole(userInfoDTO.getRoleDTOList(), FinancialRoleEnum.OPERATION.roleCode)) {
//                session.setAttribute(WebConstant.SESSION_KEY_JR_RESOURCE, WebConstant.SESSION_KEY_JR_RESOURCE_3_ADMIN);
//            }
//             
//        } else {//跳转登录
//            log.error("用户信息设置有误，请检查后重新登录。");
//            httpServletResponse.sendRedirect("/TWS/j_acegi_logout?wait=y");
//            return false;
//        }
//        log.info("session中信息正常，直接返回");
        return true;
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

}
