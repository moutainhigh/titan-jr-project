package com.fangcang.titanjr.facade;

import com.fangcang.titanjr.request.AccountInfoRequest;
import com.fangcang.titanjr.request.CheckPermissionRequest;
import com.fangcang.titanjr.request.ShowPaymentRequest;
import com.fangcang.titanjr.response.CheckAccountResponse;
import com.fangcang.titanjr.response.PermissionResponse;
import com.fangcang.titanjr.response.ShowPaymentResponse;


public interface TitanFinancialPermissionFacade {

	/**
	 * 查询是否有付款的权限
	 * @param permissionRequest 1.付款权限,2.查看权限,3充值权限,4.充值和提现权限,5.理财权限,6.贷款权限,7.消息提醒权限,8.系统运营员权限
	 * @return
	 * @author fangdaikang
	 */
	public PermissionResponse isPermissionToPayment(CheckPermissionRequest permissionRequest);
	
	/**
	 * 能否展示在线支付按钮
	 * @param showPaymentRequest
	 * @return
	 * @author fangdaikang
	 */
	public ShowPaymentResponse isShowPaymentButton(ShowPaymentRequest showPaymentRequest);
	
	/**
	 * 是否开通金融账户
	 * @param accountInfo
	 * @return
	 */
	public CheckAccountResponse isFinanceAccount(AccountInfoRequest accountInfo);
	
}
