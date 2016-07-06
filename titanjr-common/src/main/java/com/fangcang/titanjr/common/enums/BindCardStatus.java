package com.fangcang.titanjr.common.enums;

public enum BindCardStatus {
	
	BIND_FAIL(0,"绑卡失败"),BIND_SUCCESS(1,"绑定成功"),
	BIND_BINDING(2,"绑卡申请审核中");
	 
	 public int status;
	 
	 public String msg;
	 
	 
	 private BindCardStatus(int status,String msg){
		 this.status=status;
		 this.msg=msg;
	 }
}
