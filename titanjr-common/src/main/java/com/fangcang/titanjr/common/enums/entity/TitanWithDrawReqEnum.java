package com.fangcang.titanjr.common.enums.entity;


public class TitanWithDrawReqEnum {
	public enum Status{
		NEW(1,"新建"),DOING(2,"处理中"),SUCCESS(3,"成功"),FAILED(4,"失败");
		
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
