package com.titanjr.fop.request;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.common.util.GenericValidate;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrderServiceAuthcodeserviceResponse;

/**
 * Created by zhaoshan on 2017/12/28.
 */
public class WheatfieldOrderServiceAuthcodeserviceRequest extends BaseRequest implements FopRequest<WheatfieldOrderServiceAuthcodeserviceResponse> {
    private FopHashMap udfParams;
    @NotBlank
    private String orderno;
    private String intermerchantcode;
    private Integer ordercount;
    @NotBlank
    private String productid;
    private String paychannelid;
    @NotBlank
    private String requestno;
    private Integer status;
    @NotBlank
    private String funccode;
    @NotBlank
    private String merchantcode;
    @NotNull
    private Long orderamount;
    @NotNull
    private Date requesttime;
    private Date orderdate;
    private String errorcode;
    private String busitypeid;
    private String bankcode;
    @NotBlank
    private String conditioncode;
    private String orderpackageno;
    private String remark;
    @NotBlank
    private String userid;
    private Long feeamount;
    @NotNull
    private Long amount;
    private String errormsg;
    private String referuserid;
    private Long profit;
    private String tradeflowno;
    @NotBlank
    private String useripaddress;
    private Long userfee;

    public FopHashMap getUdfParams() {
        return udfParams;
    }

    public void setUdfParams(FopHashMap udfParams) {
        this.udfParams = udfParams;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getIntermerchantcode() {
        return intermerchantcode;
    }

    public void setIntermerchantcode(String intermerchantcode) {
        this.intermerchantcode = intermerchantcode;
    }

    public Integer getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(Integer ordercount) {
        this.ordercount = ordercount;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getPaychannelid() {
        return paychannelid;
    }

    public void setPaychannelid(String paychannelid) {
        this.paychannelid = paychannelid;
    }

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFunccode() {
        return funccode;
    }

    public void setFunccode(String funccode) {
        this.funccode = funccode;
    }

    public String getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
    }

    public Long getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(Long orderamount) {
        this.orderamount = orderamount;
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getBusitypeid() {
        return busitypeid;
    }

    public void setBusitypeid(String busitypeid) {
        this.busitypeid = busitypeid;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getConditioncode() {
        return conditioncode;
    }

    public void setConditioncode(String conditioncode) {
        this.conditioncode = conditioncode;
    }

    public String getOrderpackageno() {
        return orderpackageno;
    }

    public void setOrderpackageno(String orderpackageno) {
        this.orderpackageno = orderpackageno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getFeeamount() {
        return feeamount;
    }

    public void setFeeamount(Long feeamount) {
        this.feeamount = feeamount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getReferuserid() {
        return referuserid;
    }

    public void setReferuserid(String referuserid) {
        this.referuserid = referuserid;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public String getTradeflowno() {
        return tradeflowno;
    }

    public void setTradeflowno(String tradeflowno) {
        this.tradeflowno = tradeflowno;
    }

    public String getUseripaddress() {
        return useripaddress;
    }

    public void setUseripaddress(String useripaddress) {
        this.useripaddress = useripaddress;
    }

    public Long getUserfee() {
        return userfee;
    }

    public void setUserfee(Long userfee) {
        this.userfee = userfee;
    }

    public String getApiMethodName() {
        return "ruixue.wheatfield.order.service.authcodeservice";
    }

    public Map<String, String> getTextParams() {
        FopHashMap fopHashMap = new FopHashMap();
        fopHashMap.put("orderno", this.orderno);
        fopHashMap.put("intermerchantcode", this.intermerchantcode);
        fopHashMap.put("ordercount", this.ordercount);
        fopHashMap.put("productid", this.productid);
        fopHashMap.put("paychannelid", this.paychannelid);
        fopHashMap.put("requestno", this.requestno);
        fopHashMap.put("status", this.status);
        fopHashMap.put("funccode", this.funccode);
        fopHashMap.put("merchantcode", this.merchantcode);
        fopHashMap.put("orderamount", this.orderamount);
        fopHashMap.put("requesttime", this.requesttime);
        fopHashMap.put("orderdate", this.orderdate);
        fopHashMap.put("errorcode", this.errorcode);
        fopHashMap.put("busitypeid", this.busitypeid);
        fopHashMap.put("bankcode", this.bankcode);
        fopHashMap.put("conditioncode", this.conditioncode);
        fopHashMap.put("orderpackageno", this.orderpackageno);
        fopHashMap.put("remark", this.remark);
        fopHashMap.put("userid", this.userid);
        fopHashMap.put("feeamount", this.feeamount);
        fopHashMap.put("amount", this.amount);
        fopHashMap.put("errormsg", this.errormsg);
        fopHashMap.put("referuserid", this.referuserid);
        fopHashMap.put("profit", this.profit);
        fopHashMap.put("tradeflowno", this.tradeflowno);
        fopHashMap.put("useripaddress", this.useripaddress);
        fopHashMap.put("userfee", this.userfee);
        if (this.udfParams != null) {
            fopHashMap.putAll(this.udfParams);
        }

        return fopHashMap;
    }

    public void putOtherTextParam(String key, String value) {
        if (this.udfParams == null) {
            this.udfParams = new FopHashMap();
        }

        this.udfParams.put(key, value);
    }

    public Class<WheatfieldOrderServiceAuthcodeserviceResponse> getResponseClass() {
        return WheatfieldOrderServiceAuthcodeserviceResponse.class;
    }

    public void check() throws ApiRuleException {
        if (!GenericValidate.validate(this)) {
            throw new ApiRuleException(ReturnCodeEnum.CODE_NONE_ERROR.getCode(),
                    ReturnCodeEnum.CODE_NONE_ERROR.getMsg());
        }
    }
}
