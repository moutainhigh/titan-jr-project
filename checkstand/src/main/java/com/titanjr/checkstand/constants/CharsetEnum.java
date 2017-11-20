package com.titanjr.checkstand.constants;

/**
 * Created by zhaoshan on 2017/11/15.
 */
public enum CharsetEnum {

    UTF_8(1,"UTF-8"),GBK(2,"GBK"),GB2312(3,"GB2312");

    public int key;
    public String value;

    CharsetEnum(int key, String value){
        this.key = key;
        this.value = value;
    }

}
