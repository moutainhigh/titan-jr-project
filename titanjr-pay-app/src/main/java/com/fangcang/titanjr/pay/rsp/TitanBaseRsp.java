package com.fangcang.titanjr.pay.rsp;

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;

public class TitanBaseRsp {
	public static final int SUCCESS = 0;

	private TitanMsgCodeEnum result = TitanMsgCodeEnum.TITAN_SUCCESS;

	public TitanBaseRsp() {

	}

	public TitanMsgCodeEnum getResult() {
		return result;
	}

	public void setResult(TitanMsgCodeEnum result) {
		this.result = result;
	}

	public TitanBaseRsp(TitanMsgCodeEnum result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return result.getCode() == SUCCESS;
	}
}
