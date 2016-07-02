package com.fangcang.titanjr.common.enums;

public enum MunicipalityEnum {
	
    BEIJING("BEIJING","北京市"), TIANJING("TIANJING","天津市"),
    SHANGHAI("SHANGHAI","上海市"), CHONGQING("CHONGQING","重庆市");

    public String cityCode;
    public String cityMsg;

    private MunicipalityEnum(String cityCode,String cityMsg){
        this.cityCode = cityCode;
        this.cityMsg = cityMsg;
    }

}
