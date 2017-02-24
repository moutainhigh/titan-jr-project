package com.fangcang.titanjr.service;


import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
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
	 * 本地生成订单。如果支付成功则直接返回，如果支付单没成功，
	 * 则修改支付单的orderid为本地落单（本地落单是相对于融数来说的，
	 * 本地单于融数单号的区别在于orderid前面加了一个L字符）
	 * @param localAddTransOrderRequest
	 * 优化建议：放到titanpay的service里面。
	 * 
	 * @return
	 */
	public LocalAddTransOrderResponse addLocalTransOrder(TitanPaymentRequest titanPaymentRequest);
	
	/**
	 * 融数落单，步骤
	 * 1.判断该单是否存在，以及是否支付成功，如果不存在或者该支付单已支付成功，直接结束
	 * 2.判断该单是否在融数已落单，如果未落单但是已经落了本地单则直接废单，重新生成，如果未落本地单，则直接融数落单并保存融数返回单号
	 * 3.如果已落单判断该单是否已经做了充值操作，如果已经做了充值操作则不允许再次到融数落单（在融数落单的意义在于下一个充值的单）
	 * 4.判断订单是否需要重新落单，3.1 如果订单金额金额发生改变，需要重新落单，换银行也需要重新落单，民生银行的客户号改变也需要,废除原单号并重新落单
	 * 订单落单时间超过45分钟也需要重新落单。如果该订单在45分钟之内，且未出现上述情况，则返回原单号，结束
	 * 5.如4中情况需要废除单，并落新单(落新单的过程中会保存一张充值单)，则需要重新生成userOrderid(每次请求融数，需要新的userOrderId),根据原单的部分信息保存新单,
	 * 6返回新单号，结束
	 * @param paymentRequest
	 * @return
	 */
	public TransOrderCreateResponse createRsOrder(TitanPaymentRequest titanPaymentRequest);
	
	/**
	 * 调用融数网关之前需要组装充值参数。
	 * @param rechargePageRequest
	 * @return RechargeResponse 
	 * @author fangdaikang
	 * 待redis引进之后，提到titanpay的service
	 */
	public RechargeResponse packageRechargeData( RechargeRequest rechargeRequest);
	
	/**
	 * 转账 
	 * 1.确认该转帐单对应了订单，并查询出payOrderNo 
	 * 2.对单号进行加锁
	 * 3.保存转帐单，转帐，转帐成功，更新转帐单，转帐失败，重新查询转帐单，看是否成功
	 * 4.重新确认转帐也失败，则更新转帐单为失败。
	 * 5.解锁，并返回
	 * @param transferRequest
	 * @return
	 * @throws Exception
	 */
	public TransferResponse transferAccounts(TransferRequest transferRequest) throws Exception;
	
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
	 * @author fangdaikang，两个方法的命名，看能不能合并
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
	 * 更新交易单
	 * @param transOrderUpdateRequest
	 * @return
	 */
	public TransOrderUpdateResponse updateTransOrder(TransOrderUpdateRequest transOrderUpdateRequest);
    
	/**
	 * 回调对接的机构
	 * @param transferRequest
	 * @param transOrderDTO
	 * @param flag
	 * @return
	 * @author fangdaikang
	 * 把notify拆分为一个service
	 */
	public void confirmFinance(TransOrderDTO transOrderDTO)throws Exception;
	
	
	/**
	 * 获取订单
	 * @param repairTransferRequest
	 * @return
	 * 名称要通用一些。在此处不应该提供该方法
	 */
	public RepairTransferResponse getTransferOrders(RepairTransferRequest repairTransferRequest);
	
	/**
	 * 修复GDP转账失败的单
	 * 一次处理一个单，传一个单号
	 */
	public void repairTransferOrder(RepairTransferRequest repairTransferRequest);
	
	/**
	 * 下单
	 * @param titanOrderRequest
	 * @return
	 */
	public TransOrderCreateResponse saveTitanTransOrder(TitanOrderRequest titanOrderRequest);
	
	/**
	 * 获取扫码支付的地址
	 * @param rechargeDataDTO
	 * @return
	 */
	public QrCodeResponse getQrCodeUrl(RechargeDataDTO rechargeDataDTO );
	
	/**
	 * 下单并绑卡
	 * @param request
	 * @return
	 */
	public  OrderSaveAndBindCardResponse saveTransOrderAndBindCard(OrderSaveAndBindCardRequest request);
	
	/**
	* 确认订单支付成功
	 * @param ordernQueryRequest
	 * @return
	 */
	public ConfirmOrdernQueryResponse ordernQuery(ConfirmOrdernQueryRequest ordernQueryRequest);	
	
}
