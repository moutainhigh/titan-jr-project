package com.fangcang.titanjr.dto;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public enum OrganTypeEnum {
    SaaS(2,"SaaS机构"),TTMall(4,"TTMALL机构");
    /**
     *  
     */
    public Integer typeId;
    public String typeName;

    OrganTypeEnum(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }
}
