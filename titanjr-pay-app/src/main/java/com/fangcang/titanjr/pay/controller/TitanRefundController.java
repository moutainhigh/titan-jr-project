package com.fangcang.titanjr.pay.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.RefundRequest;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.RefundResponse;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.services.TitanRefundService;
import com.fangcang.titanjr.pay.util.RSADecryptString;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.JsonUtil;
import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("refund")
public class TitanRefundController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(TitanRefundController.class);
	
	private static Map<String,Object> mapLock = new  ConcurrentHashMap<String, Object>();
	@Resource
	TitanRefundService titanRefundService;
	
	@Resource
	TitanOrderService titanOrderService;
	
	@Resource
	TitanFinancialAccountService titanFinancialAccountService;
	
	/**
	 * 商家请求退款
	 * @param userId
	 * @return
	 */
	@RequestMapping("orderRefund")
	@ResponseBody
	public String orderRefund(RefundRequest refundRequest,Model model){
	
		
		if(null == refundRequest 
				||!StringUtil.isValidString(refundRequest.getOrderNo())
				||!StringUtil.isValidString(refundRequest.getPayPassword())
				||!StringUtil.isValidString(refundRequest.getUserId())
				||!StringUtil.isValidString(refundRequest.getTfsUserid())
				||!StringUtil.isValidString(refundRequest.getNotifyUrl())
				){
			log.error("参数传入异常");
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
			
		}
		
		RefundResponse response = titanRefundService.orderRefund(refundRequest);
		return JsonUtil.objectToJson(response);
		
	}
	
	@RequestMapping("refundRequest")
	public String refundRequest(String refundInfo,Model model){
		getRequest().getSession();

		if (!StringUtil.isValidString(refundInfo)) {
			log.error("退款申请的请求参数为空");
			model.addAttribute("msg",
				TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
    
		String deInfo = RSADecryptString.decryptString(refundInfo,
			TitanConstantDefine.PRIVATE_KEY);
		if (!StringUtil.isValidString(deInfo)) {
			log.error("验签不通过");
			model.addAttribute("msg",
				TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}

		RefundRequest refundRequest = JsonConversionTool.toObject(deInfo,
			RefundRequest.class);
		log.info("退款的参数"+JSONSerializer.toJSON(refundRequest));
	
		if(null == refundRequest || !StringUtil.isValidString(refundRequest.getOrderNo())
					||!StringUtil.isValidString(refundRequest.getUserId())
					||!StringUtil.isValidString(refundRequest.getTfsUserid())
					||!StringUtil.isValidString(refundRequest.getNotifyUrl())
				){
			log.error("参数传入异常");
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		
		return titanRefundService.refundRequest(refundRequest, model);
	}
	
	
}
