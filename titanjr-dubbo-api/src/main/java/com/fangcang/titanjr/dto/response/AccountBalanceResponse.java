package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.AccountBalance;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class AccountBalanceResponse extends BaseResponseDTO{
	   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //账户信息
	private List<AccountBalance> accountBalance;
	//操作状态
	private String operateStatus;

	public List<AccountBalance> getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(List<AccountBalance> accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	    

}

