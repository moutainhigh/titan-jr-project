package com.fangcang.titanjr.common.enums;

/**
 * 融数接口调用返回的异常的统一处理
 * Created by zhaoshan on 2016/4/9.
 */
public enum RSInvokeErrorEnum {

    PARAM_EMPTY("PARAM_EMPTY","校验参数为空"), PARAM_INVALID("PARAM_INVALID","校验参数非法"),
    RETURN_EMPTY("RETURN_EMPTY","返回结果为空"), UNKNOWN_ERROR("UNKNOWN_ERROR","未知异常"),
    INVOKE_SUCCESS("INVOKE_SUCCESS","调用成功"),SIGNCHECK_FAIURE("SIGNCHECK_FAIURE","签名验证失败");

    public String returnCode;
    public String returnMsg;

    private RSInvokeErrorEnum(String returnCode,String returnMsg){
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }


}
