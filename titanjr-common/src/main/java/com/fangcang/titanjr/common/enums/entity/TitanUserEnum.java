package com.fangcang.titanjr.common.enums.entity;


public class TitanUserEnum {
	/**
	 * 用户状态
	 * @author luoqinglong
	 * @2016年5月21日
	 */
	public enum Status{
		NOT_AVAILABLE(0,"失效"),AVAILABLE(1,"有效"),FREEZE(2,"冻结中");
		private Integer key;
		private String des;
		private Status(Integer key,String des){
			this.key = key;
			this.des = des;
		}
		public static Status  getEnumByKey(int key){
			Status entity = null;
			for(Status item : Status.values()) {
				if(item.key == key) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		public Integer getKey() {
			return key;
		}
		public String getDes() {
			return des;
		}
		
	}
	
}
