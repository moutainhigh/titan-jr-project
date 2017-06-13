package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import org.hibernate.validator.constraints.NotBlank;

public class NotifyRefundRequest extends BaseRequestDTO{
	
	//机构号
	@NotBlank
	private String merchantNo;
	
	//业务号
	@NotBlank
	private String busiCode;
	
	//原订单号
	@NotBlank
	private String orderNo;
	
	//退款金额
	@NotBlank
	private String refundAmount;
	
	//下单时间
	@NotBlank
	private String orderTime;
	
	//退款单号
	@NotBlank
	private String refundOrderno;
	
	//版本号
	@NotBlank
	private String version;
	
	//签名类型
	@NotBlank
	private String signType;
	
	//签名字符串
	@NotBlank
	private String signMsg;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getRefundOrderno() {
		return refundOrderno;
	}

	public void setRefundOrderno(String refundOrderno) {
		this.refundOrderno = refundOrderno;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
}
