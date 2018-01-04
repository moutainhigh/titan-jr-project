package com.titanjr.fop.response;

/**
 * Created by zhaoshan on 2018/1/2.
 */
public class WheatfieldOrderOperResponse extends FopResponse {


    private String orderid;

    private String is_success = "false";

    public WheatfieldOrderOperResponse() {
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getIs_success() {
        return this.is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }
}
