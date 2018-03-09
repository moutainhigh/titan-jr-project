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
import com.fangcang.titanjr.dto.bean.gateway.CommonPayHistoryDTO;
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
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.pay.util.TerminalUtil;
import com.fangcang.titanjr.service.RSGatewayInterfaceService;
import com.fangcang.titanjr.service.TitanCashierDeskService;
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

	@Resource
	private TitanCashierDeskService titanCashierDeskService;
	
	
	/**
	 * 确认充值
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
	 * 确认充值（卡密鉴权之后，进行表单提交方式请求）
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
					log.error("网关确认充值成功，本地查询订单状态线程等待异常：", e);
				}
				index ++;
				String orderStatus = titanOrderService.confirmOrderStatus(confirmRechargeResponse.getOrderNo());
				log.info("网关确认充值成功, 开始第{}次查询本地交易单状态, orderStatus=" + orderStatus);
				
				if("success".equals(orderStatus) || "fail".equals(orderStatus)){
					
					RechargeResultConfirmRequest.setOrderNo(confirmRechargeResponse.getOrderNo());
					return titanPaymentService.payConfirmPage(RechargeResultConfirmRequest, model);
					
				}else if("delay".equals(orderStatus)){
					
					RechargeResultConfirmRequest.setOrderNo(confirmRechargeResponse.getOrderNo());
					RechargeResultConfirmRequest.setExpand(CommonConstant.ORDER_DELAY);
					return titanPaymentService.payConfirmPage(RechargeResultConfirmRequest, model);
				}
				
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
			
			//根据返回的卡信息校验是否是泰坦金融支持的卡类型
			if(QuickPayBankEnum.isExist(bankCardBINIResponse.getBankInfo(), bankCardBINIResponse.getCardType())){
				QuickPayBankEnum bankEnum = QuickPayBankEnum.getBankEnum(bankCardBINIResponse.getBankInfo()
						, bankCardBINIResponse.getCardType());
				boolean isValidAuth = QuickPayBankEnum.isValidAuth(bankEnum);
				bankCardBINIResponse.setSingleLimit(bankEnum.getSingleLimit());
				bankCardBINIResponse.setDailyLimit(bankEnum.getDailyLimit());
				bankCardBINIResponse.setValidAuth(isValidAuth);
				bankCardBINIResponse.putSuccess("");
				return bankCardBINIResponse;
			}
			bankCardBINIResponse.putError("该银行卡不支持快捷支付");
		}
		
		return bankCardBINIResponse;
	}
	
	
	@RequestMapping(value = {"/unBindBankCard"})
	@ResponseBody
	public UnbindBankCardResponse unBindBankCard(UnbindBankCardRequest unbindBankCardRequest){
		
		UnbindBankCardResponse unbindBankCardResponse = new UnbindBankCardResponse();
		unbindBankCardResponse.putError("删除失败");
		
		if(!StringUtil.isValidString(unbindBankCardRequest.getCommonPayId())){
			log.error("【历史卡删除】commonPayId is null");
			unbindBankCardResponse.putError("删除失败");
			return unbindBankCardResponse;
		}
		
		CommonPayHistoryDTO commonPayHistoryDTO = new CommonPayHistoryDTO();
		commonPayHistoryDTO.setCommonpayid(Integer.parseInt(unbindBankCardRequest.getCommonPayId()));
		commonPayHistoryDTO = titanCashierDeskService.getCommonPayHistory(commonPayHistoryDTO);
		if(commonPayHistoryDTO == null){
			log.error("【历史卡删除】查询本地绑卡信息失败");
			unbindBankCardResponse.putError("删除失败");
			return unbindBankCardResponse;
		}
		
		//有上游的绑卡ID，说明需要去上游解绑
		if (StringUtil.isValidString(commonPayHistoryDTO.getBindcardid())) {
			
			QueryQuickPayBindCardRequest queryQuickPayBindCardRequest = new QueryQuickPayBindCardRequest();
			queryQuickPayBindCardRequest.setMerchantNo("M000016");
			queryQuickPayBindCardRequest.setBusiCode(BusiCodeEnum.QUERY_BANKCARD_BIND.getKey());
			queryQuickPayBindCardRequest.setIdCode(commonPayHistoryDTO.getIdcode());
			queryQuickPayBindCardRequest.setCardType(commonPayHistoryDTO.getPayeraccounttype());
			queryQuickPayBindCardRequest.setSignType("1");
			queryQuickPayBindCardRequest.setVersion("v1.1");
			QueryQuickPayBindCardResponse queryQuickPayBindCardResponse = rsGatewayInterfaceService
					.queryQuickPayBindCardInfo(queryQuickPayBindCardRequest);
			if("0000".equals(queryQuickPayBindCardResponse.getErrCode())){
				//解绑卡
				unbindBankCardRequest.setIdCode(commonPayHistoryDTO.getIdcode());
				unbindBankCardRequest.setUserId(commonPayHistoryDTO.getIdcode());
				unbindBankCardRequest.setBindCardId(commonPayHistoryDTO.getBindcardid());
				unbindBankCardResponse = rsGatewayInterfaceService.unBindBankCard(unbindBankCardRequest);
			}
		}
		//上游删除成功再删本地
		if("0000".equals(unbindBankCardResponse.getErrCode())){
			int delCount = titanCashierDeskService.delCommonPayHistory(commonPayHistoryDTO);
			if(delCount <= 0){
				log.error("【历史卡删除】删除本地绑卡信息0条数据");
				unbindBankCardResponse.putError("删除失败");
			}
		}
		
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
	
	
	/**
	 * 卡密鉴权
	 * @author Jerry
	 * @date 2018年1月30日 下午6:39:05
	 * @param requestl
	 * @return
	 */
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
		log.info("cardSceurityVerify request：" + cardSceurityVerifyParam.toString());
		
		return "checkstand-pay/cardSceurityVerify";
		
	}
	
	
	/**
	 * 卡密鉴权前台回调
	 * @author Jerry
	 * @date 2018年1月30日 下午6:39:21
	 */
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
	
	
	/**
	 * 卡密鉴权后台通知
	 * @author Jerry
	 * @date 2018年1月30日 下午6:39:49
	 */
	@RequestMapping(value = {"/cardSceurityVerifyResultNotice"})
	public void cardSceurityVerifyResultNotice(CardSceurityVerifyResponse cardSceurityVerifyResponse, Model model){
		
		log.info("cardSceurityVerifyResultNotice-->" + JsonUtil.objectToJson(cardSceurityVerifyResponse));
		
	}

}
