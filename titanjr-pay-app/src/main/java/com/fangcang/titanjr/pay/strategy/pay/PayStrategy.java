package com.fangcang.titanjr.pay.strategy.pay;

import org.springframework.ui.Model;

import com.fangcang.titanjr.dto.bean.RechargeDataDTO;

/**
 * 支付策略
 * @author Jerry
 *
 */
public interface PayStrategy {
	
	/**
	 * 支付
	 * 
	 * @param rechargeDataDTO  充值数据
	 * @param model
	 * @return	网关地址
	 */
	public String pay(RechargeDataDTO rechargeDataDTO, String payOrderNo, Model model);

}
