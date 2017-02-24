package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class BalanceUnFreezeRequest extends BaseRequest{

    //机构号,等同于constid
    private String rootinstcd;
    //冻结接口返回的授权码
    private String authcode;
    //冻结时上传的requestno
    private String frozenuserorderid;
    //金额 单位 分
    private String amount;
    //请求时间 yyyy-MM-dd HH:mm:ss（请按格式传递）
    private String requesttime;
    //请求号
    private String requestno;
    //特殊字段，使用时联系供应方获取
    private String conditioncode;
    
    
    @Override
    public void check() throws RSValidateException {
//    	 RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
         RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
         RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
         RequestValidationUtil.checkNotEmpty(this.getConditioncode(),"conditioncode");
         RequestValidationUtil.checkNotEmpty(this.getAmount(),"amount");
         RequestValidationUtil.checkNotEmpty(this.getRootinstcd(),"rootinstcd");
         RequestValidationUtil.checkNotEmpty(this.getFrozenuserorderid(),"frozenuserorderid");
         RequestValidationUtil.checkNotEmpty(this.getRequesttime(),"requesttime");
         RequestValidationUtil.checkNotEmpty(this.getRequestno(),"requestno");
         
         
         
    }

    public String getRootinstcd() {
        return rootinstcd;
    }

    public void setRootinstcd(String rootinstcd) {
        this.rootinstcd = rootinstcd;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public String getFrozenuserorderid() {
        return frozenuserorderid;
    }

    public void setFrozenuserorderid(String frozenuserorderid) {
        this.frozenuserorderid = frozenuserorderid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    public String getConditioncode() {
        return conditioncode;
    }

    public void setConditioncode(String conditioncode) {
        this.conditioncode = conditioncode;
    }
}
