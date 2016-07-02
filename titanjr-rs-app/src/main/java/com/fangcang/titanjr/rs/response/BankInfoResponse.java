package com.fangcang.titanjr.rs.response;


import com.fangcang.titanjr.rs.dto.BankInfo;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/12.
 */
public class BankInfoResponse extends BaseResponse{

    private List<BankInfo> bankInfos;

    public List<BankInfo> getBankInfos() {
        return bankInfos;
    }

    public void setBankInfos(List<BankInfo> bankInfos) {
        this.bankInfos = bankInfos;
    }
}
