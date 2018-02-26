/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLCommonServiceImpl.java
 * @author Jerry
 * @date 2017年12月20日 下午2:20:46  
 */
package com.titanjr.checkstand.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

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
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.JsonUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.TLQrTradeQueryRequest;
import com.titanjr.checkstand.respnse.TLQrTradeQueryResponse;
import com.titanjr.checkstand.service.TLCommonService;

/**
 * @author Jerry
 * @date 2017年12月20日 下午2:20:46  
 */
@Service("tlCommonService")
public class TLCommonServiceImpl implements TLCommonService {
	
	private final static Logger logger = LoggerFactory.getLogger(TLCommonServiceImpl.class);
	
	@Resource
	private TitanOrderService titanOrderService;

	@Override
	public TLQrTradeQueryResponse qrCodeTradeQuery(TLQrTradeQueryRequest tlQrTradeQueryRequest) {
		
		TLQrTradeQueryResponse tlQrTradeQueryResponse = null;
		String responseStr ="";
		
		try {
			
			String configKey = tlQrTradeQueryRequest.getCusid() +"_" + PayTypeEnum.QR_ALIPAY.combPayType + 
					"_" + SysConstant.TL_CHANNEL_CODE + "_" + tlQrTradeQueryRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【通联-扫码/公众号支付交易查询】失败，获取网关配置为空，configKey={}", configKey);
				return tlQrTradeQueryResponse;
			}
			logger.info("【通联-扫码/公众号支付交易查询】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			
			//TreeMap会按字段名的ASCLL码从小到大排序
			TreeMap<String,String> params = new TreeMap<String,String>();
			params.put("cusid", tlQrTradeQueryRequest.getCusid());
			params.put("appid", gateWayConfigDTO.getAppId());
			params.put("version", tlQrTradeQueryRequest.getVersion());
			params.put("reqsn", tlQrTradeQueryRequest.getReqsn());
			params.put("randomstr", tlQrTradeQueryRequest.getRandomstr());
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
			logger.info("【通联-扫码/公众号支付交易查询】加密前排序为：{}", sb.toString());
			String md5Msg = MD5.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
			logger.info("【通联-扫码/公众号支付交易查询】加密后sign为：{}", md5Msg);
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
	    	logger.info("【通联-扫码/公众号支付交易查询】上送参数：{}", paramBuf.toString());
			
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl() + "?" + paramBuf.toString());
			HttpResponse httpRes = HttpClient.httpRequest(new ArrayList<NameValuePair>(), httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				logger.info("【通联-扫码/公众号支付交易查询】返回信息：{}", responseStr);
				
				tlQrTradeQueryResponse = (TLQrTradeQueryResponse)JsonUtil.jsonToBean(responseStr, TLQrTradeQueryResponse.class);
				
				return tlQrTradeQueryResponse;
				
			}else{
				
				logger.error("【通联-扫码/公众号支付交易查询】失败 httpRes为空");
				return tlQrTradeQueryResponse;
			}
			
		} catch (Exception e) {
			logger.error("【通联-扫码/公众号支付交易查询】发生异常：", e);
			return tlQrTradeQueryResponse;
		}
		
	}

}
