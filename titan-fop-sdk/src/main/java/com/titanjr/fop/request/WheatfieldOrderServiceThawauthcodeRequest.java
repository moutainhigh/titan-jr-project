package com.titanjr.fop.request;

import com.fangcang.titanjr.common.util.GenericValidate;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrderServiceThawauthcodeResponse;
import com.titanjr.fop.util.FopUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public class WheatfieldOrderServiceThawauthcodeRequest extends BaseRequest implements FopRequest<WheatfieldOrderServiceThawauthcodeResponse> {
    private FopHashMap udfParams;
    @NotBlank
    private String amount;
    private String rootinstcd;
    private String frozenuserorderid;
    @NotBlank
    private String requestno;
    @NotBlank
    private String authcode;
    @NotBlank
    private String productid;
    private String requesttime;
    @NotBlank
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
        if (!GenericValidate.validate(this)) {
            throw new ApiRuleException(ReturnCodeEnum.CODE_NONE_ERROR.getCode(),
                    ReturnCodeEnum.CODE_NONE_ERROR.getMsg());
        }
        if (!FopUtils.isPositiveInteger(amount)) {
            throw new ApiRuleException(ReturnCodeEnum.CODE_AMOUNT_ERROR.getCode(),
                    ReturnCodeEnum.CODE_AMOUNT_ERROR.getMsg());
        }
    }
}
