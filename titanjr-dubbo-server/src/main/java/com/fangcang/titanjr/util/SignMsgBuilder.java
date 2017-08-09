package com.fangcang.titanjr.util;

import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.dto.request.QuickPaymentRequest;
import com.fangcang.titanjr.dto.request.gateway.CardSceurityVerifyRequest;
import com.fangcang.titanjr.dto.request.gateway.ConfirmRechargeRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryBankCardBINRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryQuickPayBindCardRequest;
import com.fangcang.titanjr.dto.request.gateway.ReSendVerifyCodeRequest;
import com.fangcang.titanjr.dto.request.gateway.UnbindBankCardRequest;
import com.fangcang.titanjr.dto.request.gateway.UpdateBankCardPhoneResponseRequest;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;

public final class SignMsgBuilder {
	
	public static String getSignMsgForQuickPay(QuickPaymentRequest quickPaymentRequest) {
		StringBuffer sign = new StringBuffer();
		if(quickPaymentRequest != null){
			sign.append("merchantNo=");
			sign.append(quickPaymentRequest.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(quickPaymentRequest.getOrderNo());
			sign.append("&orderAmount=");
			sign.append(quickPaymentRequest.getOrderAmount());
			sign.append("&payType=");
			sign.append(quickPaymentRequest.getPayType());
			sign.append("&orderTime=");
			sign.append(quickPaymentRequest.getOrderTime());
			sign.append("&signType=");
			sign.append(quickPaymentRequest.getSignType());
			sign.append("&version=");
			sign.append(quickPaymentRequest.getVersion());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
		}
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		return md5Msg;
	}
	
	public static String getSignMsgForConfirmRecharge(ConfirmRechargeRequest confirmRechargeRequest) {
		StringBuffer sign = new StringBuffer();
		if(confirmRechargeRequest !=null){
			sign.append("signType=");
			sign.append(confirmRechargeRequest.getSignType());
			sign.append("&version=");
			sign.append(confirmRechargeRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(confirmRechargeRequest.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(confirmRechargeRequest.getOrderNo());
			sign.append("&checkCode=");
			sign.append(confirmRechargeRequest.getCheckCode());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
		}
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		return md5Msg;
	}
	
	public static String getSignMsgForReSendVerifyCode(ReSendVerifyCodeRequest reSendVerifyCodeRequest) {
		StringBuffer sign = new StringBuffer();
		if(reSendVerifyCodeRequest !=null){
			sign.append("signType=");
			sign.append(reSendVerifyCodeRequest.getSignType());
			sign.append("&version=");
			sign.append(reSendVerifyCodeRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(reSendVerifyCodeRequest.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(reSendVerifyCodeRequest.getOrderNo());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
		}
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		return md5Msg;
	}
	
	public static String getSignMsgForQueryQuickPayBindCard(QueryQuickPayBindCardRequest queryQuickPayBindCardRequest) {
		StringBuffer sign = new StringBuffer();
		if(queryQuickPayBindCardRequest !=null){
			sign.append("signType=");
			sign.append(queryQuickPayBindCardRequest.getSignType());
			sign.append("&version=");
			sign.append(queryQuickPayBindCardRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(queryQuickPayBindCardRequest.getMerchantNo());
			sign.append("&userId=");
			sign.append(queryQuickPayBindCardRequest.getUserId());
			sign.append("&cardNo=");
			sign.append(queryQuickPayBindCardRequest.getCardNo());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
		}
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		return md5Msg;
	}
	
	public static String getSignMsgForQueryBankCardBIN(QueryBankCardBINRequest queryBankCardBINRequest) {
		StringBuffer sign = new StringBuffer();
		if(queryBankCardBINRequest !=null){
			sign.append("signType=");
			sign.append(queryBankCardBINRequest.getSignType());
			sign.append("&version=");
			sign.append(queryBankCardBINRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(queryBankCardBINRequest.getMerchantNo());
			sign.append("&cardNo=");
			sign.append(queryBankCardBINRequest.getCardNo());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
		}
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		return md5Msg;
	}
	
	public static String getSignMsgForUnBindBankCard(UnbindBankCardRequest unbindBankCardRequest) {
		StringBuffer sign = new StringBuffer();
		if(unbindBankCardRequest !=null){
			sign.append("signType=");
			sign.append(unbindBankCardRequest.getSignType());
			sign.append("&version=");
			sign.append(unbindBankCardRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(unbindBankCardRequest.getMerchantNo());
			sign.append("&userId=");
			sign.append(unbindBankCardRequest.getUserId());
			sign.append("&idCode=");
			sign.append(unbindBankCardRequest.getIdCode());
			sign.append("&cardNo=");
			sign.append(unbindBankCardRequest.getCardNo());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
		}
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		return md5Msg;
	}
	
	public static String getSignMsgForUpdatePhone(UpdateBankCardPhoneResponseRequest updatePhoneNumberRequest) {
		StringBuffer sign = new StringBuffer();
		if(updatePhoneNumberRequest !=null){
			sign.append("signType=");
			sign.append(updatePhoneNumberRequest.getSignType());
			sign.append("&version=");
			sign.append(updatePhoneNumberRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(updatePhoneNumberRequest.getMerchantNo());
			sign.append("&userId=");
			sign.append(updatePhoneNumberRequest.getUserId());
			sign.append("&idCode=");
			sign.append(updatePhoneNumberRequest.getIdCode());
			sign.append("&cardNo=");
			sign.append(updatePhoneNumberRequest.getCardNo());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
		}
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		return md5Msg;
	}
	
	
	public static String getSignMsgForCardSceurityVerify(CardSceurityVerifyRequest cardSceurityVerifyRequest) {
		StringBuffer sign = new StringBuffer();
		if(cardSceurityVerifyRequest !=null){
			sign.append("signType=");
			sign.append(cardSceurityVerifyRequest.getSignType());
			sign.append("&version=");
			sign.append(cardSceurityVerifyRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(cardSceurityVerifyRequest.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(cardSceurityVerifyRequest.getOrderNo());
			sign.append("&cardNo=");
			sign.append(cardSceurityVerifyRequest.getCardNo());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
		}
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		return md5Msg;
	}

}
