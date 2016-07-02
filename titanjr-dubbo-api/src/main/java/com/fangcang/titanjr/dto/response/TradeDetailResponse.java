package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanWithDrawDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;

public class TradeDetailResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//查询交易记录
	private PaginationSupport<TransOrderDTO> transOrders;
	
	//查询一条交易的明细


	public PaginationSupport<TransOrderDTO> getTransOrders() {
		return transOrders;
	}

	public void setTransOrders(PaginationSupport<TransOrderDTO> transOrders) {
		this.transOrders = transOrders;
	}
}
