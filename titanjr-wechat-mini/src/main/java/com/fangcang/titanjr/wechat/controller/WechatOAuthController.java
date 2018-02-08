/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName WechatOAuthController.java
 * @author Jerry
 * @date 2018年2月8日 下午4:15:53  
 */
package com.fangcang.titanjr.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jerry
 * @date 2018年2月8日 下午4:15:53  
 */
@RequestMapping("oauth")
@Controller
public class WechatOAuthController {
	
	private final static Logger logger = LoggerFactory.getLogger(WechatOAuthController.class);
	
	/**
	 * 微信的oauth2认证
	 * @author Jerry
	 * @date 2018年2月8日 下午4:25:58
	 */
	@RequestMapping("/getWechatCode")
	public String getWechatCode(HttpServletRequest request, Model model){
		
		String appid = request.getParameter("appid");
		String redirect_uri = request.getParameter("redirect_uri");
		String response_type = request.getParameter("response_type");
		String scope = request.getParameter("scope");
		String state = request.getParameter("state");
		logger.info("【微信的oauth2认证】传入参数：{}", "\nappid="+appid+"\nredirect_uri="+redirect_uri+"\nresponse_type="+response_type+"\nscope="+scope+"\nstate="+state);
		
		model.addAttribute("appid", appid);
		model.addAttribute("redirect_uri", redirect_uri);
		model.addAttribute("response_type", response_type);
		model.addAttribute("scope", scope);
		model.addAttribute("state", state);
		
		return "get-wx-code";
		
	}

}
