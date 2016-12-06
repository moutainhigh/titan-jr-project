package com.fangcang.titanjr.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

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

import com.fangcang.titanjr.common.enums.ConditioncodeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.RefundTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TransferReqEnum;
import com.fangcang.titanjr.common.enums.TransfertypeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dao.TitanFundFreezereqDao;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.dao.TitanTransOrderDao;
import com.fangcang.titanjr.dao.TitanTransferReqDao;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.FreezeAccountBalanceRequest;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundOrderRequest;
import com.fangcang.titanjr.dto.request.RefundRequest;
import com.fangcang.titanjr.dto.request.TitanJrRefundRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.RefundOrderResponse;
import com.fangcang.titanjr.dto.response.RefundResponse;
import com.fangcang.titanjr.dto.response.TitanJrRefundResponse;
import com.fangcang.titanjr.dto.response.TitanPayMethodResponse;
import com.fangcang.titanjr.entity.TitanRefund;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.SignTypeEnum;
import com.fangcang.titanjr.enums.VersionEnum;
import com.fangcang.titanjr.rs.manager.RSAccTradeManager;
import com.fangcang.titanjr.rs.request.AccountTransferRequest;
import com.fangcang.titanjr.rs.request.RSRefundRequest;
import com.fangcang.titanjr.rs.response.AccountTransferResponse;
import com.fangcang.titanjr.rs.response.RsRefundResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialRefundService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.JsonUtil;
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
	
	private static Map<String,Object> mapLock = new  ConcurrentHashMap<String, Object>();
	
	private static final Log log = LogFactory.getLog(TitanFinancialRefundServiceImpl.class);

	
	@Override
	public TitanJrRefundResponse refund(
			TitanJrRefundRequest refundRequest) {
		TitanJrRefundResponse response = new TitanJrRefundResponse();
		//标识是否需要再次冻结商户资金
		boolean isFreeze = false;
		try{
			if(null == refundRequest || !StringUtil.isValidString(refundRequest.getPayOrderNo())){
				response.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
				isFreeze  = true;
				return response;
			}
			//加锁
			this.lockOutTradeNoList(refundRequest.getPayOrderNo());
			//查询是否已经退款
			TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
			titanTransferDTO.setUserid(refundRequest.getUserId());
			titanTransferDTO.setUserrelateid(refundRequest.getUserRelateId());
			titanTransferDTO.setPayOrderNo(refundRequest.getPayOrderNo());
			List<TitanTransferDTO> titanTransferDTOList =  titanOrderService.getTitanTransferDTOList(titanTransferDTO);
			if(null !=titanTransferDTOList ){//如果存在退款转账订单，则判断其是否成功
				titanTransferDTO = titanTransferDTOList.get(0);
				if(titanTransferDTO.getStatus().intValue()==TransferReqEnum.TRANSFER_SUCCESS.getStatus()){
					log.error("退款已完成，请勿重复退款");
					response.putErrorResult(TitanMsgCodeEnum.REFUND_SUCCESSED);
					isFreeze = true;
					return response;
				}
			}
			
			//转账
			AccountTransferRequest accountTransfer = this.convertToTransferRequest(refundRequest);
			TitanTransferReq titanTransferReq = this.convertToTitanTransferReq(accountTransfer);
			titanTransferReq.setTransorderid(refundRequest.getTransorderid());
			titanTransferReq.setPayorderno(refundRequest.getPayOrderNo());

			if(titanTransferDTOList == null){//保存转账订单
				int row = titanTransferReqDao.insert(titanTransferReq);
				if(row<1){
					log.error("退款保存转账单失败");
					response.putErrorResult(TitanMsgCodeEnum.LOCAL_ADD_TRANSFER_FAIL);
					isFreeze = true;
					return response;
				}
			}else{
				if(null == titanTransferDTOList.get(0) || null == titanTransferDTOList.get(0).getTransferreqid()){
					log.error("退款单异常");
					response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
					isFreeze = true;
					return response;
				}
				titanTransferReq.setTransferreqid(titanTransferDTOList.get(0).getTransferreqid());
			}
			
			//转账
			AccountTransferResponse accountTransferResponse = rsAccTradeManager
					.accountBalanceTransfer(accountTransfer);
			if(!CommonConstant.OPERATE_SUCCESS.equals(accountTransferResponse.getOperateStatus())){
				log.error("退款转账单失败");
				response.putErrorResult(TitanMsgCodeEnum.REFUND_TRANSFER_FAIL);
				isFreeze = true;
				return response;
			}
			
			//收益账户转账
			AccountTransferRequest balanceAccountTransfer =null;
			BigDecimal free = new BigDecimal(refundRequest.getFee());
			if(free.compareTo(BigDecimal.ZERO)==1){
				balanceAccountTransfer =  this.convertToTransferRequest(refundRequest);
				balanceAccountTransfer.setUserid("141223100000056");
				balanceAccountTransfer.setProductid("P000229");
				balanceAccountTransfer.setAmount(refundRequest.getFee());
				accountTransferResponse = rsAccTradeManager
						.accountBalanceTransfer(balanceAccountTransfer);
				if(!CommonConstant.OPERATE_SUCCESS.equals(accountTransferResponse.getOperateStatus())){
					log.error("退收益转账单失败");
					this.reverseTransfer(accountTransfer,refundRequest);
					//退款失败
					response.putErrorResult(TitanMsgCodeEnum.BALANCE_ACCOUNT_NOT_ENOUGH);
					isFreeze = true;
					return response;
				}
			}
			
			//修改退款转账订单
			titanTransferReq.setStatus(TransferReqEnum.TRANSFER_SUCCESS.getStatus());
			BigDecimal tradeAmount = new BigDecimal(refundRequest.getFee()).add(new BigDecimal(refundRequest.getTradeAmount()));
			titanTransferReq.setAmount(tradeAmount.doubleValue());
			int uprow = titanTransferReqDao.update(titanTransferReq);
			if(uprow!=1){
				log.error("更新退款转账单失败");
				reverseTransfer(accountTransfer, refundRequest);
				reverseTransfer(balanceAccountTransfer, refundRequest);
				//返回商家账户，并冻结金额（如果有解冻操作）
				response.putErrorResult(TitanMsgCodeEnum.TRANSFER_SUCCESS_UPDATE_LOACL_FAIL);
				isFreeze = true;
				return  response;
			}
			
			if(refundRequest.getToBankCardOrAccount().equals(RefundTypeEnum.REFUND_ACCOUNT.type)){
				log.info("退款到账户完成");
				response.putSuccess();
				return response;
			}
			//下退款单
			RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
			refundOrderRequest.setAmount(refundRequest.getRefundAmount());
			refundOrderRequest.setOrderId(refundRequest.getOrderNo());
			refundOrderRequest.setOrderTime(refundRequest.getOrderTime());
			RefundOrderResponse refundOrderResponse = this.addRefundOrder(refundOrderRequest);
			if(!refundOrderResponse.isResult()){
				log.error("下退款单失败");
				reverseTransfer(accountTransfer, refundRequest);
				reverseTransfer(balanceAccountTransfer, refundRequest);
				//记录退款失败单
				titanTransferReq.setStatus(TransferReqEnum.TRANSFER_FAIL.getStatus());
				titanTransferReqDao.update(titanTransferReq);
				//重新冻结资金
				response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
				isFreeze = true;
				return response;
			}
			
			NotifyRefundRequest notifyRefundRequest = this.convertToNotifyRefundRequest(refundRequest);
			notifyRefundRequest.setRefundOrderno(refundOrderResponse.getRefundOrderNo());
			NotifyRefundResponse notifyRefundResponse = this.notifyGateawayRefund(notifyRefundRequest);
			if(!notifyRefundResponse.isResult()){
				log.error("封装网关退款参数失败");
				response.putErrorResult(TitanMsgCodeEnum.PACKAGE_REFUND_PARAM_FAIL);
				return response;
			}
			response.putSuccess();
			this.unlockOutTradeNoList(refundRequest.getPayOrderNo());
			return response;
		}catch(Exception e){
			log.error("退款失败"+e.getMessage());
			response.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
			this.unlockOutTradeNoList(refundRequest.getPayOrderNo());
		}finally{
			if(isFreeze){
				this.orderRefundFreeze(refundRequest);
			}
			this.unlockOutTradeNoList(refundRequest.getPayOrderNo());
		}
		return response;
	}
	
	
	private void reverseTransfer(AccountTransferRequest accountTransfer,TitanJrRefundRequest refundRequest){
		if(null == accountTransfer || Long.parseLong(accountTransfer.getAmount())==0){
			return ;
		}
		accountTransfer = convertToAccountTransferRequest(accountTransfer);
		AccountTransferResponse accountTrans= rsAccTradeManager
				.accountBalanceTransfer(accountTransfer);
		if(!CommonConstant.OPERATE_SUCCESS.equals(accountTrans.getOperateStatus())){
			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(refundRequest.getPayOrderNo(), "退款单回滚失败", OrderExceptionEnum.REFUND_UPDATE_TRANSFER, refundRequest.getPayOrderNo());
    		titanOrderService.saveOrderException(orderExceptionDTO);
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
	
	private RefundOrderResponse addRefundOrder(
			RefundOrderRequest refundOrderRequest) {
		RefundOrderResponse refundOrderResponse = new RefundOrderResponse();
		
		if(null !=refundOrderRequest && 
				StringUtil.isValidString(refundOrderRequest.getAmount())&&
				StringUtil.isValidString(refundOrderRequest.getOrderId())){
			refundOrderResponse.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED); 
		}
		RSRefundRequest  refundRequest = this.convertToRefundRequest(refundOrderRequest);
		RsRefundResponse refundResponse = rsAccTradeManager.addOrderRefund(refundRequest);
		if(!CommonConstant.OPERATE_SUCCESS.equals(refundResponse.getOperateStatus())){
			log.error("下退款单失败:"+refundResponse.getReturnCode()+":"+refundResponse.getReturnMsg());
			refundOrderResponse.putErrorResult(TitanMsgCodeEnum.RS_ADD_REFUND_ORDER_FAIL);
			return refundOrderResponse;
		}
		
		TitanRefund titanRefund = new TitanRefund();
		titanRefund.setOrderid(refundOrderRequest.getOrderId());
		titanRefund.setRefundOrderno(refundResponse.getRefundOrderNo());
		titanRefund.setRefundAmount(refundRequest.getAmount());
		titanRefund.setCreateTime(new Date());
		titanRefund.setOrderTime(refundOrderRequest.getOrderTime());
		try{
			titanRefundDao.insert(titanRefund);
		}catch(Exception e){
			log.error("保存退款单下单失败"+e.getMessage()+":data"+JsonUtil.objectToJson(titanRefund));
			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(refundOrderRequest.getOrderId(), "退款落单保存失败", OrderExceptionEnum.REFUND_INSERT, refundOrderRequest.getOrderId());
    		titanOrderService.saveOrderException(orderExceptionDTO);
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
		if(null ==notifyRefundRequest){
			notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		List<NameValuePair> params = this.getGateawayParam(notifyRefundRequest);
		String response ="";
		HttpPost httpPost = new HttpPost();
		HttpResponse resp = HttpClient.httpRequest(params, RSInvokeConstant.gateWayURL,httpPost);
		if (null != resp) {
			HttpEntity entity = resp.getEntity();
			try {
				response = EntityUtils.toString(entity);
				notifyRefundResponse = RSConvertFiled2ObjectUtil.convertField2Object(NotifyRefundResponse.class, response);
				notifyRefundResponse.putSuccess();
				if(StringUtil.isValidString(notifyRefundResponse.getErrCode()) 
			    		|| StringUtil.isValidString(notifyRefundResponse.getErrMsg())){//通知退款失败
					log.error("退款通知异常:"+notifyRefundResponse.getErrMsg());
					notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
			    }
				return notifyRefundResponse;
			} catch (ParseException e) {
				notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
				log.error("退款通知异常"+e.getMessage(),e);
			} catch (Exception e) {
				notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
				log.error("退款通知异常"+e.getMessage(),e);
			}
		}
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
	
	private void lockOutTradeNoList(String out_trade_no) throws InterruptedException {
		synchronized (mapLock) {
			if(mapLock.containsKey(out_trade_no)) {
				synchronized (mapLock.get(out_trade_no)) 
				{
					mapLock.get(out_trade_no).wait();
				}
			}
			else{
				mapLock.put(out_trade_no, new Object());
			}
			
		}
	}
	
	private void unlockOutTradeNoList(String out_trade_no) {
		if(mapLock.containsKey(out_trade_no)){
			synchronized (mapLock.get(out_trade_no)) {
				mapLock.remove(out_trade_no).notifyAll();
			}
		}
	}

	//冻结操作
	private void orderRefundFreeze(TitanJrRefundRequest refundRequest){
		//查询该单是否有解冻操作
		if(!refundRequest.isFreeze()){
			log.info("没有解冻操作");
			return;
		}
		
		//判断该解冻是否成功
		List<FundFreezeDTO> fundFreezeDTOList = titanFundFreezereqDao.queryFundFreezeDTOByOrderNo(refundRequest.getOrderNo());
		if(fundFreezeDTOList ==null || fundFreezeDTOList.size()<1){
			log.info("该冻结暂时没有解冻记录,无需更改");
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
		    	OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(refundRequest.getOrderNo(), "重新冻结失败", OrderExceptionEnum.REFUND_FREEZE_AGAIN, refundRequest.getOrderNo());
	    		titanOrderService.saveOrderException(orderExceptionDTO);
		    }
		    
		    TransOrderDTO transOrderDTO = new TransOrderDTO();
			transOrderDTO.setStatusid(OrderStatusEnum.FREEZE_SUCCESS.getStatus());
			transOrderDTO.setOrderid(refundRequest.getOrderNo());
			boolean flag = titanOrderService.updateTransOrder(transOrderDTO);
			if(!flag){
				log.error("重新冻结之后修改订单状态失败");
				OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(refundRequest.getOrderNo(), "重新冻结失败", OrderExceptionEnum.REFUND_FREEZE_UPDATE, refundRequest.getOrderNo());
	    		titanOrderService.saveOrderException(orderExceptionDTO);
			}
		} catch (Exception e) {
			log.error("退款之后冻结订单失败"+e.getMessage());
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
		
		List<RefundDTO> refundDTOList =  titanTransOrderDao.selectTitanTransOrderAndRefund(transOrderRequest);
		if(null == refundDTOList || refundDTOList.size()<1){
			log.info("无退款记录");
			return ;
		}
		
		for(RefundDTO refundDTO :refundDTOList){
			NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
			notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
			notifyRefundRequest.setMerchantNo(refundDTO.getMerchantNo());
			notifyRefundRequest.setOrderNo(refundDTO.getOrderNo());
		
			notifyRefundRequest.setRefundAmount(refundDTO.getRefundAmount());
			notifyRefundRequest.setOrderTime(refundDTO.getOrderTime());
			notifyRefundRequest.setRefundOrderno(refundDTO.getRefundOrderno());
			notifyRefundRequest.setVersion(VersionEnum.Version_1.getKey());
			notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
			
			NotifyRefundResponse notifyRefundResponse = this.notifyGateawayRefund(notifyRefundRequest);
			if(notifyRefundResponse.isResult()){
				TransOrderDTO transOrderDTO = new TransOrderDTO();
				transOrderDTO.setOrderid(refundDTO.getOrderNo());
				String status = notifyRefundResponse.getRefundStatus();
				if(CommonConstant.REFUND_SUCCESS.equals(status)){
					transOrderDTO.setStatusid(OrderStatusEnum.REFUND_SUCCESS.getStatus());
				
			    }else if(CommonConstant.REFUND_IN_PROCESS.equals(status)){
			    	transOrderDTO.setStatusid(OrderStatusEnum.REFUND_FAIL.getStatus());
			    }	
				try{
					titanOrderService.updateTransOrder(transOrderDTO);
				}catch(Exception e){
					log.error("定时器更新订单状态失败"+e.getMessage());
					OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(refundDTO.getOrderNo(), "定时器更新订单状态失败", OrderExceptionEnum.REFUND_UPDATE_TRANSORDER, refundDTO.getOrderNo());
		    		titanOrderService.saveOrderException(orderExceptionDTO);
				}
			}
		}
	}
}
