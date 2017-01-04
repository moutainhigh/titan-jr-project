package com.fangcang.titanjr.common.exception;

/**
 * 融数接口调用的异常统一封装类
 * Created by zhaoshan on 2016/4/8.
 */
public class RSValidateException extends Exception{

    private static final long serialVersionUID = 4381788732268651773L;

    private String errorCode;

    private String errorMsg;

    public RSValidateException(){
    }

    public RSValidateException(String msg){
        super(msg);
    }

    public RSValidateException(Throwable cause){
        super(cause);
    }

    public RSValidateException(String msg, Throwable cause){
        super(msg, cause);
    }

    public RSValidateException(String errCode, String errMsg){
        super(errCode + "-" + errMsg);
    }

    public RSValidateException(String errCode, String errMsg, Throwable cause){
        super(errCode + "-" + errMsg, cause);
    }
    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}