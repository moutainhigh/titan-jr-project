package com.fangcang.titanjr.request;


/**
 * Created by zhaoshan on 2016/12/27.
 */
public class BalanceQueryRequest extends BaseRequest {

    /**
     * 金融机构编码
     */
    private String titanOrgCode;

    public String getTitanOrgCode() {
        return titanOrgCode;
    }

    public void setTitanOrgCode(String titanOrgCode) {
        this.titanOrgCode = titanOrgCode;
    }
}
