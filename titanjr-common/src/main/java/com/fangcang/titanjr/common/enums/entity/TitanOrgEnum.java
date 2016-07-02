package com.fangcang.titanjr.common.enums.entity;

/**
 * 机构表
 * @author luoqinglong
 * @2016年5月5日
 */
public class TitanOrgEnum {
	/**
	 * 身份认证类别
	 * @author luoqinglong
	 * @2016年5月18日
	 */
	public enum CertificateType{
		SFZ(0,"身份证"),HZ(1,"护照"),JGZ(2,"军官证"),SBZ(3,"士兵证"),HXZ(4,"回乡证"),HKB(5,"户口本"),WGHZ(6,"外国护照"),QT(7,"其它");
		private int key;
		private String des;
		
		private CertificateType(int key,String des){
			this.key = key;
			this.des = des;
		}
		public static CertificateType  getEnumByKey(int key){
			CertificateType entity = null;
			for(CertificateType item : CertificateType.values()) {
				if(item.key == key) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		public int getKey() {
			return key;
		}
		public String getDes() {
			return des;
		}
	}
	
	public enum UserType{
		ENTERPRISE(1,"企业用户"),PERSONAL(2,"个人用户");
		private int key;
		private String des;
		
		private UserType(int key,String des){
			this.key = key;
			this.des = des;
		}
		public static UserType  getEnumByKey(int key){
			UserType entity = null;
			for(UserType item : UserType.values()) {
				if(item.key == key) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		public int getKey() {
			return key;
		}
		public String getDes() {
			return des;
		}
	}
	/***
	 * 账户状态（1：生效，2：冻结，3：注销 不填默认为1）
	 * @author luoqinglong
	 * @2016年5月18日
	 */
	public enum StatusId{
		AVAILABLE(1,"有效"),FREEZE(2,"冻结中"),NOT_AVAILABLE(3,"注销");
		private int key;
		private String des;
		
		private StatusId(int key,String des){
			this.key = key;
			this.des = des;
		}
		public static StatusId  getEnumByKey(int key){
			StatusId entity = null;
			for(StatusId item : StatusId.values()) {
				if(item.key == key) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		public int getKey() {
			return key;
		}
		public String getDes() {
			return des;
		}
	}
	
}
