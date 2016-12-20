package com.fangcang.titanjr.pay.services.listener;

import com.fangcang.titanjr.common.exception.GlobalServiceException;

/**
 * 授信状态通知监听器
 */
public interface TitanCreditServiceListener 
{
	public void creditSucceed(String buessNo, int status) throws GlobalServiceException;

	public void creditFailure(String buessNo, int status,String msg) throws GlobalServiceException;
	
	/**
	 * 协议确认
	 * @param buessNo 授信申请单号(金融的)
	 */
	public void agreementConfirm(String buessNo);
}
