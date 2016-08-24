package com.fangcang.titanjr.pay.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.text.ParseException;
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
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.dto.BaseResponseDTO.ReturnCode;
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
import com.fangcang.titanjr.facade.TitanFinancialPermissionFacade;
import com.fangcang.titanjr.facade.TitanFinancialTradeFacade;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.pay.util.RSADecryptString;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.request.AccountInfoRequest;
import com.fangcang.titanjr.request.CheckPermissionRequest;
import com.fangcang.titanjr.response.CheckAccountResponse;
import com.fangcang.titanjr.response.PermissionResponse;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;
//import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("/trade")
public class FinancialTradeController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(FinancialTradeController.class);

	private static final String TRADE_PAY_ERROR_PAGE = "/checkstand-pay/cashierDeskError";

	private String prv = "30820276020100300d06092a864886f70d0101010500048202603082025c02010002818100d8b6e03dd8f9bf45157f0d14aedf9a696665641da90cab5114a22b7f6c711f22429c32c99ab76e3ce74de00145bcd50b9d2e7c60cd97a4979a5d0ce4ead9ba61baca1495758d69cc1f76e69db43f1ef1f9c33cd2edb8c726ed17c297a7b9fa3f18e58aef9d3f33f8431a41cc3c0ca7bc5151d33a8691e6506e0439363aec0063020301000102818027846d6e89b6bce48d8f6de4b420ad0904357fe492b36f37e941cb19c0bdfdf5e2dc95bc427ca95aecb8bc1caf4948360672f81634d72e99c079b044bbf878ee4c3c6050d319fc545736e392fa7dcf2761d23551663d3844f3f2f61f652bec45eedf6d398a7e0916944bba8c64dd70f770ba4e213764e97c2aeaf46e66a3b591024100f7e35b74120740e22c85c2ddae516bcde62648447f2269b3701503afe4775749d7ffba66eff7024b2b6e879b6b9f3945508eb189d7fe602488575dae609bf151024100dfce5d97595fce458c1769fea32b175bb594a404ea070009c1c139d63907acad0433cc55ffcbf7c81359057efbda4f968813ed34dfedd9edb9079fdfd486c97302406832ad9290b173d89e966b5efb9346197a90c4f7e5e8f53d73f3a1652247f7ed165a6c6430a247d8891d20eb77c5aa3134b7867146d5aa5c30e3688190227cc1024100ad298b8a75d145d4d3aeae0922104e335c0c14d7e486e405a88f2b83cf7e5ba14676196c94cd28faf9d550064f313b9119da691717077e2d8b9315a4e6581f770240177a20e4fc96cb896a899ef9a5bc4c54c24b416e2fda7debf7536e851fb33fdeb95750742b0f09154acd53d73af8750461bca5bd7cc0de58a8fa635453152e78";

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
	private TitanFinancialTradeFacade titanFinancialTradeFacade;

	@Resource
	private TitanFinancialPermissionFacade titanFinancialPermissionFacade;

	/**
	 * @Title: titanPay
	 * @Description: TODO
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
			return TRADE_PAY_ERROR_PAGE;
		}

		try {

			String deInfo = RSADecryptString.decryptString(orderInfo, prv);
			if (!StringUtil.isValidString(deInfo)) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TRADE_PAY_ERROR_PAGE;
			}

			log.info("decrypt orderInfo is ok .");

			TitanOrderRequest dto = JsonConversionTool.toObject(deInfo,
					TitanOrderRequest.class);

			if (dto == null) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TRADE_PAY_ERROR_PAGE;
			}

			log.info("auth user is ok .");

			// 检查解析后的参数是否合理
			if (checkOrderInfo(dto)) {
				log.error("orderInfo check fail!");
				model.addAttribute("msg",
						TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED
								.getResMsg());
				return TRADE_PAY_ERROR_PAGE;
			}

			log.info("check order info is ok");

			if (checkPermission(dto)) {
				log.error("checkPermission is fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.PERMISSION_CHECK_FAILED.getResMsg());
				return TRADE_PAY_ERROR_PAGE;
			}

			log.info("check permission  is ok");

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
				log.error("orderCreateResponse "
						+ orderCreateResponse.getReturnCode() + ":"
						+ orderCreateResponse.getReturnMessage());

				model.addAttribute("msg",
						TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());
				return TRADE_PAY_ERROR_PAGE;
			}

			log.info("end sava titan trans order... [orderNo: "
					+ orderCreateResponse.getOrderNo() + "]");

			PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
			paymentUrlRequest.setPayOrderNo(orderCreateResponse.getOrderNo());
			paymentUrlRequest.setIsEscrowed("0");
			paymentUrlRequest.setPaySource(dto.getPayerType());
			PaymentUrlResponse response = titanFinancialTradeService
					.getPaymentUrl(paymentUrlRequest);

			if (response == null || !response.isResult()) {
				log.error("orderCreateResponse "
						+ orderCreateResponse.getReturnCode() + ":"
						+ orderCreateResponse.getReturnMessage());
				model.addAttribute("msg", TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());
				return TRADE_PAY_ERROR_PAGE;
			}

			log.info("get Payment url ok url=" + response.getUrl());

			this.getResponse().sendRedirect(response.getUrl());

		} catch (Exception ex) {
			log.error("", ex);
		}

		model.addAttribute("msg", TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());
		return TRADE_PAY_ERROR_PAGE;
	}

	private boolean checkPermission(TitanOrderRequest dto) {
		PayerTypeEnum pe = PayerTypeEnum.getPayerTypeEnumByKey(dto
				.getPayerType());

		CheckPermissionRequest permissionRequest = null;
		PermissionResponse permissionResponse = null;

		if (pe.isFcUserId() && StringUtil.isValidString(dto.getUserId())) {
			permissionRequest = new CheckPermissionRequest();
			permissionRequest.setFcuserid(dto.getUserId());
			permissionRequest.setPermission("1");

			log.info("check permission userId= " + dto.getUserId());

			permissionResponse = titanFinancialPermissionFacade
					.isPermissionToPayment(permissionRequest);

			if (permissionResponse == null || (!permissionResponse.isResult())) {
				log.error("checkPermission response  "
						+ permissionResponse.getReturnCode() + ":"
						+ permissionResponse.getReturnMessage());
				return false;
			}
		}

		if (pe.isReicveMerchantCode()
				&& StringUtil.isValidString(dto.getRuserId())) {
			AccountInfoRequest accountInfo = new AccountInfoRequest();
			accountInfo.setMerchantCode(dto.getRuserId());
			log.info("check permission ruserId= " + dto.getRuserId());

			CheckAccountResponse response = titanFinancialPermissionFacade
					.isFinanceAccount(accountInfo);

			if (response == null || (!response.isResult())) {
				log.error("checkPermission response  "
						+ response.getReturnCode() + ":"
						+ response.getReturnMessage());
				return false;
			}
		}
		return true;
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

	@ResponseBody
	@RequestMapping(value = "/checkOrderStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String checkOrderStatus(String orderId) {
		return "{\"status\":\"0\"}";
	}
	
	//DDDDDD
    @RequestMapping("/showCashierDesk")
	public String showCashierDesk(String orderNo,String sign, Model model){
		
		orderNo = "titanjrorder160606162006812";
		log.info("获取支付地址入参:" + JsonConversionTool.toJson(orderNo));
		
		if(!StringUtil.isValidString(orderNo)){
			model.addAttribute("msg", "订单流水号不空");
			return "checkstand-pay/cashierDeskError";
		}
		
		//验证签名
		
		//根据payOrderNo查询出相应的订单
		TransOrderRequest transOrderRequest = new TransOrderRequest(); 
		transOrderRequest.setUserorderid(orderNo);
		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
		
		if(null ==transOrderDTO || !StringUtil.isValidString(transOrderDTO.getUserid())){//验证付款方
			model.addAttribute("msg", "查无此单,或订单错误");
			return "checkstand-pay/cashierDeskError";
		}
		
		if(!StringUtil.isValidString(transOrderDTO.getPayerType()) 
				||null ==PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType())){
			model.addAttribute("msg", "系统错误");
			return "checkstand-pay/cashierDeskError";
		}
		
		PayerTypeEnum payerTypeEnum = PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
		
		CashDeskData cashDeskData = new CashDeskData();
		
		//查询收款方机构号
		if(StringUtil.isValidString(transOrderDTO.getPayeemerchant())){
			FinancialOrganDTO financialOrganDTO = this.getFinancialOrganDTO(transOrderDTO.getPayeemerchant());
			if(null ==financialOrganDTO ){
				model.addAttribute("msg", "收款机构信息错误");
				return "checkstand-pay/cashierDeskError";
			}
			cashDeskData.setOrgName(financialOrganDTO.getOrgName());
			cashDeskData.setTitanCode(financialOrganDTO.getTitanCode());
		}
	
		//查询账户信息，不是中间账户就需要查询
		
		if(!"141223100000056".equals(transOrderDTO.getUserid())){//不是中间账户就需要查询账户信息,
			AccountBalance accountBalance = this.getAccountBalance(transOrderDTO.getUserid());
			if(accountBalance == null){
				model.addAttribute("msg", "账户信息错误");
				return "checkstand-pay/cashierDeskError";
			}
			cashDeskData.setBalanceusable(accountBalance.getBalanceusable());
			//查询常用信息，
			List<CommonPayMethodDTO> commonPayMethodDTOList =  this.getCommonPayMethod(transOrderDTO.getUserid(),PayerTypeEnum.getPaySource(transOrderDTO.getPayerType()));
			cashDeskData.setCommonPayMethodDTOList(commonPayMethodDTOList);
		}
		
		Map<String,String> bussinessInfoMap =null;
		if(StringUtil.isValidString(transOrderDTO.getBusinessinfo())){
			bussinessInfoMap = JsonConversionTool.toObject(transOrderDTO.getBusinessinfo(), Map.class);
		}
		
		//查询收款历史
		String inAccountCode = "";
		String outAccountCode="";
		if(null != bussinessInfoMap){
			inAccountCode = bussinessInfoMap.get("inAccountCode");
			outAccountCode = bussinessInfoMap.get("outAccountCode");
		}
		AccountHistoryResponse accountHistoryResponse = this.getAccountHistoryResponse(inAccountCode, 
				outAccountCode, transOrderDTO.getUserid());
			if (accountHistoryResponse !=null && accountHistoryResponse.isResult() &&
			CollectionUtils.isNotEmpty(accountHistoryResponse.getAccountHistoryDTOList())) {
			Map<String, FinancialOrganDTO> userIDOrgMap = this.buildUserIdOrganMap(accountHistoryResponse.getAccountHistoryDTOList());
			cashDeskData.setAccountHistoryDTO(accountHistoryResponse.getAccountHistoryDTOList().get(0));
			cashDeskData.setUserIDOrgMap(userIDOrgMap);
			}
			
			//设置商家主题]
		if(StringUtil.isValidString(transOrderDTO.getMerchantcode())){
			MerchantResponseDTO merchantResponseDTO = this.getMerchantResponseDTO(transOrderDTO.getMerchantcode());
			if(null !=merchantResponseDTO){
				model.addAttribute("CURRENT_THEME",cashDeskData.getCurrentTheme());
			}
		}
	
			
		CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
		cashierDeskQueryRequest.setUserId(transOrderDTO.getUserid());
		//GDP支付时用商家的收银台
		if(StringUtil.isValidString(cashDeskData.getRecieveOrgCode())){
			cashierDeskQueryRequest.setUserId(cashDeskData.getRecieveOrgCode());
		}
		cashierDeskQueryRequest.setUsedFor(PayerTypeEnum.getPaySource(transOrderDTO.getPayerType()));
		
		CashierDeskResponse response = titanCashierDeskService.queryCashierDesk(cashierDeskQueryRequest);
		if (!(response.isResult() && CollectionUtils.isNotEmpty(response.getCashierDeskDTOList()))){
			cashDeskData.putError("收银台不存在");
			return "checkstand-pay/cashierDeskError";
		}
		cashDeskData.setCashierDeskDTO( response.getCashierDeskDTOList().get(0));
		cashDeskData.setPaySource(PayerTypeEnum.getPaySource(transOrderDTO.getPayerType()).toString());
		cashDeskData.setMerchantcode(transOrderDTO.getMerchantcode());
		cashDeskData.setUserId(transOrderDTO.getUserid());
		model.addAttribute("cashDeskData", cashDeskData);
		
		if(payerTypeEnum.isRechargeCashDesk()){
			return "account-overview/account-recharge";
		}
	    return "checkstand-pay/cashierDesk";
	}
	

	private AccountBalance getAccountBalance(String userId){
		AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
		accountBalanceRequest.setUserid(userId);
		AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
		if (balanceResponse.isResult() && CollectionUtils.isNotEmpty(balanceResponse.getAccountBalance())) {
			AccountBalance accountBalance = balanceResponse.getAccountBalance().get(0);
			if (accountBalance.getBalanceusable() != null) {
				accountBalance.setBalanceusable(new BigDecimal(accountBalance.getBalanceusable()).divide(new BigDecimal(100)).toString());
				return accountBalance;
			} 
		} 
		return null;
	}

   //查询机构信息
	private FinancialOrganDTO getFinancialOrganDTO(String userId){
		if(!StringUtil.isValidString(userId)){
			return null;
		}
		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
    	organQueryRequest.setUserId(userId);
    	FinancialOrganResponse financialOrgan =  titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
    	if(financialOrgan.isResult()){
    		return financialOrgan.getFinancialOrganDTO();
    	}
    	return null;
	}
	
	//查询账户信息
	private AccountHistoryResponse getAccountHistoryResponse(String inAccountCode,String outAccountCode,String userId){
		if(StringUtil.isValidString(inAccountCode) 
				|| StringUtil.isValidString(outAccountCode)
				||StringUtil.isValidString(userId)){
			return null;
		}
		AccountHistoryRequest accHistoryRequest = new AccountHistoryRequest();
		accHistoryRequest.setAccountHistoryDTO(new AccountHistoryDTO());
		accHistoryRequest.getAccountHistoryDTO().setInaccountcode(inAccountCode);
		accHistoryRequest.getAccountHistoryDTO().setOutaccountcode(outAccountCode);
		accHistoryRequest.getAccountHistoryDTO().setPayeruserid(userId);
		AccountHistoryResponse accountHistoryResponse = titanFinancialAccountService.queryAccountHistory(accHistoryRequest);
		if (accountHistoryResponse.isResult() &&
				CollectionUtils.isNotEmpty(accountHistoryResponse.getAccountHistoryDTOList())) {
			return accountHistoryResponse;
		}
		return null;
	}
	
	//获取常用的支付方式
	private List<CommonPayMethodDTO> getCommonPayMethod(String userId,Integer userFor){
		if(!StringUtil.isValidString(userId) || userFor == null){
			return null;
		}
		CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
		cashierDeskQueryRequest.setUsedFor(userFor);
		cashierDeskQueryRequest.setUserId(userId);
		List<CommonPayMethodDTO> commonPayMethodDTOList = titanCashierDeskService.queryCommonPayMethod(cashierDeskQueryRequest);
		if(commonPayMethodDTOList!=null && commonPayMethodDTOList.size()>0){
			return commonPayMethodDTOList;
		}
		return null;
	}
	
	private Map<String,FinancialOrganDTO> buildUserIdOrganMap(List<AccountHistoryDTO> historyDTOList){
		Map<String,FinancialOrganDTO> result = new HashMap<String, FinancialOrganDTO>();
		for (AccountHistoryDTO accountHistoryDTO : historyDTOList) {
			FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
			organQueryRequest.setUserId(accountHistoryDTO.getPayeeuserid());
			FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
			if (financialOrganResponse.isResult() && financialOrganResponse.getFinancialOrganDTO() != null) {
				result.put(accountHistoryDTO.getPayeeuserid(), financialOrganResponse.getFinancialOrganDTO());
			}
		}
		return result;
	}
	
	private MerchantResponseDTO getMerchantResponseDTO(String merchantCode){
		MerchantDetailQueryDTO queryDTO = new MerchantDetailQueryDTO();
        queryDTO.setMerchantCode(merchantCode);
        return this.getMerchantFacade().queryMerchantDetail(queryDTO);
	}

	private MerchantFacade getMerchantFacade(){
		if(merchantFacade ==null){
			merchantFacade = hessianProxyBeanFactory.getHessianProxyBean(MerchantFacade.class,
		             ProxyFactoryConstants.merchantServerUrl + "merchantFacade");
		}
		
		System.out.println(merchantFacade);
		 return merchantFacade;
	}
	
	@ResponseBody
	@RequestMapping("genLoacalPayOrderNo")
	public Map<String,String> getRechargePayOrderNo(){
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("result", OrderGenerateService.genLoacalPayOrderNo());
	    return resultMap;
	}
}
