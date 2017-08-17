package com.fangcang.titanjr.pay.strategy.pay;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.request.QuickPaymentRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.response.gateway.QuickPaymentResponse;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.service.RSGatewayInterfaceService;
import com.fangcang.util.JsonUtil;
import com.fangcang.util.SpringContextUtil;
import com.fangcang.util.StringUtil;

@Component
public class QuickPay implements PayStrategy {
	
	private RSGatewayInterfaceService rsGatewayInterfaceService;
	private TitanPaymentService titanPaymentService;

	@Override
	public String pay(RechargeDataDTO rechargeDataDTO, TitanPaymentRequest titanPaymentRequest, Model model) {
		
		rsGatewayInterfaceService = (RSGatewayInterfaceService) SpringContextUtil.getBean("rsGatewayInterfaceService");
		titanPaymentService = (TitanPaymentService) SpringContextUtil.getBean("titanPaymentService");
		
		//要校验金融机构信息吗？
		if(StringUtil.isValidString(titanPaymentRequest.getFcUserid())){
			titanPaymentService.saveQuickcardHistory(titanPaymentRequest);
		}
		
		QuickPaymentRequest quickPaymentRequest = new QuickPaymentRequest();
		BeanUtils.copyProperties(rechargeDataDTO, quickPaymentRequest);
    	QuickPaymentResponse quickPaymentResponse = rsGatewayInterfaceService.quickPay(quickPaymentRequest);
    	model.addAttribute("quickPaymentResponse", quickPaymentResponse);
    	model.addAttribute("rechargeDataDTO", rechargeDataDTO);
    	
		return JsonUtil.objectToJson(quickPaymentResponse);
    	
	}

}
