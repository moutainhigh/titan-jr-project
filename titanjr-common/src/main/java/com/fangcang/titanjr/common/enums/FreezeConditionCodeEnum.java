package com.fangcang.titanjr.common.enums;

public enum FreezeConditionCodeEnum {
	  MAIN_ORDER("1","主业务单"), BTANCH_ORDER("0","业务子单"),NO_ORDER("2","无业务单");
	  private String key;
	  private String msg;

	  private FreezeConditionCodeEnum(String key,String msg){
	      this.key = key;
	      this.msg = msg;
	  }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
