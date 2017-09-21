package com.fangcang.titanjr.dto.response;

import java.util.List;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.OrderOperateInfoDTO;

public class TransOrderCreateResponse extends BaseResponseDTO{
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = -8026898256332404137L;

	private String orderNo;
	
	private Integer transId;
	
	//是否可用余额支付
	private boolean canAccountBalance;
	
	public Integer getTransId() {
		return transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}

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

	public boolean isCanAccountBalance() {
		return canAccountBalance;
	}

	public void setCanAccountBalance(boolean canAccountBalance) {
		this.canAccountBalance = canAccountBalance;
	}
	
}
