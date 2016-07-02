package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class OrderOperateRequest extends BaseRequest{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单时间
    private Date ordertime;
    //备注
    private String remark;
    //调整类型
    private String adjusttype;
    //用户订单编号
    private String userorderid;
    //基础业务为B1，扩展业务待定
    private String ordertypeid;
    //操作类型（修改：2,新增：1,取消4,查询3）
    private String opertype;
    //商品数量
    private Integer number;
    //订单日期
    private Date orderdate;
    //调整内容
    private String adjustcontent;
    //关联用户ID（若有第三方则必须填写
    private String userrelateid;
    //订单金额（若不存在商品概念则必填）
    private String amount;
    //商品描述
    private String goodsdetail;
    //商品名称
    private String goodsname;
    //单价
    private String unitprice;

    @Override
    public void check() throws RSValidateException {
        //校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.getOrdertypeid(), "ordertypeid");
        RequestValidationUtil.checkNotEmpty(this.getOpertype(), "opertype");
        RequestValidationUtil.checkNotEmpty(this.getOrderdate(), "orderdate");
        RequestValidationUtil.checkNotEmpty(this.getOrdertime(), "ordertime");
        RequestValidationUtil.checkNotEmpty(this.getUserorderid(), "userorderid");
        RequestValidationUtil.checkNotEmpty(this.getAmount(), "amount");
        RequestValidationUtil.checkMaxLength(this.getOpertype(), 1, "opertype");
        
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAdjusttype() {
        return adjusttype;
    }

    public void setAdjusttype(String adjusttype) {
        this.adjusttype = adjusttype;
    }

    public String getUserorderid() {
        return userorderid;
    }

    public void setUserorderid(String userorderid) {
        this.userorderid = userorderid;
    }

    public String getOrdertypeid() {
        return ordertypeid;
    }

    public void setOrdertypeid(String ordertypeid) {
        this.ordertypeid = ordertypeid;
    }

    public String getOpertype() {
        return opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getAdjustcontent() {
        return adjustcontent;
    }

    public void setAdjustcontent(String adjustcontent) {
        this.adjustcontent = adjustcontent;
    }

    public String getUserrelateid() {
        return userrelateid;
    }

    public void setUserrelateid(String userrelateid) {
        this.userrelateid = userrelateid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGoodsdetail() {
        return goodsdetail;
    }

    public void setGoodsdetail(String goodsdetail) {
        this.goodsdetail = goodsdetail;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }
}
