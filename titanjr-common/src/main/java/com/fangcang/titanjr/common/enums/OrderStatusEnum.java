package com.fangcang.titanjr.common.enums;

public enum OrderStatusEnum {
	
	Status_1("1","处理中"), Status_2("2","交易成功"),
    Status_3("3","已冻结"), Status_4("4","付款失败"),Status_5("5","失效"),;

    private String status;
    private String statusMsg;

    private OrderStatusEnum(String status,String statusMsg){
        this.status = status;
        this.statusMsg = statusMsg;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
    
}
