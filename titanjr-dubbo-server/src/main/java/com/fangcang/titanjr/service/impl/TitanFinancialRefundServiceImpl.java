package com.fangcang.titanjr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import com.fangcang.titanjr.dto.bean.*;
import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.bean.CallBackInfo;
import com.fangcang.titanjr.common.bean.NotifyBean;
import com.fangcang.titanjr.common.enums.ConditioncodeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.RefundStatusEnum;
import com.fangcang.titanjr.common.enums.RefundTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TransferReqEnum;
import com.fangcang.titanjr.common.enums.TransfertypeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.common.util.httpclient.TitanjrHttpTools;
import com.fangcang.titanjr.dao.TitanFundFreezereqDao;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.dao.TitanTransOrderDao;
import com.fangcang.titanjr.dao.TitanTransferReqDao;
import com.fangcang.titanjr.dao.TitanUserDao;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundOrderRequest;
import com.fangcang.titanjr.dto.request.TitanJrRefundRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.RefundOrderResponse;
import com.fangcang.titanjr.dto.response.TitanJrRefundResponse;
import com.fangcang.titanjr.entity.TitanRefund;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.SignTypeEnum;
import com.fangcang.titanjr.enums.RsVersionEnum;
import com.fangcang.titanjr.rs.manager.RSAccTradeManager;
import com.fangcang.titanjr.rs.manager.impl.InvokeLogRecordManager;
import com.fangcang.titanjr.rs.request.AccountTransferRequest;
import com.fangcang.titanjr.rs.request.RSRefundRequest;
import com.fangcang.titanjr.rs.response.AccountTransferResponse;
import com.fangcang.titanjr.rs.response.RsRefundResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialRefundService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

@Service("titanFinancialRefundService")
public class TitanFinancialRefundServiceImpl implements
		TitanFinancialRefundService {
	
	@Resource
	private TitanTransferReqDao titanTransferReqDao;
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource 
	TitanFundFreezereqDao titanFundFreezereqDao;
	
	@Resource 
	private TitanFinancialAccountService titanFinancialAccountService;
	
	@Resource
	private RSAccTradeManager rsAccTradeManager;
	
	@Resource
	private TitanRefundDao titanRefundDao;
	
	@Resource
	private TitanTransOrderDao titanTransOrderDao;
	
	@Resource 
	private TitanUserDao titanUserDao;
	
	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	
	@Resource
	private InvokeLogRecordManager invokeLogRecordManager;
	
	@Resource
	private TitanFinancialUtilService utilService;
	
	
	@SuppressWarnings("unused")
	private static Map<String,Object> mapLock = new  ConcurrentHashMap<String, Object>();
	
	private static final Log log = LogFactory.getLog(TitanFinancialRefundServiceImpl.class);


	@Override
	public TitanJrRefundResponse refund(TitanJrRefundRequest refundRequest) {
		TitanJrRefundResponse response = new TitanJrRefundResponse();
		log.info("发起退款，请求参数refundRequest："+Tools.gsonToString(refundRequest));
		//标识是否需要再次冻结商户资金
		boolean isFreeze = false;
		try {
			if (null == refundRequest || !StringUtil.isValidString(refundRequest.getPayOrderNo())) {
				response.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
				isFreeze = true;
				return response;
			}
			
			//查询是否已经退款
			log.info("6.1.验证是否可以退款");
			TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
			titanTransferDTO.setUserid(refundRequest.getUserId());
			titanTransferDTO.setUserrelateid(refundRequest.getUserRelateId());
			titanTransferDTO.setPayOrderNo(refundRequest.getPayOrderNo());
			List<TitanTransferDTO> titanTransferDTOList = titanOrderService.getTitanTransferDTOList(titanTransferDTO);
			if (null != titanTransferDTOList) {//如果存在退款转账订单，则判断其是否成功
				titanTransferDTO = titanTransferDTOList.get(0);
				log.info("用户退款，订单号orderid:"+titanTransferDTO.getTransorderid());
				if (titanTransferDTO.getStatus().intValue() == TransferReqEnum.TRANSFER_SUCCESS.getStatus()) {
					log.error("退款已完成，请勿重复退款,Transorderid:"+titanTransferDTO.getTransorderid());
					response.putErrorResult(TitanMsgCodeEnum.REFUND_SUCCESSED);
					isFreeze = true;
					return response;
				}
			}

			log.info("6.2保存一张退款转账单，进行收款账户余额转账");
			AccountTransferRequest tradeTransferRequest = this.convertToTransferRequest(refundRequest);
			TitanTransferReq titanTransferReq = this.convertToTitanTransferReq(tradeTransferRequest);
			titanTransferReq.setTransorderid(refundRequest.getTransorderid());
			titanTransferReq.setPayorderno(refundRequest.getPayOrderNo());

			if (titanTransferDTOList == null) {//保存转账订单，
				int row = titanTransferReqDao.insert(titanTransferReq);
				if (row < 1) {
					log.error(refundRequest.getTransorderid()+"退款保存转账单失败");
					response.putErrorResult(TitanMsgCodeEnum.LOCAL_ADD_TRANSFER_FAIL);
					isFreeze = true;
					return response;
				}
				//后面会用这个ID更新订单
				titanTransferDTO.setTransorderid(titanTransferReq.getTransorderid());
			} else {//现在这一步基本不会出现，因为对在恢复操作中对退款转账单做了物理删除
				if (null == titanTransferDTOList.get(0) || null == titanTransferDTOList.get(0).getTransferreqid()) {
					log.error(refundRequest.getTransorderid()+"退款单异常");
					response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
					isFreeze = true;
					return response;
				}
				titanTransferReq.setTransferreqid(titanTransferDTOList.get(0).getTransferreqid());
			}

			AccountTransferResponse accountTransferResponse = rsAccTradeManager.accountBalanceTransfer(tradeTransferRequest);
			if (!CommonConstant.OPERATE_SUCCESS.equals(accountTransferResponse.getOperateStatus())) {
				log.error("交易退款转账失败");
				response.putErrorResult(TitanMsgCodeEnum.REFUND_TRANSFER_FAIL);
				isFreeze = true;
				this.fixRefundProcess(null, null, titanTransferReq, null);
				return response;
			}
			refundRequest.setTransferAmount(tradeTransferRequest.getAmount());

			//收益账户转账
			log.info("6.3进行收益子账户手续费转账");
			AccountTransferRequest userFeeTransferRequest = null;
			BigDecimal free = new BigDecimal(refundRequest.getFee());
			if (free.compareTo(BigDecimal.ZERO) == 1) {
				userFeeTransferRequest = this.convertToTransferRequest(refundRequest);
				userFeeTransferRequest.setUserid(CommonConstant.RS_FANGCANG_USER_ID);
				userFeeTransferRequest.setProductid("P000229");
				userFeeTransferRequest.setAmount(refundRequest.getFee());
				accountTransferResponse = rsAccTradeManager.accountBalanceTransfer(userFeeTransferRequest);
				if (!CommonConstant.OPERATE_SUCCESS.equals(accountTransferResponse.getOperateStatus())) {
					log.error("手续费退款转账失败,Requestno:"+userFeeTransferRequest.getRequestno()+",Transorderid:"+refundRequest.getTransorderid());
					this.fixRefundProcess(tradeTransferRequest, null, titanTransferReq, refundRequest);
					response.putErrorResult(TitanMsgCodeEnum.BALANCE_ACCOUNT_NOT_ENOUGH);
					isFreeze = true;
					return response;
				}
			}

			//修改退款转账订单
			log.info("6.4设置退款转账单状态");
			titanTransferReq.setStatus(TransferReqEnum.TRANSFER_SUCCESS.getStatus());
			int uprow = titanTransferReqDao.update(titanTransferReq);
			if (uprow != 1) {
				log.error("更新退款转账单失败，Requestno:"+titanTransferReq.getRequestno());
				this.fixRefundProcess(tradeTransferRequest, userFeeTransferRequest, titanTransferReq, refundRequest);
				//返回商家账户，并冻结金额（如果有解冻操作）
				response.putErrorResult(TitanMsgCodeEnum.TRANSFER_SUCCESS_UPDATE_LOACL_FAIL);
				isFreeze = true;
				return response;
			}
			//下退款单
			log.info("6.5下退款单进行退款");
			RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
			refundOrderRequest.setAmount(refundRequest.getRefundAmount());
			refundOrderRequest.setOrderId(refundRequest.getOrderNo());
			refundOrderRequest.setOrderTime(refundRequest.getOrderTime());
			refundOrderRequest.setTransferAmount(refundRequest.getTransferAmount());
			refundOrderRequest.setFee(refundRequest.getFee());
			refundOrderRequest.setPayOrderNo(refundRequest.getPayOrderNo());
			refundOrderRequest.setUserOrderId(refundRequest.getUserOrderId());
			refundOrderRequest.setNotifyUrl(refundRequest.getNotifyUrl());
			refundOrderRequest.setBusinessInfo(refundRequest.getBusinessInfo());
			if (StringUtil.isValidString(refundRequest.getTfsUerId())) {
				TitanUser user = titanUserDao.selectTitanUser(Integer.parseInt(refundRequest.getTfsUerId()));
				if (user != null) {
					refundOrderRequest.setCreator(user.getUsername());
				}
			}

			if (RefundTypeEnum.REFUND_ACCOUNT.type.equals(refundRequest.getToBankCardOrAccount())) {
				log.info("6.5.1如果只有转账退款，则保存转账单设置成功");
				if (this.saveRefundOrder(refundOrderRequest)) {
					log.info("资金退款到账户余额完成,订单号orderid："+refundOrderRequest.getOrderId());
					response.putSuccess();
				} else {
					log.error("系统退款成功，保存退款单失败，当前参数refundRequest："+Tools.gsonToString(refundRequest)+",订单号orderid："+refundOrderRequest.getOrderId());
				}
				return response;
			}
			NotifyRefundRequest notifyRefundRequest = this.convertToNotifyRefundRequest(refundRequest);
			NotifyRefundResponse notifyRefundResponse = notifyRefundOrder(refundOrderRequest,notifyRefundRequest);
			RefundStatusEnum refundStatus = null;
			if(notifyRefundResponse.isResult()){
				RefundStatusEnum status = RefundStatusEnum.getRefundStatusEnumByStatus(Integer.parseInt(notifyRefundResponse.getRefundStatus()));
				if(RefundStatusEnum.REFUND_AFAINST==status){
					refundStatus = RefundStatusEnum.REFUND_IN_PROCESS;
				}else{
					refundStatus = status;
				}
			}else{//网关退款请求无响应或者失败都当成退款中
				refundStatus = RefundStatusEnum.REFUND_IN_PROCESS;
				log.info("网关退款调用失败,订单orderid:"+refundRequest.getOrderNo()+",响应结果:"+Tools.gsonToString(notifyRefundResponse));
			}
				
			log.info("6.7通知业务系统退款结果,订单号orderid："+refundOrderRequest.getOrderId());
			this.threadNotify(refundOrderRequest.getOrderId(), refundStatus);
			response.putSuccess("已发起退款");
			return response;
		} catch (Exception e) {
			log.error("退款失败", e);
			response.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
		} finally {
			if (isFreeze) {
				this.orderRefundFreeze(refundRequest);
			}
		}
		return response;
	}
	
	private  NotifyRefundResponse notifyRefundOrder(RefundOrderRequest refundOrderRequest,NotifyRefundRequest notifyRefundRequest){
		log.info("6.5.2去融数和本地同时下退款单");
		NotifyRefundResponse notifyRefundResponse = new NotifyRefundResponse();
		RefundOrderResponse refundOrderResponse = this.addRefundOrder(refundOrderRequest);
		if (!refundOrderResponse.isResult()) {
			log.error("下退款单失败,下单参数refundOrderRequest："+Tools.gsonToString(refundOrderRequest)+",响应结果refundOrderResponse:" + Tools.gsonToString(refundOrderResponse));
			notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
			return notifyRefundResponse;
		}
		
		log.info("6.6回调通知网关退款");
		notifyRefundRequest.setRefundOrderno(refundOrderResponse.getRefundOrderNo());
		notifyRefundResponse = this.notifyGateawayRefund(notifyRefundRequest);
		log.info("调用网关退款，orderid:"+refundOrderRequest.getOrderId()+",退款结果notifyRefundResponse："+Tools.gsonToString(notifyRefundResponse));

		TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
		titanOrderPayDTO.setOrderNo(refundOrderRequest.getOrderId());
		TitanOrderPayDTO orderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
		boolean needDelay = false;
		if (null != orderPayDTO && "41".equals(orderPayDTO.getPayType())){
			needDelay = true;
		}

		if (!needDelay) {
			notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
			NotifyRefundResponse queryNotifyRefundResponse = this.notifyGateawayRefund(notifyRefundRequest);
			log.info("调用网关退款查询，orderid:" + refundOrderRequest.getOrderId() + ",查询结果notifyRefundResponse：" + Tools.gsonToString(queryNotifyRefundResponse));
			return queryNotifyRefundResponse;
		} else {
			log.info("需要异步线程查询并再次通知，直接返回退款申请的结果");
			Thread thread = new Thread(new DelayNotifyThread(notifyRefundRequest,refundOrderRequest));
			thread.start();
			return notifyRefundResponse;
		}
	}

	private class DelayNotifyThread implements Runnable{

		private NotifyRefundRequest notifyRefundRequest ;
		private RefundOrderRequest refundOrderRequest;

		public DelayNotifyThread (NotifyRefundRequest notifyRefundRequest,RefundOrderRequest refundOrderRequest){
			this.notifyRefundRequest = notifyRefundRequest;
			this.refundOrderRequest = refundOrderRequest;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000*60l);//休眠一分钟后再执行；

				TransOrderDTO transOrderDTO = new TransOrderDTO();
				transOrderDTO.setOrderid(refundOrderRequest.getOrderId());

				RefundDTO refundDTO = new RefundDTO();
				refundDTO.setOrderNo(refundOrderRequest.getOrderId());

				notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
				NotifyRefundResponse queryNotifyRefundResponse = notifyGateawayRefund(notifyRefundRequest);
				log.info("调用网关退款查询，orderid:" + refundOrderRequest.getOrderId() + ",查询结果notifyRefundResponse：" + Tools.gsonToString(queryNotifyRefundResponse));
				RefundStatusEnum refundStatus;
				if(queryNotifyRefundResponse.isResult()){
					RefundStatusEnum status = RefundStatusEnum.getRefundStatusEnumByStatus(Integer.parseInt(queryNotifyRefundResponse.getRefundStatus()));
					if(RefundStatusEnum.REFUND_AFAINST==status){
						refundStatus = RefundStatusEnum.REFUND_IN_PROCESS;
					}else{
						refundStatus = status;
					}

					//设置交易单状态,TODO 审核失败先算处理中
					if (RefundStatusEnum.REFUND_FAILURE == status){
						transOrderDTO.setStatusid(OrderStatusEnum.REFUND_FAIL.getStatus());
					} else if (RefundStatusEnum.REFUND_SUCCESS == status){
						transOrderDTO.setStatusid(OrderStatusEnum.REFUND_SUCCESS.getStatus());
					} else {
						transOrderDTO.setStatusid(OrderStatusEnum.REFUND_IN_PROCESS.getStatus());
					}
				}else{//网关退款请求无响应或者失败都当成退款中
					refundStatus = RefundStatusEnum.REFUND_IN_PROCESS;
					//设置交易单状态
					transOrderDTO.setStatusid(OrderStatusEnum.REFUND_IN_PROCESS.getStatus());
					log.info("网关退款调用失败,订单orderid:"+refundOrderRequest.getOrderId()+",响应结果:"+Tools.gsonToString(queryNotifyRefundResponse));
				}
				//更新交易单
				log.info("再次退款通知时更新交易单状态交易单号:"+ transOrderDTO.getOrderid()+ "，状态为："  + transOrderDTO.getStatusid());
				titanOrderService.updateTransOrder(transOrderDTO);
				//更新退款单
				refundDTO.setStatus(refundStatus.status);
				log.info("再次退款通知时更新退款单状态退款单号:"+ refundDTO.getOrderNo()+ "，状态为："  + refundDTO.getStatus());
				titanRefundDao.updateRefundDTO(refundDTO);
				log.info("6.8.快捷支付再次通知业务系统退款结果,订单号orderid："+refundOrderRequest.getOrderId());
				threadNotify(refundOrderRequest.getOrderId(), refundStatus);
				log.info("快捷支付再次通知成功");
			} catch (Exception e){
				log.error("异步退款通知异常", e);
			}
		}
	}
	
	//TODO 修复过程也失败了只能线下处理
	private void fixRefundProcess(AccountTransferRequest tradeTransferRequest,AccountTransferRequest userFeeTransferRequest,
						   TitanTransferReq titanTransferReq,TitanJrRefundRequest refundRequest){
		if (null != tradeTransferRequest) {
			reverseTransfer(tradeTransferRequest, refundRequest);
		}
		if (null != userFeeTransferRequest){
			reverseTransfer(userFeeTransferRequest, refundRequest);
		}
		if (null!=titanTransferReq) {
			this.deleteRefundTransOrder(titanTransferReq);
		}
	}
	
	private boolean saveRefundOrder(RefundOrderRequest refundOrderRequest){
		TitanRefund titanRefund = new TitanRefund();
		titanRefund.setOrderid(refundOrderRequest.getOrderId());
		titanRefund.setRefundOrderno(null);
		titanRefund.setRefundAmount(refundOrderRequest.getAmount());
		titanRefund.setCreateTime(new Date());
		titanRefund.setOrderTime(DateUtil.sdf5.format(new Date()));
		titanRefund.setCreator(refundOrderRequest.getCreator());
		titanRefund.setStatus(RefundStatusEnum.REFUND_SUCCESS.status);
		titanRefund.setNotifyUrl(refundOrderRequest.getNotifyUrl());
		titanRefund.setTransferAmount(refundOrderRequest.getAmount());
		titanRefund.setFee("0");
		try{
			titanRefundDao.insert(titanRefund);
			this.threadNotify(refundOrderRequest.getOrderId(), RefundStatusEnum.REFUND_SUCCESS);
		}catch(Exception e){
			log.error("保存退款单下单失败"+e.getMessage()+":data:"+JSONSerializer.toJSON(titanRefund));
			titanFinancialUtilService.saveOrderException(refundOrderRequest.getOrderId(),OrderKindEnum.OrderId, OrderExceptionEnum.Refund_Save_Order_Fail, JSONSerializer.toJSON(titanRefund).toString());
			return false;
		}
		return true;
	}

	//更新交易单状态用交易单编码或单号
	private boolean updateTransOrderByKey(OrderStatusEnum status,Integer transId,String orderNo){
	    TransOrderDTO order = new TransOrderDTO();
	    order.setStatusid(status.getStatus());
		boolean flag = false;
		if (transId != null || StringUtil.isValidString(orderNo)) {
			order.setTransid(transId);
			order.setOrderid(orderNo);
			flag = titanOrderService.updateTransOrder(order);
		}
	    if(!flag){
	    	log.error("更新交易单状态用交易单编码或单号失败");
	    	titanFinancialUtilService.saveOrderException(orderNo,OrderKindEnum.OrderId, OrderExceptionEnum.Refund_Update_Order_OrderNo_Fail, JSONSerializer.toJSON(order).toString());
	    }
		return flag;
	}
	
	private boolean deleteRefundTransOrder(TitanTransferReq req){
		try{
			return titanTransferReqDao.delete(req)==1?true:false;
		}catch(Exception e){
			log.error("删除失败",e);
			titanFinancialUtilService.saveOrderException(req.getPayorderno(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Refund_Delete_Transfer_Order_Fail, JSONSerializer.toJSON(req).toString());
		}
		return false;
	}
	
	private void reverseTransfer(AccountTransferRequest accountTransfer,TitanJrRefundRequest refundRequest){
		if(null == accountTransfer || Long.parseLong(accountTransfer.getAmount())==0){
			return ;
		}
		accountTransfer = convertToAccountTransferRequest(accountTransfer);
		AccountTransferResponse accountTrans= rsAccTradeManager.accountBalanceTransfer(accountTransfer);
		if(!CommonConstant.OPERATE_SUCCESS.equals(accountTrans.getOperateStatus())){
			log.error("退款单修复性转账失败");
			titanFinancialUtilService.saveOrderException(refundRequest.getPayOrderNo(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Refund_Repair_Tranfer_Fail, JSONSerializer.toJSON(accountTransfer).toString());
		}
		
	}
	
	private AccountTransferRequest convertToAccountTransferRequest(AccountTransferRequest accountTransfer){
		String middleStr = accountTransfer.getUserid();
		accountTransfer.setUserid(accountTransfer.getUserrelateid());
		accountTransfer.setUserrelateid(middleStr);
		middleStr = accountTransfer.getProductid();
		accountTransfer.setProductid(accountTransfer.getInterproductid());
		accountTransfer.setInterproductid(middleStr);
		middleStr = accountTransfer.getMerchantcode();
		accountTransfer.setMerchantcode(accountTransfer.getIntermerchantcode());
		accountTransfer.setIntermerchantcode(middleStr);
		accountTransfer.setRequestno(OrderGenerateService.genResquestNo());
		return accountTransfer;
	} 
	
	private NotifyRefundRequest convertToNotifyRefundRequest(TitanJrRefundRequest refundRequest){
		NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
		notifyRefundRequest.setBusiCode(refundRequest.getBusiCode());
		notifyRefundRequest.setMerchantNo(refundRequest.getMerchantCode());
		notifyRefundRequest.setOrderNo(refundRequest.getOrderNo());
		notifyRefundRequest.setRefundAmount(refundRequest.getRefundAmount());
		notifyRefundRequest.setOrderTime(refundRequest.getOrderTime());
		notifyRefundRequest.setSignType(refundRequest.getSignType());
		notifyRefundRequest.setVersion(refundRequest.getVersion());
		return notifyRefundRequest;
	}
	
	private TitanTransferReq convertToTitanTransferReq(AccountTransferRequest accountTransfer) throws java.text.ParseException{
		TitanTransferReq titanTransferReq = new TitanTransferReq();
		titanTransferReq = new TitanTransferReq();
		if (StringUtil.isValidString(accountTransfer.getAmount())) {
			titanTransferReq.setAmount(Double
					.parseDouble(accountTransfer.getAmount()));
		}
			titanTransferReq.setTransfertype(Integer
					.parseInt(TransfertypeEnum.BRANCH_TRANSFER.getKey())); // 1:子账户转账
		titanTransferReq.setCreatetime(new Date());
		titanTransferReq
				.setMerchantcode(accountTransfer.getMerchantcode());
		titanTransferReq.setProductid(accountTransfer.getProductid());
		titanTransferReq
				.setIntermerchantcode(accountTransfer.getIntermerchantcode());
		titanTransferReq.setInterproductid(accountTransfer.getInterproductid());
		titanTransferReq.setRequestno(accountTransfer.getRequestno());
		titanTransferReq.setRequesttime(DateUtil.sdf4.parse(accountTransfer.getRequesttime()));
		titanTransferReq.setStatus(Integer.parseInt("0"));
		titanTransferReq.setUserfee(Double.valueOf(0));
		titanTransferReq.setUserid(accountTransfer.getUserid());
		titanTransferReq.setUserrelateid(accountTransfer.getUserrelateid());
		titanTransferReq.setStatus(TransferReqEnum.TRANSFER_IN_PROCESS.getStatus());
		if(StringUtil.isValidString(accountTransfer.getConditioncode())){
			titanTransferReq.setConditioncode(Integer.parseInt(accountTransfer.getConditioncode()));
		}
		return titanTransferReq;
	}
	
	private AccountTransferRequest convertToTransferRequest(TitanJrRefundRequest refundRequest){
		AccountTransferRequest accountTransfer = new AccountTransferRequest();
		accountTransfer.setAmount(refundRequest.getTradeAmount());
		accountTransfer.setConditioncode(ConditioncodeEnum.ADD_OEDER.getKey());
		accountTransfer.setIntermerchantcode(refundRequest.getInterMerchantCode());
		accountTransfer.setInterproductid(refundRequest.getInterProductId());
		accountTransfer.setUserrelateid(refundRequest.getUserRelateId());
		
		accountTransfer.setUserid(refundRequest.getUserId());
		accountTransfer.setProductid(refundRequest.getProductId());
		accountTransfer.setMerchantcode(refundRequest.getMerchantCode());
		accountTransfer.setUserfee("0");
		accountTransfer.setRequestno(OrderGenerateService.genResquestNo());
		accountTransfer.setRequesttime(DateUtil.sdf4.format(new Date()));
		accountTransfer.setTransfertype(TransfertypeEnum.BRANCH_TRANSFER.getKey());
		
		return accountTransfer;
	}
	
	
	private RefundOrderResponse addRefundOrder(	RefundOrderRequest refundOrderRequest) {
		RefundOrderResponse refundOrderResponse = new RefundOrderResponse();
		
		if(null ==refundOrderRequest || 
				!StringUtil.isValidString(refundOrderRequest.getAmount())||
				!StringUtil.isValidString(refundOrderRequest.getOrderId())){
			refundOrderResponse.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED); 
			return refundOrderResponse;
		}
		RSRefundRequest  refundRequest = this.convertToRefundRequest(refundOrderRequest);
		RsRefundResponse refundResponse = rsAccTradeManager.addOrderRefund(refundRequest);
		if(!CommonConstant.OPERATE_SUCCESS.equals(refundResponse.getOperateStatus())){
			log.error("下退款单失败，参数refundOrderRequest:"+Tools.gsonToString(refundOrderRequest)+",响应结果refundResponse:"+Tools.gsonToString(refundResponse));
			refundOrderResponse.putErrorResult(TitanMsgCodeEnum.RS_ADD_REFUND_ORDER_FAIL);
			return refundOrderResponse;
		}
		TitanRefund titanRefund = new TitanRefund();
		titanRefund.setOrderid(refundOrderRequest.getOrderId());
		titanRefund.setRefundOrderno(refundResponse.getRefundOrderNo());
		titanRefund.setRefundAmount(refundRequest.getAmount());
		titanRefund.setCreateTime(new Date());
		titanRefund.setOrderTime(refundOrderRequest.getOrderTime());
		titanRefund.setCreator(refundOrderRequest.getCreator());
		titanRefund.setTransferAmount(refundOrderRequest.getTransferAmount());
		titanRefund.setFee(refundOrderRequest.getFee());
		titanRefund.setBusinessInfo(refundOrderRequest.getBusinessInfo());
		titanRefund.setStatus(RefundStatusEnum.REFUND_IN_PROCESS.status);
		titanRefund.setNotifyUrl(refundOrderRequest.getNotifyUrl());
		titanRefund.setUserorderid(refundOrderRequest.getUserOrderId());
		titanRefund.setPayOrderNo(refundOrderRequest.getPayOrderNo());
		try{
			titanRefundDao.insert(titanRefund);
		}catch(Exception e){
			log.error("本地保存数据失败，参数refundOrderRequest:"+Tools.gsonToString(refundOrderRequest)+",响应结果refundResponse:"+Tools.gsonToString(refundResponse)+",data"+JSONSerializer.toJSON(titanRefund),e);
			titanFinancialUtilService.saveOrderException(refundOrderRequest.getOrderId(),OrderKindEnum.OrderId, OrderExceptionEnum.Refund_Save_Order_Fail, JSONSerializer.toJSON(titanRefund).toString());
			refundOrderResponse.putErrorResult("本地保存数据失败");
			return refundOrderResponse;
		}
		
		refundOrderResponse.putSuccess();
		refundOrderResponse.setRefundOrderNo(refundResponse.getRefundOrderNo());
		return refundOrderResponse;
	}

	private RSRefundRequest convertToRefundRequest(RefundOrderRequest refundOrderRequest){
		RSRefundRequest   refundRequest = new RSRefundRequest();
		refundRequest.setAmount(refundOrderRequest.getAmount());
		refundRequest.setOrderid(refundOrderRequest.getOrderId());
		refundRequest.setUserorderid(refundOrderRequest.getUserOrderId());
		refundRequest.setOrderitemid(refundOrderRequest.getOrderItemId());
		return refundRequest;
	}
	
	private NotifyRefundResponse notifyGateawayRefund(
			NotifyRefundRequest notifyRefundRequest) {
		NotifyRefundResponse notifyRefundResponse = new NotifyRefundResponse();
		if(null == notifyRefundRequest){
			notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
			return notifyRefundResponse;
		}
		List<NameValuePair> params = this.getGateawayParam(notifyRefundRequest);
		String response ="";
		Date beginDate = new Date();
		BusiCodeEnum busiCodeEnum = BusiCodeEnum.getEnumByKey(notifyRefundRequest.getBusiCode());
		HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
		try {
 
			HttpResponse resp = HttpClient.httpRequest(params,httpPost);
			if (null != resp) {
				HttpEntity entity = resp.getEntity();
				response = EntityUtils.toString(entity);
				log.info("调用融数网关gateWayURL退款或查询退款状态,操作："+busiCodeEnum.toString()+",orderId："+notifyRefundRequest.getOrderNo()+",请求参数:"+Tools.gsonToString(params)+",退款返回信息response："+response);
				
				notifyRefundResponse = RSConvertFiled2ObjectUtil.convertField2Object(NotifyRefundResponse.class, response);
				notifyRefundResponse.putSuccess("");
				if(StringUtil.isValidString(notifyRefundResponse.getErrCode()) 
			    		|| StringUtil.isValidString(notifyRefundResponse.getErrMsg())
			    		|| !StringUtil.isValidString(notifyRefundResponse.getRefundOrderno())){//通知退款失败
					log.error("调用融数网关gateWayURL退款异常,错误信息:"+notifyRefundResponse.getErrMsg()+",参数params:"+Tools.gsonToString(params));
					notifyRefundResponse.setRefundStatus(RefundStatusEnum.REFUND_IN_PROCESS.status.toString());
			    }
				
				if(busiCodeEnum!=null&&busiCodeEnum.equals(BusiCodeEnum.MerchantRefund)){
					invokeLogRecordManager.logELK(beginDate, new Date(), "titanjr:notifygateawayrefund."+busiCodeEnum.toString().toLowerCase(), Tools.gsonToString(params)+",gatewayurl:"+RSInvokeConstant.gateWayURL+","+Tools.gsonToString(busiCodeEnum), Tools.gsonToString(notifyRefundResponse), notifyRefundResponse.isResult()+"");
				}
 
				return notifyRefundResponse;
			}else{
				//网络无响应，则
				notifyRefundResponse.putSuccess();
				notifyRefundResponse.setRefundStatus(RefundStatusEnum.REFUND_IN_PROCESS.status.toString());
				log.error("网关退款失败 resp 为空,操作："+busiCodeEnum.toString()+",参数params:"+Tools.gsonToString(params)+",退款地址gateWayURL:"+RSInvokeConstant.gateWayURL);
				return notifyRefundResponse;
			}
		} catch (ParseException e) {
			notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
			log.error("网关退款失败,parse异常："+e.getMessage()+",参数params:"+Tools.gsonToString(params),e);
		} catch (Exception e) {
			notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
			log.error("网关退款失败,异常："+e.getMessage()+",参数params:"+Tools.gsonToString(params),e);
		}
		if(busiCodeEnum!=null&&busiCodeEnum.equals(BusiCodeEnum.MerchantRefund)){
			invokeLogRecordManager.logELK(beginDate, new Date(), "titanjr:notifygateawayrefund."+busiCodeEnum.toString().toLowerCase(), Tools.gsonToString(params)+",gatewayurl:"+RSInvokeConstant.gateWayURL+","+Tools.gsonToString(busiCodeEnum), Tools.gsonToString(notifyRefundResponse), notifyRefundResponse.isResult()+"");
		}
		notifyRefundResponse.putSuccess();
		notifyRefundResponse.setRefundStatus(RefundStatusEnum.REFUND_IN_PROCESS.status.toString());
		return notifyRefundResponse;
	}
	
	private List<NameValuePair> getGateawayParam(NotifyRefundRequest notifyRefundRequest){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("merchantNo", notifyRefundRequest.getMerchantNo()));
		params.add(new BasicNameValuePair("busiCode", notifyRefundRequest.getBusiCode()));
		params.add(new BasicNameValuePair("orderNo", notifyRefundRequest.getOrderNo()));
		params.add(new BasicNameValuePair("refundAmount", notifyRefundRequest.getRefundAmount()));
		params.add(new BasicNameValuePair("orderTime", notifyRefundRequest.getOrderTime()));//
		params.add(new BasicNameValuePair("refundOrderno", notifyRefundRequest.getRefundOrderno()));
		params.add(new BasicNameValuePair("version", notifyRefundRequest.getVersion()));
		params.add(new BasicNameValuePair("signType", notifyRefundRequest.getSignType()));
		String signMsg = this.getMD5Sign(this.getStrSign(notifyRefundRequest) ,"UTF-8");
		params.add(new BasicNameValuePair("signMsg", signMsg));
		return params;
	}

	//获取前面字符串
	private String getStrSign(NotifyRefundRequest notifyRefundRequest){
		StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append("signType="+notifyRefundRequest.getSignType());
    	stringBuffer.append("&version="+notifyRefundRequest.getVersion());
    	stringBuffer.append("&merchantNo="+notifyRefundRequest.getMerchantNo());
    	stringBuffer.append("&refundOrderno="+notifyRefundRequest.getRefundOrderno());
    	stringBuffer.append("&orderNo="+notifyRefundRequest.getOrderNo());
    	stringBuffer.append("&orderTime="+notifyRefundRequest.getOrderTime());
    	stringBuffer.append("&refundAmount="+notifyRefundRequest.getRefundAmount());
    	stringBuffer.append("&key="+RSInvokeConstant.rsCheckKey);
		return stringBuffer.toString();
	}
	
	
	private String getMD5Sign(String str,String charset){
		return MD5.MD5Encode(str, charset);
	}
	

	//冻结操作
	private void orderRefundFreeze(TitanJrRefundRequest refundRequest){
		//查询该单是否有解冻操作
		if(refundRequest.isFreeze()){
			log.info("没有解冻操作");
			return;
		}
		
		//判断该解冻是否成功
		List<FundFreezeDTO> fundFreezeDTOList = titanFundFreezereqDao.queryFundFreezeDTOByOrderNo(refundRequest.getOrderNo());
		if(fundFreezeDTOList ==null || fundFreezeDTOList.size()<1){
			log.info("该冻结暂时没有解冻记录,无需更改");
			return ;
		}
		
		//查询是否已成功下了退款单，如果已经成功下了退款单就不能进行恢复操作了。
		RefundDTO refund = new RefundDTO();
		refund.setUserOrderId(refundRequest.getUserOrderId());
		List<RefundDTO> refundList = titanRefundDao.queryRefundDTO(refund);
		if(refundList!=null && refundList.get(0)!=null ){
			log.error("退款单已在落单,不能进行恢复操作了");
			return ;
		}
		
		//始终拿最新的解冻记录
		FundFreezeDTO fundFreezeDTO = fundFreezeDTOList.get(0);
		//重新冻结并修改解冻的相关信息
		RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
		rechargeResultConfirmRequest.setOrderAmount(refundRequest.getTradeAmount());
		rechargeResultConfirmRequest.setPayAmount(refundRequest.getTradeAmount());
		rechargeResultConfirmRequest.setUserid(refundRequest.getUserId());
		rechargeResultConfirmRequest.setOrderNo(refundRequest.getOrderNo());
		rechargeResultConfirmRequest.setFreezereqId(fundFreezeDTO.getFreezereqId());
		try {
			FreezeAccountBalanceResponse freezeAccountBalanceResponse = titanFinancialAccountService.freezeAccountBalance(rechargeResultConfirmRequest);
		    if(!freezeAccountBalanceResponse.isFreezeSuccess()){
		    	log.error("退款之后冻结订单失败");
		    	titanFinancialUtilService.saveOrderException(refundRequest.getOrderNo(),OrderKindEnum.OrderId, OrderExceptionEnum.Refund_Freeze_Order_Fail, JSONSerializer.toJSON(rechargeResultConfirmRequest).toString());
		    }
			boolean flag = this.updateTransOrderByKey(OrderStatusEnum.FREEZE_SUCCESS,null,refundRequest.getOrderNo());
			if(!flag){
				log.error("重新冻结之后修改订单状态失败");
				titanFinancialUtilService.saveOrderException(refundRequest.getOrderNo(),OrderKindEnum.OrderId, OrderExceptionEnum.Refund_Freeze_Update_Order_Fail, OrderStatusEnum.FREEZE_SUCCESS.getStatus());
			}
		} catch (Exception e) {
			log.error("退款之后冻结订单失败",e);
		}
		
	}
	
	@Override
	public void refundConfirm(RefundConfirmRequest refundConfirm) {
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		if(null !=refundConfirm){
			transOrderRequest.setUserid(refundConfirm.getUserId());
			transOrderRequest.setOrderid(refundConfirm.getOrderNo());
		}
		transOrderRequest.setStatusId(OrderStatusEnum.REFUND_IN_PROCESS.getStatus());
		
		List<RefundDTO> refundDTOList =  titanTransOrderDao.selectTransRefundOrder(transOrderRequest);
		if(null == refundDTOList || refundDTOList.size()<1){
			log.info("refundConfirm ，无退款中的订单记录");
			return ;
		}
		
		for(RefundDTO refundDTO :refundDTOList){
			log.info("refundConfirm()同步退款状态，订单号orderid："+refundDTO.getOrderNo());
			NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
			notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
			notifyRefundRequest.setMerchantNo(refundDTO.getMerchantNo());
			notifyRefundRequest.setOrderNo(refundDTO.getOrderNo());
		
			notifyRefundRequest.setRefundAmount(refundDTO.getRefundAmount());
			notifyRefundRequest.setOrderTime(refundDTO.getOrderTime());
			notifyRefundRequest.setRefundOrderno(refundDTO.getRefundOrderno());

			TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
			titanOrderPayDTO.setOrderNo(refundDTO.getOrderNo());
			TitanOrderPayDTO orderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
			//按照充值单中版本号设置
			if (null != orderPayDTO){
				notifyRefundRequest.setVersion(orderPayDTO.getVersion());
			} else {
				log.error("对应退款单不存在充值单，请核实退款单信息:" + JSONSerializer.toJSON(refundDTO));
				continue;
			}

			notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
			
			NotifyRefundResponse notifyRefundResponse = this.notifyGateawayRefund(notifyRefundRequest);
			RefundStatusEnum refundStatusEnum = null;
			if(notifyRefundResponse.isResult()){
				refundStatusEnum = RefundStatusEnum.getRefundStatusEnumByStatus(Integer.parseInt(notifyRefundResponse.getRefundStatus()));
				log.info("refundConfirm()-查询退款单号orderId："+refundDTO.getOrderNo()+",融数退款状态是:"+refundStatusEnum.toString());
				double diffHour = DateUtil.getDiffHour(refundDTO.getCreatetime(), new Date());
				boolean isLongTime = false;//规定时长内不同步状态和通知
				boolean isSynState = false;//是否需要同步状态
				if(diffHour>12){
					isLongTime = true;
				}
				TransOrderDTO transOrderDTO = new TransOrderDTO();
				transOrderDTO.setOrderid(refundDTO.getOrderNo());
				
				if(RefundStatusEnum.REFUND_SUCCESS==refundStatusEnum){
					isSynState = true;
					isLongTime = true;
					transOrderDTO.setStatusid(OrderStatusEnum.REFUND_SUCCESS.getStatus());
					refundDTO.setStatus(RefundStatusEnum.REFUND_SUCCESS.status);
			    }else if(RefundStatusEnum.REFUND_FAILURE==refundStatusEnum
			    		|| RefundStatusEnum.REFUND_PROCESS_FAILURE==refundStatusEnum){
			    	isSynState = true;
			    	transOrderDTO.setStatusid(OrderStatusEnum.REFUND_FAIL.getStatus());
			    	refundDTO.setStatus(refundStatusEnum.status);
			    }else if(RefundStatusEnum.REFUND_AFAINST==refundStatusEnum){//不需要同步状态，业务实行递归
					//1融数下单
					RSRefundRequest   refundRequest = new RSRefundRequest();
					refundRequest.setAmount(refundDTO.getRefundAmount());
					refundRequest.setOrderid(refundDTO.getOrderNo());
					refundRequest.setUserorderid(refundDTO.getUserOrderId());
					RsRefundResponse refundResponse = rsAccTradeManager.addOrderRefund(refundRequest);
					log.info("冲销退款单，定时器重新下退款单，请求参数refundRequest："+Tools.gsonToString(refundRequest)+",响应结果refundResponse："+Tools.gsonToString(refundResponse));
					if(!CommonConstant.OPERATE_SUCCESS.equals(refundResponse.getOperateStatus())){
						log.error("定时器重新下退款单，请求参数refundRequest："+Tools.gsonToString(refundRequest)+",响应结果refundResponse："+Tools.gsonToString(refundResponse));
						continue;
					}else if(StringUtil.isValidString(refundResponse.getRefundOrderNo())){
						//2更新退款单
						refundDTO.setRefundOrderno(refundResponse.getRefundOrderNo());
						refundDTO.setStatus(RefundStatusEnum.REFUND_IN_PROCESS.status);
						titanRefundDao.updateRefundDTO(refundDTO);
						
						//3网关退款
						notifyRefundRequest.setBusiCode(BusiCodeEnum.MerchantRefund.getKey());
						notifyRefundRequest.setRefundOrderno(refundResponse.getRefundOrderNo());
						NotifyRefundResponse gatewayRefundResponse = this.notifyGateawayRefund(notifyRefundRequest);
						if(gatewayRefundResponse.isResult()){
							String gatewayStatus = gatewayRefundResponse.getRefundStatus();
							if(RefundStatusEnum.REFUND_SUCCESS.status==Integer.parseInt(gatewayStatus)){
								isSynState = true;
								isLongTime = true;
								transOrderDTO.setStatusid(OrderStatusEnum.REFUND_SUCCESS.getStatus());
								refundDTO.setStatus(RefundStatusEnum.REFUND_SUCCESS.status);
							}else{
								isSynState = false;
							}
						}else{
							isSynState = false;
							log.error("定时器重新下退款单网关退款查询调用失败,订单orderid:"+notifyRefundRequest.getOrderNo()+",响应结果:"+Tools.gsonToString(gatewayRefundResponse));
						}
					}
			    }
				if(isSynState&&isLongTime){
					titanOrderService.updateTransOrder(transOrderDTO);
					titanRefundDao.updateRefundDTO(refundDTO);
					//更新退款单状态
					this.threadNotify(refundDTO.getOrderNo(),RefundStatusEnum.getRefundStatusEnumByStatus(refundDTO.getStatus()));
					
				}
				//发失败邮件
				if(refundStatusEnum!=RefundStatusEnum.REFUND_SUCCESS&&refundStatusEnum!=RefundStatusEnum.REFUND_IN_PROCESS){
					titanFinancialUtilService.saveOrderException(refundDTO.getOrderNo(),OrderKindEnum.OrderId, OrderExceptionEnum.Refund_RS_Fail, "退款状态："+refundStatusEnum.toString());
				}
			}else{
				log.error("refundConfirm()-退款单状态查询失败,订单[orderid]:"+refundDTO.getOrderNo()+",返回值[notifyRefundResponse]:"+Tools.gsonToString(notifyRefundResponse));
			}
		}
	}

	@Override
	public RefundDTO queryRefundRequest(RefundDTO refundDTO) {
		return null;
	}
	
	private void notifyTTMall(NotifyBean bean){
		
		try{
			HttpPost httpPost = new HttpPost(bean.getNotifyUrl());
			String response = "";
			
			response = TitanjrHttpTools.confirmRefund(bean, httpPost);
			if(!StringUtil.isValidString(response)){
				log.error("回调退款单失败,参数[NotifyBean]："+JSONSerializer.toJSON(bean));
				throw new Exception("回调异常");
			}
			CallBackInfo callBackInfo = TitanjrHttpTools.analyzeResponse(response);
			if (callBackInfo == null ||!"000".equals(callBackInfo.getCode())) {
				log.error("回调异常，TTMALL返回失败,参数[NotifyBean]："+JSONSerializer.toJSON(bean)+",返回信息："+response);
				throw new Exception("回调异常，TTMALL返回失败");
			}
			log.info("回调TTMALL成功,参数[NotifyBean]："+JSONSerializer.toJSON(bean));
			
		}catch(Exception e){
			log.error("退款回调失败",e);
			titanFinancialUtilService.saveOrderException(bean.getPayOrderNo(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.ReFund_Notify_Fail, JSONSerializer.toJSON(bean).toString());
		}
	}
	
	private void threadNotify(final String orderNo,final RefundStatusEnum refundStatus){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				RefundDTO refund = new RefundDTO();
				refund.setOrderNo(orderNo);
				List<RefundDTO> reFundList= titanRefundDao.queryRefundDTO(refund);
				if(null==reFundList || reFundList.size()!=1 || reFundList.get(0)==null){
					log.error("查询退款单异常，单号RefundDTO.orderNo："+orderNo);
					return;
				}
				refund = reFundList.get(0);
				NotifyBean bean = new NotifyBean();
				bean.setPayOrderNo(refund.getPayOrderNo());
				bean.setNotifyUrl(refund.getNotifyUrl());
				bean.setUserOrderId(refund.getUserOrderId());
				bean.setCode(refundStatus.status.toString());
				bean.setBusinessInfo(refund.getBusinessInfo());
				log.info("threadNotify()通知第三方退款单状态,参数[NotifyBean]："+JSONSerializer.toJSON(bean)+",单号orderid:"+orderNo);
				notifyTTMall(bean);
			}
		});
		t.start();
	}
	
	
	@Override
	public NotifyRefundResponse notifyRefund(RefundOrderRequest refundOrderRequest, NotifyRefundRequest 
			notifyRefundRequest, TransOrderDTO transOrderDTO){
		
		NotifyRefundResponse notifyRefundResponse = notifyRefundOrder(refundOrderRequest,notifyRefundRequest);
		
		OrderStatusEnum orderStatusEnum = null;
		if(notifyRefundResponse.isResult()){
			RefundStatusEnum status = RefundStatusEnum.getRefundStatusEnumByStatus(Integer.parseInt(notifyRefundResponse.getRefundStatus()));
			//除了退款成功，其他状态都设置成退款中
			if(RefundStatusEnum.REFUND_SUCCESS == status){
				orderStatusEnum = OrderStatusEnum.REFUND_SUCCESS;
			}else{
				orderStatusEnum = OrderStatusEnum.REFUND_IN_PROCESS;
			}
		}else{
			//网关退款请求无响应或者失败都当成退款中
			orderStatusEnum = OrderStatusEnum.REFUND_IN_PROCESS;
			log.info("网关退款调用失败,订单orderid:"+transOrderDTO.getOrderid()+",响应结果:"+Tools.gsonToString(notifyRefundResponse));
		}
		
		TransOrderDTO transOrder = new TransOrderDTO();
		transOrder.setTransid(transOrderDTO.getTransid());
		transOrder.setStatusid(orderStatusEnum.getStatus());
		log.info("退款成功修改订单状态,Transid:" + transOrderDTO.getTransid() + ",orderId:"+transOrderDTO.getOrderid()+",Statusid:" + transOrder.getStatusid());
		boolean flag = titanOrderService.updateTransOrder(transOrder);
		if (!flag) {
			log.error("退款单状态更新失败,orderId:"+transOrderDTO.getOrderid());
			utilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.Refund_Success_Update_Order_Fail, JSONSerializer.toJSON(transOrder).toString());
		}
		
		return notifyRefundResponse;
		
	}
	
}
