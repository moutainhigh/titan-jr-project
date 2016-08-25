package com.fangcang.titanjr.pay.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.pay.req.TitanConfirmBussOrderReq;
import com.fangcang.titanjr.pay.req.TitanNotifyPayResultReq;
import com.fangcang.titanjr.pay.rsp.TianConfirmBussOrderRsp;
import com.fangcang.titanjr.pay.rsp.TitanNotifyPayResultRsp;
import com.fangcang.titanjr.pay.util.JsonConversionTool;

/**
 * 收銀台交易服務實現類
 * 
 * @author wengxitao
 *
 */
@Component
public class FinancialTradeService {

	private static final Log log = LogFactory
			.getLog(FinancialTradeService.class);

	/**
	 * 确认业务的订单信息
	 * 
	 * @param req
	 * @return
	 */
	public TianConfirmBussOrderRsp confirmBussOrder(TitanConfirmBussOrderReq req) {

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("orderNo", req.getBussOrderNo());
		paramMap.put("orderAmount", req.getAmount());

		log.info("confirm buss order req[" + paramMap + "]" + " url["
				+ req.getUrl() + "]");

		DefaultHttpClient httpclient = new DefaultHttpClient();

		try {
			HttpPost post = HttpUtils.buildPostForm(req.getUrl(), paramMap);
			HttpResponse response = httpclient.execute(post);
			String resultStr = EntityUtils.toString(response.getEntity());
			log.info("confirm buss order response content ["+ resultStr +"]");
			return JsonConversionTool.toObject(resultStr,
					TianConfirmBussOrderRsp.class);

		} catch (Exception ex) {
			log.error("", ex);
		}

		return null;
	}

	public TitanNotifyPayResultRsp notifyPayResult(TitanNotifyPayResultReq req) {
		
		
		
		return null;
	}
}
