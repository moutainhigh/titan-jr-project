package com.fangcang.titanjr.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.fangcang.titanjr.common.bean.CallBackInfo;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.common.util.httpclient.TitanjrHttpTools;
import com.fangcang.titanjr.dao.DomainConfigDao;
import com.fangcang.titanjr.dao.TitanDynamicKeyDao;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.RSInvokeConfig;
import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.dto.request.NotifyClientRequest;
import com.fangcang.titanjr.dto.request.PayMethodConfigRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.entity.TitanDynamicKey;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
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
	
	private SysConfig config;
	
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
			paymentUrlResponse.putErrorResult("dynamicKey_set_error",
					"金融密钥设置失败");
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
				payMethodConfigDTO.setPageurl("http://"+domainName+"/titanjr-pay-02/payment/payConfirmPage.action");
				payMethodConfigDTO.setNotifyurl("http://"+domainName+"/titanjr-pay-02/payment/notify.action");
			}
			return payMethodConfigDTO;
		}catch(Exception e){
			log.error("查询支付方式的配置出错"+e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void notifyClient(NotifyClientRequest request) {
		if(null == request || !StringUtil.isValidString(request.getUrl())){
			return ;
		}
		log.info("回调客户端参数："+JSONSerializer.toJSON(request));
		 String response = "";
		try{
			HttpPost httpPost = new HttpPost(request.getUrl());
		    HttpResponse resp = HttpClient.httpRequest(request.getParams(),  httpPost);
			if (null != resp) {
				InputStream in = resp.getEntity().getContent();
				byte b[] = new byte[1024];
				
				int length = 0;
				if((length = in.read(b)) !=-1){
					byte d[] = new byte[length];
					System.arraycopy(b, 0, d, 0, length);
					response = new String(d , "UTF-8");
				}
				httpPost.releaseConnection();
			}
			
			if(!StringUtil.isValidString(response)){
				log.error("回调返回信息为空:" + response);
				throw new Exception("回调返回信息为空");
			}
			CallBackInfo callBackInfo = TitanjrHttpTools.analyzeResponse(response);
			if (callBackInfo == null ||!"000".equals(callBackInfo.getCode())) {
				log.error("回调返回信息异常");
				throw new Exception("回调返回信息异常");
			}
			
		}catch(Exception e){
			log.error("回调失败");
			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(
					null, "回调失败",
					OrderExceptionEnum.NOTIFY_FAILED,
					JSONSerializer.toJSON(request).toString());
			titanOrderService.saveOrderException(orderExceptionDTO);
		}
	}

	@Override
	public SysConfig querySysConfig() {
		if(config == null){
			config = new SysConfig();
			config.setSessionKey(RSInvokeConstant.sessionKey);
			config.setRsCheckKey(RSInvokeConstant.rsCheckKey);
			config.setGateWayURL(RSInvokeConstant.gateWayURL);
			config.setTitanjrCheckKey(RSInvokeConstant.titanjrCheckKey);
		}
		return config;
	}
}
