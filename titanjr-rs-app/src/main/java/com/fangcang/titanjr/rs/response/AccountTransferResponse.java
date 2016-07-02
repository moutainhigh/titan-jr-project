package com.fangcang.titanjr.rs.response;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class AccountTransferResponse extends BaseResponse{
	//流水单号
	private String orderid;
	//操作返回编码
	private String retcode;
	//操作返回信息
    private String retmsg;
    //操作是否成功
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
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
	 
}
