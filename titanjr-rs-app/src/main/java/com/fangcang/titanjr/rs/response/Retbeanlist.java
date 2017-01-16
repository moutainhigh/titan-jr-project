package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 用于解析融数返回的xml字符串
 * 封装单号信息
 * @author fangdaikang
 */
public class Retbeanlist {
 
	@XStreamImplicit(itemFieldName="com.rkylin.order.pojo.OrderInfo")
	private List<OrderOperateInfo> orderOperateInfo;

	public List<OrderOperateInfo> getOrderOperateInfo() {
		return orderOperateInfo;
	}

	public void setOrderOperateInfo(List<OrderOperateInfo> orderOperateInfo) {
		this.orderOperateInfo = orderOperateInfo;
	}

}
