package com.fangcang.titanjr.pay.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.pay.util.WebConstant;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.StringUtil;

@RequestMapping("account")
public class TitanAccountController {
	
	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;
	
	@Resource
	private TitanFinancialUserService titanFinancialUserService;

	@ResponseBody
	@RequestMapping("/check_account")
	public String checkRecieveAccount(String recieveOrgName,String recieveTitanCode){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", "false");
		if(!StringUtil.isValidString(recieveOrgName)
				|| !StringUtil.isValidString(recieveTitanCode)){
			resultMap.put("msg", "账户名和泰坦码不能为空");
			return JsonConversionTool.toJson(resultMap);
		}
		
		AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
		accountCheckRequest.setOrgName(recieveOrgName);
		accountCheckRequest.setTitanCode(recieveTitanCode);
		AccountCheckResponse accountCheckResponse = titanFinancialAccountService.checkTitanCode(accountCheckRequest);
		if(accountCheckResponse.isCheckResult()){
		   return null;
//		   return toJson(putSuccess());
		}
		return null;
//		return toJson(putSysError(accountCheckResponse.getReturnMessage()));
	} 
	
	 @ResponseBody
     @RequestMapping("checkIsSetPayPassword")
     public Map<String, String> checkIsSetPayPassword(String fcUserid,String tfsUserId) {
    	boolean flag = false;
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "false");
        flag = titanFinancialUserService.checkIsSetPayPassword(fcUserid,tfsUserId);
        if (flag) {
            map.put("result", "success");
        }
        return map;
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
