package com.titanjr.checkstand.dto;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by zhaoshan on 2017/11/15.
 */
public class GateWayPayDTO {

    //支付请求id，保存后才有
    private Long payreqid;
    //用户请求单号，落单时保存
    private String userOrderMo;
    //商户号，需要根据传来的商户号匹配
    @NotBlank
    private String merchantNo;
    //订单号，系统生成，返回给金融收银台
    @NotBlank
    private String orderNo;
    //产品号，相当于产品id
    private String productNo;
    //产品名称
    private String productName;
    //产品描述
    private String productDesc;
    //产品数量
    private Integer productNum;
    //订单金额，精确到分
    @NotNull
    private Long orderAmount;
    //支付方式
    private Integer payType;
    //币种
    @NotNull
    private Integer amtType;
    //银行标示
    private String bankInfo;
    //付款方账号
    private String payerAccount;
    //支付人姓名
    private String payerName;
    //支付人手机号
    private String payerPhone;
    //支付人邮箱
    private String payerMail;
    //页面地址
    @NotBlank
    private String pageUrl;
    //后台通知地址
    @NotBlank
    private String notifyUrl;
    //订单提交时间
    @NotBlank
    private String orderTime;
    //订单过期时间
    private String orderExpireTime;
    //订单标示
    private String orderMark;
    //扩展字段
    private String expand;
    //扩展字段
    private String expand2;
    //签名类型
    private Integer signType;
    //业务号
    private String busiCode;
    //版本
    private String version;
    //字符编码
    private String charset;
    //签名信息
    private String signMsg;
    //微信服务号专用openid
    private String openid;
    //身份证号
    private String idcode;
    //银行卡类型
    private String payerAccounttype;
    //安全码
    private String safetycode;
    //有效期
    private String validthru;
    //终端IP
    private String terminalip;
    //终端类型
    private String terminaltype;
    //终端信息
    private String terminalinfo;
    //支付状态
    private String reqstatus;
    //语言
    private String language;
    //合作伙伴商户号
    private String pid;
    //产品价格/单价
    private String productPrice;
    //业务扩展字段
    private String extTL;
    //贸易类型
    private String tradeNature;
    //海关扩展字段
    private String customsExt;
    //应用id，扫码等付款
    private String appid;
    //随机字符串
    private String randomstr;
    //支付限制，不用信用卡
    private String limit_pay;

    public Long getPayreqid() {
        return payreqid;
    }

    public void setPayreqid(Long payreqid) {
        this.payreqid = payreqid;
    }

    public String getUserOrderMo() {
        return userOrderMo;
    }

    public void setUserOrderMo(String userOrderMo) {
        this.userOrderMo = userOrderMo;
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

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode;
    }

    public String getPayerAccounttype() {
        return payerAccounttype;
    }

    public void setPayerAccounttype(String payerAccounttype) {
        this.payerAccounttype = payerAccounttype;
    }

    public String getSafetycode() {
        return safetycode;
    }

    public void setSafetycode(String safetycode) {
        this.safetycode = safetycode;
    }

    public String getValidthru() {
        return validthru;
    }

    public void setValidthru(String validthru) {
        this.validthru = validthru;
    }

    public String getTerminalip() {
        return terminalip;
    }

    public void setTerminalip(String terminalip) {
        this.terminalip = terminalip;
    }

    public String getTerminaltype() {
        return terminaltype;
    }

    public void setTerminaltype(String terminaltype) {
        this.terminaltype = terminaltype;
    }

    public String getTerminalinfo() {
        return terminalinfo;
    }

    public void setTerminalinfo(String terminalinfo) {
        this.terminalinfo = terminalinfo;
    }

    public String getReqstatus() {
        return reqstatus;
    }

    public void setReqstatus(String reqstatus) {
        this.reqstatus = reqstatus;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getExtTL() {
        return extTL;
    }

    public void setExtTL(String extTL) {
        this.extTL = extTL;
    }

    public String getTradeNature() {
        return tradeNature;
    }

    public void setTradeNature(String tradeNature) {
        this.tradeNature = tradeNature;
    }

    public String getCustomsExt() {
        return customsExt;
    }

    public void setCustomsExt(String customsExt) {
        this.customsExt = customsExt;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getRandomstr() {
        return randomstr;
    }

    public void setRandomstr(String randomstr) {
        this.randomstr = randomstr;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }
}
