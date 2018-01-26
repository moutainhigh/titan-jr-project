package com.titanjr.checkstand.util;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.request.RBBindCardQueryRequest;
import com.titanjr.checkstand.request.RBCardAuthRequest;
import com.titanjr.checkstand.request.RBCardBINQueryRequest;
import com.titanjr.checkstand.request.RBQuickPayConfirmRequest;
import com.titanjr.checkstand.request.RBQuickPayQueryRequest;
import com.titanjr.checkstand.request.RBQuickPayRefundQueryRequest;
import com.titanjr.checkstand.request.RBQuickPayRefundRequest;
import com.titanjr.checkstand.request.RBQuickPayRequest;
import com.titanjr.checkstand.request.RBReSendMsgRequest;
import com.titanjr.checkstand.request.RBUnBindCardRequest;
import com.titanjr.checkstand.request.TLNetBankPayRequest;
import com.titanjr.checkstand.request.TLNetBankOrderRefundRequest;
import com.titanjr.checkstand.request.TLNetBankPayQueryRequest;
import com.titanjr.checkstand.request.TLNetBankRefundQueryRequest;
import com.titanjr.checkstand.respnse.TitanQrCodePayResponse;

public class SignMsgBuilder {
	
	private final static Logger logger = LoggerFactory.getLogger(SignMsgBuilder.class);
	
	
	public static String getNetBanPaySignMsg(TLNetBankPayRequest tlNetBankPayRequest) {
		
		StringBuffer sign = new StringBuffer();
		if(tlNetBankPayRequest != null){
			sign.append("inputCharset=");
			sign.append(tlNetBankPayRequest.getInputCharset());
			sign.append("&pickupUrl=");
			sign.append(tlNetBankPayRequest.getPickupUrl());
			sign.append("&receiveUrl=");
			sign.append(tlNetBankPayRequest.getReceiveUrl());
			sign.append("&version=");
			sign.append(tlNetBankPayRequest.getVersion());
			sign.append("&language=");
			sign.append(tlNetBankPayRequest.getLanguage());
			sign.append("&signType=");
			sign.append(tlNetBankPayRequest.getSignType());
			sign.append("&merchantId=");
			sign.append(tlNetBankPayRequest.getMerchantId());
			sign.append("&orderNo=");
			sign.append(tlNetBankPayRequest.getOrderNo());
			sign.append("&orderAmount=");
			sign.append(tlNetBankPayRequest.getOrderAmount());
			sign.append("&orderCurrency=");
			sign.append(tlNetBankPayRequest.getOrderCurrency());
			sign.append("&orderDatetime=");
			sign.append(tlNetBankPayRequest.getOrderDatetime());
			sign.append("&payType=");
			sign.append(tlNetBankPayRequest.getPayType());
			sign.append("&issuerId=");
			sign.append(tlNetBankPayRequest.getIssuerId());
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
	
	public static String getSignMsgForRefundQuery(TLNetBankRefundQueryRequest tlNetBankRefundQueryRequest, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(tlNetBankRefundQueryRequest != null){
			sign.append("version=");
			sign.append(tlNetBankRefundQueryRequest.getVersion());
			sign.append("&signType=");
			sign.append(tlNetBankRefundQueryRequest.getSignType());
			sign.append("&merchantId=");
			sign.append(tlNetBankRefundQueryRequest.getMerchantId());
			sign.append("&orderNo=");
			sign.append(tlNetBankRefundQueryRequest.getOrderNo());
			sign.append("&refundAmount=");
			sign.append(tlNetBankRefundQueryRequest.getRefundAmount());
			sign.append("&mchtRefundOrderNo=");
			sign.append(tlNetBankRefundQueryRequest.getMchtRefundOrderNo());
			sign.append("&key=");
			sign.append(key);
		}
		logger.info("tl-refund-sourceMsg：{}", sign.toString());
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8").toUpperCase();
		logger.info("tl-refund-signMsg：{}", md5Msg);
		return md5Msg;
		
	}
	
	
	public static TreeMap<String,String> quickPaySign(RBQuickPayRequest rbQuickPayRequest, 
			String key, String requestType) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbQuickPayRequest.getMerchant_id());
		params.put("version", rbQuickPayRequest.getVersion());
		params.put("card_no", rbQuickPayRequest.getCard_no());
		params.put("owner", rbQuickPayRequest.getOwner());
		params.put("cert_type", rbQuickPayRequest.getCert_type());
		params.put("cert_no", rbQuickPayRequest.getCert_no());
		params.put("phone", rbQuickPayRequest.getPhone());
		params.put("order_no", rbQuickPayRequest.getOrder_no());
		params.put("transtime", rbQuickPayRequest.getTranstime());
		params.put("currency", rbQuickPayRequest.getCurrency());
		params.put("title", rbQuickPayRequest.getTitle());
		params.put("body", rbQuickPayRequest.getBody());
		params.put("member_id", rbQuickPayRequest.getMember_id());
		params.put("terminal_type", rbQuickPayRequest.getTerminal_type());
		params.put("terminal_info", rbQuickPayRequest.getTerminal_info());
		params.put("notify_url", rbQuickPayRequest.getNotify_url());
		params.put("member_ip", rbQuickPayRequest.getMember_ip());
		params.put("seller_email", rbQuickPayRequest.getSeller_email());
		params.put("total_fee", rbQuickPayRequest.getTotal_fee().toString());
		params.put("token_id", rbQuickPayRequest.getToken_id());
		if(RequestTypeEnum.QUICK_PAY_CREDIT.getKey().equals(rbQuickPayRequest.getRequestType())){
			params.put("cvv2", rbQuickPayRequest.getCvv2());
			params.put("validthru", rbQuickPayRequest.getValidthru());
		}
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbQuickPayRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbQuickPayRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> cardBINQuerySign(RBCardBINQueryRequest rbCardBINQueryRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbCardBINQueryRequest.getMerchant_id());
		params.put("version", rbCardBINQueryRequest.getVersion());
		params.put("card_no", rbCardBINQueryRequest.getCard_no());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbCardBINQueryRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbCardBINQueryRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> quickPayConfirmSign(RBQuickPayConfirmRequest rbQuickPayConfirmRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbQuickPayConfirmRequest.getMerchant_id());
		params.put("order_no", rbQuickPayConfirmRequest.getOrder_no());
		params.put("check_code", rbQuickPayConfirmRequest.getCheck_code());
		params.put("version", rbQuickPayConfirmRequest.getVersion());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbQuickPayConfirmRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbQuickPayConfirmRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> reSendMsgSign(RBReSendMsgRequest rbReSendMsgRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbReSendMsgRequest.getMerchant_id());
		params.put("order_no", rbReSendMsgRequest.getOrder_no());
		params.put("phone", rbReSendMsgRequest.getPhone());
		params.put("version", rbReSendMsgRequest.getVersion());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbReSendMsgRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbReSendMsgRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> quickPayQuerySign(RBQuickPayQueryRequest rbQuickPayQueryRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbQuickPayQueryRequest.getMerchant_id());
		params.put("order_no", rbQuickPayQueryRequest.getOrder_no());
		params.put("version", rbQuickPayQueryRequest.getVersion());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbQuickPayQueryRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbQuickPayQueryRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> quickPayRefundSign(RBQuickPayRefundRequest rbQuickPayRefundRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbQuickPayRefundRequest.getMerchant_id());
		params.put("orig_order_no", rbQuickPayRefundRequest.getOrig_order_no());
		params.put("order_no", rbQuickPayRefundRequest.getOrder_no());
		params.put("amount", rbQuickPayRefundRequest.getAmount());
		params.put("version", rbQuickPayRefundRequest.getVersion());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbQuickPayRefundRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbQuickPayRefundRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> quickPayRefundQuerySign(RBQuickPayRefundQueryRequest 
			rbQuickPayRefundQueryRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbQuickPayRefundQueryRequest.getMerchant_id());
		params.put("order_no", rbQuickPayRefundQueryRequest.getOrder_no());
		params.put("orig_order_no", rbQuickPayRefundQueryRequest.getOrig_order_no());
		params.put("version", rbQuickPayRefundQueryRequest.getVersion());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbQuickPayRefundQueryRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbQuickPayRefundQueryRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> cardAuthSign(RBCardAuthRequest rbCardAuthRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbCardAuthRequest.getMerchant_id());
		params.put("member_id", rbCardAuthRequest.getMember_id());
		params.put("order_no", rbCardAuthRequest.getOrder_no());
		params.put("bind_id", rbCardAuthRequest.getBind_id());
		params.put("terminal_type", rbCardAuthRequest.getTerminal_type());
		params.put("return_url", rbCardAuthRequest.getReturn_url());
		params.put("notify_url", rbCardAuthRequest.getNotify_url());
		params.put("version", rbCardAuthRequest.getVersion());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbCardAuthRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbCardAuthRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> queryBindCardSign(RBBindCardQueryRequest rbBindCardQueryRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbBindCardQueryRequest.getMerchant_id());
		params.put("member_id", rbBindCardQueryRequest.getMember_id());
		params.put("bank_card_type", rbBindCardQueryRequest.getBank_card_type());
		params.put("version", rbBindCardQueryRequest.getVersion());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbBindCardQueryRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbBindCardQueryRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static TreeMap<String,String> unBindCardSign(RBUnBindCardRequest rbUnBindCardRequest, String key) {
		
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("merchant_id", rbUnBindCardRequest.getMerchant_id());
		params.put("member_id", rbUnBindCardRequest.getMember_id());
		params.put("bind_id", rbUnBindCardRequest.getBind_id());
		params.put("version", rbUnBindCardRequest.getVersion());
		
		String signSource = getSignSource(params, key);
		
		logger.info("rb-quickpay-sourceMsg：{}", signSource);
		String md5Sign = MD5.MD5Encode(signSource, "UTF-8");
		logger.info("rb-quickpay-signMsg：{}", md5Sign);
		
		rbUnBindCardRequest.setSign(md5Sign);
		params.put("sign", md5Sign);
		params.put("sign_type", rbUnBindCardRequest.getSign_type());
		
		return params;
		
	}
	
	
	public static String getPayCallbackSignMsg(RechargeResultConfirmRequest confirmRequest, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(confirmRequest != null){
			sign.append("merchantNo=");
			sign.append(confirmRequest.getMerchantNo());
			sign.append("&payType=");
			sign.append(confirmRequest.getPayType());
			sign.append("&orderNo=");
			sign.append(confirmRequest.getOrderNo());
			sign.append("&payOrderNo=");
			sign.append(confirmRequest.getPayOrderNo());
			sign.append("&payStatus=");
			sign.append(confirmRequest.getPayStatus());
			sign.append("&orderTime=");
			sign.append(confirmRequest.getOrderTime());
			sign.append("&orderAmount=");
			sign.append(confirmRequest.getOrderAmount());
			sign.append("&bankCode=");
			sign.append(confirmRequest.getBankCode());
			sign.append("&orderPayTime=");
			sign.append(confirmRequest.getOrderPayTime());
			sign.append("&key=");
			sign.append(key);
		}
		logger.info("tl-gateWayPay-sourceMsg：{}", sign.toString());
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		logger.info("tl-gateWayPay-signMsg：{}", md5Msg);
		return md5Msg;
		
	}
	
	
	public static String tlQrCodePayResponseSignMsg(TitanQrCodePayResponse titanQrPayResponse, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(titanQrPayResponse != null){
			sign.append("merchantNo=");
			sign.append(titanQrPayResponse.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(titanQrPayResponse.getOrderNo());
			sign.append("&orderAmount=");
			sign.append(titanQrPayResponse.getOrderAmount());
			sign.append("&orderTime=");
			sign.append(titanQrPayResponse.getOrderTime());
			sign.append("&payType=");
			sign.append(titanQrPayResponse.getPayType());
			sign.append("&key=");
			sign.append(key);
		}
		logger.info("tl-gateWayPay-sourceMsg：{}", sign.toString());
		String md5Msg = MD5.MD5Encode(sign.toString(), "UTF-8");
		logger.info("tl-gateWayPay-signMsg：{}", md5Msg);
		return md5Msg;
		
	}
	
	
	private static String getSignSource(TreeMap<String,String> params, String key){
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> entry:params.entrySet()){
			if(entry.getValue()!=null&&entry.getValue().length()>0){
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(key);
		return sb.toString();
	}

}
