package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.OrderOperateInfoDTO;

public class TransOrderCreateResponse extends BaseResponseDTO{
	
	private String orderNo;
	
	private List<OrderOperateInfoDTO> orderOperateInfoList;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<OrderOperateInfoDTO> getOrderOperateInfoList() {
		return orderOperateInfoList;
	}

	public void setOrderOperateInfoList(List<OrderOperateInfoDTO> orderOperateInfoList) {
		this.orderOperateInfoList = orderOperateInfoList;
	}
	
}
