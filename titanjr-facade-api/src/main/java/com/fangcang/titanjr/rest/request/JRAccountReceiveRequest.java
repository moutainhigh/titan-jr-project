/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName JRConfirmOrderRequest.java
 * @author Jerry
 * @date 2017年8月14日 上午11:23:26  
 */
package com.fangcang.titanjr.rest.request;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Jerry
 * @date 2017年8月14日 上午11:23:26  
 */
@ApiModel(value = "AccountReceiveRequest", description = "供应商收款请求")
public class JRAccountReceiveRequest {
	
	@ApiModelProperty(value = "第三方机构编码",required = true)
	@NotBlank
    private String partnerOrgCode;
	
	@ApiModelProperty(value = "支付单号",required = true)
	@NotBlank
    private String payOrderNo;
	
	@ApiModelProperty(value = "交易金额，单位分",required = true)
	@NotNull
    private Long tradeAmount;
	
	@ApiModelProperty(value = "操作方式 --- 1直接收款  2收款并冻结  3拒单并原路退回")
    private int operateType;

	@ApiModelProperty(value = "是否需要原路退回，默认不需要")
	private Integer isBackTrack = 0;
	
	@ApiModelProperty(value = "操作人")
    private String operator;

	
	public String getPartnerOrgCode() {
		return partnerOrgCode;
	}

	public void setPartnerOrgCode(String partnerOrgCode) {
		this.partnerOrgCode = partnerOrgCode;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public Long getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Long tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public Integer getIsBackTrack() {
		return isBackTrack;
	}

	public void setIsBackTrack(Integer isBackTrack) {
		this.isBackTrack = isBackTrack;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
