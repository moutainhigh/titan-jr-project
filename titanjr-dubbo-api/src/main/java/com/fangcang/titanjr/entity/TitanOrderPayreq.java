package com.fangcang.titanjr.entity;

// default package


/**
 * TitanOrderPayreq entity. @author MyEclipse Persistence Tools
 */

public class TitanOrderPayreq implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -886082104465042242L;
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

	// Constructors

	public Long getStandfee() {
		return standfee;
	}

	public void setStandfee(Long standfee) {
		this.standfee = standfee;
	}

	/** default constructor */
	public TitanOrderPayreq() {
	}

	/** minimal constructor */
	public TitanOrderPayreq(String merchantNo, String orderNo) {
		this.merchantNo = merchantNo;
		this.orderNo = orderNo;
	}

	/** full constructor */
	public TitanOrderPayreq(Integer transorderid, String merchantNo,
			String orderNo, String productNo, String productName,
			String productDesc, Integer productNum, Double orderAmount,
			String payType, String amtType, String bankInfo,
			String payerAcount, String payerName, String payerPhone,
			String payerMail, String pageUrl, String notifyUrl,
			String orderTime, Integer orderExpireTime, Integer orderMark,
			String expand, String expand2, Integer signType, String busiCode,
			String version, Integer charset, String signMsg, Integer reqstatus,
			Float standardrate, Float executionrate, Integer ratetype,
			Long receivablefee, Long receivedfee) {
		this.transorderid = transorderid;
		this.merchantNo = merchantNo;
		this.orderNo = orderNo;
		this.productNo = productNo;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productNum = productNum;
		this.orderAmount = orderAmount;
		this.payType = payType;
		this.amtType = amtType;
		this.bankInfo = bankInfo;
		this.payerAcount = payerAcount;
		this.payerName = payerName;
		this.payerPhone = payerPhone;
		this.payerMail = payerMail;
		this.pageUrl = pageUrl;
		this.notifyUrl = notifyUrl;
		this.orderTime = orderTime;
		this.orderExpireTime = orderExpireTime;
		this.orderMark = orderMark;
		this.expand = expand;
		this.expand2 = expand2;
		this.signType = signType;
		this.busiCode = busiCode;
		this.version = version;
		this.charset = charset;
		this.signMsg = signMsg;
		this.reqstatus = reqstatus;
		this.standardrate = standardrate;
		this.executionrate = executionrate;
		this.ratetype = ratetype;
		this.receivablefee = receivablefee;
		this.receivedfee = receivedfee;
	}

	// Property accessors
	public Integer getOrderpayreqid() {
		return this.orderpayreqid;
	}

	public void setOrderpayreqid(Integer orderpayreqid) {
		this.orderpayreqid = orderpayreqid;
	}

	public Integer getTransorderid() {
		return this.transorderid;
	}

	public void setTransorderid(Integer transorderid) {
		this.transorderid = transorderid;
	}

	public String getMerchantNo() {
		return this.merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductNo() {
		return this.productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getProductNum() {
		return this.productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Double getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getAmtType() {
		return this.amtType;
	}

	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}

	public String getBankInfo() {
		return this.bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	public String getPayerAcount() {
		return this.payerAcount;
	}

	public void setPayerAcount(String payerAcount) {
		this.payerAcount = payerAcount;
	}

	public String getPayerName() {
		return this.payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerPhone() {
		return this.payerPhone;
	}

	public void setPayerPhone(String payerPhone) {
		this.payerPhone = payerPhone;
	}

	public String getPayerMail() {
		return this.payerMail;
	}

	public void setPayerMail(String payerMail) {
		this.payerMail = payerMail;
	}

	public String getPageUrl() {
		return this.pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getNotifyUrl() {
		return this.notifyUrl;
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
		return this.orderMark;
	}

	public void setOrderMark(Integer orderMark) {
		this.orderMark = orderMark;
	}

	public String getExpand() {
		return this.expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public String getExpand2() {
		return this.expand2;
	}

	public void setExpand2(String expand2) {
		this.expand2 = expand2;
	}

	public Integer getSignType() {
		return this.signType;
	}

	public void setSignType(Integer signType) {
		this.signType = signType;
	}

	public String getBusiCode() {
		return this.busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getCharset() {
		return this.charset;
	}

	public void setCharset(Integer charset) {
		this.charset = charset;
	}

	public String getSignMsg() {
		return this.signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public Integer getReqstatus() {
		return this.reqstatus;
	}

	public void setReqstatus(Integer reqstatus) {
		this.reqstatus = reqstatus;
	}

	public Float getStandardrate() {
		return this.standardrate;
	}

	public void setStandardrate(Float standardrate) {
		this.standardrate = standardrate;
	}

	public Float getExecutionrate() {
		return this.executionrate;
	}

	public void setExecutionrate(Float executionrate) {
		this.executionrate = executionrate;
	}

	public Integer getRatetype() {
		return this.ratetype;
	}

	public void setRatetype(Integer ratetype) {
		this.ratetype = ratetype;
	}

	public Long getReceivablefee() {
		return this.receivablefee;
	}

	public void setReceivablefee(Long receivablefee) {
		this.receivablefee = receivablefee;
	}

	public Long getReceivedfee() {
		return this.receivedfee;
	}

	public void setReceivedfee(Long receivedfee) {
		this.receivedfee = receivedfee;
	}

}
