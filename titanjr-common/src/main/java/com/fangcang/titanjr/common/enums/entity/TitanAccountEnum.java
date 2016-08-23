package com.fangcang.titanjr.common.enums.entity;

/***
 * 账户
 * @author luoqinglong
 * @2016年5月13日
 */
public class TitanAccountEnum {
	@Deprecated
	public enum Status{
		NORMAL(1,"正常"),FORZEN(2,"冻结中");
		private int key;
		private String des;
		private Status(int key,String des){
			this.key = key;
			this.des = des;
		}
		
		public static Status  getEnumByKey(int key){
			Status statusEnum = null;
			for(Status item : Status.values()) {
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
