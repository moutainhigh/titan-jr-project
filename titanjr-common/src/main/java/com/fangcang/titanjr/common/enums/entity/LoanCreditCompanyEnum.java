package com.fangcang.titanjr.common.enums.entity;

/**
 * 企业资料
 * @author luoqinglong
 * @date   2016年12月3日
 */
public class LoanCreditCompanyEnum {
	/**
	 * 个人证件类型
	 * @author luoqinglong
	 * @date   2016年12月3日
	 */
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
	/***
	 * 企业规模类型
	 * @author luoqinglong
	 * @date   2016年12月2日
	 */
	public enum OrgSize{
		SIZE_1_50(1,"1-50"),SIZE_51_100(2,"51-100"),SIZE_101_500(3,"101-500"),SIZE_501_1000(4,"501-1000"),SIZE_1000(5,"1000以上");
		
		private int sizeType;
		private String des;
		private OrgSize(int sizeType,String des){
			this.sizeType = sizeType;
			this.des = des;
		}
		
		public static OrgSize  getEnumBySizeType(int sizeType){
			OrgSize entity = null;
			for(OrgSize item : OrgSize.values()) {
				if(item.sizeType == sizeType) {
					entity = item;
					break;
				}
			}
			return entity;
		}

		public int getSizeType() {
			return sizeType;
		}

		public void setSizeType(int sizeType) {
			this.sizeType = sizeType;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
	}
	/**
	 * 公司类型
	 * @author luoqinglong
	 * @date   2016年12月3日
	 */
	public enum CompanyType{
		YXZR_1(1,"有限责任公司"),GFYX_2(2,"股份有限公司"),NZ_3(3,"内资"),GYQZ_4(4,"国有全资"),JZQZ_5(5,"集资全资"),GWTZ_6(6,"国外投资股份有限公司"),QT_99(99,"其他");
		private int type;
		private String des;
		
		private CompanyType(int type,String des){
			this.type = type;
			this.des = des;
		}
		
		public static CompanyType  getEnumByType(int type){
			CompanyType entity = null;
			for(CompanyType item : CompanyType.values()) {
				if(item.type == type) {
					entity = item;
					break;
				}
			}
			return entity;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
		
	
	}
}
