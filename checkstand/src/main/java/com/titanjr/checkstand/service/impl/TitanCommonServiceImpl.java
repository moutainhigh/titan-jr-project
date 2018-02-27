/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanCommonServiceImpl.java
 * @author Jerry
 * @date 2018年1月15日 下午3:15:17  
 */
package com.titanjr.checkstand.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.TitanPayDTO;
import com.titanjr.checkstand.respnse.RBCardAuthResponse;
import com.titanjr.checkstand.respnse.TitanCardAuthResponse;
import com.titanjr.checkstand.respnse.TitanPayCallBackResponse;
import com.titanjr.checkstand.service.TitanCommonService;
import com.titanjr.checkstand.util.SignMsgBuilder;

/**
 * @author Jerry
 * @date 2018年1月15日 下午3:15:17  
 */
@Service("titanCommonServiceImpl")
public class TitanCommonServiceImpl implements TitanCommonService {

	private final static Logger logger = LoggerFactory.getLogger(TitanCommonServiceImpl.class);
	
	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;

	@Resource
	private TitanOrderService titanOrderService;
	

	@Override
	public TitanPayCallBackResponse payConfirmCallback(RechargeResultConfirmRequest confirmRequest) {
		
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		
		try {
			
			//获取系统配置
			SysConfig config = titanFinancialUtilService.querySysConfig();
			confirmRequest.setSignMsg(SignMsgBuilder.getPayCallbackSignMsg(confirmRequest, config.getRsCheckKey()));
			
			payCallBackResponse.setPayConfirmPageUrl(config.getCsPayConfirmPageURL());
			payCallBackResponse.setRechargeResultConfirmRequest(confirmRequest);
			logger.info("【支付结果前台回调】地址：{}", config.getCsPayConfirmPageURL());
			
			payCallBackResponse.putSuccess();
			return payCallBackResponse;
			
		} catch (Exception e) {
			
			logger.error("【支付结果前台回调】发生异常：", e);
			return payCallBackResponse;
			
		}
		
	}
	
	
	@Override
	public TitanPayCallBackResponse PayNotice(RechargeResultConfirmRequest confirmRequest) {
		
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		
		try {
			
			SysConfig config = titanFinancialUtilService.querySysConfig();
			confirmRequest.setSignMsg(SignMsgBuilder.getPayCallbackSignMsg(confirmRequest, config.getRsCheckKey()));
			
			logger.info("【支付结果后台通知】地址：{}", config.getCsPayNoticeURL());
			HttpPost httpPost = new HttpPost(config.getCsPayNoticeURL());
			List<NameValuePair> params = BeanConvertor.beanToList(confirmRequest);
			logger.info("【支付结果后台通知】请求参数：{}" ,confirmRequest.toString());
			
			HttpResponse httpRes = HttpClient.httpRequest(params, httpPost);
			if (null != httpRes) {
				HttpEntity entity = httpRes.getEntity();
				String resStr = EntityUtils.toString(entity, "UTF-8");
				logger.info("【支付结果后台通知】返回信息：{}" ,resStr);
				Map<String, String> result = new HashMap<String, String>();
				String[] parameters = resStr.split("&");
				for (int i = 0; i < parameters.length; i++) {
					String msg = parameters[i];
					int index = msg.indexOf('=');
					if (index > 0) {
						String name = msg.substring(0, index);
						String value = msg.substring(index + 1);
						result.put(name, value);
					}
				}
				if (!StringUtil.isValidString(result.get("returnCode")) || !result.get("returnCode").equals("000000")) {
					return payCallBackResponse;
				}
				
				payCallBackResponse.putSuccess();
				return payCallBackResponse;
				
			}else{
				logger.error("【支付结果后台通知】失败，httpRes为空，参数params:"+Tools.gsonToString(params));
				return payCallBackResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【支付结果后台通知】发生异常：", e);
			return payCallBackResponse;
			
		}
		
	}
	
	
	@Override
	public TitanCardAuthResponse cardAuthPage(RBCardAuthResponse rbCardAuthResponse) {
		
		TitanCardAuthResponse titanCardAuthResponse = new TitanCardAuthResponse();
		
		try {
			
			SysConfig config = titanFinancialUtilService.querySysConfig();
			titanCardAuthResponse = this.quick2TitanCardAuthCallbackRequest(rbCardAuthResponse, config);
			logger.info("【卡密鉴权前台回调】参数：{}", titanCardAuthResponse.toString());
			
			return titanCardAuthResponse;
			
		} catch (Exception e) {
			
			logger.error("【支付结果前台回调】发生异常：", e);
			return null;
			
		}
		
	}
    
    
    /**
	 * 交易单存在并且可以支付
	 * @author Jerry
	 * @date 2018年1月12日 上午10:27:26
	 */
	@Override
	public boolean isOrderCanPay(TitanPayDTO payDTO){
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setOrderid(payDTO.getOrderNo());
		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
		if(transOrderDTO == null){
			logger.error("【支付】失败：交易单不存在，orderNo={}", payDTO.getOrderNo());
			return false;
		}
		if(OrderStatusEnum.isPaySuccess(transOrderDTO.getStatusid())){
			logger.error("【支付】失败：交易单不是可支付状态，orderNo={}", payDTO.getOrderNo());
			return false;
		}
		return true;
	}
	
	

	private TitanCardAuthResponse quick2TitanCardAuthCallbackRequest(RBCardAuthResponse rbCardAuthResponse, 
			SysConfig config){
		TitanCardAuthResponse titanCardAuthResponse = new TitanCardAuthResponse();
		titanCardAuthResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
		titanCardAuthResponse.setBusiCode(BusiCodeEnum.CARE_SCEURITY_VERIFY.getKey());
		titanCardAuthResponse.setCardNo(rbCardAuthResponse.getCard_last());//卡号后四位
		titanCardAuthResponse.setOrderNo(rbCardAuthResponse.getOrder_no());
		titanCardAuthResponse.setPhone(rbCardAuthResponse.getPhone());
		if(SysConstant.RB_QUICKPAY_SUCCESS.equals(rbCardAuthResponse.getResult_code())){
			titanCardAuthResponse.setStatusId(SysConstant.RS_CARD_AUTH_SUCCESS);
		}else{
			titanCardAuthResponse.setStatusId(SysConstant.RS_CARD_AUTH_FAILED);
		}
		titanCardAuthResponse.setBankInfo(rbCardAuthResponse.getBank_code());
		titanCardAuthResponse.setBankName(rbCardAuthResponse.getBank_name());
		titanCardAuthResponse.setTerminalType("null_MAC");
		titanCardAuthResponse.setCardPassCheckMsg(rbCardAuthResponse.getResult_msg());
		titanCardAuthResponse.setVersion(SysConstant.RS_VERSION);
		titanCardAuthResponse.setSignType(SysConstant.RS_SIGN_TYPE);
		titanCardAuthResponse.setTitanCardAuthCallbackPageUrl(config.getCsCardAuthPageURL());
		return titanCardAuthResponse;
	}

}
