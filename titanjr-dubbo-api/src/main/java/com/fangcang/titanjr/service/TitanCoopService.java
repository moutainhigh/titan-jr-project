package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.SynOrgInfoRequest;


/***
 * 合作方
 * @author luoqinglong
 * @date   2017年2月28日
 */
public interface TitanCoopService {
	/**
	 * 通知合作方机构信息
	 */
	void notifyCoopOrgInfo();
	void insertSynOrgInfo(SynOrgInfoRequest synOrgInfoRequest);
}
