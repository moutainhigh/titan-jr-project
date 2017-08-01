package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class CancelOrgBindRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3106209875011152953L;
	/**
     * 操作人，可为空，一般需要有值
     */
    private String operator;
    private String orgCode;
    
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
    
}
