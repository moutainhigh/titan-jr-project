package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.AccountDTO;

/**
 * 账户查询
 * @author luoqinglong
 * @2016年7月4日
 */
public class AccountResponse extends BaseResponseDTO {
	
	
	private AccountDTO accountDTO;

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}
	
	
	
}
