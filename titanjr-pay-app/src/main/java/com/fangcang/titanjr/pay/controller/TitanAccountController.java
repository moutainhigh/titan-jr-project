package com.fangcang.titanjr.pay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.CityInfoDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.request.AccountHistoryRequest;
import com.fangcang.titanjr.dto.request.BankInfoQueryRequest;
import com.fangcang.titanjr.dto.request.PayPasswordRequest;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.AccountHistoryResponse;
import com.fangcang.titanjr.dto.response.BankInfoResponse;
import com.fangcang.titanjr.dto.response.CityInfosResponse;
import com.fangcang.titanjr.dto.response.PayPasswordResponse;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.StringUtil;

@RequestMapping("/account")
@Controller
public class TitanAccountController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(TitanAccountController.class);

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;

	@Resource
	private TitanFinancialUserService titanFinancialUserService;

	@Resource
	private TitanPaymentService titanPaymentService;

	@Resource
	private TitanFinancialBaseInfoService titanFinancialBaseInfoService;

	@ResponseBody
	@RequestMapping("/check_account")
	public String checkRecieveAccount(String recieveOrgName,
			String recieveTitanCode) {
		if (!StringUtil.isValidString(recieveOrgName)
				|| !StringUtil.isValidString(recieveTitanCode)) {
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}

		AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
		accountCheckRequest.setOrgName(recieveOrgName);
		accountCheckRequest.setTitanCode(recieveTitanCode);
		AccountCheckResponse accountCheckResponse = titanFinancialAccountService
				.checkTitanCode(accountCheckRequest);
		if (accountCheckResponse.isCheckResult()) {
			return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
		}
		log.error("检查收款账户失败:" + accountCheckResponse.getReturnMessage());
		return toMsgJson(TitanMsgCodeEnum.CASHIER_INSTITUTIONS_NOT_EXISTS);
	}
	/**
	 * 检查是否设置了支付密码
	 * @param fcUserid 合作方用户id
	 * @param tfsUserId  金融用户id
	 * @param partnerOrgCode 合作方商家编码,ttm ,saas
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkIsSetPayPassword")
	public String checkIsSetPayPassword(String fcUserid, String tfsUserId,String partnerOrgCode) {
		boolean flag = false;
		flag = titanFinancialUserService.checkIsSetPayPassword(fcUserid,
				tfsUserId,partnerOrgCode);
		if (flag) {
			return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
		}
		return toMsgJson(TitanMsgCodeEnum.PAY_PWD_ERROR);
	}

	@ResponseBody
	@RequestMapping("setPayPassword")
	public String setPayPassword(HttpServletRequest request,
			PayPasswordRequest payPasswordRequest) {
		if (payPasswordRequest != null
				&& StringUtil
						.isValidString(payPasswordRequest.getPayPassword())) {
			// payPasswordRequest.setPayPassword(RSADecryptString.decryptString(payPasswordRequest.getPayPassword(),request));
			payPasswordRequest.setPayPassword(payPasswordRequest
					.getPayPassword());
			if (StringUtil.isValidString(payPasswordRequest.getOldPassword())) {
				// payPasswordRequest.setOldPassword(RSADecryptString.decryptString(payPasswordRequest.getOldPassword(),request));
				payPasswordRequest.setOldPassword(payPasswordRequest
						.getOldPassword());
			}
			// payPasswordRequest.setTfsuserid(this.getTfsUserId());
			log.info("设置付款密码的传入参数:"
					+ JsonConversionTool.toJson(payPasswordRequest));
			PayPasswordResponse payPasswordResponse = titanFinancialUserService
					.saveOrUpdatePayPassword(payPasswordRequest);
			if (payPasswordResponse.isSaveSuccess()) {
				return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
			}
			log.error("设置密码失败:" + payPasswordResponse.getReturnMessage());
			return toMsgJson(TitanMsgCodeEnum.SET_PAY_PWD_ERROR);
		}
		log.error("参数错误:" + JsonConversionTool.toJson(payPasswordRequest));
		return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
	}

	@RequestMapping("/error_cashier")
	public String returnErrorPage(String msg, Model model) {
		model.addAttribute("msg", msg);
		return "checkstand-pay/cashierDeskError";
	}

	@ResponseBody
	@RequestMapping("/check_payPassword")
	public String checkPayPassword(String payPassword, String fcUserid,
			String tfsUserid, String merchantCode) throws GlobalServiceException {
		String ttfsUserid = null;
		if (StringUtil.isValidString(fcUserid)) {
			TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
			titanUserBindInfoDTO.setFcuserid(Long.parseLong(fcUserid));
			titanUserBindInfoDTO.setMerchantcode(merchantCode);
			titanUserBindInfoDTO = titanFinancialUserService
					.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
			if (titanUserBindInfoDTO != null
					&& titanUserBindInfoDTO.getTfsuserid() != null) {
				ttfsUserid = titanUserBindInfoDTO.getTfsuserid().toString();
			}
		} else {
			ttfsUserid = tfsUserid;
		}

		if (!StringUtil.isValidString(payPassword)
				|| !StringUtil.isValidString(ttfsUserid)) {
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		boolean istrue = titanFinancialUserService.checkPayPassword(
				payPassword, ttfsUserid);
		if (!istrue) {
			return toMsgJson(TitanMsgCodeEnum.PAY_PWD_ERROR);
		}
		return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
	}

	/**
	 * 确认是否免密支付
	 * 
	 * @param userid
	 * @param totalAmount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("allownopwdpay")
	public String validateIsAllowAoAwdpay(String userid, String totalAmount) {
		if (StringUtil.isValidString(userid)) {
			boolean isAllowNoPwdPay = titanPaymentService.isAllowNoPwdPay(
					userid, totalAmount);
			if (isAllowNoPwdPay) {
				return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
			}
		}
		return toMsgJson(TitanMsgCodeEnum.TITAN_FAIL);
	}

	@RequestMapping("/selectAccHistory")
	public String showAccountHistory(AccountHistoryDTO accountHistoryDTO,
			Model model) {
		if (accountHistoryDTO != null) {
			AccountHistoryRequest accountHistoryRequest = new AccountHistoryRequest();
			accountHistoryRequest.setAccountHistoryDTO(accountHistoryDTO);
			AccountHistoryResponse accountHistoryResponse = titanFinancialAccountService
					.queryAccountHistory(accountHistoryRequest);
			if (accountHistoryResponse.isResult()) {
				model.addAttribute("accountHistoryResponse",
						accountHistoryResponse);
			}
		}
		return "checkstand-pay/selectAccHistory";
	}
	
	@RequestMapping("/queryAccountHistory")
	@ResponseBody
	public AccountHistoryResponse queryAccountHistory(AccountHistoryDTO accountHistoryDTO) {
		
		AccountHistoryResponse accountHistoryResponse = new AccountHistoryResponse();
		
		if (accountHistoryDTO != null) {
			
			AccountHistoryRequest accountHistoryRequest = new AccountHistoryRequest();
			accountHistoryRequest.setAccountHistoryDTO(accountHistoryDTO);
			accountHistoryResponse = titanFinancialAccountService
					.queryAccountHistory(accountHistoryRequest);
		}
		
		return accountHistoryResponse;
	}

	@RequestMapping("deleteAccountHistory")
	public String deleteAccountHistory(String payerUserid,
			String inAccountCode, String outAccountCode) {

		if (!StringUtil.isValidString(payerUserid)
				|| !StringUtil.isValidString(inAccountCode)
				|| !StringUtil.isValidString(outAccountCode)) {
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}

		AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
		accountHistoryDTO.setPayeruserid(payerUserid);
		accountHistoryDTO.setInaccountcode(inAccountCode);
		accountHistoryDTO.setOutaccountcode(outAccountCode);
		int row = titanFinancialAccountService
				.deleteAccountHistory(accountHistoryDTO);
		if (row > 0) {
			return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
		}

		return toMsgJson(TitanMsgCodeEnum.TITAN_FAIL);
	}

	@RequestMapping("showSetPayPassword")
	public String showSetPayPassword() {
		return "checkstand-pay/setPayPassword";
	}

	@RequestMapping("showPayPassword")
	public String showPayPassword() {
		return "checkstand-pay/putPayPassword";
	}

	private Map<String, CityInfoDTO> getParentCity() {
		CityInfoDTO cityInfo = new CityInfoDTO();
		cityInfo.setDataType(1);
		CityInfosResponse response = titanFinancialAccountService
				.getCityInfoList(cityInfo);

		Map<String, CityInfoDTO> citys = new HashMap<String, CityInfoDTO>();
		for (CityInfoDTO city : response.getCityInfoDTOList()) {// 将其code和name放到键值对中
			citys.put(city.getCityCode(), city);
		}

		Set<String> cityCodes = new HashSet<String>(citys.keySet());
		for (String cityCode : cityCodes) {
			cityInfo.setDataType(null);
			cityInfo.setParentCode(cityCode);
			response = titanFinancialAccountService.getCityInfoList(cityInfo);
			for (CityInfoDTO city : response.getCityInfoDTOList()) {// 将其code和name放到键值对中
				citys.put(city.getCityCode(), city);
			}
		}
		return citys;
	}

	private List<CityInfoDTO> getCity() {
		List<CityInfoDTO> citys = new ArrayList<CityInfoDTO>();

		CityInfoDTO cityInfo = new CityInfoDTO();
		cityInfo.setDataType(1);
		CityInfosResponse response = titanFinancialAccountService
				.getCityInfoList(cityInfo);

		// for(CityInfoDTO city : response.getCityInfoDTOList()
		// ){//将其code和name放到键值对中
		// citys.add(city);
		// }

		for (CityInfoDTO city : response.getCityInfoDTOList()) {
			cityInfo.setDataType(null);
			cityInfo.setParentCode(city.getCityCode());
			response = titanFinancialAccountService.getCityInfoList(cityInfo);
			for (CityInfoDTO cityinfo : response.getCityInfoDTOList()) {// 将其code和name放到键值对中
				citys.add(cityinfo);
			}
		}
		return citys;
	}

	private String getCityName(CityInfoDTO city,
			Map<String, CityInfoDTO> cityMap) {
		if (city == null || !StringUtil.isValidString(city.getCityCode())) {
			return "";
		}
		if (CommonConstant.BEIJING_CODE.equals(city.getCityCode())
				|| CommonConstant.TIANJING_CODE.equals(city.getCityCode())
				|| CommonConstant.CHONGQING_CODE.equals(city.getCityCode())
				|| CommonConstant.SHNAGHAI_CODE.equals(city.getCityCode())) {// 直辖市
			return city.getCityName();
		}
		StringBuffer cityName = new StringBuffer(city.getCityName());
		city = cityMap.get(city.getParentCode());
		if (city == null) {
			return cityName.toString();
		}
		cityName = cityName.insert(0, "-").insert(0, city.getCityName());
		city = cityMap.get(city.getParentCode());
		if (city == null) {
			return cityName.toString();
		}
		return cityName.insert(0, "-").insert(0, city.getCityName()).toString();
	}

	@ResponseBody
	@RequestMapping("getCitys")
	public String getCitys() {
		CityInfosResponse response = new CityInfosResponse();
		Map<String, CityInfoDTO> cityMap = this.getParentCity();
		List<CityInfoDTO> cityInfos = getCity();
		for (CityInfoDTO city : cityInfos) {
			city.setCityName(this.getCityName(city, cityMap));
		}
		response.setCityInfoDTOList(cityInfos);
		return JsonConversionTool.toJson(response);
	}

	@ResponseBody
	@RequestMapping("getBankInfoList")
	public String getBankInfo(BankInfoQueryRequest request) {
		BankInfoResponse bankInfoResponse = titanFinancialBaseInfoService
				.queryBankInfoList(request);
		if (bankInfoResponse.isResult()
				&& CollectionUtils.isNotEmpty(bankInfoResponse
						.getBankInfoDTOList())) {
		
			return 	JSONSerializer.toJSON(bankInfoResponse).toString();
		}
		return null;
	}

}