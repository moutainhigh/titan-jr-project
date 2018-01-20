package com.titanjr.checkstand.constants;

/**
 * Created by zhaoshan on 2017/11/15.
 */
public enum LanguageEnum {
    CHN_SIM(1,"简体中文"),CHN_TRAD(2,"繁体中文"),ENG(3,"英文");

    public int key;
    public String value;

    LanguageEnum(int key, String value){
        this.key = key;
        this.value = value;
    }
}
