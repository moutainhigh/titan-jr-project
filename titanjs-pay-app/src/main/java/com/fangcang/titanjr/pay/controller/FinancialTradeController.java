package com.fangcang.titanjr.pay.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.CashDeskData;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.AccountHistoryRequest;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.AccountHistoryResponse;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.services.FinancialTradeService;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.pay.util.RSADecryptString;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("/trade")
public class FinancialTradeController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(FinancialTradeController.class);

	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;

	@Resource
	private TitanCashierDeskService titanCashierDeskService;

	@Resource
	private TitanOrderService titanOrderService;

	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;

	private MerchantFacade merchantFacade;

	@Resource
	private HessianProxyBeanFactory hessianProxyBeanFactory;

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;

	@Resource
	private FinancialTradeService financialTradeService;

	/**
	 * @Title: titanPay
	 * @Description: 提交订单并对身份进行认证
	 * @param orderInfo
	 * @param businessInfo
	 * @param response
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/titanPay", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String titanPay(String orderInfo, String businessInfo, Model model) {

		if (!StringUtil.isValidString(orderInfo)) {
			log.error("orderInfo is not null!");
			model.addAttribute("msg",
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}

		try {

			String deInfo = RSADecryptString.decryptString(orderInfo,
					TitanConstantDefine.PRIVATE_KEY);
			if (!StringUtil.isValidString(deInfo)) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("decrypt orderInfo is ok .");

			// 解析订单信息
			TitanOrderRequest dto = JsonConversionTool.toObject(deInfo,
					TitanOrderRequest.class);

			if (dto == null) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("auth user is ok .");

			// 检查解析后的参数是否合理
			if (!checkOrderInfo(dto)) {
				log.error("orderInfo check fail!");
				model.addAttribute("msg",
						TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED
								.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("check order info is ok");
			// 检查用户权限
			if (!financialTradeService.checkPermission(dto)) {
				log.error("checkPermission is fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.PERMISSION_CHECK_FAILED.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("check permission is ok");
			// 确认业务订单信息
			if (!financialTradeService.checkConfirmBussOrder(dto)) {
				log.error("checkConfirmBussOrder is fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.BUSS_ORDER_CHANGE_CHECK_FAILED
								.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("confirm buss order is ok");

			// 解析携带的业务信息
			Map<String, String> busMap = JsonConversionTool.toObject(
					businessInfo, Map.class);
			dto.setBusinessInfo(busMap);

			log.info("begin sava titan trans order ...");
			// 保存金融订单
			TransOrderCreateResponse orderCreateResponse = titanFinancialTradeService
					.saveTitanTransOrder(dto);

			if (orderCreateResponse == null
					|| (!orderCreateResponse.isResult())
					|| !StringUtil.isValidString(orderCreateResponse
							.getOrderNo())) {
				model.addAttribute("msg",
						TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());

				if (orderCreateResponse != null) {
					log.error("orderCreateResponse "
							+ orderCreateResponse.getReturnCode() + ":"
							+ orderCreateResponse.getReturnMessage());

					TitanMsgCodeEnum codeEnum = TitanMsgCodeEnum
							.findTitanMsgCodeEnum(orderCreateResponse
									.getReturnCode());
					if (codeEnum != null) {
						model.addAttribute("msg", codeEnum.getResMsg());
					}
				}
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("end sava titan trans order... [orderNo: "
					+ orderCreateResponse.getOrderNo() + "]");

			// 获取收银台地址
			PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
			paymentUrlRequest.setPayOrderNo(orderCreateResponse.getOrderNo());
			paymentUrlRequest.setIsEscrowed("0");
			paymentUrlRequest.setPaySource(dto.getPayerType());
			PaymentUrlResponse response = titanFinancialTradeService
					.getPaymentUrl(paymentUrlRequest);

			if (response == null || !response.isResult()) {
				if (response != null) {
					log.error("orderCreateResponse "
							+ orderCreateResponse.getReturnCode() + ":"
							+ orderCreateResponse.getReturnMessage());
				}
				model.addAttribute("msg",
						TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("get Payment url ok ");

			this.getResponse().sendRedirect(
					this.getRequest().getContextPath() + response.getUrl());

			log.info("success goto url = " + this.getRequest().getContextPath()
					+ response.getUrl());

		} catch (Exception ex) {
			log.error("", ex);
		}

		model.addAttribute("msg", TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());
		return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
	}

	/**
	 * 检查订单中的信息是否合法
	 * 
	 * @param dto
	 * @return
	 */
	private boolean checkOrderInfo(TitanOrderRequest dto) {
		if (!StringUtil.isValidString(dto.getAmount())) {
			log.error("Amount is null");
			return false;
		}

		if (!StringUtil.isValidString(dto.getGoodsId())) {
			log.error("GoodsId is null");
			return false;
		}

		if (!StringUtil.isValidString(dto.getPayerType())) {
			log.error("PayerType is null");
			return false;
		}
		PayerTypeEnum pe = PayerTypeEnum.getPayerTypeEnumByKey(dto
				.getPayerType());
		if (pe == null) {
			log.error("PayerType convert is null");
			return false;
		}

		if (pe.isUserId() && !StringUtil.isValidString(dto.getUserId())) {
			log.error(pe + "userId is null");
			return false;
		}

		if (pe.isFcUserId() && !StringUtil.isValidString(dto.getUserId())) {
			log.error(pe + "userId is null");
			return false;
		}

		if (pe.isB2BPayment() && !StringUtil.isValidString(dto.getRuserId())) {
			log.error(pe + "RuserId is null");
			return false;
		}

		if (StringUtil.isValidString(dto.getEscrowedDate())) {
			try {
				DateUtil.sdf.parse(dto.getEscrowedDate());
			} catch (ParseException e) {
				log.error("parse escrowedDate fail.");
				return false;
			}
		}

		return true;
	}

	// DDDDDD
	@RequestMapping("/showCashierDesk")
	public String showCashierDesk(String payOrderNo, String sign, Model model) {

		// orderNo = "TJO1608181126362212";
		log.info("获取支付地址入参:" + JsonConversionTool.toJson(payOrderNo));

		if (!StringUtil.isValidString(payOrderNo)) {
			model.addAttribute("msg", "订单流水号不空");
			return "checkstand-pay/cashierDeskError";
		}

		// 验证签名

		// 根据payOrderNo查询出相应的订单
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setUserorderid(payOrderNo);
		TransOrderDTO transOrderDTO = titanOrderService
				.queryTransOrderDTO(transOrderRequest);

		if (null == transOrderDTO
				|| !StringUtil.isValidString(transOrderDTO.getUserid())) {// 验证付款方
			model.addAttribute("msg", "查无此单,或订单错误");
			return "checkstand-pay/cashierDeskError";
		}

		if (!StringUtil.isValidString(transOrderDTO.getPayerType())
				|| null == PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO
						.getPayerType())) {
			model.addAttribute("msg", "系统错误");
			return "checkstand-pay/cashierDeskError";
		}

		PayerTypeEnum payerTypeEnum = PayerTypeEnum
				.getPayerTypeEnumByKey(transOrderDTO.getPayerType());

		CashDeskData cashDeskData = new CashDeskData();

		// 查询收款方机构号，如果收款方不为空但是是酒店支付，还是不将收款信息填写
		if (StringUtil.isValidString(transOrderDTO.getPayeemerchant())
				&& payerTypeEnum.isMustPayeement()) {
			FinancialOrganDTO financialOrganDTO = this
					.getFinancialOrganDTO(transOrderDTO.getPayeemerchant());
			if (null == financialOrganDTO) {
				model.addAttribute("msg", "收款机构信息错误");
				return "checkstand-pay/cashierDeskError";
			}
			cashDeskData.setOrgName(financialOrganDTO.getOrgName());
			cashDeskData.setTitanCode(financialOrganDTO.getTitanCode());
		}

		// 查询账户信息，不是中间账户就需要查询

		if (!"141223100000056".equals(transOrderDTO.getUserid())) {// 不是中间账户就需要查询账户信息,
			AccountBalance accountBalance = this
					.getAccountBalance(transOrderDTO.getUserid());
			if (accountBalance == null) {
				model.addAttribute("msg", "账户信息错误");
				return "checkstand-pay/cashierDeskError";
			}
			cashDeskData.setBalanceusable(accountBalance.getBalanceusable());
			// 查询常用信息，
			List<CommonPayMethodDTO> commonPayMethodDTOList = this
					.getCommonPayMethod(transOrderDTO.getUserid(),
							PayerTypeEnum.getPaySource(transOrderDTO
									.getPayerType()));
			cashDeskData.setCommonPayMethodDTOList(commonPayMethodDTOList);
		}

		Map<String, String> bussinessInfoMap = null;
		if (StringUtil.isValidString(transOrderDTO.getBusinessinfo())) {
			bussinessInfoMap = JsonConversionTool.toObject(
					transOrderDTO.getBusinessinfo(), Map.class);
		}

		// 查询收款历史
		String inAccountCode = "";
		String outAccountCode = "";
		if (null != bussinessInfoMap) {
			inAccountCode = bussinessInfoMap.get("inAccountCode");
			outAccountCode = bussinessInfoMap.get("outAccountCode");
			cashDeskData.setFcUserid(bussinessInfoMap.get("partnerId"));
		}
		AccountHistoryResponse accountHistoryResponse = this
				.getAccountHistoryResponse(inAccountCode, outAccountCode,
						transOrderDTO.getUserid());
		if (accountHistoryResponse != null
				&& accountHistoryResponse.isResult()
				&& CollectionUtils.isNotEmpty(accountHistoryResponse
						.getAccountHistoryDTOList())) {
			Map<String, FinancialOrganDTO> userIDOrgMap = this
					.buildUserIdOrganMap(accountHistoryResponse
							.getAccountHistoryDTOList());
			cashDeskData.setAccountHistoryDTO(accountHistoryResponse
					.getAccountHistoryDTOList().get(0));
			cashDeskData.setUserIDOrgMap(userIDOrgMap);
		}

		// 设置商家主题]
		if (StringUtil.isValidString(transOrderDTO.getMerchantcode())) {
			MerchantResponseDTO merchantResponseDTO = this
					.getMerchantResponseDTO(transOrderDTO.getMerchantcode());
			if (null != merchantResponseDTO) {
				model.addAttribute("CURRENT_THEME",
						merchantResponseDTO.getTheme());
			}
		}

		CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
		cashierDeskQueryRequest.setUserId(transOrderDTO.getUserid());
		// GDP支付时用商家的收银台
		if (payerTypeEnum.isRecieveCashDesk()) {// 使用收款商家收银台
			cashierDeskQueryRequest.setUserId(transOrderDTO.getPayeemerchant());
		}
		cashierDeskQueryRequest.setUsedFor(PayerTypeEnum
				.getPaySource(transOrderDTO.getPayerType()));

		CashierDeskResponse response = titanCashierDeskService
				.queryCashierDesk(cashierDeskQueryRequest);
		if (!(response.isResult() && CollectionUtils.isNotEmpty(response
				.getCashierDeskDTOList()))) {
			cashDeskData.putError("收银台不存在");
			return "checkstand-pay/cashierDeskError";
		}
		cashDeskData.setCashierDeskDTO(response.getCashierDeskDTOList().get(0));
		cashDeskData.setPaySource(PayerTypeEnum.getPaySource(
				transOrderDTO.getPayerType()).toString());
		cashDeskData.setMerchantcode(transOrderDTO.getMerchantcode());
		cashDeskData.setUserId(transOrderDTO.getUserid());
		if (transOrderDTO.getTradeamount() != null) {
			cashDeskData.setAmount(transOrderDTO.getTradeamount().toString());
		}
		model.addAttribute("cashDeskData", cashDeskData);

		if (payerTypeEnum.isRechargeCashDesk()) {
			return "account-overview/account-recharge";
		}
		return "checkstand-pay/cashierDesk";
	}

	private AccountBalance getAccountBalance(String userId) {
		AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
		accountBalanceRequest.setUserid(userId);
		AccountBalanceResponse balanceResponse = titanFinancialAccountService
				.queryAccountBalance(accountBalanceRequest);
		if (balanceResponse.isResult()
				&& CollectionUtils.isNotEmpty(balanceResponse
						.getAccountBalance())) {
			AccountBalance accountBalance = balanceResponse.getAccountBalance()
					.get(0);
			if (accountBalance.getBalanceusable() != null) {
				accountBalance.setBalanceusable(new BigDecimal(accountBalance
						.getBalanceusable()).divide(new BigDecimal(100))
						.toString());
				return accountBalance;
			}
		}
		return null;
	}

	// 查询机构信息
	private FinancialOrganDTO getFinancialOrganDTO(String userId) {
		if (!StringUtil.isValidString(userId)) {
			return null;
		}
		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
		organQueryRequest.setUserId(userId);
		FinancialOrganResponse financialOrgan = titanFinancialOrganService
				.queryFinancialOrgan(organQueryRequest);
		if (financialOrgan.isResult()) {
			return financialOrgan.getFinancialOrganDTO();
		}
		return null;
	}

	// 查询账户信息
	private AccountHistoryResponse getAccountHistoryResponse(
			String inAccountCode, String outAccountCode, String userId) {
		if (StringUtil.isValidString(inAccountCode)
				|| StringUtil.isValidString(outAccountCode)
				|| StringUtil.isValidString(userId)) {
			return null;
		}
		AccountHistoryRequest accHistoryRequest = new AccountHistoryRequest();
		accHistoryRequest.setAccountHistoryDTO(new AccountHistoryDTO());
		accHistoryRequest.getAccountHistoryDTO()
				.setInaccountcode(inAccountCode);
		accHistoryRequest.getAccountHistoryDTO().setOutaccountcode(
				outAccountCode);
		accHistoryRequest.getAccountHistoryDTO().setPayeruserid(userId);
		AccountHistoryResponse accountHistoryResponse = titanFinancialAccountService
				.queryAccountHistory(accHistoryRequest);
		if (accountHistoryResponse.isResult()
				&& CollectionUtils.isNotEmpty(accountHistoryResponse
						.getAccountHistoryDTOList())) {
			return accountHistoryResponse;
		}
		return null;
	}

	// 获取常用的支付方式
	private List<CommonPayMethodDTO> getCommonPayMethod(String userId,
			Integer userFor) {
		if (!StringUtil.isValidString(userId) || userFor == null) {
			return null;
		}
		CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
		cashierDeskQueryRequest.setUsedFor(userFor);
		cashierDeskQueryRequest.setUserId(userId);
		List<CommonPayMethodDTO> commonPayMethodDTOList = titanCashierDeskService
				.queryCommonPayMethod(cashierDeskQueryRequest);
		if (commonPayMethodDTOList != null && commonPayMethodDTOList.size() > 0) {
			return commonPayMethodDTOList;
		}
		return null;
	}

	private Map<String, FinancialOrganDTO> buildUserIdOrganMap(
			List<AccountHistoryDTO> historyDTOList) {
		Map<String, FinancialOrganDTO> result = new HashMap<String, FinancialOrganDTO>();
		for (AccountHistoryDTO accountHistoryDTO : historyDTOList) {
			FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
			organQueryRequest.setUserId(accountHistoryDTO.getPayeeuserid());
			FinancialOrganResponse financialOrganResponse = titanFinancialOrganService
					.queryFinancialOrgan(organQueryRequest);
			if (financialOrganResponse.isResult()
					&& financialOrganResponse.getFinancialOrganDTO() != null) {
				result.put(accountHistoryDTO.getPayeeuserid(),
						financialOrganResponse.getFinancialOrganDTO());
			}
		}
		return result;
	}

	private MerchantResponseDTO getMerchantResponseDTO(String merchantCode) {
		MerchantDetailQueryDTO queryDTO = new MerchantDetailQueryDTO();
		queryDTO.setMerchantCode(merchantCode);
		return this.getMerchantFacade().queryMerchantDetail(queryDTO);
	}

	private MerchantFacade getMerchantFacade() {
		if (merchantFacade == null) {
			merchantFacade = hessianProxyBeanFactory.getHessianProxyBean(
					MerchantFacade.class,
					ProxyFactoryConstants.merchantServerUrl + "merchantFacade");
		}

		System.out.println(merchantFacade);
		return merchantFacade;
	}

	@ResponseBody
	@RequestMapping("genLoacalPayOrderNo")
	public Map<String, String> getRechargePayOrderNo() {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", OrderGenerateService.genLoacalPayOrderNo());
		return resultMap;
	}
}
