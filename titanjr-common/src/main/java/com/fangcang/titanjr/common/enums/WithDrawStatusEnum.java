package com.fangcang.titanjr.common.enums;

public enum WithDrawStatusEnum {
	  WithDraw_DOING(1,"处理中"),WithDraw_FAILED(2,"失败"),WithDraw_SUCCESSED(3,"成功");

	  private Integer key;
	  private String msg;

	  private WithDrawStatusEnum(Integer key,String msg){
	      this.key = key;
	      this.msg = msg;
	  }

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
