package com.fangcang.titanjr.rs.manager.impl;


import java.util.List;

import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.Rop.api.request.WheatfieldAccountCloseRequest;
import com.Rop.api.request.WheatfieldAccountFreezeRequest;
import com.Rop.api.request.WheatfieldAccountRmfreezeRequest;
import com.Rop.api.request.WheatfieldFinanaceEntrylistQueryRequest;
import com.Rop.api.response.WheatfieldAccountCloseResponse;
import com.Rop.api.response.WheatfieldAccountFreezeResponse;
import com.Rop.api.response.WheatfieldAccountRmfreezeResponse;
import com.Rop.api.response.WheatfieldFinanaceEntrylistQueryResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.rs.dto.AccountFlow;
import com.fangcang.titanjr.rs.manager.RSAccountManager;
import com.fangcang.titanjr.rs.request.AccountFlowQueryRequest;
import com.fangcang.titanjr.rs.request.AccountFreezeRequest;
import com.fangcang.titanjr.rs.request.AccountUnFreezeRequest;
import com.fangcang.titanjr.rs.request.DestoryAccountRequest;
import com.fangcang.titanjr.rs.response.AccountFlowQueryResponse;
import com.fangcang.titanjr.rs.response.AccountFlowResponse;
import com.fangcang.titanjr.rs.response.AccountFreezeResponse;
import com.fangcang.titanjr.rs.response.AccountUnFreezeResponse;
import com.fangcang.titanjr.rs.response.DestoryAccountResponse;
import com.fangcang.titanjr.rs.util.MyConvertXmlToObject;
import com.fangcang.util.MyBeanUtil;

public class RSAccountManagerImpl implements RSAccountManager {
	private static final Log log = LogFactory
			.getLog(RSAccountManagerImpl.class);


	private static final boolean needCheckRequest = true;// 是否校验请求
	@Override
	public AccountFreezeResponse freezeAccount(
			AccountFreezeRequest accountFreezeRequest) {
		AccountFreezeResponse response = new AccountFreezeResponse();
		try {
			WheatfieldAccountFreezeRequest req = new WheatfieldAccountFreezeRequest();
			if (needCheckRequest) {
				accountFreezeRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, accountFreezeRequest);
			WheatfieldAccountFreezeResponse rsp = RSInvokeConstant.ropClient.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.debug("调用freezeAccount返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口getRSProvinceInfo异常：" + errorMsg);
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
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用freezeAccount过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public AccountUnFreezeResponse unFreezeAccount(
			AccountUnFreezeRequest accountUnFreezeRequest) {
		AccountUnFreezeResponse response = new AccountUnFreezeResponse();
		try {
			WheatfieldAccountRmfreezeRequest req = new WheatfieldAccountRmfreezeRequest();
			if (needCheckRequest) {
				accountUnFreezeRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, accountUnFreezeRequest);
			WheatfieldAccountRmfreezeResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.debug("调用unFreezeAccount返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口unFreezeAccount异常：" + errorMsg);
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
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用unFreezeAccount过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public DestoryAccountResponse destoryAccount(
			DestoryAccountRequest destoryAccountRequest) {
		DestoryAccountResponse response = new DestoryAccountResponse();
		try {
			WheatfieldAccountCloseRequest req = new WheatfieldAccountCloseRequest();
			if (needCheckRequest) {
				destoryAccountRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, destoryAccountRequest);
			WheatfieldAccountCloseResponse rsp = RSInvokeConstant.ropClient.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.debug("调用destoryAccount返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口destoryAccount异常：" + errorMsg);
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
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用destoryAccount过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public AccountFlowQueryResponse queryAccountFlow(
			AccountFlowQueryRequest accountFlowQueryRequest) {
		AccountFlowQueryResponse response = new AccountFlowQueryResponse();
		try {
			WheatfieldFinanaceEntrylistQueryRequest req = new WheatfieldFinanaceEntrylistQueryRequest();
			if (needCheckRequest) {
				accountFlowQueryRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, accountFlowQueryRequest);
			WheatfieldFinanaceEntrylistQueryResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.debug("调用queryAccountFlow返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口queryAccountFlow异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
				    Object obj = MyConvertXmlToObject.convertXml2Object(rsp.getBody());
					AccountFlowResponse accountFlowResponse = (AccountFlowResponse)obj;
					if(accountFlowResponse !=null && accountFlowResponse.getAccountFlowEntry() !=null){
						List<AccountFlow> accountFlowList = accountFlowResponse.getAccountFlowEntry().getAccountFlow();
						response.setAccountFlow(accountFlowList);
					}
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用queryAccountFlow过程出现未知异常", e);
		}
		return response;
	}

	
}
