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
 * 通联账户交易-对账文件下载请求对象<br>
 * @author Jerry
 * @date 2017年12月28日 下午7:10:59
 */
public class TitanAgentDownloadDTO {
	
	/**
	 * 商家ID
	 */
	@NotBlank
	private String merchantNo;
	
	/**
	 * 商户号+唯一标识
	 */
	@NotBlank
	private String serialNo;
	
	/**
	 * 交易代码 {@link TradeCodeEnum}
	 */
	@NotBlank
	private String tradeCode;
	
	/**
	 * 交易状态条件, 0成功，1失败，2全部
	 */
	@NotBlank
	private String tradeStatus;
	
	/**
	 * 查询类型  0.按完成日期  1.按提交日期 
	 */
	@NotBlank
	private String queryType;
	
	/**
	 * 开始时间  yyyyMMddHHmmss
	 */
	@NotBlank
	private String startDate;
	
	/**
	 * 结束时间  YYYYMMDDHHmmss
	 */
	@NotBlank
	private String endDate;
	
	/**
	 * 是否包含手续费    0.不需手续费，1.包含手续费
	 */
	@NotBlank
	private String contFee;
	

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getContFee() {
		return contFee;
	}
	public void setContFee(String contFee) {
		this.contFee = contFee;
	}

}
