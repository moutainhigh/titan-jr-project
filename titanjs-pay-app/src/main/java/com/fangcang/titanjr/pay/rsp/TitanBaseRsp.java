package com.fangcang.titanjr.pay.rsp;

public class TitanBaseRsp {
	public static final int SUCCESS = 0;

	private int resultCode;

	private String resultMsg;

	public int getResultCode() {
		return resultCode;
	}

	public boolean isSuccess() {
		return this.resultCode == TitanBaseRsp.SUCCESS;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
