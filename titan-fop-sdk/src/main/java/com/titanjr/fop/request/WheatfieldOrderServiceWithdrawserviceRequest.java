package com.titanjr.fop.request;

import com.fangcang.titanjr.common.util.GenericValidate;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrderServiceWithdrawserviceResponse;
import com.titanjr.fop.util.FopUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * Created by zhaoshan on 2018/1/26.
 */
public class WheatfieldOrderServiceWithdrawserviceRequest extends BaseRequest implements FopRequest<WheatfieldOrderServiceWithdrawserviceResponse> {
    private FopHashMap udfParams;
    @NotBlank
    private String userid;
    @NotBlank
    private String orderdate;
    @NotBlank
    private String merchantcode;
    @NotBlank
    private String amount;
    private String cardno;
    @NotBlank
    private String productid;
    @NotBlank
    private String userorderid;
    @NotBlank
    private Long userfee;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
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

    public Long getUserfee() {
        return userfee;
    }

    public void setUserfee(Long userfee) {
        this.userfee = userfee;
    }

    public String getApiMethodName() {
        return "ruixue.wheatfield.order.service.withdrawservice";
    }

    public Map<String, String> getTextParams() {
        FopHashMap fopHashMap = new FopHashMap();
        fopHashMap.put("userid", this.userid);
        fopHashMap.put("orderdate", this.orderdate);
        fopHashMap.put("merchantcode", this.merchantcode);
        fopHashMap.put("amount", this.amount);
        fopHashMap.put("cardno", this.cardno);
        fopHashMap.put("productid", this.productid);
        fopHashMap.put("userorderid", this.userorderid);
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

    public Class<WheatfieldOrderServiceWithdrawserviceResponse> getResponseClass() {
        return WheatfieldOrderServiceWithdrawserviceResponse.class;
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
