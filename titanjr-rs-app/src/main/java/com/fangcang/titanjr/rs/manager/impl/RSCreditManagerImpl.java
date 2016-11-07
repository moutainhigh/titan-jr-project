package com.fangcang.titanjr.rs.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Rop.api.ApiException;
import com.Rop.api.domain.BorrowRepayment;
import com.Rop.api.request.WheatfieldInterestRepaymentQueryborrowinfoRequest;
import com.Rop.api.request.WheatfieldOprsystemCreditCompanyRequest;
import com.Rop.api.request.WheatfieldOrderMixserviceCreditapplicationRequest;
import com.Rop.api.request.WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest;
import com.Rop.api.request.WheatfieldOrderServiceAgreementconfirmRequest;
import com.Rop.api.request.WheatfieldOrderServiceNewloanapplyRequest;
import com.Rop.api.response.WheatfieldInterestRepaymentQueryborrowinfoResponse;
import com.Rop.api.response.WheatfieldOprsystemCreditCompanyResponse;
import com.Rop.api.response.WheatfieldOrderMixserviceCreditapplicationResponse;
import com.Rop.api.response.WheatfieldOrderMixserviceCreditmerchantinfoqueryResponse;
import com.Rop.api.response.WheatfieldOrderServiceAgreementconfirmResponse;
import com.Rop.api.response.WheatfieldOrderServiceNewloanapplyResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.rs.dto.TBorrowRepayment;
import com.fangcang.titanjr.rs.manager.RSCreditManager;
import com.fangcang.titanjr.rs.request.InterestRepaymentQueryborrowinfoRequest;
import com.fangcang.titanjr.rs.request.OprsystemCreditCompanyRequest;
import com.fangcang.titanjr.rs.request.OrderMixserviceCreditapplicationRequest;
import com.fangcang.titanjr.rs.request.OrderMixserviceCreditmerchantinfoqueryRequest;
import com.fangcang.titanjr.rs.request.OrderServiceAgreementconfirmRequest;
import com.fangcang.titanjr.rs.request.OrderServiceNewloanapplyRequest;
import com.fangcang.titanjr.rs.response.InterestRepaymentQueryborrowinfoResponse;
import com.fangcang.titanjr.rs.response.OprsystemCreditCompanyResponse;
import com.fangcang.titanjr.rs.response.OrderMixserviceCreditapplicationResponse;
import com.fangcang.titanjr.rs.response.OrderMixserviceCreditmerchantinfoqueryResponse;
import com.fangcang.titanjr.rs.response.OrderServiceAgreementconfirmResponse;
import com.fangcang.titanjr.rs.response.OrderServiceNewloanapplyResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.util.MyBeanUtil;

public class RSCreditManagerImpl implements RSCreditManager {
	
	private static final Log log = LogFactory.getLog(RSCreditManagerImpl.class);
	private static final boolean needCheckRequest = true;// 是否校验请求
	
	@Override
	public OprsystemCreditCompanyResponse oprsystemCreditCompany(
			OprsystemCreditCompanyRequest request) {
		OprsystemCreditCompanyResponse response = new OprsystemCreditCompanyResponse();
		WheatfieldOprsystemCreditCompanyRequest req = new WheatfieldOprsystemCreditCompanyRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOprsystemCreditCompanyResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用oprsystemCreditCompany方法--请求参数OprsystemCreditCompanyRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOprsystemCreditCompanyResponse: \n :"+Tools.gsonToString(rsp));
			if (rsp != null) {
				if (rsp.isSuccess() != true) {
					String errorMsg;
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("oprsystemCreditCompany接口调用异常,参数OprsystemCreditCompanyRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("oprsystemCreditCompany接口调用异常,参数OprsystemCreditCompanyRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public OrderMixserviceCreditapplicationResponse orderMixserviceCreditapplication(
			OrderMixserviceCreditapplicationRequest request) {
		OrderMixserviceCreditapplicationResponse response = new OrderMixserviceCreditapplicationResponse();
		WheatfieldOrderMixserviceCreditapplicationRequest req = new WheatfieldOrderMixserviceCreditapplicationRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderMixserviceCreditapplicationResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用orderMixserviceCreditapplication方法--请求参数OrderMixserviceCreditapplicationRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderMixserviceCreditapplicationResponse: \n :"+Tools.gsonToString(rsp));
			if (rsp != null) {
				if (rsp.isSuccess() != true) {
					String errorMsg;
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
				} else {
					response.setSuccess(true);
					response.setOrderid(rsp.getOrderid());
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("orderMixserviceCreditapplication接口调用异常,参数OrderMixserviceCreditapplicationRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("orderMixserviceCreditapplication接口调用异常,参数OrderMixserviceCreditapplicationRequest:"+Tools.gsonToString(request),e);
		
		}
		
		return response;
	}

	@Override
	public OrderServiceAgreementconfirmResponse orderServiceAgreementConfirm(
			OrderServiceAgreementconfirmRequest request) {
		OrderServiceAgreementconfirmResponse response = new OrderServiceAgreementconfirmResponse();
		WheatfieldOrderServiceAgreementconfirmRequest req = new WheatfieldOrderServiceAgreementconfirmRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderServiceAgreementconfirmResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用orderServiceAgreementConfirm方法--请求参数OrderServiceAgreementconfirmRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderServiceAgreementconfirmResponse: \n :"+Tools.gsonToString(rsp));
			if (rsp != null) {
				if (rsp.isSuccess() != true) {
					String errorMsg;
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("orderServiceAgreementConfirm接口调用异常,参数OrderServiceAgreementconfirmRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("orderServiceAgreementConfirm接口调用异常,参数OrderServiceAgreementconfirmRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public OrderMixserviceCreditmerchantinfoqueryResponse orderMixserviceCreditmerchantinfoquery(
			OrderMixserviceCreditmerchantinfoqueryRequest request) {
		OrderMixserviceCreditmerchantinfoqueryResponse response = new OrderMixserviceCreditmerchantinfoqueryResponse();
		WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest req = new WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderMixserviceCreditmerchantinfoqueryResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用orderMixserviceCreditmerchantinfoquery方法(机构授信信息查询)--请求参数OrderMixserviceCreditmerchantinfoqueryRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderMixserviceCreditmerchantinfoqueryResponse: \n :"+Tools.gsonToString(rsp));
			if (rsp != null) {
				if (rsp.isSuccess() != true) {
					String errorMsg;
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("orderMixserviceCreditmerchantinfoquery接口(机构授信信息查询)调用异常,参数OrderMixserviceCreditmerchantinfoqueryRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("orderMixserviceCreditmerchantinfoquery接口(机构授信信息查询)调用异常,参数OrderMixserviceCreditmerchantinfoqueryRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public OrderServiceNewloanapplyResponse orderServiceNewloanapply(
			OrderServiceNewloanapplyRequest request) {
		OrderServiceNewloanapplyResponse response = new OrderServiceNewloanapplyResponse();
		WheatfieldOrderServiceNewloanapplyRequest req = new WheatfieldOrderServiceNewloanapplyRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderServiceNewloanapplyResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用orderServiceNewloanapply方法(个人贷款申请)--请求参数OrderServiceNewloanapplyRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderServiceNewloanapplyResponse: \n :"+Tools.gsonToString(rsp));
			if (rsp != null) {
				if (rsp.isSuccess() != true) {
					String errorMsg;
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
				} else {
					response.setSuccess(true);
					response.setOrderid(rsp.getOrderid());
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("orderServiceNewloanapply方法(个人贷款申请)调用异常,参数OrderServiceNewloanapplyRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("orderServiceNewloanapply方法(个人贷款申请)调用异常,参数OrderServiceNewloanapplyRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}
		

	@Override
	public InterestRepaymentQueryborrowinfoResponse interestRepaymentQueryBorrowinfo(
			InterestRepaymentQueryborrowinfoRequest request) {
		InterestRepaymentQueryborrowinfoResponse response = new InterestRepaymentQueryborrowinfoResponse();
		WheatfieldInterestRepaymentQueryborrowinfoRequest req = new WheatfieldInterestRepaymentQueryborrowinfoRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldInterestRepaymentQueryborrowinfoResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用interestRepaymentQueryBorrowinfo方法(查询应还款信息)--请求参数InterestRepaymentQueryborrowinfoRequest："+Tools.gsonToString(request)+",返回报文WheatfieldInterestRepaymentQueryborrowinfoResponse: \n :"+Tools.gsonToString(rsp));
			if (rsp != null) {
				if (rsp.isSuccess() != true) {
					String errorMsg;
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
				} else {
					response.setSuccess(true);
					response.settBorrowRepayment(rsp.getBorrowrepayment());
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("interestRepaymentQueryBorrowinfo方法(查询应还款信息)调用异常,参数InterestRepaymentQueryborrowinfoRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("interestRepaymentQueryBorrowinfo方法(查询应还款信息)调用异常,参数InterestRepaymentQueryborrowinfoRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}
	
}
