package com.fangcang.titanjr.pay.services.listener;
/**
 * 贷款通知监听器
 * @author wengxitao
 *
 */
public interface TitanLoanServiceListener {

	public void loanSucceed(String orderNo);

	public void loanFailure(String orderNo);
}
