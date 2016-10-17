package com.fangcang.titanjr.rs.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Rop.api.domain.Accountinfo;
import com.Rop.api.request.WheatfieldAccountUpdateRequest;
import com.Rop.api.request.WheatfieldAccountinfoDeleteRequest;
import com.Rop.api.request.WheatfieldAccountinfoQueryRequest;
import com.Rop.api.request.WheatfieldAccountinfoUpdateRequest;
import com.Rop.api.request.WheatfieldAccountnumCkeckRequest;
import com.Rop.api.request.WheatfieldBankaccountBindingRequest;
import com.Rop.api.response.WheatfieldAccountUpdateResponse;
import com.Rop.api.response.WheatfieldAccountinfoDeleteResponse;
import com.Rop.api.response.WheatfieldAccountinfoQueryResponse;
import com.Rop.api.response.WheatfieldAccountinfoUpdateResponse;
import com.Rop.api.response.WheatfieldAccountnumCkeckResponse;
import com.Rop.api.response.WheatfieldBankaccountBindingResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.rs.dto.BankCardInfo;
import com.fangcang.titanjr.rs.manager.RSBankCardInfoManager;
import com.fangcang.titanjr.rs.request.BankCardBindCheckRequest;
import com.fangcang.titanjr.rs.request.BankCardBindRequest;
import com.fangcang.titanjr.rs.request.BankCardQueryRequest;
import com.fangcang.titanjr.rs.request.DeletePersonCardRequest;
import com.fangcang.titanjr.rs.request.InvalidPubCardModifyRequest;
import com.fangcang.titanjr.rs.request.WithDrawCardModifyRequest;
import com.fangcang.titanjr.rs.response.BankCardBindCheckResponse;
import com.fangcang.titanjr.rs.response.BankCardBindResponse;
import com.fangcang.titanjr.rs.response.BankCardQueryResponse;
import com.fangcang.titanjr.rs.response.DeletePersonCardResponse;
import com.fangcang.titanjr.rs.response.InvalidPubCardModifyResponse;
import com.fangcang.titanjr.rs.response.WithDrawCardModifyResponse;
import com.fangcang.util.MyBeanUtil;

public class RSBankCardInfoManagerImpl implements RSBankCardInfoManager {
	private static final Log log = LogFactory.getLog(RSBankCardInfoManagerImpl.class);


	private static final boolean needCheckRequest = true;// 是否校验请求
	
	@Override
	public BankCardBindResponse bindBankCard(
			BankCardBindRequest bankCardBindRequest) {
		BankCardBindResponse response = new BankCardBindResponse();
		try {
			WheatfieldBankaccountBindingRequest req = new WheatfieldBankaccountBindingRequest();
			if (needCheckRequest) {
				bankCardBindRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, bankCardBindRequest);
			WheatfieldBankaccountBindingResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用bindBankCard返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口bindBankCard异常：" + errorMsg);
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
			log.error("调用bindBankCard过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public BankCardQueryResponse queryBindCard(BankCardQueryRequest bankCardQueryRequest) {
		BankCardQueryResponse response = new BankCardQueryResponse();
		try {
			WheatfieldAccountinfoQueryRequest req = new WheatfieldAccountinfoQueryRequest();
			if (needCheckRequest) {
				bankCardQueryRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, bankCardQueryRequest);
			WheatfieldAccountinfoQueryResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用queryBindCard返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口queryBindCard异常：" + errorMsg);
				} else {
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setBankCardInfoList(AccountInfoToBankCardInfo(rsp.getAccountinfos()));
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
			log.error("调用queryBindCard过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public BankCardBindCheckResponse checkBindCardBindStatus(
			BankCardBindCheckRequest bankCardBindCheckRequest) {
		BankCardBindCheckResponse response = new BankCardBindCheckResponse();
		try {
			WheatfieldAccountnumCkeckRequest req = new WheatfieldAccountnumCkeckRequest();
			if (needCheckRequest) {
				bankCardBindCheckRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, bankCardBindCheckRequest);
			WheatfieldAccountnumCkeckResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用checkBindCardBindStatus返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口checkBindCardBindStatus异常：" + errorMsg);
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
			log.error("调用checkBindCardBindStatus过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public WithDrawCardModifyResponse modityWithDrawCard(
			WithDrawCardModifyRequest withDrawCardModifyRequest) {
		WithDrawCardModifyResponse response = new WithDrawCardModifyResponse();
		try {
			WheatfieldAccountinfoUpdateRequest req = new WheatfieldAccountinfoUpdateRequest();
			if (needCheckRequest) {
				withDrawCardModifyRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, withDrawCardModifyRequest);
			WheatfieldAccountinfoUpdateResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.debug("调用modityWithDrawCard返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口modityWithDrawCard异常：" + errorMsg);
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
			log.error("调用modityWithDrawCard过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public InvalidPubCardModifyResponse modifyInvalidPublicCard(
			InvalidPubCardModifyRequest invalidPubCardModifyRequest) {
		InvalidPubCardModifyResponse response = new InvalidPubCardModifyResponse();
		try {
			WheatfieldAccountUpdateRequest req = new WheatfieldAccountUpdateRequest();
			if (needCheckRequest) {
				invalidPubCardModifyRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, invalidPubCardModifyRequest);
			WheatfieldAccountUpdateResponse rsp = RSInvokeConstant.ropClient.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用modifyInvalidPublicCard返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口modifyInvalidPublicCard异常：" + errorMsg);
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
			log.error("调用modifyInvalidPublicCard过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public DeletePersonCardResponse deletePersonCard(
			DeletePersonCardRequest deletePersonCardRequest) {
		DeletePersonCardResponse response = new DeletePersonCardResponse();
		try {
			WheatfieldAccountinfoDeleteRequest req = new WheatfieldAccountinfoDeleteRequest();
			if (needCheckRequest) {
				deletePersonCardRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, deletePersonCardRequest);
			WheatfieldAccountinfoDeleteResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用deletePersonCard返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口deletePersonCard异常：" + errorMsg);
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
			log.error("调用modifyInvalidPublicCard过程出现未知异常", e);
		}
		return response;
	}

	/**
	 * 将融数返回的银行账户信息转换成我们的银行卡信息
	 * @param accountinfos
	 * @return
	 * @author fangdaikang
	 */
	private List<BankCardInfo> AccountInfoToBankCardInfo(List<Accountinfo> accountinfos){
		List<BankCardInfo> bankCardInfos = new ArrayList<BankCardInfo>();
		if(accountinfos !=null && accountinfos.size()>0){
			for(int i=0;i<accountinfos.size();i++){
				BankCardInfo bankCardInfo = new BankCardInfo();
				Accountinfo  accountinfo = accountinfos.get(i);
				if(accountinfo !=null){
					bankCardInfo.setAccount_number(accountinfo.getAccount_number());
					bankCardInfo.setAccount_type_id(accountinfo.getAccount_type_id());
					bankCardInfo.setAccountid(accountinfo.getAccountid());
					bankCardInfo.setAccountname(accountinfo.getAccountname());
					bankCardInfo.setAccountproperty(accountinfo.getAccountproperty());
					bankCardInfo.setAccountpurpose(accountinfo.getAccountpurpose());
					bankCardInfo.setAccountrealname(accountinfo.getAccountrealname());
					
					bankCardInfo.setBankbranch(accountinfo.getBankbranch());
					bankCardInfo.setBankbranchname(accountinfo.getBankbranchname());
					bankCardInfo.setBankcity(accountinfo.getBankcity());
					bankCardInfo.setBankhead(accountinfo.getBankhead());
					bankCardInfo.setBankheadname(accountinfo.getBankheadname());
					bankCardInfo.setBankprovince(accountinfo.getBankprovince());
					
					
					bankCardInfo.setCertificatenumber(accountinfo.getCertificatenumber());
					bankCardInfo.setCertificatetype(accountinfo.getCertificatetype());
					bankCardInfo.setCreatedtime(accountinfo.getCreatedtime());
					bankCardInfo.setCurrency(accountinfo.getCurrency());
					
					bankCardInfo.setErrorcode(accountinfo.getErrorcode());
					bankCardInfo.setFinaccountid(accountinfo.getFinaccountid());
					bankCardInfo.setOpen_account_date(accountinfo.getOpen_account_date());
					bankCardInfo.setOpen_account_description(accountinfo.getOpen_account_description());
					bankCardInfo.setStatus(accountinfo.getStatus());
					bankCardInfo.setUpdatedtime(accountinfo.getUpdatedtime());
					
					bankCardInfos.add(bankCardInfo);
				}
			}
		}
		
		return bankCardInfos;
	}
	
}
