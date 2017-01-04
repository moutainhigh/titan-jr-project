package com.fangcang.titanjr.dto.request;

import java.io.File;
import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;
/**
 * 发送信息(短信和邮件)
 * @author luoqinglong
 * @date   2016年12月31日
 */
public class SendMessageRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3712765554299486581L;
	private String receiveAddress;
	/**
	 * 邮件主题
	 */
	private String subject;
	private String content;
	//短信发送选用服务号
	private String providerkey;
	
	//商户号
	private String merchantCode;
	
	/**
	 * 邮件附件
	 */
	private List<File> fileList;
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public String getProviderkey() {
		return providerkey;
	}

	public void setProviderkey(String providerkey) {
		this.providerkey = providerkey;
	}

	public void setContent(String content) {
		this.content = content;
	}
 
	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
	
	
}
