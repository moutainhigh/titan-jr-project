package com.fangcang.titanjr.common.enums.entity;

import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum.CertificateType;

/**
 * 授信申请单
 * @author luoqinglong
 * @date   2016年11月29日
 */
public class LoanCreditOrderEnum {
	
	public enum AssureType{
		PERSON(1,"个人"),ENTERPRISE(2,"企业");
		int type;
		String des;
		private AssureType(int type,String des){
			this.type = type;
			this.des = des;
		}
		
		public static AssureType  getEnumByType(int type){
			AssureType entity = null;
			for(AssureType item : AssureType.values()) {
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
