package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.enums.*;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/5/17.
 */
public class RechargePageRequest extends BaseRequestDTO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 商家编号,此处的merchantcode为传到财务系统的商家编号，不属于融数的商家编号
    private String merchantcode;

    // 单号
    private String orderNo;

    //系统单号，落单时由融数生成
    private String orderid;

    //付款方
    private String userid;

    //收款方姓名
    private String receiverName;

    //接收方
    private String userrelateid;

    //操作类型,对订单是落单，修改，取消，查询
    private OperTypeEnum operType = OperTypeEnum.Add_Order;

    //数量
    private Integer number;

    //调整类容
    private String adjustcontent;

    //调整时间
    private String adjusttype;

    //业务类型
    private OrderTypeEnum ordertype = OrderTypeEnum.OrderType_1;

    //第三方关联用户
    private String userorderid;

    //单价
    private String unitprice;

    //客户识别号
    private String payerAcount;

    //支付人姓名
    private String payerName;

    //支付人手机号
    private String payerPhone;

    //支付人邮箱
    private String payerMail;

    //页面返回地址
    private String pageUrl;

    //结果通讯地址
    private String notifyUrl;

    //银行标识
    private String bankInfo;

    //过期时长
    private Integer orderExpireTime;

    //订单标志
    private OrderMarkEnum orderMark =OrderMarkEnum.InsideOrder;

    //扩展字段
    private String expand;

    //扩展字段2
    private String expand2;
    
    
    //签名类型
    private SignTypeEnum signType = SignTypeEnum.MD5;

    //业务号
    private BusiCodeEnum busiCode = BusiCodeEnum.MerchantOrder;

    //版本
    private VersionEnum version = VersionEnum.Version_1;

    //编码
    private CharsetEnum charset = CharsetEnum.UTF_8;

    //支付方式
    private PayTypeEnum payType = PayTypeEnum.Personal_Banking;

    //创建人
    private String creator;
    
    //订单时间
    private Date orderTime = new Date();
    
    //充值时填写的金额
    private String rechargeAmount;

    
	public String getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(String rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public Integer getOrderExpireTime() {
        return orderExpireTime;
    }

    public void setOrderExpireTime(Integer orderExpireTime) {
        this.orderExpireTime = orderExpireTime;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    public String getUserorderid() {
        return userorderid;
    }

    public void setUserorderid(String userorderid) {
        this.userorderid = userorderid;
    }

    public OrderTypeEnum getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(OrderTypeEnum ordertype) {
        this.ordertype = ordertype;
    }

    public String getAdjustcontent() {
        return adjustcontent;
    }

    public void setAdjustcontent(String adjustcontent) {
        this.adjustcontent = adjustcontent;
    }

    public String getAdjusttype() {
        return adjusttype;
    }

    public void setAdjusttype(String adjusttype) {
        this.adjusttype = adjusttype;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public OperTypeEnum getOperType() {
        return operType;
    }

    public void setOperType(OperTypeEnum operType) {
        this.operType = operType;
    }

    public String getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getUserrelateid() {
        return userrelateid;
    }

    public void setUserrelateid(String userrelateid) {
        this.userrelateid = userrelateid;
    }

    public OrderMarkEnum getOrderMark() {
        return orderMark;
    }

    public void setOrderMark(OrderMarkEnum orderMark) {
        this.orderMark = orderMark;
    }

    public SignTypeEnum getSignType() {
        return signType;
    }

    public void setSignType(SignTypeEnum signType) {
        this.signType = signType;
    }

    public BusiCodeEnum getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(BusiCodeEnum busiCode) {
        this.busiCode = busiCode;
    }

    public VersionEnum getVersion() {
        return version;
    }

    public void setVersion(VersionEnum version) {
        this.version = version;
    }

    public CharsetEnum getCharset() {
        return charset;
    }

    public void setCharset(CharsetEnum charset) {
        this.charset = charset;
    }

    public PayTypeEnum getPayType() {
        return payType;
    }

    public void setPayType(PayTypeEnum payType) {
        this.payType = payType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

}