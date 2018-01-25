package com.titanjr.fop.request;

import com.fangcang.titanjr.common.util.GenericValidate;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrderServiceReturngoodsResponse;
import com.titanjr.fop.response.WheatfieldOrderServiceThawauthcodeResponse;
import com.titanjr.fop.util.FopUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public class WheatfieldOrderServiceReturngoodsRequest extends BaseRequest implements FopRequest<WheatfieldOrderServiceReturngoodsResponse> {
    private FopHashMap udfParams;
    @NotBlank
    private String userorderid;
    @NotBlank
    private String amount;
    @NotBlank
    private String orderid;
    private String orderitemid;

    public WheatfieldOrderServiceReturngoodsRequest() {
    }

    public String getUserorderid() {
        return this.userorderid;
    }

    public void setUserorderid(String var1) {
        this.userorderid = var1;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String var1) {
        this.amount = var1;
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String var1) {
        this.orderid = var1;
    }

    public String getOrderitemid() {
        return this.orderitemid;
    }

    public void setOrderitemid(String var1) {
        this.orderitemid = var1;
    }

    public String getApiMethodName() {
        return "ruixue.wheatfield.order.service.returngoods";
    }

    public Map<String, String> getTextParams() {
        FopHashMap fopHashMap = new FopHashMap();
        fopHashMap.put("userorderid", this.userorderid);
        fopHashMap.put("amount", this.amount);
        fopHashMap.put("orderid", this.orderid);
        fopHashMap.put("orderitemid", this.orderitemid);
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

    public Class<WheatfieldOrderServiceReturngoodsResponse> getResponseClass() {
        return WheatfieldOrderServiceReturngoodsResponse.class;
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
