package com.fangcang.titanjr.rs.manager;

import com.fangcang.titanjr.rs.request.AccountFlowQueryRequest;
import com.fangcang.titanjr.rs.request.AccountFreezeRequest;
import com.fangcang.titanjr.rs.request.AccountUnFreezeRequest;
import com.fangcang.titanjr.rs.request.DestoryAccountRequest;
import com.fangcang.titanjr.rs.response.AccountFlowQueryResponse;
import com.fangcang.titanjr.rs.response.AccountFreezeResponse;
import com.fangcang.titanjr.rs.response.AccountUnFreezeResponse;
import com.fangcang.titanjr.rs.response.DestoryAccountResponse;

/**
 * Created by zhaoshan on 2016/4/12.
 * 包括账户冻结、解冻、
 * 销户、
 * 帐户流水查询、
 */
public interface RSAccountManager {

    /**
     * 账户整体冻结接口，并非冻结账户的部分金额
     * @param accountFreezeRequest
     * @return
     */
    public AccountFreezeResponse freezeAccount(AccountFreezeRequest accountFreezeRequest);


    /**
     * 账户整体解冻接口
     * @param accountUnFreezeRequest
     * @return
     */
    public AccountUnFreezeResponse unFreezeAccount(AccountUnFreezeRequest accountUnFreezeRequest);

    /**
     * 销户操作，相当于删除账户的操作
     * @param destoryAccountRequest
     * @return
     */
    public DestoryAccountResponse destoryAccount(DestoryAccountRequest destoryAccountRequest);

    /**
     * 账户流水查询，只关联账户的进出账记录，不能标记进出账所属的订单
     * @param accountFlowQueryRequest
     * @return
     */
    public AccountFlowQueryResponse queryAccountFlow(AccountFlowQueryRequest accountFlowQueryRequest);
}
