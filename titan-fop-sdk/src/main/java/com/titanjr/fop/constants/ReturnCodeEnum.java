package com.titanjr.fop.constants;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public enum ReturnCodeEnum {

    CODE_SIGN_ERROR("-200","sign错误"),
    CODE_SESSION_ERROR("-201","session信息错误"),
    CODE_SYS_ERROR("-300","系统错误");
    private String code;
    private String msg;

    private ReturnCodeEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
