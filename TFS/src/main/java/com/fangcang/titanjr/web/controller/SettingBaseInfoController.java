package com.fangcang.titanjr.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.LoginPasswordRequest;
import com.fangcang.titanjr.dto.request.OrgUpdateRequest;
import com.fangcang.titanjr.dto.request.UpdateCheckCodeRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.request.VerifyCheckCodeRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.VerifyCheckCodeResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;
/**
 * 基础信息
 * @author luoqinglong
 * @2016年7月1日
 */
@Controller
@RequestMapping("/setting")
@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
public class SettingBaseInfoController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(SettingBaseInfoController.class);
	
	@Resource
    private TitanFinancialOrganService organService;
	
	@Resource
	private TitanFinancialUserService userService;
	
	/**
	 * 基础信息
	 * @return
	 */
	
	@RequestMapping("/base-info")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_VIEW_39})
	public String baseInfo(Model model){
		
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		if(StringUtil.isValidString(getTfsUserId())){
			userInfoQueryRequest.setTfsUserId(Integer.valueOf(getTfsUserId()));
			UserInfoPageResponse userInfoPageResponse = userService.queryUserInfoPage(userInfoQueryRequest);
			TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
			
			FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
			organQueryRequest.setOrgCode(titanUser.getOrgcode());
			FinancialOrganResponse financialOrganResponse = organService.queryFinancialOrgan(organQueryRequest);
			FinancialOrganDTO financialOrganDTO = financialOrganResponse.getFinancialOrganDTO();
			model.addAttribute("financialOrganDTO", financialOrganDTO);
			for(OrgImageInfo item : financialOrganDTO.getOrgImageInfoList()){
				if(item.getSizeType()==10){
					model.addAttribute("small_img_10", item.getImageURL());
				}else if(item.getSizeType()==50){
					model.addAttribute("big_img_50", item.getImageURL());
				}
			}
			//管理员信息
			UserInfoQueryRequest adminRequest = new UserInfoQueryRequest();
			adminRequest.setOrgCode(titanUser.getOrgcode());
			adminRequest.setIsadmin(1);
			UserInfoPageResponse adminResponse = userService.queryUserInfoPage(adminRequest);
			if(adminResponse.getTitanUserPaginationSupport().getItemList().size()>0){
				TitanUser adminUser = adminResponse.getTitanUserPaginationSupport().getItemList().get(0);
				model.addAttribute("adminUser", adminUser);
			}
			return "setting/base-info";
		}else{
			//登录者没有金服id
			return "error";
		}
		
	}
	/**
	 * 跳转到修改密码页
	 * @return
	 */
	@RequestMapping("/login-pwd")
	public String loginPwd(){
		return "/setting/login-pwd";
	}
	/**
	 * 忘记密码页
	 * @return
	 */
	@RequestMapping("/login-pwd-forget")
	public String loginPwdForget(Model model){
		int tfsUserId = Integer.valueOf(getTfsUserId());
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(tfsUserId);
		UserInfoPageResponse userInfoPageResponse = userService.queryUserInfoPage(userInfoQueryRequest);
		TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
		model.addAttribute("tfsUserLoginName", titanUser.getUserloginname());
		return "/setting/login-pwd-forget";
	}
	/**
	 * 记得密码页
	 * @return
	 */
	@RequestMapping("/login-pwd-remember")
	public String loginPwdRemember(){
		return "/setting/login-pwd-remember";
	}
	/**
	 * 通过原密码修改登录密码
	 * @param oldLoginPassword 旧登录密码
	 * @param newLoginPassword 新登录密码
	 * @return
	 */
	@RequestMapping("/set-login-password-password")
	@ResponseBody
	public String setLoginPasswordByPassword(String oldLoginPassword,String newLoginPassword){
		if(!StringUtil.isValidString(oldLoginPassword)){
			putSysError("旧密码不能为空");
			return toJson();
		}
		if(!StringUtil.isValidString(newLoginPassword)){
			putSysError("新密码不能为空");
			return toJson();
		}
		
		//旧密码验证
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		if(StringUtil.isValidString(getTfsUserId())){
			int tfsUserId = Integer.valueOf(getTfsUserId());
			userInfoQueryRequest.setTfsUserId(tfsUserId);
			UserInfoPageResponse userInfoPageResponse = userService.queryUserInfoPage(userInfoQueryRequest);
			TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
			
			if(StringUtil.isValidString(titanUser.getPassword())&&titanUser.getPassword().equals(MD5.MD5Encode(oldLoginPassword))){
				LoginPasswordRequest loginPasswordRequest = new LoginPasswordRequest();
				loginPasswordRequest.setTfsuserid(tfsUserId);
				loginPasswordRequest.setNewLoginPassword(newLoginPassword);
				try {
					BaseResponseDTO response = userService.saveLoginPassword(loginPasswordRequest);
					if(response.isResult()){
						putSuccess();
					}else{
						putSysError(response.getReturnMessage());
					}
				} catch (GlobalServiceException e) {
					Map<String, Object> errorMap= new HashMap<String, Object>();
					errorMap.put("tfsUserId", tfsUserId);
					errorMap.put("oldLoginPassword", oldLoginPassword);
					errorMap.put("newLoginPassword", newLoginPassword);
					LOG.error("通过原始密码修改登录密码错误，参数:"+JSONSerializer.toJSON(errorMap).toString(), e);
					putSysError(WebConstant.CONTROLLER_ERROR_MSG);
				}
				return toJson();
			}else{
				putSysError("原登录密码不正确，请重新输入原登录密码");
				return toJson();
			}
		}else{
			putSysError("参数错误，请重试");
			return toJson();
		}
	}
	
	
	/**
	 * 通过用户名修改登录密码
	 * @return
	 */
	@RequestMapping("/set-login-password-login-name")
	@ResponseBody
	public String setLoginPasswordByLoginName(String code,String newLoginPassword){
		//验证码验证
		if(!(StringUtil.isValidString(code)&&StringUtil.isValidString(newLoginPassword))){
			return toJson(putSysError("参数错误"));
		}
    	
		VerifyCheckCodeRequest verifyCheckCodeRequest = new VerifyCheckCodeRequest();
    	verifyCheckCodeRequest.setReceiveAddress(getUserName());
    	verifyCheckCodeRequest.setInputCode(code);
    	VerifyCheckCodeResponse verifyCheckCodeResponse = organService.verifyCheckCode(verifyCheckCodeRequest);
    	if(verifyCheckCodeResponse.isResult()){
    		int tfsUserId = Integer.valueOf(getTfsUserId());
    		LoginPasswordRequest loginPasswordRequest = new LoginPasswordRequest();
			loginPasswordRequest.setTfsuserid(tfsUserId);
			loginPasswordRequest.setNewLoginPassword(newLoginPassword);
			try {
				BaseResponseDTO response = userService.saveLoginPassword(loginPasswordRequest);
				if(response.isResult()){
					if(verifyCheckCodeResponse.getCodeId()>0){
						UpdateCheckCodeRequest updateCheckCodeRequest = new UpdateCheckCodeRequest();
						updateCheckCodeRequest.setCodeId(verifyCheckCodeResponse.getCodeId());
						updateCheckCodeRequest.setIsactive(0);
						organService.useCheckCode(updateCheckCodeRequest);
					}
					putSuccess("密码修改成功");
					return toJson();
				}else{
					putSysError(response.getReturnMessage());
				}
			} catch (GlobalServiceException e) {
				Map<String, Object> errorMap= new HashMap<String, Object>();
				errorMap.put("tfsUserId", tfsUserId);
				errorMap.put("code", code);
				errorMap.put("newLoginPassword", newLoginPassword);
				LOG.error("通过原始密码修改登录密码错误，参数:"+JSONSerializer.toJSON(errorMap).toString(), e);
				putSysError(WebConstant.CONTROLLER_ERROR_MSG);
			}
    		return toJson(putSuccess("验证成功"));
    	}else{
    		return toJson(putSysError(verifyCheckCodeResponse.getReturnMessage()));
    	}
	}
	
	/**
	 * 修改企业联系信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/set-enterprise-info")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String setEnterpriseInfo(String connect,String mobile){
		if(!(StringUtil.isValidString(connect)||StringUtil.isValidString(mobile))){
			return toJson(putSysError("参数错误"));
		}
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(Integer.valueOf(getTfsUserId()));
		UserInfoPageResponse userInfoPageResponse = userService.queryUserInfoPage(userInfoQueryRequest);
		TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
		
		OrgUpdateRequest orgUpdateRequest = new OrgUpdateRequest();
		orgUpdateRequest.setOrgCode(titanUser.getOrgcode());
		
		orgUpdateRequest.setMobiletel(Tools.blanktoNull(mobile));
		orgUpdateRequest.setConnect(Tools.blanktoNull(connect));
		try {
			BaseResponseDTO response = organService.updateOrg(orgUpdateRequest);
			if(response.isResult()){
				putSuccess("企业信息修改成功");
				return toJson();
			}else{
				putSysError(response.getReturnMessage());
			}
		} catch (GlobalServiceException e) {
			Map<String, Object> errorMap= new HashMap<String, Object>();
			errorMap.put("connect", connect);
			errorMap.put("mobile", mobile);
			errorMap.put("tfsuserId", getTfsUserId());
			errorMap.put("orgCode", titanUser.getOrgcode());
			LOG.error("修改企业联系信息，参数:"+JSONSerializer.toJSON(errorMap).toString(), e);
			putSysError(WebConstant.CONTROLLER_ERROR_MSG);
		}
		return toJson();
	}
}
