package com.fangcang.titanjr.rs.response;

import com.fangcang.titanjr.rs.dto.BalanceInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 账户查询结果，返回账户余额等
 * Created by zhaoshan on 2016/4/13.
 */
public class AccountBalanceQueryResponse extends BaseResponse {

    private List<BalanceInfo> balanceInfoList;
    

    public List<BalanceInfo> getBalanceInfoList() {
        return balanceInfoList;
    }

    public void setBalanceInfoList(List<BalanceInfo> balanceInfoList) {
        this.balanceInfoList = balanceInfoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("balanceInfoList", balanceInfoList)
                .toString();
    }
}
