package com.fangcang.titanjr.service;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.CityInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;

/**
 * 账户相关服务，账户冻结，解冻，账户余额查询等
 * Created by zhaoshan on 2016/4/21.
 */
public interface TitanFinancialAccountService {

    /**
     * 创建用户的需求
     * @param accountCreateRequest
     * @return
     */
    public AccountCreateResponse createAccount(AccountCreateRequest accountCreateRequest);
    
    /**
     * 查询一个
     * @param accountRequest
     * @return
     */
    public AccountResponse getAccount(AccountRequest accountRequest);
    
    /**
     * 更新账户
     * @param accountUpdateRequest
     * @return
     */
    public AccountUpdateResponse updateAccount(AccountUpdateRequest accountUpdateRequest) throws GlobalServiceException;

    /**
     * 查询账户余额接口
     * @param accountBalanceRequest
     * @return
     */
    public AccountBalanceResponse queryAccountBalance(AccountBalanceRequest accountBalanceRequest);
    
    /**
	 * 冻结账户资金
	 * @param rechargeResultConfirmRequest
	 * @return
	 * @author fangdaikang
	 */
	public FreezeAccountBalanceResponse freezeAccountBalance(RechargeResultConfirmRequest rechargeResultConfirmRequest)throws Exception ;
	
	/**
	 * 解冻账户资金
	 * @param unFreezeAccountBalanceRequest
	 * @return
	 * @author fangdaikang
	 */
	public UnFreezeAccountBalanceResponse unfreezeAccountBalance(UnFreezeAccountBalanceRequest unFreezeAccountBalanceRequest);
	
	/**
	 * 查询账户明细
	 * @param AccountBalanceRequest
	 * @return
	 * @author fangdaikang
	 */
//	public AccountBalanceResponse queryAccountBalance(AccountBalanceRequest accountBalanceRequest);
	
	/**
	 * 账户提现
	 * @param balanceWithDrawRequest
	 * @return
	 * @author fangdaikang
	 */
	public BalanceWithDrawResponse accountBalanceWithdraw(BalanceWithDrawRequest balanceWithDrawRequest);
	
	/**
	 * 更新收付款历史记录
	 * @param accountHistoryRequest
	 * @return
	 * @author fangdaikang
	 */
	public AccountHistoryResponse addAccountHistory(AccountHistoryRequest accountHistoryRequest);
	
	/**
	 * 获取收付款历史
	 * @param accountHistoryRequest
	 * @return
	 * @author fangdaikang
	 */
	public AccountHistoryResponse getAccountHistory(AccountHistoryRequest accountHistoryRequest);
	
	
	/**
	 * 校验泰坦码和账户是否对应
	 * @param accountCheckRequest
	 * @return
	 * @author fangdaikang
	 */
	public AccountCheckResponse checkTitanCode(AccountCheckRequest accountCheckRequest);
	
	/**
	 * 获取收付款历史,泰坦码
	 * @param accountHistoryRequest
	 * @return
	 * @author fangdaikang
	 */
	public AccountHistoryResponse queryAccountHistory(AccountHistoryRequest accountHistoryRequest);
	
	/**
	 * 删除账户历史
	 * @param accountHistoryDTO
	 * @return
	 * @author fangdaikang
	 */
	public int deleteAccountHistory(AccountHistoryDTO accountHistoryDTO);
	
	/**
	 * 获取冻结的单
	 * @param unFreezeRequest
	 * @return
	 */
	public UnFreezeResponse queryUnFreezeData(UnFreezeRequest unFreezeRequest);
	
	/**
	 * 解冻所有交易订单
	 */
	 public int unFreezeOrder(int offset,int rows);
	
	/**
	 * 批量解冻
	 * @param unFreeBalanceBatchRequest
	 */
	public void  unfreezeAccountBalanceBatch (UnFreeBalanceBatchRequest unFreeBalanceBatchRequest);
	
	
	public boolean  unfreezeAccountBalanceOne (UnFreeBalanceBatchRequest unFreeBalanceBatchRequest);
	
	
	
	public DefaultPayerConfigResponse getDefaultPayerConfig();

	
	
	public CityInfosResponse getCityInfoList(CityInfoDTO cityInfo);
	
	
	/**
	 * 更新冻结订单
	 * @author jerry
	 * 
	 * @param payOrderNo 支付订单号
	 * @param operationType 操作类型 1、直接解冻  2、修改解冻日期
	 * @param uNFreezeDate 解冻日期
	 * @return BaseResponse
	 */
	public BaseResponseDTO updateFreezeOrder(UpdateFreezeOrderRequest updateFreezeOrderRequest);
	
	
	/**
	 * 订单解冻之后转账失败，重新冻结操作
	 * @author Jerry
	 * @date 2017年8月28日 上午11:54:07
	 * @param transOrderDTO
	 * @return 
	 */
	public BaseResponseDTO reFreezeOrder(TransOrderDTO transOrderDTO);
	
	
}
