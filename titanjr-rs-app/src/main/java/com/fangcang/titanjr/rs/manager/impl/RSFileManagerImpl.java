package com.fangcang.titanjr.rs.manager.impl;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Rop.api.ApiException;
import com.Rop.api.request.FsFileurlGetRequest;
import com.Rop.api.request.FsUrlkeyGetRequest;
import com.Rop.api.response.FsFileurlGetResponse;
import com.Rop.api.response.FsUrlkeyGetResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.rs.manager.RSFileManager;
import com.fangcang.titanjr.rs.request.GetFileUrlRequest;
import com.fangcang.titanjr.rs.request.GetUrlKeyRequest;
import com.fangcang.titanjr.rs.response.GetFileUrlResponse;
import com.fangcang.titanjr.rs.response.GetUrlKeyResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;

public class RSFileManagerImpl implements RSFileManager {
	private static final Log log = LogFactory.getLog(RSFileManagerImpl.class);
	@Override
	public GetUrlKeyResponse getUrlKey(GetUrlKeyRequest getUrlKeyRequest) {
		GetUrlKeyResponse response =  new GetUrlKeyResponse();
		FsUrlkeyGetRequest  fsUrlkeyGetRequest = new FsUrlkeyGetRequest();
		fsUrlkeyGetRequest.setType(getUrlKeyRequest.getType());
		fsUrlkeyGetRequest.setInvoice_date(getUrlKeyRequest.getInvoiceDate());
		try {
			 
			FsUrlkeyGetResponse rsp = RSInvokeConstant.ropClient
					.execute(fsUrlkeyGetRequest, RSInvokeConstant.sessionKey);
			 if (rsp != null) {
	                log.info(getUrlKeyRequest.getType()+"-"+getUrlKeyRequest.getInvoiceDate()+"-调用getUrlKey返回报文FsUrlkeyGetResponse: \n" + ToStringBuilder.reflectionToString(rsp));
	                String errorMsg;
	                if (rsp.isSuccess() != true) {
	                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
	                        errorMsg = rsp.getSubMsg();
	                    } else {
	                        errorMsg = rsp.getMsg();
	                    }
	                    response.setReturnCode(rsp.getErrorCode());
	                    response.setReturnMsg(errorMsg);
	                    log.error("调用接口getUrlKey异常：" + errorMsg+",param getUrlKeyRequest:"+ToStringBuilder.reflectionToString(getUrlKeyRequest));
	                } else {
	                    response.setSuccess(true);
	                    response.setFileUrlList(rsp.getFileurls());
	                    response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
	                    response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
	                }
	            } else {
	                response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
	                response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
	            }
			
		} catch (ApiException e) {
			
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public GetFileUrlResponse getFileUrl(GetFileUrlRequest getFileUrlRequest) {
		GetFileUrlResponse  response = new GetFileUrlResponse();
		
		FsFileurlGetRequest fsFileurlGetRequest = new FsFileurlGetRequest();
		fsFileurlGetRequest.setUrl_key(getFileUrlRequest.getUrlKey());
		try {
			FsFileurlGetResponse  rsp =  RSInvokeConstant.ropClient
			.execute(fsFileurlGetRequest, RSInvokeConstant.sessionKey);
			
			 if (rsp != null) {
	                log.info(ToStringBuilder.reflectionToString(getFileUrlRequest)+"-调用getFileUrl返回报文: \n" + rsp.getBody());
	                String errorMsg;
	                if (rsp.isSuccess() != true) {
	                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
	                        errorMsg = rsp.getSubMsg();
	                    } else {
	                        errorMsg = rsp.getMsg();
	                    }
	                    response.setReturnCode(rsp.getErrorCode());
	                    response.setReturnMsg(errorMsg);
	                    log.error("调用接口getFileUrl异常：" + errorMsg+",param getFileUrl:"+ToStringBuilder.reflectionToString(getFileUrlRequest));
	                } else {
	                    response.setSuccess(true);
	                    response.setFile_url(rsp.getFile_url());
	                    response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
	                    response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
	                }
	            } else {
	                response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
	                response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
	            }
			
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return response;
	}

}
