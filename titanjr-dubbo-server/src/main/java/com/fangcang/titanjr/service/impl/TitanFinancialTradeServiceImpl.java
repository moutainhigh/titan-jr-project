package com.fangcang.titanjr.service.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import com.Rop.api.domain.Refund;
import com.alibaba.fastjson.JSON;
import com.fangcang.order.api.HotelOrderSearchFacade;
import com.fangcang.order.dto.OrderDetailResponseDTO;
import com.fangcang.titanjr.common.bean.CallBackInfo;
import com.fangcang.titanjr.common.enums.*;
import com.fangcang.titanjr.common.util.*;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.common.util.httpclient.TitanjrHttpTools;
import com.fangcang.titanjr.dao.DomainConfigDao;
import com.fangcang.titanjr.dao.TitanAccountDao;
import com.fangcang.titanjr.dao.TitanDynamicKeyDao;
import com.fangcang.titanjr.dao.TitanOrderPayreqDao;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.dao.TitanTransOrderDao;
import com.fangcang.titanjr.dao.TitanTransferReqDao;
import com.fangcang.titanjr.dao.TitanUserDao;
import com.fangcang.titanjr.dto.bean.CashierItemBankDTO;
import com.fangcang.titanjr.dto.bean.GDPOrderDTO;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.enums.*;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.QrCodeDTO;
import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.bean.RepairTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TitanWithDrawDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.bean.TransOrderInfo;
import com.fangcang.titanjr.entity.TitanDynamicKey;
import com.fangcang.titanjr.entity.TitanOrderPayreq;
import com.fangcang.titanjr.entity.TitanTransOrder;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.entity.parameter.TitanAccountParam;
import com.fangcang.titanjr.entity.parameter.TitanOrderPayreqParam;
import com.fangcang.titanjr.entity.parameter.TitanTransOrderParam;
import com.fangcang.titanjr.entity.parameter.TitanTransferReqParam;
import com.fangcang.titanjr.rs.dto.Transorderinfo;
import com.fangcang.titanjr.rs.manager.RSAccTradeManager;
import com.fangcang.titanjr.rs.manager.RSPayOrderManager;
import com.fangcang.titanjr.rs.request.AccountTransferRequest;
import com.fangcang.titanjr.rs.request.OrderOperateRequest;
import com.fangcang.titanjr.rs.request.OrderSaveWithCardRequest;
import com.fangcang.titanjr.rs.request.OrderTransferFlowRequest;
import com.fangcang.titanjr.rs.request.OrdernQueryRequest;
import com.fangcang.titanjr.rs.request.RSPayOrderRequest;
import com.fangcang.titanjr.rs.response.AccountTransferResponse;
import com.fangcang.titanjr.rs.response.OrderOperateInfo;
import com.fangcang.titanjr.rs.response.OrderOperateResponse;
import com.fangcang.titanjr.rs.response.OrderSaveWithCardResponse;
import com.fangcang.titanjr.rs.response.OrderTransferFlowResponse;
import com.fangcang.titanjr.rs.response.OrdernQueryResponse;
import com.fangcang.titanjr.rs.response.RSPayOrderResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;
import com.fangcang.titanjr.entity.TitanAccount;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("titanFinancialTradeService")
public class TitanFinancialTradeServiceImpl implements TitanFinancialTradeService {

    private static final Log log = LogFactory.getLog(TitanFinancialTradeServiceImpl.class);

    @Resource
    RSAccTradeManager rsAccTradeManager;

    @Resource
    RSPayOrderManager rsPayOrderManager;

    @Resource
    TitanFinancialTradeService titanFinancialTradeService;

    @Resource
    private HessianProxyBeanFactory hessianProxyBeanFactory;

    @Resource
    private TitanTransOrderDao titanTransOrderDao;

    @Resource
    private TitanOrderPayreqDao titanOrderPayreqDao;

    @Resource
    private TitanOrderService titanOrderService;

    @Resource
    private TitanTransferReqDao titanTransferReqDao;

    @Resource
    private TitanAccountDao titanAccountDao;

	@Resource
	private TitanUserDao titanUserDao;

	@Resource
	private TitanDynamicKeyDao titanDynamicKeyDao;

	@Resource
	private TitanCashierDeskService titanCashierDeskService;

	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;

	@Resource
	private TitanFinancialUserService titanFinancialUserService;

	@Resource
	private DomainConfigDao domainConfigDao;
	
	@Resource
	private TitanRefundDao titanRefundDao;

	private HotelOrderSearchFacade hotelOrderSearchFacade;

	private static Map<String, Object> mapLock = new ConcurrentHashMap<String, Object>();


	public void tt(){
		
		
		//TitanTransOrder titanTransOrder = orderRequest2TitanTransOrder(orderRequest);
		
		
		//titanTransOrderDao.insert(titanTransOrder)
	}
	
	@Override
	public LocalAddTransOrderResponse addLocalTransOrder(
			TitanPaymentRequest titanPaymentRequest) {
		LocalAddTransOrderResponse localAddTransOrderResponse = new LocalAddTransOrderResponse();
		try {
			log.info("本地下单入参:" + JSONSerializer.toJSON(titanPaymentRequest));

			TransOrderResponse transOrderResponse = this
					.queryTransOrderByCode(titanPaymentRequest.getPayOrderNo());
			if (null == transOrderResponse
					|| null == transOrderResponse.getTransOrder()) {
				log.error("the order of query is failed: the orderNo is "
						+ titanPaymentRequest.getPayOrderNo());
				localAddTransOrderResponse.putErrorResult("110100013", "订单查询失败");
				return localAddTransOrderResponse;
			}
			TransOrderDTO transOrderDTO = transOrderResponse.getTransOrder();

			OrderRequest orderRequest = this.convertorToTitanOrderRequest2(
					titanPaymentRequest, transOrderDTO);
			TitanTransOrder titanTransOrder = orderRequest2TitanTransOrder(orderRequest);
			if (OrderStatusEnum.isRepeatedPay(transOrderDTO.getStatusid())) {
				// 两次需要充值的金额不等需改订单
				boolean flag = validateIsUpdateOrder2(titanPaymentRequest,
						transOrderDTO);
				if (flag) {// 充值金额相等则返回该单号，否则生成订单
					// 更新一下订单
					titanTransOrder.setOrderid(OrderGenerateService
							.genLocalOrderNo());
					titanTransOrder.setTransid(transOrderDTO.getTransid());
					titanTransOrderDao
							.updateTitanTransOrderByTransId(titanTransOrder);

					localAddTransOrderResponse.setOrderNo(titanTransOrder
							.getOrderid());
					localAddTransOrderResponse.setUserOrderId(titanTransOrder
							.getUserorderid());
					localAddTransOrderResponse.putSuccess();
					return localAddTransOrderResponse;
				}

			} else if (OrderStatusEnum
					.isPaySuccess(transOrderDTO.getStatusid())) {
				localAddTransOrderResponse.putErrorResult("110100014", "已支付，请勿重复支付");
				// 再次回调财务
				return localAddTransOrderResponse;
			}
			
			//废除单之后重新落单
			titanTransOrder.setOrderid(OrderGenerateService.genLocalOrderNo());
			titanTransOrder.setUserorderid(OrderGenerateService
					.genSyncUserOrderId());
			titanTransOrder.setStatusid(OrderStatusEnum.RECHARGE_IN_PROCESS
					.getStatus());
			log.info("the params of local order :"
					+ JSONSerializer.toJSON(titanTransOrder));
			if (titanTransOrderDao.insert(titanTransOrder) > 0 ? true : false) {
				localAddTransOrderResponse.setUserOrderId(titanTransOrder
						.getUserorderid());
				localAddTransOrderResponse.setOrderNo(titanTransOrder
						.getOrderid());
				localAddTransOrderResponse.putSuccess();
				return localAddTransOrderResponse;
			}
			
		} catch (Exception e) {
			log.error("add local order is failed" + e.getMessage(), e);
			localAddTransOrderResponse.putSysError();
		}
		return localAddTransOrderResponse;
	}

	// 生成并 保存单
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public TransOrderCreateResponse createRsOrder(
			TitanPaymentRequest titanPaymentRequest) {
		log.info("落单入参:" + JSONSerializer.toJSON(titanPaymentRequest));
		TransOrderCreateResponse orderResponse = new TransOrderCreateResponse();

		TransOrderResponse transOrderResponse = this
				.queryTransOrderByCode(titanPaymentRequest.getPayOrderNo());
		if (null == transOrderResponse
				|| null == transOrderResponse.getTransOrder()) {
			orderResponse.putErrorResult("订单不存在");
			return orderResponse;
		}
		TransOrderDTO transOrderDTO = transOrderResponse.getTransOrder();
		if(OrderStatusEnum.isPaySuccess(transOrderDTO.getStatusid())){
			orderResponse.putErrorResult("支付成功,请勿重复支付！");
			return orderResponse;
		}
		// 是否在融数落单
		String orderid = "";
		// 是否需要重新在本地下单
		boolean isAddOrderAgain = false;
		try {
			if (this.isRsOrder(transOrderDTO.getOrderid())) {// 在融数已经落单
				
				TitanOrderPayreq titanOrderPayreq = new TitanOrderPayreq();
				titanOrderPayreq
						.setTransorderid(transOrderDTO.getTransid());
				titanOrderPayreq = this
						.queryOrderPayReqByTransOrderId(titanOrderPayreq);
				if (null == titanOrderPayreq) {
					orderResponse.putErrorResult("系统错误");
					return orderResponse;
				}

				if (ReqstatusEnum.RECHARFE_SUCCESS.getStatus() == titanOrderPayreq
						.getReqstatus()) {
					orderResponse.putErrorResult("该单已成功充值,请勿重新充值");
					return orderResponse;
				}
				
				//check the amount is change 
				boolean isAmountChange =false;
				if(StringUtil.isValidString(titanPaymentRequest.getPayAmount()) &&transOrderDTO.getAmount()!=null){
					isAmountChange = NumberUtil.covertToCents(titanPaymentRequest.getPayAmount())
					.equals(transOrderDTO.getAmount().toString());
				}
				
				boolean isBankInfoChange = false;
				if(StringUtil.isValidString(titanOrderPayreq.getBankInfo()) && StringUtil.isValidString(titanPaymentRequest.getBankInfo())){
					isBankInfoChange = titanOrderPayreq.getBankInfo().equals(titanPaymentRequest.getBankInfo());
				}
				
				
				boolean isPayerAccountChange = true;
				if(StringUtil.isValidString(titanPaymentRequest.getPayerAcount())){
					isPayerAccountChange = titanPaymentRequest.getPayerAcount().equals(titanOrderPayreq.getPayerAcount());
				}
				
				
				if(isAmountChange && isBankInfoChange && isPayerAccountChange){
					long times = DateUtil.diffSecondByTime(
							titanOrderPayreq.getOrderTime(),
							DateUtil.sdf5.format(new Date()));
					if (times < this.getExpireTime(titanOrderPayreq)) {// 未过期
																		// 获取当前单号,需要优化
						orderid = titanOrderPayreq.getOrderNo();
						orderResponse.setOrderNo(orderid);
					} else {
						this.updateOrderNoEffect(transOrderDTO.getTransid());
						isAddOrderAgain = true;
					}
				} else {
					this.updateOrderNoEffect(transOrderDTO.getTransid());
					isAddOrderAgain = true;
				}
			}else{//余额支付不成功，之后网银支付
				
				if(StringUtil.isValidString(transOrderDTO.getOrderid())){
					this.updateOrderNoEffect(transOrderDTO.getTransid());
					isAddOrderAgain = true;
				}
			}

			OrderRequest orderRequest = this.convertorToTitanOrderRequest2(
					titanPaymentRequest, transOrderDTO);

			if (!StringUtil.isValidString(orderid)) {// 是否在融数落单,
				if (isAddOrderAgain) {
					orderRequest.setUserorderid(OrderGenerateService
							.genSyncUserOrderId());
				}
				OrderOperateResponse orderOperateResponse = this
						.getOrderId(orderRequest);

				if (!orderOperateResponse.getOperateStatus().equals(
						CommonConstant.OPERATE_SUCCESS)) {// 融数下单不成功
					orderResponse.putErrorResult(orderOperateResponse
							.getReturnMsg());
					return orderResponse;
				}
				if(PayerTypeEnum.RECHARGE.getKey().equals(transOrderDTO.getPayerType()))
				{
					orderRequest.setGoodsdetail("使用"
							+ SupportBankEnum
									.getBankDetailByName(titanPaymentRequest
											.getBankInfo()).bankRemark + "充值");
					if(StringUtil.isValidString(titanPaymentRequest.getTradeAmount()))
					{
						orderRequest.setTradeamount(Long.parseLong(NumberUtil.covertToCents(titanPaymentRequest.getTradeAmount())));
					}
				}
				orderRequest.setOrderid(orderOperateResponse.getOrderid());
				orderRequest.setTransid(transOrderDTO.getTransid());
				boolean isSuccess = this.saveOrUpdateTitanTransOrder(
						orderRequest, isAddOrderAgain);
				if (!isSuccess) {
					orderResponse.putErrorResult("保存本地单失败");
					return orderResponse;
				}
				orderResponse.setOrderNo(orderOperateResponse.getOrderid());
			}

			log.info("融数落单返回结果dubbo:" + JSONSerializer.toJSON(orderResponse));
			orderResponse.putSuccess();
			return orderResponse;

		} catch (Exception e) {
			log.error("落单失败" + e.getMessage());
			orderResponse.putErrorResult("落单异常");
		}
		return orderResponse;
	}

	//该订单是不是融数单号
	private boolean isRsOrder(String orderid){
		if(!StringUtil.isValidString(orderid)){
			return false;
		}
		
		if(!orderid.substring(0,1).equals(CommonConstant.LOCAL_ORDERNO)){
			return true;
		}
		
		return false;
	}
	
	private boolean saveOrUpdateTitanTransOrder(OrderRequest orderRequest,
			boolean isAddOrderAgain) {
		int row = 0;
		try {
			TitanTransOrder titanTransOrder = orderRequest2TitanTransOrder(orderRequest);
			if (isAddOrderAgain) {
				titanTransOrder.setTransid(null);
				row = titanTransOrderDao.insert(titanTransOrder);
			} else {
				row = titanTransOrderDao
						.updateTitanTransOrderByTransId(titanTransOrder);
			}
			if (row < 1) {
				OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(
						titanTransOrder.getOrderid(), "融数落单成功 本地记录失败",
						OrderExceptionEnum.TransOrder_Insert,
						JSON.toJSONString(titanTransOrder));
				titanOrderService.saveOrderException(orderExceptionDTO);
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error("融数成功,本地操作订单失败" + e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 根据TransOrderid获取财务单
	 *
	 * @param titanOrderPayreq
	 * @return
	 * @throws Exception
	 */
	private TitanOrderPayreq queryOrderPayReqByTransOrderId(
			TitanOrderPayreq titanOrderPayreq) {
		TitanOrderPayreq OrderPayreq = null;
		try {
			TitanOrderPayreqParam condition = new TitanOrderPayreqParam();
			condition.setTransorderid(titanOrderPayreq.getTransorderid());
			condition.setOrderNo(titanOrderPayreq.getOrderNo());
			PaginationSupport<TitanOrderPayreq> paginationSupport = new PaginationSupport<TitanOrderPayreq>();
			paginationSupport.setOrderBy("orderTime desc");
			titanOrderPayreqDao.selectForPage(condition, paginationSupport);
			List<TitanOrderPayreq> titanOrderPayreqList = paginationSupport
					.getItemList();
			if (titanOrderPayreqList != null && titanOrderPayreqList.size() > 0) {
				OrderPayreq = titanOrderPayreqList.get(0);
			}
		} catch (Exception e) {
			log.error("根据TransOrderid获取充值单失败:" + e.getMessage());
		}
		return OrderPayreq;
	}

	/**
	 * 查询落单记录
	 *
	 * @param payOrderNo
	 * @return
	 * @throws Exception
	 */
	private TransOrderResponse queryTransOrderByCode(String payOrderNo) {
		TransOrderResponse transOrderResponse = null;
		try {
			if (!StringUtil.isValidString(payOrderNo)) {
				return null;
			}

			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setPayorderno(payOrderNo);
			List<TransOrderDTO> transOrderDTOList = titanOrderService
					.queryTransOrder(transOrderRequest);

			if (transOrderDTOList != null && transOrderDTOList.size() > 0) {
				TransOrderDTO transOrderDTO = transOrderDTOList.get(0);
				transOrderResponse = new TransOrderResponse();
				transOrderResponse.setTransOrder(transOrderDTO);
			}

		} catch (Exception e) {
			log.error("查询订单失败:" + e.getMessage());
		}
		return transOrderResponse;
	}

	/**
	 * 落单操作
	 *
	 * @param orderRequest
	 * @return
	 * @throws Exception
	 * @author fangdaikang
	 */

	private OrderOperateResponse getOrderId(OrderRequest orderRequest)
			throws Exception {
		try {
			OrderOperateRequest req = new OrderOperateRequest();
			req.setUserid(orderRequest.getUserid()); // 接入机构中设置的用户ID
			req.setConstid(CommonConstant.RS_FANGCANG_CONST_ID); // 机构码
			req.setOrdertypeid(orderRequest.getOrdertypeid()); // 基础业务为B，扩展业务待定
																// M70001棉庄订金支付
			req.setProductid(orderRequest.getProductId()); // 产品号
			req.setOpertype(orderRequest.getOpertype()); // 操作类型（修改：2,新增：1,取消4,查询3）
			req.setOrderdate(new Date()); // 订单日期
			req.setOrdertime(new Date()); // 订单时间
			req.setUserorderid(orderRequest.getUserorderid()); // 用户订单编号
			req.setAmount(orderRequest.getAmount());
			req.setGoodsname(orderRequest.getGoodsname()); // 商品名称
			req.setGoodsdetail(orderRequest.getGoodsdetail()); // 商品描述
			req.setNumber(orderRequest.getNumber()); // 商品数量
			req.setUnitprice(orderRequest.getUnitprice()); // 手续费
			req.setAdjusttype(orderRequest.getAdjusttype()); // 调整类型
			req.setAdjustcontent(orderRequest.getAdjustcontent()); // 调整内容
			req.setUserrelateid(orderRequest.getUserrelateid()); // 关联用户id（若有第三方则必须填写）
			req.setUnitprice(orderRequest.getReceivedfee());//设置实收的手续费
			return rsAccTradeManager.operateOrder(req);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	// 转账
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public TransferResponse transferAccounts(TransferRequest transferRequest)
			throws Exception {
		log.info("进入转账，入参" + JSON.toJSONString(transferRequest));
		TransferResponse accTradeResponse = new TransferResponse();
		String payOrderNo = null;
		try {
			AccountTransferRequest accountRequest = transferRequest2AccountTransferRequest(transferRequest);
			if (accountRequest != null) {
				TitanTransferReq titanTransferReq = transferRequest2TitanTransferReq(transferRequest);
				accTradeResponse.setRequestNo(titanTransferReq.getRequestno());
				if (titanTransferReq != null) {
					Integer transid = null;
					TransOrderDTO transOrderDTO = getTransidByOrderId(transferRequest
							.getOrderid());
					if (transOrderDTO != null) {
						transid = transOrderDTO.getTransid();
						payOrderNo = transOrderDTO.getPayorderno();
					}
					// 获取落单时的订单id
					if (transid != null && StringUtil.isValidString(payOrderNo)) {
						titanTransferReq.setTransorderid(transid);
						titanTransferReq.setPayorderno(payOrderNo);
						lockOutTradeNoList(payOrderNo);
						// synchronized (payOrderNo) {//锁定支付单
						// 查询该单号是否已经有转账单
						TitanTransferReq titanTransfer = queryTransfer(payOrderNo);
						boolean flag = false;
						if (titanTransfer == null) {// 判断转账是否已经进行
							flag = titanTransferReqDao.insert(titanTransferReq) > 0 ? true
									: false;
						} else {
							if (TransferReqEnum.TRANSFER_SUCCESS.getStatus() != titanTransfer
									.getStatus().intValue()) {
								titanTransferReq.setTransferreqid(titanTransfer
										.getTransferreqid());
								flag = titanTransferReqDao
										.update(titanTransferReq) > 0 ? true
										: false;
							}
						}
						if (flag) {
							AccountTransferResponse accountTransferResponse = rsAccTradeManager
									.accountBalanceTransfer(accountRequest);
							log.info("转账结果"
									+ JSON.toJSONString(accountTransferResponse));
							if (accountTransferResponse != null) {
								if (CommonConstant.OPERATE_SUCCESS
										.equals(accountTransferResponse
												.getOperateStatus())) {
									titanTransferReq
											.setStatus(TransferReqEnum.TRANSFER_SUCCESS
													.getStatus());
									accTradeResponse.putSuccess();
								} else {
									titanTransferReq
											.setStatus(TransferReqEnum.TRANSFER_FAIL
													.getStatus());
									accTradeResponse
											.putErrorResult(
													accountTransferResponse
															.getRetcode(),
													accountTransferResponse
															.getRetmsg());
									// 转账是否成功，重复佐证 待确认
									ROPErrorEnum ropErrorEnum = ROPErrorEnum
											.getROPErrorEnumByCode(accountTransferResponse
													.getReturnCode());
									if (ropErrorEnum != null) {// 若错误提示是ROP连接等错误需要重复确认
										AccountTransferFlowRequest accountTransferFlowRequest = new AccountTransferFlowRequest();
										accountTransferFlowRequest
												.setRequestNo(accountRequest
														.getRequestno());
										accountTransferFlowRequest
												.setProductId(accountRequest
														.getProductid());
										accountTransferFlowRequest
												.setUserId(accountRequest
														.getUserid());
										if (this.confirmTransAccountSuccess(accountTransferFlowRequest)) {// 确认转账成功
											titanTransferReq
													.setStatus(TransferReqEnum.TRANSFER_SUCCESS
															.getStatus());
											accTradeResponse.putSuccess();
										}
									}
								}
								try {
									titanTransferReqDao
											.update(titanTransferReq);
								} catch (Exception e) {
									log.error("更新转账记录失败" + e.getMessage(), e);
									OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(
											transOrderDTO.getOrderid(),
											"转账成功 更新转账记录失败",
											OrderExceptionEnum.Transfer_Update,
											JSON.toJSONString(titanTransferReq));
									titanOrderService
											.saveOrderException(orderExceptionDTO);
								}
							}
						} else {
							log.error("充值下单失败，对应的落单id："
									+ titanTransferReq.getTransorderid());
							accTradeResponse.putErrorResult("充值下单失败,业务单可能已经支付");
						}
						unlockOutTradeNoList(payOrderNo);
					}else{
						accTradeResponse.putErrorResult("订单不存在");
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e);
		} finally {
			unlockOutTradeNoList(payOrderNo);
		}
		return accTradeResponse;
	}

	private TitanTransferReq queryTransfer(String payOrderNo) {
		TitanTransferReqParam titanTransferReqParam = new TitanTransferReqParam();
		titanTransferReqParam.setPayorderno(payOrderNo);
		List<TitanTransferReq> titanTransferList = titanTransferReqDao
				.queryTitanTransferReq(titanTransferReqParam);
		if (titanTransferList != null && titanTransferList.size() > 0) {
			TitanTransferReq titanTransferReq = titanTransferList.get(0);
			if (titanTransferReq != null) {
				return titanTransferList.get(0);
			}
		}
		return null;
	}

	// 回调财务
	@Override
	public void confirmFinance(TransOrderDTO transOrderDTO) throws Exception {

		if (transOrderDTO == null
				|| !StringUtil.isValidString(transOrderDTO.getUserorderid())) {
			return;
		}

		String response = "";

		List<NameValuePair> params = this.getHttpParams(transOrderDTO);

		if (params == null) {
			return;
		}

		String url = null;

		if (StringUtil.isValidString(transOrderDTO.getNotifyUrl())) {
			url = transOrderDTO.getNotifyUrl();
		}else{
			return;
		}
		try {
			log.info("转账成功之后回调:" + JSONSerializer.toJSON(params) + "---url---"
					+ url);
			   HttpPost httpPost = new HttpPost(url);
		        HttpResponse resp = HttpClient.httpRequest(params,  httpPost);
			if (null != resp) {
				InputStream in = resp.getEntity().getContent();
				byte b[] = new byte[1024];
				
				int length = 0;
				if((length = in.read(b)) !=-1){
					byte d[] = new byte[length];
					System.arraycopy(b, 0, d, 0, length);
					response = new String(d , "UTF-8");
				}
				httpPost.releaseConnection();
			}
		} catch (Exception e) {
			log.error("调用http请求通知支付失败", e);
			throw e;
		}
		log.info("调用http请求通知支付支付结果完成：" + response);
		if (StringUtil.isValidString(response)) {
			CallBackInfo callBackInfo = TitanjrHttpTools.analyzeResponse(response);
			if (!"000".equals(callBackInfo.getCode())) {
				log.error("回调失败单号:" + transOrderDTO.getUserorderid());
				OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(
						transOrderDTO.getUserorderid(), "转账成功 回调失败",
						OrderExceptionEnum.Finance_Confirm,
						JSON.toJSONString(callBackInfo));
				titanOrderService.saveOrderException(orderExceptionDTO);
				return;
			}

		} else {// 记录异常单
			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(
					transOrderDTO.getOrderid(), "回调订单异常",
					OrderExceptionEnum.Finance_Confirm,
					JSON.toJSONString(transOrderDTO));
			titanOrderService.saveOrderException(orderExceptionDTO);
		}

	}

	private OrgBindInfo getOrgBindInfo(String orgCode) {
		if (!StringUtil.isValidString(orgCode)) {
			return null;
		}

		OrgBindInfo orgBindInfo = new OrgBindInfo();
		orgBindInfo.setOrgcode(orgCode);
		return titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
	}

	private List<NameValuePair> getHttpParams(TransOrderDTO transOrderDTO) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("payOrderCode", transOrderDTO
				.getPayorderno()));
		params.add(new BasicNameValuePair("businessOrderCode", transOrderDTO
				.getBusinessordercode()));

		if(transOrderDTO.getTradeamount()!=null ){
			params.add(new BasicNameValuePair("amount", transOrderDTO
					.getTradeamount().toString()));
		}
		NameValuePair nameValuePair = new BasicNameValuePair("merchantCode", transOrderDTO
				.getMerchantcode());
		
		//此段处理逻辑是前一版本收银台的逻辑，新版本之后将舍去
		PayerTypeEnum payerTypeEnum = PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
		if(payerTypeEnum !=null && payerTypeEnum.isB2BPayment()){
			OrgBindInfo orgBindInfo = this.getOrgBindInfo(transOrderDTO
					.getPayeemerchant());
			if (null != orgBindInfo) {
				nameValuePair = new BasicNameValuePair("merchantCode", orgBindInfo
						.getMerchantCode());
			}
		}
		params.add(nameValuePair);
		//end
		params.add(new BasicNameValuePair("operator", transOrderDTO
				.getCreator()));
		
		params.add(new BasicNameValuePair("titanPayOrderCode", transOrderDTO
				.getUserorderid()));
		params.add(new BasicNameValuePair("businessInfo", transOrderDTO.getBusinessinfo()));
		
		params.add(new BasicNameValuePair("payResult", "1"));
		params.add(new BasicNameValuePair("code", "valid"));
		return params;
	}

//	private CallBackInfo analyzeResponse(String info) {
//		CallBackInfo callBackInfo = new CallBackInfo();
//		String[] sourceStrArray = info.split("&");
//		if (sourceStrArray != null && sourceStrArray.length > 0) {
//			String[] code = sourceStrArray[0].split("=");
//			if (null != code && code.length == 2) {
//				callBackInfo.setCode(code[1]);
//			}
//			if (sourceStrArray.length == 2) {
//				String[] msg = sourceStrArray[1].split("=");
//				if (null != msg && msg.length == 2) {
//					callBackInfo.setMsg(msg[1]);
//				}
//			}
//			return callBackInfo;
//		}
//		return null;
//	}

	// 查询转账
	@Override
	public boolean confirmTransAccountSuccess(
			AccountTransferFlowRequest accountTransferFlowRequest) {
		try {
			if (accountTransferFlowRequest != null) {
				OrderTransferFlowRequest orderTransferFlowRequest = new OrderTransferFlowRequest();
				orderTransferFlowRequest.setUserid(accountTransferFlowRequest
						.getUserId());
				orderTransferFlowRequest
						.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				orderTransferFlowRequest
						.setRequestno(accountTransferFlowRequest.getRequestNo());
				orderTransferFlowRequest
						.setProductid(accountTransferFlowRequest.getProductId());
				OrderTransferFlowResponse orderTransferFlowResponse = rsAccTradeManager
						.queryOrderTranferFlow(orderTransferFlowRequest);
				if (CommonConstant.OPERATE_SUCCESS
						.equals(orderTransferFlowResponse.getOperateStatus())) {
					return true;
				}
			}

		} catch (Exception e) {
			log.error("确认转账是否成功异常" + e.getMessage(), e);
		}
		return false;
	}

	// 获取Transid
	private TransOrderDTO getTransidByOrderId(String orderId) throws Exception {
		try {
			if (StringUtil.isValidString(orderId)) {
				TransOrderRequest transOrderRequest = new TransOrderRequest();
				transOrderRequest.setOrderid(orderId);
				TransOrderDTO transOrderDTO = titanOrderService
						.queryTransOrderDTO(transOrderRequest);
				if (transOrderDTO != null) {// 获取transid
					return transOrderDTO;
				}
			}
		} catch (Exception e) {
			log.error("获取TransId错误" + e.getMessage(), e);
			throw new Exception(e);
		}
		return null;
	}

	private AccountTransferRequest transferRequest2AccountTransferRequest(
			TransferRequest transferRequest) throws Exception {
		AccountTransferRequest account = null;
		try {
			if (transferRequest != null) {
				account = new AccountTransferRequest();
				account.setAmount(transferRequest.getAmount());
				if (transferRequest.getTransfertype() != null) {
					account.setTransfertype(transferRequest.getTransfertype()
							.getKey()); // 1:子账户转账
				}
				if (transferRequest.getConditioncode() != null) {
					account.setConditioncode(transferRequest.getConditioncode()
							.getKey()); // 1:落单
				}
				account.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID); // 转入方机构号
				account.setProductid(transferRequest.getProductId()); // 转入方产品号
				account.setUserid(transferRequest.getUserid()); // 转出的用户
				// 需要生成的业务单号
				account.setRequestno(transferRequest.getRequestno()); // 业务订单号
				account.setRequesttime(transferRequest.getRequesttime()); // 请求时间
				account.setAmount(transferRequest.getAmount()); // 金额
				account.setUserfee(transferRequest.getUserfee()); // 手续费
				account.setIntermerchantcode(CommonConstant.RS_FANGCANG_CONST_ID); // 接收机构号
				account.setInterproductid(transferRequest.getInterproductid()); // 接收方产品号
				account.setUserrelateid(transferRequest.getUserrelateid()); // 接收方用户Id
				account.setProductid(transferRequest.getProductId());
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return account;
	}

	// 将transferRequest转换为
	private TitanTransferReq transferRequest2TitanTransferReq(
			TransferRequest transferRequest) throws Exception {
		TitanTransferReq titanTransferReq = null;
		try {
			if (transferRequest != null) {
				titanTransferReq = new TitanTransferReq();
				if (StringUtil.isValidString(transferRequest.getAmount())) {
					titanTransferReq.setAmount(Double
							.parseDouble(transferRequest.getAmount()));
				}
				if (transferRequest.getTransfertype() != null) {
					titanTransferReq.setTransfertype(Integer
							.parseInt(transferRequest.getTransfertype()
									.getKey())); // 1:子账户转账
				}
				titanTransferReq.setCreatetime(new Date());
				titanTransferReq.setCreator(transferRequest.getCreator());
				titanTransferReq
						.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
				titanTransferReq.setProductid(transferRequest.getProductId());
				titanTransferReq
						.setIntermerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
				titanTransferReq.setInterproductid(transferRequest
						.getInterproductid());
				titanTransferReq.setRequestno(transferRequest.getRequestno());
				if (transferRequest.getRequesttime() != null) {
					titanTransferReq.setRequesttime(DateUtil.StringToDate(
							transferRequest.getRequesttime(),
							"yyyy-MM-dd HH:mm:ss"));
				}
				if (transferRequest.getStatus() != null) {
					titanTransferReq.setStatus(Integer.parseInt(transferRequest
							.getStatus()));
				}
				if (transferRequest.getTransfertype() != null) {
					titanTransferReq.setTransfertype(Integer
							.parseInt(transferRequest.getTransfertype()
									.getKey()));
				}
				if (transferRequest.getUserfee() != null) {
					titanTransferReq.setUserfee(Double
							.parseDouble(transferRequest.getUserfee()));
				}
				titanTransferReq.setUserid(transferRequest.getUserid());
				titanTransferReq.setUserrelateid(transferRequest
						.getUserrelateid());
				titanTransferReq.setStatus(TransferReqEnum.TRANSFER_SUCCESS
						.getStatus());
				if (transferRequest.getConditioncode() != null) {
					titanTransferReq.setConditioncode(Integer
							.parseInt(transferRequest.getConditioncode()
									.getKey()));
				}

			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return titanTransferReq;
	}

	public RechargeResponse packageRechargeData(RechargeRequest rechargeRequest) {
		RechargeResponse rechargeResponse = new RechargeResponse();
		if (null == rechargeRequest) {
			rechargeResponse.putErrorResult("参数请求不合法");
			return rechargeResponse;
		}
		RSPayOrderRequest rsPayOrderRequest = this
				.convertorToRSPayOrderRequest(rechargeRequest);

		if (null == rsPayOrderRequest) {
			rechargeResponse.putErrorResult("系统异常");
			return rechargeResponse;
		}

		TitanOrderPayreq titanOrderPayreq = this
				.convertorToTitanOrderPayReq(rechargeRequest);
		TitanOrderPayreq titanOrder = new TitanOrderPayreq();
		titanOrder.setOrderNo(rechargeRequest.getOrderNo());
		titanOrder = this.queryOrderPayReqByTransOrderId(titanOrder);

		if (titanOrder == null) {// 下充值单
			titanOrderPayreq.setReqstatus(ReqstatusEnum.RECHARFE_IN_PROCESS
					.getStatus());
			titanOrderPayreqDao.insert(titanOrderPayreq);
		}

		if (titanOrder != null) {// 更新充值单
			titanOrderPayreq.setOrderTime(null);
			titanOrderPayreqDao
					.updateTitanOrderPayreqByOrderNo(titanOrderPayreq);
			rsPayOrderRequest.setOrderTime(titanOrder.getOrderTime());
		}

		log.info("充值获取参数的入参:" + JSON.toJSONString(rsPayOrderRequest));
		RSPayOrderResponse response = rsPayOrderManager
				.getPayPage(rsPayOrderRequest);
		log.info("充值获取参数的结果:" + JSON.toJSONString(response));

		rechargeResponse.putErrorResult(response.getReturnCode(),
				response.getReturnMsg());
		if (CommonConstant.OPERATE_SUCCESS.equals(response.getOperateStatus())) {
			rechargeResponse.putSuccess();
			RechargeDataDTO rechargeDataDTO = new RechargeDataDTO();
			MyBeanUtil.copyProperties(rechargeDataDTO,
					response.getRsPayOrderRequest());
			rechargeDataDTO.setGateWayUrl(RSInvokeConstant.gateWayURL);
			rechargeResponse.setRechargeDataDTO(rechargeDataDTO);
		}
		// 将网关支付地址返回支付
		return rechargeResponse;
	}


	/**
	 * 将充值的请求转换为融数的请求参数
	 *
	 * @param rechargeRequest
	 * @return
	 * @author fangdaikang
	 */
	private RSPayOrderRequest convertorToRSPayOrderRequest(
			RechargeRequest rechargeRequest) {
		RSPayOrderRequest req = null;
		if (rechargeRequest != null) {
			req = new RSPayOrderRequest();
			req.setMerchantNo(CommonConstant.RS_FANGCANG_CONST_ID);
			req.setOrderNo(rechargeRequest.getOrderNo());
			req.setProductNo(rechargeRequest.getProductNo());
			req.setProductName(rechargeRequest.getProductName());
			req.setProductDesc(rechargeRequest.getProductDesc());
			req.setProductNum(rechargeRequest.getProductNum());

			req.setOrderAmount(rechargeRequest.getOrderAmount());
			req.setPayType(rechargeRequest.getPayType());
			req.setAmtType(rechargeRequest.getAmtType());
			req.setBankInfo(rechargeRequest.getBankInfo());
			req.setPayerAcount(rechargeRequest.getPayerAcount());
			req.setPayerName(rechargeRequest.getPayerName());
			req.setPayerPhone(rechargeRequest.getPayerPhone());
			req.setPayerMail(rechargeRequest.getPayerMail());
			req.setPageUrl(rechargeRequest.getPageUrl());
			req.setNotifyUrl(rechargeRequest.getNotifyUrl());
			req.setOrderTime(rechargeRequest.getOrderTime());
			if (rechargeRequest.getOrderExpireTime() != null) {
				req.setOrderExpireTime(rechargeRequest.getOrderExpireTime()
						.toString());
			}
			req.setOrderMark(rechargeRequest.getOrderMark());
			req.setExpand(rechargeRequest.getExpand());
			req.setExpand2(rechargeRequest.getExpand2());
			req.setSignType(rechargeRequest.getSignType());
			req.setBusiCode(rechargeRequest.getBusiCode());
			req.setVersion(rechargeRequest.getVersion());
			req.setCharset(rechargeRequest.getCharset());
		}
		return req;
	}


	private OrderRequest convertorToTitanOrderRequest2(
			TitanPaymentRequest titanPaymentRequest, TransOrderDTO transOrderDTO)
			throws Exception {
		OrderRequest orderRequest = new OrderRequest();
		try {
			orderRequest.setProductId(transOrderDTO.getProductid());
			orderRequest.setUserid(transOrderDTO.getUserid());
			orderRequest.setAmount(NumberUtil.covertToCents(titanPaymentRequest
					.getPayAmount()));
			orderRequest.setAdjustcontent(titanPaymentRequest
					.getAdjustcontent());
			orderRequest.setAdjusttype(titanPaymentRequest.getAdjusttype());

			// orderRequest.setUserorderid(OrderGenerateService.genSyncUserOrderId());
			// 将userOrderId封装到paymentRequest,回调财务或者GDP时使用
			orderRequest.setUserrelateid(titanPaymentRequest.getUserrelateid());
			orderRequest.setInterProductid(titanPaymentRequest
					.getInterProductid());
			orderRequest.setCreator(transOrderDTO.getCreator());
			orderRequest.setMerchantCode(transOrderDTO.getMerchantcode());
			orderRequest.setPayOrderNo(transOrderDTO.getPayorderno());
			if (transOrderDTO.getTradeType() != null) {
				orderRequest.setTransordertype(Integer.parseInt(transOrderDTO
						.getTradeType()));
			}
			orderRequest.setGoodsdetail(transOrderDTO.getGoodsdetail());
			orderRequest.setBusinessordercode(transOrderDTO
					.getBusinessordercode());
			orderRequest.setGoodsname(transOrderDTO.getGoodsname());
			orderRequest.setNotifyUrl(transOrderDTO.getNotifyUrl());
			orderRequest.setPayermerchant(transOrderDTO.getPayermerchant());
			orderRequest.setIsEscrowedPayment(transOrderDTO
					.getIsEscrowedPayment());
			orderRequest.setEscrowedDate(transOrderDTO.getEscrowedDate());
			orderRequest.setOpertype(OperTypeEnum.Add_Order.key);
			orderRequest.setOrdertypeid(OrderTypeEnum.OrderType_1.key);
			orderRequest.setUserorderid(transOrderDTO.getUserorderid());
			orderRequest
					.setPayeemerchant(titanPaymentRequest.getUserrelateid());
			orderRequest.setTradeamount(transOrderDTO.getTradeamount());
			orderRequest.setTransordertype(transOrderDTO.getTransordertype());
			orderRequest.setPayerType(transOrderDTO.getPayerType());
			orderRequest.setBussinessInfo(transOrderDTO.getBusinessinfo());
			orderRequest.setBusinessordercode(transOrderDTO
					.getBusinessordercode());
			//设置费率信息
			orderRequest.setReceivablefee(titanPaymentRequest.getReceivablefee());
			orderRequest.setReceivedfee(titanPaymentRequest.getReceivedfee());
			orderRequest.setStandfee(titanPaymentRequest.getStandfee());
		} catch (Exception e) {
			throw new Exception(e);
		}
		return orderRequest;
	}

	/**
	 * 落单时的请求工单封装为 本地TitanTransOrder
	 *
	 * @param orderRequest
	 * @return
	 * @throws Exception
	 */
	private TitanTransOrder orderRequest2TitanTransOrder(
			OrderRequest orderRequest) throws Exception {
		TitanTransOrder titanTransOrder = null;
		try {
			titanTransOrder = new TitanTransOrder();
			if (orderRequest != null) {
				titanTransOrder.setAdjustcontent(orderRequest
						.getAdjustcontent());
				titanTransOrder.setAdjusttype(orderRequest.getAdjusttype());
				if (orderRequest.getAmount() != null) {
					titanTransOrder.setAmount(Long.parseLong(orderRequest
							.getAmount()));
				}
				titanTransOrder.setBusinessordercode(orderRequest
						.getBusinessordercode());
				titanTransOrder.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				titanTransOrder.setCreatetime(new Date());
				titanTransOrder.setCreator(orderRequest.getCreator());

				titanTransOrder.setGoodscnt(orderRequest.getNumber());
				titanTransOrder.setGoodsdetail(orderRequest.getGoodsdetail());
				titanTransOrder.setGoodsname(orderRequest.getGoodsname());
				titanTransOrder.setOrderdate(orderRequest.getOrderdate());

				titanTransOrder.setOrderid(orderRequest.getOrderid());
				titanTransOrder.setOrdertime(orderRequest.getOrdertime());
				titanTransOrder.setOrdertypeid(orderRequest.getOrdertypeid());
				titanTransOrder.setProductid(orderRequest.getProductId());
				titanTransOrder.setProvider(null);
				titanTransOrder.setStatusid(OrderStatusEnum.ORDER_IN_PROCESS
						.getStatus());
				if (orderRequest.getUnitprice() != null) {
					titanTransOrder.setUnitprice(Long.parseLong(orderRequest
							.getUnitprice()));
				}
				titanTransOrder.setUserid(orderRequest.getUserid());
				titanTransOrder.setUserorderid(orderRequest.getUserorderid());
				titanTransOrder.setInterproductid(orderRequest
						.getInterProductid());
				titanTransOrder.setUserrelateid(orderRequest.getUserrelateid());
//				titanTransOrder.setReceivablefee(null);
//				titanTransOrder.setReceivedfee(null);
				titanTransOrder.setIsEscrowedPayment(orderRequest
						.getIsEscrowedPayment());
				titanTransOrder.setEscrowedDate(orderRequest.getEscrowedDate());
				titanTransOrder.setPayorderno(orderRequest.getPayOrderNo());
				titanTransOrder.setMerchantcode(orderRequest.getMerchantCode());
				titanTransOrder.setPayeemerchant(orderRequest
						.getPayeemerchant());
				titanTransOrder.setPayermerchant(orderRequest
						.getPayermerchant());
				if (orderRequest.getTradeamount() != null) {
					titanTransOrder.setTradeamount(orderRequest
							.getTradeamount());
				}
				if (orderRequest.getTransordertype() != null) {
					titanTransOrder.setTransordertype(orderRequest
							.getTransordertype());
				}
				titanTransOrder.setNotifyUrl(orderRequest.getNotifyUrl());
				if (orderRequest.getTransid() != null) {
					titanTransOrder.setTransid(orderRequest.getTransid());
				}
				titanTransOrder.setPayerType(orderRequest.getPayerType());
				titanTransOrder
						.setBusinessinfo(orderRequest.getBussinessInfo());
				titanTransOrder.setGoodscnt(1);

				//设置费率信息
				if(StringUtil.isValidString(orderRequest.getReceivablefee())){
					titanTransOrder.setReceivablefee(Long.parseLong(orderRequest.getReceivablefee()));
				}
				if(StringUtil.isValidString(orderRequest.getReceivedfee())){
					titanTransOrder.setReceivedfee(Long.parseLong(orderRequest.getReceivedfee()));
				}
				if(StringUtil.isValidString(orderRequest.getStandfee())){
					titanTransOrder.setStandfee(Long.parseLong(orderRequest.getStandfee()));
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}

		return titanTransOrder;
	}

	/**
	 * 支付请求 转换为TitanOrderPayreq
	 *
	 * @param rechargeRequest
	 * @return
	 * @throws Exception
	 */
	private TitanOrderPayreq convertorToTitanOrderPayReq(
			RechargeRequest rechargeRequest) {
		TitanOrderPayreq titanOrderPayreq = null;
		try {
			titanOrderPayreq = new TitanOrderPayreq();
			titanOrderPayreq.setTransorderid(rechargeRequest.getTransorderid());
			titanOrderPayreq.setAmtType(rechargeRequest.getAmtType());
			titanOrderPayreq.setBankInfo(rechargeRequest.getBankInfo());
			titanOrderPayreq.setBusiCode(rechargeRequest.getBusiCode());
			if (rechargeRequest.getCharset() != null) {
				titanOrderPayreq.setCharset(Integer.parseInt(rechargeRequest
						.getCharset()));
			}
			titanOrderPayreq.setExecutionrate(null);
			titanOrderPayreq.setExpand(rechargeRequest.getExpand());
			titanOrderPayreq.setExpand2(rechargeRequest.getExpand2());
			titanOrderPayreq.setMerchantNo(rechargeRequest.getUserid());
			titanOrderPayreq.setNotifyUrl(rechargeRequest.getNotifyUrl());
			if (rechargeRequest.getOrderAmount() != null) {
				titanOrderPayreq.setOrderAmount(Double
						.parseDouble(rechargeRequest.getOrderAmount()));
			}
			if (rechargeRequest.getOrderExpireTime() != null) {
				titanOrderPayreq.setOrderExpireTime(rechargeRequest
						.getOrderExpireTime());
			}
			if (rechargeRequest.getOrderMark() != null) {
				titanOrderPayreq.setOrderMark(Integer.parseInt(rechargeRequest
						.getOrderMark()));
			}
			titanOrderPayreq.setOrderNo(rechargeRequest.getOrderNo());
			titanOrderPayreq.setOrderTime(rechargeRequest.getOrderTime());

			titanOrderPayreq.setPageUrl(rechargeRequest.getPageUrl());
			titanOrderPayreq.setPayerAcount(rechargeRequest.getPayerAcount());
			titanOrderPayreq.setPayerMail(rechargeRequest.getPayerMail());

			titanOrderPayreq.setPayerName(rechargeRequest.getPayerName());
			titanOrderPayreq.setPayerPhone(rechargeRequest.getPayerPhone());
			titanOrderPayreq.setPayType(rechargeRequest.getPayType());
			titanOrderPayreq.setProductDesc(rechargeRequest.getProductDesc());
			titanOrderPayreq.setProductName(rechargeRequest.getProductName());

			titanOrderPayreq.setProductNo(rechargeRequest.getProductNo());
			if (rechargeRequest.getProductNum() != null) {
				titanOrderPayreq.setProductNum(Integer.parseInt(rechargeRequest
						.getProductNum()));
			}
			titanOrderPayreq.setRatetype(rechargeRequest.getRatetype());
			titanOrderPayreq.setReceivablefee(rechargeRequest.getReceivablefee());
			titanOrderPayreq.setStandfee(rechargeRequest.getStandfee());
			titanOrderPayreq.setReceivedfee(rechargeRequest.getReceivedfee());
			titanOrderPayreq.setStandardrate(rechargeRequest.getStandardrate());
			titanOrderPayreq.setExecutionrate(rechargeRequest.getExecutionrate());
			// 本地秘钥加密还是
			String sign = getPaySigStr(rechargeRequest);
			if (sign != null) {
				titanOrderPayreq.setSignMsg(MD5.MD5Encode(sign, "UTF-8"));
			}

			if (rechargeRequest.getSignType() != null) {
				titanOrderPayreq.setSignType(Integer.parseInt(rechargeRequest
						.getSignType()));
			}
			
			titanOrderPayreq.setVersion(rechargeRequest.getVersion());

		} catch (Exception e) {
			log.error("支付请求 转换为TitanOrderPayreq:" + e.getMessage());
		}
		return titanOrderPayreq;
	}

	/**
	 * 对充值信息进行加密
	 *
	 * @param rechargeRequest
	 * @return
	 * @author fangdaikang
	 */
	private String getPaySigStr(RechargeRequest rechargeRequest) {
		StringBuffer sign = new StringBuffer();
		if (rechargeRequest != null) {
			sign.append("signType=");
			sign.append(rechargeRequest.getSignType());
			sign.append("&version=");
			sign.append(rechargeRequest.getVersion());
			sign.append("&merchantNo=");
			sign.append(rechargeRequest.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(rechargeRequest.getOrderNo());
			sign.append("&orderTime=");
			sign.append(rechargeRequest.getOrderTime());
			sign.append("&key=");
			sign.append(rechargeRequest.getKey());
			sign.append(")(*");
		}
		return sign.toString();
	}

	private HotelOrderSearchFacade getHotelOrderSearchFacade() {
		if (hotelOrderSearchFacade == null) {
			hotelOrderSearchFacade = hessianProxyBeanFactory
					.getHessianProxyBean(HotelOrderSearchFacade.class,
							ProxyFactoryConstants.orderServiceUrl
									+ "hotelOrderSearchFacade");
		}
		return hotelOrderSearchFacade;
	}

	private long getExpireTime(TitanOrderPayreq titanOrderPayreq) {
		Integer orderExpireTime = titanOrderPayreq.getOrderExpireTime();
		if (orderExpireTime != null && orderExpireTime > 0) {
			return DateUtil.getSeconds(orderExpireTime);
		} else {
			return DateUtil.getDaySeconds(CommonConstant.ORDER_EXPIRE_TIME);
		}
	}

	@Override
	public AllowNoPwdPayResponse saveAllowNoPwdPay(
			AllowNoPwdPayRequest allowNoPwdPayRequest) {
		AllowNoPwdPayResponse allowNoPwdPayResponse = new AllowNoPwdPayResponse();
		try {
			if (allowNoPwdPayRequest != null) {
				TitanAccount titanAccount = new TitanAccount();
				titanAccount.setAllownopwdpay(Integer
						.parseInt(allowNoPwdPayRequest.getStatus()));
				titanAccount.setUserid(allowNoPwdPayRequest.getUserid());
				titanAccountDao.update(titanAccount);
				allowNoPwdPayResponse.putSuccess();
				allowNoPwdPayResponse.putSysError();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return allowNoPwdPayResponse;
	}

	@Override
	public AllowNoPwdPayResponse isAllowNoPwdPay(
			JudgeAllowNoPwdPayRequest judgeAllowNoPwdPayRequest) {
		AllowNoPwdPayResponse allowNoPwdPayResponse = new AllowNoPwdPayResponse();
		if (judgeAllowNoPwdPayRequest != null
				&& StringUtil.isValidString(judgeAllowNoPwdPayRequest
						.getMoney())) {
			TitanAccountParam titanAccountParam = new TitanAccountParam();
			PaginationSupport<TitanAccount> paginationSupport = new PaginationSupport<TitanAccount>();
			titanAccountParam.setUserid(judgeAllowNoPwdPayRequest.getUserid());
			titanAccountParam.setAllownopwdpay(1);
			titanAccountDao.selectForPage(titanAccountParam, paginationSupport);
			if (paginationSupport.getItemList() != null
					&& paginationSupport.getItemList().size() > 0) {
				TitanAccount titanAccount = paginationSupport.getItemList()
						.get(0);
				if (titanAccount != null
						&& titanAccount.getNopwdpaylimit() != null) {
					// ？？有待讨论，和融数的整个数据交互式以分为单位，建议数据库不要使用double等数据形式
					BigDecimal allowNoPwdPay = new BigDecimal(
							titanAccount.getNopwdpaylimit());
					BigDecimal money = new BigDecimal(
							NumberUtil.covertToCents(judgeAllowNoPwdPayRequest
									.getMoney()));
					allowNoPwdPayResponse.setAllowNoPwdPay(false);
					if (allowNoPwdPay.compareTo(money) == 1) {
						allowNoPwdPayResponse.setAllowNoPwdPay(true);
					}
					allowNoPwdPayResponse.putSuccess();
					return allowNoPwdPayResponse;
				}
			}
		}
		allowNoPwdPayResponse.setAllowNoPwdPay(false);
		allowNoPwdPayResponse.putSysError();
		return allowNoPwdPayResponse;
	}


	@Override
	public TradeDetailResponse getTradeDetail(TradeDetailRequest tradeDetailRequest) {
        TradeDetailResponse tradeDetailResponse = new TradeDetailResponse();
        tradeDetailResponse.setTransOrders(new PaginationSupport<TransOrderDTO>());
        try {
            TitanTransOrderParam condition = new TitanTransOrderParam();
            MyBeanUtil.copyProperties(condition, tradeDetailRequest);
            if (null != tradeDetailRequest.getTradeTypeEnum()) {
            	if(TradeTypeEnum.RECHARGE_RECORD.getKey().equals(tradeDetailRequest.getTradeTypeEnum().getKey())){
            		condition.setTransordertype(TransOrderTypeEnum.RECHARGE.type);
            	}else if(TradeTypeEnum.WITHDRAW_RECORD.getKey().equals(tradeDetailRequest.getTradeTypeEnum().getKey())){
            		condition.setTransordertype(TransOrderTypeEnum.WITHDRAW.type);
            	}else{
            		condition.setTransordertype(TransOrderTypeEnum.PAYMENT.type);
            	}
                condition.setStatus(tradeDetailRequest.getTradeTypeEnum().getKey());
            }
            if (StringUtil.isValidString(tradeDetailRequest.getOrderAmount())) {
                condition.setTradeamount(Long.valueOf(NumberUtil.covertToCents(tradeDetailRequest.getOrderAmount())));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getOrderOperator())) {
                condition.setCreator(tradeDetailRequest.getOrderOperator());
            }
            if (StringUtil.isValidString(tradeDetailRequest.getAdmissionName())) {
                condition.setPartner(tradeDetailRequest.getAdmissionName());
            }
            if (StringUtil.isValidString(tradeDetailRequest.getIsEscrowedPayment())){
                condition.setIsEscrowedPayment(tradeDetailRequest.getIsEscrowedPayment());
            }
            if (StringUtil.isValidString(tradeDetailRequest.getUserOrderId())){
                condition.setUserorderid(tradeDetailRequest.getUserOrderId());
            }
            if (StringUtil.isValidString(tradeDetailRequest.getPayOrderNo())) {
                condition.setPayorderno(tradeDetailRequest.getPayOrderNo());
            }
            if(StringUtil.isValidString(tradeDetailRequest.getStatusId())){
            	condition.setStatusid(tradeDetailRequest.getStatusId());
            }
            PaginationSupport<TitanTransOrder> pgSupport = new PaginationSupport<TitanTransOrder>();
            pgSupport.setPageSize(tradeDetailRequest.getPageSize());
            pgSupport.setCurrentPage(tradeDetailRequest.getCurrentPage());
            pgSupport.setOrderBy("createtime desc");
            titanTransOrderDao.selectOrderForPage(condition, pgSupport);
            this.initTradeDetailResp(tradeDetailResponse, pgSupport);
            if (CollectionUtils.isNotEmpty(pgSupport.getItemList())) {//查询结果不为空。为空不能算出错
                for (TitanTransOrder titanTransOrder : pgSupport.getItemList()) {
                    TransOrderDTO transOrderDTO = new TransOrderDTO();
                    MyBeanUtil.copyProperties(transOrderDTO, titanTransOrder);
                    if (!StringUtil.isValidString(transOrderDTO.getUserid())) { //userId不合法
                        tradeDetailResponse.putErrorResult("USERID_INVALID", "查询结果中userId不合法");
                        return tradeDetailResponse;
                    }
                    if (isPayeeValid(transOrderDTO)) { //收款方存在时
                        if (isPayerValid(transOrderDTO)) {//付款方也存在
                            if (isPayeeOrg(tradeDetailRequest, transOrderDTO)) { //当前机构等于收款方
                                transOrderDTO.setTradeType("收款");
                                if(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID.equals(transOrderDTO.getPayermerchant())){//有待改进,将GDP默认商家放到数据库
                                	transOrderDTO.setTransTarget(transOrderDTO.getCreator());
                                }else{
                                	 transOrderDTO.setTransTarget(getTransTarget(transOrderDTO.getPayermerchant()));//付款方
                                }
								// 如果当前机构是收款方并且收款类型为商家联盟，则不考虑费率显示问题
								if (StringUtil.isValidString(titanTransOrder
										.getPayerType())) {
									PayerTypeEnum payerTypeEnum = PayerTypeEnum
											.getPayerTypeEnumByKey(titanTransOrder
													.getPayerType());

									// 如果是商家联盟的付款，则收款方不需要展示费率。
									if (payerTypeEnum != null
											&& (PayerTypeEnum.SUPPLY_UNION.key
													.equals(payerTypeEnum.key) || PayerTypeEnum.SUPPLY_FINACIAL.key
													.equals(payerTypeEnum.key))) {
										transOrderDTO.setReceivedfee(0L);
									}
								}

                            } else if (isPayerOrg(tradeDetailRequest, transOrderDTO)) {//当前机构等于付款方
                                transOrderDTO.setTradeType("付款");
                                transOrderDTO.setTransTarget(getTransTarget(transOrderDTO.getPayeemerchant()));//收款方
                            }
                        } else if (isPayeeOrg(tradeDetailRequest, transOrderDTO)) {//付款方不存在机构等于收款方
                            transOrderDTO.setTradeType("充值");
                            transOrderDTO.setTransTarget(getTransTarget(transOrderDTO.getPayeemerchant()));
                        }
                    } else if (!isPayeeValid(transOrderDTO)) {//收款方不存在
                        if (isPayerValid(transOrderDTO) && isPayerOrg(tradeDetailRequest, transOrderDTO)) {//付款方存在，机构等于付款方
                            transOrderDTO.setTransTarget(getTransTarget(transOrderDTO.getPayermerchant()));
                            transOrderDTO.setTradeType("提现");
                        }
                    }
                    tradeDetailResponse.getTransOrders().getItemList().add(transOrderDTO);
                }
                tradeDetailResponse.putSuccess();
                return tradeDetailResponse;
            }
        } catch (Exception e) {
            tradeDetailResponse.putSysError();
            log.error("查询交易明细失败" + e.getMessage(), e);
        }
        return tradeDetailResponse;
    }

	@Override
	public TradeDetailResponse getOrderTradeDetail(
			TradeDetailRequest tradeDetailRequest) {
		TradeDetailResponse tradeDetailResponse = new TradeDetailResponse();
		if (tradeDetailRequest == null
				|| (!StringUtil.isValidString(tradeDetailRequest
						.getBusinessordercode())
						&& !StringUtil.isValidString(tradeDetailRequest
								.getUserOrderId()) && !StringUtil
							.isValidString(tradeDetailRequest.getPayOrderNo()))) {
			tradeDetailResponse.putSysError();
			return tradeDetailResponse;
		}
		try {
			TradeDetailResponse tradeDetail = getTradeDetail(tradeDetailRequest);
			if (CollectionUtils.isNotEmpty(tradeDetail.getTransOrders()
					.getItemList())) {
				for (TransOrderDTO transOrderDTO : tradeDetail.getTransOrders()
						.getItemList()) {
					// 获取充值记录
					if (transOrderDTO.getTradeType().equals("收款")) {// 收款记录
						TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
						titanTransferDTO.setTransorderid(transOrderDTO
								.getTransid());
						titanTransferDTO.setUserrelateid(transOrderDTO.getUserrelateid());
						titanTransferDTO = titanOrderService
								.getTitanTransferDTO(titanTransferDTO);
						transOrderDTO.setTitanTransferDTO(titanTransferDTO);
						//查询退款
						transOrderDTO.setRefundDTO(this.getRefundDTO(transOrderDTO.getOrderid()));
					} else if (transOrderDTO.getTradeType().equals("付款")) {// 付款记录
						TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
						titanTransferDTO.setTransorderid(transOrderDTO
								.getTransid());
						//查询支付转帐单，而不是退款转帐单
						titanTransferDTO.setUserrelateid(transOrderDTO.getUserrelateid());
						titanTransferDTO = titanOrderService
								.getTitanTransferDTO(titanTransferDTO);

						TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
						titanOrderPayDTO.setTransorderid(transOrderDTO
								.getTransid());
						titanOrderPayDTO = titanOrderService
								.getTitanOrderPayDTO(titanOrderPayDTO);

						transOrderDTO.setTitanOrderPayDTO(titanOrderPayDTO);
						transOrderDTO.setTitanTransferDTO(titanTransferDTO);
						transOrderDTO.setRefundDTO(this.getRefundDTO(transOrderDTO.getOrderid()));

					} else if (transOrderDTO.getTradeType().equals("充值")) {// 获取提现记录
						TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
						titanOrderPayDTO.setTransorderid(transOrderDTO
								.getTransid());
						titanOrderPayDTO = titanOrderService
								.getTitanOrderPayDTO(titanOrderPayDTO);
						// 设置银行名称
						if (StringUtil.isValidString(titanOrderPayDTO
								.getBankInfo())) {
							CashierItemBankDTO cashierItemBankDTO = titanCashierDeskService
									.queryCashierItemBankDTOByBankName(titanOrderPayDTO
											.getBankInfo());
							if (cashierItemBankDTO != null) {
								transOrderDTO.setBankname(cashierItemBankDTO
										.getBankMark());
							}
						}
						transOrderDTO.setTitanOrderPayDTO(titanOrderPayDTO);
					} else if (transOrderDTO.getTradeType().equals("提现")) {// 提现记录
						TitanWithDrawDTO titanWithDrawDTO = new TitanWithDrawDTO();
						titanWithDrawDTO.setTransorderid(transOrderDTO
								.getTransid());
						titanWithDrawDTO = titanOrderService
								.getTitanWithDrawDTO(titanWithDrawDTO);
						transOrderDTO.setTitanWithDrawDTO(titanWithDrawDTO);
					}
				}
				tradeDetailResponse = tradeDetail;
				tradeDetailResponse.putSuccess();
				return tradeDetailResponse;
			} else {
				tradeDetailResponse.putSuccess();
				return tradeDetailResponse;
			}
		} catch (Exception e) {
			log.error("查询单个订单交易记录失败" + e.getMessage(), e);
		}
		tradeDetailResponse.putSysError();
		return tradeDetailResponse;
	}

	
	private RefundDTO getRefundDTO(String orderId){
		RefundDTO refundDTO = new RefundDTO();
		refundDTO.setOrderNo(orderId);
		List<RefundDTO> refundList = titanRefundDao.queryRefundDTO(refundDTO);
		if(refundList !=null && refundList.size()>0){
			return  refundList.get(0);
		}
		return null;
	}
	
	@Override
	public PaymentUrlResponse getPaymentUrl(PaymentUrlRequest paymentUrlRequest) {
		PaymentUrlResponse paymentUrlResponse = new PaymentUrlResponse();
		if (!GenericValidate.validate(paymentUrlRequest)) {
			log.error("参数校验失败");
			paymentUrlResponse.putParamError();
			return paymentUrlResponse;
		}
		// 生成一对秘钥
		String key = MD5.getSalt();
		// 保存加密私钥
		TitanDynamicKey titanDynamicKey = new TitanDynamicKey();
		titanDynamicKey.setEncryptionkey(key);
		titanDynamicKey.setPayorderno(paymentUrlRequest.getPayOrderNo());
		try {
			titanDynamicKeyDao.delete(titanDynamicKey);
			titanDynamicKeyDao.insert(titanDynamicKey);
		} catch (Exception e) {
			log.error("金融密钥设置失败", e);
			paymentUrlResponse.putErrorResult("dynamicKey_set_error",
					"金融密钥设置失败");
			return paymentUrlResponse;
		}
		// 构造参数列表拼接收银台地址
		StringBuffer cashierDeskURL = buildParamList(paymentUrlRequest);
		cashierDeskURL.append("&key=").append(key);
		String sign = MD5.MD5Encode(cashierDeskURL.toString(), "UTF-8");
		cashierDeskURL.delete(cashierDeskURL.indexOf("&key="),
				cashierDeskURL.length());

		cashierDeskURL.append("&sign=").append(sign);
		cashierDeskURL.insert(0, CommonConstant.REQUSET_URL);
		paymentUrlResponse.setUrl(cashierDeskURL.toString());
		paymentUrlResponse.putSuccess();
		return paymentUrlResponse;
	}

	private StringBuffer buildParamList(PaymentUrlRequest paymentUrlRequest) {
		StringBuffer paramList = new StringBuffer();
		if (StringUtil.isValidString(paymentUrlRequest.getMerchantcode())) {
			paramList.append("?merchantcode=").append(
					paymentUrlRequest.getMerchantcode());
		} else {
			paramList.append("?merchantcode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getFcUserid())) {
			paramList.append("&fcUserid=").append(
					paymentUrlRequest.getFcUserid());
		} else {
			paramList.append("&fcUserid=");
		}
		paramList.append("&payOrderNo=").append(
				paymentUrlRequest.getPayOrderNo());
		paramList.append("&paySource=")
				.append(paymentUrlRequest.getPaySource());

		if (StringUtil.isValidString(paymentUrlRequest.getOperater())) {
			paramList.append("&operater=").append(
					paymentUrlRequest.getOperater());
		} else {
			paramList.append("&operater=");
		}
		if (StringUtil
				.isValidString(paymentUrlRequest.getRecieveMerchantCode())) {
			paramList.append("&recieveMerchantCode=").append(
					paymentUrlRequest.getRecieveMerchantCode());
		} else {
			paramList.append("&recieveMerchantCode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getBusinessOrderCode())) {
			paramList.append("&businessOrderCode=").append(
					paymentUrlRequest.getBusinessOrderCode());
		} else {
			paramList.append("&businessOrderCode=");
		}
		if (StringUtil.isValidString(paymentUrlRequest.getNotifyUrl())) {
			paramList.append("&notifyUrl=").append(
					paymentUrlRequest.getNotifyUrl());
		} else {
			paramList.append("&notifyUrl=");
		}
		paramList.append("&isEscrowed=").append(
				paymentUrlRequest.getIsEscrowed());
		if (StringUtil.isValidString(paymentUrlRequest.getEscrowedDate())) {
			paramList.append("&escrowedDate=").append(
					paymentUrlRequest.getEscrowedDate());
		} else {
			paramList.append("&escrowedDate=");
		}
		return paramList;
	}


	// 验证是否需要修改订单
	private boolean validateIsUpdateOrder2(
			TitanPaymentRequest titanPaymentRequest, TransOrderDTO transOrderDTO)
			throws Exception {
		if (!NumberUtil.covertToCents(titanPaymentRequest.getPayAmount())
				.equals(transOrderDTO.getAmount().toString())) {
			// 直接将本地单作废重新生成新订单
			try {
				boolean flag = this.updateLocalOrder2(transOrderDTO
						.getTransid());
				if (flag) {
					return false;
				}
				throw new Exception();
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new Exception(e);
			}
		}
		return true;
	}

	// 修改本地落单
	private boolean updateLocalOrder2(Integer transid) throws Exception {
		TitanTransOrder titanTransOrder = new TitanTransOrder();
		try {
			titanTransOrder.setStatusid(OrderStatusEnum.ORDER_NO_EFFECT
					.getStatus());
			titanTransOrder.setTransid(transid);
			int row = titanTransOrderDao.update(titanTransOrder);
			if (row > 0) {
				return true;
			}

		} catch (Exception e) {
			log.error("修改本地订单失败" + e.getMessage());
			throw new Exception(e);
		}
		return false;
	}
	
	@Override
	public PayMethodConfigDTO getPayMethodConfigDTO(
			PayMethodConfigRequest payMethodConfigRequest) {
		try{
			PayMethodConfigDTO payMethodConfigDTO = null;
			String domainName = domainConfigDao.queryCurrentEnvDomain();
			if(StringUtil.isValidString(domainName)){
				payMethodConfigDTO = new PayMethodConfigDTO();
				payMethodConfigDTO.setPageurl("http://"+domainName+"/titanjr-pay-app/payment/payConfirmPage.action");
				payMethodConfigDTO.setNotifyurl("http://"+domainName+"/titanjr-pay-app/payment/notify.action");
			}
			return payMethodConfigDTO;
		}catch(Exception e){
			log.error("查询支付方式的配置出错"+e.getMessage(),e);
		}
		return null;
	}

	@Override
	public TransOrderUpdateResponse updateTransOrder(
			TransOrderUpdateRequest transOrderUpdateRequest) {
		TransOrderUpdateResponse updateResponse = new TransOrderUpdateResponse();
		if (!GenericValidate.validate(transOrderUpdateRequest)) {
			log.error("参数校验错误");
			updateResponse.putErrorResult("参数校验错误");
			return updateResponse;
		}
		TitanTransOrder transOrder = new TitanTransOrder();
		transOrder.setUserid(transOrderUpdateRequest.getUserId());
		transOrder.setUserorderid(transOrderUpdateRequest.getUserOrderId());
		transOrder.setRemark(transOrderUpdateRequest.getRemark());
		try {
			titanTransOrderDao.update(transOrder);
		} catch (Exception e) {
			log.error("交易单更新失败", e);
			updateResponse.putErrorResult("交易单更新失败");
			return updateResponse;
		}
		updateResponse.putSuccess();
		return updateResponse;
	}

	private void initTradeDetailResp(TradeDetailResponse tradeDetailResponse,
			PaginationSupport<TitanTransOrder> pgSupport) {
		tradeDetailResponse.getTransOrders().setPageSize(
				pgSupport.getPageSize());
		tradeDetailResponse.getTransOrders().setCurrentPage(
				pgSupport.getCurrentPage());
		tradeDetailResponse.getTransOrders().setOrderBy(pgSupport.getOrderBy());
		tradeDetailResponse.getTransOrders().setTotalCount(
				pgSupport.getTotalCount());
		tradeDetailResponse.getTransOrders().setTotalPage(
				pgSupport.getTotalPage());
		tradeDetailResponse.getTransOrders().setItemList(
				new ArrayList<TransOrderDTO>());
	}

	private boolean isPayerValid(TransOrderDTO transOrderDTO) {
		return StringUtil.isValidString(transOrderDTO.getPayermerchant());
	}

	private boolean isPayeeValid(TransOrderDTO transOrderDTO) {
		return StringUtil.isValidString(transOrderDTO.getPayeemerchant());
	}

	private boolean isPayerOrg(TradeDetailRequest tradeDetailRequest,
			TransOrderDTO transOrderDTO) {
		return tradeDetailRequest.getUserid().equals(
				transOrderDTO.getPayermerchant());
	}

	private boolean isPayeeOrg(TradeDetailRequest tradeDetailRequest,
			TransOrderDTO transOrderDTO) {
		return tradeDetailRequest.getUserid().equals(
				transOrderDTO.getPayeemerchant());
	}

	// 查询收款机构名称
	private String getTransTarget(String orgUserId) {
		String result = "";
		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
		organQueryRequest.setUserId(orgUserId);
		FinancialOrganResponse organResponse = titanFinancialOrganService
				.queryFinancialOrgan(organQueryRequest);
		if (organResponse.isResult()
				&& organResponse.getFinancialOrganDTO() != null) {
			result = organResponse.getFinancialOrganDTO().getOrgName();
			if (null != organResponse.getFinancialOrganDTO().getOrgBindInfo()
					&& StringUtil.isValidString(organResponse
							.getFinancialOrganDTO().getOrgBindInfo()
							.getMerchantName())) {
				result = result
						+ "-"
						+ organResponse.getFinancialOrganDTO().getOrgBindInfo()
								.getMerchantName();
			}
		}
		return result;
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
		if(out_trade_no!=null){
			if (mapLock.containsKey(out_trade_no)) {
				synchronized (mapLock.get(out_trade_no)) {
					mapLock.remove(out_trade_no).notifyAll();
				}
			}
		}
	}

	@Override
	public String getSign(
			RechargeResultConfirmRequest rechargeResultConfirmRequest) {
		StringBuffer stringBuffer = new StringBuffer();
		if (rechargeResultConfirmRequest != null) {
			stringBuffer.append("merchantNo=");
			stringBuffer.append(rechargeResultConfirmRequest.getMerchantNo());
			stringBuffer.append("&payType=");
			stringBuffer.append(rechargeResultConfirmRequest.getPayType());
			stringBuffer.append("&orderNo=");
			stringBuffer.append(rechargeResultConfirmRequest.getOrderNo());
			stringBuffer.append("&payOrderNo=");
			stringBuffer.append(rechargeResultConfirmRequest.getPayOrderNo());
			stringBuffer.append("&payStatus=");
			stringBuffer.append(rechargeResultConfirmRequest.getPayStatus());
			stringBuffer.append("&orderTime=");
			stringBuffer.append(rechargeResultConfirmRequest.getOrderTime());
			stringBuffer.append("&orderAmount=");
			stringBuffer.append(rechargeResultConfirmRequest.getOrderAmount());
			stringBuffer.append("&bankCode=");
			stringBuffer.append(rechargeResultConfirmRequest.getBankCode());
			stringBuffer.append("&orderPayTime=");
			stringBuffer.append(rechargeResultConfirmRequest.getOrderPayTime());
			stringBuffer.append("&key=");
			stringBuffer.append(RSInvokeConstant.rsCheckKey);
		}
		return stringBuffer.toString();
	}

	@Override
	public RepairTransferResponse getTransferOrders(
			RepairTransferRequest repairTransferRequest) {
		log.info("获取订单参数:" + JSONSerializer.toJSON(repairTransferRequest));
		RepairTransferResponse repairTransferResponse = new RepairTransferResponse();
		if (!StringUtil.isValidString(repairTransferRequest.getPayermerchant())
				&& !StringUtil.isValidString(repairTransferRequest.getStatus())) {
			repairTransferResponse.setResult(false);
			repairTransferResponse.setReturnMessage("参数错误");
			return repairTransferResponse;
		}

		List<RepairTransferDTO> repairTransferDTOList = titanTransOrderDao
				.queryTitanTransOrderByStatus(repairTransferRequest);
		repairTransferResponse.setResult(true);
		repairTransferResponse
				.setRepairTransferDTOListList(repairTransferDTOList);
		return repairTransferResponse;
	}

	@Override
	public void repairTransferOrder() {
		RepairTransferRequest repairTransferRequest = new RepairTransferRequest();
    	repairTransferRequest.setStatus(OrderStatusEnum.ORDER_FAIL.getStatus());
    	repairTransferRequest.setTransferStatus(TransferReqEnum.TRANSFER_SUCCESS.getStatus());
    	repairTransferRequest.setOrderPayStatus(ReqstatusEnum.RECHARFE_SUCCESS.getStatus());
    	repairTransferRequest.setPayermerchant(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);//GDP和平台所用的中间账户
    	RepairTransferResponse response = titanFinancialTradeService.getTransferOrders(repairTransferRequest);
    	if(!response.isResult()){
    		log.info(response.getReturnMessage());
    		return;
    	}
    	log.info("获取订单详情:"+JSONSerializer.toJSON(response));
    	List<RepairTransferDTO> repairTransferDTOList = response.getRepairTransferDTOListList();
    	if(repairTransferDTOList !=null && repairTransferDTOList.size()>0){
    		for(RepairTransferDTO repairTransferDTO :repairTransferDTOList){
    			if(StringUtil.isValidString(repairTransferDTO.getTransorderid()) 
    					&& StringUtil.isValidString(repairTransferDTO.getStatus())
    					&& "2".equals(repairTransferDTO.getStatus())){//其中2代表转账成功
    				continue;
    			}
    			
    			TransferRequest transferRequest = new TransferRequest();
        		transferRequest.setTransfertype(TransfertypeEnum.BRANCH_TRANSFER);								//1:子账户转账				
        		transferRequest.setConditioncode(ConditioncodeEnum.ADD_OEDER);								//1:落单
        		transferRequest.setRequestno(OrderGenerateService.genResquestNo());									//业务订单号
        		transferRequest.setRequesttime(DateUtil.sdf4.format(new Date()));				//请求时间
        		transferRequest.setAmount(repairTransferDTO.getTradeamount().toString());
        		if(repairTransferDTO.getReceivedfee() !=null){
        			BigDecimal amount  = new BigDecimal(repairTransferDTO.getTradeamount()).subtract(new BigDecimal(repairTransferDTO.getReceivedfee()));
        			transferRequest.setAmount(amount.toString());
        		}
        		transferRequest.setOrderid(repairTransferDTO.getOrderid());
        		transferRequest.setUserid(repairTransferDTO.getUserid());                                     //转出方用户Id	
        		transferRequest.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);							//转出方机构号
        		transferRequest.setProductId(repairTransferDTO.getProductid());							//转出方产品号
        		transferRequest.setUserfee("0");//转账费率始终为0
        		
        		transferRequest.setIntermerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);					//	接收方机构码
        		transferRequest.setInterproductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);						//	接收方产品号
        		transferRequest.setUserrelateid(repairTransferDTO.getPayeemerchant());	                    //接收方用户Id	
    		
        		
        		try {
        			TransferResponse  transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
				    if(!transferResponse.isResult()){
				    	continue ;
				    }
				    
				    log.info("转账的结果:"+JSONSerializer.toJSON(transferResponse));
				    OrderStatusEnum orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
				    //是否需要冻结
				    if(0==repairTransferDTO.getIsEscrowedPayment()){
				    	RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
				    	rechargeResultConfirmRequest.setOrderNo(repairTransferDTO.getOrderid());
				    	rechargeResultConfirmRequest.setPayAmount(transferRequest.getAmount());
				    	rechargeResultConfirmRequest.setUserid(transferRequest.getUserrelateid());
				    	rechargeResultConfirmRequest.setOrderAmount(transferRequest.getAmount());
				    	log.info("冻结入参:"+JSONSerializer.toJSON(rechargeResultConfirmRequest));
				    	FreezeAccountBalanceResponse freezeAccountBalanceResponse = titanFinancialAccountService.freezeAccountBalance(rechargeResultConfirmRequest);
				    	orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
				    	if(!freezeAccountBalanceResponse.isFreezeSuccess()){//冻结不成功
				    		orderStatusEnum = OrderStatusEnum.FREEZE_FAIL;
				    		OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(repairTransferDTO.getOrderid(), "冻结失败", OrderExceptionEnum.Freeze_Insert, "");
						    titanOrderService.saveOrderException(orderExceptionDTO);
						}
						log.info("修改单:"+JSONSerializer.toJSON(orderStatusEnum));
				    }
				   //修改订单
				    TransOrderDTO  transOrderDTO = new TransOrderDTO();
				    transOrderDTO.setStatusid(orderStatusEnum.getStatus());
				    transOrderDTO.setTransid(repairTransferDTO.getTransid());
					boolean updateStatus = titanOrderService.updateTransOrder(transOrderDTO);
					if(!updateStatus &&repairTransferDTO.getTransid() !=null){
						OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(repairTransferDTO.getTransid().toString(), "修改订单失败", OrderExceptionEnum.TransOrder_update, orderStatusEnum.getStatus());
						titanOrderService.saveOrderException(orderExceptionDTO);
					}
				   
					TransOrderRequest transOrderRequest = new TransOrderRequest();
					transOrderRequest.setUserorderid(repairTransferDTO.getUserorderid());
					transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
					
					
//				    transOrderDTO.setPayeemerchant(repairTransferDTO.getPayeemerchant());
//				    transOrderDTO.setUserorderid(repairTransferDTO.getUserorderid());
//				    transOrderDTO.setUserrelateid(repairTransferDTO.getUserrelateid());
//				    transOrderDTO.setCreator(repairTransferDTO.getCreator());
//				    transOrderDTO.setPayorderno(repairTransferDTO.getPayorderno());
//				    transOrderDTO.setNotifyUrl(repairTransferDTO.getNotifyUrl());
//				    transOrderDTO.setBusinessordercode(repairTransferDTO.getBusinessordercode());
				    log.info("回调:"+JSONSerializer.toJSON(transOrderDTO));
				    titanFinancialTradeService.confirmFinance(transOrderDTO);
				    
        		} catch (Exception e) {
					e.printStackTrace();
				}
    		   
    		}
    	}
		
	}

	@Override
	public TransOrderCreateResponse saveTitanTransOrder(
			TitanOrderRequest titanOrderRequest) {

		//检查订单是否存在，如果已
		TransOrderCreateResponse localOrderResponse = checkTitanTransOrder(titanOrderRequest);

		if (localOrderResponse == null) {

			localOrderResponse = new TransOrderCreateResponse();

			TitanTransOrder titanTransOrder = new TitanTransOrder();

			if (!StringUtil.isValidString(titanOrderRequest.getUserId())
					&& !StringUtil
							.isValidString(titanOrderRequest.getRuserId())) {
				log.info("the param is error");
				localOrderResponse.putParamError();
				return localOrderResponse;
			}

			//设置收付款方信息
			localOrderResponse = this.setBaseUserInfo(titanOrderRequest,
					titanTransOrder);
			if (!localOrderResponse.isResult()) {
				log.info("save base userinfo is fail");
				return localOrderResponse;
			}

			titanTransOrder.setGoodscnt(1);
			titanTransOrder.setTradeamount(Long.parseLong(NumberUtil
					.covertToCents(titanOrderRequest.getAmount())));
			titanTransOrder.setPayorderno(titanOrderRequest.getGoodsId());
			titanTransOrder.setGoodsdetail(titanOrderRequest.getGoodsDetail());
			titanTransOrder.setGoodsname(titanOrderRequest.getGoodsName());
			titanTransOrder.setNotifyUrl(titanOrderRequest.getNotify());
			titanTransOrder.setCreator(titanOrderRequest.getName());
			titanTransOrder.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
			titanTransOrder.setPayerType(titanOrderRequest.getPayerType());
			titanTransOrder.setStatusid(OrderStatusEnum.ORDER_IN_PROCESS
					.getStatus());
			titanTransOrder.setCreatetime(new Date());
			titanTransOrder.setAmount((long) 0);
			titanTransOrder.setUserorderid(OrderGenerateService
					.genSyncUserOrderId());
			if (null != titanOrderRequest.getBusinessInfo()) {
				// 如果指定了业务编码则将编号并入库
				titanTransOrder.setBusinessordercode(titanOrderRequest
						.getBusinessInfo().get("bussCode"));
				titanTransOrder.setBusinessinfo(JSONSerializer.toJSON(
						titanOrderRequest.getBusinessInfo()).toString());
			}
			try {
				titanTransOrder.setIsEscrowedPayment("1");
				if (StringUtil.isValidString(titanOrderRequest
						.getEscrowedDate())) {
					log.info("should escrowed payment");
					titanTransOrder.setIsEscrowedPayment("0");
					titanTransOrder.setEscrowedDate(DateUtil.sdf
							.parse(titanOrderRequest.getEscrowedDate()));
				}

				boolean isSuccess = titanTransOrderDao.insert(titanTransOrder) > 0 ? true
						: false;
				localOrderResponse.setOrderNo(titanTransOrder.getUserorderid());
				localOrderResponse.setTransId(titanTransOrder.getTransid());
				localOrderResponse.putSuccess();
				if (!isSuccess) {
					log.info("save order info is fail");
					localOrderResponse.putErrorResult("订单信息保存失败");
				}
			} catch (Exception e) {
				log.error("订单信息保存失败:" + e.getMessage());
				localOrderResponse.putErrorResult("订单下单失败");
			}
		}
		return localOrderResponse;
	}

	/**
	 * 更新订单中携带的业务信息
	 * 
	 * @param newBusinessInfo
	 * @throws ParseException 
	 */
	private void updateTransOrderBussInfo(Integer transId,
			TitanOrderRequest titanOrderRequest , String oldBussInfo) {

		if (StringUtil.isValidString(JsonConversionTool.toJson(titanOrderRequest
				.getBusinessInfo()))){
			try{
				TitanTransOrder titanTransOrder = new TitanTransOrder();
				titanTransOrder.setTransid(transId);
				Map<String, String> upBussMap = null;

				Map<String, String> newBussMap = JsonConversionTool.toObject(
						JsonConversionTool.toJson(titanOrderRequest
								.getBusinessInfo()), Map.class);

				if (newBussMap != null && newBussMap.get("bussCode") != null) {
					titanTransOrder
							.setBusinessordercode(newBussMap.get("bussCode"));
				}

				if (StringUtil.isValidString(oldBussInfo)) {
					upBussMap = JsonConversionTool.toObject(oldBussInfo,
							Map.class);
					upBussMap.putAll(newBussMap);
				}
				if (upBussMap != null) {
					titanTransOrder.setBusinessinfo(JsonConversionTool
							.toJson(upBussMap));
				}
				titanTransOrder.setNotifyUrl(titanOrderRequest.getNotify());
				titanTransOrder.setCreator(titanOrderRequest.getName());
				titanTransOrder.setGoodsdetail(titanOrderRequest.getGoodsDetail());
				titanTransOrder.setGoodsname(titanOrderRequest.getGoodsName());
				titanTransOrder.setIsEscrowedPayment("1");
				if(StringUtil.isValidString(titanOrderRequest.getEscrowedDate())){
					titanTransOrder.setEscrowedDate(DateUtil.sdf.parse(titanOrderRequest.getEscrowedDate()));
					titanTransOrder.setIsEscrowedPayment("0");
				}
				titanTransOrder.setPayerType(titanOrderRequest.getPayerType());
				titanTransOrderDao.update(titanTransOrder);
			}catch(Exception e){
				log.error("更新订单失败",e);
			}
		
		}
	}

	/**
	 * 检查订单是否已经存在，存在检查是否支付成功，支付成功就再次回调，
	 * 支付不成功则查看该订单支付金额是否改变，融数落单且超过45分钟，则返回null，
	 * 如果未在融数落单，或者已经有充值成功的单，或在融数落单且在45分钟内，则返回原单号
	 * 
	 * @param titanOrderRequest
	 * @return
	 */
	private TransOrderCreateResponse checkTitanTransOrder(
			TitanOrderRequest titanOrderRequest) {
		
		TransOrderCreateResponse orderCreateResponse = new TransOrderCreateResponse();
		// 根据付款者身份类型和业务订单号确认其是否在本系统产生过订单。
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setPayorderno(titanOrderRequest.getGoodsId());
		transOrderRequest.setPayertype(titanOrderRequest.getPayerType());
		
		TransOrderDTO transOrderDTO = titanOrderService
				.queryTransOrderDTO(transOrderRequest);

		// 表明业务订单号已经重复提交
		if (null != transOrderDTO) {
			if (OrderStatusEnum.isPaySuccess(transOrderDTO
					.getStatusid())) {

				orderCreateResponse.setResult(false);
				orderCreateResponse.setReturnCode(""
						+ TitanMsgCodeEnum.PAY_ORDER_SUCCESS.getCode());
				orderCreateResponse
						.setReturnMessage(TitanMsgCodeEnum.PAY_ORDER_SUCCESS
								.getResMsg());
				try {
					if(titanOrderRequest.getBusinessInfo()!=null 
							&& StringUtil.isValidString(titanOrderRequest.getBusinessInfo().get("bussCode"))){
						transOrderDTO.setBusinessordercode(titanOrderRequest.getBusinessInfo().get("bussCode"));
					}
					this.confirmFinance(transOrderDTO);
				} catch (Exception e) {
					log.error("回调失败");
				}
				return orderCreateResponse;
			}
			orderCreateResponse.setTransId(transOrderDTO.getTransid());
			// 金额不一致,则直接将订单设置为失效
			if (!NumberUtil.covertToCents(titanOrderRequest.getAmount())
					.equals("" + transOrderDTO.getTradeamount())) {
				log.info("order amount happen change.");
				updateOrderNoEffect(transOrderRequest.getTransid());
				return null;
			}

			// 如果在融数端未产生订单号并且状态处理中的则直接返回单号
			if (!StringUtil.isValidString(transOrderDTO.getOrderid())) {
				log.info("order status process ing ."
						+ transOrderDTO.getUserorderid());

				updateTransOrderBussInfo(transOrderDTO.getTransid(),
						titanOrderRequest,
						transOrderDTO.getBusinessinfo());

				orderCreateResponse.setOrderNo(transOrderDTO.getUserorderid());
				orderCreateResponse.putSuccess();
				return orderCreateResponse;
			}

			// 如果在融数已经存在订单并且，订单状态为处理中的，则需要考虑订单在融数端是否超时
			if (StringUtil.isValidString(transOrderDTO.getOrderid())) {
				
				if (OrderStatusEnum.isRepeatedPay(transOrderDTO.getStatusid())) {
					// 查询融数网关支付的订单
					TitanOrderPayreq titanOrderPayreq = new TitanOrderPayreq();
					titanOrderPayreq
							.setTransorderid(transOrderDTO.getTransid());
					titanOrderPayreq = queryOrderPayReqByTransOrderId(titanOrderPayreq);

					//payOrder is null or payOrder is recharge success
					if (null == titanOrderPayreq ||ReqstatusEnum.RECHARFE_SUCCESS.getStatus()==titanOrderPayreq.getReqstatus().intValue()) {// 可能是余额转账
						
						updateTransOrderBussInfo(transOrderDTO.getTransid(),
								titanOrderRequest,
								transOrderDTO.getBusinessinfo());
						
						orderCreateResponse.setOrderNo(transOrderDTO
								.getUserorderid());
						orderCreateResponse.putSuccess();
						return orderCreateResponse;
					}
					
					// 获取订单时间跟当前时间的差
					long times = DateUtil.diffSecondByTime(
							titanOrderPayreq.getOrderTime(),
							DateUtil.sdf5.format(new Date()));
					if (times < this.getExpireTime(titanOrderPayreq
							.getOrderExpireTime())) {// 未过期 获取当前单号
						log.info("order no expire."
								+ titanOrderPayreq.getOrderNo());
						
						updateTransOrderBussInfo(transOrderDTO.getTransid(),
								titanOrderRequest,
								transOrderDTO.getBusinessinfo());
						
						orderCreateResponse.setOrderNo(transOrderDTO
								.getUserorderid());
						orderCreateResponse.putSuccess();
						return orderCreateResponse;
					} else {
						log.info("order already expire.");
						// 订单已经过期
						updateOrderNoEffect(transOrderRequest.getTransid());
					}
				} 
			}

		}
		return null;
	}

	private long getExpireTime(Integer orderExpireTime) {
		// Integer orderExpireTime = titanOrderPayreq.getOrderExpireTime();
		if (orderExpireTime != null && orderExpireTime > 0) {
			return DateUtil.getSeconds(orderExpireTime);
		} else {
			return DateUtil.getDaySeconds(CommonConstant.ORDER_EXPIRE_TIME);
		}
	}

	// 修改本地落单
	private boolean updateOrderNoEffect(Integer transid) {
		TitanTransOrder titanTransOrder = new TitanTransOrder();
		try {
			titanTransOrder.setStatusid(OrderStatusEnum.ORDER_NO_EFFECT
					.getStatus());
			titanTransOrder.setTransid(transid);
			titanTransOrderDao.update(titanTransOrder);
		} catch (Exception e) {
			log.error("修改本地订单失败");
		}
		return false;
	}

	/**
	 * 保存收付款方相关信息
	 * 1.付款方不为空且为FcuserId，则查询在金融系统中对应的userId，和merchcode，将相关信息封装
	 * 2.如果付款方不为空，且为金融机构号，则将付款方设置金融账户
	 * 3.收款方为merchcode,设置相关收款方信息，如果为GDP或者TTMALL则设置中间账户为付款方
	 * 4.收款方位金融机构号设置相关信息
	 * 5.如果为对外开关平台，则设置收付款相关信息
	 * 
	 * @param titanTransOrder
	 * @param titanTransOrder
	 * @return
	 */
	private TransOrderCreateResponse setBaseUserInfo(
			TitanOrderRequest titanOrderRequest, TitanTransOrder titanTransOrder) {
		TransOrderCreateResponse response = new TransOrderCreateResponse();
		response.putSuccess();
		PayerTypeEnum payerTypeEnum = PayerTypeEnum
				.getPayerTypeEnumByKey(titanOrderRequest.getPayerType());
		titanTransOrder.setTransordertype(TransOrderTypeEnum.PAYMENT.type);
		
		if(payerTypeEnum.isB2BPayment()){//B2B端支付
			OrgBindInfo orgBindInfo = this
					.queryOrgBindInfo(titanOrderRequest.getRuserId());
			if (orgBindInfo == null) {
				response.putErrorResult("接收方机构不存在");
				return response;
			}
			titanTransOrder.setMerchantcode(titanOrderRequest.getRuserId());
			titanTransOrder.setUserid(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
			titanTransOrder.setProductid(RSInvokeConstant.DEFAULTPAYERCONFIG_PRODUCTID);
			titanTransOrder.setPayermerchant(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
			titanTransOrder.setPayeemerchant(orgBindInfo.getUserid());
			titanTransOrder.setUserrelateid(orgBindInfo.getUserid());
			return response;
		}
		
		if(payerTypeEnum.isFcUserId()){//财务端付款
			TitanUserBindInfoDTO titanUserBindInfoDTO = this.getBindInfoDTO(titanOrderRequest.getUserId());
			if (titanUserBindInfoDTO == null
					|| titanUserBindInfoDTO.getTfsuserid() == null) {
				response.putSysError();
				return response;
			}
			TitanUser titanUser = titanUserDao.selectTitanUser(titanUserBindInfoDTO.getTfsuserid());
			if(titanUser == null){
				response.putSysError();
				return response;
			}
			
			titanTransOrder.setMerchantcode(titanUserBindInfoDTO.getMerchantcode());
			titanTransOrder.setUserid(titanUser.getUserid());
			titanTransOrder.setPayermerchant(titanUser.getUserid());
			titanTransOrder.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			
			if(payerTypeEnum.isSupplyUnion()){//财务端商家联盟
				OrgBindInfo orgBindInfo = this
						.queryOrgBindInfo(titanOrderRequest.getRuserId());
				if (orgBindInfo == null) {
					response.putErrorResult("接收方机构不存在");
					return response;
				}
				titanTransOrder.setPayeemerchant(orgBindInfo.getUserid());
				titanTransOrder.setUserrelateid(orgBindInfo.getUserid());
			}
			Map<String, String> bussinessInfo = titanOrderRequest
					.getBusinessInfo();
			// 接入方的用户在我们系统中对应我们金融Id的唯一标识
			bussinessInfo.put("partnerId", titanOrderRequest.getUserId());
			return response;
		}
		
		if(payerTypeEnum.isRechargeCashDesk()){//充值
			titanTransOrder.setUserid(titanOrderRequest.getRuserId());
			titanTransOrder.setPayeemerchant(titanOrderRequest.getRuserId());
			titanTransOrder.setUserrelateid(titanOrderRequest.getRuserId());
			titanTransOrder.setTransordertype(TransOrderTypeEnum.RECHARGE.type);
			titanTransOrder.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			return response;
		}
		
		if(payerTypeEnum.isWithdraw()){//提现的收付款方
			titanTransOrder.setUserid(titanOrderRequest.getUserId());
			titanTransOrder.setPayermerchant(titanOrderRequest.getRuserId());
			titanTransOrder.setTransordertype(TransOrderTypeEnum.RECHARGE.type);
			titanTransOrder.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			return response;
		}
		
		if(payerTypeEnum.isOpenOrg()){//对外开放收银台
			titanTransOrder.setUserid(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
			titanTransOrder.setProductid(RSInvokeConstant.DEFAULTPAYERCONFIG_PRODUCTID);
			titanTransOrder.setPayermerchant(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
			titanTransOrder.setTransordertype(TransOrderTypeEnum.PAYMENT.type);
			titanTransOrder.setUserrelateid(titanOrderRequest.getRuserId());
			titanTransOrder.setPayeemerchant(titanOrderRequest.getRuserId());
			return response;
		}
		
		if(payerTypeEnum.isTtMall()){
			titanTransOrder.setMerchantcode(titanOrderRequest.getRuserId());
			titanTransOrder.setUserid(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
			titanTransOrder.setProductid(RSInvokeConstant.DEFAULTPAYERCONFIG_PRODUCTID);
			titanTransOrder.setPayermerchant(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
			OrgBindInfo orgBindInfo = this
					.queryOrgBindInfo(titanOrderRequest.getRuserId());
			if (orgBindInfo == null) {
				response.putErrorResult("接收方机构不存在");
				return response;
			}
			titanTransOrder.setPayeemerchant(orgBindInfo.getUserid());
			titanTransOrder.setUserrelateid(orgBindInfo.getUserid());
			return response;
		}
		
		if(payerTypeEnum.isLoan()){
			titanTransOrder.setUserid(titanOrderRequest.getUserId());
			titanTransOrder.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID_230);
			titanTransOrder.setPayermerchant(titanOrderRequest.getUserId());
			titanTransOrder.setPayeemerchant(titanOrderRequest.getRuserId());
			titanTransOrder.setUserrelateid(titanOrderRequest.getRuserId());
			return response;
		}
		response.putSysError();
		return response;
	}
	
	private TitanUserBindInfoDTO getBindInfoDTO(String userId){
		TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
		titanUserBindInfoDTO.setFcuserid(Long
				.parseLong(userId));
		return titanFinancialUserService
				.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
	}

	private OrgBindInfo queryOrgBindInfo(String merchantCode) {
		OrgBindInfo orgBindInfo = new OrgBindInfo();
		orgBindInfo.setMerchantCode(merchantCode);
		return titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
	}

	@Override
	public QrCodeResponse getQrCodeUrl(RechargeDataDTO rechargeDataDTO) {
		QrCodeResponse qrCodeResponse = new QrCodeResponse();
		try {
			List<NameValuePair> params = this.getCommonHttpParams(rechargeDataDTO);
			log.info("微信调用网关接口参数:" + JSONSerializer.toJSON(params) );
			
			   HttpPost httpPost = new HttpPost(rechargeDataDTO.getGateWayUrl());
//		        httpPost.setConfig(requestConfig);
		        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			HttpResponse resp = HttpClient.httpRequest(params, httpPost);
			if(resp == null){
				log.error("调用融数网关失败");
				qrCodeResponse.putErrorResult("调用融数网关失败");
				return qrCodeResponse;
			}
			
			HttpEntity entity = resp.getEntity();
			String response = EntityUtils.toString(entity);
			
			if(!StringUtil.isValidString(response)){
				log.error("融数网关返回信息为空");
				qrCodeResponse.putErrorResult("融数网关返回信息为空");
				return qrCodeResponse;
			}
			
			//解析response
			QrCodeDTO qr = new QrCodeDTO();
			qr = (QrCodeDTO)RSConvertFiled2ObjectUtil.convertField2Object(qr.getClass(), response);
			String payUrl =null;
			if(response.indexOf("weixin")!=-1){
				payUrl = response.substring(response.indexOf("weixin"), response.length());
			}
			qr.setRespJs(payUrl);
			log.info("网关返回参数:"+JSONSerializer.toJSON(qr));
			boolean sign = this.validateGateSign(qr);
			if(!sign){
				log.error("网关返回签名失败");
				qrCodeResponse.putErrorResult("签名验证失败");
				return qrCodeResponse;
			}
			
			if(!StringUtil.isValidString(qr.getRespJs())){
				log.error("网关返回参数异常"+qr.getRespJs());
				qrCodeResponse.putErrorResult("网关返回参数异常");
				return qrCodeResponse;
			}
			
			qrCodeResponse.putSuccess();
			qrCodeResponse.setQrCodeDTO(qr);
			return qrCodeResponse;
			
		} catch (Exception e) {
			log.error("系统异常",e);
			qrCodeResponse.putErrorResult("系统异常");
		}
		return qrCodeResponse;
	}
	
	
	protected <T> List<NameValuePair> getCommonHttpParams(T t) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		Field[] cls =  t.getClass().getDeclaredFields();
		for(Field field : cls){
			field.setAccessible(true);  
			if(field.get(t) !=null){
				params.add(new BasicNameValuePair(field.getName(), field.get(t).toString()));
			}else{
				params.add(new BasicNameValuePair(field.getName(), null));
			}
			
		}
		return params;
	}
	
	private boolean validateGateSign(QrCodeDTO qr){
	    StringBuffer signStr = new StringBuffer("merchantNo="+qr.getMerchantNo());
	    signStr.append("&orderNo="+qr.getOrderNo());
	    signStr.append("&orderAmount="+qr.getOrderAmount());
	    signStr.append("&orderTime="+qr.getOrderTime());
	    signStr.append("&payType="+qr.getPayType());
	    signStr.append("&key="+RSInvokeConstant.rsCheckKey);
	    String Md5Str = MD5.MD5Encode(signStr.toString(), "UTF-8");
	    if(Md5Str.equals(qr.getSignMsg())){//51c13905fec3d21223b43f0cf99a8bcf
	    	return true;
	    }
	    return false;
	}
	
	@Override
	public OrderSaveAndBindCardResponse saveTransOrderAndBindCard(
			OrderSaveAndBindCardRequest request) {
		OrderSaveAndBindCardResponse orderSaveAndBindCardResponse = new OrderSaveAndBindCardResponse();
		if(null == request){
			orderSaveAndBindCardResponse.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
			return orderSaveAndBindCardResponse;
		}
		
		OrderSaveWithCardRequest orderSaveWithCardRequest = this.convertToOrderSaveWithCardRequest(request);
		OrderSaveWithCardResponse orderSaveWithCardResponse = rsAccTradeManager.orderSaveWithdraw(orderSaveWithCardRequest);
		
		if(CommonConstant.OPERATE_SUCCESS.equals(orderSaveWithCardResponse.getOperateStatus())){
			orderSaveAndBindCardResponse.setOrderId(orderSaveWithCardResponse.getOrderId());
			
			orderSaveAndBindCardResponse.putSuccess();
			return orderSaveAndBindCardResponse;
		}
		
		log.error("落单+绑卡失败:"+orderSaveWithCardResponse.getReturnMsg());
		orderSaveAndBindCardResponse.putErrorResult(TitanMsgCodeEnum.OPER_ORDER_AND_BIND_CARD_FAIL);
		return orderSaveAndBindCardResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	private OrderSaveWithCardRequest convertToOrderSaveWithCardRequest(OrderSaveAndBindCardRequest request){
		OrderSaveWithCardRequest orderSaveWithCardRequest = new OrderSaveWithCardRequest();
		orderSaveWithCardRequest.setAccountname(request.getAccountName());
		orderSaveWithCardRequest.setAccountnumber(request.getAccountNumber());
		orderSaveWithCardRequest.setAccountproperty(request.getAccountProperty());
		orderSaveWithCardRequest.setAccountpurpose(request.getAccountPurpose());
		orderSaveWithCardRequest.setAccounttypeid(request.getAccountTypeId());
		orderSaveWithCardRequest.setAmount(request.getAmount());
		
		orderSaveWithCardRequest.setBankhead(request.getBankHead());
		orderSaveWithCardRequest.setBankheadname(request.getBankHeadName());
		orderSaveWithCardRequest.setCertificatenumber(request.getCertificateNumber());
		orderSaveWithCardRequest.setCertificatetype(request.getCertificateType());
		orderSaveWithCardRequest.setConstid(request.getConstId());
		orderSaveWithCardRequest.setCurrency(request.getCurrency());
		
		orderSaveWithCardRequest.setGoodsdetail(request.getGoodsDetail());
		orderSaveWithCardRequest.setGoodsname(request.getGoodsName());
		orderSaveWithCardRequest.setOrdertypeid(request.getOrderTypeId());
		orderSaveWithCardRequest.setOrdertime(request.getOrderTime());
		
		orderSaveWithCardRequest.setProductid(request.getProductId());
		orderSaveWithCardRequest.setUserid(request.getUserId());
		orderSaveWithCardRequest.setUserorderid(request.getUserOrderId());
		
		return orderSaveWithCardRequest;
	}
	
	@Override
	public ConfirmOrdernQueryResponse ordernQuery(
			ConfirmOrdernQueryRequest confirmOrdernQueryRequest) {
		
		ConfirmOrdernQueryResponse response = new ConfirmOrdernQueryResponse();
		OrdernQueryRequest ordernQueryRequest = new OrdernQueryRequest();
		ordernQueryRequest.setOrderno(confirmOrdernQueryRequest.getOrderNo());
		ordernQueryRequest.setMerchantcode(confirmOrdernQueryRequest.getMerchantcode());
		
		OrdernQueryResponse ordernQueryResponse = rsAccTradeManager.ordernQuery(ordernQueryRequest);
		if(!ordernQueryResponse.isSuccess()){
			log.error("查询订单失败:"+ordernQueryResponse.getReturnCode()+":"+ordernQueryResponse.getReturnMsg());
			response.putErrorResult(ordernQueryResponse.getReturnCode(), ordernQueryResponse.getReturnMsg());
		    return response;
		}
		
		List<Transorderinfo> transorderinfos = ordernQueryResponse.getTransorderinfo();
		List<TransOrderInfo> orderInfo = new ArrayList<TransOrderInfo>();
		for(Transorderinfo info :transorderinfos){
			TransOrderInfo order = new TransOrderInfo();
			MyBeanUtil.copyBeanProperties(order, info);
			orderInfo.add(order);
		}
		response.setTransOrderInfos(orderInfo);
		response.putSuccess();
		return response;
	}
	
}

