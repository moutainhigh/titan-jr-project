package com.fangcang.titanjr.pay.services.listener;

import com.fangcang.titanjr.common.exception.GlobalServiceException;

/**
 * 授信状态通知监听器
 */
public interface TitanCreditServiceListener 
{
	public void creditSucceed(String orderNo, int status) throws GlobalServiceException;

	public void creditFailure(String orderNo, int status,String msg) throws GlobalServiceException;
}
