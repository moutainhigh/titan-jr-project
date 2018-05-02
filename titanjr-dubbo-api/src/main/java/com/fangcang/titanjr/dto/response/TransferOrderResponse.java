package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.RepairTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;

public class TransferOrderResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<RepairTransferDTO> repairTransferDTOListList;

	public List<RepairTransferDTO> getRepairTransferDTOListList() {
		return repairTransferDTOListList;
	}

	public void setRepairTransferDTOListList(
			List<RepairTransferDTO> repairTransferDTOListList) {
		this.repairTransferDTOListList = repairTransferDTOListList;
	}

}
