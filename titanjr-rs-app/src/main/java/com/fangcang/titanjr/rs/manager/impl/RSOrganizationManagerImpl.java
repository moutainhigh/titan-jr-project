package com.fangcang.titanjr.rs.manager.impl;

import com.Rop.api.request.WheatfieldAccountCheckRequest;
import com.Rop.api.request.WheatfieldBatchqueryCompanyRequest;
import com.Rop.api.request.WheatfieldBatchqueryPersonRequest;
import com.Rop.api.request.WheatfieldEnterpriseEntityaccountoptRequest;
import com.Rop.api.request.WheatfieldEnterpriseUpdatecompanyinfoRequest;
import com.Rop.api.request.WheatfieldPersonAccountoprRequest;
import com.Rop.api.response.WheatfieldAccountCheckResponse;
import com.Rop.api.response.WheatfieldBatchqueryCompanyResponse;
import com.Rop.api.response.WheatfieldBatchqueryPersonResponse;
import com.Rop.api.response.WheatfieldEnterpriseEntityaccountoptResponse;
import com.Rop.api.response.WheatfieldEnterpriseUpdatecompanyinfoResponse;
import com.Rop.api.response.WheatfieldPersonAccountoprResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.rs.request.*;
import com.fangcang.titanjr.rs.response.*;
import com.fangcang.titanjr.rs.manager.RSOrganizationManager;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.util.MyBeanUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public class RSOrganizationManagerImpl implements RSOrganizationManager {

	private static final Log log = LogFactory
			.getLog(RSOrganizationManagerImpl.class);

	private static final boolean needCheckRequest = true;// 是否校验请求

	@Override
	public CompanyOrgRegResponse resigterCompanyOrg(
			CompanyOrgRegRequest companyOrgRegRequest) {
		CompanyOrgRegResponse response = new CompanyOrgRegResponse();
		try {
			WheatfieldEnterpriseEntityaccountoptRequest req = new WheatfieldEnterpriseEntityaccountoptRequest();
			if (needCheckRequest) {
				companyOrgRegRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, companyOrgRegRequest);
			WheatfieldEnterpriseEntityaccountoptResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用resigterCompanyOrg返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口异常：" + errorMsg);
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
		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用resigterCompanyOrg参数校验异常", re);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用resigterCompanyOrg过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public CompanyOrgUpdateResponse updateCompanyOrg(
			CompanyOrgUpdateRequest companyOrgUpdateRequest) {
		CompanyOrgUpdateResponse response = new CompanyOrgUpdateResponse();
		try {
			WheatfieldEnterpriseUpdatecompanyinfoRequest req = new WheatfieldEnterpriseUpdatecompanyinfoRequest();
			if (needCheckRequest) {
				companyOrgUpdateRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, companyOrgUpdateRequest);
			WheatfieldEnterpriseUpdatecompanyinfoResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用updateCompanyOrg返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口updateCompanyOrg异常：" + errorMsg);
				} else {
					log.info("调用接口updateCompanyOrg更新机构成功");
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用updateCompanyOrg参数校验异常", re);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用resigterCompanyOrg过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public PersonOrgRegResponse resigterPersonOrg(
			PersonOrgRegRequest personOrgRegRequest) {
		PersonOrgRegResponse response = new PersonOrgRegResponse();
		try {
			WheatfieldPersonAccountoprRequest req = new WheatfieldPersonAccountoprRequest();
			if (needCheckRequest) {
				personOrgRegRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, personOrgRegRequest);
			log.info("开始调用个人账户注册接口,请求参数personOrgRegRequest："+Tools.gsonToString(personOrgRegRequest));
			WheatfieldPersonAccountoprResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用resigterPersonOrg返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口resigterPersonOrg异常：" + errorMsg);
				} else {
					log.info("调用接口resigterPersonOrg注册个人账户成功");
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用resigterPersonOrg参数校验异常", re);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用resigterPersonOrg过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public PersonOrgUpdateResponse updatePersonOrg(
			PersonOrgUpdateRequest personOrgUpdateRequest) {
		PersonOrgUpdateResponse response = new PersonOrgUpdateResponse();
		try {
			WheatfieldPersonAccountoprRequest req = new WheatfieldPersonAccountoprRequest();
			if (needCheckRequest) {
				personOrgUpdateRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, personOrgUpdateRequest);
			log.info("开始调用个人账户更新接口:");
			WheatfieldPersonAccountoprResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用updateCompanyOrg返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口updateCompanyOrg异常：" + errorMsg);
				} else {
					log.info("调用接口updateCompanyOrg更新个人账户成功");
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用updateCompanyOrg参数校验异常", re);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用updateCompanyOrg过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public OrgStatusQueryResponse queryOrgStatus(
			OrgStatusQueryRequest orgStatusQueryRequest) {
		OrgStatusQueryResponse response = new OrgStatusQueryResponse();
		try {
			WheatfieldAccountCheckRequest req = new WheatfieldAccountCheckRequest();
			if (needCheckRequest) {
				orgStatusQueryRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, orgStatusQueryRequest);
			WheatfieldAccountCheckResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			log.info("开始调用更新账户信息接口:");
			if (rsp != null) {
				log.info("调用queryOrgStatus返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口queryOrgStatus异常：" + errorMsg);
				} else {
					log.info("调用接口queryOrgStatus查询用户状态成功");
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}

		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用updateCompanyOrg参数校验异常", re);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用updateCompanyOrg过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public CompOrgInfoQueryResponse queryCompOrgInfo(
			CompOrgInfoQueryRequest compOrgInfoQueryRequest) {
		CompOrgInfoQueryResponse response = new CompOrgInfoQueryResponse();
		try {
			WheatfieldBatchqueryCompanyRequest req = new WheatfieldBatchqueryCompanyRequest();
			if (needCheckRequest) {
				compOrgInfoQueryRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, compOrgInfoQueryRequest);
			WheatfieldBatchqueryCompanyResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			log.info("开始调用查询机构信息接口:");
			if (rsp != null) {
				log.info("调用queryCompOrgInfo返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口queryCompOrgInfo异常：" + errorMsg);
				} else {
					log.info("调用接口queryCompOrgInfo查询机构信息成功");
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setCompanyOrgList(rsp.getOpenaccountcompanys());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}

		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用queryCompOrgInfo参数校验异常", re);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用queryCompOrgInfo过程出现未知异常", e);
		}
		return response;
	}

	@Override
	public PersOrgInfoQueryResponse queryPersOrgInfo(
			PersOrgInfoQueryRequest persOrgInfoQueryRequest) {
		PersOrgInfoQueryResponse response = new PersOrgInfoQueryResponse();
		try {
			WheatfieldBatchqueryPersonRequest req = new WheatfieldBatchqueryPersonRequest();
			if (needCheckRequest) {
				persOrgInfoQueryRequest.check();
			}
			MyBeanUtil.copyBeanProperties(req, persOrgInfoQueryRequest);
			WheatfieldBatchqueryPersonResponse rsp = RSInvokeConstant.ropClient
					.execute(req, RSInvokeConstant.sessionKey);
			log.info("开始调用查询个人信息接口:");
			if (rsp != null) {
				log.info("调用queryPersOrgInfo返回报文: \n" + rsp.getBody());
				String errorMsg;
				if (rsp.isSuccess() != true) {
					if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
						errorMsg = rsp.getSubMsg();
					} else {
						errorMsg = rsp.getMsg();
					}
					response.setReturnCode(rsp.getErrorCode());
					response.setReturnMsg(errorMsg);
					log.error("调用接口queryPersOrgInfo异常：" + errorMsg);
				} else {
					log.info("调用接口queryPersOrgInfo查询个人信息成功");
					response.setSuccess(true);
					response.setOperateStatus(rsp.getIs_success());
					response.setPersonOrgList(rsp.getOpenaccountpersons());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}

		} catch (RSValidateException re) {
			response.setReturnCode(re.getErrorCode());
			response.setReturnMsg(re.getErrorMsg());
			log.error("调用queryPersOrgInfo参数校验异常", re);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(e.getMessage());
			log.error("调用queryPersOrgInfo过程出现未知异常", e);
		}
		return response;
	}

}
