package com.fangcang.titanjr.service.impl;

import java.text.MessageFormat;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.SMSTemplate;
import com.fangcang.titanjr.dao.DomainConfigDao;
import com.fangcang.titanjr.dao.TitanDynamicKeyDao;
import com.fangcang.titanjr.dao.TitanOrderExceptionDao;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.dto.request.PayMethodConfigRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.request.SendMessageRequest;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.entity.TitanDynamicKey;
import com.fangcang.titanjr.entity.TitanOrderException;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

@Service("titanFinancialUtilService")
public class TitanFinancialUtilServiceImpl implements TitanFinancialUtilService{

	private static final Log log = LogFactory.getLog(TitanFinancialTradeServiceImpl.class);
	
	@Resource
	private TitanDynamicKeyDao titanDynamicKeyDao;
	
	@Resource
	private DomainConfigDao domainConfigDao;
	
	@Resource 
	private TitanOrderService titanOrderService;
	
	
	@Resource 
	private TitanOrderExceptionDao orderExceptionDao;
	
	@Resource
	private TitanFinancialSendSMSService smsService;
	
	
	SysConfig config;
	
	@Value("${pay.callback.pageurl}")
	private String payCallbackPageUrl;
	
	@Value("${pay.notifyurl}")
	private String payNotifyUrl;
	
	@Value("${tl.netBankPay.confirmPageurl}")
	private String tl_NetBankPay_ConfirmPageurl;
	
	@Value("${tl.netBankPay.notifyurl}")
	private String tl_NetBankPay_Notifyurl;
	
	@Value("${tl.qrCodePay.notifyurl}")
	private String tl_QrCodePay_Notifyurl;
	
	@Value("${tl.wechatPay.notifyurl}")
	private String tl_WechatPay_Notifyurl;
	
	@Value("${rb.quickPay.notifyurl}")
	private String rb_QuickPay_Notifyurl;
	
	@Value("${rb.cardAuth.pageurl}")
	private String rb_CardAuth_Pageurl;
	
	@Value("${rb.cardAuth.notifyurl}")
	private String rb_CardAuth_Notifyurl;
	
	@Value("${send.order.warning}")
	private String isSendWarning;
	
	@Override
	public PaymentUrlResponse getPaymentUrl(PaymentUrlRequest paymentUrlRequest) {
		PaymentUrlResponse paymentUrlResponse = new PaymentUrlResponse();
		if (!GenericValidate.validate(paymentUrlRequest)) {
			log.error("参数校验失败");
			paymentUrlResponse.putParamError();
			return paymentUrlResponse;
		}
		// 生成一对秘钥
		String key = MD5.getSalt();
		// 保存加密私钥
		TitanDynamicKey titanDynamicKey = new TitanDynamicKey();
		titanDynamicKey.setEncryptionkey(key);
		titanDynamicKey.setPayorderno(paymentUrlRequest.getPayOrderNo());
		try {
			titanDynamicKeyDao.delete(titanDynamicKey);
			titanDynamicKeyDao.insert(titanDynamicKey);
		} catch (Exception e) {
			log.error("金融密钥设置失败", e);
			paymentUrlResponse.putErrorResult("dynamicKey_set_error","金融密钥设置失败");
			this.saveOrderException(titanDynamicKey.getPayorderno(),OrderKindEnum.OrderId, OrderExceptionEnum.Save_Order_Get_Desk_Url_Fail, JSONSerializer.toJSON(titanDynamicKey).toString());
			return paymentUrlResponse;
		}
		// 构造参数列表拼接收银台地址
		StringBuffer cashierDeskURL = buildParamList(paymentUrlRequest);
		cashierDeskURL.append("&key=").append(key);
		String sign = MD5.MD5Encode(cashierDeskURL.toString(), "UTF-8");
		cashierDeskURL.delete(cashierDeskURL.indexOf("&key="),
				cashierDeskURL.length());

		cashierDeskURL.append("&sign=").append(sign);
		cashierDeskURL.insert(0, CommonConstant.REQUSET_URL);
		paymentUrlResponse.setUrl(cashierDeskURL.toString());
		paymentUrlResponse.putSuccess();
		return paymentUrlResponse;
	}

	private StringBuffer buildParamList(PaymentUrlRequest paymentUrlRequest) {
		StringBuffer paramList = new StringBuffer();
		if (StringUtil.isValidString(paymentUrlRequest.getMerchantcode())) {
			paramList.append("?merchantcode=").append(
					paymentUrlRequest.getMerchantcode());
		} else {
			paramList.append("?merchantcode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getFcUserid())) {
			paramList.append("&fcUserid=").append(
					paymentUrlRequest.getFcUserid());
		} else {
			paramList.append("&fcUserid=");
		}
		paramList.append("&payOrderNo=").append(
				paymentUrlRequest.getPayOrderNo());
		paramList.append("&paySource=")
				.append(paymentUrlRequest.getPaySource());

		if (StringUtil.isValidString(paymentUrlRequest.getOperater())) {
			paramList.append("&operater=").append(
					paymentUrlRequest.getOperater());
		} else {
			paramList.append("&operater=");
		}
		if (StringUtil
				.isValidString(paymentUrlRequest.getRecieveMerchantCode())) {
			paramList.append("&recieveMerchantCode=").append(
					paymentUrlRequest.getRecieveMerchantCode());
		} else {
			paramList.append("&recieveMerchantCode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getBusinessOrderCode())) {
			paramList.append("&businessOrderCode=").append(
					paymentUrlRequest.getBusinessOrderCode());
		} else {
			paramList.append("&businessOrderCode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getNotifyUrl())) {
			paramList.append("&notifyUrl=").append(
					paymentUrlRequest.getNotifyUrl());
		} else {
			paramList.append("&notifyUrl=");
		}
		paramList.append("&isEscrowed=").append(
				paymentUrlRequest.getIsEscrowed());
		if (StringUtil.isValidString(paymentUrlRequest.getEscrowedDate())) {
			paramList.append("&escrowedDate=").append(
					paymentUrlRequest.getEscrowedDate());
		} else {
			paramList.append("&escrowedDate=");
		}
		paramList.append("&version=").append(paymentUrlRequest.getVersion());
		paramList.append("&canAccountBalance=").append(paymentUrlRequest.isCanAccountBalance());
		if (StringUtil.isValidString(paymentUrlRequest.getPartnerOrgCode())) {
			paramList.append("&partnerOrgCode=").append(
					paymentUrlRequest.getPartnerOrgCode());
		} else {
			paramList.append("&partnerOrgCode=");
		}
		return paramList;
	}
	
	@Override
	public PayMethodConfigDTO getPayMethodConfigDTO(
			PayMethodConfigRequest payMethodConfigRequest) {
		try{
			PayMethodConfigDTO payMethodConfigDTO = null;
			String domainName = domainConfigDao.queryCurrentEnvDomain();
			if(StringUtil.isValidString(domainName)){
				payMethodConfigDTO = new PayMethodConfigDTO();
				payMethodConfigDTO.setPageurl(MessageFormat.format(payCallbackPageUrl, domainName));
				payMethodConfigDTO.setNotifyurl(MessageFormat.format(payNotifyUrl, domainName));
				payMethodConfigDTO.setTl_NetBankPay_ConfirmPageurl(MessageFormat.format(tl_NetBankPay_ConfirmPageurl, domainName));
				payMethodConfigDTO.setTl_NetBankPay_Notifyurl(MessageFormat.format(tl_NetBankPay_Notifyurl, domainName));
				payMethodConfigDTO.setTl_QrCodePay_Notifyurl(MessageFormat.format(tl_QrCodePay_Notifyurl, domainName));
				payMethodConfigDTO.setTl_WechatPay_Notifyurl(MessageFormat.format(tl_WechatPay_Notifyurl, domainName));
				payMethodConfigDTO.setRb_QuickPay_Notifyurl(MessageFormat.format(rb_QuickPay_Notifyurl, domainName));
				payMethodConfigDTO.setRb_CardAuth_Pageurl(MessageFormat.format(rb_CardAuth_Pageurl, domainName));
				payMethodConfigDTO.setRb_CardAuth_Notifyurl(MessageFormat.format(rb_CardAuth_Notifyurl, domainName));
			}
			return payMethodConfigDTO;
		}catch(Exception e){
			log.error("查询支付方式的配置出错"+e.getMessage(),e);
		}
		return null;
	}

//	@Override
//	public void notifyClient(NotifyClientRequest request) {
//		if(null == request || !StringUtil.isValidString(request.getUrl())){
//			return ;
//		}
//		log.info("回调客户端参数："+JSONSerializer.toJSON(request));
//		 String response = "";
//		try{
//			HttpPost httpPost = new HttpPost(request.getUrl());
//		    HttpResponse resp = HttpClient.httpRequest(request.getParams(),  httpPost);
//			if (null != resp) {
//				InputStream in = resp.getEntity().getContent();
//				byte b[] = new byte[1024];
//				
//				int length = 0;
//				if((length = in.read(b)) !=-1){
//					byte d[] = new byte[length];
//					System.arraycopy(b, 0, d, 0, length);
//					response = new String(d , "UTF-8");
//				}
//				httpPost.releaseConnection();
//			}
//			
//			if(!StringUtil.isValidString(response)){
//				log.error("回调返回信息为空:" + response);
//				throw new Exception("回调返回信息为空");
//			}
//			CallBackInfo callBackInfo = TitanjrHttpTools.analyzeResponse(response);
//			if (callBackInfo == null ||!"000".equals(callBackInfo.getCode())) {
//				log.error("回调返回信息异常");
//				throw new Exception("回调返回信息异常");
//			}
//			
//		}catch(Exception e){
//			log.error("回调失败");
//			utilService.saveOrderException(null, OrOrderExceptionEnum.Notify_Fail, JSONSerializer.toJSON(request).toString());
//		}
//	}

	@Override
	public SysConfig querySysConfig() {
		if(config == null){
			config = new SysConfig();
			config.setSessionKey(RSInvokeConstant.sessionKey);
			config.setRsCheckKey(RSInvokeConstant.rsCheckKey);
			config.setGateWayURL(RSInvokeConstant.gateWayURL);
			config.setTitanjrCheckKey(RSInvokeConstant.titanjrCheckKey);
			config.setCsPayConfirmPageURL(RSInvokeConstant.csPayConfirmPageURL);
			config.setCsPayNoticeURL(RSInvokeConstant.csPayNoticeURL);
			config.setCsCardAuthPageURL(RSInvokeConstant.csCardAuthPageURL);
			config.setCsCardAuthNoticeURL(RSInvokeConstant.csCardAuthNoticeURL);
		}
		return config;
	}

	@Override
	public void saveOrderException(String orderId, OrderKindEnum orEnum,OrderExceptionEnum oet,
			String content) {
		
		if(null == oet){
			return;
		}
		Date now = new Date();
		TitanOrderException ex = new TitanOrderException();
		ex.setType(oet.type);
		ex.setExceptionContent(content);
		ex.setExceptionTime(now);
		ex.setOrderId(orderId);
		ex.setFailState(oet.failState);
		ex.setExceptionMsg(oet.msg);
		ex.setUpdateTime(now);
		ex.setOrderType(orEnum.getType());
		
		try{
			orderExceptionDao.insertTitanOrderException(ex);
			
			if("1".equals(isSendWarning)){
				SendMessageRequest sendCodeRequest = new SendMessageRequest();
				sendCodeRequest.setReceiveAddress("jinrong@fangcang.com");
				sendCodeRequest.setSubject(SMSTemplate.ORDER_WARNING.getSubject());
				sendCodeRequest.setContent(MessageFormat.format(SMSTemplate.ORDER_WARNING.getContent(), orderId,oet.msg,","+content));
				sendCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
				smsService.asynSendMessage(sendCodeRequest);
			}
			
		}catch(Exception e){
			log.error("插入异常信息失败",e);
		}
	}

}
