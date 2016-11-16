package com.fangcang.titanjr.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.FreezeConditionCodeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.TransOrderTypeEnum;
import com.fangcang.titanjr.common.enums.WithDrawStatusEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dao.TitanAccountDao;
import com.fangcang.titanjr.dao.TitanAccountHistoryDao;
import com.fangcang.titanjr.dao.TitanFundFreezereqDao;
import com.fangcang.titanjr.dao.TitanFundUnFreezereqDao;
import com.fangcang.titanjr.dao.TitanOrgDao;
import com.fangcang.titanjr.dao.TitanTransOrderDao;
import com.fangcang.titanjr.dao.TitanWithDrawReqDao;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.AccountDTO;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.request.AccountCreateRequest;
import com.fangcang.titanjr.dto.request.AccountHistoryRequest;
import com.fangcang.titanjr.dto.request.AccountRequest;
import com.fangcang.titanjr.dto.request.AccountUpdateRequest;
import com.fangcang.titanjr.dto.request.BalanceWithDrawRequest;
import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.FinancialOrderRequest;
import com.fangcang.titanjr.dto.request.FreezeAccountBalanceRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.request.UnFreezeAccountBalanceRequest;
import com.fangcang.titanjr.dto.request.UnFreezeRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.AccountCreateResponse;
import com.fangcang.titanjr.dto.response.AccountHistoryResponse;
import com.fangcang.titanjr.dto.response.AccountResponse;
import com.fangcang.titanjr.dto.response.AccountUpdateResponse;
import com.fangcang.titanjr.dto.response.BalanceWithDrawResponse;
import com.fangcang.titanjr.dto.response.DefaultPayerConfigResponse;
import com.fangcang.titanjr.dto.response.FinancialOrderResponse;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.dto.response.UnFreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.UnFreezeResponse;
import com.fangcang.titanjr.entity.TitanAccount;
import com.fangcang.titanjr.entity.TitanAccountHistory;
import com.fangcang.titanjr.entity.TitanFundFreezereq;
import com.fangcang.titanjr.entity.TitanFundUnFreezereq;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.TitanTransOrder;
import com.fangcang.titanjr.entity.TitanWithDrawReq;
import com.fangcang.titanjr.entity.parameter.TitanAccountHistoryParam;
import com.fangcang.titanjr.entity.parameter.TitanAccountParam;
import com.fangcang.titanjr.entity.parameter.TitanFundFreezereqParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgParam;
import com.fangcang.titanjr.rs.dto.BalanceInfo;
import com.fangcang.titanjr.rs.manager.RSAccTradeManager;
import com.fangcang.titanjr.rs.request.AccountBalanceQueryRequest;
import com.fangcang.titanjr.rs.request.AccountWithDrawRequest;
import com.fangcang.titanjr.rs.request.BalanceFreezeRequest;
import com.fangcang.titanjr.rs.request.BalanceUnFreezeRequest;
import com.fangcang.titanjr.rs.response.AccountBalanceQueryResponse;
import com.fangcang.titanjr.rs.response.AccountWithDrawResponse;
import com.fangcang.titanjr.rs.response.BalanceFreezeResponse;
import com.fangcang.titanjr.rs.response.BalanceUnFreezeResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

/**
 * Created by zhaoshan on 2016/5/9.
 */
@Service("titanFinancialAccountService")
public class TitanFinancialAccountServiceImpl implements TitanFinancialAccountService {
   
	private static final Log log = LogFactory.getLog(TitanFinancialUserServiceImpl.class);


    @Resource
    private TitanAccountDao titanAccountDao;

    @Autowired
    private RSAccTradeManager rsAccTradeManager;
    
    @Resource
    private TitanFundFreezereqDao titanFundFreezereqDao;
    
    @Resource
    private TitanFundUnFreezereqDao titanFundUnFreezereqDao;
    
    @Resource
    private TitanTransOrderDao titanTransOrderDao;
    
    @Resource 
    private TitanWithDrawReqDao titanWithDrawReqDao;
    
    @Resource
    private TitanOrgDao titanOrgDao;
    
    @Resource
    private TitanAccountHistoryDao titanAccountHistoryDao;
    
    @Resource
    private TitanFinancialTradeService titanFinancialTradeService;
    
    @Resource
    private TitanOrderService titanOrderService;
    
    @Resource
    private TitanFinancialBankCardService titanFinancialBankCardService;
    
    @Resource
    private TitanFinancialAccountService titanFinancialAccountService;
    

    @Override
    public AccountCreateResponse createAccount(AccountCreateRequest accountCreateRequest) {
        AccountCreateResponse accountCreateResponse = new AccountCreateResponse();
        AccountBalanceQueryRequest accountBalanceQueryRequest = new AccountBalanceQueryRequest();
        AccountBalanceQueryResponse response = rsAccTradeManager.queryAccountBalance(accountBalanceQueryRequest);
        if (response.isSuccess()) {
            TitanAccount account = new TitanAccount();
            account.setCreator(accountCreateRequest.getOperator());
            account.setCreatetime(new Date());
            account.setAccountcode(accountCreateRequest.getAccountcode());
            account.setAccountname(accountCreateRequest.getAccountname());
            account.setAllownopwdpay(0);
            account.setCurrency(1);
            account.setFinaccountid(response.getBalanceInfoList().get(0).getFinaccountid());
            account.setNopwdpaylimit(1000d);
            account.setUserid(accountCreateRequest.getUserid());
            account.setStatus(1);
            account.setCreditamount(Double.valueOf(response.getBalanceInfoList().get(0).getBalancecredit()));
            account.setForzenamount(Double.valueOf(response.getBalanceInfoList().get(0).getBalancefrozon()));
            account.setSettleamount(Double.valueOf(response.getBalanceInfoList().get(0).getBalancesettle()));
            account.setTotalamount(Double.valueOf(response.getBalanceInfoList().get(0).getAmount()));
            account.setUsableamount(Double.valueOf(response.getBalanceInfoList().get(0).getBalanceusable()));
            account.setBalanceoverlimit(Double.valueOf(response.getBalanceInfoList().get(0).getBalanceoverlimit()));
            try {
                titanAccountDao.insert(account);
            } catch (Exception e) {
                log.error("账户创建异常", e);
                accountCreateResponse.putErrorResult("create_account_error","账户创建异常");
                return accountCreateResponse;
            }
        } else {
            accountCreateResponse.putErrorResult("create_account_error","账户创建异常");
            return accountCreateResponse;
        }
        accountCreateResponse.putSuccess();
        return accountCreateResponse;
    }
    
    

//    @Override
//    public AccountBalanceResponse queryAccountBalance(AccountBalanceRequest accountBalanceRequest) {
//
//        return null;
//    }

	@Override
	public AccountResponse getAccount(AccountRequest accountRequest) {
		AccountResponse response = new AccountResponse();
		if(!StringUtil.isValidString(accountRequest.getUserid())){
			response.putSysError();
			return response;
		}
		PaginationSupport<TitanAccount> paginationSupport =  new PaginationSupport<TitanAccount>();
		paginationSupport.setPageSize(accountRequest.getPageSize());
		paginationSupport.setCurrentPage(accountRequest.getCurrentPage());
		paginationSupport.setOrderBy(" createtime desc ");
		TitanAccountParam condition = new TitanAccountParam();
		condition.setUserid(accountRequest.getUserid());
		try {
			paginationSupport = titanAccountDao.selectForPage(condition, paginationSupport);
			if(paginationSupport.getItemList().size()>0){
				AccountDTO accountDTO = new AccountDTO();
				MyBeanUtil.copyProperties(accountDTO, paginationSupport.getItemList().get(0));
				response.setAccountDTO(accountDTO);
			}
			response.putSuccess();
		} catch (Exception e) {
			log.error("get account error, param  accountRequest :"+JSONSerializer.toJSON(accountRequest).toString(), e); 
			response.putErrorResult("查询失败");
			return response;
		}
		return response;
	}


	@Override
	public AccountUpdateResponse updateAccount(AccountUpdateRequest accountUpdateRequest) throws GlobalServiceException {
		AccountUpdateResponse response = new AccountUpdateResponse();
		if(!StringUtil.isValidString(accountUpdateRequest.getUserId())){
			response.putSysError();
			return response;
		}
		TitanAccount entity = new TitanAccount();
		entity.setUserid(accountUpdateRequest.getUserId());
		entity.setStatus(accountUpdateRequest.getStatus());
		entity.setAllownopwdpay(accountUpdateRequest.getAllownopwdpay());
		entity.setBalanceoverlimit(accountUpdateRequest.getBalanceOverLimit());
		entity.setModifier(accountUpdateRequest.getOperator());
		entity.setModifytime(new Date());
		try {
			titanAccountDao.update(entity);
			response.putSuccess();
			return response;
		} catch (Exception e) {
			throw new GlobalServiceException("更新账户信息失败，参数accountUpdateRequest："+JSONSerializer.toJSON(accountUpdateRequest).toString(),e);
		}
	}


	@Override
	public FreezeAccountBalanceResponse freezeAccountBalance(
			RechargeResultConfirmRequest rechargeResultConfirmRequest) throws Exception {
		FreezeAccountBalanceResponse freezeAccountBalanceResponse = new FreezeAccountBalanceResponse();
		try{
			FreezeAccountBalanceRequest freezeAccountBalanceRequest = null;
			if(rechargeResultConfirmRequest !=null){
				freezeAccountBalanceRequest = convertToFreezeAccountBalanceRequest(rechargeResultConfirmRequest);
			}
			if(freezeAccountBalanceRequest !=null){
				BalanceFreezeRequest balanceFreezeRequest = convertTobalanceFreezeRequest(freezeAccountBalanceRequest);
				if(balanceFreezeRequest !=null){
					BalanceFreezeResponse balanceFreezeResponse = rsAccTradeManager.freezeAccountBalance(balanceFreezeRequest);
					if(balanceFreezeResponse !=null){
						if(CommonConstant.OPERATE_SUCCESS.equals(balanceFreezeResponse.getOperateStatus())){
							freezeAccountBalanceResponse.setAuthcode(balanceFreezeResponse.getAuthcode());
							//将冻结的信息插入数据库
							TitanFundFreezereq titanFundFreezereq = convertToTitanFundFreezereq(freezeAccountBalanceRequest);
							if(titanFundFreezereq !=null){
								titanFundFreezereq.setAuthcode(balanceFreezeResponse.getAuthcode());
								int row = titanFundFreezereqDao.insert(titanFundFreezereq);
								if(row<1){
									throw new Exception();
								}
							}
							freezeAccountBalanceResponse.setFreezeSuccess(true);
							freezeAccountBalanceResponse.putSuccess();
						}
					}
					freezeAccountBalanceResponse.putErrorResult(balanceFreezeResponse.getReturnCode(), balanceFreezeResponse.getReturnMsg());
					return freezeAccountBalanceResponse;
				}
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new Exception(e);
		}
		freezeAccountBalanceResponse.setFreezeSuccess(false);
		freezeAccountBalanceResponse.putSysError();
		return freezeAccountBalanceResponse;
	}
	
	/**
	 * 将回调回来的参数封装为冻结金额的参数
	 * @param rechargeResultConfirmRequest
	 * @return
	 * @throws Exception
	 * @author fangdaikang
	 */
	/**
	 * @param rechargeResultConfirmRequest
	 * @return
	 * @throws Exception
	 */
	private FreezeAccountBalanceRequest convertToFreezeAccountBalanceRequest(RechargeResultConfirmRequest rechargeResultConfirmRequest) throws Exception{
		try{
			FreezeAccountBalanceRequest freezeAccountBalanceRequest = new FreezeAccountBalanceRequest();
	    	if(rechargeResultConfirmRequest.getPayAmount()!=null){
	    		freezeAccountBalanceRequest.setAmount(Long.parseLong(rechargeResultConfirmRequest.getPayAmount()));
	    	}
	    	freezeAccountBalanceRequest.setOrderno(rechargeResultConfirmRequest.getOrderNo());
	    	freezeAccountBalanceRequest.setBankcode(rechargeResultConfirmRequest.getBankCode());
	    	freezeAccountBalanceRequest.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
	    	freezeAccountBalanceRequest.setRequestno(OrderGenerateService.genFreeAccountBalanceRequestNo());
	    	freezeAccountBalanceRequest.setFunccode(CommonConstant.FUNCCODE);
	    	freezeAccountBalanceRequest.setUserfee((long)0);
	    	freezeAccountBalanceRequest.setConditioncode(FreezeConditionCodeEnum.NO_ORDER);
	    	freezeAccountBalanceRequest.setRequesttime(new Date());
	    	freezeAccountBalanceRequest.setStatus(CommonConstant.FREEZE_STATUS);
	    	freezeAccountBalanceRequest.setUseripaddress(CommonConstant.USER_IPADDRESS);
	    	freezeAccountBalanceRequest.setUserid(rechargeResultConfirmRequest.getUserid());
	    	freezeAccountBalanceRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
	    	freezeAccountBalanceRequest.setOrdercount(1);
	    	if(rechargeResultConfirmRequest.getOrderAmount() !=null){
	    		freezeAccountBalanceRequest.setOrderamount(Long.parseLong(rechargeResultConfirmRequest.getOrderAmount()));
	    	}
	    	
	    	//用orderNo获取订单
	    	freezeAccountBalanceRequest.setOrdercount(1);
	    	return freezeAccountBalanceRequest;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new Exception(e);
		}
	}
	
	/**
	 * 冻结资金的请求参数封装（保存到本地数据库）
	 * @param freezeAccountBalanceRequest
	 * @return
	 * @author fangdaikang
	 */
	private TitanFundFreezereq convertToTitanFundFreezereq(FreezeAccountBalanceRequest freezeAccountBalanceRequest){
		TitanFundFreezereq titanFundFreezereq = null;
		if(freezeAccountBalanceRequest !=null){
			titanFundFreezereq = new TitanFundFreezereq();
			if(freezeAccountBalanceRequest.getAmount() !=null){
				titanFundFreezereq.setAmount(freezeAccountBalanceRequest.getAmount());
			}
			if(freezeAccountBalanceRequest.getConditioncode() !=null){
				titanFundFreezereq.setConditioncode(freezeAccountBalanceRequest.getConditioncode().getKey());
			}
			titanFundFreezereq.setFunccode(freezeAccountBalanceRequest.getFunccode());
			titanFundFreezereq.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
			titanFundFreezereq.setOrdercount(freezeAccountBalanceRequest.getOrdercount());
			titanFundFreezereq.setOrderno(freezeAccountBalanceRequest.getOrderno());
			titanFundFreezereq.setRequestno(freezeAccountBalanceRequest.getRequestno());
			titanFundFreezereq.setRequesttime(freezeAccountBalanceRequest.getRequesttime());
			titanFundFreezereq.setStatus(freezeAccountBalanceRequest.getStatus());
			if(freezeAccountBalanceRequest.getUserfee() !=null){
				titanFundFreezereq.setUserfee(freezeAccountBalanceRequest.getUserfee().intValue());
			}
			titanFundFreezereq.setUseripaddress(freezeAccountBalanceRequest.getUseripaddress());
			titanFundFreezereq.setUserid(freezeAccountBalanceRequest.getUserid());
			titanFundFreezereq.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			titanFundFreezereq.setOrderamount(freezeAccountBalanceRequest.getOrderamount());
			titanFundFreezereq.setOrdercount(freezeAccountBalanceRequest.getOrdercount());
		}
		
		return titanFundFreezereq;
	}
	
	/**
	 * 冻结资金的请求参数封装（融数）
	 * @param freezeAccountBalanceRequest
	 * @return
	 * @throws Exception
	 * @author fangdaikang
	 */
	private BalanceFreezeRequest convertTobalanceFreezeRequest(FreezeAccountBalanceRequest freezeAccountBalanceRequest) throws Exception{
		BalanceFreezeRequest balanceFreezeRequest = null;
		try{
			if(freezeAccountBalanceRequest !=null){
				balanceFreezeRequest = new BalanceFreezeRequest();
				balanceFreezeRequest.setAmount(freezeAccountBalanceRequest.getAmount());
				if(freezeAccountBalanceRequest.getConditioncode() !=null){
					balanceFreezeRequest.setConditioncode(freezeAccountBalanceRequest.getConditioncode().getKey());
				}
				balanceFreezeRequest.setOrderno(freezeAccountBalanceRequest.getOrderno());
				balanceFreezeRequest.setBankcode(freezeAccountBalanceRequest.getBankcode());
				balanceFreezeRequest.setMerchantcode(freezeAccountBalanceRequest.getMerchantcode());
				balanceFreezeRequest.setRequestno(freezeAccountBalanceRequest.getRequestno());
				balanceFreezeRequest.setFunccode(freezeAccountBalanceRequest.getFunccode());
				balanceFreezeRequest.setUserfee(freezeAccountBalanceRequest.getUserfee());
				balanceFreezeRequest.setRequesttime(freezeAccountBalanceRequest.getRequesttime());
				balanceFreezeRequest.setStatus(freezeAccountBalanceRequest.getStatus());
				balanceFreezeRequest.setUseripaddress(freezeAccountBalanceRequest.getUseripaddress());
				balanceFreezeRequest.setUserid(freezeAccountBalanceRequest.getUserid());
				balanceFreezeRequest.setProductid(freezeAccountBalanceRequest.getProductid());
				balanceFreezeRequest.setOrderamount(freezeAccountBalanceRequest.getOrderamount());
				balanceFreezeRequest.setOrdercount(freezeAccountBalanceRequest.getOrdercount());
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new Exception(e);
		}
		return balanceFreezeRequest;
	}

	//查询账户明细
	@Override
	public AccountBalanceResponse queryAccountBalance(AccountBalanceRequest accountBalanceRequest) {
		log.info("查询账户明细:"+JSON.toJSON(accountBalanceRequest));
		AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
		if (accountBalanceRequest != null) {
			AccountBalanceQueryRequest balanceQueryRequest = new AccountBalanceQueryRequest();
			MyBeanUtil.copyProperties(balanceQueryRequest, accountBalanceRequest);
			balanceQueryRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
			AccountBalanceQueryResponse balanceQueryResponse = null;
			try {
				balanceQueryResponse = rsAccTradeManager.queryAccountBalance(balanceQueryRequest);
				log.info("查询账户明细结果:"+JSON.toJSON(balanceQueryResponse));
			} catch (Exception e) {
				log.error("调用融数查询账户余额异常", e);
			}
			if (balanceQueryResponse != null && CommonConstant.OPERATE_SUCCESS.equals(balanceQueryResponse.getOperateStatus())
					&& CollectionUtils.isNotEmpty(balanceQueryResponse.getBalanceInfoList())) {
				List<AccountBalance> accountBalanceList = new ArrayList<AccountBalance>();
				for (BalanceInfo balanceInfo : balanceQueryResponse.getBalanceInfoList()) {
					AccountBalance accountBalance = new AccountBalance();
					MyBeanUtil.copyProperties(accountBalance, balanceInfo);
					accountBalanceList.add(accountBalance);
				}
				accountBalanceResponse.setAccountBalance(accountBalanceList);
				accountBalanceResponse.setOperateStatus(CommonConstant.OPERATE_SUCCESS);
				accountBalanceResponse.putSuccess();
				return accountBalanceResponse;
			} else {
				log.error("查询账户结果信息异常" + balanceQueryResponse.toString());
				accountBalanceResponse.putErrorResult("ACC_RES_ERROR", "查询账户结果信息异常");
			}
		} else {
			log.error("查询参数不合法");
			accountBalanceResponse.putErrorResult("REQ_PARAM_INVALID", "查询参数不合法");
		}
		accountBalanceResponse.putSysError();
		return accountBalanceResponse;
	}

	//解冻资金,个人进行
	@Override
	public UnFreezeAccountBalanceResponse unfreezeAccountBalance(
			UnFreezeAccountBalanceRequest unFreezeAccountBalanceRequest) {
		UnFreezeAccountBalanceResponse unFreezeAccountBalanceResponse = new UnFreezeAccountBalanceResponse();
		try{
			if(unFreezeAccountBalanceRequest !=null && StringUtil.isValidString(unFreezeAccountBalanceRequest.getOrderNo())){
				//查询冻结记录
				TitanFundFreezereq titanFundFreezereq = getTitanFundFreezereq(unFreezeAccountBalanceRequest.getOrderNo());
				BalanceUnFreezeRequest balanceUnFreezeRequest = new BalanceUnFreezeRequest();
				MyBeanUtil.copyProperties(balanceUnFreezeRequest, unFreezeAccountBalanceRequest);
				if(titanFundFreezereq !=null){//一条冻结记录对应一条解冻记录
					balanceUnFreezeRequest.setAuthcode(titanFundFreezereq.getAuthcode());
					if(titanFundFreezereq.getAmount() !=null){
						balanceUnFreezeRequest.setAmount(titanFundFreezereq.getAmount().toString());
					}
					balanceUnFreezeRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
					balanceUnFreezeRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
					balanceUnFreezeRequest.setConditioncode(FreezeConditionCodeEnum.NO_ORDER.getKey());
					balanceUnFreezeRequest.setFrozenuserorderid(titanFundFreezereq.getRequestno());
					BalanceUnFreezeResponse balanceUnFreezeResponse = rsAccTradeManager.unFreezeAccountBalance(balanceUnFreezeRequest);
					if(balanceUnFreezeResponse !=null ){//解冻成功，插入数据库
						if(CommonConstant.OPERATE_SUCCESS.equals(balanceUnFreezeResponse.getOperateStatus())){
							TitanFundUnFreezereq titanFundUnFreezereq = new TitanFundUnFreezereq();
							MyBeanUtil.copyProperties(titanFundUnFreezereq, unFreezeAccountBalanceRequest);
							titanFundUnFreezereq.setFundFreezereqid(titanFundFreezereq.getFreezereqid());
							titanFundUnFreezereq.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
							titanFundUnFreezereq.setConditioncode(FreezeConditionCodeEnum.NO_ORDER.getKey());
							int row = titanFundUnFreezereqDao.insert(titanFundUnFreezereq);
							if(row<1){
								throw new Exception();
							}
							unFreezeAccountBalanceResponse.putSuccess();
						}else{
							unFreezeAccountBalanceResponse.putErrorResult(balanceUnFreezeResponse.getRetcode(), balanceUnFreezeResponse.getRetmsg());
						}
						return unFreezeAccountBalanceResponse;
					}
				}
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		unFreezeAccountBalanceResponse.putSysError();
		return unFreezeAccountBalanceResponse;
	}
	
	private TitanFundFreezereq getTitanFundFreezereq(String orderNo){
		if(StringUtil.isValidString(orderNo)){
			TitanFundFreezereqParam condition = new TitanFundFreezereqParam();
			condition.setOrderno(orderNo);
			PaginationSupport<TitanFundFreezereq> paginationSupport = new PaginationSupport<TitanFundFreezereq>();
			titanFundFreezereqDao.selectForPage(condition, paginationSupport);
			List<TitanFundFreezereq> titanFundFreezereqList = paginationSupport.getItemList();
			if(titanFundFreezereqList !=null && titanFundFreezereqList.size()>0){
				return titanFundFreezereqList.get(0);
			}
		}
		return null;
	}

	@Override
	public BalanceWithDrawResponse accountBalanceWithdraw(BalanceWithDrawRequest balanceWithDrawRequest) {
		BalanceWithDrawResponse withDrawResponse = new BalanceWithDrawResponse();
		if (!GenericValidate.validate(balanceWithDrawRequest)) {
			withDrawResponse.putErrorResult("请求参数校验错误");
			return withDrawResponse;
		}
		//判断提现金额和可用余额，获取可用余额
		boolean flag = isBalanceVaild(balanceWithDrawRequest);
		if ( !flag ) {//提现落单，不需要在融数落单，只需要在本地落单
			withDrawResponse.putErrorResult("提现金额不正确请核实后再次发起");
			return withDrawResponse;
		}
		TitanTransOrder titanTransOrder = convertToTitanTransOrder(balanceWithDrawRequest);
		
		
		TransOrderDTO orderDTO = null;
		if (balanceWithDrawRequest.getOrderNo() != null) {
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setUserorderid(balanceWithDrawRequest
					.getOrderNo());
			 orderDTO = titanOrderService
					.queryTransOrderDTO(transOrderRequest);
			if (orderDTO != null) {
				titanTransOrder.setUserorderid(balanceWithDrawRequest.getOrderNo());
				titanTransOrder.setTransid(orderDTO.getTransid());
			}
		}
		
		if (null == titanTransOrder ){
			withDrawResponse.putErrorResult("构造提现交易单失败");
			return withDrawResponse;
		}
		try {
			titanTransOrder.setStatusid(OrderStatusEnum.ORDER_IN_PROCESS.getStatus());
			titanTransOrder.setTransordertype(TransOrderTypeEnum.WITHDRAW.type);
			int rowNum = 0;
			if (orderDTO != null) {
				rowNum =	titanTransOrderDao.updateTitanTransOrderByTransId(titanTransOrder);
			} else {
				rowNum = titanTransOrderDao.insert(titanTransOrder);
			}
			if (rowNum <= 0) {
				withDrawResponse.putErrorResult("保存交易单失败");
				return withDrawResponse;
			}
			//本地化提现信息
			TitanWithDrawReq titanWithDrawReq = saveTitanWithDraw(balanceWithDrawRequest, titanTransOrder.getTransid());
			if (null == titanWithDrawReq){
				withDrawResponse.putErrorResult("保存提现请求失败");
				return withDrawResponse;
			}
			//调用融数接口提现
			AccountWithDrawRequest accountWithDrawRequest = new AccountWithDrawRequest();
			accountWithDrawRequest.setUserid(balanceWithDrawRequest.getUserId());
			accountWithDrawRequest.setUserfee(Long.valueOf(NumberUtil.covertToCents(balanceWithDrawRequest.getReceivedfee())));
			accountWithDrawRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
			accountWithDrawRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			accountWithDrawRequest.setOrderdate(balanceWithDrawRequest.getOrderDate());
			accountWithDrawRequest.setCardno(balanceWithDrawRequest.getCardNo());
			accountWithDrawRequest.setUserorderid(balanceWithDrawRequest.getUserorderid());
			accountWithDrawRequest.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
			
			String amount = NumberUtil.covertToCents(balanceWithDrawRequest.getAmount());
			//将提现金额减去手续费
			if(accountWithDrawRequest.getUserfee() != null)
			{
				amount = String.valueOf((Long.parseLong(amount) - accountWithDrawRequest
								.getUserfee()));
			}
			
			accountWithDrawRequest.setAmount(amount);
			AccountWithDrawResponse accountWithDrawResponse = rsAccTradeManager.accountBalanceWithDraw(accountWithDrawRequest);
			if (accountWithDrawResponse != null) {
				if (CommonConstant.OPERATE_SUCCESS.equals(accountWithDrawResponse.getOperateStatus())) {
					withDrawResponse.putSuccess();
					withDrawResponse.setOperateStatus(true);
					titanWithDrawReq.setStatus(WithDrawStatusEnum.WithDraw_SUCCESSED.getKey());
					titanTransOrder.setStatusid(OrderStatusEnum.ORDER_SUCCESS.getStatus());
				} else {
					withDrawResponse.putErrorResult(accountWithDrawResponse.getReturnCode(), accountWithDrawResponse.getReturnMsg());
					titanTransOrder.setStatusid(OrderStatusEnum.ORDER_FAIL.getStatus());
					titanWithDrawReq.setStatus(WithDrawStatusEnum.WithDraw_FAILED.getKey());
					withDrawResponse.setOperateStatus(false);
				}
				//更新订单
				titanTransOrderDao.update(titanTransOrder);
				//更新提现信息
				titanWithDrawReqDao.update(titanWithDrawReq);
			}
		} catch (Exception e) {
			log.error("账户提现操作失败", e);
			withDrawResponse.putSysError();
		}
		return withDrawResponse;
	}
	
	//保存提现信息
	private TitanWithDrawReq saveTitanWithDraw(BalanceWithDrawRequest balanceWithDrawRequest, Integer transOrderId){
		try{
			TitanWithDrawReq titanWithDrawReq = convertToTitanWithDrawReq(balanceWithDrawRequest);
			titanWithDrawReq.setStatus(WithDrawStatusEnum.WithDraw_DOING.getKey());
			titanWithDrawReq.setTransorderid(transOrderId);
			BankCardBindInfoRequest bankCardBindInfoRequest = new BankCardBindInfoRequest();
			//查询是商户还是个人
			TitanOrgParam condition = new TitanOrgParam();
			condition.setUserid(balanceWithDrawRequest.getUserId());
			TitanOrg titanOrg = titanOrgDao.selectOne(condition);
			if(titanOrg !=null){
				bankCardBindInfoRequest.setUsertype(CommonConstant.PERSONAL);
				if(TitanOrgEnum.UserType.ENTERPRISE.getKey()==titanOrg.getUsertype()){
					bankCardBindInfoRequest.setUsertype(CommonConstant.ENTERPRISE);
				}
			}
			bankCardBindInfoRequest.setObjorlist(CommonConstant.ALLCARD);
			bankCardBindInfoRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
			bankCardBindInfoRequest.setProductid(balanceWithDrawRequest.getProductid());
			bankCardBindInfoRequest.setUserid(balanceWithDrawRequest.getUserId());
			QueryBankCardBindInfoResponse queryBankCardBindInfoResponse = titanFinancialBankCardService.getBankCardBindInfo(bankCardBindInfoRequest);
			List<BankCardInfoDTO> bankCardInfoDTOList = queryBankCardBindInfoResponse.getBankCardInfoDTOList();
			BankCardInfoDTO bindBankCard = null;
			if(bankCardInfoDTOList !=null && bankCardInfoDTOList.size()>0) {
				for(BankCardInfoDTO bankCardInfoDTO:bankCardInfoDTOList) {//未输入卡号则获取 提现卡或者提现结算一体卡
					if(bankCardInfoDTO.getAccountpurpose().equals(BankCardEnum.BankCardPurposeEnum.DEBIT_WITHDRAW_CARD.getKey()) ||
							bankCardInfoDTO.getAccountpurpose().equals(BankCardEnum.BankCardPurposeEnum.WITHDRAW_CARD.getKey())){
						bindBankCard = bankCardInfoDTO;
						break;
					}
				}
			}
			//验证传入卡号是否就是已绑定的卡号
			if (null != bindBankCard && bindBankCard.getAccount_number().equals(balanceWithDrawRequest.getCardNo())){
				titanWithDrawReq.setBankcode(bindBankCard.getAccount_number());
				titanWithDrawReq.setBankname(bindBankCard.getBankheadname());
			} else {
				return null;
			}
			int rowNum = titanWithDrawReqDao.insert(titanWithDrawReq);
			if (rowNum <= 0) {
				return null;
			}
			return titanWithDrawReq;
		} catch (Exception e) {
			log.error("封装并保存提现信息失败", e);
			return null;
		}
	}
	
	//是否可以提现
	private boolean isBalanceVaild(BalanceWithDrawRequest balanceWithDrawRequest) {
		AccountBalanceRequest balanceRequest = new AccountBalanceRequest();
		balanceRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
		balanceRequest.setUserid(balanceWithDrawRequest.getUserId());
		AccountBalanceResponse balanceResponse = this.queryAccountBalance(balanceRequest);
		if (!balanceResponse.isResult() || CollectionUtils.isEmpty(balanceResponse.getAccountBalance())) {
			return false;
		}
		AccountBalance accountBalance = balanceResponse.getAccountBalance().get(0);
		if (accountBalance != null && StringUtil.isValidString(accountBalance.getBalanceusable())) {
			BigDecimal withdrawMoney = new BigDecimal(balanceWithDrawRequest.getAmount());
			BigDecimal balanceUseable = new BigDecimal(accountBalance.getBalancesettle());
			if (balanceUseable.subtract(withdrawMoney).compareTo(BigDecimal.ZERO) > -1) {
				return true;
			}
		}
		return false;
	}

	private TitanTransOrder convertToTitanTransOrder(BalanceWithDrawRequest balanceWithDrawRequest) {
		TitanTransOrder titanTransOrder = new TitanTransOrder();
		
		if (balanceWithDrawRequest == null || balanceWithDrawRequest.getAmount() == null) {
			return null;
		}
		try{
			titanTransOrder.setAmount(Long.parseLong(NumberUtil.covertToCents(balanceWithDrawRequest.getAmount())));
			titanTransOrder.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
			titanTransOrder.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			titanTransOrder.setUserid(balanceWithDrawRequest.getUserId());
			titanTransOrder.setCreatetime(new Date());
			titanTransOrder.setUserorderid(OrderGenerateService.genSyncUserOrderId());
			titanTransOrder.setCreator(balanceWithDrawRequest.getCreator());
			titanTransOrder.setGoodsname("提现申请");
			titanTransOrder.setGoodsdetail("提现至银行卡：" + balanceWithDrawRequest.getBankName() + "("
					+ balanceWithDrawRequest.getCardNo() + ")");
			titanTransOrder.setOrdertime(new Date());
			titanTransOrder.setOrderdate(new Date());
			titanTransOrder.setTradeamount(titanTransOrder.getAmount());
			//设置费率
			titanTransOrder.setReceivablefee(Long.parseLong(NumberUtil.covertToCents(balanceWithDrawRequest.getReceivablefee())));
			titanTransOrder.setReceivedfee(Long.parseLong(NumberUtil.covertToCents(balanceWithDrawRequest.getReceivedfee())));
			titanTransOrder.setStandfee(Long.parseLong(NumberUtil.covertToCents(balanceWithDrawRequest.getStandfee())));
			//付款账户
			titanTransOrder.setPayermerchant(balanceWithDrawRequest.getUserId());
		} catch (Exception e) {
			log.error("构造提现交易单失败", e);
			return null;
		}
		return titanTransOrder;
	}
	

	
	private TitanWithDrawReq convertToTitanWithDrawReq(BalanceWithDrawRequest balanceWithDrawRequest) throws Exception{
		TitanWithDrawReq titanWithDrawReq =null;
		try{
			if(balanceWithDrawRequest !=null){
				titanWithDrawReq = new TitanWithDrawReq();
				if(balanceWithDrawRequest.getAmount() !=null){
					titanWithDrawReq.setAmount(Long.parseLong(NumberUtil.covertToCents(balanceWithDrawRequest.getAmount())));
				}
				titanWithDrawReq.setCreatetime(new Date());
				titanWithDrawReq.setOrderdate(balanceWithDrawRequest.getOrderDate());
				titanWithDrawReq.setUserid(balanceWithDrawRequest.getUserId());
				titanWithDrawReq.setUserfee(Long.parseLong(NumberUtil.covertToCents(balanceWithDrawRequest.getReceivedfee())));
				titanWithDrawReq.setCreator(balanceWithDrawRequest.getCreator());
				titanWithDrawReq.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
				titanWithDrawReq.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
				titanWithDrawReq.setUserorderid(balanceWithDrawRequest.getUserorderid());
			}
		}catch(Exception e){
			throw new Exception(e);
		}
		return titanWithDrawReq;
	}
	

	@Override
	public AccountHistoryResponse addAccountHistory2(AccountHistoryRequest accountHistoryRequest){

		AccountHistoryResponse accountHistoryResponse = new AccountHistoryResponse();
		try{
			if(null ==accountHistoryRequest || null==accountHistoryRequest.getAccountHistoryDTO()){
				accountHistoryResponse.putErrorResult("传入参数错误");
				return accountHistoryResponse;
			}
			
			TitanAccountHistoryParam condition = new TitanAccountHistoryParam();
			AccountHistoryDTO accountHistoryDTO = accountHistoryRequest.getAccountHistoryDTO();
			condition.setPayeeuserid(accountHistoryDTO.getPayeeuserid());
			condition.setPayeruserid(accountHistoryDTO.getPayeruserid());
			PaginationSupport<TitanAccountHistory> paginationSupport = new PaginationSupport<TitanAccountHistory>();
			titanAccountHistoryDao.selectForPage(condition, paginationSupport);  
			//如果数据了中没有收付款账户记录则插入一条数据，否则直接返回成功
			if(paginationSupport.getItemList() !=null && paginationSupport.getItemList().size()>0){
				accountHistoryResponse.putSuccess();
			}else{
				TitanAccountHistory titanAccountHistory = new TitanAccountHistory();
				MyBeanUtil.copyBeanProperties(titanAccountHistory, accountHistoryDTO);
				int row = titanAccountHistoryDao.insert(titanAccountHistory);
				if(row >0){
					accountHistoryResponse.putSuccess();
				}else{
					accountHistoryResponse.putSysError();
				}
			}			    
			
		}catch(Exception e){
			log.error("添加收款账户历史异常"+e.getMessage(),e);
			accountHistoryResponse.putSysError();
		}
		return accountHistoryResponse;
		
	}
	
	@Override
	public AccountHistoryResponse getAccountHistory(
			AccountHistoryRequest accountHistoryRequest) {
		AccountHistoryResponse accountHistoryResponse = new AccountHistoryResponse();
		try{
			if(accountHistoryRequest !=null && accountHistoryRequest.getAccountHistoryDTO()!=null){
				TitanAccountHistory titanAccountHistory = new TitanAccountHistory();
				MyBeanUtil.copyProperties(titanAccountHistory, accountHistoryRequest.getAccountHistoryDTO());
				TitanAccountHistoryParam condition = new TitanAccountHistoryParam();
				condition.setPayeeuserid(titanAccountHistory.getPayeeuserid());
				condition.setPayeruserid(titanAccountHistory.getPayeruserid());
				condition.setInaccountcode(titanAccountHistory.getInaccountcode());
				condition.setOutaccountcode(titanAccountHistory.getOutaccountcode());
				PaginationSupport<TitanAccountHistory> paginationSupport = new PaginationSupport<TitanAccountHistory>();
				titanAccountHistoryDao.selectForPage(condition, paginationSupport);
				if(paginationSupport !=null && paginationSupport.getItemList()!=null){
					List<AccountHistoryDTO> accountHistoryDTOList = new ArrayList<AccountHistoryDTO>();
					AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
					for(TitanAccountHistory tianAccountHis:paginationSupport.getItemList()){
					    MyBeanUtil.copyProperties(accountHistoryDTO, tianAccountHis);
					    accountHistoryDTOList.add(accountHistoryDTO);
					}
					accountHistoryResponse.setAccountHistoryDTOList(accountHistoryDTOList);
					accountHistoryResponse.putSuccess();
					return accountHistoryResponse;
				}
				
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		accountHistoryResponse.putSysError();
		return accountHistoryResponse;
	}
	
	
	

	@Override
	public AccountCheckResponse checkTitanCode(AccountCheckRequest accountCheckRequest) {
		AccountCheckResponse accountCheckResponse = new AccountCheckResponse();
		PaginationSupport<TitanOrg> paginationSupport = new PaginationSupport<TitanOrg>();
		accountCheckResponse.putErrorResult("对不起，您输入的账户不存在");
		if (accountCheckRequest != null) {
			TitanOrgParam condition = new TitanOrgParam();
			condition.setOrgname(accountCheckRequest.getOrgName());
			condition.setTitancode(accountCheckRequest.getTitanCode());
			condition.setStatusid(accountCheckRequest.getStatusId());
			try {
				titanOrgDao.selectForPage(condition, paginationSupport);
			} catch (Exception e) {
				accountCheckResponse.putErrorResult("查询账户信息失败");
				log.error("查询账户信息失败", e);
			}
			if (CollectionUtils.isNotEmpty(paginationSupport.getItemList())) {
				TitanOrg titanOrg = paginationSupport.getItemList().get(0);
				if (titanOrg != null) {
					accountCheckResponse.setUserid(titanOrg.getUserid());
					accountCheckResponse.putSuccess();
					accountCheckResponse.setCheckResult(true);
				}
			}
		} else {
			accountCheckResponse.putErrorResult("账户检查参数不正确");
		}
		return accountCheckResponse;
	}

    public TitanAccountDao getTitanAccountDao() {
        return titanAccountDao;
    }

    public void setTitanAccountDao(TitanAccountDao titanAccountDao) {
        this.titanAccountDao = titanAccountDao;
    }

	@Override
	public AccountHistoryResponse queryAccountHistory(
			AccountHistoryRequest accountHistoryRequest) {
		AccountHistoryResponse accountHistoryResponse = new AccountHistoryResponse();
		try{
			if(accountHistoryRequest !=null){
				accountHistoryResponse.setResult(true);
				List<AccountHistoryDTO> accHisList = titanAccountHistoryDao.queryAccountHistory(accountHistoryRequest.getAccountHistoryDTO());
				accountHistoryResponse.setAccountHistoryDTOList(accHisList);
			}
		}catch(Exception e){
			log.error("查询付款历史失败"+e.getMessage(),e);
		}
		return accountHistoryResponse;
	}

	@Override
	public int deleteAccountHistory(AccountHistoryDTO accountHistoryDTO) {
		try{
			if(StringUtil.isValidString(accountHistoryDTO.getPayeruserid()) &&
					StringUtil.isValidString(accountHistoryDTO.getInaccountcode())&&
					StringUtil.isValidString(accountHistoryDTO.getOutaccountcode())){
				return titanAccountHistoryDao.deleteAccountHistory(accountHistoryDTO);
			}
		}catch(Exception e){
			log.error("删除账户历史记录失败"+e.getMessage(),e);
		}
		return 0;
	}

	@Override
	public UnFreezeResponse queryUnFreezeData(UnFreezeRequest unFreezeRequest) {
		UnFreezeResponse unFreezeResponse = new UnFreezeResponse();
		unFreezeResponse.putSysError();
		try{
			List<FundFreezeDTO> fundFreezeDTOList = titanFundFreezereqDao.queryFundFreezeDTO(unFreezeRequest);
			if(fundFreezeDTOList !=null && fundFreezeDTOList.size()>0){
				unFreezeResponse.putSuccess();
				unFreezeResponse.setFundFreezeDTO(fundFreezeDTOList);
			}
		}catch(Exception e){
			log.error("查询解冻数据失败"+e.getMessage(),e);
		}
		return unFreezeResponse;
	}

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public int unFreezeOrder(int offset,int rows) {
    	UnFreezeRequest unFreezeRequest = new UnFreezeRequest();
    	unFreezeRequest.setOffset(offset);
    	unFreezeRequest.setRows(rows);
    	Date date = new Date();
    	String dateStr = DateUtil.sdf.format(date);
		try {
			unFreezeRequest.setUnFreezeDate(DateUtil.sdf.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		log.info("解冻时入参:"+JSONSerializer.toJSON(unFreezeRequest));
		UnFreezeResponse unFreezeResponse =  this.queryUnFreezeData(unFreezeRequest);
		System.out.println("**********************************************");
		System.out.println(Thread.currentThread().getName());
		System.out.println("**********************************************");
		log.info("解冻查询结果:"+JSONSerializer.toJSON(unFreezeResponse));
		if(unFreezeResponse.getFundFreezeDTO() !=null && unFreezeResponse.getFundFreezeDTO().size()>0){
			rows = unFreezeResponse.getFundFreezeDTO().size();
			//调用解冻操作
			UnFreeBalanceBatchRequest unFreeBalanceBatchRequest = new UnFreeBalanceBatchRequest();
			unFreeBalanceBatchRequest.setFundFreezeDTOList(unFreezeResponse.getFundFreezeDTO());
			this.unfreezeAccountBalanceBatch(unFreeBalanceBatchRequest);
			return rows;
		}
		return 0;
    }
	
	@Override
	public void unfreezeAccountBalanceBatch(
			UnFreeBalanceBatchRequest unFreezeBalanceBatchRequest) {
		if(unFreezeBalanceBatchRequest.getFundFreezeDTOList() !=null){
			for(FundFreezeDTO fundFreezeDTO :unFreezeBalanceBatchRequest.getFundFreezeDTOList()){
				BalanceUnFreezeRequest balanceUnFreezeRequest = convertBalanceUnFreezeRequest(fundFreezeDTO);
				if(balanceUnFreezeRequest !=null){
					try{
						log.info("调用融数解冻:"+JSONSerializer.toJSON(balanceUnFreezeRequest));
						BalanceUnFreezeResponse balanceUnFreezeResponse = rsAccTradeManager.unFreezeAccountBalance(balanceUnFreezeRequest);
						log.info("调用融数解冻结果:"+JSONSerializer.toJSON(balanceUnFreezeResponse));
						if(CommonConstant.OPERATE_SUCCESS.equals(balanceUnFreezeResponse.getOperateStatus())){
					    	//插入解冻记录
					    	TitanFundUnFreezereq titanFundUnFreezereq = covertToTitanFundUnFreezereq(fundFreezeDTO);
					    	try{
					    		titanFundUnFreezereqDao.insert(titanFundUnFreezereq);
					    	}catch(Exception e){
					    		OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(fundFreezeDTO.getOrderNo(), "解冻资金记录插入失败", OrderExceptionEnum.UNFREEZE_RECORD_INSERT, JSON.toJSONString(titanFundUnFreezereq));
		    	        		titanOrderService.saveOrderException(orderExceptionDTO);
					    	}
					    	
					    	//修改系统单号
					    	TitanTransOrder titanTransOrder = new TitanTransOrder();
					    	titanTransOrder.setStatusid(OrderStatusEnum.ORDER_SUCCESS.getStatus());
					    	titanTransOrder.setOrderid(fundFreezeDTO.getOrderNo());
					    	try{
					    		titanTransOrderDao.update(titanTransOrder);
					    	}catch(Exception e){
					    		OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(fundFreezeDTO.getOrderNo(), "解冻资金之后，修改订单失败", OrderExceptionEnum.TransOrder_update, JSON.toJSONString(titanTransOrder));
		    	        		titanOrderService.saveOrderException(orderExceptionDTO);
					    	}
					    	
					    }else{
					    	OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(fundFreezeDTO.getOrderNo(), "解冻失败", OrderExceptionEnum.UNFREEZE_INSERT, JSON.toJSONString(fundFreezeDTO));
	    	        		titanOrderService.saveOrderException(orderExceptionDTO);
					    }
					}catch(Exception e){
						OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(fundFreezeDTO.getOrderNo(), "解冻失败", OrderExceptionEnum.UNFREEZE_INSERT, JSON.toJSONString(fundFreezeDTO));
    	        		titanOrderService.saveOrderException(orderExceptionDTO);
					}
				}
			}
		}
	}
	
	private BalanceUnFreezeRequest convertBalanceUnFreezeRequest(FundFreezeDTO fundFreezeDTO){
		if(fundFreezeDTO !=null){
			BalanceUnFreezeRequest balanceUnFreezeRequest = new BalanceUnFreezeRequest();
			balanceUnFreezeRequest.setAuthcode(fundFreezeDTO.getAuthCode());
			if(fundFreezeDTO.getAmount() !=null){
				balanceUnFreezeRequest.setAmount(fundFreezeDTO.getAmount().toString());
			}
			balanceUnFreezeRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
			balanceUnFreezeRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			balanceUnFreezeRequest.setConditioncode(FreezeConditionCodeEnum.NO_ORDER.getKey());
			balanceUnFreezeRequest.setFrozenuserorderid(fundFreezeDTO.getRequestNo());
			balanceUnFreezeRequest.setRequestno(OrderGenerateService.genUnfreezeResquestNo());
			balanceUnFreezeRequest.setRequesttime(fundFreezeDTO.getRequestTime());
			balanceUnFreezeRequest.setUserid(fundFreezeDTO.getUserId());
			return balanceUnFreezeRequest;
		}
		return null;
	}
	
	private TitanFundUnFreezereq covertToTitanFundUnFreezereq(FundFreezeDTO fundFreezeDTO){
		if(fundFreezeDTO !=null){
			TitanFundUnFreezereq titanFundUnFreezereq = new TitanFundUnFreezereq();
		    titanFundUnFreezereq.setFundFreezereqid(fundFreezeDTO.getFreezereqId());
			titanFundUnFreezereq.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
			titanFundUnFreezereq.setConditioncode(FreezeConditionCodeEnum.NO_ORDER.getKey());
			titanFundUnFreezereq.setRequestno(fundFreezeDTO.getRequestNo());
			titanFundUnFreezereq.setRequesttime(fundFreezeDTO.getRequestTime());
			return titanFundUnFreezereq;
		}
		return null;
	}

	@Override
	public DefaultPayerConfigResponse getDefaultPayerConfig() {
		DefaultPayerConfigResponse response = new DefaultPayerConfigResponse();
		response.setUserId(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
		response.setProductId(RSInvokeConstant.DEFAULTPAYERCONFIG_PRODUCTID);
		response.putSuccess();
		return response;
	}

	
}
