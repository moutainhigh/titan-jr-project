package com.titanjr.fop.response;

import com.titanjr.fop.dto.Transorderinfo;

import java.util.List;

/**
 * Created by zhaoshan on 2018/1/16.
 */
public class WheatfieldOrdernQueryResponse extends FopResponse {
    private List<Transorderinfo> transorderinfos;

    public List<Transorderinfo> getTransorderinfos() {
        return transorderinfos;
    }

    public void setTransorderinfos(List<Transorderinfo> transorderinfos) {
        this.transorderinfos = transorderinfos;
    }
}
