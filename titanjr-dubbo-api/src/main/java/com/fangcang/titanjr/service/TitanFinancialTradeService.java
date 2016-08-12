package com.fangcang.titanjr.service;


import com.fangcang.titanjr.dto.bean.GDPOrderDTO;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;

/**
 * 金服交易相关服务
 * 交易工单，交易请求，交易流水等逻辑
 * 账户余额支付，资金冻结
 * Created by zhaoshan on 2016/4/21.
 */
public interface TitanFinancialTradeService {

	/**
	 * 落单操作,泰坦金服生成交易工单
	 * 融数交易工单操作接口
	 * @param orderRequest
	 * @return
	 * @author fangdaikang
	 */
	public TransOrderCreateResponse operateRSTransOrder(OrderRequest orderRequest);
	
	/**
	 * 本地生成订单
	 * @param localAddTransOrderRequest
	 * @return
	 */
	public LocalAddTransOrderResponse addLocalTransOrder(PaymentRequest paymentRequest,FinancialOrderResponse financialOrderResponse);
	
	/**
	 * 创建泰坦金服交易单接口
	 * 在调用支付接口之前判定调用融数落单
	 * 保存泰坦交易工单
	 * @param paymentRequest
	 * @return
	 * @author fangdaikang
	 */
	public TransOrderCreateResponse createTitanTransOrder(PaymentRequest paymentRequest) throws Exception;
	
	/**
	 * 在收银台支付页面点击确定按钮时，生成充值页面的操作
	 * @param rechargePageRequest
	 * @return RechargeResponse 
	 * @author fangdaikang
	 */
	public RechargeResponse generateRechargePage(RechargePageRequest rechargePageRequest) throws Exception;
	
	/**
	 * 用户进行充值操作(不需要财务单)
	 * @param rechargePageRequest
	 * @return
	 * @throws Exception
	 * @author fangdaikang
	 */
	public RechargeResponse generateOrderNoAndRechargePage(RechargePageRequest rechargePageRequest)throws Exception;


	/**
	 * 转账 将已经充入账户余额的资金转到对方账户
	 * @param transferRequest
	 * @return
	 * @throws Exception
	 */
	public TransferResponse transferAccounts(TransferRequest transferRequest) throws Exception;
	
	/**
	 * 查询财务工单信息
	 * 对调用financeSearchRemote进行财务工单查询的封装
	 * @param financialOrderRequest
	 * @return
	 * @author fangdaikang
	 */
	public FinancialOrderResponse queryFinanceOrderDetail(FinancialOrderRequest financialOrderRequest);
	
	/**
	 * 开通或关闭免密支付
	 * @param allowNoPwdPayRequest
	 * @return
	 * @author fangdaikang
	 */
	public AllowNoPwdPayResponse saveAllowNoPwdPay(AllowNoPwdPayRequest allowNoPwdPayRequest);
	
	/**
	 * 判断是否能免密支付
	 * @param judgeAllowNoPwdPayRequest
	 * @return
	 * @author fangdaikang
	 */
	public AllowNoPwdPayResponse isAllowNoPwdPay(JudgeAllowNoPwdPayRequest judgeAllowNoPwdPayRequest);
	
	
	/**
	 * 查询交易明细,以该账户为中心，查询其交易记录
	 * @param tradeDetailRequest
	 * @return
	 * @author fangdaikang
	 */
	public TradeDetailResponse getTradeDetail(TradeDetailRequest tradeDetailRequest);

	/**
	 * 根据订单号查询该订单的交易信息
	 * @param tradeDetailRequest
	 * @return
	 * @author fangdaikang
	 */
	public TradeDetailResponse getOrderTradeDetail(TradeDetailRequest tradeDetailRequest);
	
	/**
	 * 获取支付的url
	 * @param paymentUrlRequest
	 * @return
	 * @author fangdaikang
	 */
	public PaymentUrlResponse getPaymentUrl(PaymentUrlRequest paymentUrlRequest);
	
	
	/**
	 * 查询付款方式
	 * @param payMethodConfigRequest
	 * @return
	 * @author fangdaikang
	 */
	public PayMethodConfigDTO getPayMethodConfigDTO(PayMethodConfigRequest payMethodConfigRequest);

	/**
	 * 更新交易单
	 * @param transOrderUpdateRequest
	 * @return
	 */
	public TransOrderUpdateResponse updateTransOrder(TransOrderUpdateRequest transOrderUpdateRequest);
    
	/**
	 * 回调财务
	 * @param transferRequest
	 * @param transOrderDTO
	 * @param flag
	 * @return
	 * @author fangdaikang
	 */
	public void confirmFinance(TransOrderDTO transOrderDTO)throws Exception;
	
	/**
	 * 确认转账是否成功
	 * @param accountTransferRequest
	 * @return
	 * @throws Exception
	 */
	public boolean confirmTransAccountSuccess(AccountTransferFlowRequest accountTransferFlowRequest);
	
	/**
	 * 获取GDP的订单
	 * @param orderCode
	 * @return
	 */
	public GDPOrderResponse getGDPOrderDTO(String orderCode);
	
	/**
	 * 获取密文
	 * @param rechargeResultConfirmRequest
	 * @return
	 */
	public String getSign(RechargeResultConfirmRequest rechargeResultConfirmRequest);
	
	/**
	 * 获取订单
	 * @param repairTransferRequest
	 * @return
	 */
	public RepairTransferResponse getTransferOrders(RepairTransferRequest repairTransferRequest);
	
	/**
	 * 修复GDP转账失败的单
	 */
	public void repairTransferOrder();
	
}
