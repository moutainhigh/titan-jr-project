package com.fangcang.titanjr.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 贷款请求控制器
 * @author wengxitao
 */
@Controller
@RequestMapping("loan")
public class FinancialLoanController 
{
	/**
	 * 进入贷款主页
	 */
	@RequestMapping(value = "/loanMain", method = RequestMethod.GET)
	public String loanMain()
	{
		return "/loan/loan-main";
	}
}
