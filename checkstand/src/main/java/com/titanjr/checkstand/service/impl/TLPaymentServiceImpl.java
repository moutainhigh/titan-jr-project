/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLPaymentServiceImpl.java
 * @author Jerry
 * @date 2017年12月18日 上午10:48:44  
 */
package com.titanjr.checkstand.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.util.JsonUtil;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.constants.TLQrReturnCodeEnum;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.TLQrCodePayRequest;
import com.titanjr.checkstand.respnse.TLQrCodePayResponse;
import com.titanjr.checkstand.respnse.TitanQrCodePayResponse;
import com.titanjr.checkstand.service.TLPaymentService;

/**
 * 通联支付服务实现类
 * @author Jerry
 * @date 2017年12月18日 上午10:48:44  
 */
@Service("tlPaymentService")
public class TLPaymentServiceImpl implements TLPaymentService {
	
	private static Logger logger = LoggerFactory.getLogger(TLPaymentServiceImpl.class);
	

	@Override
	public TitanQrCodePayResponse qrCodePay(TLQrCodePayRequest tlQrCodePayRequest) {
		
		TitanQrCodePayResponse titanQrCodePayResponse = new TitanQrCodePayResponse();
		TLQrCodePayResponse tlQrCodePayResponse = new TLQrCodePayResponse();
		String responseStr ="";
		
		try {
			//查询订单，获取支付方式
			
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(tlQrCodePayRequest.getCusid()+"_2_01_"+tlQrCodePayRequest.getRequestType());
			logger.info("【通联-扫码支付】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			
			//TreeMap会按字段名的ASCLL码从小到大排序
			TreeMap<String,String> params = new TreeMap<String,String>();
			params.put("cusid", tlQrCodePayRequest.getCusid());
			params.put("appid", gateWayConfigDTO.getAppId());
			params.put("version", tlQrCodePayRequest.getVersion());
			params.put("trxamt", String.valueOf(tlQrCodePayRequest.getTrxamt()));
			params.put("reqsn", tlQrCodePayRequest.getReqsn());
			params.put("paytype", tlQrCodePayRequest.getPaytype());
			params.put("randomstr", tlQrCodePayRequest.getRandomstr());
			params.put("body", tlQrCodePayRequest.getBody());
			params.put("remark", "");
			params.put("validtime", "");
			params.put("notify_url", tlQrCodePayRequest.getNotify_url());
			params.put("limit_pay", "");
			params.put("key", gateWayConfigDTO.getSecretKey());
			
			StringBuilder sb = new StringBuilder();
			for(Map.Entry<String, String> entry:params.entrySet()){
				if(entry.getValue()!=null&&entry.getValue().length()>0){
					sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
				}
			}
			if(sb.length()>0){
				sb.deleteCharAt(sb.length()-1);
			}
			logger.info("【通联-扫码支付】加密前排序为：{}", sb.toString());
			String md5Msg = MD5.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
			logger.info("【通联-扫码支付】加密后sign为：{}", md5Msg);
			params.put("sign", md5Msg);
			params.remove("key");
			
			StringBuilder paramBuf = new StringBuilder();
	    	boolean isNotFirst = false;
	    	for (Map.Entry<String, String> entry: params.entrySet()){
	    		if(entry.getValue()!=null&&entry.getValue().length()>0){
		    		if (isNotFirst)
		    			paramBuf.append('&');
		    		isNotFirst = true;
		    		paramBuf
		    			.append(entry.getKey())
		    			.append('=')
		    			.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	    		}
	    	}
	    	logger.info("【通联-扫码支付】上送参数：{}", paramBuf.toString());
			
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl() + "?" + paramBuf.toString());
			HttpResponse httpRes = HttpClient.httpRequest(new ArrayList<NameValuePair>(), httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				logger.info("【通联-扫码支付】返回信息：{}", responseStr);
				
				tlQrCodePayResponse = (TLQrCodePayResponse)JsonUtil.jsonToBean(responseStr, TLQrCodePayResponse.class);
				
				if(!tlQrCodePayResponse.qrCodeResult()){
					logger.error("【通联-扫码支付】失败，retmsg：{}", tlQrCodePayResponse.getRetmsg()+"，"+tlQrCodePayResponse.getErrmsg());
					titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.build(tlQrCodePayResponse.getRetmsg()+"，"+tlQrCodePayResponse.getErrmsg()));
					return titanQrCodePayResponse;
				}
				if(TLQrReturnCodeEnum.TRADE_SUCCESS.getCode().equals(tlQrCodePayResponse.getTrxstatus())){
					titanQrCodePayResponse.setRespJs(tlQrCodePayResponse.getPayinfo());
					//还需要设置其他参数
					titanQrCodePayResponse.setPayMsg(TLQrReturnCodeEnum.TRADE_SUCCESS.getRemark());
				}else {//支付失败
					TLQrReturnCodeEnum em = TLQrReturnCodeEnum.getEnumByCode(tlQrCodePayResponse.getTrxstatus());
					titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.build(em.getRemark()));
				}
				
				return titanQrCodePayResponse;
				
			}else{
				
				logger.error("【通联-扫码支付】失败 httpRes为空");
				titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);;
				return titanQrCodePayResponse;
			}
			
		} catch (Exception e) {
			logger.error("【通联-扫码支付】发生异常：", e);
			titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanQrCodePayResponse;
		}
		
	}

}
