package com.titanjr.fop.response;

import com.titanjr.fop.dto.SHBalanceInfo;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public class WheatfieldBalanceGetlistResponse extends FopResponse {

    private List<SHBalanceInfo> shbalanceinfos;

    private String is_success = "false";

    public List<SHBalanceInfo> getShbalanceinfos() {
        return shbalanceinfos;
    }

    public void setShbalanceinfos(List<SHBalanceInfo> shbalanceinfos) {
        this.shbalanceinfos = shbalanceinfos;
    }

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }
}
