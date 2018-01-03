package com.titanjr.fop.response;

/**
 * Created by zhaoshan on 2018/1/2.
 */
public class WheatfieldOrderOperResponse extends FopResponse {


    private String orderid;

    private String is_success;

    public WheatfieldOrderOperResponse() {
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String var1) {
        this.orderid = var1;
    }

    public String getIs_success() {
        return this.is_success;
    }

    public void setIs_success(String var1) {
        this.is_success = var1;
    }
}
