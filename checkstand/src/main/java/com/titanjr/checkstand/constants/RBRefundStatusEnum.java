/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBErrorCodeEnum.java
 * @author Jerry
 * @date 2018年1月3日 下午2:16:20  
 */
package com.titanjr.checkstand.constants;

import com.fangcang.titanjr.common.enums.RefundStatusEnum;
import com.fangcang.util.StringUtil;

/**
 * @author Jerry
 * @date 2018年1月3日 下午2:16:20  
 */
public enum RBRefundStatusEnum {

	PROCESS("processing","处理中"),
	SUCCESS("completed","退款成功"),
	FAILED("failed","退款失败");
	
	private String key;
	private String desc;
	
	private RBRefundStatusEnum(String key, String desc) {
		this.key = key;
		this.desc = desc;
	}
	
	
	public static String trans2TitanStatus(String rbStatus){
		
		if(!StringUtil.isValidString(rbStatus)){
			return null;
		}
		
		if(rbStatus.equals(RBRefundStatusEnum.PROCESS)){
			return RefundStatusEnum.REFUND_IN_PROCESS.status.toString();
		}
		if(rbStatus.equals(RBRefundStatusEnum.SUCCESS)){
			return RefundStatusEnum.REFUND_SUCCESS.status.toString();
		}
		if(rbStatus.equals(RBRefundStatusEnum.FAILED)){
			return RefundStatusEnum.REFUND_FAILURE.status.toString();
		}
		return null;
		
	}
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
