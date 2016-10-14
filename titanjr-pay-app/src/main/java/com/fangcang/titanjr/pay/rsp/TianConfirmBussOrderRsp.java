package com.fangcang.titanjr.pay.rsp;

public class TianConfirmBussOrderRsp extends TitanBaseRsp
{
	private String resultCode;

	private String resultMsg;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
