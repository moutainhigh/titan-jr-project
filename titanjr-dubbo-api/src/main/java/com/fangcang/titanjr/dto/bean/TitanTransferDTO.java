package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;
/***
 * 转账单
 * @author luoqinglong
 * @date 2017年11月20日
 */
public class TitanTransferDTO implements Serializable {

    private Integer transferreqid;
    private Integer transorderid;
    private Integer transfertype;
    private Integer conditioncode;
    private String merchantcode;
    private String userid;
    private String productid;
    private String intermerchantcode;
    private String userrelateid;
    private String interproductid;
    private Double amount;
    private Double userfee;
    private String requestno;
    private Date requesttime;
    private String useripaddress;
    private String remark;
    private Integer status;
    private String creator;
    private Date createtime;
    private String payOrderNo;
    

    public Integer getTransferreqid() {
        return transferreqid;
    }

    public void setTransferreqid(Integer transferreqid) {
        this.transferreqid = transferreqid;
    }

    public Integer getTransorderid() {
        return transorderid;
    }

    public void setTransorderid(Integer transorderid) {
        this.transorderid = transorderid;
    }

    public Integer getTransfertype() {
        return transfertype;
    }

    public void setTransfertype(Integer transfertype) {
        this.transfertype = transfertype;
    }

    public Integer getConditioncode() {
        return conditioncode;
    }

    public void setConditioncode(Integer conditioncode) {
        this.conditioncode = conditioncode;
    }

    public String getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getIntermerchantcode() {
        return intermerchantcode;
    }

    public void setIntermerchantcode(String intermerchantcode) {
        this.intermerchantcode = intermerchantcode;
    }

    public String getUserrelateid() {
        return userrelateid;
    }

    public void setUserrelateid(String userrelateid) {
        this.userrelateid = userrelateid;
    }

    public String getInterproductid() {
        return interproductid;
    }

    public void setInterproductid(String interproductid) {
        this.interproductid = interproductid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUserfee() {
        return userfee;
    }

    public void setUserfee(Double userfee) {
        this.userfee = userfee;
    }

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

    public String getUseripaddress() {
        return useripaddress;
    }

    public void setUseripaddress(String useripaddress) {
        this.useripaddress = useripaddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}
    
}