package com.fangcang.titanjr.pay.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.CashierItemTypeEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TitanjrVersionEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.dto.PaySourceEnum;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfoDTO;
import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.enums.PayTypeEnum;
import com.fangcang.titanjr.enums.RsVersionEnum;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.req.CreateTitanRateRecordReq;
import com.fangcang.titanjr.pay.req.MobilePayOrderReq;
import com.fangcang.titanjr.pay.req.TitanRateComputeReq;
import com.fangcang.titanjr.pay.req.UrlParam;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.pay.services.TitanRateService;
import com.fangcang.titanjr.pay.services.TitanTradeService;
import com.fangcang.titanjr.pay.util.HttpUtils;
import com.fangcang.titanjr.pay.util.WeChatUtils;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.util.StringUtil;

/**
 * 接受微信发送请求的Controller
 * 
 * @author WENGXITAO
 */
@Controller
@RequestMapping("/")
public class WeChatController {
	private static final Log log = LogFactory.getLog(WeChatController.class);

	private String token = "titanjr123456789";
	private String appId = "wx11e7782762237311";
	private String secret = "48e9b1649839dff8d2e4897bb611b3f8";

	private static Map<String, Object> mapLock = new ConcurrentHashMap<String, Object>();
	//开发测试的上下文路径，如果修复了，建议为空
	private final static String context_path=null;
	
	@Resource
	private TitanRateService titanRateService;

	@Resource
	private TitanPaymentService titanPaymentService;

	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;
	
	@Resource
	private TitanFinancialOrganService orgService;

	@Resource
	private TitanTradeService financialTradeService;

	@Resource
	private TitanCashierDeskService titanCashierDeskService;
	
	@Resource
	private TitanFinancialUtilService utilservice;

	/**
	 * 移动支付入口
	 * 
	 * @Title: titanPay
	 * @Description: TODO
	 * @param req
	 *            落单对象
	 * @param request
	 * @param response
	 * @return: void
	 */
	@RequestMapping(value = "/titanPay")
	public void titanPay(MobilePayOrderReq req, HttpServletRequest request,
			HttpServletResponse response) {

		log.info("Wx titan pay request Json = "
				+ JsonConversionTool.toJson(req));
		
		if (!checkOrderInfo(req)) {
			jumpFailUrl(req.getFailJumpUrl(), response, request,
					TitanMsgCodeEnum.UNEXPECTED_ERROR);
			return;
		}
		//签名校验
		Map<String, String> paramMap = null;
		try {
			paramMap = BeanUtils.describe(req);
			paramMap.remove("sign");//排除签名串本身
			paramMap.remove("class");//排除签名串本身
		} catch (NoSuchMethodException e) {
			log.error("微信公众号支付请求,请求参数转map失败NoSuchMethodException：  "+ JsonConversionTool.toJson(req),e);
			jumpFailUrl(req.getFailJumpUrl(), response, request,
					TitanMsgCodeEnum.UNEXPECTED_ERROR);
			return;
		} catch (IllegalAccessException e) {
			log.error("微信公众号支付请求,请求参数转map失败IllegalAccessException：  "+ JsonConversionTool.toJson(req),e);
			jumpFailUrl(req.getFailJumpUrl(), response, request,
					TitanMsgCodeEnum.UNEXPECTED_ERROR);
			return;
		} catch (InvocationTargetException e) {
			log.error("微信公众号支付请求,请求参数转map失败InvocationTargetException：  "+ JsonConversionTool.toJson(req),e);
			jumpFailUrl(req.getFailJumpUrl(), response, request,
					TitanMsgCodeEnum.UNEXPECTED_ERROR);
			return;
		}
		
		String sign = MD5.MD5Encode(MD5.generatorSignParam(paramMap, TitanConstantDefine.PAY_APP_CASHIER_SIGN_MD5_KEY),"UTF-8").toUpperCase();
		if(!sign.toUpperCase().equals(req.getSign())){
			log.error("微信公众号支付请求签名错误signerror,请求参数：  "+ JsonConversionTool.toJson(req)+",金融签名sign为："+sign);
			jumpFailUrl(req.getFailJumpUrl(), response, request,
					TitanMsgCodeEnum.SIGN_INCORRECT);
			return;
		}
		// 查询收款人对于的机构信息
		if(StringUtil.isValidString(req.getPayType())&&req.getPayType().toUpperCase().startsWith("S")){//SAAS商家编码
			OrgBindInfoDTO orgDto = new OrgBindInfoDTO();
			orgDto.setMerchantCode(req.getPayeeOrg());
			List<OrgBindInfoDTO> list = orgService.queryOrgBindInfoDTO(orgDto);
			if(CollectionUtils.isEmpty(list)){
				log.error("saas PayeeOrg financialOrganDTO is null,PayeeOrg:"+req.getPayeeOrg());
				jumpFailUrl(req.getFailJumpUrl(), response, request,
						TitanMsgCodeEnum.CASHIER_INSTITUTIONS_NOT_EXISTS);
				return;
			}else{
				orgDto = list.get(0);
				req.setPayeeOrg(orgDto.getUserid());
			}
		}
		
		// 构建本地落单请求对象
		TitanOrderRequest dto = convertToTitanOrderReq(req);
		
		// 设置业务信息
		if (StringUtil.isValidString(req.getBussInfo())) {
			Map<String, String> map = JsonConversionTool.toObject(
					req.getBussInfo(), Map.class);
			dto.setBusinessInfo(map);
		}

		try {

			log.info("Create local trans order request Json="
					+ JsonConversionTool.toJson(dto));

			// 落本地单
			TransOrderCreateResponse orderCreateResponse = titanFinancialTradeService
					.saveTitanTransOrder(dto);

			if (orderCreateResponse == null
					|| (!orderCreateResponse.isResult())
					|| !StringUtil.isValidString(orderCreateResponse
							.getOrderNo())) {

				if (orderCreateResponse != null) {
					log.error("orderCreateResponse "
							+ orderCreateResponse.getReturnCode() + ":"
							+ orderCreateResponse.getReturnMessage());

					TitanMsgCodeEnum codeEnum = TitanMsgCodeEnum
							.findTitanMsgCodeEnum(orderCreateResponse
									.getReturnCode());

					if (codeEnum != null) {

						jumpFailUrl(req.getFailJumpUrl(), response, request,
								TitanMsgCodeEnum.UNEXPECTED_ERROR);
						return;
					}
				}

				jumpFailUrl(req.getFailJumpUrl(), response, request,
						TitanMsgCodeEnum.UNEXPECTED_ERROR);
				return;
			}

			log.info("Create local order success . [orderNo: "
					+ orderCreateResponse.getOrderNo() + "]");

			// 构建融数落单的请求对象
			TitanPaymentRequest titanPaymentRequest = new TitanPaymentRequest();
			titanPaymentRequest.setLinePayType(PayTypeEnum.WECHAT_PAY
					.getLinePayType());
			titanPaymentRequest.setPayAmount(req.getAmount());
			titanPaymentRequest.setPayerAcount("");
			titanPaymentRequest.setBankInfo("");
			titanPaymentRequest.setPaySource(PaySourceEnum.TT_MALL_MOBILE
					.getDeskCode());
			titanPaymentRequest.setPayType(PayTypeEnum.WECHAT_PAY);
			titanPaymentRequest
					.setUserid(TitanConstantDefine.EXTERNAL_PAYMENT_ACCOUNT);
			titanPaymentRequest.setTradeAmount(req.getAmount());
			titanPaymentRequest.setJrVersion(TitanjrVersionEnum.VERSION_1.getKey());
			titanPaymentRequest.setRsVersion(RsVersionEnum.Version_1.key);

			// 查询收款人对于的机构信息
			FinancialOrganDTO financialOrganDTO = financialTradeService
					.getFinancialOrganDTO(req.getPayeeOrg());
			if (null == financialOrganDTO) {

				log.error("PayeeOrg financialOrganDTO is null,PayeeOrg:"+req.getPayeeOrg());

				jumpFailUrl(req.getFailJumpUrl(), response, request,
						TitanMsgCodeEnum.CASHIER_INSTITUTIONS_NOT_EXISTS);
				return;

			}

			log.info("Get financial organ Info [orgName="
					+ financialOrganDTO.getOrgName() + ",titanCode="
					+ financialOrganDTO.getTitanCode() + "]");

			titanPaymentRequest.setRecieveOrgName(financialOrganDTO
					.getOrgName());
			titanPaymentRequest.setRecieveTitanCode(financialOrganDTO
					.getTitanCode());
			titanPaymentRequest.setPayOrderNo(req.getBussOrderNo());

			// 查询收款人对于的收银台信息
			CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
			cashierDeskQueryRequest.setUserId(req.getPayeeOrg());
			cashierDeskQueryRequest.setUsedFor(Integer
					.valueOf(PayTypeEnum.WECHAT_PAY.linePayType));

			CashierDeskResponse cashierResponse = titanCashierDeskService
					.queryCashierDesk(cashierDeskQueryRequest);

			if (cashierResponse == null
					|| cashierResponse.getCashierDeskDTOList() == null
					|| cashierResponse.getCashierDeskDTOList().isEmpty()) {
				log.error("cashier config  is null!");

				jumpFailUrl(req.getFailJumpUrl(), response, request,
						TitanMsgCodeEnum.CASHIER_DESK_NOT_EXISTS);
				return;
			}

			titanPaymentRequest.setDeskId(String.valueOf(cashierResponse
					.getCashierDeskDTOList().get(0).getDeskId()));

			log.info("Get financial cashier desk [deskId="
					+ titanPaymentRequest.getDeskId() + "]");

			// 开始计算并设置费率
			TitanRateComputeReq computeReq = new TitanRateComputeReq();
			computeReq.setAmount(titanPaymentRequest.getPayAmount());
			computeReq.setItemTypeEnum(CashierItemTypeEnum.WX_PUBLIC);
			computeReq.setUserId(dto.getRuserId());

			log.info("Set rate request info Json= "
					+ JsonConversionTool.toJson(computeReq));

			// 设置费率信息
			titanPaymentRequest = titanRateService.setRateAmount(computeReq,
					titanPaymentRequest);

			log.info("Create RS order Json="
					+ JsonConversionTool.toJson(titanPaymentRequest));

			// 进行融数平台落单
			TransOrderCreateResponse transOrderCreateResponse = titanFinancialTradeService
					.createRsOrder(titanPaymentRequest);
			// 查看返回结果是否成功
			if (!transOrderCreateResponse.isResult()
					|| !StringUtil.isValidString(transOrderCreateResponse
							.getOrderNo())) {
				log.error("call createTitanTransOrder fail msg["
						+ JsonConversionTool.toJson(transOrderCreateResponse)
						+ "]");

				jumpFailUrl(req.getFailJumpUrl(), response, request,
						TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
				return;
			}

			log.info("Create Rs order success [orderNo = "
					+ transOrderCreateResponse.getOrderNo() + "]");

			titanPaymentRequest.setOrderid(transOrderCreateResponse
					.getOrderNo());
			HttpSession httpSession = request.getSession();
			httpSession.setMaxInactiveInterval(70);
			// 进行微信的oauth2验证
			String callBackUrl = this.getRequestBaseUrl(request)
					+ "/checkstand.action;jsessionid="
					+ httpSession.getId()
					+ "?orderData="
					+ URLEncoder.encode(
							JsonConversionTool.toJson(titanPaymentRequest),
							"UTF-8") + "&failJumpUrl="
					+ URLEncoder.encode(req.getFailJumpUrl(), "UTF-8")
					+ "&successJumpUrl="
					+ URLEncoder.encode(req.getSuccessJumpUrl(), "UTF-8");

			log.info("set wx callback url = " + callBackUrl);

			// 重定向到微信的认证平台
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?"
					+ "appid="
					+ appId
					+ "&redirect_uri="
					+ URLEncoder.encode(callBackUrl, "UTF-8")
					+ "&response_type=code"
					+ "&scope=snsapi_base"
					+ "&state=123#wechat_redirect");
			//记录费率日志
			CreateTitanRateRecordReq rateReq = new CreateTitanRateRecordReq();
			rateReq.setAmount(Long.parseLong(NumberUtil
					.covertToCents(computeReq.getAmount())));
			rateReq.setReceivablefee(Long.parseLong(titanPaymentRequest
					.getReceivablefee()));
			rateReq.setReceivedfee(Long.parseLong(titanPaymentRequest
					.getReceivedfee()));
			rateReq.setStanderdfee(Long.parseLong(titanPaymentRequest
					.getStandfee()));
			rateReq.setPayType(Integer
					.parseInt(CashierItemTypeEnum.WX_PUBLIC.itemCode));
			if (StringUtil.isValidString(titanPaymentRequest.getPaySource())) {
				rateReq.setUsedFor(Integer.parseInt(titanPaymentRequest
						.getPaySource()));
			}
			rateReq.setUserId(computeReq.getUserId());
			rateReq.setReceivableRate(titanPaymentRequest.getReceivablerate());
			rateReq.setReceivedRate(titanPaymentRequest.getExecutionrate());
			rateReq.setStandardRate(titanPaymentRequest.getStandardrate());
			rateReq.setRateType(titanPaymentRequest.getRateType());
			rateReq.setOrderNo(transOrderCreateResponse.getOrderNo());
			rateReq.setCreator(computeReq.getUserId());
			titanRateService.addRateRecord(rateReq);

		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * 获取请求的基础URL,截止到上下文
	 * 
	 * @Title: getRequestBaseUrl
	 * @Description: TODO
	 * @param req
	 * @return
	 * @return: String
	 */
	private String getRequestBaseUrl(HttpServletRequest req) {

		String contextPath = req.getContextPath();
		if (StringUtil.isValidString(context_path)) {
			contextPath = context_path;
		}
		String host = req.getServerName();
		if (req.getServerPort() != 80) {
			host += ":" + req.getServerPort();
		}
		String url = req.getScheme() + "://" + host + contextPath;
		return url;
	}

	@RequestMapping(value = "/errorView")
	public String errorView(){
		return "/wx/errorView";
	}
	
	@RequestMapping(value = "/checkstand")
	public String checkstand(String code, String state, String orderData,
			String successJumpUrl, String failJumpUrl,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			log.info("checkstand request param[code=" + code + ",state="
					+ state + ",orderData=" + orderData + ",successJumpUrl="
					+ successJumpUrl + ",failJumpUrl=" + failJumpUrl + "]");

			successJumpUrl = URLDecoder.decode(successJumpUrl, "UTF-8");
			failJumpUrl = URLDecoder.decode(failJumpUrl, "UTF-8");
			
			if (!StringUtil.isValidString(code)
					|| !StringUtil.isValidString(orderData)) {
				jumpFailUrl(failJumpUrl, response, request,
						TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
				return null;
			}

			orderData = URLDecoder.decode(orderData, "UTF-8");

			TitanPaymentRequest req = JsonConversionTool.toObject(orderData,
					TitanPaymentRequest.class);

			if (req == null) {
				jumpFailUrl(failJumpUrl, response, request,
						TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
				return null;
			}

			// 开始获取微信的openid
			log.info("request wx openId param[appId=" + appId + ",secret="
					+ secret + ",code=" + code
					+ ",authorization_code=authorization_code]");

			Map<String, String> params = new HashMap<String, String>();
			params.put("appid", appId);
			params.put("secret", secret);
			params.put("code", code);
			params.put("grant_type", "authorization_code");

			String str = HttpUtils.doGet(
					"https://api.weixin.qq.com/sns/oauth2/access_token",
					params, true);

			log.info("request wx openid result[" + str + "]");

			Map<String, String> map = JsonConversionTool.toObject(str,
					Map.class);

			if (!StringUtil.isValidString(map.get("openid"))) {
				log.error("wx return openid is null");
				jumpFailUrl(failJumpUrl, response, request,
						TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
				return null;
			}

			// 获取网关的请求参数
			RechargeResponse rechargeResponse = titanPaymentService
					.packageRechargeData(req);

			RechargeDataDTO dataDTO = rechargeResponse.getRechargeDataDTO();
			// 构造网关的请求参数
			Map<String, String> gatewayParam = new HashMap<String, String>();
			gatewayParam.put("merchantNo", dataDTO.getMerchantNo());
			gatewayParam.put("orderNo", dataDTO.getOrderNo());
			gatewayParam.put("orderAmount", dataDTO.getOrderAmount());
			gatewayParam.put("amtType", dataDTO.getAmtType());
			gatewayParam.put("payType", PayTypeEnum.WECHAT_PAY.getKey());
			gatewayParam.put("bankInfo", "");
			gatewayParam.put("pageUrl", dataDTO.getPageUrl());
			gatewayParam.put("notifyUrl", dataDTO.getNotifyUrl());
			gatewayParam.put("orderTime", dataDTO.getOrderTime());
			gatewayParam.put("orderExpireTime", dataDTO.getOrderExpireTime());
			gatewayParam.put("orderMark", dataDTO.getOrderMark());
			gatewayParam.put("signType", dataDTO.getSignType());
			gatewayParam.put("busiCode", dataDTO.getBusiCode());
			gatewayParam.put("version", dataDTO.getVersion());
			gatewayParam.put("charset", dataDTO.getCharset());
			gatewayParam.put("payerAcount", dataDTO.getPayerAcount());
			gatewayParam.put("signMsg", dataDTO.getSignMsg());
			gatewayParam.put("openId", map.get("openid"));

			// 加锁，避免同一个签名的参数对网关接口进行请求，因为网关端会对同一个签名并发请求进行时间间隔控制
			lockOutTradeNoList(dataDTO.getSignMsg());

			HttpSession session = request.getSession();

			log.info("gateway request param[gatewayParam="
					+ gatewayParam.toString() + " sessionId=" + session.getId()
					+ " ]");

			String result = null;
			try {
				// 如果会话中存在该签名，那么就直接返回签名信息，不用向网关进行查询
				if (session.getAttribute(dataDTO.getSignMsg()) == null) {
					String gateway = utilservice.querySysConfig().getGateWayURL();
					result = HttpUtils
							.doPost(gateway,
									gatewayParam);
					session.setAttribute(dataDTO.getSignMsg(), result);
				}

				result = String.valueOf(session.getAttribute(dataDTO
						.getSignMsg()));

				log.info("gateway request result[" + result + "]");

			} finally {
				// 必须要对签名进行解锁，避免死锁
				unlockOutTradeNoList(dataDTO.getSignMsg());
			}

			if (!StringUtil.isValidString(result)) {
				log.error("gateway response json fail!");
				jumpFailUrl(failJumpUrl, response, request,
						TitanMsgCodeEnum.REQUEST_PAYER_FAIL);
				return null;
			}

			// errCode=&errMsg=&merchantNo=M000016&orderNo=2017050510300100001&orderAmount=1000&orderTime=20170505102821
			// &payType=28&payMsg=&version=v1.0&signType=1&signMsg=abfd918d053f02ad866558b28096ce3e&respJs={"appId":"wxda18d758438de540","timeStamp":"1493951435","signType":"MD5","package":"prepay_id=wx201705051030359b5a999b150596358798","nonceStr":"66045","paySign":"06721DAA61442C2AA36DCEC7DDA90025"}

			Map<String, String> resultMap = resultParse(result);

			if (!StringUtil.isValidString(resultMap.get("respJs"))) {
				log.error("gateway response json fail!");
				jumpFailUrl(failJumpUrl, response, request,
						TitanMsgCodeEnum.REQUEST_PAYER_FAIL);
				return null;
			}

			request.setAttribute("payJson", resultMap.get("respJs"));
			request.setAttribute("successJumpUrl", successJumpUrl);
			request.setAttribute(
					"failJumpUrl",
					appendRequestParam(failJumpUrl,
							TitanMsgCodeEnum.REQUEST_PAYER_FAIL));
			return "/checkstand-pay/wxjsapi";

		} catch (Exception e) {
			log.error("", e);
		}
		return "/checkstand-pay/wxjsapi";
	}

	private Map<String, String> resultParse(String str) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("respJs",
				str.substring(str.indexOf("respJs=") + 7, str.length()));
		return map;
	}
	
	
	/**
	 * 转换成本地单请求对象
	 * 
	 * @Title: convertToTitanOrderReq
	 * @Description: TODO
	 * @param req
	 * @return
	 * @return: TitanOrderRequest
	 */
	private TitanOrderRequest convertToTitanOrderReq(MobilePayOrderReq req) {
		TitanOrderRequest dto = new TitanOrderRequest();
		dto.setAmount(req.getAmount());
		dto.setCurrencyType(req.getCurrencyType());
		dto.setGoodsDetail(req.getCommodityDesc());
		dto.setGoodsName(req.getCommodityName());
		dto.setPayerType(PayerTypeEnum.B2B_WX_PUBLIC_PAY.key);
		dto.setUserId(req.getPayerOrg());
		dto.setRuserId(req.getPayeeOrg());
		dto.setGoodsId(req.getBussOrderNo());
		dto.setName(req.getPayerOrgName());
		dto.setNotify(req.getNotifyUrl());
		dto.setEscrowedDate(req.getEscrowedDate());
		
		return dto;
	}

	/**
	 * 检查订单中的信息是否合法
	 * 
	 * @param dto
	 * @return
	 */
	private boolean checkOrderInfo(MobilePayOrderReq req) {

		if (!PayerTypeEnum.B2B_WX_PUBLIC_PAY.getKey().equals(req.getChannelType())) {
			log.error("wx public pay ChannelType is incorrect");
			return false;
		}
		PayerTypeEnum pe = PayerTypeEnum.getPayerTypeEnumByKey(req
				.getChannelType());
		if (pe == null) {
			log.error(" wx public pay ChannelType convert is null");
			return false;
		}

		if (!StringUtil.isValidString(req.getAmount())) {
			log.error("wx public pay Amount is null");
			return false;
		}
		if (StringUtil.isValidString(req.getAmount())) {
			String neg = "(^[1-9]{1}\\d{0,20}(\\.\\d{1,2})?$)";
			String neg2 = "(^[0]{1}(\\.\\d{1,2})?$)";
			if (!(Pattern.matches(neg, req.getAmount()) || Pattern.matches(
					neg2, req.getAmount()))) {
				log.error("传入金额格式错误");
				return false;
			}
		}

		if (new BigDecimal(req.getAmount()).compareTo(BigDecimal.ZERO) < 1) {
			log.error(" wx public pay Amount not regular ");
			return false;
		}

		if (!CommonConstant.CURRENT_TYPE.equals(req.getCurrencyType())) {
			log.error("wx public pay Currency type must be RMB ");
			return false;
		}

		if (!StringUtil.isValidString(req.getBussOrderNo())) {
			log.error("wx public pay BussOrderNo is null");
			return false;
		}

		if (pe.isB2BPayment() && !StringUtil.isValidString(req.getPayeeOrg())) {
			log.error(pe + "PayeeOrg is null");
			return false;
		}
		if(!StringUtil.isValidString(req.getSign())){
			log.error("wx public pay request sign is null");
			return false;
		}
		return true;
	}

	private String appendRequestParam(String url, TitanMsgCodeEnum codeEnum) {
		char flag = '?';
		if (StringUtil.isValidString(url)) {
			if (url.indexOf("?") != -1) {
				flag = '&';
			}
			try {
				url += flag + "errCode=" + codeEnum.getCode() + "&errMsg="
						+ URLEncoder.encode(codeEnum.getKey(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error("", e);
			}
		}
		return url;
	}

	/**
	 * 跳转到错误地址
	 * 
	 * @Title: jumpFailUrl
	 * @Description: TODO
	 * @param url
	 * @param rep
	 * @param req
	 * @param codeEnum
	 * @return: void
	 */
	private void jumpFailUrl(String url, HttpServletResponse rep,
			HttpServletRequest req, TitanMsgCodeEnum codeEnum) {
		try {

			if (StringUtil.isValidString(url)) {
				rep.sendRedirect(appendRequestParam(url, codeEnum));
				return;
			}

			rep.sendRedirect(appendRequestParam(this.getRequestBaseUrl(req)
					+ "/errorView.action", codeEnum));

		} catch (IOException ex) {
			log.error("", ex);
			try {
				rep.sendRedirect(appendRequestParam(this.getRequestBaseUrl(req)
						+ "/errorView.action", codeEnum));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void lockOutTradeNoList(String out_trade_no)
			throws InterruptedException {
		synchronized (mapLock) {
			if (mapLock.containsKey(out_trade_no)) {
				synchronized (mapLock.get(out_trade_no)) {
					mapLock.get(out_trade_no).wait();
				}
			} else {
				mapLock.put(out_trade_no, new Object());
			}

		}
	}

	private void unlockOutTradeNoList(String out_trade_no) {
		if (mapLock.containsKey(out_trade_no)) {
			synchronized (mapLock.get(out_trade_no)) {
				mapLock.remove(out_trade_no).notifyAll();
			}
		}
	}

	/**
	 * 用于接入微信接口的验证步骤
	 *
	 * @param echostr
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wechat", method = RequestMethod.GET, params = "echostr")
	@ResponseBody
	public String wechatAccess(String echostr, UrlParam params)
			throws Exception {
		System.out.println(echostr + "|" + params);
		if (WeChatUtils.verifyMsg(token, params.getSignature(),
				params.getTimestamp(), params.getNonce())) {
			return echostr;
		} else {
			throw new Exception("检验消息签名失败！");
		}
	}
}