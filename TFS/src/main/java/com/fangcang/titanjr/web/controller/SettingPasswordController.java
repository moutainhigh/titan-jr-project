package com.fangcang.titanjr.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangcang.titanjr.service.TitanFinancialUserService;

/**
 * 付款密码设置
 * @author luoqinglong
 * @2016年7月1日
 */
@Controller
@RequestMapping("/setting")
public class SettingPasswordController extends BaseController{
	
	@Resource
	private TitanFinancialUserService userService;
	
	/***
	 * 付款密码设置
	 * @return
	 */
	@RequestMapping("/pay-set")
	public String paySet(Model model){
		
		//userService.queryFinancialUser(userInfoQueryRequest);
		
		
		
		
		///测试
		model.addAttribute("isSetPayPass","");
		return "setting/pay-set";
	}
	
}
