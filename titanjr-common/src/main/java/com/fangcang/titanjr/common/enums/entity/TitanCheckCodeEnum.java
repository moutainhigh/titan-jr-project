package com.fangcang.titanjr.common.enums.entity;


public class TitanCheckCodeEnum {
	public enum Isactive{
		NOT_ACTIVE(0,"无效"),ACTIVE(1,"有效");
		private int key;
		private String des;
		
		private Isactive(int key,String des){
			this.key = key;
			this.des = des;
		}
		public static Isactive  getEnumByKey(int key){
			Isactive statusEnum = null;
			for(Isactive item : Isactive.values()) {
				if(item.key == key) {
					statusEnum = item;
					break;
				}
			}
			return statusEnum;
		}
		public int getKey() {
			return key;
		}
		public String getDes() {
			return des;
		}
		
	}
	
}
