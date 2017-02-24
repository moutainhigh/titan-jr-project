package com.fangcang.titanjr.service;

import com.fangcang.exception.ServiceException;

/**
 * 金服各种码生成中心
 * @author luoqinglong
 * @2016年5月6日
 */
public interface TitanCodeCenterService {
	/**
	 * 生成机构编码
	 * @return
	 */
	public String createOrgCode() throws ServiceException;
	/***
	 * 生成泰坦码
	 * @return
	 * @throws ServiceException
	 */
	public String createTitanCode() throws ServiceException;
	
	/**
	 * 生成AccountCode编码
	 * @return
	 * @throws ServiceException
	 */
	public String createTitanAccountCode() throws ServiceException;

	/**
	 * 生成信贷授信申请单号编码
	 * @return
	 * @throws ServiceException
	 */
	public String createLoanCreditOrderNo() throws ServiceException;
}
