package com.fangcang.titanjr.dto.bean;

import com.fangcang.util.StringUtil;
/**
 * 基础业务为B 扩展业务待定 M70001棉庄订金支付
 * @author Administrator
 *
 */
public enum OrderTypeEnum {
	   OrderType_1("B1","基础业务为B");
		
		public String key;
		
		public String value;
		
		private OrderTypeEnum(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public static AmtTypeEnum getAmtTypeEnum(String atmcode){
			if(StringUtil.isValidString(atmcode)){
				for(AmtTypeEnum ate:AmtTypeEnum.values()){
					if(ate.getCode().equals(atmcode)){
						return ate;
					}
				}
			}
			return null;
		}
		
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

}
