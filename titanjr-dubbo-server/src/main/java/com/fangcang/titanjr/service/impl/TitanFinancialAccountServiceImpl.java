package com.fangcang.titanjr.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.fangcang.titanjr.common.enums.BusinessLog;
import com.fangcang.titanjr.common.enums.FreezeConditionCodeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TransOrderTypeEnum;
import com.fangcang.titanjr.common.enums.WithDrawStatusEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanAccountDao;
import com.fangcang.titanjr.dao.TitanAccountHistoryDao;
import com.fangcang.titanjr.dao.TitanBalanceInfoDao;
import com.fangcang.titanjr.dao.TitanCityInfoDao;
import com.fangcang.titanjr.dao.TitanFundFreezereqDao;
import com.fangcang.titanjr.dao.TitanFundUnFreezereqDao;
import com.fangcang.titanjr.dao.TitanOrgDao;
import com.fangcang.titanjr.dao.TitanOrgSubDao;
import com.fangcang.titanjr.dao.TitanTransOrderDao;
import com.fangcang.titanjr.dao.TitanTransferReqDao;
import com.fangcang.titanjr.dao.TitanVirtualOrgDao;
import com.fangcang.titanjr.dao.TitanWithDrawReqDao;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.AccountDTO;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.CityInfoDTO;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.request.AccountCreateRequest;
import com.fangcang.titanjr.dto.request.AccountHistoryRequest;
import com.fangcang.titanjr.dto.request.AccountRequest;
import com.fangcang.titanjr.dto.request.AccountUpdateRequest;
import com.fangcang.titanjr.dto.request.AddPayLogRequest;
import com.fangcang.titanjr.dto.request.BalanceInfoRequest;
import com.fangcang.titanjr.dto.request.BalanceWithDrawRequest;
import com.fangcang.titanjr.dto.request.FreezeAccountBalanceRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RecordRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.request.UnFreezeAccountBalanceRequest;
import com.fangcang.titanjr.dto.request.UnFreezeRequest;
import com.fangcang.titanjr.dto.request.UpdateFreezeOrderRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.AccountCreateResponse;
import com.fangcang.titanjr.dto.response.AccountHistoryResponse;
import com.fangcang.titanjr.dto.response.AccountResponse;
import com.fangcang.titanjr.dto.response.AccountUpdateResponse;
import com.fangcang.titanjr.dto.response.BalanceWithDrawResponse;
import com.fangcang.titanjr.dto.response.BankCardStatusResponse;
import com.fangcang.titanjr.dto.response.CityInfosResponse;
import com.fangcang.titanjr.dto.response.DefaultPayerConfigResponse;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.dto.response.UnFreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.UnFreezeResponse;
import com.fangcang.titanjr.entity.TitanAccount;
import com.fangcang.titanjr.entity.TitanAccountHistory;
import com.fangcang.titanjr.entity.TitanBalanceInfo;
import com.fangcang.titanjr.entity.TitanBankcard;
import com.fangcang.titanjr.entity.TitanFundFreezereq;
import com.fangcang.titanjr.entity.TitanFundUnFreezereq;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.TitanOrgSub;
import com.fangcang.titanjr.entity.TitanTransOrder;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.entity.TitanVirtualOrg;
import com.fangcang.titanjr.entity.TitanWithDrawReq;
import com.fangcang.titanjr.entity.parameter.TitanAccountHistoryParam;
import com.fangcang.titanjr.entity.parameter.TitanAccountParam;
import com.fangcang.titanjr.entity.parameter.TitanBalanceInfoParam;
import com.fangcang.titanjr.entity.parameter.TitanFundFreezereqParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgParam;
import com.fangcang.titanjr.entity.parameter.TitanTransferReqParam;
import com.fangcang.titanjr.entity.parameter.TitanWithDrawReqParam;
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
import com.fangcang.titanjr.service.AccountRecordService;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.titanjr.service.TitanCodeCenterService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

import net.sf.json.JSONSerializer;

/**
 * Created by zhaoshan on 2016/5/9.
 */
@Service("titanFinancialAccountService")
public class TitanFinancialAccountServiceImpl implements TitanFinancialAccountService {
   
	private static final Log log = LogFactory.getLog(TitanFinancialAccountServiceImpl.class);

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
    private TitanOrgSubDao titanOrgSubDao;
    
    @Resource
    private TitanVirtualOrgDao titanVirtualOrgDao;
    
    @Resource
    private TitanAccountHistoryDao titanAccountHistoryDao;
    
    @Resource
    private TitanOrderService titanOrderService;
    
    @Resource
    private TitanFinancialBankCardService titanFinancialBankCardService;
    
    @Resource
    private TitanFinancialUtilService titanFinancialUtilService;
    
    @Resource
    private TitanFinancialTradeService tradeService;
    
    @Resource
    private TitanCityInfoDao titanCityInfoDao;
    
    @Resource
    private TitanOrgDao orgdao;
    
    @Resource
    private TitanTransferReqDao transferReqDao;
    
    @Resource
    private TitanCodeCenterService codeCenterService;
    
    @Resource
	private BusinessLogService businessLogService;
    @Resource
    private TitanBalanceInfoDao balanceInfoDao;
    
    @Resource
    private AccountRecordService accountRecordService;
    
    
	/***
	 * 融数的账户是否在本地存在
	 * @param item 融数账户
	 * @param list 本地帐户
	 * @return
	 */
	private String localIsExist(BalanceInfo item,List<TitanBalanceInfo> list){
		for(TitanBalanceInfo banlance : list){
			if((banlance.getUserid().equals(item.getUserid()))&&(banlance.getProductid().equals(item.getProductid()))){
				return banlance.getAccountcode();
			}
		}
		return "";
	}

	@Override
	public BaseResponseDTO synBalanceInfo(BalanceInfoRequest balanceInfoRequest) {
		
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		if(!StringUtil.isValidString(balanceInfoRequest.getUserId())){
			responseDTO.putErrorResult("userid参数不能为空");
			log.error("同步余额账户,userid参数不能为空");
			return responseDTO;
		}
		//查出机构本地帐户
		TitanBalanceInfoParam param = new TitanBalanceInfoParam();
		param.setUserid(balanceInfoRequest.getUserId());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(param);
		
		AccountBalanceQueryRequest accountBalanceQueryRequest = new AccountBalanceQueryRequest();
        accountBalanceQueryRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
        accountBalanceQueryRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
        accountBalanceQueryRequest.setUserid(balanceInfoRequest.getUserId());
        AccountBalanceQueryResponse response = rsAccTradeManager.queryAccountBalance(accountBalanceQueryRequest);
        //从融数同步账户
		if(response.isSuccess()&&CollectionUtils.isNotEmpty(response.getBalanceInfoList())){
			for(BalanceInfo item :response.getBalanceInfoList()){
				String accountCode = localIsExist(item,balanceInfoList);
				if(StringUtil.isValidString(localIsExist(item,balanceInfoList))){
					//存在,则更新金额
					TitanBalanceInfo entity = new TitanBalanceInfo();
					entity.setAccountcode(accountCode);
					entity.setCreditamount(Long.parseLong(item.getBalancecredit()));
					entity.setSettleamount(Long.parseLong(item.getBalancesettle()));
					entity.setFrozonamount(Long.parseLong(item.getBalancefrozon()));
					entity.setOverlimit(Long.parseLong(item.getBalanceoverlimit()));
					entity.setUsablelimit(Long.parseLong(item.getBalanceusable()));
					entity.setTotalamount(Long.parseLong(item.getAmount()));
					balanceInfoDao.update(entity);
				}else{
					//不存在,则插入
					TitanBalanceInfo entity = new TitanBalanceInfo();
					entity.setAccountcode(codeCenterService.createTitanAccountCode());
					entity.setUserid(item.getUserid());
					entity.setProductid(item.getProductid());
					entity.setCurrency(1);//人民币
					entity.setCreditamount(Long.parseLong(item.getBalancecredit()));
					entity.setSettleamount(Long.parseLong(item.getBalancesettle()));
					entity.setFrozonamount(Long.parseLong(item.getBalancefrozon()));
					entity.setOverlimit(Long.parseLong(item.getBalanceoverlimit()));
					entity.setUsablelimit(Long.parseLong(item.getBalanceusable()));
					entity.setTotalamount(Long.parseLong(item.getAmount()));
					entity.setStatus(1);//1：正常,2:冻结中
					entity.setCreatetime(new Date());
					balanceInfoDao.insert(entity);
				}
			}
			responseDTO.putSuccess();
		}else{
			//不存在,则新建主账户
			TitanBalanceInfo entity = new TitanBalanceInfo();
			entity.setAccountcode(codeCenterService.createTitanAccountCode());
			entity.setUserid(balanceInfoRequest.getUserId());
			entity.setProductid(balanceInfoRequest.getProductId());
			entity.setCurrency(1);//人民币
			entity.setCreditamount(0L);
			entity.setSettleamount(0L);
			entity.setFrozonamount(0L);
			entity.setOverlimit(0L);
			entity.setUsablelimit(0L);
			entity.setTotalamount(0L);
			entity.setStatus(1);//1：正常,2:冻结中
			entity.setCreatetime(new Date());
			balanceInfoDao.insert(entity);
			
			log.info("该userid在融数那边不存在，请检查.userid:"+balanceInfoRequest.getUserId());
			responseDTO.putErrorResult("该账户不存在，请检查");
		}
		return responseDTO;
	}

	
	@Override
	public BaseResponseDTO addBalanceInfo(BalanceInfoRequest balanceInfoRequest) {
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		//不存在,则新建主账户
		TitanBalanceInfo entity = new TitanBalanceInfo();
		entity.setAccountcode(codeCenterService.createTitanAccountCode());
		entity.setUserid(balanceInfoRequest.getUserId());
		entity.setProductid(balanceInfoRequest.getProductId());
		entity.setCurrency(1);//人民币
		entity.setCreditamount(0L);
		entity.setSettleamount(0L);
		entity.setFrozonamount(0L);
		entity.setOverlimit(0L);
		entity.setUsablelimit(0L);
		entity.setTotalamount(0L);
		entity.setStatus(1);//1：正常,2:冻结中
		entity.setCreatetime(new Date());
		balanceInfoDao.insert(entity);
		responseDTO.putSuccess();
		return responseDTO;
	}

	@Override
	public void initAllBalanceInfo() {
		int page = 1;
		PaginationSupport<TitanOrg> paginationSupport = new PaginationSupport<TitanOrg>();
		paginationSupport.setPageSize(100);
		paginationSupport.setCurrentPage(page);
		paginationSupport.setOrderBy(" orgid ASC ");
		paginationSupport = orgdao.selectForPage(null, paginationSupport);
		log.info("开始同步org");
		while (paginationSupport.getTotalPage()>=page) {
			for(TitanOrg item : paginationSupport.getItemList()){
				BalanceInfoRequest balanceInfoRequest = new BalanceInfoRequest();
				balanceInfoRequest.setUserId(item.getUserid());
				synBalanceInfo(balanceInfoRequest);
			}
			page++;
			paginationSupport.setCurrentPage(page);
			paginationSupport = orgdao.selectForPage(null, paginationSupport);
			log.info("同步,总页数："+paginationSupport.getTotalPage()+","+page);
		}
		log.info("开始同步suborg");
		//suborg
		int spage = 1;
		PaginationSupport<TitanOrgSub> spaginationSupport = new PaginationSupport<TitanOrgSub>();
		spaginationSupport.setPageSize(100);
		spaginationSupport.setCurrentPage(page);
		spaginationSupport.setOrderBy(" orgsubid ASC ");
		spaginationSupport = titanOrgSubDao.selectForPage(null, spaginationSupport);
		
		while (spaginationSupport.getTotalPage()>=spage) {
			for(TitanOrgSub item : spaginationSupport.getItemList()){
				BalanceInfoRequest balanceInfoRequest = new BalanceInfoRequest();
				balanceInfoRequest.setUserId(item.getOrgcode());
				synBalanceInfo(balanceInfoRequest);
			}
			spage++;
			spaginationSupport.setCurrentPage(spage);
			spaginationSupport = titanOrgSubDao.selectForPage(null, spaginationSupport);
			log.info("同步,总页数："+spaginationSupport.getTotalPage()+","+spage);
		}
		log.info("开始同步vorg");
		//vorg
		
		int vpage = 1;
		PaginationSupport<TitanVirtualOrg> vpaginationSupport = new PaginationSupport<TitanVirtualOrg>();
		vpaginationSupport.setPageSize(100);
		vpaginationSupport.setCurrentPage(page);
		vpaginationSupport.setOrderBy(" id ASC ");
		vpaginationSupport = titanVirtualOrgDao.selectForPage(null, vpaginationSupport);
		
		while (vpaginationSupport.getTotalPage()>=vpage) {
			for(TitanVirtualOrg item : vpaginationSupport.getItemList()){
				BalanceInfoRequest balanceInfoRequest = new BalanceInfoRequest();
				balanceInfoRequest.setUserId(item.getOrgCode());
				synBalanceInfo(balanceInfoRequest);
			}
			vpage++;
			vpaginationSupport.setCurrentPage(vpage);
			vpaginationSupport = titanVirtualOrgDao.selectForPage(null, vpaginationSupport);
			log.info("同步,总页数："+vpaginationSupport.getTotalPage()+","+vpage);
		}
		
	}

	@Override
    public AccountCreateResponse createAccount(AccountCreateRequest accountCreateRequest) {
        AccountCreateResponse accountCreateResponse = new AccountCreateResponse();
        AccountBalanceQueryRequest accountBalanceQueryRequest = new AccountBalanceQueryRequest();
        accountBalanceQueryRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
        accountBalanceQueryRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
        accountBalanceQueryRequest.setUserid(accountCreateRequest.getUserid());
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
		if (rechargeResultConfirmRequest == null) { //添加参数校验
			log.error("parameter validation error");
			freezeAccountBalanceResponse.putParamError();
			return freezeAccountBalanceResponse;
		}
		try {
			FreezeAccountBalanceRequest freezeAccountBalanceRequest = convertToFreezeAccountBalanceRequest(rechargeResultConfirmRequest);
			BalanceFreezeRequest balanceFreezeRequest = convertTobalanceFreezeRequest(freezeAccountBalanceRequest);
			if (balanceFreezeRequest != null) {
				BalanceFreezeResponse balanceFreezeResponse = rsAccTradeManager.freezeAccountBalance(balanceFreezeRequest);
				if (balanceFreezeResponse != null) {
					if (CommonConstant.OPERATE_SUCCESS.equals(balanceFreezeResponse.getOperateStatus())) {
						freezeAccountBalanceResponse.setAuthcode(balanceFreezeResponse.getAuthcode());
						//将冻结的信息插入数据库
						if (rechargeResultConfirmRequest.getFreezereqId() == null) {
							TitanFundFreezereq titanFundFreezereq = convertToTitanFundFreezereq(freezeAccountBalanceRequest);
							if (titanFundFreezereq != null) {
								titanFundFreezereq.setAuthcode(balanceFreezeResponse.getAuthcode());
								int row = titanFundFreezereqDao.insert(titanFundFreezereq);
								if (row < 1) {
									log.error("插入冻结单失败");
									throw new Exception();
								}
							}
						} else {
							TitanFundFreezereq titanFundFreezereq = new TitanFundFreezereq();
							titanFundFreezereq.setFreezereqid(rechargeResultConfirmRequest.getFreezereqId());
							titanFundFreezereq.setRequestno(balanceFreezeRequest.getRequestno());
							titanFundFreezereq.setStatus(1);//1-冻结成功，2-已经解冻成功
							titanFundFreezereq.setAuthcode(balanceFreezeResponse.getAuthcode());
							int row = titanFundFreezereqDao.update(titanFundFreezereq);
							if (row < 1) {
								log.error("修改冻结单失败");
								throw new Exception();
							}
						}
						freezeAccountBalanceResponse.setFreezeSuccess(true);
						freezeAccountBalanceResponse.putSuccess();
						//冻结记账
						TransOrderRequest transOrderRequest = new TransOrderRequest();
						TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
						RecordRequest recordRequest = new RecordRequest();
						recordRequest.setTransOrderId(transOrderDTO.getTransid());
						recordRequest.setUserId(balanceFreezeRequest.getUserid());
						recordRequest.setProductId(balanceFreezeRequest.getProductid());
						recordRequest.setAmount(balanceFreezeRequest.getAmount());
						accountRecordService.freeze(recordRequest);
						return freezeAccountBalanceResponse;
					}
				}
				freezeAccountBalanceResponse.putErrorResult(balanceFreezeResponse.getReturnCode(), balanceFreezeResponse.getReturnMsg());
				return freezeAccountBalanceResponse;
			}
		} catch (Exception e) {
			log.error("冻结操作异常" + e.getMessage(), e);
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
	private FreezeAccountBalanceRequest convertToFreezeAccountBalanceRequest(RechargeResultConfirmRequest rechargeResultConfirmRequest) throws Exception {
		try {
			FreezeAccountBalanceRequest freezeAccountBalanceRequest = new FreezeAccountBalanceRequest();
			if (rechargeResultConfirmRequest.getPayAmount() != null) {
				freezeAccountBalanceRequest.setAmount(Long.parseLong(rechargeResultConfirmRequest.getPayAmount()));
			}
			freezeAccountBalanceRequest.setOrderno(rechargeResultConfirmRequest.getOrderNo());
			freezeAccountBalanceRequest.setBankcode(rechargeResultConfirmRequest.getBankCode());
			freezeAccountBalanceRequest.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
			freezeAccountBalanceRequest.setRequestno(OrderGenerateService.genFreeAccountBalanceRequestNo());
			freezeAccountBalanceRequest.setFunccode(CommonConstant.FUNCCODE);
			freezeAccountBalanceRequest.setUserfee((long) 0);
			freezeAccountBalanceRequest.setConditioncode(FreezeConditionCodeEnum.NO_ORDER);
			freezeAccountBalanceRequest.setRequesttime(new Date());
			freezeAccountBalanceRequest.setStatus(CommonConstant.FREEZE_STATUS);
			freezeAccountBalanceRequest.setUseripaddress(CommonConstant.USER_IPADDRESS);
			freezeAccountBalanceRequest.setUserid(rechargeResultConfirmRequest.getUserid());
			freezeAccountBalanceRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			freezeAccountBalanceRequest.setOrdercount(1);
			if (rechargeResultConfirmRequest.getOrderAmount() != null) {
				freezeAccountBalanceRequest.setOrderamount(Long.parseLong(rechargeResultConfirmRequest.getOrderAmount()));
			}
			//用orderNo获取订单
			freezeAccountBalanceRequest.setOrdercount(1);
			return freezeAccountBalanceRequest;
		} catch (Exception e) {
			log.error("转换冻结账户余额请求失败", e);
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
	private BalanceFreezeRequest convertTobalanceFreezeRequest(FreezeAccountBalanceRequest freezeAccountBalanceRequest) throws Exception {
		BalanceFreezeRequest balanceFreezeRequest = null;
		try {
			if (freezeAccountBalanceRequest != null) {
				balanceFreezeRequest = new BalanceFreezeRequest();
				balanceFreezeRequest.setAmount(freezeAccountBalanceRequest.getAmount());
				if (freezeAccountBalanceRequest.getConditioncode() != null) {
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
		} catch (Exception e) {
			log.error("转换冻结参数失败", e);
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
							//解冻记账
							TransOrderRequest transOrderRequest = new TransOrderRequest();
							TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
							RecordRequest recordRequest = new RecordRequest();
							recordRequest.setTransOrderId(transOrderDTO.getTransid());
							recordRequest.setUserId(balanceUnFreezeRequest.getUserid());
							recordRequest.setProductId(balanceUnFreezeRequest.getProductid());
							recordRequest.setAmount(Long.parseLong(balanceUnFreezeRequest.getAmount()));
							accountRecordService.freeze(recordRequest);
							
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
			paginationSupport.setOrderBy(" requesttime DESC ");
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
			log.error("提现时校验参数失败："+JSONSerializer.toJSON(balanceWithDrawRequest));
			withDrawResponse.putErrorResult("请求参数校验错误");
			return withDrawResponse;
		}
		//判断提现金额和可用余额，获取可用余额
		boolean flag = isBalanceVaild(balanceWithDrawRequest);
		if ( !flag ) {//提现落单，不需要在融数落单，只需要在本地落单
			log.error("提现金额不正确,参数："+JSONSerializer.toJSON(balanceWithDrawRequest));
			withDrawResponse.putErrorResult("提现金额不正确请核实后再次发起");
			return withDrawResponse;
		}
		
		try {
			//查询绑卡记录
			BankCardStatusResponse bankCardStatusResponse = titanFinancialBankCardService.getBankCardStatus(balanceWithDrawRequest.getUserId());
			TitanBankcard bankcard = bankCardStatusResponse.getBankcard();
			if(bankcard==null||(!BankCardEnum.BankCardStatusEnum.NORMAL.getKey().equals(bankcard.getStatus().toString()))){
				withDrawResponse.putErrorResult(bankCardStatusResponse.getOrgBankcardMsg());
				return withDrawResponse;
			}
			
			String vUserId = balanceWithDrawRequest.getUserId();//余额账户
			
			balanceWithDrawRequest.setCardNo(bankcard.getAccountnumber());//"其他卡"
			balanceWithDrawRequest.setBankName(bankcard.getBankheadname());
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
			
			titanTransOrder.setStatusid(OrderStatusEnum.ORDER_IN_PROCESS.getStatus());
			titanTransOrder.setTransordertype(TransOrderTypeEnum.WITHDRAW.type);
			
			int rowNum = 0;
			if (orderDTO != null) {
				//如果存在提现交易记录，则判断是否已经有提现记录，有则不允许重复提交提现申请，需要重新提交
				TitanWithDrawReqParam withDrawReqParam = new TitanWithDrawReqParam();
				withDrawReqParam.setTransorderid(orderDTO.getTransid());
				List<TitanWithDrawReq> withDrawReqList = titanWithDrawReqDao.queryList(withDrawReqParam);
				if(CollectionUtils.isNotEmpty(withDrawReqList)){
					log.error("提现记录重复，请重试。提现卡号："+bankcard.getAccountnumber()+",Transid："+orderDTO.getTransid());
					withDrawResponse.putErrorResult("提现记录重复，请重试");
					return withDrawResponse;
				}
				
				if(!StringUtil.isValidString(orderDTO.getOrderid())){
					titanTransOrder.setOrderid(OrderGenerateService.genLocalOrderNo());
				}
				rowNum = titanTransOrderDao.updateTitanTransOrderByTransId(titanTransOrder);
			} else {
				if(!StringUtil.isValidString(titanTransOrder.getOrderid())){
					titanTransOrder.setOrderid(OrderGenerateService.genLocalOrderNo());
				}
				rowNum = titanTransOrderDao.insert(titanTransOrder);
			}
			if (rowNum <= 0) {
				log.error("提现时，保存交易单失败，请求参数titanTransOrder："+Tools.gsonToString(titanTransOrder));
				withDrawResponse.putErrorResult("保存交易单失败");
				return withDrawResponse;
			}
			if(!vUserId.equals(bankcard.getUserid())){
				//转账
				TransferRequest transferRequest = new TransferRequest();
		    	transferRequest.setUserid(vUserId);										//转出的用户
		    	transferRequest.setRequestno(OrderGenerateService.genResquestNo());		//业务订单号
		    	transferRequest.setRequesttime(DateUtil.sdf4.format(new Date()));		//请求时间
		    	transferRequest.setAmount(NumberUtil.covertToCents(balanceWithDrawRequest.getAmount()));										//金额 必须是分
		    	transferRequest.setUserfee("0");									
		    	transferRequest.setUserrelateid(bankcard.getUserid());	                   //接收方用户Id
		    	transferRequest.setOrderid(titanTransOrder.getOrderid());//
				
		    	TransferResponse transferResponse = tradeService.transferAccounts(transferRequest);
		    	if(transferResponse != null && !transferResponse.isResult()){//转账失败
					log.error("提现时转账失败，订单号orderid:"+titanTransOrder.getOrderid()+",错误信息:"+Tools.gsonToString(transferResponse));
					TransOrderDTO transOrderReq = new TransOrderDTO();
					transOrderReq.setStatusid(OrderStatusEnum.ORDER_FAIL.getStatus());
					transOrderReq.setTransid(orderDTO.getTransid());
					boolean updateFlag = titanOrderService.updateTransOrder(transOrderReq);
					if(!updateFlag){
						log.error("提现时转账失转账后更新订单失败,订单号orderid:"+titanTransOrder.getOrderid());
						titanFinancialUtilService.saveOrderException(orderDTO.getPayorderno(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Balance_Pay_Update_TransOrder_Fail, JSONSerializer.toJSON(orderDTO).toString());
					}
					withDrawResponse.putErrorResult(TitanMsgCodeEnum.WITHDRAW_TRANSFER_FAIL);
					return withDrawResponse;
				}
				log.info("提现操作时转账成功,订单号orderid:"+titanTransOrder.getOrderid());
			}else{
				log.info("提现操作时，真实机构和虚拟机构orgcode相同，不需要转账，直接提现。orgcode:"+vUserId);
			}
			
			//本地提现记录
			balanceWithDrawRequest.setUserId(bankcard.getUserid());//提现到真实账户
			TitanWithDrawReq titanWithDrawReq = saveTitanWithDraw(balanceWithDrawRequest, titanTransOrder.getTransid());
			if (null == titanWithDrawReq){
				log.error("保存提现请求失败,参数balanceWithDrawRequest："+Tools.gsonToString(balanceWithDrawRequest)+",Transid:"+titanTransOrder.getTransid());
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
					//提现记账
					RecordRequest recordRequest = new RecordRequest();
					recordRequest.setUserId(accountWithDrawRequest.getUserid());
					recordRequest.setProductId(accountWithDrawRequest.getProductid());
					recordRequest.setTransOrderId(titanTransOrder.getTransid());
					recordRequest.setAmount(Long.parseLong(accountWithDrawRequest.getAmount()));
					recordRequest.setFee(accountWithDrawRequest.getUserfee());
					
					accountRecordService.withdraw(recordRequest);
					
				} else {//提现失败
					log.error("提现到银行卡失败,订单号orderid:"+orderDTO.getOrderid()+"，错误信息："+Tools.gsonToString(accountWithDrawResponse));
					withDrawResponse.putErrorResult(accountWithDrawResponse.getReturnCode(), accountWithDrawResponse.getReturnMsg());
					titanTransOrder.setStatusid(OrderStatusEnum.ORDER_FAIL.getStatus());
					titanWithDrawReq.setStatus(WithDrawStatusEnum.WithDraw_FAILED.getKey());
					withDrawResponse.setOperateStatus(false);
					//TODO 转账成功，提现失败，需要自动修复提现
					titanFinancialUtilService.saveOrderException(titanTransOrder.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.WithDraw_Transfer_WithDraw_Fail, accountWithDrawResponse.getReturnMsg());
				}
				//更新订单
				titanTransOrderDao.update(titanTransOrder);
				//更新提现信息
				titanWithDrawReqDao.update(titanWithDrawReq);
			}else{
				log.error("提现到银行卡失败,融数接口返回信息为空,订单号orderid:"+orderDTO.getOrderid());
			}
		} catch (Exception e) {
			log.error("账户提现操作失败", e);
			withDrawResponse.putSysError();
		}
		return withDrawResponse;
	}
	
	/**
	 * 向融数发起提现
	 */
	private BalanceWithDrawResponse pushWithDraw(String orderId){
		BalanceWithDrawResponse withDrawResponse = new BalanceWithDrawResponse();
		//1-校验该提现单本地状态是否已经成功
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setOrderid(orderId);
		TransOrderDTO orderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
		TitanTransferReqParam titanTransferReqParam = new TitanTransferReqParam();
		titanTransferReqParam.setTransferreqid(orderDTO.getTransid());
		List<TitanTransferReq> transferReqList = transferReqDao.queryTitanTransferReq(titanTransferReqParam);
		if(CollectionUtils.isEmpty(transferReqList)){
			log.info("无转账记录，不需要重新发起提现.提现交易订单号orderId："+orderId);
			return withDrawResponse;
		}
		TitanTransferReq titanTransferReq = transferReqList.get(0);
		if(!titanTransferReq.getStatus().equals("2")){//2:成功
			log.info("无成功的转账记录，不需要重新发起提现。提现交易订单orderId："+orderId+"，转账单号Transferreqid："+titanTransferReq.getTransferreqid());
			return withDrawResponse;
		}
		//提现转账已经成功,检查提现单状态
		PaginationSupport<TitanWithDrawReq> withDrawReqPage = new PaginationSupport<TitanWithDrawReq>();
		withDrawReqPage.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
		TitanWithDrawReqParam condition = new TitanWithDrawReqParam();
		condition.setTransorderid(orderDTO.getTransid());
		withDrawReqPage.setOrderBy(" createtime desc ");
		titanWithDrawReqDao.selectForPage(condition, withDrawReqPage);
		if(CollectionUtils.isEmpty(withDrawReqPage.getItemList())){
			log.info("数据库无提现记录，不需要重新发起提现。提现交易订单orderId："+orderId);
			return withDrawResponse;
		}
		TitanWithDrawReq titanWithDrawReq = withDrawReqPage.getItemList().get(0);
		if(titanWithDrawReq.getStatus()!=2){//不是提现失败的单，不处理
			log.info("不需要重新发起提现。提现交易订单orderId："+orderId+"，提现单号Withdrawreqid："+titanWithDrawReq.getWithdrawreqid());
			return withDrawResponse;
		}
		
		//保存本地提现记录
		BalanceWithDrawRequest balanceWithDrawRequest = new BalanceWithDrawRequest();
		balanceWithDrawRequest.setBankName(titanWithDrawReq.getBankname());
		balanceWithDrawRequest.setCardNo(titanWithDrawReq.getBankcode());
		balanceWithDrawRequest.setAmount(titanWithDrawReq.getAmount()+"");
		balanceWithDrawRequest.setReceivablefee(titanWithDrawReq.getUserfee()+"");
		balanceWithDrawRequest.setOrderDate(titanWithDrawReq.getOrderdate());
		balanceWithDrawRequest.setUserId(titanWithDrawReq.getUserid());
		balanceWithDrawRequest.setUserorderid(titanWithDrawReq.getUserorderid());
		
		TitanWithDrawReq newWithDrawReq = saveTitanWithDraw(balanceWithDrawRequest, orderDTO.getTransid());
		if (null == newWithDrawReq){
			log.error("保存提现记录失败,参数balanceWithDrawRequest："+Tools.gsonToString(balanceWithDrawRequest)+",Transid:"+orderDTO.getTransid());
			withDrawResponse.putErrorResult("保存提现记录失败");
			return withDrawResponse;
		}
		//2-调用融数接口提现
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
		//3-到融数提现
		TitanTransOrder titanTransOrder = new TitanTransOrder();
		titanTransOrder.setTransid(orderDTO.getTransid());
		AccountWithDrawResponse accountWithDrawResponse = rsAccTradeManager.accountBalanceWithDraw(accountWithDrawRequest);
		if (accountWithDrawResponse != null) {
			if (CommonConstant.OPERATE_SUCCESS.equals(accountWithDrawResponse.getOperateStatus())) {
				withDrawResponse.putSuccess("提现请求已经提交，请关注银行通知");
				withDrawResponse.setOperateStatus(true);
				newWithDrawReq.setStatus(WithDrawStatusEnum.WithDraw_SUCCESSED.getKey());
				titanTransOrder.setStatusid(OrderStatusEnum.ORDER_SUCCESS.getStatus());
			} else {//提现失败
				log.error("提现到银行卡失败,交易订单号orderid:"+orderDTO.getOrderid()+",提现单号:"+newWithDrawReq.getWithdrawreqid()+"，错误信息："+Tools.gsonToString(accountWithDrawResponse));
				withDrawResponse.putErrorResult(accountWithDrawResponse.getReturnCode(), accountWithDrawResponse.getReturnMsg());
				titanTransOrder.setStatusid(OrderStatusEnum.ORDER_FAIL.getStatus());
				newWithDrawReq.setStatus(WithDrawStatusEnum.WithDraw_FAILED.getKey());
				withDrawResponse.setOperateStatus(false);
			}
			titanTransOrderDao.update(titanTransOrder);
			titanWithDrawReqDao.update(newWithDrawReq);
		}else{
			log.error("提现到银行卡失败,错误信息："+Tools.gsonToString(accountWithDrawResponse));
			withDrawResponse.putErrorResult("请求失败");
		}
		return withDrawResponse;
		
	}
	
	//保存提现信息
	private TitanWithDrawReq saveTitanWithDraw(BalanceWithDrawRequest balanceWithDrawRequest, Integer transOrderId){
		try{
			TitanWithDrawReq titanWithDrawReq = convertToTitanWithDrawReq(balanceWithDrawRequest);
			titanWithDrawReq.setStatus(WithDrawStatusEnum.WithDraw_DOING.getKey());
			titanWithDrawReq.setTransorderid(transOrderId);
			titanWithDrawReq.setBankcode(balanceWithDrawRequest.getCardNo());
			titanWithDrawReq.setBankname(balanceWithDrawRequest.getBankName());
			int rowNum = titanWithDrawReqDao.insert(titanWithDrawReq);
			if (rowNum <= 0) {
				log.error("插入提现记录失败,参数titanWithDrawReq："+Tools.gsonToString(titanWithDrawReq));
				return null;
			}
			return titanWithDrawReq;
		} catch (Exception e) {
			log.error("本地封装并保存提现记录失败,参数transOrderId："+transOrderId, e);
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
	public AccountHistoryResponse addAccountHistory(AccountHistoryRequest accountHistoryRequest){

		AccountHistoryResponse accountHistoryResponse = new AccountHistoryResponse();
		try{
			if(null ==accountHistoryRequest || null==accountHistoryRequest.getAccountHistoryDTO()){
				accountHistoryResponse.putErrorResult("传入参数错误");
				return accountHistoryResponse;
			}
			
			TitanAccountHistoryParam condition = new TitanAccountHistoryParam();
			AccountHistoryDTO accountHistoryDTO = accountHistoryRequest.getAccountHistoryDTO();
			condition.setOutaccountcode(accountHistoryDTO.getOutaccountcode());
			condition.setInaccountcode(accountHistoryDTO.getInaccountcode());
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
			condition.setOrgName(accountCheckRequest.getOrgName());
			condition.setTitanCode(accountCheckRequest.getTitanCode());
			condition.setStatusId(accountCheckRequest.getStatusId());
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

	@Override
	public AccountHistoryResponse queryAccountHistory(AccountHistoryRequest accountHistoryRequest) {
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
		try {
			if (StringUtil.isValidString(accountHistoryDTO.getPayeruserid()) &&
					StringUtil.isValidString(accountHistoryDTO.getInaccountcode()) &&
					StringUtil.isValidString(accountHistoryDTO.getOutaccountcode())) {
				return titanAccountHistoryDao.deleteAccountHistory(accountHistoryDTO);
			}
		} catch (Exception e) {
			log.error("删除账户历史记录失败" + e.getMessage(), e);
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
				unFreezeResponse.putSuccess("冻结记录查询成功");
				unFreezeResponse.setFundFreezeDTO(fundFreezeDTOList);
			}else{
				unFreezeResponse.putSuccess("未查到冻结记录");
			}
		}catch(Exception e){
			log.error("冻结记录查询异常,参数："+Tools.gsonToString(unFreezeRequest),e);
			unFreezeResponse.putErrorResult("冻结记录查询异常");
		}
		return unFreezeResponse;
	}

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
	public int unFreezeOrder(int offset, int rows) {
		UnFreezeRequest unFreezeRequest = new UnFreezeRequest();
		unFreezeRequest.setOffset(offset);
		unFreezeRequest.setRows(rows);
		String today = DateUtil.sdf.format(new Date());
		try {
			unFreezeRequest.setUnFreezeDate(DateUtil.sdf.parse(today));
		} catch (ParseException e) {//不会发生
			log.error("解冻日期解析失败", e);
		}
		log.info("解冻时入参:" + JSONSerializer.toJSON(unFreezeRequest));
		UnFreezeResponse unFreezeResponse = this.queryUnFreezeData(unFreezeRequest);
		log.info("解冻查询结果:" + JSONSerializer.toJSON(unFreezeResponse));
		if (unFreezeResponse.getFundFreezeDTO() != null && unFreezeResponse.getFundFreezeDTO().size() > 0) {
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
	public void unfreezeAccountBalanceBatch(UnFreeBalanceBatchRequest unFreezeBalanceBatchRequest) {
		if (unFreezeBalanceBatchRequest.getFundFreezeDTOList() != null) {
			for (FundFreezeDTO fundFreezeDTO : unFreezeBalanceBatchRequest.getFundFreezeDTOList()) {
				this.fundUnFreezeWithFreezeOrder(fundFreezeDTO);
			}
		}
	}
	
	@Override
	public boolean unfreezeAccountBalanceOne(UnFreeBalanceBatchRequest unFreezeBalanceBatchRequest) {
		List<FundFreezeDTO> fundFreezeList = unFreezeBalanceBatchRequest.getFundFreezeDTOList();
		if (fundFreezeList == null || fundFreezeList.size() != 1) {
			log.error("解冻参数不正确");
			return false;
		}
		return fundUnFreezeWithFreezeOrder(fundFreezeList.get(0));
	}
	
	//根据冻结订单解冻账户资金
	private boolean fundUnFreezeWithFreezeOrder(FundFreezeDTO fundFreezeDTO ){
		if(fundFreezeDTO.getStatus()==2){//已经解冻
			log.info("该冻结记录状态已经成功解冻，无需重复解冻,解冻记录:"+Tools.gsonToString(fundFreezeDTO));
			return true;
		}
		BalanceUnFreezeRequest balanceUnFreezeRequest = convertBalanceUnFreezeRequest(fundFreezeDTO);
		if(balanceUnFreezeRequest !=null){
			try{
				BalanceUnFreezeResponse balanceUnFreezeResponse = rsAccTradeManager.unFreezeAccountBalance(balanceUnFreezeRequest);
				log.info("调用融数解冻,请求参数："+Tools.gsonToString(balanceUnFreezeRequest)+",响应结果:"+Tools.gsonToString(balanceUnFreezeResponse));
				if(CommonConstant.OPERATE_SUCCESS.equals(balanceUnFreezeResponse.getOperateStatus())){
			    	//解冻记账
					TransOrderRequest transOrderRequest = new TransOrderRequest();
					TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
					RecordRequest recordRequest = new RecordRequest();
					recordRequest.setTransOrderId(transOrderDTO.getTransid());
					recordRequest.setUserId(balanceUnFreezeRequest.getUserid());
					recordRequest.setProductId(balanceUnFreezeRequest.getProductid());
					recordRequest.setAmount(Long.parseLong(balanceUnFreezeRequest.getAmount()));
					accountRecordService.freeze(recordRequest);
					
					//插入解冻记录
			    	TitanFundUnFreezereq titanFundUnFreezereq = covertToTitanFundUnFreezereq(fundFreezeDTO);
			    	try{
			    		TitanFundFreezereq fundFreezereq = new TitanFundFreezereq();
			    		fundFreezereq.setFreezereqid(fundFreezeDTO.getFreezereqId());
			    		fundFreezereq.setStatus(2);//解冻成功
			    		titanFundFreezereqDao.update(fundFreezereq);
			    		titanFundUnFreezereqDao.insert(titanFundUnFreezereq);
			    	}catch(Exception e){
			    		log.error("插入解冻记录失败，解冻参数:"+Tools.gsonToString(balanceUnFreezeRequest)+",解冻响应结果:"+Tools.gsonToString(balanceUnFreezeResponse));
			    		titanFinancialUtilService.saveOrderException(fundFreezeDTO.getOrderNo(), OrderKindEnum.OrderId,OrderExceptionEnum.UnFreeze_Insert_Order_Fail, JSONSerializer.toJSON(titanFundUnFreezereq).toString());
    	        		return false;
			    	}
			    	
			    	businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.UnfreezeSucc, OrderKindEnum.OrderId, fundFreezeDTO.getOrderNo()));
			    	//修改系统单号
			    	TitanTransOrder titanTransOrder = new TitanTransOrder();
			    	if(fundFreezeDTO.getOrderStatusEnum() == OrderStatusEnum.ORDER_FAIL){
			    		titanTransOrder.setStatusid(fundFreezeDTO.getOrderStatusEnum().getStatus());
			    	}else{
			    		titanTransOrder.setStatusid(OrderStatusEnum.ORDER_SUCCESS.getStatus());
			    	}
			    	titanTransOrder.setOrderid(fundFreezeDTO.getOrderNo());
			    	try{
			    		titanTransOrderDao.update(titanTransOrder);
			    	}catch(Exception e){
			    		log.error("解冻修改订单失败,请求参数："+Tools.gsonToString(balanceUnFreezeRequest)+",响应结果:"+Tools.gsonToString(balanceUnFreezeResponse));
			    		titanFinancialUtilService.saveOrderException(fundFreezeDTO.getOrderNo(),OrderKindEnum.OrderId,OrderExceptionEnum.UnFreeze_Update_Order_Fail,JSONSerializer.toJSON(titanTransOrder).toString());
    	        		return false;
			    	}
			    	
			    }else{
			    	log.error("调用融数解冻失败,请求参数："+Tools.gsonToString(balanceUnFreezeRequest)+",响应结果:"+Tools.gsonToString(balanceUnFreezeResponse));
			    	titanFinancialUtilService.saveOrderException(fundFreezeDTO.getOrderNo(),OrderKindEnum.OrderId,OrderExceptionEnum.UnFreeze_RS_Fail,JSONSerializer.toJSON(fundFreezeDTO).toString());
	        		return false;
			    }
			}catch(Exception e){
				log.error("调用融数解冻异常,请求参数："+Tools.gsonToString(balanceUnFreezeRequest));
				titanFinancialUtilService.saveOrderException(fundFreezeDTO.getOrderNo(),OrderKindEnum.OrderId,OrderExceptionEnum.UnFreeze_Fail,JSONSerializer.toJSON(fundFreezeDTO).toString());
        		return false;
			}
		}
		return true;
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

	@Override
	public CityInfosResponse getCityInfoList(CityInfoDTO cityInfo) {
		CityInfosResponse response = new CityInfosResponse();
		try{
			List<CityInfoDTO> cityInfoList = titanCityInfoDao.getCityInfoList(cityInfo);
			response.setCityInfoDTOList(cityInfoList);
			response.putSuccess();
		}catch(Exception e){
			log.error("查询城市信息失败",e);
			response.putSysError();
		}
		return response;
	}
	
	
	@Override
	public BaseResponseDTO updateFreezeOrder(UpdateFreezeOrderRequest updateFreezeOrderRequest) {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			//获取金融订单
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setPayorderno(updateFreezeOrderRequest.getPayOrderNo());
			transOrderRequest.setPayertype(updateFreezeOrderRequest.getPayerType());
			TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
			if(transOrderDTO == null){
				log.error("查询金融订单失败 transOrderDTO==null");
				baseResponseDTO.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
				return baseResponseDTO;
			}
			log.info("金融订单 transOrderDTO 状态为：" + transOrderDTO.getStatusid());
			
			//冻结成功进入下一步
			if (OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())) {
				if (!StringUtil.isValidString(transOrderDTO.getOrderid())) {
					log.error("金融订单orderid为空");
					baseResponseDTO.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
					return baseResponseDTO;
				}
				//操作类型 1、直接解冻  2、修改解冻日期
				if("1".equals(updateFreezeOrderRequest.getOperationType())){
					
					log.info("进行订单资金解冻,orderid:"+transOrderDTO.getOrderid());
					FundFreezeDTO fundFreezeDTO = new FundFreezeDTO();
					fundFreezeDTO.setOrderNo(transOrderDTO.getOrderid());
					List<FundFreezeDTO> fundFreezeDTOList = titanOrderService.queryFundFreezeDTO(fundFreezeDTO);
					if (CollectionUtils.isEmpty(fundFreezeDTOList)) {
						log.error("冻结单查询失败,orderid:"+transOrderDTO.getOrderid());
						baseResponseDTO.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
						return baseResponseDTO;
					}
					log.info("查询到解冻订单" + fundFreezeDTOList.size() + "条记录,orderid:"+transOrderDTO.getOrderid());
					
					boolean flag = fundUnFreezeWithFreezeOrder(fundFreezeDTOList.get(0));
					
					if(!flag){
						baseResponseDTO.putErrorResult(TitanMsgCodeEnum.REFUND_UNFREEZE_FAIL);
						return baseResponseDTO;
					}
					log.info("订单资金解冻成功,orderid:"+transOrderDTO.getOrderid());
					baseResponseDTO.putSuccess("解冻成功");
					
				}else if("2".equals(updateFreezeOrderRequest.getOperationType())){
					
					if(!StringUtil.isValidString(updateFreezeOrderRequest.getuNFreezeDate())){
						log.error("解冻日期不能为空,orderid:"+transOrderDTO.getOrderid());
						baseResponseDTO.putErrorResult("uNFreezeDate is null");
						return baseResponseDTO;
					}
					log.info("开始修改订单解冻日期");
					TransOrderDTO updateTransOrderDTO = new TransOrderDTO();
					updateTransOrderDTO.setOrderid(transOrderDTO.getOrderid());
					updateTransOrderDTO.setEscrowedDate(DateUtil.StringToDate(updateFreezeOrderRequest
							.getuNFreezeDate(), "yyyy-MM-dd"));
					boolean flag = titanOrderService.updateTransOrder(updateTransOrderDTO);
					
					if(!flag){
						log.error("更新订单解冻日期失败，零条记录被更新");
						baseResponseDTO.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
						return baseResponseDTO;
					}
					log.info("订单修改解冻日期成功，解冻日期修改为：" + updateFreezeOrderRequest.getuNFreezeDate()+",orderid:"+transOrderDTO.getOrderid());
					baseResponseDTO.putSuccess("订单修改解冻日期成功");
					
				}else{
					log.error("操作类型参数不匹配,orderid:"+transOrderDTO.getOrderid());
					baseResponseDTO.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		    		return baseResponseDTO;
				}
			}
			
		} catch (Exception e) {
			log.error("解冻异常,请求参数updateFreezeOrderRequest:"+Tools.gsonToString(updateFreezeOrderRequest), e);
			baseResponseDTO.putErrorResult("解冻异常");
		}
		return baseResponseDTO;
	}
	
	
	@Override
	public BaseResponseDTO reFreezeOrder(TransOrderDTO transOrderDTO) {
		
		log.info("开始重新冻结订单");
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		
		//判断该解冻是否成功
		List<FundFreezeDTO> fundFreezeDTOList = titanFundFreezereqDao.queryFundFreezeDTOByOrderNo(transOrderDTO.getOrderid());
		if(fundFreezeDTOList ==null || fundFreezeDTOList.size()<1){
			log.error("没有冻结或者解冻记录");
			baseResponseDTO.putErrorResult("没有冻结或者解冻记录");
			return baseResponseDTO;
		}
		
		FundFreezeDTO fundFreezeDTO = fundFreezeDTOList.get(0);
		RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
		rechargeResultConfirmRequest.setPayAmount(String.valueOf(transOrderDTO.getTradeamount()));
		rechargeResultConfirmRequest.setUserid(transOrderDTO.getUserid());
		rechargeResultConfirmRequest.setOrderNo(transOrderDTO.getOrderid());
		rechargeResultConfirmRequest.setFreezereqId(fundFreezeDTO.getFreezereqId());
		try {
			FreezeAccountBalanceResponse freezeAccountBalanceResponse = this.freezeAccountBalance(rechargeResultConfirmRequest);
		    if(!freezeAccountBalanceResponse.isFreezeSuccess()){
		    	log.error("重新冻结失败 ：" + JSONSerializer.toJSON(freezeAccountBalanceResponse));
		    	titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.AccountReceive_ReFreezePayer_Fail, JSONSerializer.toJSON(rechargeResultConfirmRequest).toString());
		    	baseResponseDTO.putErrorResult("重新冻结失败：" + freezeAccountBalanceResponse.getReturnMessage());
		    	return baseResponseDTO;
		    }
		} catch (Exception e) {
			log.error("重新冻结异常",e);
			baseResponseDTO.putErrorResult("重新冻结异常");
			return baseResponseDTO; 
		}
		
		log.info("重新冻结订单成功");
		baseResponseDTO.putSuccess();
		return baseResponseDTO;
	}


	public TitanAccountDao getTitanAccountDao() {
		return titanAccountDao;
	}

	public void setTitanAccountDao(TitanAccountDao titanAccountDao) {
		this.titanAccountDao = titanAccountDao;
	}
}
