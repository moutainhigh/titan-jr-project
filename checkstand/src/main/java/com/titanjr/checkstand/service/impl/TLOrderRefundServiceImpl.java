/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLOrderRefundServiceImpl.java
 * @author Jerry
 * @date 2017年12月4日 下午2:42:58  
 */
package com.titanjr.checkstand.service.impl;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.enums.RefundStatusEnum;
import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.TLNetBankOrderRefundRequest;
import com.titanjr.checkstand.respnse.TLNetBankOrderRefundResponse;
import com.titanjr.checkstand.respnse.TitanOrderRefundResponse;
import com.titanjr.checkstand.service.TLOrderRefundService;
import com.titanjr.checkstand.util.SignMsgBuilder;

/**
 * @author Jerry
 * @date 2017年12月4日 下午2:42:58  
 */
@Service("tlOrderRefundService")
public class TLOrderRefundServiceImpl implements TLOrderRefundService {
	
	private final static Logger logger = LoggerFactory.getLogger(TLPayQueryServiceImpl.class);
	

	@Override
	public TitanOrderRefundResponse netBankOrderRefund(TLNetBankOrderRefundRequest 
			tlNetBankOrderRefundRequest) {
		
		TitanOrderRefundResponse titanOrderRefundResponse = new TitanOrderRefundResponse();
		TLNetBankOrderRefundResponse tlNetBankOrderRefundResponse = new TLNetBankOrderRefundResponse();
		String responseStr ="";
		
		try {
			//查询订单，获取支付方式
			
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(tlNetBankOrderRefundRequest.getMerchantId()+"_1_01_"+tlNetBankOrderRefundRequest.getRequestType());
			tlNetBankOrderRefundRequest.setSignMsg(SignMsgBuilder.getSignMsgForOrderRefund(tlNetBankOrderRefundRequest, gateWayConfigDTO.getSecretKey()));
			logger.info("【通联-订单退款】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> params = BeanConvertor.beanToList(tlNetBankOrderRefundRequest);
			logger.info("【通联-订单退款】请求参数：{}", tlNetBankOrderRefundRequest.toString());
			
			HttpResponse httpRes = HttpClient.httpRequest(params, httpPost);
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				tlNetBankOrderRefundResponse = RSConvertFiled2ObjectUtil.convertField2Object(
						TLNetBankOrderRefundResponse.class, responseStr);
				
				if(tlNetBankOrderRefundResponse != null){
					
					logger.info("【通联-订单退款】返回信息:" + tlNetBankOrderRefundResponse.toString());
					if("20".equals(tlNetBankOrderRefundResponse.getRefundResult())){
						titanOrderRefundResponse.setRefundStatus(String.valueOf(RefundStatusEnum.REFUND_SUCCESS.status));
					}else{
						titanOrderRefundResponse.setRefundStatus(String.valueOf(RefundStatusEnum.REFUND_FAILURE.status));
					}
					
					titanOrderRefundResponse.putSuccess();
					return titanOrderRefundResponse;
					
				}
				
				logger.error("【通联-订单退款】失败， tlNetBankOrderRefundResponse为空");
				titanOrderRefundResponse.putSysError();
				return titanOrderRefundResponse;
				
			}else{
				logger.error("【通联-订单退款】失败 httpRes为空");
				titanOrderRefundResponse.putSysError();
				return titanOrderRefundResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【通联-订单退款】发生异常：{}", e);
			titanOrderRefundResponse.putErrorResult("系统异常");
			return titanOrderRefundResponse;
			
		}
	}

}
