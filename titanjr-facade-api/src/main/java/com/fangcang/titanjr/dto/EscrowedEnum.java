package com.fangcang.titanjr.dto;

public enum EscrowedEnum {
	Escrowed("0","冻结"),NotEscrowed("1","不冻结");
	
	public String key;
	
	public String value;
	
	private EscrowedEnum(String key,String value){
		this.key=key;
		this.value=value;
	}
}
