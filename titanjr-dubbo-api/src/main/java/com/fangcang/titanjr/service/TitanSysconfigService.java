package com.fangcang.titanjr.service;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;

/**
 * 配置文件
 * @author luoqinglong
 * @2016年8月26日
 */
public interface TitanSysconfigService {
	/**
	 * 获取ftp配置信息
	 * @param request
	 * @return
	 */
	FTPConfigResponse getFTPConfig();

	/**
	 * 服务访问测试，测试dubbo服务调用的可用性
	 * 只测试是否能访问，不需调用数据库
	 * @throws ServiceException
     */
	void serviceAccessTest() throws ServiceException;
}
