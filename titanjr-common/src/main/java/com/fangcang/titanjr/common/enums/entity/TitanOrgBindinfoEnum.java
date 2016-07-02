package com.fangcang.titanjr.common.enums.entity;

/**
 * 机构绑定信息
 * @author luoqinglong
 * @2016年5月5日
 */
public class TitanOrgBindinfoEnum {
	/**
	 * 是否有效
	 * @author luoqinglong
	 * @2016年5月5日
	 */
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
	/**
	 * 绑定状态
	 * @author luoqinglong
	 * @2016年5月5日
	 */
	public enum BindStatus{
		NOT_BIND(0,"未绑定"),BIND(1,"已绑定");
		private int key;
		private String des;
		
		private BindStatus(int key,String des){
			this.key = key;
			this.des = des;
		}
		public static BindStatus  getEnumByKey(int key){
			BindStatus statusEnum = null;
			for(BindStatus item : BindStatus.values()) {
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
