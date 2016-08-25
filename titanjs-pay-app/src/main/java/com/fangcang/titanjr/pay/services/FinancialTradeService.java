package com.fangcang.titanjr.pay.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.pay.req.TitanConfirmBussOrderReq;
import com.fangcang.titanjr.pay.req.TitanNotifyPayResultReq;
import com.fangcang.titanjr.pay.rsp.TianConfirmBussOrderRsp;
import com.fangcang.titanjr.pay.task.TitanPayResultNotifyTask;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.pay.util.TitanThreadPool;

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

		log.info("confirm buss order req =" + JsonConversionTool.toJson(req));

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("orderNo", req.getBussOrderNo());
		paramMap.put("orderAmount", req.getAmount());

		try {
			HttpPost post = HttpUtils.buildPostForm(req.getUrl(), paramMap);
			post.setConfig(HttpUtils.getDefRequestConfig());
			HttpResponse response = HttpUtils.getHttpClientFactory().execute(
					post);
			String resultStr = EntityUtils.toString(response.getEntity());
			log.info("confirm buss order response content [" + resultStr + "]");
			return JsonConversionTool.toObject(resultStr,
					TianConfirmBussOrderRsp.class);

		} catch (Exception ex) {
			log.error("", ex);
		}

		return null;
	}

	/**
	 * 通知业务付款结果
	 * 
	 * @param req
	 */
	public void notifyPayResult(TitanNotifyPayResultReq req) {

		String reqJson = JsonConversionTool.toJson(req);

		log.info("notify pay result req =" + reqJson);

		String taskId = MD5.MD5Encode(reqJson + System.currentTimeMillis());
		
		log.info("gen taskId = " + taskId);
		TitanPayResultNotifyTask notifyTask = new TitanPayResultNotifyTask();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("resultCode", "" + req.getResultCode());
		paramMap.put("resultMsg", req.getResultMsg());
		notifyTask.setTaskId(taskId);
		notifyTask.setUrl(req.getUrl());
		if (req.getBussInfos() != null) {
			paramMap.putAll(req.getBussInfos());
		}
		notifyTask.setBussInfos(paramMap);

		log.info("execute pay result notity taskId=" + taskId);
		TitanThreadPool.executeTask(notifyTask);

	}
}
