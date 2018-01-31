/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName ChannelCallbackController.java
 * @author Jerry
 * @date 2017年11月27日 下午5:28:18  
 */
package com.titanjr.checkstand.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.JsonUtil;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.request.RBDataRequest;
import com.titanjr.checkstand.request.RBQuickPayCallbackResponse;
import com.titanjr.checkstand.request.TLNetBankPayCallbackRequest;
import com.titanjr.checkstand.request.TLQrCodePayCallbackRequest;
import com.titanjr.checkstand.respnse.RBCardAuthResponse;
import com.titanjr.checkstand.respnse.TitanCardAuthResponse;
import com.titanjr.checkstand.respnse.TitanPayCallBackResponse;
import com.titanjr.checkstand.service.TitanCommonService;
import com.titanjr.checkstand.util.rbUtil.Decipher;

/**
 * 支付渠道回调控制器
 * @author Jerry
 * @date 2017年11月27日 下午5:28:18  
 */
@Controller
@RequestMapping(value = "/callback")
public class ChannelCallbackController extends BaseController {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 4077037033223889564L;
	private final static Logger logger = LoggerFactory.getLogger(ChannelCallbackController.class);
	
	@Resource
	private TitanCommonService titanCommonService;
	
	@Resource
	private TitanOrderService titanOrderService;
	
	
	/**
	 * 通联网银支付前台回调，只有支付成功才回调
	 * @author Jerry
	 * @date 2017年11月27日 下午5:33:38
	 */
	@RequestMapping(value = "/tlNetBankPayConfirmPage", method = {RequestMethod.POST})
	public String tlNetBankPayConfirmPage(HttpServletRequest request, TLNetBankPayCallbackRequest 
			tlNetBankPayCallbackRequest, Model model){
		
		logger.info("=========================通联网银支付前台回调：{}", tlNetBankPayCallbackRequest.toString());
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		
		try {
			ValidateResponse res = GenericValidate.validateNew(tlNetBankPayCallbackRequest);
			if (!res.isSuccess()){
				logger.error("【网银支付前台回调】参数错误：{}", res.getReturnMessage());
				return super.payFailedCallback(model);
			}
			
			payCallBackResponse = this.getNetBankPayCallbackRequest(tlNetBankPayCallbackRequest);
			if(!payCallBackResponse.isSuccess()){
				return super.payFailedCallback(model);
			}
			payCallBackResponse.putError();
			
			payCallBackResponse = titanCommonService.payConfirmCallback(payCallBackResponse.getRechargeResultConfirmRequest());
			if(!payCallBackResponse.isSuccess()){
				logger.error("【网银支付前台回调】失败");
				return super.payFailedCallback(model);
			}
			
			model.addAttribute("payConfirmPageUrl", payCallBackResponse.getPayConfirmPageUrl());
			model.addAttribute("confirmPage", payCallBackResponse.getRechargeResultConfirmRequest());
			return "payment/payConfirmPageCallback";
			
		} catch (Exception e) {
			
			logger.error("【网银支付前台回调】发生异常：", e);
			return super.payFailedCallback(model);
			
		}
		
	}
	
	/**
	 * 通联网银支付后台通知
	 * @author Jerry
	 * @date 2017年11月27日 下午5:33:38
	 */
	@RequestMapping(value = "/tlNetBankPayNotice", method = {RequestMethod.POST})
	public void tlNetBankPayNotice(HttpServletRequest request, TLNetBankPayCallbackRequest 
			tlNetBankPayCallbackRequest){
		
		logger.info("=========================通联网银支付后台通知：{}", tlNetBankPayCallbackRequest.toString());
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		
		try {
			ValidateResponse res = GenericValidate.validateNew(tlNetBankPayCallbackRequest);
			if (!res.isSuccess()){
				logger.error("【网银支付后台通知】参数错误：{}", res.getReturnMessage());
				return;
			}
			
			payCallBackResponse = this.getNetBankPayCallbackRequest(tlNetBankPayCallbackRequest);
			if(!payCallBackResponse.isSuccess()){
				return;
			}
			payCallBackResponse.putError();
			
			payCallBackResponse = titanCommonService.PayNotice(payCallBackResponse.getRechargeResultConfirmRequest());
			if(!payCallBackResponse.isSuccess()){
				logger.error("【网银支付后台通知】失败");
				return;
			}
			
			logger.info("【网银支付后台通知】成功");
			
		} catch (Exception e) {
			
			logger.error("【网银支付后台通知】发生异常：", e);
			return;
			
		}
		
	}
	
	/**
	 * 通联扫码支付后台通知
	 * @author Jerry
	 * @date 2017年12月21日 上午9:53:49
	 */
	@RequestMapping(value = "/tlQrCodePayNotice", method = {RequestMethod.POST})
	public void tlQrCodePayNotice(HttpServletRequest request, TLQrCodePayCallbackRequest 
			tlQrCodePayCallbackRequest){
		
		logger.info("=========================通联扫码支付结果通知：{}", tlQrCodePayCallbackRequest.toString());
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		
		try {
			
			payCallBackResponse = this.getQrCodePayCallbackRequest(tlQrCodePayCallbackRequest);
			if(!payCallBackResponse.isSuccess()){
				return;
			}
			payCallBackResponse.putError();
			
			payCallBackResponse = titanCommonService.PayNotice(payCallBackResponse.getRechargeResultConfirmRequest());
			if(!payCallBackResponse.isSuccess()){
				logger.error("【扫码支付后台通知】失败");
				return;
			}
			
			logger.info("【扫码支付后台通知】成功");
			
		} catch (Exception e) {
			
			logger.error("【扫码支付后台通知】发生异常：", e);
			return;
			
		}
		
	}
	
	/**
	 * 通联微信公众号支付后台通知
	 * @author Jerry
	 * @date 2018年1月23日 下午4:18:06
	 */
	@RequestMapping(value = "/tlWechatPayNotice", method = {RequestMethod.POST})
	public void tlWechatPayNotice(HttpServletRequest request, TLQrCodePayCallbackRequest 
			tlQrCodePayCallbackRequest){
		
		logger.info("=========================通联微信公众号支付结果通知");
		
	}
	
	/**
	 * 融宝快捷支付后台通知
	 * @author Jerry
	 * @throws Exception 
	 * @date 2018年1月5日 下午5:00:50
	 */
	@RequestMapping(value = "/rbQuickPayNotice")
	public void rbQuickPayNotice(HttpServletRequest request, HttpServletResponse response, 
			RBDataRequest rbDataRequest) {
		
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		
		try {
			
			RBQuickPayCallbackResponse quickPayCallbackResponse = new RBQuickPayCallbackResponse();
			response.getWriter().print("success");
			response.flushBuffer();
			String jsonRes = JsonUtil.objectToJson(rbDataRequest);
			jsonRes = Decipher.decryptData(jsonRes);
			quickPayCallbackResponse = (RBQuickPayCallbackResponse) JsonUtil.jsonToBean(jsonRes,
					RBQuickPayCallbackResponse.class);
			logger.info("【融宝-快捷支付结果通知】:" + quickPayCallbackResponse.toString());
			
			payCallBackResponse = this.getQuickPayCallbackRequest(quickPayCallbackResponse);
			if(!payCallBackResponse.isSuccess()){
				return;
			}
			payCallBackResponse.putError();
			
			payCallBackResponse = titanCommonService.PayNotice(payCallBackResponse.getRechargeResultConfirmRequest());
			if(!payCallBackResponse.isSuccess()){
				logger.error("【融宝-快捷支付结果通知】失败");
				return;
			}
			
			logger.info("【融宝-快捷支付结果通知】成功");
			
		} catch (Exception e) {
			
			logger.error("【融宝-快捷支付结果通知】发生异常：", e);
			return;
		}
		
	}
	
	
	/**
	 * 卡密鉴权前台回调
	 * @author Jerry
	 * @date 2018年1月31日 上午11:40:01
	 */
	@RequestMapping(value = "/rbCardAuthPage")
	public String rbCardAuthPage(HttpServletRequest request, HttpServletResponse response, 
			RBDataRequest rbDataRequest, Model model) {
		
		TitanCardAuthResponse titanCardAuthResponse = new TitanCardAuthResponse();
		RBCardAuthResponse rbCardAuthResponse = new RBCardAuthResponse();
		
		try {
			
			String jsonRes = JsonUtil.objectToJson(rbDataRequest);
			jsonRes = Decipher.decryptData(jsonRes);
			rbCardAuthResponse = (RBCardAuthResponse) JsonUtil.jsonToBean(jsonRes,
					RBCardAuthResponse.class);
			logger.info("【融宝-卡密鉴权前台回调】:" + rbCardAuthResponse.toString());
			
			titanCardAuthResponse = titanCommonService.cardAuthPage(rbCardAuthResponse);
			if(titanCardAuthResponse == null){
				return super.payFailedCallback(model);
			}
			
			model.addAttribute("titanCardAuthResponse", titanCardAuthResponse);
			return "payment/titanCardAuthCallback";
			
		} catch (Exception e) {
			
			logger.error("【融宝-卡密鉴权前台回调】发生异常：", e);
			return super.payFailedCallback(model);
		}
		
	}
	
	
	/**
	 * 卡密鉴权后台通知（待完善）
	 * @author Jerry
	 * @date 2018年1月31日 下午3:07:49
	 */
	@RequestMapping(value = "/rbCardAuthNotice")
	public void rbCardAuthNotice(HttpServletRequest request, HttpServletResponse response, 
			RBDataRequest rbDataRequest) {
		
		/*TitanCardAuthResponse titanCardAuthResponse = new TitanCardAuthResponse();
		RBCardAuthResponse rbCardAuthResponse = new RBCardAuthResponse();*/
		
		try {
			
			RBQuickPayCallbackResponse data = new RBQuickPayCallbackResponse();
			response.getWriter().print("success");
			response.flushBuffer();
			String jsonRes = JsonUtil.objectToJson(rbDataRequest);
			jsonRes = Decipher.decryptData(jsonRes);
			data = (RBQuickPayCallbackResponse) JsonUtil.jsonToBean(jsonRes,
					RBQuickPayCallbackResponse.class);
			logger.info("【融宝-卡密鉴权后台通知】:" + data.toString());
			
			
			
		} catch (Exception e) {
			
			logger.error("【融宝-卡密鉴权后台通知】发生异常：", e);
			return;
		}
		
	}
	
	
	private TitanPayCallBackResponse getNetBankPayCallbackRequest(TLNetBankPayCallbackRequest tlNetBankPayCallbackRequest){
		
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		payCallBackResponse.putError();
		
		TransOrderDTO transOrderDTO = this.getTransOrder(tlNetBankPayCallbackRequest.getOrderNo());
		if(transOrderDTO == null){
			return payCallBackResponse;
		}
		TitanOrderPayDTO titanOrderPayDTO = this.getOrderPayReq(transOrderDTO.getUserid(), 
				tlNetBankPayCallbackRequest.getOrderNo());
		if(titanOrderPayDTO == null){
			return payCallBackResponse;
		}
		
		RechargeResultConfirmRequest confirmRequest = this.netBank2TitanCallbackRequest(tlNetBankPayCallbackRequest, 
				transOrderDTO.getPayorderno(), titanOrderPayDTO.getPayType());
		payCallBackResponse.setRechargeResultConfirmRequest(confirmRequest);
		payCallBackResponse.putSuccess();
		return payCallBackResponse;
		
	}
	private TitanPayCallBackResponse getQrCodePayCallbackRequest(TLQrCodePayCallbackRequest tlQrCodePayCallbackRequest){
		
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		payCallBackResponse.putError();
		
		TransOrderDTO transOrderDTO = this.getTransOrder(tlQrCodePayCallbackRequest.getCusorderid());
		if(transOrderDTO == null){
			return payCallBackResponse;
		}
		TitanOrderPayDTO titanOrderPayDTO = this.getOrderPayReq(transOrderDTO.getUserid(), 
				tlQrCodePayCallbackRequest.getCusorderid());
		if(titanOrderPayDTO == null){
			return payCallBackResponse;
		}
		
		RechargeResultConfirmRequest confirmRequest = this.qrCode2TitanCallbackRequest(tlQrCodePayCallbackRequest, 
				transOrderDTO.getPayorderno(), titanOrderPayDTO.getPayType());
		payCallBackResponse.setRechargeResultConfirmRequest(confirmRequest);
		payCallBackResponse.putSuccess();
		return payCallBackResponse;
		
	}
	private TitanPayCallBackResponse getQuickPayCallbackRequest(RBQuickPayCallbackResponse rbQuickPayCallbackData){
		
		TitanPayCallBackResponse payCallBackResponse = new TitanPayCallBackResponse();
		payCallBackResponse.putError();
		
		TransOrderDTO transOrderDTO = this.getTransOrder(rbQuickPayCallbackData.getOrder_no());
		if(transOrderDTO == null){
			return payCallBackResponse;
		}
		TitanOrderPayDTO titanOrderPayDTO = this.getOrderPayReq(transOrderDTO.getUserid(), 
				rbQuickPayCallbackData.getOrder_no());
		if(titanOrderPayDTO == null){
			return payCallBackResponse;
		}
		
		RechargeResultConfirmRequest confirmRequest = this.quick2TitanCallbackRequest(rbQuickPayCallbackData, 
				transOrderDTO.getPayorderno(), titanOrderPayDTO.getPayType());
		payCallBackResponse.setRechargeResultConfirmRequest(confirmRequest);
		payCallBackResponse.putSuccess();
		return payCallBackResponse;
		
	}
	
	
	private TransOrderDTO getTransOrder(String orderNo){
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setOrderid(orderNo);
		TransOrderDTO transOrderDTO  = titanOrderService.queryTransOrderDTO(transOrderRequest);
		if(transOrderDTO == null){
			logger.error("【支付路由-支付回调】失败，查询交易单为空，orderNo={}", orderNo);
			return transOrderDTO;
		}
		return transOrderDTO;
		
	}
	private TitanOrderPayDTO getOrderPayReq(String userId, String orderNo){
		TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
		titanOrderPayDTO.setMerchantNo(userId);
		titanOrderPayDTO.setOrderNo(orderNo);
		titanOrderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
		if(titanOrderPayDTO == null){
			logger.error("【支付路由-支付回调】失败，查询充值单为空，orderNo={}", orderNo);
			return titanOrderPayDTO;
		}
		return titanOrderPayDTO;
	}
	
	
	private RechargeResultConfirmRequest netBank2TitanCallbackRequest(TLNetBankPayCallbackRequest 
			tlNetBankPayCallbackRequest, String payOrderNo, String payType){
		RechargeResultConfirmRequest confirmRequest = new RechargeResultConfirmRequest();
		confirmRequest.setMerchantNo(SysConstant.RS_MERCHANT_NO);
		confirmRequest.setOrderNo(tlNetBankPayCallbackRequest.getOrderNo());
		confirmRequest.setOrderAmount(tlNetBankPayCallbackRequest.getOrderAmount().toString());
		confirmRequest.setPayAmount(tlNetBankPayCallbackRequest.getPayAmount().toString());
		confirmRequest.setOrderTime(tlNetBankPayCallbackRequest.getOrderDatetime());
		confirmRequest.setPayStatus("0");
		confirmRequest.setPayMsg("支付失败");
		if("1".equals(tlNetBankPayCallbackRequest.getPayResult())){
			confirmRequest.setPayStatus("3");
			confirmRequest.setPayMsg("支付成功");
		}
		confirmRequest.setPayOrderNo(payOrderNo);
		confirmRequest.setOrderPayTime(tlNetBankPayCallbackRequest.getPayDatetime());
		confirmRequest.setPayType(payType);
		confirmRequest.setVersion(SysConstant.RS_VERSION);
		confirmRequest.setSignType(SysConstant.RS_SIGN_TYPE);
		return confirmRequest;
	}
	private RechargeResultConfirmRequest qrCode2TitanCallbackRequest(TLQrCodePayCallbackRequest 
			tlQrCodePayCallbackRequest, String payOrderNo, String payType){
		RechargeResultConfirmRequest confirmRequest = new RechargeResultConfirmRequest();
		confirmRequest.setMerchantNo(SysConstant.RS_MERCHANT_NO);
		confirmRequest.setOrderNo(tlQrCodePayCallbackRequest.getCusorderid());
		//confirmRequest.setOrderAmount(tlQrCodePayCallbackRequest.getTrxamt());//无返回
		confirmRequest.setPayAmount(tlQrCodePayCallbackRequest.getTrxamt());
		confirmRequest.setOrderTime(tlQrCodePayCallbackRequest.getTrxdate());
		confirmRequest.setPayStatus("0");
		confirmRequest.setPayMsg("支付失败");
		if("0000".equals(tlQrCodePayCallbackRequest.getTrxstatus())){
			confirmRequest.setPayStatus("3");
			confirmRequest.setPayMsg("支付成功");
		}
		confirmRequest.setPayOrderNo(payOrderNo);
		confirmRequest.setOrderPayTime(tlQrCodePayCallbackRequest.getPaytime());
		confirmRequest.setPayType(payType);
		confirmRequest.setVersion(SysConstant.RS_VERSION);
		confirmRequest.setSignType(SysConstant.RS_SIGN_TYPE);
		return confirmRequest;
	}
	private RechargeResultConfirmRequest quick2TitanCallbackRequest(RBQuickPayCallbackResponse 
			rbQuickPayCallbackData, String payOrderNo, String payType){
		RechargeResultConfirmRequest confirmRequest = new RechargeResultConfirmRequest();
		confirmRequest.setMerchantNo(SysConstant.RS_MERCHANT_NO);
		confirmRequest.setOrderNo(rbQuickPayCallbackData.getOrder_no());
		//confirmRequest.setOrderAmount(rbQuickPayCallbackData.getTrxamt());//无返回
		confirmRequest.setPayAmount(rbQuickPayCallbackData.getTotal_fee());
		//confirmRequest.setOrderTime(rbQuickPayCallbackData.getTrxdate());//无返回
		confirmRequest.setPayStatus("0");
		confirmRequest.setPayMsg("支付失败");
		if("TRADE_FINISHED".equals(rbQuickPayCallbackData.getStatus())){
			confirmRequest.setPayStatus("3");
			confirmRequest.setPayMsg("支付成功");
		}
		confirmRequest.setPayOrderNo(payOrderNo);
		//confirmRequest.setOrderPayTime(rbQuickPayCallbackData.getPaytime());//无返回
		confirmRequest.setPayType(payType);
		confirmRequest.setVersion(SysConstant.RS_VERSION);
		confirmRequest.setSignType(SysConstant.RS_SIGN_TYPE);
		return confirmRequest;
	}

}
