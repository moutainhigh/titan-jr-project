package com.fangcang.titanjr.rs.manager;

import com.fangcang.titanjr.rs.request.AccountBalanceQueryRequest;
import com.fangcang.titanjr.rs.request.AccountTransferRequest;
import com.fangcang.titanjr.rs.request.AccountWithDrawRequest;
import com.fangcang.titanjr.rs.request.BalanceFreezeRequest;
import com.fangcang.titanjr.rs.request.BalanceUnFreezeRequest;
import com.fangcang.titanjr.rs.request.OrderOperateRequest;
import com.fangcang.titanjr.rs.request.OrdernQueryRequest;
import com.fangcang.titanjr.rs.request.RSRefundRequest;
import com.fangcang.titanjr.rs.response.AccountBalanceQueryResponse;
import com.fangcang.titanjr.rs.response.AccountTransferResponse;
import com.fangcang.titanjr.rs.response.AccountWithDrawResponse;
import com.fangcang.titanjr.rs.response.BalanceFreezeResponse;
import com.fangcang.titanjr.rs.response.BalanceUnFreezeResponse;
import com.fangcang.titanjr.rs.response.OrderOperateResponse;
import com.fangcang.titanjr.rs.response.OrdernQueryResponse;
import com.fangcang.titanjr.rs.response.RsRefundResponse;

/**
 * Created by zhaoshan on 2016/4/12.
 * 账户可用余额查询
 * 账户资金冻结、解冻、
 * 订单基础操作
 * 订单流水查询
 * 提现
 * 转账（单笔）
 */
public interface RSAccTradeManager {

	/**
	 * 上线新账户系统时，初始化同步融数的账户余额使用
	 * @param accountBalanceQueryRequest
	 * @return
	 */
	public AccountBalanceQueryResponse queryAccountBalanceWithRS(AccountBalanceQueryRequest accountBalanceQueryRequest);
	
    /**
     * 账户可用余额查询
     * 查询二级机构的账户可用余额
     * <b>有一个查询所有商户账户余额的接口ruixue.wheatfield.balance.getlist
     *  http://open.ruixuesoft.com/api/ApiMethod-dc39f74e-69ab-4b24-b6bf-f46a3a30763a.html
     *  我认为可以不接，待确认<b/>
     * @param accountBalanceQueryRequest
     * @return
     */
    public AccountBalanceQueryResponse queryAccountBalance(AccountBalanceQueryRequest accountBalanceQueryRequest);


    /**
     * 账户余额冻结接口
     * 担保支付时候使用
     * @param balanceFreezeRequest
     * @return
     */
    public BalanceFreezeResponse freezeAccountBalance(BalanceFreezeRequest balanceFreezeRequest);


    /**
     * 账户余额解冻接口，跟冻结相反
     * 担保支付时候使用，联盟商家付款
     * @param balanceUnFreezeRequest
     * @return
     */
    public BalanceUnFreezeResponse unFreezeAccountBalance(BalanceUnFreezeRequest balanceUnFreezeRequest);

    /**
     * 融数支付订单基础操作
     * =============查询订单时候，此接口返回结果需自行解析，比较麻烦===========
     * 包括新建订单，查询订单，修改订单等
     * 返回结果需重新考虑，跟接口文档不一致
     * @param orderOperateRequest
     * @return
     */
    public OrderOperateResponse operateOrder(OrderOperateRequest orderOperateRequest);

    /**
     * 查询订单交易流水，调用的接口是ruixue.wheatfield.order.service.multitransfer.query
     * 多次转账订单查询，单次转账也可以查
     * SDK中封装的请求参数 WheatfieldOrderServiceMultitransferQueryRequest
     * SDK中封装的返回结果 WheatfieldOrderServiceMultitransferQueryResponse TradeInfoResponse
     * 此接口旨在查询一张订单对应的交易流水，
     * @param orderTransferFlowRequest
     * @return
     */
//    public OrderTransferFlowResponse queryOrderTranferFlow(OrderTransferFlowRequest orderTransferFlowRequest);

    /**
     * 基本的交易，单笔转账操作接口
     * @param accountTransferRequest
     * @return
     */
    public AccountTransferResponse accountBalanceTransfer(AccountTransferRequest accountTransferRequest);

    /**
     * 账户提现接口
     * @param accountWithDrawRequest
     * @return
     */
    public AccountWithDrawResponse accountBalanceWithDraw(AccountWithDrawRequest accountWithDrawRequest);
    
    /**
     * 绑卡加下单的接口
     * @param orderSaveWithCardRequest
     * @return
     */
//    public OrderSaveWithCardResponse orderSaveWithdraw(OrderSaveWithCardRequest orderSaveWithCardRequest);

    
    /**
     * 下退款订单
     * @param refundRequest
     * @return
     */
    public RsRefundResponse addOrderRefund(RSRefundRequest refundRequest);
    

    /**
     * 订单状态查询
     * @param ordernQueryRequest
     * @return
     */
	public OrdernQueryResponse ordernQuery(OrdernQueryRequest ordernQueryRequest);

}
