package com.titanjr.fop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.titanjr.fop.request.WheatfieldAccountUpdateRequest;
import com.titanjr.fop.request.WheatfieldAccountinfoDeleteRequest;
import com.titanjr.fop.request.WheatfieldBankaccountBindingRequest;
import com.titanjr.fop.response.WheatfieldAccountUpdateResponse;
import com.titanjr.fop.response.WheatfieldAccountinfoDeleteResponse;
import com.titanjr.fop.response.WheatfieldBankaccountBindingResponse;
import com.titanjr.fop.util.BeanUtils;
import com.titanjr.fop.util.ResponseUtils;

/***
 * 银行卡
 * 
 * @author luoqinglong
 * @date 2018年3月2日
 */
@Controller
@RequestMapping(value = "/bankcard")
public class BankCardController extends BaseController {

	/***
	 * 绑卡
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/bindbankCard", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String bingbankCard(HttpServletRequest request, RedirectAttributes attr) {

		WheatfieldBankaccountBindingResponse bankBindingResponse = new WheatfieldBankaccountBindingResponse();
		String validResult = ResponseUtils.validRequestSign(request, bankBindingResponse);
		if (null != validResult) {
			return validResult;
		}
		bankBindingResponse.setIs_success("true");

		WheatfieldBankaccountBindingRequest bankAccountBindRequest = BeanUtils
				.switch2RequestDTO(WheatfieldBankaccountBindingRequest.class, request);
		if (null == bankAccountBindRequest) {
			return ResponseUtils.getConvertErrorResp(bankBindingResponse);
		}

		return toJson(bankBindingResponse);
	}
	/**
	 * 删除个人绑卡
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/deletePersonCard", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String deletePersonCard(HttpServletRequest request, RedirectAttributes attr) {
		WheatfieldAccountinfoDeleteResponse accountinfoDeleteResponse = new WheatfieldAccountinfoDeleteResponse(); 
		
		
		String validResult = ResponseUtils.validRequestSign(request, accountinfoDeleteResponse);
		if (null != validResult) {
			return validResult;
		}
		accountinfoDeleteResponse.setIs_success("true");

		WheatfieldAccountinfoDeleteRequest bankAccountdeleteRequest = BeanUtils
				.switch2RequestDTO(WheatfieldAccountinfoDeleteRequest.class, request);
		if (null == bankAccountdeleteRequest) {
			return ResponseUtils.getConvertErrorResp(accountinfoDeleteResponse);
		}

		return toJson(accountinfoDeleteResponse);
	}
	/**
	 * 修改绑卡
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/accountUpdate", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String accountUpdate(HttpServletRequest request, RedirectAttributes attr){
		
		WheatfieldAccountUpdateResponse accountUpdateResponse = new WheatfieldAccountUpdateResponse(); 
		String validResult = ResponseUtils.validRequestSign(request, accountUpdateResponse);
		if (null != validResult) {
			return validResult;
		}
		accountUpdateResponse.setIs_success("true");

		WheatfieldAccountUpdateRequest accountUpdateRequest = BeanUtils
				.switch2RequestDTO(WheatfieldAccountUpdateRequest.class, request);
		if (null == accountUpdateRequest) {
			return ResponseUtils.getConvertErrorResp(accountUpdateResponse);
		}

		return toJson(accountUpdateResponse);
		
	}
	
	
}
