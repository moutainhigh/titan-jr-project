package com.fangcang.titanjr.pay.strategy.pay;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

@Component
public class QuickPay implements PayStrategy {
	
	private static final Log log = LogFactory.getLog(QuickPay.class);
	
	private RSGatewayInterfaceService rsGatewayInterfaceService;
	
	private TitanPaymentService titanPaymentService;

	@Override
	public String pay(RechargeDataDTO rechargeDataDTO, TitanPaymentRequest titanPaymentRequest, Model model) {
		
		rsGatewayInterfaceService = (RSGatewayInterfaceService) SpringContextUtil.getBean("rsGatewayInterfaceService");
		titanPaymentService = (TitanPaymentService) SpringContextUtil.getBean("titanPaymentService");
		
		QuickPaymentRequest quickPaymentRequest = new QuickPaymentRequest();
		BeanUtils.copyProperties(rechargeDataDTO, quickPaymentRequest);
    	QuickPaymentResponse quickPaymentResponse = rsGatewayInterfaceService.quickPay(quickPaymentRequest);
    	try {
    		if(quickPaymentResponse.isSuccess() && "1".equals(titanPaymentRequest.getIsSaveHistorypay())){
    			titanPaymentService.saveCommonPayHistory(titanPaymentRequest);
    		}
		} catch (Exception e) {
			log.error("保存快捷支付常用卡历史记录失败：", e);
		}
    	model.addAttribute("quickPaymentResponse", quickPaymentResponse);
    	model.addAttribute("rechargeDataDTO", rechargeDataDTO);
    	
		return JsonUtil.objectToJson(quickPaymentResponse);
    	
	}

}
