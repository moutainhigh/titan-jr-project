package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;

public class QueryBankCardBindInfoResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BankCardInfoDTO> bankCardInfoDTOList;

	public List<BankCardInfoDTO> getBankCardInfoDTOList() {
		return bankCardInfoDTOList;
	}

	public void setBankCardInfoDTOList(List<BankCardInfoDTO> bankCardInfoDTOList) {
		this.bankCardInfoDTOList = bankCardInfoDTOList;
	}
	
}
