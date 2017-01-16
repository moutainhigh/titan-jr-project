package com.fangcang.titanjr.rs.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件存储信息
 * @author luoqinglong
 * @2016年8月11日
 */
public class TFileUrl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8986003622447077542L;
	/**
	 * 文件key
	 */
	private String url_key;
	/**
	 * 文件类型
	 */
	private int type;
	/**
	 * 帐期
	 */
	private Date invoice_date;
	/**
	 * 批次
	 */
	private String batch;
	/**
	 * 	源文件名
	 */
	private String sourcefile;
	/**
	 * 上传时间
	 */
	private Date uploadtime;
	
	
	public String getUrl_key() {
		return url_key;
	}
	public void setUrl_key(String url_key) {
		this.url_key = url_key;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getSourcefile() {
		return sourcefile;
	}
	public void setSourcefile(String sourcefile) {
		this.sourcefile = sourcefile;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	
}
