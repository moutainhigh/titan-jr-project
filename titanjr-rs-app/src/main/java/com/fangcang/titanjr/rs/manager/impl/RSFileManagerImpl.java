package com.fangcang.titanjr.rs.manager.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Rop.api.ApiException;
import com.Rop.api.domain.FileUrl;
import com.Rop.api.request.FsFileUploadRequest;
import com.Rop.api.request.FsFileurlGetRequest;
import com.Rop.api.request.FsUrlkeyGetRequest;
import com.Rop.api.response.FsFileUploadResponse;
import com.Rop.api.response.FsFileurlGetResponse;
import com.Rop.api.response.FsUrlkeyGetResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.rs.dto.TFileUrl;
import com.fangcang.titanjr.rs.manager.RSFileManager;
import com.fangcang.titanjr.rs.request.GetFileUrlRequest;
import com.fangcang.titanjr.rs.request.GetUrlKeyRequest;
import com.fangcang.titanjr.rs.request.RSFsFileUploadRequest;
import com.fangcang.titanjr.rs.response.GetFileUrlResponse;
import com.fangcang.titanjr.rs.response.GetUrlKeyResponse;
import com.fangcang.titanjr.rs.response.RSFsFileUploadResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.util.MyBeanUtil;

public class RSFileManagerImpl implements RSFileManager {
	private static final Log log = LogFactory.getLog(RSFileManagerImpl.class);
	private static final boolean needCheckRequest = true;
	
	@Override
	public GetUrlKeyResponse getUrlKey(GetUrlKeyRequest getUrlKeyRequest) {
		GetUrlKeyResponse response =  new GetUrlKeyResponse();
		FsUrlkeyGetRequest  fsUrlkeyGetRequest = new FsUrlkeyGetRequest();
		try {
			if(needCheckRequest){
				fsUrlkeyGetRequest.check();
			}
			MyBeanUtil.copyBeanProperties(fsUrlkeyGetRequest, fsUrlkeyGetRequest);
			FsUrlkeyGetResponse rsp = RSInvokeConstant.ropClient
					.execute(fsUrlkeyGetRequest, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用getUrlKey方法(获取url_key)--请求参数GetUrlKeyRequest："+Tools.gsonToString(getUrlKeyRequest)+",返回报文: \n :"+rsp.getBody());
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
					rop2TFileUrlList(response,rsp);
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				log.info("调用getUrlKey方法(获取url_key)--请求参数GetUrlKeyRequest："+Tools.gsonToString(getUrlKeyRequest)+",返回FsUrlkeyGetResponse is null");
				
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
			
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("getUrlKey接口(获取url_key)调用异常,参数GetUrlKeyRequest:"+Tools.gsonToString(getUrlKeyRequest),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("getUrlKey接口(获取url_key)调用异常,参数GetUrlKeyRequest:"+Tools.gsonToString(getUrlKeyRequest),e);
		}
		
		return response;
	}
	
	private void rop2TFileUrlList(GetUrlKeyResponse response,FsUrlkeyGetResponse rsp){
		List<TFileUrl> tFileUrlList = new ArrayList<TFileUrl>();
		if(rsp.getFileurls()!=null&&rsp.getFileurls().size()>0){
			for(FileUrl item : rsp.getFileurls()){
				TFileUrl entity = new TFileUrl();
				MyBeanUtil.copyProperties(entity, item);
				tFileUrlList.add(entity);
			}
		}
		response.settFileUrlList(tFileUrlList);
	}
	

	@Override
	public GetFileUrlResponse getFileUrl(GetFileUrlRequest getFileUrlRequest) {
		GetFileUrlResponse  response = new GetFileUrlResponse();
		FsFileurlGetRequest fsFileurlGetRequest = new FsFileurlGetRequest();
		try {
			if(needCheckRequest){
				fsFileurlGetRequest.check();
			}
			fsFileurlGetRequest.setUrl_key(getFileUrlRequest.getUrlKey());
			FsFileurlGetResponse  rsp =  RSInvokeConstant.ropClient.execute(fsFileurlGetRequest, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用getFileUrl方法(获取文件下载路径)--请求参数GetFileUrlRequest："+Tools.gsonToString(getFileUrlRequest)+",返回报文: \n :"+rsp.getBody());
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
					response.setFile_url(rsp.getFile_url());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				log.info("调用getFileUrl方法(获取文件下载路径)--请求参数GetFileUrlRequest："+Tools.gsonToString(getFileUrlRequest)+",返回FsFileurlGetResponse is null");
				
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("getFileUrl接口(获取文件下载路径)调用异常,参数GetFileUrlRequest:"+Tools.gsonToString(getFileUrlRequest),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("getFileUrl接口(获取文件下载路径)调用异常,参数GetFileUrlRequest:"+Tools.gsonToString(getFileUrlRequest),e);
		}
		return response;
	}

	@Override
	public RSFsFileUploadResponse fsFileUpload(
			RSFsFileUploadRequest rsFsFileUploadRequest) {
		RSFsFileUploadResponse  response = new RSFsFileUploadResponse();
		FsFileUploadRequest fsFileUploadRequest = new FsFileUploadRequest();
		try {
			fsFileUploadRequest.check();
			fsFileUploadRequest.setPath(rsFsFileUploadRequest.getPath());
			fsFileUploadRequest.setType(rsFsFileUploadRequest.getType());
			fsFileUploadRequest.setInvoice_date(rsFsFileUploadRequest.getInvoiceDate());
			fsFileUploadRequest.setBatch(rsFsFileUploadRequest.getBacth());
			
			FsFileUploadResponse  rsp =  RSInvokeConstant.ropClient.execute(fsFileUploadRequest, RSInvokeConstant.sessionKey);
			if (rsp != null) {
				log.info("调用fsFileUpload方法(上传私密文件)--请求参数RSFsFileUploadRequest："+Tools.gsonToString(rsFsFileUploadRequest)+",返回报文: \n :"+rsp.getBody());
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
					response.setUrlKey(rsp.getUrl_key());
					response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
					response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
				}
			} else {
				log.info("调用fsFileUpload方法(上传私密文件)--请求参数RSFsFileUploadRequest："+Tools.gsonToString(rsFsFileUploadRequest)+",返回FsFileUploadResponse is null");
				response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		} catch (ApiException e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg("第三方接口调用异常");
			log.error("fsFileUpload方法(上传私密文件)调用异常,参数RSFsFileUploadRequest:"+Tools.gsonToString(rsFsFileUploadRequest),e);
		} catch (Exception e) {
			response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
			response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
			log.error("fsFileUpload方法(上传私密文件)调用异常,参数RSFsFileUploadRequest:"+Tools.gsonToString(rsFsFileUploadRequest),e);
		}
		return response;
	}
	
	

}
