package com.titanjr.fop.request;

import com.fangcang.titanjr.common.util.GenericValidate;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrderOperResponse;
import com.titanjr.fop.util.FopUtils;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * Created by zhaoshan on 2018/1/2.
 */

public class WheatfieldOrderOperRequest extends BaseRequest implements FopRequest<WheatfieldOrderOperResponse> {
    private FopHashMap udfParams;
    @NotNull
    private Date ordertime;
    private String remark;
    private String adjusttype;
    @NotBlank
    private String productid;
    @NotBlank
    private String userid;
    @NotBlank
    private String userorderid;
    private String ordertypeid;
    @NotBlank
    private String constid;
    //修改：2,新增：1,取消4,查询3
    @NotBlank
    private String opertype;
    private Integer number;
    @NotNull
    private Date orderdate;
    private String adjustcontent;
    private String userrelateid;
    @NotBlank
    private String amount;
    private String goodsdetail;
    private String goodsname;
    private String unitprice;

    public WheatfieldOrderOperRequest() {
    }

    public Date getOrdertime() {
        return this.ordertime;
    }

    public void setOrdertime(Date var1) {
        this.ordertime = var1;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String var1) {
        this.remark = var1;
    }

    public String getAdjusttype() {
        return this.adjusttype;
    }

    public void setAdjusttype(String var1) {
        this.adjusttype = var1;
    }

    public String getProductid() {
        return this.productid;
    }

    public void setProductid(String var1) {
        this.productid = var1;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String var1) {
        this.userid = var1;
    }

    public String getUserorderid() {
        return this.userorderid;
    }

    public void setUserorderid(String var1) {
        this.userorderid = var1;
    }

    public String getOrdertypeid() {
        return this.ordertypeid;
    }

    public void setOrdertypeid(String var1) {
        this.ordertypeid = var1;
    }

    public String getConstid() {
        return this.constid;
    }

    public void setConstid(String var1) {
        this.constid = var1;
    }

    public String getOpertype() {
        return this.opertype;
    }

    public void setOpertype(String var1) {
        this.opertype = var1;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer var1) {
        this.number = var1;
    }

    public Date getOrderdate() {
        return this.orderdate;
    }

    public void setOrderdate(Date var1) {
        this.orderdate = var1;
    }

    public String getAdjustcontent() {
        return this.adjustcontent;
    }

    public void setAdjustcontent(String var1) {
        this.adjustcontent = var1;
    }

    public String getUserrelateid() {
        return this.userrelateid;
    }

    public void setUserrelateid(String var1) {
        this.userrelateid = var1;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String var1) {
        this.amount = var1;
    }

    public String getGoodsdetail() {
        return this.goodsdetail;
    }

    public void setGoodsdetail(String var1) {
        this.goodsdetail = var1;
    }

    public String getGoodsname() {
        return this.goodsname;
    }

    public void setGoodsname(String var1) {
        this.goodsname = var1;
    }

    public String getUnitprice() {
        return this.unitprice;
    }

    public void setUnitprice(String var1) {
        this.unitprice = var1;
    }

    public String getApiMethodName() {
        return "ruixue.wheatfield.order.oper";
    }

    public Map<String, String> getTextParams() {
        FopHashMap fopHashMap = new FopHashMap();
        fopHashMap.put("ordertime", this.ordertime);
        fopHashMap.put("remark", this.remark);
        fopHashMap.put("adjusttype", this.adjusttype);
        fopHashMap.put("productid", this.productid);
        fopHashMap.put("userid", this.userid);
        fopHashMap.put("userorderid", this.userorderid);
        fopHashMap.put("ordertypeid", this.ordertypeid);
        fopHashMap.put("constid", this.constid);
        fopHashMap.put("opertype", this.opertype);
        fopHashMap.put("number", this.number);
        fopHashMap.put("orderdate", this.orderdate);
        fopHashMap.put("adjustcontent", this.adjustcontent);
        fopHashMap.put("userrelateid", this.userrelateid);
        fopHashMap.put("amount", this.amount);
        fopHashMap.put("goodsdetail", this.goodsdetail);
        fopHashMap.put("goodsname", this.goodsname);
        fopHashMap.put("unitprice", this.unitprice);
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

    public Class<WheatfieldOrderOperResponse> getResponseClass() {
        return WheatfieldOrderOperResponse.class;
    }

    public void check() throws ApiRuleException {
        if (!"1".equals(opertype) && !"2".equals(opertype) &&
                !"3".equals(opertype) && !"4".equals(opertype)){
            throw new ApiRuleException(ReturnCodeEnum.CODE_OPERTYPE_ERROR.getCode(),
                    ReturnCodeEnum.CODE_OPERTYPE_ERROR.getMsg());
        }
        if (!GenericValidate.validate(this)){
            throw new ApiRuleException(ReturnCodeEnum.CODE_NONE_ERROR.getCode(),
                    ReturnCodeEnum.CODE_NONE_ERROR.getMsg());
        }
        if (!FopUtils.isPositiveInteger(amount)) {
            throw new ApiRuleException(ReturnCodeEnum.CODE_AMOUNT_ERROR.getCode(),
                    ReturnCodeEnum.CODE_AMOUNT_ERROR.getMsg());
        }
    }
}
