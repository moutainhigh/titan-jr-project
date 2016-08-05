package com.fangcang.titanjr.rs.manager.impl;

import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.rs.dto.RSPayResultInfo;
import com.fangcang.titanjr.rs.manager.RSPayOrderManager;
import com.fangcang.titanjr.rs.request.RSPayOrderRequest;
import com.fangcang.titanjr.rs.response.PayResultResponse;
import com.fangcang.titanjr.rs.response.RSPayOrderResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class RSPayOrderManagerImpl implements RSPayOrderManager{

	private static final Log log = LogFactory
			.getLog(RSOrganizationManagerImpl.class);
	@Override
	public RSPayOrderResponse getPayPage(RSPayOrderRequest rsPayOrderRequest) {
		RSPayOrderResponse response = new RSPayOrderResponse();
		try{
			if(rsPayOrderRequest !=null){
				//校验
				rsPayOrderRequest.check();
				rsPayOrderRequest.setKey(RSInvokeConstant.rsCheckKey);
				String sign = getSigStr(rsPayOrderRequest);
				if(sign!=null){
					rsPayOrderRequest.setSignMsg(getSigStr(sign));
					response.setSuccess(true);
					response.setOperateStatus("true");
					response.setRsPayOrderRequest(rsPayOrderRequest);
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			}
		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用getPayPage参数校验异常", re);	
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用getPayPage过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public PayResultResponse queryPayResult(RSPayOrderRequest rsPayOrderRequest) {
		PayResultResponse response = new PayResultResponse();
		try{
			if(rsPayOrderRequest !=null){
				//校验
				rsPayOrderRequest.checkPayResult();
				Map<String,String> paramMap = RSPayOrderRequest.RSPayOrderRequestToMap(rsPayOrderRequest);
				
				String sign = getPaySigStr(rsPayOrderRequest);
				if(sign!=null){
					paramMap.put("signMsg", getSigStr(sign));
					String resultStr = sendHttpClientRequest(paramMap,RSInvokeConstant.gateWayURL);
					if(resultStr !=null){
						response.setSuccess(true);
					    RSPayResultInfo rsPayResultInfo  = new RSPayResultInfo();
					    Class cls = rsPayResultInfo.getClass();
					    rsPayResultInfo=  (RSPayResultInfo)RSConvertFiled2ObjectUtil.convertField2Object(cls,resultStr);
					    String signStr = getSigStr(RSPayResultInfo.getSign(rsPayResultInfo));
					    
					    if(signStr.equals(rsPayResultInfo.getSignMsg())){
					    	 response.setRsRayResultInfo(rsPayResultInfo);
						     response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
							 response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
					    }else{
					    	 response.setReturnCode(RSInvokeErrorEnum.SIGNCHECK_FAIURE.returnCode);
							 response.setReturnMsg(RSInvokeErrorEnum.SIGNCHECK_FAIURE.returnMsg);
					    }
					   
					}else{
						response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
						response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
					}
				}
			}
		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用getPayPage参数校验异常", re);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用getPayPage过程出现未知异常", e);
		}
		return response;
	}
	
	@Override
	public String notifyResult(RSPayOrderRequest rsPayOrderRequest) {
		Map<String,String> paramMap = RSPayOrderRequest.RSPayOrderRequestToMap(rsPayOrderRequest);
		String sign = getPaySigStr(rsPayOrderRequest);
		paramMap.put("signMsg", getSigStr(sign));
		String resultStr = sendHttpClientRequest(paramMap,"http://192.168.1.96:8080/TFS/pay/notify.action");
		return resultStr;
	}
	
	
	/**
	 * 获取签名的字符串
	 * @param rsPayOrderRequest
	 * @return
	 * @author fangdaikang
	 */
	private String getSigStr(RSPayOrderRequest rsPayOrderRequest){
		StringBuffer sign = new StringBuffer();
		if(rsPayOrderRequest !=null){
			sign.append("merchantNo=");
			sign.append(rsPayOrderRequest.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(rsPayOrderRequest.getOrderNo());
			sign.append("&orderAmount=");
			sign.append(rsPayOrderRequest.getOrderAmount());
			sign.append("&payType=");
			sign.append(rsPayOrderRequest.getPayType());
			sign.append("&orderTime=");
			sign.append(rsPayOrderRequest.getOrderTime());
			sign.append("&signType=");
			sign.append(rsPayOrderRequest.getSignType());
			sign.append("&version=");
			sign.append(rsPayOrderRequest.getVersion());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
			sign.append(")(*");
		}
		return sign.toString();
	}
	
	/**
	 * 
	 */
	public String getPaySigStr(RSPayOrderRequest rsPayOrderRequest){
		StringBuffer sign = new StringBuffer();
		if(rsPayOrderRequest !=null){
			sign.append("signType=");
			sign.append(rsPayOrderRequest.getSignType());
			sign.append("&version=");
			sign.append(rsPayOrderRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(rsPayOrderRequest.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(rsPayOrderRequest.getOrderNo());
			sign.append("&orderTime=");
			sign.append(rsPayOrderRequest.getOrderTime());
			sign.append("&key=");
			sign.append(RSInvokeConstant.rsCheckKey);
			sign.append(")(*");
		}
		return sign.toString();
	}
	
	/**
	 * MD5加密
	 * @param sigStr
	 * @return
	 */
	private String getSigStr(String sigStr){
		return MD5.MD5Encode(sigStr, "UTF-8");
	}
	
	/**
	 * 发送http请求
	 * @param paramMap
	 * @param url
	 * @return
	 * @author fangdaikang
	 */
	private String sendHttpClientRequest(Map<String,String> paramMap,String url){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost post = HttpUtils.buildPostForm(url, paramMap);
			HttpResponse response = httpclient.execute(post);
			return new String(EntityUtils.toByteArray(response.getEntity()), "UTF-8");
		} catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}




}
