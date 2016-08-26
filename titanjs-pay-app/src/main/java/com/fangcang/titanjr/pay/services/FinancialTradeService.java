package com.fangcang.titanjr.pay.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.facade.TitanFinancialPermissionFacade;
import com.fangcang.titanjr.pay.req.TitanConfirmBussOrderReq;
import com.fangcang.titanjr.pay.req.TitanNotifyPayResultReq;
import com.fangcang.titanjr.pay.rsp.TianConfirmBussOrderRsp;
import com.fangcang.titanjr.pay.task.TitanPayResultNotifyTask;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.pay.util.TitanThreadPool;
import com.fangcang.titanjr.request.AccountInfoRequest;
import com.fangcang.titanjr.request.CheckPermissionRequest;
import com.fangcang.titanjr.response.CheckAccountResponse;
import com.fangcang.titanjr.response.PermissionResponse;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.util.StringUtil;

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

	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;

	@Resource
	private TitanFinancialPermissionFacade titanFinancialPermissionFacade;

	/**
	 * 确认业务的订单信息
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
	 * 检查付款和付款方的权限
	 * 
	 * @param dto
	 * @return
	 */
	public boolean checkPermission(TitanOrderRequest dto) {
		PayerTypeEnum pe = PayerTypeEnum.getPayerTypeEnumByKey(dto
				.getPayerType());

		CheckPermissionRequest permissionRequest = null;
		PermissionResponse permissionResponse = null;

		if (pe.isFcUserId() && StringUtil.isValidString(dto.getUserId())) {
			permissionRequest = new CheckPermissionRequest();
			permissionRequest.setFcuserid(dto.getUserId());
			permissionRequest.setPermission("1");

			log.info("check permission userId= " + dto.getUserId());

			permissionResponse = titanFinancialPermissionFacade
					.isPermissionToPayment(permissionRequest);

			if (permissionResponse == null || (!permissionResponse.isResult())) {
				if (permissionResponse != null) {
					log.error("checkPermission response  "
							+ permissionResponse.getReturnCode() + ":"
							+ permissionResponse.getReturnMessage());
				}
				return false;
			}
		}

		if (pe.isReicveMerchantCode()
				&& StringUtil.isValidString(dto.getRuserId())) {
			AccountInfoRequest accountInfo = new AccountInfoRequest();
			accountInfo.setMerchantCode(dto.getRuserId());
			log.info("check permission ruserId= " + dto.getRuserId());

			CheckAccountResponse response = titanFinancialPermissionFacade
					.isFinanceAccount(accountInfo);

			if (response == null || (!response.isResult())) {
				if (response != null) {
					log.error("checkPermission response  "
							+ response.getReturnCode() + ":"
							+ response.getReturnMessage());
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * 根据指定的URL确认业务订单的金额
	 * 
	 * @param dto
	 * @return
	 */
	public boolean checkConfirmBussOrder(TitanOrderRequest dto) {
		if (StringUtil.isValidString(dto.getCheckOrderUrl())) {
			TitanConfirmBussOrderReq req = new TitanConfirmBussOrderReq();
			req.setAmount(dto.getAmount());
			req.setBussOrderNo(dto.getGoodsId());
			req.setUrl(dto.getCheckOrderUrl());
			TianConfirmBussOrderRsp bussOrderRsp = this.confirmBussOrder(req);

			if (bussOrderRsp == null || !bussOrderRsp.isSuccess()) {
				if (bussOrderRsp != null) {
					log.error("checkConfirmBussOrder response  "
							+ bussOrderRsp.getResult().getResMsg());
				}
				return false;
			}

		}
		return true;
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
