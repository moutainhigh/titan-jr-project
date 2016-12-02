package com.fangcang.titanjr.rs.response;


import com.fangcang.titanjr.rs.dto.CityInfo;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/12.
 */
public class CityInfoResponse extends BaseResponse {

    private List<CityInfo> cityInfos;

    public List<CityInfo> getCityInfos() {
        return cityInfos;
    }

    public void setCityInfos(List<CityInfo> cityInfos) {
        this.cityInfos = cityInfos;
    }
}
