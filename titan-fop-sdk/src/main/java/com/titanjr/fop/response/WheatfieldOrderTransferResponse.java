package com.titanjr.fop.response;

/**
 * Created by zhaoshan on 2018/1/22.
 */
public class WheatfieldOrderTransferResponse extends FopResponse {
    private String retmsg;
    private String retcode;
    private String orderid;
    private String is_success;

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }
}
