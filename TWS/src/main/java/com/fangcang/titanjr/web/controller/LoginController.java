package com.fangcang.titanjr.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.request.PassLoginRequest;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.pojo.LoginPojo;
import com.fangcang.util.StringUtil;

/**
 * 登录
 * @author luoqinglong
 * @date   2016年12月23日
 */
@Controller
public class LoginController extends BaseController{
	
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
		//PassLoginResponse passLoginResponse = userService.passLogin(passLoginRequest);
		
		//保存登录表示到session
		
		
		return "login";
	}
	
	/**
	 * 动态码登录
	 * @return
	 */
	@RequestMapping(value = "/smslogin")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String smslogin(LoginPojo login){
		
		return "login";
	}
	
}
