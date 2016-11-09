package com.fangcang.titanjr.rs.manager.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Rop.api.ApiException;
import com.Rop.api.domain.UserRepayment;
import com.Rop.api.request.WheatfieldInterestRepaymentQueryborrowinfoRequest;
import com.Rop.api.request.WheatfieldInterestRepaymentQueryuserinitiativerepaymentRequest;
import com.Rop.api.request.WheatfieldInterestRepaymentQueryuserrepaymentRequest;
import com.Rop.api.request.WheatfieldInterestRepaymentUserinitiativerepamentRequest;
import com.Rop.api.request.WheatfieldOprsystemCreditCompanyRequest;
import com.Rop.api.request.WheatfieldOrderMixserviceCreditapplicationRequest;
import com.Rop.api.request.WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest;
import com.Rop.api.request.WheatfieldOrderMixserviceQueryloanapplyRequest;
import com.Rop.api.request.WheatfieldOrderMixserviceStoploanRequest;
import com.Rop.api.request.WheatfieldOrderServiceAgreementconfirmRequest;
import com.Rop.api.request.WheatfieldOrderServiceNewloanapplyRequest;
import com.Rop.api.response.WheatfieldInterestRepaymentQueryborrowinfoResponse;
import com.Rop.api.response.WheatfieldInterestRepaymentQueryuserinitiativerepaymentResponse;
import com.Rop.api.response.WheatfieldInterestRepaymentQueryuserrepaymentResponse;
import com.Rop.api.response.WheatfieldInterestRepaymentUserinitiativerepamentResponse;
import com.Rop.api.response.WheatfieldOprsystemCreditCompanyResponse;
import com.Rop.api.response.WheatfieldOrderMixserviceCreditapplicationResponse;
import com.Rop.api.response.WheatfieldOrderMixserviceCreditmerchantinfoqueryResponse;
import com.Rop.api.response.WheatfieldOrderMixserviceQueryloanapplyResponse;
import com.Rop.api.response.WheatfieldOrderMixserviceStoploanResponse;
import com.Rop.api.response.WheatfieldOrderServiceAgreementconfirmResponse;
import com.Rop.api.response.WheatfieldOrderServiceNewloanapplyResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.rs.dto.TUserRepayment;
import com.fangcang.titanjr.rs.manager.RSCreditManager;
import com.fangcang.titanjr.rs.request.NewLoanApplyRequest;
import com.fangcang.titanjr.rs.request.OprsystemCreditCompanyRequest;
import com.fangcang.titanjr.rs.request.OrderMixserviceCreditapplicationRequest;
import com.fangcang.titanjr.rs.request.OrderServiceAgreementConfirmRequest;
import com.fangcang.titanjr.rs.request.QueryBorrowinfoRequest;
import com.fangcang.titanjr.rs.request.QueryCreditMerchantInfoRequest;
import com.fangcang.titanjr.rs.request.QueryLoanApplyRequest;
import com.fangcang.titanjr.rs.request.QueryUserInitiativeRepaymentRequest;
import com.fangcang.titanjr.rs.request.QueryUserRepaymentRequest;
import com.fangcang.titanjr.rs.request.StopLoanRequest;
import com.fangcang.titanjr.rs.request.UserInitiativeRepamentRequest;
import com.fangcang.titanjr.rs.response.NewLoanApplyResponse;
import com.fangcang.titanjr.rs.response.OprsystemCreditCompanyResponse;
import com.fangcang.titanjr.rs.response.OrderMixserviceCreditapplicationResponse;
import com.fangcang.titanjr.rs.response.OrderServiceAgreementConfirmResponse;
import com.fangcang.titanjr.rs.response.QueryBorrowinfoResponse;
import com.fangcang.titanjr.rs.response.QueryCreditMerchantInfoResponse;
import com.fangcang.titanjr.rs.response.QueryLoanApplyResponse;
import com.fangcang.titanjr.rs.response.QueryUserInitiativeRepaymentResponse;
import com.fangcang.titanjr.rs.response.QueryUserRepaymentResponse;
import com.fangcang.titanjr.rs.response.StopLoanResponse;
import com.fangcang.titanjr.rs.response.UserInitiativeRepamentResponse;
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
			log.info("调用oprsystemCreditCompany方法(上报企业资料信息)--请求参数OprsystemCreditCompanyRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOprsystemCreditCompanyResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("oprsystemCreditCompany接口(上报企业资料信息)调用异常,参数OprsystemCreditCompanyRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("oprsystemCreditCompany接口(上报企业资料信息)调用异常,参数OprsystemCreditCompanyRequest:"+Tools.gsonToString(request),e);
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
			log.info("调用orderMixserviceCreditapplication方法(申请授信)--请求参数OrderMixserviceCreditapplicationRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderMixserviceCreditapplicationResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("orderMixserviceCreditapplication接口(申请授信)调用异常,参数OrderMixserviceCreditapplicationRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("orderMixserviceCreditapplication接口(申请授信)调用异常,参数OrderMixserviceCreditapplicationRequest:"+Tools.gsonToString(request),e);
		
		}
		
		return response;
	}

	@Override
	public OrderServiceAgreementConfirmResponse agreementConfirm(
			OrderServiceAgreementConfirmRequest request) {
		OrderServiceAgreementConfirmResponse response = new OrderServiceAgreementConfirmResponse();
		WheatfieldOrderServiceAgreementconfirmRequest req = new WheatfieldOrderServiceAgreementconfirmRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderServiceAgreementconfirmResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用agreementConfirm方法(协议确认)--请求参数OrderServiceAgreementconfirmRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderServiceAgreementconfirmResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("agreementConfirm接口(协议确认)调用异常,参数OrderServiceAgreementconfirmRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("agreementConfirm接口(协议确认)调用异常,参数OrderServiceAgreementConfirmRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public QueryCreditMerchantInfoResponse queryCreditMerchantInfo(
			QueryCreditMerchantInfoRequest request) {
		QueryCreditMerchantInfoResponse response = new QueryCreditMerchantInfoResponse();
		WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest req = new WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderMixserviceCreditmerchantinfoqueryResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用queryCreditMerchantInfo方法(机构授信信息查询)--请求参数QueryCreditMerchantInfoRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderMixserviceCreditmerchantinfoqueryResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("queryCreditMerchantInfo接口(机构授信信息查询)调用异常,参数QueryCreditMerchantInfoRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("queryCreditMerchantInfo接口(机构授信信息查询)调用异常,参数QueryCreditMerchantInfoRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public NewLoanApplyResponse newLoanApply(
			NewLoanApplyRequest request) {
		NewLoanApplyResponse response = new NewLoanApplyResponse();
		WheatfieldOrderServiceNewloanapplyRequest req = new WheatfieldOrderServiceNewloanapplyRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderServiceNewloanapplyResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用newLoanApply方法(个人贷款申请)--请求参数NewLoanApplyRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderServiceNewloanapplyResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("newLoanApply方法(个人贷款申请)调用异常,参数NewLoanApplyRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("newLoanApply方法(个人贷款申请)调用异常,参数NewLoanApplyRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}
		

	@Override
	public QueryBorrowinfoResponse queryBorrowinfo(
			QueryBorrowinfoRequest request) {
		QueryBorrowinfoResponse response = new QueryBorrowinfoResponse();
		WheatfieldInterestRepaymentQueryborrowinfoRequest req = new WheatfieldInterestRepaymentQueryborrowinfoRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldInterestRepaymentQueryborrowinfoResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用queryBorrowinfo方法(查询应还款信息)--请求参数QueryBorrowinfoRequest："+Tools.gsonToString(request)+",返回WheatfieldInterestRepaymentQueryborrowinfoResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("queryBorrowinfo方法(查询应还款信息)调用异常,参数QueryBorrowinfoRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("queryBorrowinfo方法(查询应还款信息)调用异常,参数QueryBorrowinfoRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public StopLoanResponse stopLoan(StopLoanRequest request) {
		
		StopLoanResponse response = new StopLoanResponse();
		WheatfieldOrderMixserviceStoploanRequest req = new WheatfieldOrderMixserviceStoploanRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderMixserviceStoploanResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用stopLoan方法(终止贷款)--请求参数StopLoanRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderMixserviceStoploanResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("stopLoan方法(终止贷款)调用异常,参数StopLoanRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("stopLoan方法(终止贷款)调用异常,参数StopLoanRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public UserInitiativeRepamentResponse userInitiativeRepament(
			UserInitiativeRepamentRequest request) {
		UserInitiativeRepamentResponse response = new UserInitiativeRepamentResponse();
		WheatfieldInterestRepaymentUserinitiativerepamentRequest req = new WheatfieldInterestRepaymentUserinitiativerepamentRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldInterestRepaymentUserinitiativerepamentResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用userInitiativeRepament方法(终止贷款)--请求参数UserInitiativeRepamentRequest："+Tools.gsonToString(request)+",返回报文WheatfieldInterestRepaymentUserinitiativerepamentResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("userInitiativeRepament方法((主动还款)调用异常,参数UserInitiativeRepamentRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("userInitiativeRepament方法(主动还款)调用异常,参数OrderMixserviceStoploanRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public QueryUserInitiativeRepaymentResponse queryUserInitiativeRepayment(QueryUserInitiativeRepaymentRequest request) {
		
		QueryUserInitiativeRepaymentResponse response = new QueryUserInitiativeRepaymentResponse();
		WheatfieldInterestRepaymentQueryuserinitiativerepaymentRequest req = new WheatfieldInterestRepaymentQueryuserinitiativerepaymentRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldInterestRepaymentQueryuserinitiativerepaymentResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用queryUserInitiativeRepayment方法(查询主动还款)--请求参数QueryUserInitiativeRepaymentRequest："+Tools.gsonToString(request)+",返回报文WheatfieldInterestRepaymentQueryuserinitiativerepaymentResponse: \n :"+Tools.gsonToString(rsp));
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
			log.error("queryUserInitiativeRepayment方法(查询主动还款)调用异常,参数QueryUserInitiativeRepaymentRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("queryUserInitiativeRepayment方法(查询主动还款)调用异常,参数QueryUserInitiativeRepaymentRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}

	@Override
	public QueryLoanApplyResponse queryLoanApply(
			QueryLoanApplyRequest request) {
		QueryLoanApplyResponse response = new QueryLoanApplyResponse();
		WheatfieldOrderMixserviceQueryloanapplyRequest req = new WheatfieldOrderMixserviceQueryloanapplyRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldOrderMixserviceQueryloanapplyResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用queryLoanApply方法(查询贷款订单状态)--请求参数QueryLoanApplyRequest："+Tools.gsonToString(request)+",返回报文WheatfieldOrderMixserviceQueryloanapplyResponse: \n :"+Tools.gsonToString(rsp));
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
					rop2QueryLoanApplyResponse(response,rsp);
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
			log.error("queryLoanApply方法(查询贷款订单状态)调用异常,参数QueryLoanApplyRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("queryLoanApply方法(查询贷款订单状态)调用异常,参数QueryLoanApplyRequest:"+Tools.gsonToString(request),e);
		}
		
		return response;
	}
	/**
	 * 对象转换
	 * @param response
	 * @param rsp
	 */
	private void rop2QueryLoanApplyResponse(QueryLoanApplyResponse response,WheatfieldOrderMixserviceQueryloanapplyResponse rsp){
		response.setStatustring(rsp.getStatustring());
		response.setRatetemplrate(rsp.getRatetemplrate());
		response.setShouldamount(rsp.getShouldamount());
		response.setUrlkeya(rsp.getUrlkeya());
		response.setUrlkeyb(rsp.getUrlkeyb());
		response.setUrlkeyc(rsp.getUrlkeyc());
		response.setUrlkeyd(rsp.getUrlkeyd());
		response.setLoanmoney(rsp.getLoanmoney());
		response.setLoandate(rsp.getLoandate());
		response.setCreatetime(rsp.getCreatetime());
		response.setShouldloanamount(rsp.getShouldloanamount());
		response.setLastappealtime(rsp.getLastappealtime());
	}
	
	@Override
	public QueryUserRepaymentResponse queryUserRepayment(
			QueryUserRepaymentRequest request) {
		QueryUserRepaymentResponse response = new QueryUserRepaymentResponse();
		WheatfieldInterestRepaymentQueryuserrepaymentRequest req = new WheatfieldInterestRepaymentQueryuserrepaymentRequest();
		try {
			if (needCheckRequest) {
				request.check();
			}
			MyBeanUtil.copyBeanProperties(req, request);
			WheatfieldInterestRepaymentQueryuserrepaymentResponse rsp = RSInvokeConstant.ropClient.execute(req,RSInvokeConstant.sessionKey);
			log.info("调用queryUserRepayment方法(查询贷款的还款状态及历史)--请求参数QueryUserRepaymentRequest："+Tools.gsonToString(request)+",返回报文WheatfieldInterestRepaymentQueryuserrepaymentResponse: \n :"+Tools.gsonToString(rsp));
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
					rop2QueryUserRepaymentResponse(response,rsp);
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
			log.error("queryUserRepayment方法(查询贷款的还款状态及历史)调用异常,参数QueryUserRepaymentRequest:"+Tools.gsonToString(request),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("queryUserRepayment方法(查询贷款的还款状态及历史)调用异常,参数QueryUserRepaymentRequest:"+Tools.gsonToString(request),e);
		}
		return response;
	}
	/**
	 * 用户还款信息
	 * @param response
	 * @param rsp
	 */
	private void rop2QueryUserRepaymentResponse(QueryUserRepaymentResponse response,WheatfieldInterestRepaymentQueryuserrepaymentResponse rsp){
		List<TUserRepayment> tUserRepaymentList = new ArrayList<TUserRepayment>();
		if(rsp.getUserrepayments()!=null&&rsp.getUserrepayments().size()>0){
			for(UserRepayment item : rsp.getUserrepayments()){
				TUserRepayment entity = new TUserRepayment();
				MyBeanUtil.copyBeanProperties(entity, item);
				tUserRepaymentList.add(entity);
			}
		}
		response.settUserRepaymentList(tUserRepaymentList);
	}
}
