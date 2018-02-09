package com.titanjr.fop.request;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrdernQueryResponse;

import java.util.Date;
import java.util.Map;

/**
 * Created by zhaoshan on 2018/1/16.
 */
public class WheatfieldOrdernQueryRequest extends BaseRequest implements FopRequest<WheatfieldOrdernQueryResponse> {
    private FopHashMap udfParams;
    private String intermerchantcode;
    private String status;
    private Date starttime;
    private Long amount;
    private String funccode;
    private String merchantcode;
    private String userid;
    private String orderno;
    private Date endtime;

    public WheatfieldOrdernQueryRequest() {
    }

    public FopHashMap getUdfParams() {
        return udfParams;
    }

    public void setUdfParams(FopHashMap udfParams) {
        this.udfParams = udfParams;
    }

    public String getIntermerchantcode() {
        return intermerchantcode;
    }

    public void setIntermerchantcode(String intermerchantcode) {
        this.intermerchantcode = intermerchantcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getApiMethodName() {
        return "ruixue.wheatfield.ordern.query";
    }

    public Map<String, String> getTextParams() {
        FopHashMap fopHashMap = new FopHashMap();
        fopHashMap.put("intermerchantcode", this.intermerchantcode);
        fopHashMap.put("status", this.status);
        fopHashMap.put("starttime", this.starttime);
        fopHashMap.put("amount", this.amount);
        fopHashMap.put("funccode", this.funccode);
        fopHashMap.put("merchantcode", this.merchantcode);
        fopHashMap.put("userid", this.userid);
        fopHashMap.put("orderno", this.orderno);
        fopHashMap.put("endtime", this.endtime);
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

    public Class<WheatfieldOrdernQueryResponse> getResponseClass() {
        return WheatfieldOrdernQueryResponse.class;
    }

    public void check() throws ApiRuleException {
    }
}
