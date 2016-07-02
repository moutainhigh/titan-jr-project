package com.fangcang.titanjr.rs.response;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class OrderOperateResponse extends BaseResponse{

	//创建时返回单号
	private String orderid;
	
	private List<OrderOperateInfo> orderOperateInfoList;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public List<OrderOperateInfo> getOrderOperateInfoList() {
		return orderOperateInfoList;
	}

	public void setOrderOperateInfoList(List<OrderOperateInfo> orderOperateInfoList) {
		this.orderOperateInfoList = orderOperateInfoList;
	}

	
}
