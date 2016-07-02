package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;

public class AccountHistoryRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountHistoryDTO accountHistoryDTO;

	public AccountHistoryDTO getAccountHistoryDTO() {
		return accountHistoryDTO;
	}

	public void setAccountHistoryDTO(AccountHistoryDTO accountHistoryDTO) {
		this.accountHistoryDTO = accountHistoryDTO;
	}
	

}
