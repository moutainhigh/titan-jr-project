package com.fangcang.titanjr.service.impl;


import com.fangcang.titanjr.common.util.DubboServerJDBCProperties;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.service.TitanSysconfigService;

public class TitanSysconfigServiceImpl implements TitanSysconfigService {
	
	@Override
	public FTPConfigResponse getFTPConfig() {
		FTPConfigResponse response = new FTPConfigResponse();
		response.setFtpServerIp(DubboServerJDBCProperties.getFtpServerIp());
		response.setFtpServerPort(DubboServerJDBCProperties.getFtpServerPort());
		response.setFtpServerUser(DubboServerJDBCProperties.getFtpServerUser());
		response.setFtpServerPassword(DubboServerJDBCProperties.getFtpServerPassword());		
		return response;
	}
	
}
