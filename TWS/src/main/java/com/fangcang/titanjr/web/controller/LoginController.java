package com.fangcang.titanjr.web.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.request.PassLoginRequest;
import com.fangcang.titanjr.dto.request.SmsLoginRequest;
import com.fangcang.titanjr.dto.response.PassLoginResponse;
import com.fangcang.titanjr.dto.response.SmsLoginResponse;
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
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Resource
	private TitanFinancialUserService userService;
	
	/**
	 * 登录页
	 * @return
	 */
	@RequestMapping(value = "/login")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String login(){
		
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
				putSuccess("登录成功");
				//保存登录表示到session
				getSession().setAttribute(WebConstant.TWS_SESSION_LOGIN_USER_NAME, passLoginResponse.getUserLoginName());
				getSession().setAttribute(WebConstant.TWS_SESSION_TFS_USER_ID, passLoginResponse.getTfsuserId());
				return toJson();
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
				getSession().setAttribute(WebConstant.TWS_SESSION_LOGIN_USER_NAME, login.getLoginUserName());
				return toJson();
			}else{
				putSysError("用户名或者密码错误");
				return toJson();
			}
		} catch (GlobalServiceException e) {
			LOGGER.error("用户登录失败(动态码登录)，登录参数："+Tools.gsonToString(login), e);
			putSysError("登录异常，请重试");
			return toJson();
		}
	}
	
}
