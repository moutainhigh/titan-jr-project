package com.fangcang.titanjr.pay.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.bean.IdentityInfo;
import com.fangcang.titanjr.dto.bean.QuickPaymentData;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.services.QuickPaymentService;
import com.fangcang.util.StringUtil;

/**
 * 快捷支付
 * @author fangdaikang
 *
 */
@Controller      
@RequestMapping("quickPayment")
public class QuickPaymentController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(QuickPaymentController.class);
	
	@Resource
	private QuickPaymentService quickPaymentService;
	
	
	
	@RequestMapping("testOpenWindow")
	@ResponseBody
	public IdentityInfo testOpenWindow(){
		IdentityInfo identityInfo = new IdentityInfo();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return identityInfo;
	}
	

	/**
	 * 查询是否实名认证，如果已经实名认证返回银行卡
	 * @param identityInfo
	 * @return
	 */
	public String queryCertification(IdentityInfo identityInfo){
		
		return null;
	}
	
	/**
	 * 实名认证商家,如果身份信息已被实名认证则返回金融账户
	 * @param identityInfo
	 * @return
	 */
	public String userCertification(IdentityInfo identityInfo){
		
		return null;
	}
	
	
	/**
	 * 验证新绑定银行卡是否可以绑定
	 * @param bankCard
	 * @return
	 */
	public String validateBankCard(String newBankCard){
		
		return null;
	}
	
	
	/**
	 * 快捷支付
	 * @return
	 */
	@RequestMapping("quickPayement")
	public String quickPayement(QuickPaymentData quickPaymentData,Model model){
		model.addAttribute(CommonConstant.RESULT, CommonConstant.OPERATE_FAIL);
		if(null ==quickPaymentData || !StringUtil.isValidString(quickPaymentData.getOrderNo())
				|| !StringUtil.isValidString(quickPaymentData.getUserName())
				|| !StringUtil.isValidString(quickPaymentData.getBankHeadName())
				|| !StringUtil.isValidString(quickPaymentData.getAccountNumber())
				|| !StringUtil.isValidString(quickPaymentData.getIsBindCard())){
			model.addAttribute(CommonConstant.RETURN_MSG, TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
			return "checkstand-pay/genRechargePayment";
		}
		
		RechargeResponse rechargeResponse = quickPaymentService.quickPayment(quickPaymentData);
		
		model.addAttribute(CommonConstant.RETURN_MSG, rechargeResponse.getReturnMessage());
		if(rechargeResponse.isResult()){
			model.addAttribute(CommonConstant.RESULT, CommonConstant.RETURN_SUCCESS);
	    	model.addAttribute("rechargeDataDTO", rechargeResponse.getRechargeDataDTO());
		}
		
		return "checkstand-pay/genRechargePayment";
	}
	
	@RequestMapping("showQuickPayment")
	public String showQuickPayementDesk(String payOrderNo, String sign, Model model){
		
		log.info("the payOrderNo is "+payOrderNo);
		if(!StringUtil.isValidString(payOrderNo)){
			log.error("pay orderNo is null");
			model.addAttribute("msg",
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		
		
		
		return "quick-payment/quickPaymentDesk";
	}
}
