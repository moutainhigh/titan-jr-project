package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.RepairTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;

public class RepairTransferResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<RepairTransferDTO> titanTransferDTOList;

	public List<RepairTransferDTO> getTitanTransferDTOList() {
		return titanTransferDTOList;
	}

	public void setTitanTransferDTOList(List<RepairTransferDTO> titanTransferDTOList) {
		this.titanTransferDTOList = titanTransferDTOList;
	}

}
