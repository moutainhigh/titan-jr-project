package com.fangcang.titanjr.pay.strategy.pay;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.request.QuickPaymentRequest;
import com.fangcang.titanjr.dto.response.gateway.QuickPaymentResponse;
import com.fangcang.titanjr.service.RSGatewayInterfaceService;
import com.fangcang.util.SpringContextUtil;

@Component
public class QuickPay implements PayStrategy {
	
	private RSGatewayInterfaceService rsGatewayInterfaceService;

	@Override
	public String pay(RechargeDataDTO rechargeDataDTO, String payOrderNo,Model model) {
		
		rsGatewayInterfaceService = (RSGatewayInterfaceService) SpringContextUtil.getBean("rsGatewayInterfaceService");
		QuickPaymentRequest quickPaymentRequest = new QuickPaymentRequest();
		BeanUtils.copyProperties(rechargeDataDTO, quickPaymentRequest);
    	QuickPaymentResponse quickPaymentResponse = rsGatewayInterfaceService.quickPay(quickPaymentRequest);
    	model.addAttribute("quickPaymentResponse", quickPaymentResponse);
    	model.addAttribute("rechargeDataDTO", rechargeDataDTO);
    	return "checkstand-pay/quickPayResult";
		
	}

}
