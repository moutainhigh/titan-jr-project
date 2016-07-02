package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;

public class AccountHistoryResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AccountHistoryDTO> accountHistoryDTOList;

	public List<AccountHistoryDTO> getAccountHistoryDTOList() {
		return accountHistoryDTOList;
	}

	public void setAccountHistoryDTOList(
			List<AccountHistoryDTO> accountHistoryDTOList) {
		this.accountHistoryDTOList = accountHistoryDTOList;
	}
	
}
