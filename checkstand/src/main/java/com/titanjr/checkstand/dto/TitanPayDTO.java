package com.titanjr.checkstand.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 支付请求参数（融数格式），只需要将对支付路由有用的字段设置必填
 * @author Jerry
 * @date 2017年12月7日 下午2:39:02
 */
public class TitanPayDTO {
	
    /**
     * 商户号
     */
    @NotBlank
    private String merchantNo;
    /**
     * 业务订单号
     */
    @NotBlank
    private String orderNo;
    /**
     * 产品号，相当于产品id
     */
    private String productNo;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品描述
     */
    private String productDesc;
    /**
     * 产品数量
     */
    private Integer productNum;
    /**
     * 订单金额，精确到分
     */
    @NotNull
    private Long orderAmount;
    /**
     * 直连必填<br>
     * 支付方式11：个人银行 12：企业银行 13：信用卡 28：微信公众号支付  30：微信扫码支付Url 32: 支付宝扫码支付url 41：快捷支付
     */
    @NotNull
    private Integer payType;
    /**
     * 币种
     */
    @NotNull
    private Integer amtType;
    /**
     * 银行标示，快捷支付不能为空
     */
    private String bankInfo;
    /**
     * 付款方账号，快捷支付不能为空
     */
    private String payerAcount;
    /**
     * 支付人姓名，快捷支付不能为空
     */
    private String payerName;
    /**
     * 支付人手机号，快捷支付不能为空
     */
    private String payerPhone;
    /**
     * 支付人邮箱
     */
    private String payerMail;
    /**
     * 页面地址
     */
    @NotBlank
    private String pageUrl;
    /**
     * 后台通知地址
     */
    @NotBlank
    private String notifyUrl;
    /**
     * 订单提交时间   yyyyMMddHHmmss
     */
    @NotBlank
    private String orderTime;
    /**
     * 订单过期时间
     */
    private String orderExpireTime;
    /**
     * 订单标示
     */
    private String orderMark;
    /**
     * 扩展字段
     */
    private String expand;
    /**
     * 扩展字段
     */
    private String expand2;
    /**
     * 签名类型
     */
    @NotNull
    private Integer signType;
    /**
     * 业务号   商户订单：101 查询订单：102  商户退款：103 查询退款：104
     */
    @NotBlank
    private String busiCode;
    /**
     * 版本 固定值：v1.0 新版本：v1.1（含快捷支付）
     */
    @NotBlank
    private String version;
    /**
     * 字符编码  固定值：1，代表UTF-8
     */
    @NotBlank
    private String charset;
    /**
     * 签名信息
     */
    @NotBlank
    private String signMsg;
    /**
     * 微信服务号专用openid
     */
    private String openId;
    /**
     * 身份证号，快捷支付不能为空
     */
    private String idCode;
    /**
     * 银行卡类型，快捷支付不能为空  10：借记卡  21：信用卡
     */
    private String payerAccountType;
    /**
     * 安全码，信用卡支付不能为空
     */
    private String safetyCode;
    /**
     * 有效期，信用卡支付不能为空
     */
    private String validthru;
    /**
     * 终端IP，快捷支付不能为空
     */
    private String terminalIp;
    /**
     * 终端类型  web、wap、mobile，快捷支付不能为空  
     */
    private String terminalType;
    /**
     * 终端信息，快捷支付不能为空
     */
    private String terminalInfo;
    
	/**
	 * 快捷支付绑卡ID
	 */
	private String bindCardId;
    

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPayerAccountType() {
        return payerAccountType;
    }

    public void setPayerAccountType(String payerAccountType) {
        this.payerAccountType = payerAccountType;
    }

    public String getSafetyCode() {
        return safetyCode;
    }

    public void setSafetyCode(String safetyCode) {
        this.safetyCode = safetyCode;
    }

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTerminalInfo() {
        return terminalInfo;
    }

    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
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

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getAmtType() {
        return amtType;
    }

    public void setAmtType(Integer amtType) {
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

    public String getOrderExpireTime() {
        return orderExpireTime;
    }

    public void setOrderExpireTime(String orderExpireTime) {
        this.orderExpireTime = orderExpireTime;
    }

    public String getOrderMark() {
        return orderMark;
    }

    public void setOrderMark(String orderMark) {
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

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getValidthru() {
        return validthru;
    }

    public void setValidthru(String validthru) {
        this.validthru = validthru;
    }

	public String getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}
	
}
