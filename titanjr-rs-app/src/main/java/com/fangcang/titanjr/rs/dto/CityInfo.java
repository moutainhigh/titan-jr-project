package com.fangcang.titanjr.rs.dto;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/4/12.
 */
public class CityInfo {
    //查询城市信息时返回，省份
    private String parentcode;

    private Integer datatype;

    private String citycode;

    private String cityname;
    
    public Integer getDatatype() {
        return datatype;
    }

    public void setDatatype(Integer datatype) {
        this.datatype = datatype;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
}
