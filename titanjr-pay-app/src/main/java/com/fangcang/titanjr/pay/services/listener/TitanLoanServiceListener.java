package com.fangcang.titanjr.pay.services.listener;
/**
 * 贷款通知监听器
 * @author wengxitao
 *
 */
public interface TitanLoanServiceListener {
	/**
	 * 贷款申请终审通过或放款成功
	 * @param buessNo  泰坦金融的贷款订单号
	 * @param state
	 */
	public boolean loanSucceed(String buessNo,int state);
	/**
	 * 贷款申请失败
	 * @param buessNo 泰坦金融的贷款订单号
	 * @param state
	 * @param msg
	 */
	public boolean loanFailure(String buessNo,int state,String msg);
}
