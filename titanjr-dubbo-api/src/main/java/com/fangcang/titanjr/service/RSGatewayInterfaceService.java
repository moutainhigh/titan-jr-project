package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.request.gateway.CardSceurityVerifyRequest;
import com.fangcang.titanjr.dto.request.gateway.ConfirmRechargeRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryBankCardBINRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryQuickPayBindCardRequest;
import com.fangcang.titanjr.dto.request.gateway.ReSendVerifyCodeRequest;
import com.fangcang.titanjr.dto.request.gateway.UnbindBankCardRequest;
import com.fangcang.titanjr.dto.request.gateway.UpdateBankCardPhoneResponseRequest;
import com.fangcang.titanjr.dto.response.gateway.CardSceurityVerifyResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryBankCardBINIResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryQuickPayBindCardResponse;
import com.fangcang.titanjr.dto.response.gateway.QuickPaymentResponse;
import com.fangcang.titanjr.dto.response.gateway.ReSendVerifyCodeResponse;
import com.fangcang.titanjr.dto.response.gateway.UnbindBankCardResponse;
import com.fangcang.titanjr.dto.response.gateway.UpdateBankCardPhoneResponse;

/**
 * 融数网关接口服务
 * 
 * @author jerry
 * @date 2017.6.15
 */
public interface RSGatewayInterfaceService {	
	
	/**
	 * 通知网关快捷支付
	 * @author jerry
	 * 
	 * @param rechargeDataDTO
	 * @return
	 */
	public QuickPaymentResponse quickPay(RechargeDataDTO rechargeDataDTO);
	
	
	/**
	 * 通知网关确认充值
	 * @author jerry
	 * 
	 * @return
	 */
	public String confirmRecharge(ConfirmRechargeRequest confirmRechargeRequest);
	
	
	/**
	 * 重发验证码
	 * @author jerry
	 * 
	 * @param reSendVerifyCodeRequest
	 * @return
	 */
	public ReSendVerifyCodeResponse reSendVerifyCode(ReSendVerifyCodeRequest reSendVerifyCodeRequest);
	
	
	/**
	 * 查询快捷支付绑卡信息
	 * @author jerry
	 * 
	 * @param queryQuickPayBindCardRequest
	 * @return
	 */
	public QueryQuickPayBindCardResponse queryQuickPayBindCardInfo(QueryQuickPayBindCardRequest queryQuickPayBindCardRequest);
	
	
	/**
	 * 银行卡解绑
	 * @author jerry
	 * 
	 * @param unbindBankCardRequest
	 * @return
	 */
	public UnbindBankCardResponse unBindBankCard(UnbindBankCardRequest unbindBankCardRequest);
	
	
	/**
	 * 查询银行卡卡的信息
	 * @author jerry
	 * @param queryBankCardBINRequest
	 * @return
	 */
	public QueryBankCardBINIResponse queryBankCardBIN(QueryBankCardBINRequest queryBankCardBINRequest);
	
	
	/**
	 * 更改预留手机号
	 * @author jerry
	 * @param updatePhoneNumberRequest
	 * @return
	 */
	public UpdateBankCardPhoneResponse updateBankCardPhone(UpdateBankCardPhoneResponseRequest updatePhoneNumberRequest);
	
	
	/**
	 * 卡密校验（目前此接口只针对首次支付的【招行借记卡】使用）
	 * @param cardSceurityVerifyRequest
	 * @return
	 */
	public CardSceurityVerifyResponse cardSceurityVerify(CardSceurityVerifyRequest cardSceurityVerifyRequest);
	
}
