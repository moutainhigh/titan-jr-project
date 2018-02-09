package com.titanjr.fop.response;

/**
 * Created by zhaoshan on 2018/1/26.
 */
public class WheatfieldOrderServiceWithdrawserviceResponse extends FopResponse {

    private String is_success = "false";

    //新增提现状态参数1:"处理中",2:"失败",3:"成功",
    // 4:"上游成功，本地余额修改失败"
    private String status;

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
