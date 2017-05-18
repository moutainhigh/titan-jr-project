package com.fangcang.titanjr.common.enums;

public enum RefundStatusEnum {

	REFUND_IN_PROCESS(0,"退款中"),REFUND_PROCESS_FAILURE(1,"审核失败"),REFUND_SUCCESS(2,"退款成功"),REFUND_FAILURE(3,"退款失败"),REFUND_AFAINST(4,"退款冲销");
	
	public Integer status;
	
	public String msg;
	
	private RefundStatusEnum(Integer status,String msg ){
		this.status = status;
		this.msg = msg;
	}
	
	public static RefundStatusEnum getRefundStatusEnumByStatus(Integer status){
		if(null == status){
			return null;
		}
		
		for(RefundStatusEnum st :RefundStatusEnum.values()){
			if(st.status == status){
				return st;
			}
		}
		return null;
	}
	public String toString(){
		return "RefundStatusEnum{status:"+this.status+",msg:\""+this.msg+"\"}";
	}
	
}
