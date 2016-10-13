package com.fangcang.titanjr.rs.request;

import java.util.Date;

import com.fangcang.titanjr.common.exception.RSValidateException;

public class GetUrlKeyRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1216527808421211524L;
	/**
	 * 
	 */
	private int type;
	/***
	 * 帐期
	 */
	private Date invoiceDate;
	
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


	@Override
	public void check() throws RSValidateException {
		
		
	}
	
}
