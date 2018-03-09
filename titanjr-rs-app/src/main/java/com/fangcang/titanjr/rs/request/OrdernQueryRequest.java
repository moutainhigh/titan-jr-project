package com.fangcang.titanjr.rs.request;

import java.math.BigInteger;
import java.util.Date;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

public class OrdernQueryRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderno;
	
	private String merchantcode;

	private String funccode;

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getFunccode() {
		return funccode;
	}

	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}

	@Override
	public void check() throws RSValidateException {
		RequestValidationUtil.checkNotEmpty(this.getOrderno(), "orderno");
		RequestValidationUtil.checkNotEmpty(this.getMerchantcode(), "merchantcode");		
	}
	
}
