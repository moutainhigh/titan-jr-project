package com.titanjr.checkstand.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 退款请求参数（融数格式）
 * @author Jerry
 * @date 2017年12月7日 下午3:00:18
 */
public class TitanRefundDTO {

    /**
     * 商户号
     */
    @NotBlank
    private String merchantNo;
    /**
     * 业务号
     */
    private String busiCode;
    /**
     * 退款单号
     */
    @NotBlank
    private String refundOrderno;
    /**
     * 订单号
     */
    @NotBlank
    private String orderNo;
    /**
     * 订单时间
     */
    @NotBlank
    private String orderTime;
    /**
     * 退款金额
     */
    @NotBlank
    private String refundAmount;
    /**
     * 版本号
     */
    @NotBlank
    private String version;
    /**
     * 签名类型
     */
    @NotBlank
    private String signType;
    /**
     * 签名信息
     */
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

    public String getRefundOrderno() {
        return refundOrderno;
    }

    public void setRefundOrderno(String refundOrderno) {
        this.refundOrderno = refundOrderno;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}
    
}
