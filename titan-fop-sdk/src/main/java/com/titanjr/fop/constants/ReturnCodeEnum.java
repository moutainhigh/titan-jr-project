package com.titanjr.fop.constants;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public enum ReturnCodeEnum {

    CODE_SUCCESS("1","成功"),CODE_PARAM_ERROR("-200","参数错误"),CODE_SYS_ERROR("-300","系统错误");
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
