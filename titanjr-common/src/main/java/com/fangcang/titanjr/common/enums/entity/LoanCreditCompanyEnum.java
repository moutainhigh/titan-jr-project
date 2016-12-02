package com.fangcang.titanjr.common.enums.entity;


public class LoanCreditCompanyEnum {
	public enum CertificateType{
		SFZ(1,"身份证"),HZ(2,"护照"),HKB(8,"户口本"),JGZ(21,"军官证"),SBZ(22,"士兵证"),HZX(23,"回乡证"),TWWLDL(24,"台湾居民往来大陆通行证"),XGWLDL(25,"香港居民往来大陆通行证"),QT(99,"其他");
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
}
