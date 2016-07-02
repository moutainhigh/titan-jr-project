package com.fangcang.titanjr.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基础信息
 * @author luoqinglong
 * @2016年7月1日
 */
@Controller
@RequestMapping("/setting2")
public class SettingBaseInfoController extends BaseController{

	/**
	 * 基础信息
	 * @return
	 */
	@RequestMapping("/base-info")
	public String baseInfo(){
		
		return "setting/base-info";
	}
}
