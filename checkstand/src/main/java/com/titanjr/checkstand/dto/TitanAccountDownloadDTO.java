/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanAgentPayDTO.java
 * @author Jerry
 * @date 2017年12月25日 上午11:49:04  
 */
package com.titanjr.checkstand.dto;

import org.hibernate.validator.constraints.NotBlank;
import com.titanjr.checkstand.constants.TradeCodeEnum;

/**
 * 账户交易-对账文件下载请求对象<br>
 * 通联，融宝合用，有注解的是都必填的，请注意有特别说明的字段
 * @author Jerry
 * @date 2017年12月28日 下午7:10:59
 */
public class TitanAccountDownloadDTO {
	
	/**
	 * 商家ID
	 */
	@NotBlank
	private String merchantNo;
	
	/**
	 * 交易代码 {@link TradeCodeEnum}
	 */
	@NotBlank
	private String tradeCode;
	
	/**
	 * 交易日期（按某个日期下载）    yyyyMMdd
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
