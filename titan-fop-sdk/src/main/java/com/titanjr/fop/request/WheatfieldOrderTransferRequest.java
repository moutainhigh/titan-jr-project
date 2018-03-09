package com.titanjr.fop.request;

import com.fangcang.util.StringUtil;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrderTransferResponse;
import com.titanjr.fop.util.FopUtils;

import java.util.Map;

/**
 * Created by zhaoshan on 2018/1/22.
 */
public class WheatfieldOrderTransferRequest extends BaseRequest implements FopRequest<WheatfieldOrderTransferResponse> {
    private FopHashMap udfParams;
    private String requestno;
    private String userfee;
    private String userrelateid;
    private String interproductid;
    private String intermerchantcode;
    private String requesttime;
    private String userid;
    private String conditioncode;
    private String productid;
    private String amount;
    private String transfertype;
    private String merchantcode;

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    public String getUserfee() {
        return userfee;
    }

    public void setUserfee(String userfee) {
        this.userfee = userfee;
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

    public String getIntermerchantcode() {
        return intermerchantcode;
    }

    public void setIntermerchantcode(String intermerchantcode) {
        this.intermerchantcode = intermerchantcode;
    }

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getConditioncode() {
        return conditioncode;
    }

    public void setConditioncode(String conditioncode) {
        this.conditioncode = conditioncode;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransfertype() {
        return transfertype;
    }

    public void setTransfertype(String transfertype) {
        this.transfertype = transfertype;
    }

    public String getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
    }

    public String getApiMethodName() {
        return "ruixue.wheatfield.order.transfer";
    }

    public Map<String, String> getTextParams() {
        FopHashMap fopHashMap = new FopHashMap();
        fopHashMap.put("requestno", this.requestno);
        fopHashMap.put("userfee", this.userfee);
        fopHashMap.put("userrelateid", this.userrelateid);
        fopHashMap.put("interproductid", this.interproductid);
        fopHashMap.put("intermerchantcode", this.intermerchantcode);
        fopHashMap.put("requesttime", this.requesttime);
        fopHashMap.put("userid", this.userid);
        fopHashMap.put("conditioncode", this.conditioncode);
        fopHashMap.put("productid", this.productid);
        fopHashMap.put("amount", this.amount);
        fopHashMap.put("transfertype", this.transfertype);
        fopHashMap.put("merchantcode", this.merchantcode);
        if (this.udfParams != null) {
            fopHashMap.putAll(this.udfParams);
        }

        return fopHashMap;
    }

    public void putOtherTextParam(String var1, String var2) {
        if (this.udfParams == null) {
            this.udfParams = new FopHashMap();
        }

        this.udfParams.put(var1, var2);
    }

    public Class<WheatfieldOrderTransferResponse> getResponseClass() {
        return WheatfieldOrderTransferResponse.class;
    }

    public void check() throws ApiRuleException {
        if (!StringUtil.isValidString(amount) || !FopUtils.isPositiveInteger(amount)) {
            throw new ApiRuleException(ReturnCodeEnum.CODE_AMOUNT_ERROR.getCode(),
                    ReturnCodeEnum.CODE_AMOUNT_ERROR.getMsg());
        }
        if (!StringUtil.isValidString(userid) || !StringUtil.isValidString(userrelateid)) {
            throw new ApiRuleException(ReturnCodeEnum.CODE_USERID_ERROR.getCode(),
                    ReturnCodeEnum.CODE_USERID_ERROR.getMsg());
        }
        if (!StringUtil.isValidString(productid) || !StringUtil.isValidString(interproductid)) {
            throw new ApiRuleException(ReturnCodeEnum.CODE_PRODID_ERROR.getCode(),
                    ReturnCodeEnum.CODE_PRODID_ERROR.getMsg());
        }
        if (!StringUtil.isValidString(requesttime)) {
            throw new ApiRuleException(ReturnCodeEnum.CODE_TIME_ERROR.getCode(),
                    ReturnCodeEnum.CODE_TIME_ERROR.getMsg());
        }
    }
}
