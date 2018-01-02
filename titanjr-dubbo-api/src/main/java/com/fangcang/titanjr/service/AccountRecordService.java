package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.RecordRechargeRequest;
import com.fangcang.titanjr.dto.request.RecordTransferRequest;

/**
 * 记账
 * @author luoqinglong
 * @date 2017年12月28日
 */
public interface AccountRecordService {
	/**
	 * 充值记账
	 * @param recordRechargeRequest
	 */
	void recharge(RecordRechargeRequest recordRechargeRequest);
	
	void transfer(RecordTransferRequest recordTransferRequest);
	
	void freeze();
	void unFreeze();
	void withdraw();
	void refund();
}
