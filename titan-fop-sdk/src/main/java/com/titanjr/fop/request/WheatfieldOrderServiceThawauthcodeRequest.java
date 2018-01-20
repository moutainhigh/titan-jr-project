package com.titanjr.fop.request;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrderServiceThawauthcodeResponse;

import java.util.Map;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public class WheatfieldOrderServiceThawauthcodeRequest extends BaseRequest implements FopRequest<WheatfieldOrderServiceThawauthcodeResponse> {
    private FopHashMap udfParams;
    private String amount;
    private String rootinstcd;
    private String frozenuserorderid;
    private String requestno;
    private String authcode;
    private String productid;
    private String requesttime;
    private String userid;
    private String conditioncode;

    public WheatfieldOrderServiceThawauthcodeRequest() {
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String var1) {
        this.amount = var1;
    }

    public String getRootinstcd() {
        return this.rootinstcd;
    }

    public void setRootinstcd(String var1) {
        this.rootinstcd = var1;
    }

    public String getFrozenuserorderid() {
        return this.frozenuserorderid;
    }

    public void setFrozenuserorderid(String var1) {
        this.frozenuserorderid = var1;
    }

    public String getRequestno() {
        return this.requestno;
    }

    public void setRequestno(String var1) {
        this.requestno = var1;
    }

    public String getAuthcode() {
        return this.authcode;
    }

    public void setAuthcode(String var1) {
        this.authcode = var1;
    }

    public String getProductid() {
        return this.productid;
    }

    public void setProductid(String var1) {
        this.productid = var1;
    }

    public String getRequesttime() {
        return this.requesttime;
    }

    public void setRequesttime(String var1) {
        this.requesttime = var1;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String var1) {
        this.userid = var1;
    }

    public String getConditioncode() {
        return this.conditioncode;
    }

    public void setConditioncode(String var1) {
        this.conditioncode = var1;
    }

    public String getApiMethodName() {
        return "ruixue.wheatfield.order.service.thawauthcode";
    }

    public Map<String, String> getTextParams() {
        FopHashMap fopHashMap = new FopHashMap();
        fopHashMap.put("amount", this.amount);
        fopHashMap.put("rootinstcd", this.rootinstcd);
        fopHashMap.put("frozenuserorderid", this.frozenuserorderid);
        fopHashMap.put("requestno", this.requestno);
        fopHashMap.put("authcode", this.authcode);
        fopHashMap.put("productid", this.productid);
        fopHashMap.put("requesttime", this.requesttime);
        fopHashMap.put("userid", this.userid);
        fopHashMap.put("conditioncode", this.conditioncode);
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

    public Class<WheatfieldOrderServiceThawauthcodeResponse> getResponseClass() {
        return WheatfieldOrderServiceThawauthcodeResponse.class;
    }

    public void check() throws ApiRuleException {
    }
}
