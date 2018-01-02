/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLReturnCodeEnum.java
 * @author Jerry
 * @date 2017年12月21日 下午2:39:18  
 */
package com.titanjr.checkstand.constants;

import com.fangcang.titanjr.common.enums.RefundStatusEnum;
import com.fangcang.util.StringUtil;

/**
 * @author Jerry
 * @date 2017年12月21日 下午2:39:18  
 */
public enum TLQrReturnCodeEnum {
	
	TRADE_SUCCESS("0000", "交易成功"),
	ORDER_NOT_PAY("3045", "订单未支付"),
	BALANCE_NOT_ENOUGH("3008", "余额不足"),
	TRADE_FAILD("3999", "交易失败"),
	TRADE_PROCESS("2008", "交易处理中"),
	TRADE_REPEAL("3050", "交易已撤销");
	
	private String code;
	private String remark;
	
	private TLQrReturnCodeEnum(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}
	
	public static TLQrReturnCodeEnum getEnumByCode(String code){
		if(!StringUtil.isValidString(code)){
			return null;
		}
		for (TLQrReturnCodeEnum em : TLQrReturnCodeEnum.values()) {
			if(em.code.equals(code)){
				return em;
			}
		}
		return null;
	}
	
	public static String getRsPayStatus(String tlPayStatus){
		if(!StringUtil.isValidString(tlPayStatus)){
			return null;
		}
		if(tlPayStatus.equals(TRADE_SUCCESS.getCode())){
			return RSPayStatusEnum.SUCCESS.getStatus();
		}else if(tlPayStatus.equals(TRADE_PROCESS.getCode())){
			return RSPayStatusEnum.PROCESS.getStatus();
		}else{
			return RSPayStatusEnum.FAILD.getStatus();
		}
	}
	
	public static String getRsRefundStatus(String tlRefundStatus){
		if(!StringUtil.isValidString(tlRefundStatus)){
			return null;
		}
		if(tlRefundStatus.equals(TRADE_SUCCESS.getCode())){
			return RefundStatusEnum.REFUND_SUCCESS.status.toString();
		}else if(tlRefundStatus.equals(TRADE_PROCESS.getCode())){
			return RefundStatusEnum.REFUND_IN_PROCESS.status.toString();
		}else{
			return RefundStatusEnum.REFUND_FAILURE.status.toString();
		}
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
