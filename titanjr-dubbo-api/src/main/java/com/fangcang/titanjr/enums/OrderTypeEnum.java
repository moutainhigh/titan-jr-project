package com.fangcang.titanjr.enums;

import com.fangcang.util.StringUtil;
/**
 * 基础业务为B 扩展业务待定 M70001棉庄订金支付
 * @author Administrator
 *
 */
public enum OrderTypeEnum {
	   OrderType_1("B1","基础业务为B"),OrderType_BindCard("BX1","下单加绑卡"),OrderType_QUICK("B13","一定支付再次下单");
		
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
