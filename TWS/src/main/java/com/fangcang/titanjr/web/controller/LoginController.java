package com.fangcang.titanjr.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.SMSType;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.request.CheckUserRequest;
import com.fangcang.titanjr.dto.request.GetCheckCodeRequest;
import com.fangcang.titanjr.dto.request.PassLoginRequest;
import com.fangcang.titanjr.dto.request.SendCodeRequest;
import com.fangcang.titanjr.dto.request.SmsLoginRequest;
import com.fangcang.titanjr.dto.response.CheckUserResponse;
import com.fangcang.titanjr.dto.response.GetCheckCodeResponse;
import com.fangcang.titanjr.dto.response.PassLoginResponse;
import com.fangcang.titanjr.dto.response.SendCodeResponse;
import com.fangcang.titanjr.dto.response.SmsLoginResponse;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.pojo.LoginPojo;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;

/**
 * 登录,不需要登录就可以访问的controller
 * @author luoqinglong
 * @date   2016年12月23日
 */
@Controller
@RequestMapping("/ex")
public class LoginController extends BaseController{
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);
	
	@Resource
	private TitanFinancialUserService userService;
	
	@Resource
    private TitanFinancialOrganService organService;
	
    @Resource
    private TitanFinancialSendSMSService sendSMSService;
	/**
	 * 登录页
	 * @return
	 */
	@RequestMapping(value = "/login")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String login(String returnUrl,Model model){
		model.addAttribute("returnUrl", returnUrl);
		return "login";
	}
	
	/**
	 * 密码登录
	 * @return
	 */
	@RequestMapping(value = "/passlogin")
	@ResponseBody
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String passlogin(LoginPojo login){
		if(!StringUtil.isValidString(login.getLoginUserName())){
			return toJson(putSysError("登录用户名不能为空"));
		}
		if(!StringUtil.isValidString(login.getPassword())){
			return toJson(putSysError("登录密码不能为空"));
		}
		
		//校验用户名和密码
		PassLoginRequest passLoginRequest = new PassLoginRequest();
		passLoginRequest.setLoginUsername(login.getLoginUserName());
		passLoginRequest.setPassword(login.getPassword());
		try {
			PassLoginResponse passLoginResponse = userService.passLogin(passLoginRequest);
			if(passLoginResponse.isResult()){
				
				//保存登录表示到session
				getSession().setAttribute(WebConstant.SESSION_KEY_JR_USERID, passLoginResponse.getUserId().toString());
				getSession().setAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME, passLoginResponse.getUserLoginName());
				getSession().setAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID, passLoginResponse.getTfsuserId().toString());
				
				return checkUser();
			}else{
				putSysError("用户名或者密码错误");
				return toJson();
			}
		} catch (GlobalServiceException e) {
			LOGGER.error("用户登录失败(密码登录)，登录参数："+Tools.gsonToString(login), e);
			putSysError("登录异常，请重试");
			return toJson();
		}
	}
	private String checkUser() throws GlobalServiceException{
		//检查用户状态
		String tfsUserId = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		CheckUserRequest checkUserRequest = new CheckUserRequest();
		checkUserRequest.setTfsUserId(Integer.valueOf(tfsUserId));
		CheckUserResponse checkUserResponse = userService.checkUser(checkUserRequest);
		if(checkUserResponse.isResult()){
			putSuccess("登录成功");
			return toJson();
		}else{
			//账号异常
			String returnUrl = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+getRequest().getContextPath()+"/reg/user-state.shtml";
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("returnUrl", returnUrl);
			putSysError(checkUserResponse.getReturnMessage(), data);
			return toJson();
		}
	}
	/**
	 * 动态码登录
	 * @return
	 */
	@RequestMapping(value = "/smslogin")
	@ResponseBody
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String smslogin(LoginPojo login){
		if(!StringUtil.isValidString(login.getLoginUserName())){
			return toJson(putSysError("登录用户名不能为空"));
		}
		if(!StringUtil.isValidString(login.getPassword())){
			return toJson(putSysError("校验码不能为空"));
		}
		SmsLoginRequest smsLoginRequest = new SmsLoginRequest();
		smsLoginRequest.setLoginUsername(login.getLoginUserName());
		smsLoginRequest.setSmsCode(login.getPassword());
		try {
			SmsLoginResponse smsLoginResponse = userService.smsLogin(smsLoginRequest);
			if(smsLoginResponse.isResult()){
				putSuccess("登录成功");
				//保存登录表示到session
				getSession().setAttribute(WebConstant.SESSION_KEY_JR_USERID, smsLoginResponse.getUserId().toString());
				getSession().setAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME, smsLoginResponse.getUserLoginName());
				getSession().setAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID, smsLoginResponse.getTfsuserId().toString());
				
				return checkUser();
			}else{
				putSysError("用户名或者验证码错误");
				return toJson();
			}
		} catch (GlobalServiceException e) {
			LOGGER.error("用户登录失败(动态码登录)，登录参数："+Tools.gsonToString(login), e);
			putSysError("登录异常，请重试");
			return toJson();
		}
	}
	/***
	 * 用户登录状态和登录信息
	 * @return
	 */
	@RequestMapping(value = "/loginService", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String loginService(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String tfsUserId = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		String loginusername = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME);
		if(StringUtil.isValidString(tfsUserId)){
			dataMap.put("tfsuserId", tfsUserId);
			dataMap.put("loginusername", loginusername);
			dataMap.put("islogin", "1");
			putSuccess("已经登录", dataMap);
		}else{
			
			dataMap.put("islogin", "0");
			putSysError("未登录",dataMap);
		}
		
		return toJson();
	}
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value = "/loginout", produces = "text/html;charset=UTF-8")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String loginout(){
		//清理资源
		getSession().removeAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		getSession().removeAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME);
		
		return "redirect:/ex/login.shtml"; 
	}
	/***
	 * 发送验证码
	 * @param receiveAddress
	 * @param msgType
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value = "/sendCode")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String sendCode(String receiveAddress,Integer msgType){
    	SendCodeRequest sendRegCodeRequest = new SendCodeRequest();
    	if(StringUtil.isValidString(receiveAddress)){
    		sendRegCodeRequest.setReceiveAddress(receiveAddress);
    	}else {
    		return toJson(putSysError("参数错误"));
    	}
    	if(!(Tools.isEmailAddress(receiveAddress)||Tools.isPhone(receiveAddress))){
    		return toJson(putSysError("手机号码或者邮箱地址格式不正确"));
    	}
    	sendRegCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
    	msgType = msgType==null?SMSType.REG_CODE.getType():msgType;
    	GetCheckCodeRequest getCheckCodeRequest = new GetCheckCodeRequest();
    	getCheckCodeRequest.setMsgType(msgType);
    	getCheckCodeRequest.setReceiveAddress(receiveAddress);
    	String regCode;
    	try {
			GetCheckCodeResponse getCheckCodeResponse = organService.getCheckCode(getCheckCodeRequest);
			if(getCheckCodeResponse.isResult()){
				regCode = getCheckCodeResponse.getCheckCode();
			}else{
				return toJson(putSysError(getCheckCodeResponse.getReturnMessage()));
			}
		} catch (GlobalServiceException e) {
			LOGGER.error("send code fail ,param|receiveAddress:"+receiveAddress+",msgType:"+msgType, e);
			return toJson(putSysError("验证码获取失败"));
		}
    	
    	if(msgType==SMSType.REG_CODE.getType()){//注册
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在申请开通泰坦金融服务，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融注册验证码");
    	}else if(msgType==SMSType.PAY_PASSWORD_MODIFY.getType()){//修改付款密码
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在修改泰坦金融的付款密码，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融修改付款密码");
    	}else if(msgType==SMSType.LOGIN_PASSWORD_MODIFY.getType()){//修改登录密码
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在修改泰坦金融的登录密码，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融修改登录密码");
    	}else if(msgType==SMSType.LOGIN_BY_CODE.getType()){//动态码登录
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在使用动态验证码登录泰坦金融，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("动态验证码登录");
    	}
    	
    	SendCodeResponse sendRegCodeResponse = sendSMSService.sendCode(sendRegCodeRequest);
    	if(sendRegCodeResponse.isResult()){
    		return toJson(putSuccess("验证码发送成功"));
    	}else{
    		return toJson(putSysError(sendRegCodeResponse.getReturnMessage()));
    	}
    	
	}
	/***
	 * 忘记登录密码页面
	 * @return
	 */
   	@RequestMapping(value = "/u-login-pwd-forget")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String uLoginPwdForget(){
   		
   		
		return  "user/u-login-pwd-forget";
	}
}
