package com.fangcang.titanjr.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dto.bean.gateway.QuickPayCardDTO;
import com.fangcang.titanjr.dto.request.QuickPaymentRequest;
import com.fangcang.titanjr.dto.request.gateway.CardSceurityVerifyRequest;
import com.fangcang.titanjr.dto.request.gateway.ConfirmRechargeRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryBankCardBINRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryQuickPayBindCardRequest;
import com.fangcang.titanjr.dto.request.gateway.ReSendVerifyCodeRequest;
import com.fangcang.titanjr.dto.request.gateway.UnbindBankCardRequest;
import com.fangcang.titanjr.dto.request.gateway.UpdateBankCardPhoneResponseRequest;
import com.fangcang.titanjr.dto.response.gateway.ConfirmRechargeResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryBankCardBINIResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryQuickPayBindCardResponse;
import com.fangcang.titanjr.dto.response.gateway.QuickPaymentResponse;
import com.fangcang.titanjr.dto.response.gateway.ReSendVerifyCodeResponse;
import com.fangcang.titanjr.dto.response.gateway.UnbindBankCardResponse;
import com.fangcang.titanjr.dto.response.gateway.UpdateBankCardPhoneResponse;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.RsVersionEnum;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.RSGatewayInterfaceService;
import com.fangcang.titanjr.util.SignMsgBuilder;
import com.fangcang.util.StringUtil;

/**
 * 融数网关接口服务实现
 * 
 * @author jerry
 * @date 2017.6.15
 *
 */
@Service("rsGatewayInterfaceService")
public class RSGatewayInterfaceServiceImpl implements RSGatewayInterfaceService {
	
	private static final Log log = LogFactory.getLog(RSGatewayInterfaceServiceImpl.class);

	@Override
	public QuickPaymentResponse quickPay(QuickPaymentRequest quickPaymentRequest) {
		QuickPaymentResponse quickPaymentResponse = new QuickPaymentResponse();
		quickPaymentRequest.setSignMsg(SignMsgBuilder.getSignMsgForQuickPay(quickPaymentRequest));
		String response ="";
		
		try {
			log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
			HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
			List<NameValuePair> quickPayParams = BeanConvertor.beanToList(quickPaymentRequest);
			log.info("【快捷支付】请求参数:" + quickPaymentRequest.toString());
			
			HttpResponse quickPayResp = HttpClient.httpRequest(quickPayParams, httpPost);
			if (null != quickPayResp) {
				HttpEntity entity = quickPayResp.getEntity();
				response = EntityUtils.toString(entity, "UTF-8");
				quickPaymentResponse = RSConvertFiled2ObjectUtil.convertField2ObjectSuper(QuickPaymentResponse.class, response);
				log.info("【快捷支付】返回信息:" + quickPaymentResponse.toString());
				
				return quickPaymentResponse;
				
			}else{
				log.error("【快捷支付】失败 resp 为空, 参数params:"+Tools.gsonToString(quickPayParams));
				quickPaymentResponse.putError("系统错误，请联系管理员");
				return quickPaymentResponse;
			}
		} catch (Exception e) {
			log.error("【快捷支付】失败,异常：", e);
			quickPaymentResponse.putError("系统异常");
			return quickPaymentResponse;
		}
		
	}
	
	
	@Override
	public ConfirmRechargeResponse confirmRecharge(ConfirmRechargeRequest confirmRechargeRequest) {
		ConfirmRechargeResponse confirmRechargeResponse = new ConfirmRechargeResponse();
		confirmRechargeRequest.setSignMsg(SignMsgBuilder.getSignMsgForConfirmRecharge(confirmRechargeRequest));
		String response ="";
		try {
			log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
			HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
			List<NameValuePair> confirmRechargeParams = BeanConvertor.beanToList(confirmRechargeRequest);
			log.info("【确认充值】请求参数:" + confirmRechargeRequest.toString());
			
			HttpResponse confirmRechargeResp = HttpClient.httpRequest(confirmRechargeParams, httpPost);
			if (confirmRechargeResp != null) {
				HttpEntity entity = confirmRechargeResp.getEntity();
				response = EntityUtils.toString(entity, "UTF-8");
				confirmRechargeResponse = RSConvertFiled2ObjectUtil.convertField2ObjectSuper(ConfirmRechargeResponse.class,response);
				log.info("【确认充值】返回信息:" + confirmRechargeResponse.toString());
				
				return confirmRechargeResponse;
				
			}else{
				log.error("【确认充值】失败 confirmRechargeResp 为空, 参数params:"+Tools.gsonToString(confirmRechargeParams));
				return confirmRechargeResponse;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			confirmRechargeResponse.setErrMsg("确认充值异常");
			return confirmRechargeResponse;
		}
	}
	
	
	
	@Override
	public ReSendVerifyCodeResponse reSendVerifyCode(ReSendVerifyCodeRequest reSendVerifyCodeRequest) {
		ReSendVerifyCodeResponse reSendVerifyCodeResponse = new ReSendVerifyCodeResponse();
		reSendVerifyCodeRequest.setSignMsg(SignMsgBuilder.getSignMsgForReSendVerifyCode(reSendVerifyCodeRequest));
		String responseStr ="";
		try {
			log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
			HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
			List<NameValuePair> params = BeanConvertor.beanToList(reSendVerifyCodeRequest);
			
			log.info("【重发验证码】请求参数为：" + reSendVerifyCodeRequest.toString());
			HttpResponse resp = HttpClient.httpRequest(params, httpPost);
			
			if (resp != null) {
				HttpEntity entity = resp.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				reSendVerifyCodeResponse = RSConvertFiled2ObjectUtil
						.convertField2ObjectSuper(ReSendVerifyCodeResponse.class,responseStr);
				log.info("调用融数网关gateWayURL【重发验证码】返回信息:" + reSendVerifyCodeResponse.toString());
				
				return reSendVerifyCodeResponse;
			}else{
				log.error("网关【重发验证码】失败 resp 为空, 参数params:"+Tools.gsonToString(params));
				reSendVerifyCodeResponse.putError("返回结果为空");
				return reSendVerifyCodeResponse;
			}
			
		} catch (Exception e) {
			log.error("重发验证码异常", e);
			reSendVerifyCodeResponse.putError("重发验证码异常");
			return reSendVerifyCodeResponse;
		}
		
	}
	
	
	@Override
	public QueryQuickPayBindCardResponse queryQuickPayBindCardInfo(
			QueryQuickPayBindCardRequest queryQuickPayBindCardRequest) {
		QueryQuickPayBindCardResponse queryQuickPayBindCardResponse = new QueryQuickPayBindCardResponse();
		queryQuickPayBindCardRequest.setSignMsg(SignMsgBuilder.getSignMsgForQueryQuickPayBindCard(queryQuickPayBindCardRequest));
		String responseStr ="";
		try {
			log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
			HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
			List<NameValuePair> params = BeanConvertor.beanToList(queryQuickPayBindCardRequest);
			
			log.info("【查询快捷绑卡信息】请求参数为：" + queryQuickPayBindCardRequest.toString());
			HttpResponse resp = HttpClient.httpRequest(params, httpPost);
			
			if (resp != null) {
				HttpEntity entity = resp.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				queryQuickPayBindCardResponse = convertToQueryQuickPayBindCardResponse(responseStr);
				log.info("【查询快捷绑卡信息】返回信息:" + queryQuickPayBindCardResponse.toString());
				
				return queryQuickPayBindCardResponse;
			}else{
				log.error("网关【查询快捷绑卡信息】失败 resp 为空, 参数params:"+Tools.gsonToString(params));
				queryQuickPayBindCardResponse.putError("返回结果为空");
				return queryQuickPayBindCardResponse;
			}
			
		} catch (Exception e) {
			log.error("查询快捷绑卡信息异常", e);
			queryQuickPayBindCardResponse.putError("查询快捷绑卡信息异常");
			return queryQuickPayBindCardResponse;
		}
	}
	
	
	@Override
	public QueryBankCardBINIResponse queryBankCardBIN(QueryBankCardBINRequest queryBankCardBINRequest) {
		QueryBankCardBINIResponse bankCardBINIResponse = new QueryBankCardBINIResponse();
		queryBankCardBINRequest.setSignMsg(SignMsgBuilder.getSignMsgForQueryBankCardBIN(queryBankCardBINRequest));
		String responseStr ="";
		try {
			log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
			HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
			List<NameValuePair> params = BeanConvertor.beanToList(queryBankCardBINRequest);
			
			log.info("【查询银行卡BIN信息】请求参数为：" + queryBankCardBINRequest.toString());
			HttpResponse resp = HttpClient.httpRequest(params, httpPost);
			
			if (resp != null) {
				HttpEntity entity = resp.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				bankCardBINIResponse = RSConvertFiled2ObjectUtil
						.convertField2ObjectSuper(QueryBankCardBINIResponse.class, responseStr);
				log.info("【查询银行卡BIN信息】返回信息:" + bankCardBINIResponse.toString());
				
				return bankCardBINIResponse;
			}else{
				log.error("网关【查询银行卡BIN信息】失败 resp 为空, 参数params:"+Tools.gsonToString(params));
				bankCardBINIResponse.putError("返回结果为空");
				return bankCardBINIResponse;
			}
			
		} catch (Exception e) {
			log.error("查询银行卡BIN信息异常", e);
			bankCardBINIResponse.putError("查询银行卡BIN异常");
			return bankCardBINIResponse;
		}
	}
	
	
	@Override
	public UnbindBankCardResponse unBindBankCard(UnbindBankCardRequest unbindBankCardRequest) {
		UnbindBankCardResponse unbindBankCardResponse = new UnbindBankCardResponse();
		unbindBankCardRequest.setSignMsg(SignMsgBuilder.getSignMsgForUnBindBankCard(unbindBankCardRequest));
		String responseStr ="";
		try {
			log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
			HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
			List<NameValuePair> params = BeanConvertor.beanToList(unbindBankCardRequest);
			
			log.info("【银行卡解绑】请求参数为：" + unbindBankCardRequest.toString());
			HttpResponse resp = HttpClient.httpRequest(params, httpPost);
			
			if (resp != null) {
				HttpEntity entity = resp.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				unbindBankCardResponse = RSConvertFiled2ObjectUtil
						.convertField2ObjectSuper(UnbindBankCardResponse.class, responseStr);
				log.info("【银行卡解绑】返回信息:" + unbindBankCardResponse.toString());
				
				return unbindBankCardResponse;
			}else{
				log.error("网关【银行卡解绑】失败 resp 为空, 参数params:"+Tools.gsonToString(params));
				unbindBankCardResponse.putError("返回结果为空");
				return unbindBankCardResponse;
			}
			
		} catch (Exception e) {
			log.error("银行卡解绑异常", e);
			unbindBankCardResponse.putError("银行卡解绑异常");
			return unbindBankCardResponse;
		}
	}
	
	
	@Override
	public UpdateBankCardPhoneResponse updateBankCardPhone(UpdateBankCardPhoneResponseRequest updatePhoneNumberRequest) {
		UpdateBankCardPhoneResponse updatePhoneNumberResponse = new UpdateBankCardPhoneResponse();
		updatePhoneNumberRequest.setSignMsg(SignMsgBuilder.getSignMsgForUpdatePhone(updatePhoneNumberRequest));
		String responseStr ="";
		try {
			log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
			HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
			List<NameValuePair> params = BeanConvertor.beanToList(updatePhoneNumberRequest);
			
			log.info("【更改预留手机号】请求参数为：" + updatePhoneNumberRequest.toString());
			HttpResponse resp = HttpClient.httpRequest(params, httpPost);
			
			if (resp != null) {
				HttpEntity entity = resp.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				updatePhoneNumberResponse = RSConvertFiled2ObjectUtil
						.convertField2ObjectSuper(UpdateBankCardPhoneResponse.class, responseStr);
				log.info("【更改预留手机号】返回信息:" + updatePhoneNumberResponse.toString());
				
				return updatePhoneNumberResponse;
			}else{
				log.error("网关【更改预留手机号】失败 resp 为空, 参数params:"+Tools.gsonToString(params));
				updatePhoneNumberResponse.putError("返回结果为空");
				return updatePhoneNumberResponse;
			}
			
		} catch (Exception e) {
			log.error("更改预留手机号异常", e);
			updatePhoneNumberResponse.putError("更改预留手机号异常");
			return updatePhoneNumberResponse;
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private QueryQuickPayBindCardResponse convertToQueryQuickPayBindCardResponse(String responseStr){
		QueryQuickPayBindCardResponse queryQuickPayBindCardResponse = new QueryQuickPayBindCardResponse();
		List<QuickPayCardDTO> agentProtocolList = new ArrayList<QuickPayCardDTO>();
		log.info("responseStr：" + responseStr);
		try {
		   Class responseClas = queryQuickPayBindCardResponse.getClass();
		   String[]  responseStrArr = responseStr.split("&");
		   for(int i = 0; i < responseStrArr.length; i++){
	    	   String key = responseStrArr[i].split("=")[0];
	    	   String value = responseStrArr[i].split("=").length>1? responseStrArr[i].split("=")[1] : "";
	    	   for (Field field : responseClas.getDeclaredFields()) {
	               if (field.getName().equals(key)) {
	            	   if("agentProtocolList".equals(key)){
	            		   if("null".equals(value) || "".equals(value)){
	            			   break;
	            		   }
	            		   JSONArray jsonArray = JSONArray.fromObject(value);
	    				   List<Map<String,Object>> mapListJson = (List<Map<String, Object>>)jsonArray;
	    				   for (int j = 0; j < mapListJson.size(); ++j) {
	    				    	Map<String,Object> mapObj = mapListJson.get(j);
	    				    	QuickPayCardDTO quickPayCardDTO = new QuickPayCardDTO();
	    				    	quickPayCardDTO.setAccountName(mapObj.get("accountName").toString());
	    				    	quickPayCardDTO.setAccountNo(mapObj.get("accountNo").toString());
	    				    	quickPayCardDTO.setAccountType(mapObj.get("accountType").toString());
	    				    	quickPayCardDTO.setBankNo(mapObj.get("bankNo").toString());
	    				    	quickPayCardDTO.setMobile(mapObj.get("mobile").toString());
	    				    	quickPayCardDTO.setProtocolNo(mapObj.get("protocolNo").toString());
	    				    	agentProtocolList.add(quickPayCardDTO);
	    				   }
	    				   break;
	            	   }
	                   field = responseClas.getDeclaredField(key);
	                   field.setAccessible(true);
	                   field.set(queryQuickPayBindCardResponse, value);
	                   break;
	               }
	           }
	    	   for (Field field : responseClas.getSuperclass().getDeclaredFields()) {
	               if (field.getName().equals(key)) {
	                   field = responseClas.getSuperclass().getDeclaredField(key);
	                   field.setAccessible(true);
	                   field.set(queryQuickPayBindCardResponse, value);
	                   break;
	               }
	           }
	    	   
	    	   if("errCode".equals(key)){
	    		   for (Field field : responseClas.getSuperclass().getDeclaredFields()) {
		               if (field.getName().equals("isSuccess")) {
		                   field = responseClas.getSuperclass().getDeclaredField("isSuccess");
		                   field.setAccessible(true);
		                   if(StringUtil.isValidString(value) && "0000".equals(value)){
		                	   field.set(queryQuickPayBindCardResponse, true);
		                   }else{
		                	   field.set(queryQuickPayBindCardResponse, false);
		                   }
		                   break;
		               }
		           }
	    	   }
	       }
		   queryQuickPayBindCardResponse.setAgentProtocolList(agentProtocolList);
		   
		} catch (Exception e) {
			log.error("银行列表信息转换异常", e);
			queryQuickPayBindCardResponse.putError("银行列表信息转换异常");
			return queryQuickPayBindCardResponse;
		}
		
		return queryQuickPayBindCardResponse;
	}
	
	
	@Override
	public CardSceurityVerifyRequest getCardSceurityVerifyParam(CardSceurityVerifyRequest cardSceurityVerifyRequest) {
		
		CardSceurityVerifyRequest cardSceurityVerifyParam = new CardSceurityVerifyRequest();
		cardSceurityVerifyParam.setBusiCode(BusiCodeEnum.CARE_SCEURITY_VERIFY.getKey());
		cardSceurityVerifyParam.setCardNo(cardSceurityVerifyRequest.getCardNo());
		cardSceurityVerifyParam.setMerchantNo("M000016");
		cardSceurityVerifyParam.setOrderNo(cardSceurityVerifyRequest.getOrderNo());
		cardSceurityVerifyParam.setPayType("41");
		cardSceurityVerifyParam.setSignType("1");
		cardSceurityVerifyParam.setVersion(RsVersionEnum.Version_2.key);
		cardSceurityVerifyParam.setTerminalType(cardSceurityVerifyRequest.getTerminalType());
		cardSceurityVerifyParam.setCardChecknotifyUrl("http://www.fangcang.org/titanjr-pay-dev3/quickPay/cardSceurityVerifyResultNotice.action");
		cardSceurityVerifyParam.setCardCheckPageUrl("http://www.fangcang.org/titanjr-pay-dev3/quickPay/cardSceurityVerifyResultPage.action");
		cardSceurityVerifyParam.setSignMsg(SignMsgBuilder.getSignMsgForCardSceurityVerify(cardSceurityVerifyParam));
		
		return cardSceurityVerifyParam;
	}

}
