package com.fangcang.titanjr.service.impl;


import com.fangcang.exception.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.DubboServerJDBCProperties;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.service.TitanSysconfigService;


@Service("titanSysconfigService")
public class TitanSysconfigServiceImpl implements TitanSysconfigService {

	private static final Log log = LogFactory.getLog(TitanSysconfigServiceImpl.class);

	@Override
	public FTPConfigResponse getFTPConfig() {
		FTPConfigResponse response = new FTPConfigResponse();
		response.setFtpServerIp(DubboServerJDBCProperties.getFtpServerIp());
		response.setFtpServerPort(DubboServerJDBCProperties.getFtpServerPort());
		response.setFtpServerUser(DubboServerJDBCProperties.getFtpServerUser());
		response.setFtpServerPassword(DubboServerJDBCProperties.getFtpServerPassword());		
		return response;
	}

	@Override
	public void serviceAccessTest() throws ServiceException {
		log.info("serviceAccessTest，invoke success");
	}
}
