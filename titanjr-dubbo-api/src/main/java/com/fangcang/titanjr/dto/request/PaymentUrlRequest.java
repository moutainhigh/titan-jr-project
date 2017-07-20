package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

import org.hibernate.validator.constraints.NotEmpty;

public class PaymentUrlRequest extends BaseRequestDTO {

    /** 
	 * 
	 */
	private static final long serialVersionUID = -3163998348942517998L;

	private String merchantcode;

    @NotEmpty
    private String payOrderNo;

    private String userid;

    private String fcUserid;

    //来源
    @NotEmpty
    private String paySource;

    //接收签名
    private String sign;

    //操作者
    private String operater;
    
    //后台回调地址
    private String notifyUrl;

    //接受账户
    private String recieveMerchantCode;
    
	//订单号
	private String businessOrderCode;

    //是否冻结
    @NotEmpty
    private String isEscrowed;

    //解冻日期
    private String escrowedDate;
    
    //收银台版本 @see CashierDeskVersionEnum
    @NotEmpty
    private String cashierDeskVersion;


    public String getFcUserid() {
        return fcUserid;
    }

    public void setFcUserid(String fcUserid) {
        this.fcUserid = fcUserid;
    }

    public String getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPaySource() {
        return paySource;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getRecieveMerchantCode() {
        return recieveMerchantCode;
    }

    public void setRecieveMerchantCode(String recieveMerchantCode) {
        this.recieveMerchantCode = recieveMerchantCode;
    }

    public String getIsEscrowed() {
        return isEscrowed;
    }

    public void setIsEscrowed(String isEscrowed) {
        this.isEscrowed = isEscrowed;
    }

    public String getEscrowedDate() {
        return escrowedDate;
    }

    public void setEscrowedDate(String escrowedDate) {
        this.escrowedDate = escrowedDate;
    }

	public String getBusinessOrderCode() {
		return businessOrderCode;
	}

	public void setBusinessOrderCode(String businessOrderCode) {
		this.businessOrderCode = businessOrderCode;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getCashierDeskVersion() {
		return cashierDeskVersion;
	}

	public void setCashierDeskVersion(String cashierDeskVersion) {
		this.cashierDeskVersion = cashierDeskVersion;
	}
	
}
