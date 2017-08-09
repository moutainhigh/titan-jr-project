package com.fangcang.titanjr.pay.strategy.pay;

import org.springframework.ui.Model;

import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;

public class Payment {
	
	private PayStrategy strategy;
	
	public Payment(PayStrategy strategy){
		this.strategy = strategy;
	}
	
	/**
	 * 支付
	 * @param rechargeDataDTO
	 * @param payOrderNo
	 * @param model
	 * @return 支付url
	 */
	public String doPay(RechargeDataDTO rechargeDataDTO, TitanPaymentRequest titanPaymentRequest, Model model){
		return this.strategy.pay(rechargeDataDTO, titanPaymentRequest, model);
	}

}
