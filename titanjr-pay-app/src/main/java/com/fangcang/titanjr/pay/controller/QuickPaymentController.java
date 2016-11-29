package com.fangcang.titanjr.pay.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/quickPayment")
@Controller
public class QuickPaymentController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@RequestMapping("customerNotify")
	public String testQuickPayment(HttpServletRequest request,Model model,HttpServletRequest response){
		
		model.addAttribute("payOrderCode", request.getParameter("payOrderCode"));
		model.addAttribute("businessOrderCode", request.getParameter("businessOrderCode"));
		model.addAttribute("amount",request.getParameter("amount"));
		model.addAttribute("operator",request.getParameter("operator"));
		model.addAttribute("merchantCode",request.getParameter("merchantCode"));
		model.addAttribute("titanPayOrderCode",request.getParameter("titanPayOrderCode"));
		model.addAttribute("payResult",request.getParameter("payResult"));
		model.addAttribute("code",request.getParameter("code"));
		return "checkstand-pay/notify";
	}
	
}
