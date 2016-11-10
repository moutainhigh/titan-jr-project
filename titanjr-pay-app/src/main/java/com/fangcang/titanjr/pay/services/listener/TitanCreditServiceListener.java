package com.fangcang.titanjr.pay.services.listener;
/**
 * 授信状态通知监听器
 */
public interface TitanCreditServiceListener 
{
	public void creditSucceed(String orderNo);

	public void creditFailure(String orderNo);
}
