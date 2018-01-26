package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TitanOrderPayDTO implements Serializable {

    /** 
	 * 
	 */
	private static final long serialVersionUID = -3100935006039877244L;
	private Integer orderpayreqid;
    private Integer transorderid;
    private String merchantNo;
    private String orderNo;
    private String productNo;
    private String productName;
    private String productDesc;
    private Integer productNum;
    private Double orderAmount;
    private String payType;
    private String amtType;
    private String bankInfo;
    private String payerAcount;
    private String payerName;
    private String payerPhone;
    private String payerMail;
    private String pageUrl;
    private String notifyUrl;
    private String orderTime;
    private Date orderDate;
    private Integer orderExpireTime;
    private Integer orderMark;
    private String expand;
    private String expand2;
    private Integer signType;
    private String busiCode;
    private String version;
    private Integer charset;
    private String signMsg;
    private Integer reqstatus;
    private Float standardrate;
    private Float executionrate;
    private Integer ratetype;
    private Long receivablefee;
    private Long receivedfee;
    private Long standfee;
    
    public Long getReceivablefee() {
		return receivablefee;
	}

	public void setReceivablefee(Long receivablefee) {
		this.receivablefee = receivablefee;
	}

	public Long getReceivedfee() {
		return receivedfee;
	}

	public void setReceivedfee(Long receivedfee) {
		this.receivedfee = receivedfee;
	}

	public Long getStandfee() {
		return standfee;
	}

	public void setStandfee(Long standfee) {
		this.standfee = standfee;
	}

	public Integer getOrderpayreqid() {
        return orderpayreqid;
    }

    public void setOrderpayreqid(Integer orderpayreqid) {
        this.orderpayreqid = orderpayreqid;
    }

    public Integer getTransorderid() {
        return transorderid;
    }

    public void setTransorderid(Integer transorderid) {
        this.transorderid = transorderid;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAmtType() {
        return amtType;
    }

    public void setAmtType(String amtType) {
        this.amtType = amtType;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getPayerAcount() {
        return payerAcount;
    }

    public void setPayerAcount(String payerAcount) {
        this.payerAcount = payerAcount;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public String getPayerMail() {
        return payerMail;
    }

    public void setPayerMail(String payerMail) {
        this.payerMail = payerMail;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderExpireTime() {
        return orderExpireTime;
    }

    public void setOrderExpireTime(Integer orderExpireTime) {
        this.orderExpireTime = orderExpireTime;
    }

    public Integer getOrderMark() {
        return orderMark;
    }

    public void setOrderMark(Integer orderMark) {
        this.orderMark = orderMark;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getExpand2() {
        return expand2;
    }

    public void setExpand2(String expand2) {
        this.expand2 = expand2;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getCharset() {
        return charset;
    }

    public void setCharset(Integer charset) {
        this.charset = charset;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public Integer getReqstatus() {
        return reqstatus;
    }

    public void setReqstatus(Integer reqstatus) {
        this.reqstatus = reqstatus;
    }

    public Float getStandardrate() {
        return standardrate;
    }

    public void setStandardrate(Float standardrate) {
        this.standardrate = standardrate;
    }

    public Float getExecutionrate() {
        return executionrate;
    }

    public void setExecutionrate(Float executionrate) {
        this.executionrate = executionrate;
    }

    public Integer getRatetype() {
        return ratetype;
    }

    public void setRatetype(Integer ratetype) {
        this.ratetype = ratetype;
    }

	public Date getOrderDate() {
        return com.fangcang.util.DateUtil.stringToDate(this.orderTime, "yyyyMMddHHmmss");
    }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
