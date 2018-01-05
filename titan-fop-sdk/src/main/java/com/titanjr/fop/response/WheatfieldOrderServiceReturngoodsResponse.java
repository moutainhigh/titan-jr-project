package com.titanjr.fop.response;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public class WheatfieldOrderServiceReturngoodsResponse extends FopResponse {
    private String batchno;

    private String is_success;

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }
}
