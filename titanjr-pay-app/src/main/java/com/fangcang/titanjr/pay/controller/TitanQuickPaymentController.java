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
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.gateway.QuickPayCardDTO;
import com.fangcang.titanjr.dto.request.gateway.CardSceurityVerifyRequest;
import com.fangcang.titanjr.dto.request.gateway.ConfirmRechargeRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryBankCardBINRequest;
import com.fangcang.titanjr.dto.request.gateway.QueryQuickPayBindCardRequest;
import com.fangcang.titanjr.dto.request.gateway.ReSendVerifyCodeRequest;
import com.fangcang.titanjr.dto.request.gateway.UnbindBankCardRequest;
import com.fangcang.titanjr.dto.request.gateway.UpdateBankCardPhoneResponseRequest;
import com.fangcang.titanjr.dto.response.gateway.CardSceurityVerifyResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryBankCardBINIResponse;
import com.fangcang.titanjr.dto.response.gateway.QueryQuickPayBindCardResponse;
import com.fangcang.titanjr.dto.response.gateway.ReSendVerifyCodeResponse;
import com.fangcang.titanjr.dto.response.gateway.UnbindBankCardResponse;
import com.fangcang.titanjr.dto.response.gateway.UpdateBankCardPhoneResponse;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.VersionEnum;
import com.fangcang.titanjr.pay.util.TerminalUtil;
import com.fangcang.titanjr.service.RSGatewayInterfaceService;
import com.fangcang.util.JsonUtil;

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
	
	
	/**
	 * 确认充值
	 * @param confirmRechargeRequest
	 * @return
	 */
	@RequestMapping(value = {"/confirmRecharge"}, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String confirmRecharge(ConfirmRechargeRequest confirmRechargeRequest){
		
		String result = rsGatewayInterfaceService.confirmRecharge(confirmRechargeRequest);
		
		return result;
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
	public String reSendVerifyCode(ReSendVerifyCodeRequest reSendVerifyCodeRequest){
		
		ReSendVerifyCodeResponse reSendVerifyCodeResponse = rsGatewayInterfaceService.reSendVerifyCode(reSendVerifyCodeRequest);
		
		return String.valueOf(reSendVerifyCodeResponse.isSuccess());
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
				String bankInfo = QuickPayBankEnum.getBankInfo(bankCardBINIResponse.getBankCode()
						, bankCardBINIResponse.getCardType());
				bankCardBINIResponse.setBankInfo(bankInfo);
				
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
		cardSceurityVerifyRequest.setBusiCode(BusiCodeEnum.CARE_SCEURITY_VERIFY.getKey());
		cardSceurityVerifyRequest.setCardChecknotifyUrl("http://www.fangcang.org/titanjr-pay-dev3/quickPay/cardSceurityVerifyResultNotice.action");
		cardSceurityVerifyRequest.setCardCheckPageUrl("http://www.fangcang.org/titanjr-pay-dev3/quickPay/cardSceurityVerifyResultPage.action");
		cardSceurityVerifyRequest.setCardNo("6214837833012036");
		cardSceurityVerifyRequest.setMerchantNo("M000016");
		cardSceurityVerifyRequest.setOrderNo(cardSceurityVerifyRequest.getOrderNo());
		cardSceurityVerifyRequest.setPayType("41");
		cardSceurityVerifyRequest.setSignType("1");
		cardSceurityVerifyRequest.setVersion(VersionEnum.Version_2.key);
		
		CardSceurityVerifyResponse cardSceurityVerifyResponse = rsGatewayInterfaceService.cardSceurityVerify(cardSceurityVerifyRequest);
		cardSceurityVerifyRequest.setGateWayURL(cardSceurityVerifyResponse.getErrCode());
		cardSceurityVerifyRequest.setSignMsg(cardSceurityVerifyResponse.getSignMsg());
		
		model.addAttribute("cardSceurityVerifyRequest", cardSceurityVerifyRequest);
		log.info("cardSceurityVerify request："+Tools.gsonToString(cardSceurityVerifyRequest));
		
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
