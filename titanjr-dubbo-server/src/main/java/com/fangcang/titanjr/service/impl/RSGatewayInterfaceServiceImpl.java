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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.bean.gateway.QuickPayCardDTO;
import com.fangcang.titanjr.dto.request.gateway.CardSceurityVerifyRequest;
import com.fangcang.titanjr.dto.request.gateway.ConfirmRechargeRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryBankCardBINRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryQuickPayBindCardRequest;
import com.fangcang.titanjr.dto.request.gateway.ReSendVerifyCodeRequest;
import com.fangcang.titanjr.dto.request.gateway.UnbindBankCardRequest;
import com.fangcang.titanjr.dto.request.gateway.UpdateBankCardPhoneResponseRequest;
import com.fangcang.titanjr.dto.response.gateway.CardSceurityVerifyResponse;
import com.fangcang.titanjr.dto.response.gateway.ConfirmRechargeResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryBankCardBINIResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryQuickPayBindCardResponse;
import com.fangcang.titanjr.dto.response.gateway.QuickPaymentResponse;
import com.fangcang.titanjr.dto.response.gateway.ReSendVerifyCodeResponse;
import com.fangcang.titanjr.dto.response.gateway.UnbindBankCardResponse;
import com.fangcang.titanjr.dto.response.gateway.UpdateBankCardPhoneResponse;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.VersionEnum;
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
	public QuickPaymentResponse quickPay(RechargeDataDTO rechargeDataDTO) {
		QuickPaymentResponse quickPaymentResponse = new QuickPaymentResponse();
		log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
		HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
		List<NameValuePair> quickPayParams = this.getGateawayQuickPayParam(rechargeDataDTO);
		String response ="";
		log.info("【快捷支付】请求参数:" + Tools.gsonToString(quickPayParams));
		try {
			HttpResponse quickPayResp = HttpClient.httpRequest(quickPayParams, httpPost);
			if (null != quickPayResp) {
				HttpEntity entity = quickPayResp.getEntity();
				response = EntityUtils.toString(entity, "UTF-8");
				quickPaymentResponse = RSConvertFiled2ObjectUtil.convertField2ObjectSuper(QuickPaymentResponse.class, response);
				log.info("快捷支付返回信息:"+quickPaymentResponse.toString());
				if(StringUtil.isValidString(quickPaymentResponse.getErrCode()) 
			    		&& !"0000".equals(quickPaymentResponse.getErrCode())){//通知快捷支付失败
					quickPaymentResponse.putError(quickPaymentResponse.getErrMsg());
					return quickPaymentResponse;
				}
				Thread.sleep(10000);
				
				quickPaymentResponse.setSuccess(true);
				return quickPaymentResponse;
				
			}else{
				log.error("网关快捷支付失败 resp 为空, 参数params:"+Tools.gsonToString(quickPayParams));
				quickPaymentResponse.putError("融数返回对象为空");
				return quickPaymentResponse;
			}
		} catch (Exception e) {
			log.error("网关快捷支付失败,异常：", e);
			quickPaymentResponse.putError("网关快捷支付失败");
			return quickPaymentResponse;
		}
		
	}
	
	
	@Override
	public String confirmRecharge(ConfirmRechargeRequest confirmRechargeRequest) {
		ConfirmRechargeResponse confirmRechargeResponse = new ConfirmRechargeResponse();
		confirmRechargeRequest.setSignMsg(SignMsgBuilder.getSignMsgForConfirmRecharge(confirmRechargeRequest));
		
		log.info("【网关接口】为：" + RSInvokeConstant.gateWayURL);
		HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
		String response ="";
		try {
			List<NameValuePair> confirmRechargeParams = BeanConvertor.beanToList(confirmRechargeRequest);
			HttpResponse confirmRechargeResp = HttpClient.httpRequest(confirmRechargeParams, httpPost);
			
			if (confirmRechargeResp != null) {
				HttpEntity entity = confirmRechargeResp.getEntity();
				response = EntityUtils.toString(entity, "UTF-8");
				confirmRechargeResponse = RSConvertFiled2ObjectUtil.convertField2ObjectSuper(ConfirmRechargeResponse.class,response);
				log.info("调用融数网关gateWayURL确认充值返回信息:"+ confirmRechargeResponse.toString());
				
				if (StringUtil.isValidString(confirmRechargeResponse.getErrCode())
						&& !"0000".equals(confirmRechargeResponse.getErrCode())) {
					confirmRechargeResponse.setSuccess(false);
					return confirmRechargeResponse.getErrMsg();
				}
				return "success";
			}else{
				log.error("网关确认充值失败 confirmRechargeResp 为空, 参数params:"+Tools.gsonToString(confirmRechargeParams));
				return "false";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
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
				log.info("调用融数网关gateWayURL【重发验证码】返回信息:"
						+ reSendVerifyCodeResponse.toString());
				if (StringUtil.isValidString(reSendVerifyCodeResponse.getErrCode()) && !"0000"
								.equals(reSendVerifyCodeResponse.getErrCode())) {
					reSendVerifyCodeResponse.setSuccess(false);
					return reSendVerifyCodeResponse;
				}
				reSendVerifyCodeResponse.setSuccess(true);
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
				if(!queryQuickPayBindCardResponse.isSuccess()){
					return queryQuickPayBindCardResponse;
				}
				log.info("【查询快捷绑卡信息】返回信息:" + queryQuickPayBindCardResponse.toString());
				
				if (StringUtil.isValidString(queryQuickPayBindCardResponse.getErrCode()) && !"0000"
						.equals(queryQuickPayBindCardResponse.getErrCode())) {
					queryQuickPayBindCardResponse.setSuccess(false);
					return queryQuickPayBindCardResponse;
				}
				queryQuickPayBindCardResponse.setSuccess(true);
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
				if (StringUtil.isValidString(bankCardBINIResponse.getErrCode()) && !"0000"
						.equals(bankCardBINIResponse.getErrCode())) {
					bankCardBINIResponse.setSuccess(false);
					return bankCardBINIResponse;
				}
				bankCardBINIResponse.setSuccess(true);
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
				if (StringUtil.isValidString(unbindBankCardResponse.getErrCode()) && !"0000"
						.equals(unbindBankCardResponse.getErrCode())) {
					unbindBankCardResponse.setSuccess(false);
					return unbindBankCardResponse;
				}
				unbindBankCardResponse.setSuccess(true);
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
				if (StringUtil.isValidString(updatePhoneNumberResponse.getErrCode()) && !"0000"
						.equals(updatePhoneNumberResponse.getErrCode())) {
					updatePhoneNumberResponse.setSuccess(false);
					return updatePhoneNumberResponse;
				}
				updatePhoneNumberResponse.setSuccess(true);
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
	
	
	
	/**
	 * 获取快捷支付支付参数
	 * @param rechargeDataDTO
	 * @return
	 */
	private List<NameValuePair> getGateawayQuickPayParam(RechargeDataDTO rechargeDataDTO){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("merchantNo", rechargeDataDTO.getMerchantNo()));
		params.add(new BasicNameValuePair("orderNo", rechargeDataDTO.getOrderNo()));
		params.add(new BasicNameValuePair("orderAmount", rechargeDataDTO.getOrderAmount()));
		params.add(new BasicNameValuePair("amtType", rechargeDataDTO.getAmtType()));
		params.add(new BasicNameValuePair("pageUrl", rechargeDataDTO.getPageUrl()));
		params.add(new BasicNameValuePair("notifyUrl", rechargeDataDTO.getNotifyUrl()));
		params.add(new BasicNameValuePair("orderTime", rechargeDataDTO.getOrderTime()));
		params.add(new BasicNameValuePair("orderExpireTime", rechargeDataDTO.getOrderExpireTime()));
		params.add(new BasicNameValuePair("orderMark", rechargeDataDTO.getOrderMark()));
		params.add(new BasicNameValuePair("signType", rechargeDataDTO.getSignType()));
		params.add(new BasicNameValuePair("busiCode", rechargeDataDTO.getBusiCode()));
		params.add(new BasicNameValuePair("charset", rechargeDataDTO.getCharset()));
		params.add(new BasicNameValuePair("signMsg", rechargeDataDTO.getSignMsg()));
		params.add(new BasicNameValuePair("payType", rechargeDataDTO.getPayType()));
		params.add(new BasicNameValuePair("bankInfo", rechargeDataDTO.getBankInfo()));
		params.add(new BasicNameValuePair("version", rechargeDataDTO.getVersion()));
		params.add(new BasicNameValuePair("idCode", rechargeDataDTO.getIdCode()));
		params.add(new BasicNameValuePair("payerAccountType", rechargeDataDTO.getPayerAccountType()));
		params.add(new BasicNameValuePair("payerName", rechargeDataDTO.getPayerName()));
		params.add(new BasicNameValuePair("payerPhone", rechargeDataDTO.getPayerPhone()));
		params.add(new BasicNameValuePair("payerAcount", rechargeDataDTO.getPayerAcount()));
		params.add(new BasicNameValuePair("terminalIp", rechargeDataDTO.getTerminalIp()));
		params.add(new BasicNameValuePair("terminalType", rechargeDataDTO.getTerminalType()));
		params.add(new BasicNameValuePair("terminalInfo", rechargeDataDTO.getTerminalInfo()));
		return params;
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
	       }
		   queryQuickPayBindCardResponse.setAgentProtocolList(agentProtocolList);
		   queryQuickPayBindCardResponse.setSuccess(true);
		   
		} catch (Exception e) {
			log.error("银行列表信息转换异常", e);
			queryQuickPayBindCardResponse.putError("银行列表信息转换异常");
			return queryQuickPayBindCardResponse;
		}
		
		return queryQuickPayBindCardResponse;
	}
	
	
	@Override
	public CardSceurityVerifyResponse cardSceurityVerify(CardSceurityVerifyRequest cardSceurityVerifyRequest) {
		CardSceurityVerifyResponse cardSceurityVerifyResponse = new CardSceurityVerifyResponse();
		
		/*cardSceurityVerifyRequest.setBusiCode(BusiCodeEnum.CARE_SCEURITY_VERIFY.getKey());
		cardSceurityVerifyRequest.setCardChecknotifyUrl("http://www.fangcang.org/titanjr-pay-dev3/quickPay/cardSceurityVerifyResultNotice.action");
		cardSceurityVerifyRequest.setCardCheckPageUrl("http://www.fangcang.org/titanjr-pay-dev3/quickPay/cardSceurityVerifyResultPage.action");
		cardSceurityVerifyRequest.setCardNo("6214242710498509");
		cardSceurityVerifyRequest.setMerchantNo("M000016");
		cardSceurityVerifyRequest.setOrderNo("");
		cardSceurityVerifyRequest.setPayType("41");
		cardSceurityVerifyRequest.setSignType("1");
		cardSceurityVerifyRequest.setVersion(VersionEnum.Version_2.key);*/
		cardSceurityVerifyResponse.setErrCode(RSInvokeConstant.gateWayURL);
		cardSceurityVerifyResponse.setSignMsg(SignMsgBuilder.getSignMsgForCardSceurityVerify(cardSceurityVerifyRequest));
		
		return cardSceurityVerifyResponse;
	}

}
