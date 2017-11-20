package com.titanjr.checkstand.constants;

/**
 * Created by zhaoshan on 2017/11/15.
 */
public enum AmtTypeEnum {

    RMN_0(0,"简体中文"),RMB_156(156,"繁体中文"),USD(840,"英文"),HKD(344,"英文");

    public int code;
    public String value;

    AmtTypeEnum(int code, String value){
        this.code = code;
        this.value = value;
    }
}
