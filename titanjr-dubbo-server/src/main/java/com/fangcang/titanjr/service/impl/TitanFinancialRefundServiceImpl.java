package com.fangcang.titanjr.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

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

import com.alibaba.fastjson.JSON;
import com.fangcang.enums.RefundStateEnum;
import com.fangcang.titanjr.common.bean.CallBackInfo;
import com.fangcang.titanjr.common.bean.NotifyBean;
import com.fangcang.titanjr.common.enums.ConditioncodeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
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
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.common.util.httpclient.TitanjrHttpTools;
import com.fangcang.titanjr.dao.TitanFundFreezereqDao;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.dao.TitanTransOrderDao;
import com.fangcang.titanjr.dao.TitanTransferReqDao;
import com.fangcang.titanjr.dao.TitanUserDao;
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
import com.fangcang.titanjr.entity.TitanUser;
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
import com.sun.jdi.Method;

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
			refundRequest.setTransferAmount(accountTransfer.getAmount());
			
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
					this.deleteRefundTransOrder(titanTransferReq);
					//退款失败
					response.putErrorResult(TitanMsgCodeEnum.BALANCE_ACCOUNT_NOT_ENOUGH);
					isFreeze = true;
					return response;
				}
			}
			
			//修改退款转账订单
			titanTransferReq.setStatus(TransferReqEnum.TRANSFER_SUCCESS.getStatus());
			int uprow = titanTransferReqDao.update(titanTransferReq);
			if(uprow!=1){
				log.error("更新退款转账单失败");
				reverseTransfer(accountTransfer, refundRequest);
				reverseTransfer(balanceAccountTransfer, refundRequest);
				this.deleteRefundTransOrder(titanTransferReq);
				//返回商家账户，并冻结金额（如果有解冻操作）
				response.putErrorResult(TitanMsgCodeEnum.TRANSFER_SUCCESS_UPDATE_LOACL_FAIL);
				isFreeze = true;
				return  response;
			}
			
			//下退款单
			RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
			refundOrderRequest.setAmount(refundRequest.getRefundAmount());
			refundOrderRequest.setOrderId(refundRequest.getOrderNo());
			refundOrderRequest.setOrderTime(refundRequest.getOrderTime());
			refundOrderRequest.setTransferAmount(refundRequest.getTransferAmount());
			refundOrderRequest.setFee(refundRequest.getFee());
			refundOrderRequest.setPayOrderNo(refundRequest.getPayOrderNo());
			refundOrderRequest.setUserOrderId(refundRequest.getUserOrderId());
			refundOrderRequest.setNotifyUrl(refundRequest.getNotifyUrl());
			refundOrderRequest.setIsRealTime(refundRequest.getIsRealTime());
			refundOrderRequest.setBusinessInfo(refundRequest.getBusinessInfo());
			if(StringUtil.isValidString(refundRequest.getTfsUerId())){
				TitanUser user = titanUserDao.selectTitanUser(Integer.parseInt(refundRequest.getTfsUerId()));
			    if(user !=null){
			    	refundOrderRequest.setCreator(user.getUsername());
			    }
			}
			
			if(refundRequest.getToBankCardOrAccount().equals(RefundTypeEnum.REFUND_ACCOUNT.type)){
				log.info("退款到账户完成");
				this.saveRefundOrder(refundOrderRequest);
				response.putSuccess();
				return response;
			}
			
			RefundOrderResponse refundOrderResponse = this.addRefundOrder(refundOrderRequest);
			if(!refundOrderResponse.isResult()){
				log.error("下退款单失败"+refundOrderResponse.getReturnMessage());
				reverseTransfer(accountTransfer, refundRequest);
				reverseTransfer(balanceAccountTransfer, refundRequest);
				//记录退款失败单
				this.deleteRefundTransOrder(titanTransferReq);
				response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
				isFreeze = true;
				return response;
			}
			
			NotifyRefundRequest notifyRefundRequest = this.convertToNotifyRefundRequest(refundRequest);
			notifyRefundRequest.setRefundOrderno(refundOrderResponse.getRefundOrderNo());
			NotifyRefundResponse notifyRefundResponse = this.notifyGateawayRefund(notifyRefundRequest);
			if(!notifyRefundResponse.isResult()){
				log.error("网关退款参数失败");
				//修改订单状态
				this.updateTransOrder(OrderStatusEnum.REFUND_FAIL, titanTransferDTO.getTransorderid());
				response.putErrorResult(TitanMsgCodeEnum.PACKAGE_REFUND_PARAM_FAIL);
				return response;
			}
			response.putSuccess();
			RefundStatusEnum refundStatus = RefundStatusEnum.REFUND_IN_PROCESS;
			if(refundRequest.getIsRealTime()==CommonConstant.REAL_TIME){
				refundStatus = RefundStatusEnum.REFUND_SUCCESS;
			}
			this.threadNotify(refundOrderRequest.getOrderId(),refundStatus);
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
	
	private void saveRefundOrder(RefundOrderRequest refundOrderRequest){
		TitanRefund titanRefund = new TitanRefund();
		titanRefund.setOrderid(refundOrderRequest.getOrderId());
		titanRefund.setRefundOrderno(null);
		titanRefund.setRefundAmount(refundOrderRequest.getAmount());
		titanRefund.setCreateTime(new Date());
		titanRefund.setOrderTime(DateUtil.sdf5.format(new Date()));
		titanRefund.setCreator(refundOrderRequest.getCreator());
		titanRefund.setStatus(RefundStatusEnum.REFUND_SUCCESS.status);
		titanRefund.setTransferAmount(refundOrderRequest.getAmount());
		titanRefund.setFee("0");
		try{
			titanRefundDao.insert(titanRefund);
		}catch(Exception e){
			log.error("保存退款单下单失败"+e.getMessage()+":data"+JsonUtil.objectToJson(titanRefund));
			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(refundOrderRequest.getOrderId(), "退款落单保存失败", OrderExceptionEnum.REFUND_INSERT, refundOrderRequest.getOrderId());
    		titanOrderService.saveOrderException(orderExceptionDTO);
		}
	}
	
	private void updateTransOrder(OrderStatusEnum status,Integer transId){
		//修改transOrderDTO
	    TransOrderDTO order = new TransOrderDTO();
	    order.setStatusid(status.getStatus());
	    order.setTransid(transId);
	    boolean flag = titanOrderService.updateTransOrder(order);
	    if(!flag){
	    	OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(transId.toString(), "退款网关请求失败", OrderExceptionEnum.REFUND_UPDATE_ORDER, transId.toString());
    		titanOrderService.saveOrderException(orderExceptionDTO);
	    }
	}
	
	private boolean deleteRefundTransOrder(TitanTransferReq req){
		try{
			return titanTransferReqDao.delete(req)==1?true:false;
		}catch(Exception e){
			log.error("删除失败",e);
			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(req.getTransferreqid().toString(), "删除退款单失败", OrderExceptionEnum.REFUND_UPDATE_TRANSFER, req.getTransferreqid().toString());
    		titanOrderService.saveOrderException(orderExceptionDTO);
		}
		return false;
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
		
		if(null ==refundOrderRequest || 
				!StringUtil.isValidString(refundOrderRequest.getAmount())||
				!StringUtil.isValidString(refundOrderRequest.getOrderId())){
			refundOrderResponse.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED); 
			return refundOrderResponse;
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
		titanRefund.setCreator(refundOrderRequest.getCreator());
		titanRefund.setTransferAmount(refundOrderRequest.getTransferAmount());
		titanRefund.setFee(refundOrderRequest.getFee());
		titanRefund.setBusinessInfo(refundOrderRequest.getBusinessInfo());
		titanRefund.setStatus(RefundStatusEnum.REFUND_IN_PROCESS.status);
		if(refundOrderRequest.getIsRealTime() == CommonConstant.REAL_TIME){
			titanRefund.setStatus(RefundStatusEnum.REFUND_SUCCESS.status);
		}
		titanRefund.setNotifyUrl(refundOrderRequest.getNotifyUrl());
		titanRefund.setUserorderid(refundOrderRequest.getUserOrderId());
		titanRefund.setPayOrderNo(refundOrderRequest.getPayOrderNo());
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
			return notifyRefundResponse;
		}
		List<NameValuePair> params = this.getGateawayParam(notifyRefundRequest);
		String response ="";
		HttpPost httpPost = new HttpPost(RSInvokeConstant.gateWayURL);
		try {
			HttpResponse resp = HttpClient.httpRequest(params,httpPost);
			if (null != resp) {
				HttpEntity entity = resp.getEntity();
				
					response = EntityUtils.toString(entity);
					notifyRefundResponse = RSConvertFiled2ObjectUtil.convertField2Object(NotifyRefundResponse.class, response);
					notifyRefundResponse.putSuccess();
					if(StringUtil.isValidString(notifyRefundResponse.getErrCode()) 
				    		|| StringUtil.isValidString(notifyRefundResponse.getErrMsg())){//通知退款失败
						log.error("退款通知异常:"+notifyRefundResponse.getErrMsg());
						notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
				    }
					return notifyRefundResponse;
			}
		} catch (ParseException e) {
			notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
			log.error("退款通知异常"+e.getMessage(),e);
		} catch (Exception e) {
			notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
			log.error("退款通知异常"+e.getMessage(),e);
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
			log.error("退款单"+notifyRefundResponse.getOrderNo()+"状态是:"+notifyRefundResponse.getRefundStatus());
			boolean isUpdate = false;
			if(notifyRefundResponse.isResult()){
				TransOrderDTO transOrderDTO = new TransOrderDTO();
				transOrderDTO.setOrderid(refundDTO.getOrderNo());
				String status = notifyRefundResponse.getRefundStatus();
				if(RefundStatusEnum.REFUND_SUCCESS.status==Integer.parseInt(status)){
					isUpdate = true;
					transOrderDTO.setStatusid(OrderStatusEnum.REFUND_SUCCESS.getStatus());
					refundDTO.setStatus(RefundStatusEnum.REFUND_SUCCESS.status);
			    }else if(RefundStatusEnum.REFUND_FAILURE.status==Integer.parseInt(status)
			    		|| RefundStatusEnum.REFUND_PROCESS_FAILURE.status==Integer.parseInt(status) 
			    		|| RefundStatusEnum.REFUND_AFAINST.status==Integer.parseInt(status) ){
			    	isUpdate = true;
			    	transOrderDTO.setStatusid(OrderStatusEnum.REFUND_FAIL.getStatus());
			    	refundDTO.setStatus(Integer.parseInt(status));
			    }	
				try{
					if(isUpdate){
						this.threadNotify(refundDTO.getOrderNo(),RefundStatusEnum.getRefundStatusEnumByStatus(refundDTO.getStatus()));
						titanOrderService.updateTransOrder(transOrderDTO);
						titanRefundDao.updateRefundDTO(refundDTO);
					}
				}catch(Exception e){
					log.error("定时器更新订单状态失败"+e.getMessage());
					OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(refundDTO.getOrderNo(), "定时器更新订单状态失败", OrderExceptionEnum.REFUND_UPDATE_TRANSORDER, refundDTO.getOrderNo());
		    		titanOrderService.saveOrderException(orderExceptionDTO);
				}
			}
		}
	}

	@Override
	public RefundDTO queryRefundRequest(RefundDTO refundDTO) {
		return null;
	}
	
	private void notifyTTMall(NotifyBean bean){
		HttpPost httpPost = new HttpPost(bean.getNotifyUrl());
		String response = "";
		try{
			response = TitanjrHttpTools.confirmRefund(bean, httpPost);
			if(!StringUtil.isValidString(response)){
				log.error("回调退款单失败:" + bean.getUserOrderId());
				throw new Exception("回调异常");
			}
			CallBackInfo callBackInfo = TitanjrHttpTools.analyzeResponse(response);
			if (!"000".equals(callBackInfo.getCode())) {
				log.error("回调异常，TTMALL返回失败");
				throw new Exception("回调异常，TTMALL返回失败");
			}
			
			log.info("回调TTMALL成功");
			
		}catch(Exception e){
			log.error("退款回调失败",e);
			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(
					bean.getUserOrderId(), "",
					OrderExceptionEnum.Finance_Confirm,
					response);
			titanOrderService.saveOrderException(orderExceptionDTO);
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
					log.error("查询退款单异常："+orderNo);
					return;
				}
				refund = reFundList.get(0);
				NotifyBean bean = new NotifyBean();
				bean.setPayOrderNo(refund.getPayOrderNo());
				bean.setNotifyUrl(refund.getNotifyUrl());
				bean.setUserOrderId(refund.getUserOrderId());
				bean.setCode(refundStatus.status.toString());
				bean.setBusinessInfo(refund.getBusinessInfo());
				log.info("退款单回调的参数："+JSONSerializer.toJSON(bean));
				notifyTTMall(bean);
			}
		});
		t.start();
	}
	
}
