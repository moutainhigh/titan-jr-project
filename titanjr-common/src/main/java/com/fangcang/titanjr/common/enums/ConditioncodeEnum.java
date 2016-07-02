package com.fangcang.titanjr.common.enums;

public enum ConditioncodeEnum {
	  ADD_OEDER("1","落单之后转账"), NO_ADD_OEDER("2","不落单转账");

	  private String key;
	  private String msg;

	  private ConditioncodeEnum(String key,String msg){
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
