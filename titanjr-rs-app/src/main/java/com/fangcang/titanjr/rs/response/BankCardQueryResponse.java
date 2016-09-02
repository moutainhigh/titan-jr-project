package com.fangcang.titanjr.rs.response;

import com.fangcang.titanjr.rs.dto.BankCardInfo;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class BankCardQueryResponse extends BaseResponse{

    private List<BankCardInfo> bankCardInfoList;
    

    public List<BankCardInfo> getBankCardInfoList() {
        return bankCardInfoList;
    }

    public void setBankCardInfoList(List<BankCardInfo> bankCardInfoList) {
        this.bankCardInfoList = bankCardInfoList;
    }
}
