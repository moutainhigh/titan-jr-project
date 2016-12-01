package com.fangcang.titanjr.pay.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;
import com.fangcang.titanjr.dto.bean.CashierDeskItemDTO;
import com.fangcang.titanjr.dto.bean.CashierItemBankDTO;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.TitanOpenOrgDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.AccountHistoryRequest;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.AccountHistoryResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.facade.TitanFinancialPermissionFacade;
import com.fangcang.titanjr.pay.req.TitanConfirmBussOrderReq;
import com.fangcang.titanjr.pay.rsp.TianConfirmBussOrderRsp;
import com.fangcang.titanjr.pay.task.TitanPayResultNotifyTask;
import com.fangcang.titanjr.pay.util.TitanThreadPool;
import com.fangcang.titanjr.request.AccountInfoRequest;
import com.fangcang.titanjr.request.CheckPermissionRequest;
import com.fangcang.titanjr.response.CheckAccountResponse;
import com.fangcang.titanjr.response.PermissionResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

/**
 * 收銀台交易服務實現類
 * 
 * @author wengxitao
 *
 */
@Component
public class TitanTradeService {

	private static final Log log = LogFactory
			.getLog(TitanTradeService.class);

	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;

	@Resource
	private TitanFinancialPermissionFacade titanFinancialPermissionFacade;

	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;

	@Resource
	private HessianProxyBeanFactory hessianProxyBeanFactory;

	@Resource
	private TitanCashierDeskService titanCashierDeskService;

	@Resource
	private TitanOrderService titanOrderService;

	/**
	 * 确认业务的订单信息
	 * 
	 * @param req
	 * @return
	 */
	public TianConfirmBussOrderRsp confirmBussOrder(TitanConfirmBussOrderReq req) {

		log.info("confirm buss order req =" + JsonConversionTool.toJson(req));

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("orderNo", req.getBussOrderNo());
		paramMap.put("orderAmount", req.getAmount());

		try {
			HttpPost post = HttpUtils.buildPostForm(req.getUrl(), paramMap);
			post.setConfig(HttpUtils.getDefRequestConfig());
			HttpResponse response = HttpUtils.getHttpClientFactory().execute(
					post);
			String resultStr = EntityUtils.toString(response.getEntity());
			log.info("confirm buss order response content [" + resultStr + "]");
			return JsonConversionTool.toObject(resultStr,
					TianConfirmBussOrderRsp.class);

		} catch (Exception ex) {
			log.error("", ex);
		}

		return null;
	}

	/**
	 * 检查付款和付款方的权限
	 * 
	 * @param dto
	 * @return
	 */
	public boolean checkPermission(TitanOrderRequest dto) {
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
				if (permissionResponse != null) {
					log.error("checkPermission response  "
							+ permissionResponse.getReturnCode() + ":"
							+ permissionResponse.getReturnMessage());
				}
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
				if (response != null) {
					log.error("checkPermission response  "
							+ response.getReturnCode() + ":"
							+ response.getReturnMessage());
				}
				return false;
			}
		}
		
		if(pe.isOpenOrg() && StringUtil.isValidString(dto.getRuserId())){
			if(StringUtil.isValidString(dto.getUserId())){
				log.error("对外商户传入的参数不合法");
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 根据指定的URL确认业务订单的金额
	 * 
	 * @param dto
	 * @return
	 */
	public boolean checkConfirmBussOrder(TitanOrderRequest dto) {
		if (StringUtil.isValidString(dto.getCheckOrderUrl())) {
			TitanConfirmBussOrderReq req = new TitanConfirmBussOrderReq();
			req.setAmount(dto.getAmount());
			req.setBussOrderNo(dto.getGoodsId());
			req.setUrl(dto.getCheckOrderUrl());
			TianConfirmBussOrderRsp bussOrderRsp = this.confirmBussOrder(req);

			if (bussOrderRsp == null || !bussOrderRsp.isSuccess()) {
				if (bussOrderRsp != null) {
					log.error("checkConfirmBussOrder response  "
							+ bussOrderRsp.getResult().getResMsg());
				}
				return false;
			}

		}
		return true;
	}

	/**
	 * 通知业务付款结果
	 * 
	 * @param req
	 */
	public void notifyPayResult(String orderId) {

		log.info("notify pay result req =" + orderId);

		String taskId = MD5.MD5Encode(orderId + System.currentTimeMillis());

		log.info("gen taskId = " + taskId);
		
		TitanPayResultNotifyTask notifyTask = new TitanPayResultNotifyTask(
				titanFinancialTradeService);
		
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setUserorderid(orderId);
		TransOrderDTO transOrderDTO = titanOrderService
				.queryTransOrderDTO(transOrderRequest);
		
		notifyTask.setTransOrderDTO(transOrderDTO);
		notifyTask.setTaskId(taskId);

		log.info("execute pay result notity taskId=" + taskId);
		TitanThreadPool.executeTask(notifyTask);

	}

	/**
	 * 根据用户ID获取账号信息
	 * 
	 * @param userId
	 * @return
	 */
	public AccountBalance getAccountBalance(String userId) {
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

	/**
	 * 根据用户ID查询对于的机构信息
	 * 
	 * @param userId
	 * @return
	 */
	public FinancialOrganDTO getFinancialOrganDTO(String userId) {
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

	/**
	 * 查询用户的账户历史
	 * 
	 * @param inAccountCode
	 * @param outAccountCode
	 * @param userId
	 * @return
	 */
	public AccountHistoryResponse getAccountHistoryResponse(
			String inAccountCode, String outAccountCode, String userId) {
		if (!StringUtil.isValidString(inAccountCode)
				|| !StringUtil.isValidString(outAccountCode)
				|| !StringUtil.isValidString(userId)) {
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

	public Map<String, FinancialOrganDTO> buildUserIdOrganMap(
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

	// 获取常用的支付方式
	public List<CommonPayMethodDTO> getCommonPayMethod(String userId,
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

	public MerchantResponseDTO getMerchantResponseDTO(String merchantCode) {

		try{
			MerchantDetailQueryDTO queryDTO = new MerchantDetailQueryDTO();
			queryDTO.setMerchantCode(merchantCode);

			MerchantFacade merchantFacade = hessianProxyBeanFactory
					.getHessianProxyBean(MerchantFacade.class,
							ProxyFactoryConstants.merchantServerUrl
									+ "merchantFacade");
	        log.info("hessionUrl:"+ProxyFactoryConstants.merchantServerUrl);
			return merchantFacade.queryMerchantDetail(queryDTO);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 将民生银行的企业银行放在最后面
	 * @param commonPayMethodDTOList
	 */
	public void sortBank(List<CommonPayMethodDTO> commonPayMethodDTOList){
		if(commonPayMethodDTOList ==null ||commonPayMethodDTOList.size()==0){
			return ;
		}
		CommonPayMethodDTO cmbcBank =null;
		for(CommonPayMethodDTO commonPayMethodDTO :commonPayMethodDTOList){
			if(CommonConstant.CMBC.equals(commonPayMethodDTO.getBankname()) && 
					 commonPayMethodDTO.getPaytype()!=null &&
					 commonPayMethodDTO.getPaytype().intValue()==CommonConstant.BUS_BANK){
		    		cmbcBank = commonPayMethodDTO;
		    		commonPayMethodDTOList.remove(commonPayMethodDTO);
		    		break;
		     }
		}
		
		if(cmbcBank !=null){
			commonPayMethodDTOList.add(cmbcBank);
		}
		
	}
	
	public void sortBank(CashierDeskDTO cashierDesk){
		List<CashierDeskItemDTO> cashierDeskItemDTOList = cashierDesk.getCashierDeskItemDTOList();
		if(cashierDeskItemDTOList==null || cashierDeskItemDTOList.size()==0){
			return;
		}
		
		CashierItemBankDTO cmbcBank = null;
		for(CashierDeskItemDTO cashierDeskItem :cashierDeskItemDTOList){
			//B2B Item
			if(cashierDeskItem.getItemType()!=null && cashierDeskItem.getItemType().intValue()==1){
				List<CashierItemBankDTO> cashierItemBankDTOList = cashierDeskItem.getCashierItemBankDTOList();
				if(cashierItemBankDTOList ==null || cashierItemBankDTOList.size()==0){
					continue;
				} 
				
				for( CashierItemBankDTO cashierItemBankDTO :cashierItemBankDTOList){
					 if(CommonConstant.CMBC.equals(cashierItemBankDTO.getBankName())){
						 cmbcBank = cashierItemBankDTO;
						 cashierItemBankDTOList.remove(cashierItemBankDTO);
				    	 break;
					}
				}
				if(cmbcBank !=null){
					 cashierItemBankDTOList.add(cmbcBank);
				}
			}
		}
	}
	
	public TitanOpenOrgDTO queryOpenOrg(String userId){
		return titanFinancialOrganService.queryTitanOpenOrgDTO(userId);
	}
	
	public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtil.isValidString(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtil.isValidString(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
