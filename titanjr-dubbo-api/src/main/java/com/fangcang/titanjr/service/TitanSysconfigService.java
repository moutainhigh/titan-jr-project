package com.fangcang.titanjr.service;

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
}
