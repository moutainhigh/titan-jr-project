package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.BankCardDTO;

public class BindBankCardResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BankCardDTO> bankCardDTO;

	public List<BankCardDTO> getBankCardDTO() {
		return bankCardDTO;
	}

	public void setBankCardDTO(List<BankCardDTO> bankCardDTO) {
		this.bankCardDTO = bankCardDTO;
	}

}
