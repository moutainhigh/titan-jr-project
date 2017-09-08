package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

public class RefundRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//业务订单号
	private String orderNo;
	
	//支付密码
	private String payPassword;
	
	//用户ID
	private String tfsUserid;
	
	//机构编码
	private String userId;
	
	//是否为商户编码，这个参数是对传入Merchcode的接入方设置的，默认为传入金融机构的id和机构编码
	private String isMerchCode;

	//是否原路退回，若原路退回，；
	private Integer isBackTrack = 0;
	
	//回调地址
	private String notifyUrl;
	
	//业务信息
	private String businessInfo;
	
	//该业务单是否冻结
	private boolean isFreeze = false;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isFreeze() {
		return isFreeze;
	}

	public void setFreeze(boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public String getTfsUserid() {
		return tfsUserid;
	}

	public void setTfsUserid(String tfsUserid) {
		this.tfsUserid = tfsUserid;
	}

	public String getIsMerchCode() {
		return isMerchCode;
	}

	public void setIsMerchCode(String isMerchCode) {
		this.isMerchCode = isMerchCode;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}

	public Integer getIsBackTrack() {
		return isBackTrack;
	}

	public void setIsBackTrack(Integer isBackTrack) {
		this.isBackTrack = isBackTrack;
	}
}
