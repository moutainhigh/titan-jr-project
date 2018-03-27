/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanGatewayDownloadDTO.java
 * @author Jerry
 * @date 2018年3月15日 下午6:23:47  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.TradeCodeEnum;

/**
 * @author Jerry
 * @date 2018年3月15日 下午6:23:47  
 */
public class TitanQrcodeDownloadDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -20721888819956302L;
	
	/**
	 * 商户号
	 */
	@NotBlank
	private String merchantNo;
	
	/**
	 * 交易代码 {@link TradeCodeEnum}
	 */
	@NotBlank
	private String tradeCode;

	/**
	 * 格式为 yyyyMMdd，交易日期
	 */
	@NotBlank
	private String tradeDate;
	

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

}
