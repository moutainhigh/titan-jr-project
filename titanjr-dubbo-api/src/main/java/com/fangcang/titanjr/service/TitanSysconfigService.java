package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.RSInvokeConfigRequest;
import com.fangcang.titanjr.dto.request.TitanCallBackConfigRequest;
import com.fangcang.titanjr.dto.request.TitanPayMethodRequest;
import com.fangcang.titanjr.dto.response.RSInvokeConfigResponse;
import com.fangcang.titanjr.dto.response.TitanCallBackConfigResponse;
import com.fangcang.titanjr.dto.response.TitanPayMethodResponse;

/**
 * 配置文件
 * @author luoqinglong
 * @2016年8月26日
 */
public interface TitanSysconfigService {
	/**
	 * 获取融数sdk配置信息
	 * @param request
	 * @return
	 */
	RSInvokeConfigResponse getRSInvokeConfig(RSInvokeConfigRequest request);
	/***
	 * 获取融数支付参数
	 * @param request
	 * @return
	 */
	TitanPayMethodResponse getTitanPayMethod(TitanPayMethodRequest request);
	/***
	 * 获取支付成功后通知第三方的地址
	 * @param request
	 * @return
	 */
	TitanCallBackConfigResponse getTitanCallBackConfig(TitanCallBackConfigRequest request);
}
