package com.fangcang.titanjr.common.enums;

public enum ReqstatusEnum {
	RECHARFE_IN_PROCESS(1,"处理中"),
	RECHARFE_SUCCESS(2,"成功"), RECHARFE_FAIL(3,"失败");

    private int status;
    private String statusMsg;

    private ReqstatusEnum(int status,String statusMsg){
        this.status = status;
        this.statusMsg = statusMsg;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
}
