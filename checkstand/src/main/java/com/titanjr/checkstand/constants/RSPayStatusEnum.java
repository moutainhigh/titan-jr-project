package com.titanjr.checkstand.constants;

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
