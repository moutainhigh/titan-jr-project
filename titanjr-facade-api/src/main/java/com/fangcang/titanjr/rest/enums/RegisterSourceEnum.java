package com.fangcang.titanjr.rest.enums;

/**
 * Created by zhaoshan on 2017/8/1.
 */
public enum RegisterSourceEnum {

    SAAS_MERCHANT(6,"SaaS接口自动注册"),TTM_SUPPLY(7,"TTM供应商"),
    TTM_AGENT(8,"TTM分销商");

    /**
     * 注册渠道
     */
    public int key;

    public String des;

    RegisterSourceEnum(int key,String des){
        this.key = key;
        this.des = des;
    }
}
