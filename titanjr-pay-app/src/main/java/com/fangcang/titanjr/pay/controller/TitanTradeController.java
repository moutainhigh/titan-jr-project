package com.fangcang.titanjr.pay.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
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

import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.CashDeskData;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.AccountHistoryResponse;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.services.TitanTradeService;
import com.fangcang.titanjr.pay.util.RSADecryptString;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("/trade")
public class TitanTradeController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(TitanTradeController.class);

	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;

	@Resource
	private TitanCashierDeskService titanCashierDeskService;

	@Resource
	private TitanOrderService titanOrderService;

	@Resource
	private TitanTradeService financialTradeService;

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
		getRequest().getSession();
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
		if (StringUtil.isValidString(dto.getNotify())) {
			try {
				dto.setNotify(URLDecoder.decode(dto.getNotify(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.error("notify url URLDecoder fail.");
			}
		}

		if (StringUtil.isValidString(dto.getCheckOrderUrl())) {
			try {
				dto.setCheckOrderUrl(URLDecoder.decode(dto.getCheckOrderUrl(),
						"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.error("CheckOrderUrl URLDecoder fail.");
			}
		}

		return true;
	}

	/**
	 * 验证成功后进入收银台地址
	 * 
	 * @param payOrderNo
	 *            付款单号
	 * @param sign
	 *            用户签名
	 * @param model
	 * @return
	 */
	@RequestMapping("/showCashierDesk")
	public String showCashierDesk(String payOrderNo, String sign, Model model) {

		log.info("showCashierDesk payOrderNo = " + payOrderNo);

		if (!StringUtil.isValidString(payOrderNo)) {
			log.error("pay orderNo is null");
			model.addAttribute("msg",
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}

		// 验证签名

		// 根据payOrderNo查询出相应的订单
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setUserorderid(payOrderNo);
		TransOrderDTO transOrderDTO = titanOrderService
				.queryTransOrderDTO(transOrderRequest);

		if (null == transOrderDTO
				|| !StringUtil.isValidString(transOrderDTO.getUserid())) {// 验证付款方

			log.error("trans order info is null");
			model.addAttribute("msg",
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());

			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}

		PayerTypeEnum payerTypeEnum = PayerTypeEnum
				.getPayerTypeEnumByKey(transOrderDTO.getPayerType());

		if (!StringUtil.isValidString(transOrderDTO.getPayerType())
				|| null == payerTypeEnum) {

			log.error("payerType is unknown");

			model.addAttribute("msg",
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());

			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}

		CashDeskData cashDeskData = new CashDeskData();

		// 查询收款方机构号，如果收款方不为空但是是酒店支付，还是不将收款信息填写
		if (StringUtil.isValidString(transOrderDTO.getPayeemerchant())
				&& payerTypeEnum.isMustPayeement()) {
			FinancialOrganDTO financialOrganDTO = financialTradeService
					.getFinancialOrganDTO(transOrderDTO.getPayeemerchant());
			if (null == financialOrganDTO) {

				log.error("financialOrganDTO is null!");

				model.addAttribute("msg",
						TitanMsgCodeEnum.CASHIER_INSTITUTIONS_NOT_EXISTS
								.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}
			log.info("set org info is ok.");
			cashDeskData.setOrgName(financialOrganDTO.getOrgName());
			cashDeskData.setTitanCode(financialOrganDTO.getTitanCode());
		}

		// 查询账户信息，不是中间账户就需要查询
		if (!TitanConstantDefine.EXTERNAL_PAYMENT_ACCOUNT.equals(transOrderDTO
				.getUserid())) {// 不是中间账户就需要查询账户信息,
			AccountBalance accountBalance = financialTradeService
					.getAccountBalance(transOrderDTO.getUserid());
			if (accountBalance == null) {

				log.error("accountBalance is null.");

				model.addAttribute("msg",
						TitanMsgCodeEnum.TITAN_ACCOUNT_NOT_EXISTS.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			cashDeskData.setBalanceusable(accountBalance.getBalanceusable());
			log.info("set balanceusable info is ok.");
			// 查询常用信息，
			List<CommonPayMethodDTO> commonPayMethodDTOList = financialTradeService
					.getCommonPayMethod(transOrderDTO.getUserid(),
							PayerTypeEnum.getPaySource(transOrderDTO
									.getPayerType()));

			cashDeskData.setCommonPayMethodDTOList(commonPayMethodDTOList);
			log.info("set pay method info is ok.");
		}

		// 解析业务信息并获取财务的进出账户
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
			log.info("set fcuserid is ok.");
		}

		log.info("parse bussInfo = " + transOrderDTO.getBusinessinfo());

		if (StringUtil.isValidString(inAccountCode)
				&& StringUtil.isValidString(outAccountCode)) {
			AccountHistoryResponse accountHistoryResponse = financialTradeService
					.getAccountHistoryResponse(inAccountCode, outAccountCode,
							transOrderDTO.getUserid());
			if (accountHistoryResponse != null
					&& accountHistoryResponse.isResult()
					&& CollectionUtils.isNotEmpty(accountHistoryResponse
							.getAccountHistoryDTOList())) {
				Map<String, FinancialOrganDTO> userIDOrgMap = financialTradeService
						.buildUserIdOrganMap(accountHistoryResponse
								.getAccountHistoryDTOList());

				cashDeskData.setAccountHistoryDTO(accountHistoryResponse
						.getAccountHistoryDTOList().get(0));
				cashDeskData.setUserIDOrgMap(userIDOrgMap);
				log.info("set account history is ok");
			}

		}

		// 设置商家主题]
		log.info("the merchantcode is"+transOrderDTO.getMerchantcode());
		if (StringUtil.isValidString(transOrderDTO.getMerchantcode())) {
			MerchantResponseDTO merchantResponseDTO = financialTradeService
					.getMerchantResponseDTO(transOrderDTO.getMerchantcode());
			if (null != merchantResponseDTO) {
				log.info("the theme is"+merchantResponseDTO.getTheme());
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

			log.error("cashier desk is null!");

			model.addAttribute("msg",
					TitanMsgCodeEnum.CASHIER_DESK_NOT_EXISTS.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}

		log.info("begin set cash desk data ");
		cashDeskData.setCashierDeskDTO(response.getCashierDeskDTOList().get(0));
		cashDeskData.setPaySource(PayerTypeEnum.getPaySource(
				transOrderDTO.getPayerType()).toString());
		cashDeskData.setMerchantcode(transOrderDTO.getMerchantcode());
		cashDeskData.setUserId(transOrderDTO.getUserid());
		cashDeskData.setPayOrderNo(transOrderDTO.getPayorderno());

		if (response.getCashierDeskDTOList().get(0) != null) {
			cashDeskData.setDeskId(response.getCashierDeskDTOList().get(0)
					.getDeskId());
		}
		if (transOrderDTO.getTradeamount() != null) {
			BigDecimal amount = new BigDecimal(transOrderDTO.getTradeamount()).divide(new BigDecimal(100));
			cashDeskData.setAmount(amount.toString());
		}

		model.addAttribute("cashDeskData", cashDeskData);

		log.info("end set cash desk data ");

		if (payerTypeEnum.isRechargeCashDesk()) {
			return TitanConstantDefine.RECHARGE_MAIN_PAGE;
		}
		return TitanConstantDefine.CASHIER_DESK_MAIN_PAGE;
	}

	@ResponseBody
	@RequestMapping("genLoacalPayOrderNo")
	public Map<String, String> getRechargePayOrderNo() {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", OrderGenerateService.genLoacalPayOrderNo());
		return resultMap;
	}
}
