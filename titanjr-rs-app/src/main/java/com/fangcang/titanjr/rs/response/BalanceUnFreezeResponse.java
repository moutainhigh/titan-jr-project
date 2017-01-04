package com.fangcang.titanjr.rs.response;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class BalanceUnFreezeResponse extends BaseResponse {

    //返回码
    private String retcode;
    //返回信息
    private String retmsg;

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

}
