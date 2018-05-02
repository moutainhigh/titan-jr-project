package com.fangcang.titanjr.dto;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public enum OrganStatusEnum {
    FT("FT","待审核"),FT_INVALID("FT_INVALID","初审未通过"),REVIEW("REVIEW","复审中"),
    REVIEW_INVALID("REVIEW_INVALID","复审未通过"),PASS("PASS","已通过");

    public String status;
    public String desc;

    OrganStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static OrganStatusEnum getEnumByCode(String status){
        for (OrganStatusEnum statusEnum : OrganStatusEnum.values()){
            if (statusEnum.status.equals(status)){
                return statusEnum;
            }
        }
        return  null;
    }
}
