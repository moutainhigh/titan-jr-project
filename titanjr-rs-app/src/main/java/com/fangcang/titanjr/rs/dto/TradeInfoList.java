package com.fangcang.titanjr.rs.dto;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class TradeInfoList {

	@XStreamImplicit(itemFieldName="tradeinfo")
	private List<TradeInfo> tradeInfoList;

	public List<TradeInfo> getTradeInfoList() {
		return tradeInfoList;
	}

	public void setTradeInfoList(List<TradeInfo> tradeInfoList) {
		this.tradeInfoList = tradeInfoList;
	}
	
}
