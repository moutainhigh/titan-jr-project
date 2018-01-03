package com.titanjr.fop.request;

import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldOrderOperResponse;

import java.util.Date;
import java.util.Map;

/**
 * Created by zhaoshan on 2018/1/2.
 */
public class WheatfieldOrderOperRequest extends BaseRequest implements FopRequest<WheatfieldOrderOperResponse> {
    private FopHashMap udfParams;
    private Date ordertime;
    private String remark;
    private String adjusttype;
    private String productid;
    private String userid;
    private String userorderid;
    private String ordertypeid;
    private String constid;
    //修改：2,新增：1,取消4,查询3
    private String opertype;
    private Integer number;
    private Date orderdate;
    private String adjustcontent;
    private String userrelateid;
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
        FopHashMap var1 = new FopHashMap();
        var1.put("ordertime", this.ordertime);
        var1.put("remark", this.remark);
        var1.put("adjusttype", this.adjusttype);
        var1.put("productid", this.productid);
        var1.put("userid", this.userid);
        var1.put("userorderid", this.userorderid);
        var1.put("ordertypeid", this.ordertypeid);
        var1.put("constid", this.constid);
        var1.put("opertype", this.opertype);
        var1.put("number", this.number);
        var1.put("orderdate", this.orderdate);
        var1.put("adjustcontent", this.adjustcontent);
        var1.put("userrelateid", this.userrelateid);
        var1.put("amount", this.amount);
        var1.put("goodsdetail", this.goodsdetail);
        var1.put("goodsname", this.goodsname);
        var1.put("unitprice", this.unitprice);
        if (this.udfParams != null) {
            var1.putAll(this.udfParams);
        }

        return var1;
    }

    public void putOtherTextParam(String var1, String var2) {
        if (this.udfParams == null) {
            this.udfParams = new FopHashMap();
        }

        this.udfParams.put(var1, var2);
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

    }
}
