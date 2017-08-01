package com.fangcang.titanjr.facade;

import com.fangcang.titanjr.request.BalanceQueryRequest;
import com.fangcang.titanjr.request.TitanOrderPaymentRequest;
import com.fangcang.titanjr.request.UpdateFreezeRequest;
import com.fangcang.titanjr.response.BalanceQueryResponse;
import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.response.TitanOrderPaymentResponse;


public interface TitanFinancialTradeFacade {

	/**
	 * 提供给支付请求方，根据工单号，商家编码和支付来源
	 * 请求得到收银台的地址，不同支付来源地址是不一样的
	 * 返回结果是收银台的地址
	 * @param titanOrderPaymentRequest
	 * @return
     */
	public TitanOrderPaymentResponse getOrderPaymentUrl(TitanOrderPaymentRequest titanOrderPaymentRequest);


	/**
	 * 根据房仓金融机构id查询账户资金信息，现只提供主账户资金信息
	 * @param accountBalanceRequest
	 * @return
     */
	public BalanceQueryResponse queryAccountBalance(BalanceQueryRequest accountBalanceRequest);
	
	
	/**
	 * 更新冻结订单
	 * @author jerry
	 * 
	 * @param payOrderNo 支付订单号
	 * @param operationType 操作类型 1、直接解冻  2、修改解冻日期
	 * @param uNFreezeDate 解冻日期（yyyy-MM-dd）
	 * @return BaseResponse
	 */
	public BaseResponse updateFreezeOrder(UpdateFreezeRequest updateFreezeRequest);

}

