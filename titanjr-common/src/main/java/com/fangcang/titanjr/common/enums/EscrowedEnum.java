package com.fangcang.titanjr.common.enums;

import com.fangcang.util.StringUtil;

public enum EscrowedEnum {

	  ESCROWED_PAYMENT("0","担保支付"), NO_ESCROWED_PAYMENT("1","不担保支付");

	  private String key;
	  private String msg;

	  private EscrowedEnum(String key,String msg){
	      this.key = key;
	      this.msg = msg;
	  }
	  
	  public static EscrowedEnum getEscrowedEnumByKey(String key){
		  if(StringUtil.isValidString(key)){
			  for(EscrowedEnum escrowedEnum:EscrowedEnum.values()){
				  if(escrowedEnum.getKey().equals(key)){
					  return escrowedEnum;
				  }
			  }
		  }
          return null;		  
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
