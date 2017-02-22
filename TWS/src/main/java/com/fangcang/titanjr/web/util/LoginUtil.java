package com.fangcang.titanjr.web.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;

import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialUserService;

public class LoginUtil {
	/**
	 * 登录时保存登录用户信息到session
	 */
	public static void putLoginInfo(HttpSession session,TitanFinancialUserService userService, String userId,String loginUsername,String tfsUserId){
		session.setAttribute(WebConstant.SESSION_KEY_JR_USERID, userId);
		session.setAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME, loginUsername);
		session.setAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID, tfsUserId);
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(Integer.valueOf(tfsUserId));
		UserInfoResponse userInfoResponse = userService.queryFinancialUser(userInfoQueryRequest);
		List<RoleDTO> activeRoleList = new ArrayList<RoleDTO>();
		if (CollectionUtils.isNotEmpty(userInfoResponse.getUserInfoDTOList())) {
            UserInfoDTO userInfoDTO = userInfoResponse.getUserInfoDTOList().get(0);
            if(userInfoDTO.getRoleDTOList()!=null&&userInfoDTO.getRoleDTOList().size()>0){
            	for(RoleDTO item : userInfoDTO.getRoleDTOList()){
            		if(item.getIsActive()==1){
            			activeRoleList.add(item);
            		}
            	}
            }
        }
		session.setAttribute(WebConstant.SESSION_KEY_JR_ROLE_LIST, activeRoleList);//金服用户角色列表
	}
}
