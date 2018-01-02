/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLRefundQueryServiceImpl.java
 * @author Jerry
 * @date 2017年12月5日 下午6:48:42  
 */
package com.titanjr.checkstand.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.allinpay.ets.client.SecurityUtil;
import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.constants.TLNetBankRefundResultEnum;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.TLNetBankRefundQueryRequest;
import com.titanjr.checkstand.respnse.TitanRefundQueryResponse;
import com.titanjr.checkstand.service.TLRefundQueryService;
import com.titanjr.checkstand.util.SignMsgBuilder;

/**
 * @author Jerry
 * @date 2017年12月5日 下午6:48:42  
 */
@Service("tlRefundQueryService")
public class TLRefundQueryServiceImpl implements TLRefundQueryService {
	
	private final static Logger logger = LoggerFactory.getLogger(TLRefundQueryServiceImpl.class);
	private static String postUrl = "";

	@Override
	public TitanRefundQueryResponse netBankRefundQuery(TLNetBankRefundQueryRequest tlNetBankRefundQueryRequest) {
		
		TitanRefundQueryResponse titanRefundQueryResponse = new TitanRefundQueryResponse();
		String responseStr ="";
		
		try {
			
			//查询订单，获取支付方式
			
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(tlNetBankRefundQueryRequest.getMerchantId()+"_1_01_"+tlNetBankRefundQueryRequest.getRequestType());
			tlNetBankRefundQueryRequest.setSignMsg(SignMsgBuilder.getSignMsgForRefundQuery(tlNetBankRefundQueryRequest, gateWayConfigDTO.getSecretKey()));
			ValidateResponse res = GenericValidate.validateNew(tlNetBankRefundQueryRequest);
			if (!res.isSuccess()){
				logger.error("参数错误：{}", res.getReturnMessage());
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanRefundQueryResponse;
			}
			postUrl = gateWayConfigDTO.getGateWayUrl();
			logger.info("【通联-网银退款查询】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> params = BeanConvertor.beanToList(tlNetBankRefundQueryRequest);
			logger.info("【通联-网银退款查询】请求参数：{}", tlNetBankRefundQueryRequest.toString());
			
			HttpResponse httpRes = HttpClient.httpRequest(params, httpPost);
			if (null != httpRes) {
				HttpEntity entity = httpRes.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				return NetBankRefundQueryResult(responseStr, gateWayConfigDTO.getSecretKey());
				
			}else{
				logger.error("【通联-网银退款查询】失败 httpRes为空");
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanRefundQueryResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【通联-网银退款查询】发生异常：{}", e);
			titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanRefundQueryResponse;
			
		}
		
	}
	
	
	/**
	 * 网银退款查询结果解析
	 * @author Jerry
	 * @date 2017年12月6日 下午6:17:14
	 */
	private TitanRefundQueryResponse NetBankRefundQueryResult(String responseStr, String key) throws IOException{
		
		TitanRefundQueryResponse titanRefundQueryResponse = new TitanRefundQueryResponse();
		logger.info("【通联-网银退款查询】返回信息：{}", responseStr);
		if(!StringUtil.isValidString(responseStr)){
			logger.error("【通联-网银退款查询】失败：返回消息为空");
			titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanRefundQueryResponse;
		}
		responseStr = URLDecoder.decode(responseStr, "UTF-8");
		
		if(responseStr.indexOf("ERRORCODE=") < 0){
			String[] responseArr = responseStr.trim().split("\\|");
			String refundResult = responseArr[7];
			String orderNo = responseArr[3];
			String signTypeMsg = responseArr[1];;//签名类型
			String fileAsString = ""; // 签名信息前的对账文件内容（签名源串）
			String fileSignMsg = ""; // 文件签名信息
			String lines;
			
			//获取退款结果
			if(!StringUtil.isValidString(refundResult)){
				logger.error("【通联-网银退款查询】解析refundResult为空");
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanRefundQueryResponse;
			}
			
			TLNetBankRefundResultEnum refundResultEnum = TLNetBankRefundResultEnum.getTLRefundResultEnumByKey(refundResult);
			if(refundResultEnum == null){
				logger.error("【通联-网银退款查询】通联返回的退款状态未匹配到应有的状态");
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanRefundQueryResponse;
			}
			titanRefundQueryResponse.setRefundStatus(refundResultEnum.getStatus());
			logger.info("【通联-网银退款查询】退款结果为：{}", refundResultEnum.getRemark());
			
			//验签
			BufferedReader fileReader = new BufferedReader(new StringReader(responseStr));
			StringBuffer fileBuf = new StringBuffer(); // 签名信息前的字符串	
			List<String> refundOrderList = new ArrayList<String>();
			while ((lines = fileReader.readLine()) != null) {
				if (lines.length() > 0) {
					//将数据放入List中
					refundOrderList.add(lines);
				} 
			}
			fileReader.close();
			//取签名源串 
			for(int i=0;i<refundOrderList.size()-2;i++){
					fileBuf.append(refundOrderList.get(i)+"\r\n");
			}
			fileBuf.append(refundOrderList.get(refundOrderList.size()-2));
			fileAsString = fileBuf.toString();
			//取签名信息 
			fileSignMsg = refundOrderList.get(refundOrderList.size()-1);
			signTypeMsg = String.valueOf(responseStr.charAt(5));
			
			boolean isVerified = false; // 验证签名结果
			if("0".equals(signTypeMsg)){
				// 验证签名：先对文件内容计算MD5摘要，再将MD5摘要与返回的签名进行对比
				String sourceString = fileAsString+"|"+key;
				String fileMd5 = SecurityUtil.MD5Encode(sourceString);
				if(fileMd5.equals(fileSignMsg)){
					logger.info("【通联-网银退款查询】验签成功，orderNo={}", orderNo);
					
				}else{
					logger.info("【通联-网银退款查询】验签失败，orderNo={}", orderNo);
				}
				
			}else if("1".equals(signTypeMsg)){
				
		        String fileMd5String = SecurityUtil.MD5Encode(fileAsString);
				String certPath="";
				if(postUrl.indexOf("service.allinpay.com")>0){
					certPath="/opt/conf/TLCert-prod.cer";//生产证书路径
				}else{
					certPath="/opt/conf/TLCert-test.cer"; //测试证书路径
				}
				isVerified = SecurityUtil.verifyByRSA(certPath, fileMd5String.getBytes(), Base64.decode(fileSignMsg));
				if (isVerified) {
					logger.info("【通联-网银退款查询】验签成功，orderNo={}", orderNo);
				} else {
					logger.info("【通联-网银退款查询】验签失败，orderNo={}", orderNo);
	
				}
				
			}else{
				logger.error("【通联-网银退款查询】验签失败，解析signTypeMsg={}", signTypeMsg);
			}
			
		}else{
			
			Map<String, String> result = new HashMap<String, String>();
			String[] parameters = responseStr.split("&");
			for (int i = 0; i < parameters.length; i++) {
				String msg = parameters[i];
				int index = msg.indexOf('=');
				if (index > 0) {
					String name = msg.substring(0, index);
					String value = msg.substring(index + 1);
					result.put(name, value);
				}
			}
			//订单号或退款单号有误都会返回：订单号不存在
			logger.error("【通联-网银退款查询】失败：{}", result.get("ERRORMSG"));
			titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.build(result.get("ERRORMSG")));
			return titanRefundQueryResponse;
		}
		
		return titanRefundQueryResponse;
	}

}
