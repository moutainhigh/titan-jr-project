package com.fangcang.titanjr.rs.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * 获取url_key
 * @author luoqinglong
 * @2016年11月10日
 */
public class GetUrlKeyRequest implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1216527808421211524L;
	/**
	 * 文件类型:58. 账户给天下房仓的对账文件(在线支付),59. 账户给天下房仓的对账文件(在线支付以外) ,60. 账户给天下房仓的对账文件(代收付) 
	 */
	@NotNull
	private int type;
	/***
	 * 帐期:格式 yyyy-MM-dd
	 */
	@NotNull
	private Date invoice_date;
	/***
	 * 批次
	 */
	private String batch;
	
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

	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
	}
	
}
