/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanAgentPayDTO.java
 * @author Jerry
 * @date 2017年12月25日 上午11:49:04  
 */
package com.titanjr.checkstand.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.TradeCodeEnum;

/**
 * 账户交易-对账文件下载请求对象<br>
 * 通联，融宝合用，有注解的是都必填的，请注意有特别说明的字段
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
	 * 通联必填 ；   融宝不需要
	 */
	private String serialNo;
	
	/**
	 * 交易代码 {@link TradeCodeEnum}
	 */
	@NotBlank
	private String tradeCode;
	
	/**
	 * 交易状态条件, 0成功，1失败，2全部
	 * 通联必填 ；   融宝不需要
	 */
	private String tradeStatus;
	
	/**
	 * 查询类型  0.按完成日期1.按提交日期
	 * 通联必填  建议用1；   融宝不需要      
	 */
	private String queryType;
	
	/**
	 * 开始时间  yyyyMMddHHmmss
	 * 通联必填 ；   融宝不需要
	 */
	private String startDate;
	
	/**
	 * 结束时间  YYYYMMDDHHmmss
	 * 通联必填 ；   融宝不需要
	 */
	private String endDate;
	
	/**
	 * 交易日期（按某个日期下载）    yyyy-MM-dd
	 * 通联不需要 ；   融宝必填
	 */
	private String tradeDate;
	
	
	public ValidateResponse validateForTL(){
		ValidateResponse res = new ValidateResponse();
		res.putSuccess();
		if(!StringUtil.isValidString(this.serialNo)){
			res.putError("serialNo is null");
			return res;
		}
		if(!StringUtil.isValidString(this.tradeStatus)){
			res.putError("tradeStatus is null");
			return res;
		}
		if(!StringUtil.isValidString(this.queryType)){
			res.putError("queryType is null");
			return res;
		}
		if(!StringUtil.isValidString(this.queryType)){
			res.putError("queryType is null");
			return res;
		}
		if(!StringUtil.isValidString(this.startDate)){
			res.putError("startDate is null");
			return res;
		}
		if(!StringUtil.isValidString(this.endDate)){
			res.putError("endDate is null");
			return res;
		}
		return res;
	}
	public ValidateResponse validateForRB(){
		ValidateResponse res = new ValidateResponse();
		res.putSuccess();
		if(!StringUtil.isValidString(this.tradeDate)){
			res.putError("tradeDate is null");
			return res;
		}
		return res;
	}
	

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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

}
