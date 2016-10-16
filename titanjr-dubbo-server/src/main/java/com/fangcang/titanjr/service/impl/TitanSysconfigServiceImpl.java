package com.fangcang.titanjr.service.impl;


import javax.annotation.Resource;


import com.fangcang.titanjr.dao.TitanSysconfigDao;
import com.fangcang.titanjr.dto.request.RSInvokeConfigRequest;
import com.fangcang.titanjr.dto.request.TitanCallBackConfigRequest;
import com.fangcang.titanjr.dto.request.TitanPayMethodRequest;
import com.fangcang.titanjr.dto.response.RSInvokeConfigResponse;
import com.fangcang.titanjr.dto.response.TitanCallBackConfigResponse;
import com.fangcang.titanjr.dto.response.TitanPayMethodResponse;
import com.fangcang.titanjr.service.TitanSysconfigService;

public class TitanSysconfigServiceImpl implements TitanSysconfigService {
	@Resource
	private TitanSysconfigDao titanSysconfigDao;
	
	/**
	 * 请参照RSInvokeInitManagerImpl.java，从中获取
	 */
	@Override
	public RSInvokeConfigResponse getRSInvokeConfig(RSInvokeConfigRequest request) {
		
		return null;
	}

	@Override
	public TitanPayMethodResponse getTitanPayMethod(
			TitanPayMethodRequest request) {
		
		return null;
	}

	@Override
	public TitanCallBackConfigResponse getTitanCallBackConfig(
			TitanCallBackConfigRequest request) {
		 
		return null;
	}
	
}
