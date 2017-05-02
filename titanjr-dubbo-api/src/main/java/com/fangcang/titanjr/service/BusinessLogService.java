package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.AddPayLogRequest;


/**
 * 业务流程日志
 * @author luoqinglong
 * @date   2017年4月13日
 */
public interface BusinessLogService {
	/***
	 * 流程日志
	 * @param payStep 
	 * @param orEnum 单号类型
	 * @param orderId 单号
	 */
	void addPayLog(AddPayLogRequest addPayLogRequest);
	
}
