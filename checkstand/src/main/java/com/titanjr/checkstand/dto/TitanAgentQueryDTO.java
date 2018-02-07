/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanAgentQueryDTO.java
 * @author Jerry
 * @date 2017年12月27日 下午5:58:40  
 */
package com.titanjr.checkstand.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.AgentTradeCodeEnum;

/**
 * @author Jerry
 * @date 2017年12月27日 下午5:58:40  
 */
public class TitanAgentQueryDTO {
	
	/**
	 * 商家ID
	 */
	@NotBlank
	private String merchantNo;
	
	/**
	 * 订单号或/唯一流水号/批次号    通联：若不填时间必填 ；  融宝：必填
	 */
	private String orderNo;
	
	/**
	 * 交易代码 {@link AgentTradeCodeEnum}
	 */
	@NotBlank
	private String tradeCode;

	/**
	 * 交易状态条件, 0成功，1失败，2全部，3退票，4代付失败退款，5代付退票退款，6委托扣款，7提现
	 * 通联：必填；  融宝：不需要
	 */
	private String tradeStatus;

	/**
	 * 查询类型    0按完成日期，1按提交日期，默认为1       
	 * 通联：必填 ；  融宝：不需要
	 */
	private String queryType;
	
	/**
	 * 开始时间     
	 *  通联：若不填则orderNo则必填；  融宝：不需要
	 */
	private String startDate;
	
	/**
	 * 结束时间       
	 * 通联：填了开始时间必填；  融宝：不需要
	 */
	private String endDate;
	
	/**
	 * 查询时间   yyyy-MM-dd   
	 * 通联：不需要；  融宝：必填
	 */
	private String transDate;
	
	/**
	 * 序号     同一批次内不可重复，不能大于6个字符  
	 * 通联：不需要；   融宝：必填
	 */
	private String number;
	
	
	public ValidateResponse validateForTL(){
		ValidateResponse res = new ValidateResponse();
		res.putSuccess();
		if(!StringUtil.isValidString(this.tradeStatus)){
			res.putError("tradeStatus is null");
			return res;
		}
		if(!StringUtil.isValidString(this.queryType)){
			res.putError("queryType is null");
			return res;
		}
		if(!StringUtil.isValidString(this.orderNo) && !StringUtil.isValidString(this.startDate)){
			res.putError("queryType and startDate can not both null");
			return res;
		}
		if(StringUtil.isValidString(this.startDate) && !StringUtil.isValidString(this.endDate)){
			res.putError("startDate and endDate can not both null");
			return res;
		}
		return res;
	}
	public ValidateResponse validateForRB(){
		ValidateResponse res = new ValidateResponse();
		res.putSuccess();
		if(!StringUtil.isValidString(this.orderNo)){
			res.putError("orderNo is null");
			return res;
		}
		if(!StringUtil.isValidString(this.transDate)){
			res.putError("trans_time is null");
			return res;
		}
		if(!StringUtil.isValidString(this.number)){
			res.putError("number is null");
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
