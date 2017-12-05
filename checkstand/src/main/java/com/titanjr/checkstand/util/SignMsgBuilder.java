package com.titanjr.checkstand.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcang.titanjr.common.util.MD5;
import com.titanjr.checkstand.request.TLGateWayPayRequest;
import com.titanjr.checkstand.request.TLNetBankOrderRefundRequest;
import com.titanjr.checkstand.request.TLNetBankPayQueryRequest;

public final class SignMsgBuilder {
	
	private final static Logger logger = LoggerFactory.getLogger(SignMsgBuilder.class);
	
	public static String getSignMsgForGateWayPay(TLGateWayPayRequest tlGateWayPayRequest) {
		
		StringBuffer sign = new StringBuffer();
		if(tlGateWayPayRequest != null){
			sign.append("inputCharset=");
			sign.append(tlGateWayPayRequest.getInputCharset());
			sign.append("&pickupUrl=");
			sign.append(tlGateWayPayRequest.getPickupUrl());
			sign.append("&receiveUrl=");
			sign.append(tlGateWayPayRequest.getReceiveUrl());
			sign.append("&version=");
			sign.append(tlGateWayPayRequest.getVersion());
			sign.append("&language=");
			sign.append(tlGateWayPayRequest.getLanguage());
			sign.append("&signType=");
			sign.append(tlGateWayPayRequest.getSignType());
			sign.append("&merchantId=");
			sign.append(tlGateWayPayRequest.getMerchantId());
			sign.append("&orderNo=");
			sign.append(tlGateWayPayRequest.getOrderNo());
			sign.append("&orderAmount=");
			sign.append(tlGateWayPayRequest.getOrderAmount());
			sign.append("&orderCurrency=");
			sign.append(tlGateWayPayRequest.getOrderCurrency());
			sign.append("&orderDatetime=");
			sign.append(tlGateWayPayRequest.getOrderDatetime());
			sign.append("&payType=");
			sign.append(tlGateWayPayRequest.getPayType());
			sign.append("&issuerId=");
			sign.append(tlGateWayPayRequest.getIssuerId());
			sign.append("&key=");
			sign.append("1234567890");
		}
		logger.info("tl-gateWayPay-sourceMsg：{}", sign.toString());
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8").toUpperCase();
		logger.info("tl-gateWayPay-signMsg：{}", md5Msg);
		return md5Msg;
		
	}
	
	public static String getSignMsgForOrderQuery(TLNetBankPayQueryRequest tlOrderPayQueryRequest, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(tlOrderPayQueryRequest != null){
			sign.append("merchantId=");
			sign.append(tlOrderPayQueryRequest.getMerchantId());
			sign.append("&version=");
			sign.append(tlOrderPayQueryRequest.getVersion());
			sign.append("&signType=");
			sign.append(tlOrderPayQueryRequest.getSignType());
			sign.append("&orderNo=");
			sign.append(tlOrderPayQueryRequest.getOrderNo());
			sign.append("&orderDatetime=");
			sign.append(tlOrderPayQueryRequest.getOrderDatetime());
			sign.append("&queryDatetime=");
			sign.append(tlOrderPayQueryRequest.getQueryDatetime());
			sign.append("&key=");
			sign.append(key);
		}
		logger.info("tl-queryOrder-sourceMsg：{}", sign.toString());
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8").toUpperCase();
		logger.info("tl-queryOrder-signMsg：{}", md5Msg);
		return md5Msg;
		
	}
	
	public static String getSignMsgForOrderRefund(TLNetBankOrderRefundRequest tlNetBankOrderRefundRequest, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(tlNetBankOrderRefundRequest != null){
			sign.append("version=");
			sign.append(tlNetBankOrderRefundRequest.getVersion());
			sign.append("&signType=");
			sign.append(tlNetBankOrderRefundRequest.getSignType());
			sign.append("&merchantId=");
			sign.append(tlNetBankOrderRefundRequest.getMerchantId());
			sign.append("&orderNo=");
			sign.append(tlNetBankOrderRefundRequest.getOrderNo());
			sign.append("&refundAmount=");
			sign.append(tlNetBankOrderRefundRequest.getRefundAmount());
			sign.append("&mchtRefundOrderNo=");
			sign.append(tlNetBankOrderRefundRequest.getMchtRefundOrderNo());
			sign.append("&orderDatetime=");
			sign.append(tlNetBankOrderRefundRequest.getOrderDatetime());
			sign.append("&key=");
			sign.append(key);
		}
		logger.info("tl-refund-sourceMsg：{}", sign.toString());
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8").toUpperCase();
		logger.info("tl-refund-signMsg：{}", md5Msg);
		return md5Msg;
		
	}

}
