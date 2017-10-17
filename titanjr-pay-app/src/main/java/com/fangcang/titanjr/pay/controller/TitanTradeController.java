package com.fangcang.titanjr.pay.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.BusinessLog;
import com.fangcang.titanjr.common.enums.EscrowedEnum;
import com.fangcang.titanjr.common.enums.FreezeTypeEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TitanjrVersionEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.rsa.Base64Helper;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.CashDeskData;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;
import com.fangcang.titanjr.dto.bean.CityInfoDTO;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.TitanOpenOrgDTO;
import com.fangcang.titanjr.dto.bean.TitanVirtualOrgRelation;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.bean.gateway.CommonPayHistoryDTO;
import com.fangcang.titanjr.dto.request.AddPayLogRequest;
import com.fangcang.titanjr.dto.request.BindingVirtuaOrgBankCardRequest;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.request.CreateVirtualOrgRequest;
import com.fangcang.titanjr.dto.request.GetVirtuaOrgBindCarListRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.AccountHistoryResponse;
import com.fangcang.titanjr.dto.response.BindingVirtuaOrgBankCardResponse;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;
import com.fangcang.titanjr.dto.response.CityInfosResponse;
import com.fangcang.titanjr.dto.response.CreateVirtualOrgResponse;
import com.fangcang.titanjr.dto.response.GetVirtuaOrgBindCarListResponse;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.req.CreateVirtualOrgReq;
import com.fangcang.titanjr.pay.req.DeskReq;
import com.fangcang.titanjr.pay.services.TitanTradeService;
import com.fangcang.titanjr.pay.util.RSADecryptString;
import com.fangcang.titanjr.pay.util.TitanjrUpgradeHander;
import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanFinancialVirtualService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

import net.sf.json.JSONSerializer;

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

	@Resource
	private TitanFinancialOrganService financialOrganService;

	@Resource
	private TitanTradeService titanTradeService;

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;

	@Resource
	private TitanFinancialVirtualService titanFinancialVirtualService;

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;
	
	@Resource
	private BusinessLogService businessLogService;

//	/**
//	 * 移动支付请求入口
//	 * 
//	 * @Title: titanMobilePay
//	 * @Description: TODO
//	 * @param req
//	 * @return
//	 * @return: String
//	 */
//	public String titanMobilePay(MobilePayOrderReq req ,  HttpServletResponse response) {
//
//		TitanOrderRequest dto = new TitanOrderRequest();
//		
//		dto.setAmount(req.getAmount());
//		dto.setCurrencyType(req.getChannelType());
//		dto.setGoodsDetail(req.getCommodityDesc());
//		dto.setGoodsName(req.getCommodityName());
////		dto.setPayerType(payerType);
//		// 保存金融订单
//		TransOrderCreateResponse orderCreateResponse = titanFinancialTradeService
//				.saveTitanTransOrder(dto);
//
//		if (orderCreateResponse == null || (!orderCreateResponse.isResult())
//				|| !StringUtil.isValidString(orderCreateResponse.getOrderNo())) {
//			// model.addAttribute("msg",
//			// TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());
//
//			if (orderCreateResponse != null) {
//				log.error("orderCreateResponse "
//						+ orderCreateResponse.getReturnCode() + ":"
//						+ orderCreateResponse.getReturnMessage());
//
//				TitanMsgCodeEnum codeEnum = TitanMsgCodeEnum
//						.findTitanMsgCodeEnum(orderCreateResponse
//								.getReturnCode());
//				
////				response.set
//				// if (codeEnum != null) {
//				// model.addAttribute("msg", codeEnum.getResMsg());
//				// }
//			}
//			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
//		}
//
//		
//		log.info("end sava titan trans order... [orderNo: "
//				+ orderCreateResponse.getOrderNo() + "]");
//		return null;
//	}

	/**
	 * PC端支付入口
	 * @Title: titanPay
	 * @Description: 提交订单并对身份进行认证
	 * @param orderInfo
	 * @param businessInfo
	 * @param response
	 * @param succUrl
	 *            点击“支付成功"
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/titanPay", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String titanPay(String orderInfo, String businessInfo,
			String wrapType, String succUrl, Model model,
			HttpServletRequest request) {
		getRequest().getSession();

		if (!StringUtil.isValidString(orderInfo)) {
			log.error("orderInfo is not null!");
			model.addAttribute("msg",
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}

		try {
			// 解析携带的业务信息
			Map<String, String> busMap = JsonConversionTool.toObject(
					businessInfo, Map.class);
			String ruserId = null;
			if (busMap != null) {
				ruserId = busMap.get("ruserId");
			}

			// 解密参数，其中对外接口调用，每个商家有自己的公钥和私钥，对内的对接只有一套公钥和私钥
			String decryptOrderInfo = null;
			TitanOpenOrgDTO openOrgDTO = null;
			if (StringUtil.isValidString(ruserId)) {// ruserId标识对接方的身份，根据ruserId获取私钥
				openOrgDTO = financialTradeService.queryOpenOrg(ruserId);
				if (openOrgDTO == null) {
					model.addAttribute("msg",
							TitanMsgCodeEnum.TITAN_ACCOUNT_NOT_EXISTS
									.getResMsg());
					return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
				}

				decryptOrderInfo = RSADecryptString.decryptString(
						orderInfo,
						new BigInteger(Base64Helper.decode(openOrgDTO
								.getPrivatekey())).toString(16));

			} else {

				decryptOrderInfo = RSADecryptString.decryptString(orderInfo,
						TitanConstantDefine.PRIVATE_KEY);
			}

			if (!StringUtil.isValidString(decryptOrderInfo)) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("decrypt orderInfo is ok .");

			// 解析订单信息
			TitanOrderRequest orderRequest = JsonConversionTool.toObject(decryptOrderInfo,
					TitanOrderRequest.class);

			log.info("the titanOrderRequest of info is:"
					+ JSONSerializer.toJSON(orderRequest));

			if (orderRequest == null) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}
			if(orderRequest.getVersion() == null || "".equals(orderRequest.getVersion())){
				orderRequest.setVersion(TitanjrVersionEnum.VERSION_1.getKey());//默认老版本
			}
			if(!TitanjrVersionEnum.isExist(orderRequest.getVersion())){
				log.error("titanjrVersion error,version:"+orderRequest.getVersion());
				model.addAttribute("msg", TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("auth user is ok .");

			// 校验参数以及检查用户权限
			if(TitanjrVersionEnum.isVersion1(orderRequest.getVersion())){
				if (!checkOrderInfo(orderRequest)) {
					log.error("orderInfo check fail!");
					model.addAttribute("msg",
							TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED
									.getResMsg());
					return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
				}
				log.info("check order info is ok");
				
				if (!financialTradeService.checkPermission(orderRequest)) {
					log.error("checkPermission is fail.");
					model.addAttribute("msg",
							TitanMsgCodeEnum.PERMISSION_CHECK_FAILED.getResMsg());
					return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
				}
				log.info("check permission is ok");
			}else{
				//金融升级后不在这里检查权限，在设置收付款信息那里
				if (!TitanjrUpgradeHander.checkOrderInfo(orderRequest)) {
					log.error("orderInfo check fail!");
					model.addAttribute("msg",
							TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED
									.getResMsg());
					return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
				}
				log.info("check order info is ok");
			}
			
			// 确认业务订单信息
			if (!financialTradeService.checkConfirmBussOrder(orderRequest)) {
				log.error("checkConfirmBussOrder is fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.BUSS_ORDER_CHANGE_CHECK_FAILED
								.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("confirm buss order is ok");

			orderRequest.setBusinessInfo(busMap);

			log.info("begin sava titan trans order ...");

			// 保存金融订单
			TransOrderCreateResponse orderCreateResponse = titanFinancialTradeService
					.saveTitanTransOrder(orderRequest);

			if (orderCreateResponse == null
					|| (!orderCreateResponse.isResult())
					|| !StringUtil.isValidString(orderCreateResponse
							.getOrderNo())) {
				model.addAttribute("msg",
						TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());

				if (orderCreateResponse != null) {
					log.error("创建订单失败，错误信息： "+Tools.gsonToString(orderCreateResponse));

					TitanMsgCodeEnum codeEnum = TitanMsgCodeEnum
							.findTitanMsgCodeEnum(orderCreateResponse
									.getReturnCode());
					if (codeEnum != null) {
						model.addAttribute("msg", codeEnum.getResMsg());
					}else{
						model.addAttribute("msg", orderCreateResponse.getReturnMessage());
					}
				}
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}

			log.info("end sava titan trans order... [orderNo: "
					+ orderCreateResponse.getOrderNo() + "]");

			String url = "";
			businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.SaveTitanTransOrder, OrderKindEnum.TransOrderId, orderCreateResponse.getTransId()+""));
			if (orderRequest.getPayerType().equals(PayerTypeEnum.WITHDRAW.getKey())) {
				String fcUserId = "";
				String tfsUserId = "";
				Map<String, String> businessInfoMap = orderRequest.getBusinessInfo();
				if (businessInfoMap != null) {
					fcUserId = businessInfoMap.get("fcUserId") == null ? "" : businessInfoMap
							.get("fcUserId");
					tfsUserId = businessInfoMap.get("tfsUserId") == null ? "" : businessInfoMap
							.get("tfsUserId");
				}
				url = "/withdraw/account-withdraw.action?userId="
						+ orderRequest.getUserId() + "&tfsUserId=" + tfsUserId
						+ "&fcUserId=" + fcUserId + "&orderNo="
						+ orderCreateResponse.getOrderNo();
			} else {
				// 获取收银台地址
				PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
				paymentUrlRequest.setPayOrderNo(orderCreateResponse
						.getOrderNo());
				paymentUrlRequest.setIsEscrowed(EscrowedEnum.ESCROWED_PAYMENT
						.getKey());
				paymentUrlRequest.setPaySource(orderRequest.getPayerType());
				if(orderRequest.getPayerType().equals(PayerTypeEnum.SUPPLY_FINACIAL.key)){
					paymentUrlRequest.setFcUserid(orderRequest.getUserId());
				}
				if(TitanjrVersionEnum.isVersion2(orderRequest.getVersion())){
					paymentUrlRequest.setFcUserid(orderRequest.getUserId());//金融二期，userId只能是第三方用户ID
					paymentUrlRequest.setCanAccountBalance(orderCreateResponse.isCanAccountBalance());//是否有余额支付权限
					paymentUrlRequest.setPartnerOrgCode(orderRequest.getPartnerOrgCode());//第三方机构编码
				}
				paymentUrlRequest.setVersion(orderRequest.getVersion());
				PaymentUrlResponse response = titanFinancialUtilService
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

				url = response.getUrl();
			}
			log.info("get Payment url ok ");
			if (StringUtil.isValidString(wrapType)) {//支付和充值页面显示样式，钱包head
				Map<String, String> extParam = new HashMap<String, String>();
				extParam.put("wrapType", wrapType);
				url = Tools.appendRequestParam(url, extParam);
			}
			if (StringUtil.isValidString(succUrl)) {
				Map<String, String> extParam = new HashMap<String, String>();
				extParam.put("succUrl", URLEncoder.encode(succUrl, "UTF-8"));
				url = Tools.appendRequestParam(url, extParam);
			}
			this.getResponse().sendRedirect(
					this.getRequest().getContextPath() + url);

			log.info("success goto url = " + this.getRequest().getContextPath()
					+ url);

		} catch (Exception ex) {
			log.error("", ex);
		}

		model.addAttribute("msg", TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());
		return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
	}
	
	private StringBuffer buildParamList(PaymentUrlRequest paymentUrlRequest) {
		StringBuffer paramList = new StringBuffer();
		if (StringUtil.isValidString(paymentUrlRequest.getMerchantcode())) {
			paramList.append("?merchantcode=").append(
					paymentUrlRequest.getMerchantcode());
		} else {
			paramList.append("?merchantcode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getFcUserid())) {
			paramList.append("&fcUserid=").append(
					paymentUrlRequest.getFcUserid());
		} else {
			paramList.append("&fcUserid=");
		}
		paramList.append("&payOrderNo=").append(
				paymentUrlRequest.getPayOrderNo());
		paramList.append("&paySource=")
				.append(paymentUrlRequest.getPaySource());

		if (StringUtil.isValidString(paymentUrlRequest.getOperater())) {
			paramList.append("&operater=").append(
					paymentUrlRequest.getOperater());
		} else {
			paramList.append("&operater=");
		}
		if (StringUtil
				.isValidString(paymentUrlRequest.getRecieveMerchantCode())) {
			paramList.append("&recieveMerchantCode=").append(
					paymentUrlRequest.getRecieveMerchantCode());
		} else {
			paramList.append("&recieveMerchantCode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getBusinessOrderCode())) {
			paramList.append("&businessOrderCode=").append(
					paymentUrlRequest.getBusinessOrderCode());
		} else {
			paramList.append("&businessOrderCode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getNotifyUrl())) {
			paramList.append("&notifyUrl=").append(
					paymentUrlRequest.getNotifyUrl());
		} else {
			paramList.append("&notifyUrl=");
		}
		paramList.append("&isEscrowed=").append(
				paymentUrlRequest.getIsEscrowed());
		if (StringUtil.isValidString(paymentUrlRequest.getEscrowedDate())) {
			paramList.append("&escrowedDate=").append(
					paymentUrlRequest.getEscrowedDate());
		} else {
			paramList.append("&escrowedDate=");
		}
		paramList.append("&version=").append(paymentUrlRequest.getVersion());
		paramList.append("&canAccountBalance=").append(paymentUrlRequest.isCanAccountBalance());
		if (StringUtil.isValidString(paymentUrlRequest.getPartnerOrgCode())) {
			paramList.append("&partnerOrgCode=").append(
					paymentUrlRequest.getPartnerOrgCode());
		} else {
			paramList.append("&partnerOrgCode=");
		}
		return paramList;
	}
	/**
	 * 检查订单中的信息是否合法
	 * 
	 * @param dto
	 * @return
	 */
	private boolean checkOrderInfo(TitanOrderRequest dto) {

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
		if (pe == PayerTypeEnum.B2B_WX_PUBLIC_PAY) {
			log.error("PayerType is B2B_WX_PUBLIC_PAY");
			return false;
		}
		

		if (!pe.isRWL() && !StringUtil.isValidString(dto.getAmount())) {
			log.error("Amount is null");
			return false;
		}
		if (StringUtil.isValidString(dto.getAmount())) {
			String neg = "(^[1-9]{1}\\d{0,20}(\\.\\d{1,2})?$)";
			String neg2 = "(^[0]{1}(\\.\\d{1,2})?$)";
			if (!(Pattern.matches(neg, dto.getAmount()) || Pattern.matches(
					neg2, dto.getAmount()))) {
				log.error("传入金额格式错误");
				return false;
			}
		}

		if (!pe.isRWL() && new BigDecimal(dto.getAmount()).compareTo(BigDecimal.ZERO) <= 0 
				|| new BigDecimal(dto.getAmount()).compareTo(new BigDecimal(9999999)) > 0) {
			log.error("传入金额小等于0 或者超过7位数");
			return false;
		}

		if (StringUtil.isValidString(dto.getCurrencyType())
				&& !dto.getCurrencyType().equals(CommonConstant.CURRENT_TYPE)) {
			log.error("Currency type must be RMB ");
			return false;
		}

		/**
		 * 非充值、提现、贷款的goodsId是第三方系统的订单号，必传
		 */
		if (!pe.isRWL() && !StringUtil.isValidString(dto.getGoodsId())) {
			log.error("GoodsId is null");
			return false;
		}

		if (pe.isRWL() && !StringUtil.isValidString(dto.getUserId())) {
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

		if (pe.useReceiverCashDesk()
				&& !StringUtil.isValidString(dto.getRuserId())) {
			log.error(pe + "RuserId is null");
			return false;
		}

		if (StringUtil.isValidString(dto.getEscrowedDate())) {
			try {

				String regex = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
				boolean flg = Pattern.matches(regex, dto.getEscrowedDate());
				if (!flg) {
					log.error("parse escrowedDate fail.");
					return false;
				}
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
		
		//如果付款方不用自己的账户，则不允许使用冻结方案3
		if(!StringUtil.isValidString(dto.getPartnerOrgCode()) || !StringUtil.isValidString(
				dto.getOrgCode()) || !StringUtil.isValidString(dto.getUserId())){
			if(StringUtil.isValidString(dto.getFreezeType()) && FreezeTypeEnum.FREEZE_PAYER.getKey()
					.equals(dto.getFreezeType())){
				log.error("freezeType error");
				return false;
			}
		}
		//默认冻结方案2
		if(!StringUtil.isValidString(dto.getFreezeType())){
			dto.setFreezeType(FreezeTypeEnum.FREEZE_PAYEE.getKey());
		}

		return true;
	}

	@ResponseBody
	@RequestMapping(value = "confirmedTrade")
	public Map<String, String> confirmedTrade(String payOrderNo,
			String paySource) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", "success");
		if (StringUtil.isValidString(payOrderNo)) {// 支付单号不为空则查询订单
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setPayorderno(payOrderNo);
			TransOrderDTO transOrderDTO = titanOrderService
					.queryTransOrderDTO(transOrderRequest);
			if (transOrderDTO == null) {
				resultMap.put("msg", "支付失败");
				return resultMap;
			}

			// 将冻结失败，冻结成功，和订单成功统一认为支付成功
			if (OrderStatusEnum.ORDER_SUCCESS.getStatus().equals(
					transOrderDTO.getStatusid())
					|| OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(
							transOrderDTO.getStatusid())
					|| OrderStatusEnum.FREEZE_FAIL.getStatus().equals(
							transOrderDTO.getStatusid())) {
				resultMap.put("msg", "支付成功");

				return resultMap;
			}

			// 将充值失败，转账失败，和订单失败，统一设置为失败
			if (OrderStatusEnum.ORDER_FAIL.getStatus().equals(
					transOrderDTO.getStatusid())
					|| OrderStatusEnum.RECHARGE_FAIL.getStatus().equals(
							transOrderDTO.getStatusid())
					|| OrderStatusEnum.TRANSFER_FAIL.getStatus().equals(
							transOrderDTO.getStatusid())) {
				resultMap.put("msg", "支付失败");
				return resultMap;
			}

			// 处理中是指 充值成功，转账成功视为处理中
			if (OrderStatusEnum.RECHARGE_SUCCESS.getStatus().equals(
					transOrderDTO.getStatusid())
					|| OrderStatusEnum.TRANSFER_SUCCESS.getStatus().equals(
							transOrderDTO.getStatusid())
					|| OrderStatusEnum.ORDER_IN_PROCESS.getStatus().equals(
							transOrderDTO.getStatusid())
					|| OrderStatusEnum.RECHARGE_IN_PROCESS.getStatus().equals(
							transOrderDTO.getStatusid())) {
				resultMap.put("msg", "支付处理中");
				return resultMap;
			}

		}
		resultMap.put("msg", "系统错误");
		return resultMap;
	}

	@RequestMapping(value = "/selectBank", method = RequestMethod.GET)
	public String selectBank(String userId, Model model) {

		GetVirtuaOrgBindCarListRequest req = new GetVirtuaOrgBindCarListRequest();
		req.setOrgCode(userId);

		GetVirtuaOrgBindCarListResponse listResponse = titanFinancialVirtualService
				.getVirtuaOrgBindingBankCardList(req);

		List<TitanVirtualOrgRelation> list = listResponse.getvOrgRelationList();

		model.addAttribute("orgList", list);

		return "/checkstand-pay/select_bank";
	}

	@ResponseBody
	@RequestMapping("createVirtualOrg")
	public String createVirtualOrg(CreateVirtualOrgReq req) {

		// 创建虚拟
		CreateVirtualOrgRequest createVirtualOrgRequest = new CreateVirtualOrgRequest();
		createVirtualOrgRequest.setOrgCode(req.getUserId());
		createVirtualOrgRequest.setVirtualOrgName(req.getAccountName());

		CreateVirtualOrgResponse orgResponse = titanFinancialVirtualService
				.createVirtualOrg(createVirtualOrgRequest);

		if (orgResponse == null || !orgResponse.isResult()
				|| !StringUtil.isValidString(orgResponse.getvOrgCode())) {
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}

		BindingVirtuaOrgBankCardRequest request = new BindingVirtuaOrgBankCardRequest();
		request.setBankCard(req.getCardNum());
		request.setBankCode(req.getBankCode());
		request.setBankName(req.getBankName());
		request.setvOrgCode(orgResponse.getvOrgCode());
		request.setOrgCode(req.getUserId());

		// 中国银行才需要考虑地区编码和支行
		if (StringUtil.isValidString(req.getBankCityCode())) {
			request.setBankBranch(req.getBankBranch());
			CityInfoDTO city = new CityInfoDTO();
			city.setCityCode(req.getBankCityCode());
			CityInfosResponse response = titanFinancialAccountService
					.getCityInfoList(city);
			if (response.isResult()
					&& CollectionUtils
							.isNotEmpty(response.getCityInfoDTOList())) {
				request.setBankCity(response.getCityInfoDTOList().get(0)
						.getCityName());
			}
			request.setBankProvince(this.queryProvinceName(req
					.getBankCityCode()));
		}

		BindingVirtuaOrgBankCardResponse cardResponse = titanFinancialVirtualService
				.bindingBankCardToVirtuaOrg(request);

		if (cardResponse == null || !cardResponse.isResult()) {
			return toMsgJson(TitanMsgCodeEnum.UNEXPECTED_ERROR);
		}
		return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
	}

	/**
	 * 根据选择的地区编码查询对应的省份名称
	 * 
	 * @Title: queryProvinceName
	 * @Description: TODO
	 * @param cityCode
	 * @return
	 * @return: String
	 */
	private String queryProvinceName(String cityCode) {
		if (!StringUtil.isValidString(cityCode)) {
			return null;
		}
		CityInfoDTO cityInfo = new CityInfoDTO();
		cityInfo.setCityCode(cityCode);
		CityInfosResponse response = titanFinancialAccountService
				.getCityInfoList(cityInfo);
		if (!response.isResult() || response.getCityInfoDTOList() == null
				&& response.getCityInfoDTOList().size() > 0) {// 如果是北京市或者重庆市的话，这个地方的size为2
			return null;
		}

		cityInfo = response.getCityInfoDTOList().get(0);
		if (response.getCityInfoDTOList().size() == 2) {
			return cityInfo.getCityName();
		}

		if (StringUtil.isValidString(cityInfo.getParentCode())) {
			return queryProvinceName(cityInfo.getParentCode());
		} else {
			return cityInfo.getCityName();
		}
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
	public String showCashierDesk(DeskReq  deskReq, Model model) {
		String payOrderNo = deskReq.getPayOrderNo();
		log.info("showCashierDesk payOrderNo = " + payOrderNo);
		
		if (!StringUtil.isValidString(payOrderNo)) {
			log.error("pay orderNo is null");
			model.addAttribute("msg",
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		//校验签名sign
		String key = titanOrderService.getKeyByPayOrderNo(payOrderNo);
		StringBuffer cashierDeskURL = buildParamList(deskReq);
		cashierDeskURL.append("&key=").append(key);
		String sign = MD5.MD5Encode(cashierDeskURL.toString(), "UTF-8");
		
		if(!(sign.equals(deskReq.getSign())&&StringUtil.isValidString(deskReq.getSign()))){
			log.error("显示收银台时，签名错误。签名为："+sign+"，输入参数："+Tools.gsonToString(deskReq));
			model.addAttribute("msg",
					TitanMsgCodeEnum.SIGN_INCORRECT.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}

		// 根据payOrderNo查询出相应的订单
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setUserorderid(payOrderNo);
		TransOrderDTO transOrderDTO = titanOrderService
				.queryTransOrderDTO(transOrderRequest);
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.EnterCashierDesk, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
		
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

		//如果收款方机构编码不为空设置帐户名和泰坦码
		if (StringUtil.isValidString(transOrderDTO.getPayeemerchant())) {
			boolean isSetPayeeInfo = true;
			//老版收银台并且是财务付款给供应商就不需要设置帐户名和泰坦码
			if(TitanjrVersionEnum.isVersion1(deskReq.getVersion()) && !payerTypeEnum.isPayeeNecessary()){
				isSetPayeeInfo = false;
			}
			if(isSetPayeeInfo){
				FinancialOrganDTO financialOrganDTO = financialTradeService
						.getFinancialOrganDTO(transOrderDTO.getPayeemerchant());
				if (null == financialOrganDTO) {

					log.error("收款方不存在， 收款方编码(Payeemerchant)是："+transOrderDTO.getPayeemerchant());

					model.addAttribute("msg",
							TitanMsgCodeEnum.CASHIER_INSTITUTIONS_NOT_EXISTS
									.getResMsg());
					return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
				}
				log.info("set org info is ok.");
				cashDeskData.setOrgName(financialOrganDTO.getOrgName());
				cashDeskData.setTitanCode(financialOrganDTO.getTitanCode());
			}
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

			titanTradeService.sortBank(commonPayMethodDTOList);
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
				cashDeskData.setUserIDOrgMap(userIDOrgMap);//pay页面中未用到
				log.info("set account history is ok");
			}
		}
		String mCode = transOrderDTO.getMerchantcode();
		if (payerTypeEnum.isRWL() || payerTypeEnum.isOpenOrg()) {
			String userid = StringUtil.isValidString(transOrderDTO
					.getPayeemerchant()) ? transOrderDTO.getPayeemerchant()
					: transOrderDTO.getPayermerchant();

			OrgBindInfo orgBindInfo = new OrgBindInfo();
			orgBindInfo.setUserid(userid);
			OrgBindInfo bindInfo = financialOrganService
					.queryOrgBindInfoByUserid(orgBindInfo);
			if (bindInfo != null) {
				mCode = bindInfo.getMerchantCode();
			}

		}
		// 设置商家主题]
		log.info("the merchantcode is " + mCode);
		if (StringUtil.isValidString(mCode)) {
			MerchantResponseDTO merchantResponseDTO = financialTradeService
					.getMerchantResponseDTO(mCode);
			if (null != merchantResponseDTO) {
				log.info("the theme is" + merchantResponseDTO.getTheme());
				model.addAttribute("CURRENT_THEME",
						merchantResponseDTO.getTheme());
			}
		}

		CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
		cashierDeskQueryRequest.setUserId(transOrderDTO.getUserid());
		//非必须需要付款方的用收款方的收银台
		if (!payerTypeEnum.isNeedPayerInfo()) {
			cashierDeskQueryRequest.setUserId(transOrderDTO.getPayeemerchant());
		}
		cashierDeskQueryRequest.setUsedFor(PayerTypeEnum
				.getPaySource(transOrderDTO.getPayerType()));
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.QueryCashierDeskData, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
		
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

		CashierDeskDTO cashierDeskDTO = response.getCashierDeskDTOList().get(0);
		
		//金融升级处理
		if(TitanjrVersionEnum.isVersion2(deskReq.getVersion())){
			
			//新版收银台，需要合并个人网银（储蓄卡）和信用卡
			BaseResponse baseResponse = TitanjrUpgradeHander.resetCashierDeskItem(cashierDeskDTO);
			if(!baseResponse.isResult()){
				model.addAttribute("msg", baseResponse.getReturnMessage());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}
			
			//设置其他信息
			cashDeskData.setCanAccountBalance(deskReq.isCanAccountBalance());
			cashDeskData.setFcUserid(deskReq.getFcUserid());
			cashDeskData.setPartnerOrgCode(deskReq.getPartnerOrgCode());
			cashDeskData.setGoodName(transOrderDTO.getGoodsname());
			cashDeskData.setGoodDetail(transOrderDTO.getGoodsdetail());
			cashDeskData.setPayerTypeEnum(PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType()));
			
			//查询新的常用支付历史
			CommonPayHistoryDTO quickCardHistoryDTO = new CommonPayHistoryDTO();
			quickCardHistoryDTO.setOrgcode(transOrderDTO.getUserid());
			quickCardHistoryDTO.setFcuserid(cashDeskData.getFcUserid());
			quickCardHistoryDTO.setDeskid(String.valueOf(cashierDeskDTO.getDeskId()));
			List<CommonPayHistoryDTO> commPayList = titanCashierDeskService.queryCommonPayHistory(quickCardHistoryDTO);
			if(CollectionUtils.isNotEmpty(commPayList)){
				for (CommonPayHistoryDTO commonPayHistoryDTO : commPayList) {
					if(StringUtil.isValidString(commonPayHistoryDTO.getPayeracount()) && 
							commonPayHistoryDTO.getPayeracount().length() >= 4){
						String strsub = commonPayHistoryDTO.getPayeracount().substring(commonPayHistoryDTO.getPayeracount().length()-4);
						commonPayHistoryDTO.setSubpayeracount("****" + strsub);
					}
				}
				cashDeskData.setCommonPayHistoryList(commPayList);
			}
			
		}
		
		// 将民生银行的企业银行方到最后面
		titanTradeService.sortBank(cashierDeskDTO);
		
		cashDeskData.setCashierDeskDTO(cashierDeskDTO);
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
			BigDecimal amount = new BigDecimal(transOrderDTO.getTradeamount())
					.divide(new BigDecimal(100));
			cashDeskData.setAmount(amount.toString());
		}
		String billCode = null;
		if (bussinessInfoMap != null) {
			billCode = bussinessInfoMap.get("billCode");
		}
		// 设置当前用户是否支持贷款
		model.addAttribute("isSupportLoanApply", titanTradeService
				.isSupportLoanApply(cashDeskData.getUserId(), PayerTypeEnum
						.getPayerTypeEnumByKey(transOrderDTO.getPayerType()),
						cashDeskData.getFcUserid(), billCode));
		//
		cashDeskData.setJrVersion(deskReq.getVersion());
		cashDeskData.setSign(md5CashDeskData(cashDeskData,TitanConstantDefine.PAY_APP_CASHIER_SIGN_MD5_KEY));
		model.addAttribute("cashDeskData", cashDeskData);

		log.info("end set cash desk data ");
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.ShowCashierDesk, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
		
		if (payerTypeEnum.isRechargeCashDesk()) {
			return TitanConstantDefine.RECHARGE_MAIN_PAGE;
		}
		if(TitanjrVersionEnum.isVersion1(deskReq.getVersion())){
			return TitanConstantDefine.CASHIER_DESK_MAIN_PAGE;
		}else{
			return TitanConstantDefine.CASHIER_DESK_MAIN_PAGE_NEW;
		}
	}
	
	
	/**
	 * 加密传到收银台的敏感数据
	 * @param cashDeskData 收银台数据
	 * @param md5key
	 * @return
	 */
	private String md5CashDeskData(CashDeskData cashDeskData,String md5key){
		StringBuilder stringBuilder = new StringBuilder("1=2");
		if(StringUtil.isValidString(cashDeskData.getUserId())){
			stringBuilder.append("&").append("userId=").append(cashDeskData.getUserId());
		}
		if(StringUtil.isValidString(cashDeskData.getPayOrderNo())){
			stringBuilder.append("&").append("payOrderNo=").append(cashDeskData.getPayOrderNo());
		}
 
		if(StringUtil.isValidString(cashDeskData.getAmount())){
			stringBuilder.append("&").append("amount=").append(cashDeskData.getAmount());
		}
 
		if(StringUtil.isValidString(cashDeskData.getFcUserid())){
			stringBuilder.append("&").append("fcUserid=").append(cashDeskData.getFcUserid());
		}
 
		if(StringUtil.isValidString(cashDeskData.getOperator())){
			stringBuilder.append("&").append("operator=").append(cashDeskData.getOperator());
		}
		if(StringUtil.isValidString(cashDeskData.getPaySource())){
			stringBuilder.append("&").append("paySource=").append(cashDeskData.getPaySource());
		}
 
		if(cashDeskData.getDeskId()!=null&&cashDeskData.getDeskId()>0){
			stringBuilder.append("&").append("deskId=").append(cashDeskData.getDeskId());
		}
		stringBuilder.append("&").append("key=").append(md5key);
		log.info("showCashierDesk,md5原明文"+stringBuilder.toString());
		return MD5.MD5Encode(stringBuilder.toString());
	} 
	
	@ResponseBody
	@RequestMapping("genLoacalPayOrderNo")
	public Map<String, String> getRechargePayOrderNo() {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", OrderGenerateService.genLoacalPayOrderNo());
		return resultMap;
	}
}
