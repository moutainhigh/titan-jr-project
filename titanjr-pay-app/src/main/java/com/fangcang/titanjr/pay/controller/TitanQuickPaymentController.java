package com.fangcang.titanjr.pay.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.QuickPayBankEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.bean.gateway.QuickPayCardDTO;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.gateway.CardSceurityVerifyRequest;
import com.fangcang.titanjr.dto.request.gateway.ConfirmRechargeRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryBankCardBINRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryQuickPayBindCardRequest;
import com.fangcang.titanjr.dto.request.gateway.ReSendVerifyCodeRequest;
import com.fangcang.titanjr.dto.request.gateway.UnbindBankCardRequest;
import com.fangcang.titanjr.dto.request.gateway.UpdateBankCardPhoneResponseRequest;
import com.fangcang.titanjr.dto.response.gateway.CardSceurityVerifyResponse;
import com.fangcang.titanjr.dto.response.gateway.ConfirmRechargeResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryBankCardBINIResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryQuickPayBindCardResponse;
import com.fangcang.titanjr.dto.response.gateway.ReSendVerifyCodeResponse;
import com.fangcang.titanjr.dto.response.gateway.UnbindBankCardResponse;
import com.fangcang.titanjr.dto.response.gateway.UpdateBankCardPhoneResponse;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.pay.util.TerminalUtil;
import com.fangcang.titanjr.service.RSGatewayInterfaceService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.JsonUtil;
import com.fangcang.util.StringUtil;

/**
 * 快捷支付
 * @author jerry
 * 
 * @date 2017.6.15
 *
 */
@Controller      
@RequestMapping("quickPay")
public class TitanQuickPaymentController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3647149949437511471L;
	
	private static final Log log = LogFactory.getLog(TitanQuickPaymentController.class);
	
	@Resource
	private RSGatewayInterfaceService rsGatewayInterfaceService;
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private TitanPaymentService titanPaymentService;
	
	
	/**
	 * 确认充值（支付历史）
	 * @param confirmRechargeRequest
	 * @return
	 */
	@RequestMapping(value = {"/quickPayRecharge"})
	@ResponseBody
	public ConfirmRechargeResponse quickPayRecharge(ConfirmRechargeRequest confirmRechargeRequest){
		
		ConfirmRechargeResponse confirmRechargeResponse = rsGatewayInterfaceService.confirmRecharge(confirmRechargeRequest);
		
		return confirmRechargeResponse;
	}
	
	
	/**
	 * 确认充值（表单提交）
	 * @param confirmRechargeRequest
	 * @return
	 */
	@RequestMapping(value = {"/confirmRecharge"})
	public String confirmRecharge(ConfirmRechargeRequest confirmRechargeRequest, Model model){
		
		RechargeResultConfirmRequest RechargeResultConfirmRequest = new RechargeResultConfirmRequest();
		
		ConfirmRechargeResponse confirmRechargeResponse = rsGatewayInterfaceService
				.confirmRecharge(confirmRechargeRequest);
		
		if(confirmRechargeResponse.isSuccess() && StringUtil.isValidString(confirmRechargeResponse.getOrderNo())){
			
			int index = 0;
			
			while (index < 3) {
				
				try {
					//4,8,16
					Thread.sleep(2000 * (2<<index));
				} catch (InterruptedException e) {
					log.error("网关充值成功，本地查询订单状态线程等待异常：", e);
				}
				
				String orderStatus = titanOrderService.confirmOrderStatus(confirmRechargeResponse.getOrderNo());
				log.info("try confirmOrderStatus, index=" + (2000 * (2<<index)) + ", orderStatus=" + orderStatus);
				
				if("success".equals(orderStatus) || "fail".equals(orderStatus)){
					
					RechargeResultConfirmRequest.setOrderNo(confirmRechargeResponse.getOrderNo());
					return titanPaymentService.payConfirmPage(RechargeResultConfirmRequest, model);
					
				}else if("delay".equals(orderStatus)){
					
					RechargeResultConfirmRequest.setOrderNo(confirmRechargeResponse.getOrderNo());
					RechargeResultConfirmRequest.setExpand(CommonConstant.ORDER_DELAY);
					return titanPaymentService.payConfirmPage(RechargeResultConfirmRequest, model);
				}
				
				index ++;
			}
			
		}
		
		model.addAttribute("confirmRechargeResponse", confirmRechargeResponse);
		log.error("confirmRecharge is success, but confirmOrderStatus failed after try again three times");
		return "checkstand-pay/payResult";
	}
	
	
	/**
	 * 重发验证码
	 * @author jerry
	 * 
	 * @param reSendVerifyCodeRequest
	 * @return
	 */
	@RequestMapping(value = {"/reSendVerifyCode"})
	@ResponseBody
	public ReSendVerifyCodeResponse reSendVerifyCode(ReSendVerifyCodeRequest reSendVerifyCodeRequest){
		
		ReSendVerifyCodeResponse reSendVerifyCodeResponse = rsGatewayInterfaceService.reSendVerifyCode(reSendVerifyCodeRequest);
		
		return reSendVerifyCodeResponse;
	}
	
	
	/**
	 * 查询快捷支付卡绑定信息
	 * @param queryQuickPayBindCardRequest
	 * @return
	 */
	@RequestMapping(value = {"/queryQuickPayBindCardInfo"})
	@ResponseBody
	public List<QuickPayCardDTO> queryQuickPayBindCardInfo(QueryQuickPayBindCardRequest queryQuickPayBindCardRequest){
		
		QueryQuickPayBindCardResponse queryQuickPayBindCardResponse = rsGatewayInterfaceService.queryQuickPayBindCardInfo(queryQuickPayBindCardRequest);
		
		return queryQuickPayBindCardResponse.getAgentProtocolList();
	}
	
	
	/**
	 * 查询银行卡卡信息
	 * @param queryBankCardBIN
	 * @return
	 */
	@RequestMapping(value = {"/queryBankCardBIN"})
	@ResponseBody
	public QueryBankCardBINIResponse queryBankCardBIN(HttpServletRequest request, 
			QueryBankCardBINRequest queryBankCardBINRequest){
		
		QueryBankCardBINIResponse bankCardBINIResponse = rsGatewayInterfaceService.queryBankCardBIN(queryBankCardBINRequest);
		
		return bankCardBINIResponse;
	}
	
	
	/**
	 * 校验用户输入的银行卡号是否支持快捷支付
	 * @author Jerry
	 * @date 2017年7月25日 下午7:45:04
	 * @param queryBankCardBINRequest
	 * @return
	 */
	@RequestMapping(value = {"/checkCardCanQuickPay"})
	@ResponseBody
	public QueryBankCardBINIResponse checkCardCanQuickPay(QueryBankCardBINRequest queryBankCardBINRequest){
		
		QueryBankCardBINIResponse bankCardBINIResponse = rsGatewayInterfaceService
				.queryBankCardBIN(queryBankCardBINRequest);
		
		if(bankCardBINIResponse.isSuccess()){
			
			if(QuickPayBankEnum.isExist(bankCardBINIResponse.getBankCode(), bankCardBINIResponse.getCardType())){
				
				QuickPayBankEnum bankEnum = QuickPayBankEnum.getBankEnum(bankCardBINIResponse.getBankCode()
						, bankCardBINIResponse.getCardType());
				bankCardBINIResponse.setBankInfo(bankEnum.getBankInfo());
				bankCardBINIResponse.setSingleLimit(bankEnum.getSingleLimit());
				bankCardBINIResponse.setDailyLimit(bankEnum.getDailyLimit());
				return bankCardBINIResponse;
				
			}
			bankCardBINIResponse.putError("该银行卡不支持快捷支付");
		}
		
		return bankCardBINIResponse;
	}
	
	
	@RequestMapping(value = {"/unBindBankCard"})
	@ResponseBody
	public UnbindBankCardResponse unBindBankCard(UnbindBankCardRequest unbindBankCardRequest){
		
		UnbindBankCardResponse unbindBankCardResponse = rsGatewayInterfaceService.unBindBankCard(unbindBankCardRequest);
		
		return unbindBankCardResponse;
	}
	
	
	
	@RequestMapping(value = {"/updateBankCardPhone"})
	@ResponseBody
	public UpdateBankCardPhoneResponse updateBankCardPhone(UpdateBankCardPhoneResponseRequest updatePhoneNumberRequest){
		
		updatePhoneNumberRequest.setAcctName("韩梅梅");
		UpdateBankCardPhoneResponse updateBankCardPhoneResponse = rsGatewayInterfaceService
				.updateBankCardPhone(updatePhoneNumberRequest);
		
		return updateBankCardPhoneResponse;
	}
	
	
	@RequestMapping(value = {"/cardSceurityVerify"})
	public String cardSceurityVerify(HttpServletRequest request, CardSceurityVerifyRequest 
			cardSceurityVerifyRequest, Model model){
		
		String strUserAgent = request.getHeader("user-agent").toString().toLowerCase();
		String terminalType = null;
		if(TerminalUtil.check(strUserAgent)){
			terminalType = "mobile";
		}else{
			terminalType = "web";
		}
		cardSceurityVerifyRequest.setTerminalType(terminalType);
		
		CardSceurityVerifyRequest cardSceurityVerifyParam = rsGatewayInterfaceService
				.getCardSceurityVerifyParam(cardSceurityVerifyRequest);
		
		model.addAttribute("cardSceurityVerifyRequest", cardSceurityVerifyParam);
		//log.info("cardSceurityVerify request：" + Tools.gsonToString(cardSceurityVerifyParam));
		log.info("cardSceurityVerify request：" + cardSceurityVerifyParam.toString());
		
		return "checkstand-pay/cardSceurityVerify";
		
	}
	
	
	@RequestMapping(value = {"/cardSceurityVerifyResultPage"}, produces = "text/json;charset=UTF-8")
	public String cardSceurityVerifyResultPage(CardSceurityVerifyResponse cardSceurityVerifyResponse, Model model){
		
		try {
			String cardPassCheckMsg = new String(cardSceurityVerifyResponse.getCardPassCheckMsg()
					.getBytes("ISO-8859-1"), "UTF-8");
			String bankName = new String(cardSceurityVerifyResponse.getBankName()
					.getBytes("ISO-8859-1"), "UTF-8");
			cardSceurityVerifyResponse.setBankName(bankName);
			cardSceurityVerifyResponse.setCardPassCheckMsg(cardPassCheckMsg);
		} catch (UnsupportedEncodingException e) {
			log.info("转码错误", e);
		}
		log.info("cardSceurityVerifyResultPage-->" + cardSceurityVerifyResponse.toString());
		ConfirmRechargeRequest confirmRechargeRequest = new ConfirmRechargeRequest();
		confirmRechargeRequest.setBusiCode("109");
		confirmRechargeRequest.setSignType("1");
		confirmRechargeRequest.setVersion("v1.1");
		confirmRechargeRequest.setMerchantNo("M000016");
		confirmRechargeRequest.setOrderNo(cardSceurityVerifyResponse.getOrderNo());
		confirmRechargeRequest.setPayType("41");
		model.addAttribute("confirmRechargeRequest", confirmRechargeRequest);
		
		return "checkstand-pay/verify_confirmRecharge";
		
	}
	
	
	@RequestMapping(value = {"/cardSceurityVerifyResultNotice"})
	public void cardSceurityVerifyResultNotice(CardSceurityVerifyResponse cardSceurityVerifyResponse, Model model){
		
		log.info("cardSceurityVerifyResultNotice-->" + JsonUtil.objectToJson(cardSceurityVerifyResponse));
		
	}

}
