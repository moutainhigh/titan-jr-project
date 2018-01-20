package com.fangcang.titanjr.rs.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.fangcang.titanjr.rs.util.RSInvokeConstant;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Rop.api.domain.SHBalanceInfo;
import com.Rop.api.request.WheatfieldBalanceGetlistRequest;
import com.Rop.api.request.WheatfieldOrderSaveWithcardRequest;
import com.Rop.api.request.WheatfieldOrderServiceAuthcodeserviceRequest;
import com.Rop.api.request.WheatfieldOrderServiceMultitransferQueryRequest;
import com.Rop.api.request.WheatfieldOrderServiceReturngoodsRequest;
import com.Rop.api.request.WheatfieldOrderServiceThawauthcodeRequest;
import com.Rop.api.request.WheatfieldOrderServiceWithdrawserviceRequest;
import com.Rop.api.request.WheatfieldOrderTransferRequest;
import com.Rop.api.response.WheatfieldBalanceGetlistResponse;
import com.Rop.api.response.WheatfieldOrderSaveWithcardResponse;
import com.Rop.api.response.WheatfieldOrderServiceAuthcodeserviceResponse;
import com.Rop.api.response.WheatfieldOrderServiceMultitransferQueryResponse;
import com.Rop.api.response.WheatfieldOrderServiceReturngoodsResponse;
import com.Rop.api.response.WheatfieldOrderServiceThawauthcodeResponse;
import com.Rop.api.response.WheatfieldOrderServiceWithdrawserviceResponse;
import com.Rop.api.response.WheatfieldOrderTransferResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.rs.dto.BalanceInfo;
import com.fangcang.titanjr.rs.dto.OrderTransferFlow;
import com.fangcang.titanjr.rs.dto.TradeInfoList;
import com.fangcang.titanjr.rs.dto.Transorderinfo;
import com.fangcang.titanjr.rs.manager.RSAccTradeManager;
import com.fangcang.titanjr.rs.request.AccountBalanceQueryRequest;
import com.fangcang.titanjr.rs.request.AccountTransferRequest;
import com.fangcang.titanjr.rs.request.AccountWithDrawRequest;
import com.fangcang.titanjr.rs.request.BalanceFreezeRequest;
import com.fangcang.titanjr.rs.request.BalanceUnFreezeRequest;
import com.fangcang.titanjr.rs.request.OrderOperateRequest;
import com.fangcang.titanjr.rs.request.OrderSaveWithCardRequest;
import com.fangcang.titanjr.rs.request.OrderTransferFlowRequest;
import com.fangcang.titanjr.rs.request.RSRefundRequest;
import com.fangcang.titanjr.rs.request.OrdernQueryRequest;
import com.fangcang.titanjr.rs.response.AccountBalanceQueryResponse;
import com.fangcang.titanjr.rs.response.AccountTransferResponse;
import com.fangcang.titanjr.rs.response.AccountWithDrawResponse;
import com.fangcang.titanjr.rs.response.BalanceFreezeResponse;
import com.fangcang.titanjr.rs.response.BalanceUnFreezeResponse;
import com.fangcang.titanjr.rs.response.OrderInfoResponse;
import com.fangcang.titanjr.rs.response.OrderOperateResponse;
import com.fangcang.titanjr.rs.response.OrderSaveWithCardResponse;
import com.fangcang.titanjr.rs.response.OrderTransferFlowResponse;
import com.fangcang.titanjr.rs.response.RsRefundResponse;
import com.fangcang.titanjr.rs.response.OrdernQueryResponse;
import com.fangcang.titanjr.rs.response.Retbeanlist;
import com.fangcang.titanjr.rs.util.MyConvertXmlToObject;
import com.fangcang.util.MyBeanUtil;
import com.titanjr.fop.constants.FuncCodeEnum;
import com.titanjr.fop.request.WheatfieldOrderOperRequest;
import com.titanjr.fop.request.WheatfieldOrdernQueryRequest;
import com.titanjr.fop.response.WheatfieldOrderOperResponse;
import com.titanjr.fop.response.WheatfieldOrdernQueryResponse;

public class RSAccTradeManagerImpl implements RSAccTradeManager {

	private static final Log log = LogFactory
			.getLog(RSAccTradeManagerImpl.class);

	private static final boolean needCheckRequest = true;// 是否校验请求
	@Override
	public AccountBalanceQueryResponse queryAccountBalance(
			AccountBalanceQueryRequest accountBalanceQueryRequest) {
		AccountBalanceQueryResponse response = new AccountBalanceQueryResponse();
		try{
			WheatfieldBalanceGetlistRequest req = new WheatfieldBalanceGetlistRequest();
			if(needCheckRequest){
				accountBalanceQueryRequest.check();
			}
			MyBeanUtil.copyProperties(req, accountBalanceQueryRequest);
			WheatfieldBalanceGetlistResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用queryAccountBalance返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口queryAccountBalance异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setBalanceInfoList(shbalanceinfoToBalanceInfo(rsp.getShbalanceinfos()));
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用queryAccountBalance过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public BalanceFreezeResponse freezeAccountBalance(
			BalanceFreezeRequest balanceFreezeRequest) {
		BalanceFreezeResponse response = new BalanceFreezeResponse();
		try{
			WheatfieldOrderServiceAuthcodeserviceRequest req = new WheatfieldOrderServiceAuthcodeserviceRequest();
			if(needCheckRequest){
				balanceFreezeRequest.check();
			}
			MyBeanUtil.copyProperties(req, balanceFreezeRequest);
			WheatfieldOrderServiceAuthcodeserviceResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用freezeAccountBalance方法,冻结参数："+Tools.gsonToString(balanceFreezeRequest)+",返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口freezeAccountBalance异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setAuthcode(rsp.getAuthcode());
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用freezeAccountBalance过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public BalanceUnFreezeResponse unFreezeAccountBalance(
			BalanceUnFreezeRequest balanceUnFreezeRequest) {
		BalanceUnFreezeResponse response = new BalanceUnFreezeResponse();
		try{
			WheatfieldOrderServiceThawauthcodeRequest req = new WheatfieldOrderServiceThawauthcodeRequest();
			if(needCheckRequest){
				balanceUnFreezeRequest.check();
			}
			MyBeanUtil.copyProperties(req, balanceUnFreezeRequest);
			WheatfieldOrderServiceThawauthcodeResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用unFreezeAccountBalance返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口unFreezeAccountBalance异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setRetcode(rsp.getRetcode());
					response.setRetmsg(rsp.getRetmsg());
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用freezeAccountBalance过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public OrderOperateResponse operateOrder(
			OrderOperateRequest orderOperateRequest) {
		OrderOperateResponse response = new OrderOperateResponse();
		try{
			WheatfieldOrderOperRequest req = new WheatfieldOrderOperRequest();
			if(needCheckRequest){
				orderOperateRequest.check();
			}
			MyBeanUtil.copyProperties(req, orderOperateRequest);
			log.info("调用融数落单的参数dao:"+JSONSerializer.toJSON(req));
			WheatfieldOrderOperResponse rsp = RSInvokeConstant.fopClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用operateOrder返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口operateOrder异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setOrderid(rsp.getOrderid());
					//订单查询操作的时候进行xml解析
					if(orderOperateRequest !=null && "3".equals(orderOperateRequest.getOpertype())){//3代表查询操作
						Object obj = MyConvertXmlToObject.convertXml2Object(rsp.getBody());
						if(obj !=null){
							OrderInfoResponse orderInfoRequest = (OrderInfoResponse)obj;
							if(orderInfoRequest !=null && orderInfoRequest.getRetbeanlist()!=null){
								Retbeanlist retbeanlist = orderInfoRequest.getRetbeanlist();
								if(retbeanlist!=null && retbeanlist.getOrderOperateInfo()!=null){
									response.setOrderOperateInfoList(retbeanlist.getOrderOperateInfo());
								}
							}
						}
					}
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		}catch(RSValidateException e){
			response.setReturnCode(e.getErrorCode());
			response.setReturnMsg(e.getErrorMsg());
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用operateOrder过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public OrderTransferFlowResponse queryOrderTranferFlow(
			OrderTransferFlowRequest orderTransferFlowRequest) {
		OrderTransferFlowResponse response = new OrderTransferFlowResponse();
		try{
			WheatfieldOrderServiceMultitransferQueryRequest req = new WheatfieldOrderServiceMultitransferQueryRequest();
			if(needCheckRequest){
				orderTransferFlowRequest.check();
			}
			MyBeanUtil.copyProperties(req, orderTransferFlowRequest);
			WheatfieldOrderServiceMultitransferQueryResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用queryOrderTranferFlow返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口queryOrderTranferFlow异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setRetcode(rsp.getRetcode());
					response.setRetmsg(rsp.getRetmsg());
					Object obj = MyConvertXmlToObject.convertXml2Object(rsp.getBody());
					if(obj !=null && CommonConstant.OPERATE_SUCCESS.equals(rsp.getIs_success())){
						OrderTransferFlow orderTransferFlow = (OrderTransferFlow)obj;
						if(orderTransferFlow !=null && orderTransferFlow.getTradeInfoList()!=null){
							TradeInfoList orderTransferFlowList = orderTransferFlow.getTradeInfoList();
							if(orderTransferFlowList!=null && orderTransferFlowList.getTradeInfoList()!=null){
								response.setTradeInfoList(orderTransferFlowList.getTradeInfoList());
							}
						}
					}
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用queryOrderTranferFlow过程出现未知异常", e);
		}
		return response;	
		
	}

	@Override
	public AccountTransferResponse accountBalanceTransfer(
			AccountTransferRequest accountTransferRequest) {
		AccountTransferResponse response = new AccountTransferResponse();
		try{
			WheatfieldOrderTransferRequest req = new WheatfieldOrderTransferRequest();;
			if(needCheckRequest){
				accountTransferRequest.check();
			}
			MyBeanUtil.copyProperties(req, accountTransferRequest);
			log.info("转账的入参dao:"+JSONSerializer.toJSON(req));
			WheatfieldOrderTransferResponse rsp =null;
			
			if("0".equals(req.getAmount())){//如果转账金额为0.则直接成功
				rsp =new WheatfieldOrderTransferResponse();
				rsp.setIs_success(CommonConstant.OPERATE_SUCCESS);
			}else{
				rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			}
			
			if (rsp != null) {
				log.info("调用accountBalanceTransfer返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口accountBalanceTransfer异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setRetcode(rsp.getRetcode());
					response.setRetmsg(rsp.getRetmsg());
					response.setOrderid(rsp.getOrderid());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用accountBalanceTransfer过程出现未知异常", e);
		}
		return response;	
	}

	@Override
	public AccountWithDrawResponse accountBalanceWithDraw(
			AccountWithDrawRequest accountWithDrawRequest) {
		AccountWithDrawResponse response = new AccountWithDrawResponse();
		try{
			WheatfieldOrderServiceWithdrawserviceRequest req = new WheatfieldOrderServiceWithdrawserviceRequest();
			if(needCheckRequest){
				accountWithDrawRequest.check();
			}
			MyBeanUtil.copyProperties(req, accountWithDrawRequest);
			WheatfieldOrderServiceWithdrawserviceResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用accountBalanceWithDraw返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口accountBalanceWithDraw异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnMsg(rsp.getMsg());
					if(rsp.getIs_success().equals("true")){
						response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
						response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
					}
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用accountBalanceWithDraw过程出现未知异常", e);
		}
		return response;	
	}
	
	/**
	 * 将融数账户信息转换为本地的账户信息
	 * @param shBalanceInfos 融数返回的账户信息
	 * @return BalanceInfo本地账户信息
	 * @author fangdaikang
	 */
	private List<BalanceInfo> shbalanceinfoToBalanceInfo(List<SHBalanceInfo> shBalanceInfos){
		List<BalanceInfo> balanceInfos = new ArrayList<BalanceInfo>();
		if(shBalanceInfos !=null && shBalanceInfos.size()>0){
			for(int i=0;i<shBalanceInfos.size();i++){
				BalanceInfo balanceInfo = new BalanceInfo();
				SHBalanceInfo  shBalanceInfo = shBalanceInfos.get(i);
				if(shBalanceInfo !=null){
					balanceInfo.setAmount(shBalanceInfo.getAmount());
					balanceInfo.setBalancecredit(shBalanceInfo.getBalancecredit());
					balanceInfo.setBalancefrozon(shBalanceInfo.getBalancefrozon());
					balanceInfo.setBalanceoverlimit(shBalanceInfo.getBalanceoverlimit());
					balanceInfo.setBalancesettle(shBalanceInfo.getBalancesettle());
					
					balanceInfo.setBalanceusable(shBalanceInfo.getBalanceusable());
					balanceInfo.setProductid(shBalanceInfo.getProductid());
					balanceInfo.setUserid(shBalanceInfo.getUserid());
					balanceInfos.add(balanceInfo);
				}
				
			}
			
		}
		
		return balanceInfos;
	}


	@Override
	public OrderSaveWithCardResponse orderSaveWithdraw(
			OrderSaveWithCardRequest orderSaveWithCardRequest) {
		OrderSaveWithCardResponse response = new OrderSaveWithCardResponse();
		try{
			WheatfieldOrderSaveWithcardRequest req = new WheatfieldOrderSaveWithcardRequest();
			if(needCheckRequest){
				orderSaveWithCardRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, orderSaveWithCardRequest);
			WheatfieldOrderSaveWithcardResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用orderSaveWithCardRequest返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口orderSaveWithCardRequest异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnMsg(rsp.getMsg());
					response.setOrderId(rsp.getOrderid());
					if(rsp.getIs_success().equals(CommonConstant.OPERATE_SUCCESS)){
						response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
						response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
					}
				}
			}else{
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
			
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用orderSaveWithCardRequest过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public RsRefundResponse addOrderRefund(RSRefundRequest refundRequest) {
		RsRefundResponse response = new RsRefundResponse();
		try{
			WheatfieldOrderServiceReturngoodsRequest req = new WheatfieldOrderServiceReturngoodsRequest();
			if(needCheckRequest){
				refundRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, refundRequest);
			WheatfieldOrderServiceReturngoodsResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用addOrderRefund返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口addOrderRefund异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnMsg(rsp.getMsg());
					response.setRefundOrderNo(rsp.getBatchno());;
					if(rsp.getIs_success().equals(CommonConstant.OPERATE_SUCCESS)){
						response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
						response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
					}
				}
			}else{
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
			
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("下退款单失败"+e.getMessage());
		}
		
		return response;
	}
	
	@Override
	public OrdernQueryResponse ordernQuery(OrdernQueryRequest ordernQueryRequest) {
		OrdernQueryResponse response = new OrdernQueryResponse();
		try{
			WheatfieldOrdernQueryRequest req = new WheatfieldOrdernQueryRequest();
			if(needCheckRequest){
				ordernQueryRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, ordernQueryRequest);
			req.setFunccode(FuncCodeEnum.RECHARGE_4015.getKey());
			WheatfieldOrdernQueryResponse rsp = RSInvokeConstant.fopClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用ordernQuery返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null &&  !"".equals(rsp.getSubMsg())) {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口WheatfieldOrdernQueryRequest异常：" + errorMsg);
					return response;
				} else {
					response.setSuccess(true);
					response.setReturnMsg(rsp.getMsg());
					if(rsp.getTransorderinfos() !=null && rsp.getTransorderinfos().size()>0){
						List<Transorderinfo> infos = new ArrayList<Transorderinfo>();
						for(int i=0;i<rsp.getTransorderinfos().size();i++){
							Transorderinfo info = new Transorderinfo();
							MyBeanUtil.copyBeanProperties(info, rsp.getTransorderinfos().get(i));
							infos.add(info);
						}
						response.setTransorderinfo(infos);
					}
					response.setOperateStatus(CommonConstant.OPERATE_SUCCESS);
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
					return response;
				}
			}else{
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
			
		}catch(Exception e){
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用orderSaveWithCardRequest过程出现未知异常", e);
		}
		return response;
	}
}
