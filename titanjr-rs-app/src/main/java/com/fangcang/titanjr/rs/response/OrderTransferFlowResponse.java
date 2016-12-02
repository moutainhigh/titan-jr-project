package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.Rop.api.domain.TradeInfoResponse;
import com.fangcang.titanjr.rs.dto.TradeInfo;
import com.fangcang.titanjr.rs.dto.TradeInfoList;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by zhaoshan on 2016/4/15.
 */
@XStreamAlias("wheatfield_order_service_multitransfer_query_response")
public class OrderTransferFlowResponse extends BaseResponse{
	
	private String is_success;
	
	private String msg;
	
	//返回码
	private String retcode;
	
	//返回信息
	private String retmsg;
	
	//查询到的返回结果
	private List<TradeInfo> tradeInfoList;

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<TradeInfo> getTradeInfoList() {
		return tradeInfoList;
	}

	public void setTradeInfoList(List<TradeInfo> tradeInfoList) {
		this.tradeInfoList = tradeInfoList;
	}
	
}
