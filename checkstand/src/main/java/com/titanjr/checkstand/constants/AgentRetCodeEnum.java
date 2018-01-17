/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentRetCodeEnum.java
 * @author Jerry
 * @date 2018年1月12日 上午11:48:33  
 */
package com.titanjr.checkstand.constants;

/**
 * 账户交易-查询结果返回码
 * @author Jerry
 * @date 2018年1月12日 上午11:48:33  
 */
public enum AgentRetCodeEnum {
	
	RET_CODE_PROCESS("00", "处理中"),
	RET_CODE_SUCCESS("01", "成功"),
	RET_CODE_FAILD("02", "失败"),
	RET_CODE_NOT_FIND("03", "没有对应的交易");
	
	public String code;
	public String remark;
	
	private AgentRetCodeEnum(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}

}
