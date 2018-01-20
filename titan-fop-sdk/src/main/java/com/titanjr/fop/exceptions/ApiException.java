package com.titanjr.fop.exceptions;

/**
 * Created by zhaoshan on 2017/12/19.
 */
public class ApiException extends Exception {
    private String errCode;
    private String errMsg;

    public ApiException() {
    }

    public ApiException(String errCode, Throwable errMsg) {
        super(errCode, errMsg);
    }

    public ApiException(String errCode) {
        super(errCode);
    }

    public ApiException(Throwable errCode) {
        super(errCode);
    }

    public ApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
