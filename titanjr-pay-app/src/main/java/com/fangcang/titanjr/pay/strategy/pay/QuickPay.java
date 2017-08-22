package com.fangcang.titanjr.pay.strategy.pay;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.bean.UserBindInfoDTO;
import com.fangcang.titanjr.dto.request.QuickPaymentRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.request.UserBindInfoRequest;
import com.fangcang.titanjr.dto.response.UserBindInfoResponse;
import com.fangcang.titanjr.dto.response.gateway.QuickPaymentResponse;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.service.RSGatewayInterfaceService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.JsonUtil;
import com.fangcang.util.SpringContextUtil;
import com.fangcang.util.StringUtil;

@Component
public class QuickPay implements PayStrategy {
	
	private RSGatewayInterfaceService rsGatewayInterfaceService;
	private TitanPaymentService titanPaymentService;
	private TitanFinancialUserService titanFinancialUserService;

	@Override
	public String pay(RechargeDataDTO rechargeDataDTO, TitanPaymentRequest titanPaymentRequest, Model model) {
		
		rsGatewayInterfaceService = (RSGatewayInterfaceService) SpringContextUtil.getBean("rsGatewayInterfaceService");
		titanPaymentService = (TitanPaymentService) SpringContextUtil.getBean("titanPaymentService");
		titanFinancialUserService = (TitanFinancialUserService) SpringContextUtil.getBean("titanFinancialUserService");
		
		//保存快捷支付卡历史记录
		if(StringUtil.isValidString(titanPaymentRequest.getFcUserid()) && 
				StringUtil.isValidString(titanPaymentRequest.getPartnerOrgCode())){
			
			UserBindInfoDTO userBindInfoDTO = null;
			UserBindInfoRequest userBindInfoRequest = new UserBindInfoRequest();
			userBindInfoRequest.setMerchantcode(titanPaymentRequest.getPartnerOrgCode());
			UserBindInfoResponse userBindInfoResponse = titanFinancialUserService.queryUserBindInfoDTO(userBindInfoRequest);
			if(CollectionUtils.isNotEmpty(userBindInfoResponse.getPaginationSupport().getItemList())){
				userBindInfoDTO = userBindInfoResponse.getPaginationSupport().getItemList().get(0);
			}
			if(userBindInfoDTO != null && titanPaymentRequest.getFcUserid().equals(String.valueOf(
					userBindInfoDTO.getFcUserId()))){
				titanPaymentService.saveQuickcardHistory(titanPaymentRequest);
			}
		}
		
		QuickPaymentRequest quickPaymentRequest = new QuickPaymentRequest();
		BeanUtils.copyProperties(rechargeDataDTO, quickPaymentRequest);
    	QuickPaymentResponse quickPaymentResponse = rsGatewayInterfaceService.quickPay(quickPaymentRequest);
    	model.addAttribute("quickPaymentResponse", quickPaymentResponse);
    	model.addAttribute("rechargeDataDTO", rechargeDataDTO);
    	
		return JsonUtil.objectToJson(quickPaymentResponse);
    	
	}

}
