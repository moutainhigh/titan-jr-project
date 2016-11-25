package com.fangcang.titanjr.common.enums;

/**
 * 授信申请单
 * @author luoqinglong
 * @date   2016年11月23日
 */
public class LoanCreditOrderEnum {
	
	public enum Status{
		DRAFT(1,"草稿"),TO_CHECK(2,"待审核"),NO_PASS(3,"审核未通过"),TO_REVIEW(4,"已提交复审"),PASS(5,"审核已通过");
		private Integer value;
		private String des;
		
		private Status(Integer value,String des){
			this.value = value;
			this.des = des;
		}
		public static Status  getEnumByValue(int value){
			Status entity = null;
			for(Status item : Status.values()) {
				if(item.value == value) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		
		public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}
		public String getDes() {
			return des;
		}
		
	}
}
