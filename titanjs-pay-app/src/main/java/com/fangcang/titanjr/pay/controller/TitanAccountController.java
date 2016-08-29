package com.fangcang.titanjr.pay.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.request.PayPasswordRequest;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.PayPasswordResponse;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.StringUtil;

@RequestMapping("/account")
@Controller
public class TitanAccountController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(TitanAccountController.class);

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;
	
	@Resource
	private TitanFinancialUserService titanFinancialUserService;

	@ResponseBody
	@RequestMapping("/check_account")
	public String checkRecieveAccount(String recieveOrgName,String recieveTitanCode){
		if(!StringUtil.isValidString(recieveOrgName)
				|| !StringUtil.isValidString(recieveTitanCode)){
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		
		AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
		accountCheckRequest.setOrgName(recieveOrgName);
		accountCheckRequest.setTitanCode(recieveTitanCode);
		AccountCheckResponse accountCheckResponse = titanFinancialAccountService.checkTitanCode(accountCheckRequest);
		if(accountCheckResponse.isCheckResult()){
			return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
		}
		log.error("检查收款账户失败:"+accountCheckResponse.getReturnMessage());
		return toMsgJson(TitanMsgCodeEnum.CASHIER_INSTITUTIONS_NOT_EXISTS);
	} 
	
	 @ResponseBody
     @RequestMapping("checkIsSetPayPassword")
     public String checkIsSetPayPassword(String fcUserid,String tfsUserId) {
    	boolean flag = false;
        flag = titanFinancialUserService.checkIsSetPayPassword(fcUserid,tfsUserId);
        if (flag) {
        	return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
        }
        return toMsgJson(TitanMsgCodeEnum.PAY_PWD_ERROR);
     }
	 
	 @ResponseBody
     @RequestMapping("setPayPassword")
     public String setPayPassword(HttpServletRequest request, PayPasswordRequest payPasswordRequest) {
        Map<String, String> map = new HashMap<String, String>();
        if (payPasswordRequest != null && StringUtil.isValidString(payPasswordRequest.getPayPassword())) {
//	        	payPasswordRequest.setPayPassword(RSADecryptString.decryptString(payPasswordRequest.getPayPassword(),request));
        	payPasswordRequest.setPayPassword(payPasswordRequest.getPayPassword());
        	if(StringUtil.isValidString(payPasswordRequest.getOldPassword())){
//	        		payPasswordRequest.setOldPassword(RSADecryptString.decryptString(payPasswordRequest.getOldPassword(),request));
        		payPasswordRequest.setOldPassword(payPasswordRequest.getOldPassword());
        	}
        	//payPasswordRequest.setTfsuserid(this.getTfsUserId());
        	log.info("设置付款密码的传入参数:"+JsonConversionTool.toJson(payPasswordRequest));
            PayPasswordResponse payPasswordResponse = titanFinancialUserService.saveOrUpdatePayPassword(payPasswordRequest);
            if (payPasswordResponse.isSaveSuccess()) {
            	return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
            } 
            log.error("设置密码失败:"+payPasswordResponse.getReturnMessage());
            return toMsgJson(TitanMsgCodeEnum.SET_PAY_PWD_ERROR);
        }
        log.error("参数错误:"+JsonConversionTool.toJson(payPasswordRequest));
        return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
     }
	 
	 @RequestMapping("/error_cashier")
	 public String returnErrorPage(String msg,Model model){
		model.addAttribute("msg", msg);
		return "checkstand-pay/cashierDeskError";
	 }
		
	 
	 @RequestMapping("showSetPayPassword")
	 public String showSetPayPassword() {
		 return "checkstand-pay/setPayPassword";
	 }
	 
	 @RequestMapping("showPayPassword")
	 public String showPayPassword() {
		 return "checkstand-pay/putPayPassword";
	 }

}
