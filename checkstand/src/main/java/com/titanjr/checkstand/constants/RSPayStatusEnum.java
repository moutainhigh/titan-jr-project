package com.titanjr.checkstand.constants;

import com.fangcang.util.StringUtil;

/**
 * 融数支付状态
 * @author Jerry
 * @date 2017年12月7日 下午5:18:56
 */
public enum RSPayStatusEnum {

	PROCESS("0","受理中"),
    UNPAY("1","未支付"),
    INPAY("2","支付中"),
    SUCCESS("3","支付成功"),
    FAILD("4","支付失败");
    
    private String status;
    private String remark;
	private RSPayStatusEnum(String status, String remark) {
		this.status = status;
		this.remark = remark;
	}
	
	/**
	 * 把融宝订单状态转换为收银台需要的状态
	 * 融宝：completed交易完成，failed支付失败，processing交易处理中，wait等待买家付款，closed订单关闭
	 * @author Jerry
	 * @date 2018年1月27日 下午4:44:05
	 */
	public static String convertRBPayStatus2RS(String rbstatus){
		if(!StringUtil.isValidString(rbstatus)){
			return null;
		}
		if("completed".equals(rbstatus)){
			return RSPayStatusEnum.SUCCESS.status;
		}
		if("failed".equals(rbstatus) || "closed".equals(rbstatus)){
			return RSPayStatusEnum.FAILD.status;
		}
		if("processing".equals(rbstatus) || "wait".equals(rbstatus)){
			return RSPayStatusEnum.PROCESS.status;
		}
		return null;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
