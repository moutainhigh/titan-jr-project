package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class TitanWithDrawDTO implements Serializable {

    private Integer withdrawreqid;
    private Integer transorderid;
    private String merchantcode;
    private String userid;
    private String productid;
    private String userorderid;
    private String orderdate;
    private Long amount;
    private Long userfee;
    private Integer status;
    private String creator;
    private Date createtime;
    private Float standardrate;
    private Float executionrate;
    private Integer ratetype;
    private Float receivablefee;
    private Float receivedfee;
    private String bankname;

    private String bankcode;

    public Integer getWithdrawreqid() {
        return withdrawreqid;
    }

    public void setWithdrawreqid(Integer withdrawreqid) {
        this.withdrawreqid = withdrawreqid;
    }

    public Integer getTransorderid() {
        return transorderid;
    }

    public void setTransorderid(Integer transorderid) {
        this.transorderid = transorderid;
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

    public String getUserorderid() {
        return userorderid;
    }

    public void setUserorderid(String userorderid) {
        this.userorderid = userorderid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getUserfee() {
        return userfee;
    }

    public void setUserfee(Long userfee) {
        this.userfee = userfee;
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

    public Float getReceivablefee() {
        return receivablefee;
    }

    public void setReceivablefee(Float receivablefee) {
        this.receivablefee = receivablefee;
    }

    public Float getReceivedfee() {
        return receivedfee;
    }

    public void setReceivedfee(Float receivedfee) {
        this.receivedfee = receivedfee;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }
}
