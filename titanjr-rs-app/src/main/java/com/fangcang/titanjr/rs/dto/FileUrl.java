package com.fangcang.titanjr.rs.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件结构
 * @author luoqinglong
 * @2016年8月11日
 */
public class FileUrl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8986003622447077542L;
	/**
	 * 文件key
	 */
	private String urlKey;
	private int type;
	/**
	 * 帐期
	 */
	private Date invoiceDate;
	/**
	 * 批次
	 */
	private String batch;
	private String sourceFile;
	/**
	 * 上传时间
	 */
	private Date uploadTime;
	
	public String getUrlKey() {
		return urlKey;
	}
	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}
