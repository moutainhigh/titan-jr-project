package com.fangcang.titanjr.rs.response;

import com.fangcang.titanjr.rs.dto.BankCardInfo;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class BankCardQueryResponse extends BaseResponse{

    private List<BankCardInfo> bankCardInfoList;
    
    private String operateStatus;

    public List<BankCardInfo> getBankCardInfoList() {
        return bankCardInfoList;
    }

    public void setBankCardInfoList(List<BankCardInfo> bankCardInfoList) {
        this.bankCardInfoList = bankCardInfoList;
    }

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
    
    
}
