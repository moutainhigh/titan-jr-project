package com.fangcang.titanjr.common.enums;

public enum TransfertypeEnum {
	  BRANCH_TRANSFER("3","子账户转账");

	  private String key;
	  private String msg;

	  private TransfertypeEnum(String key,String msg){
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
