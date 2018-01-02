package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 
 * @author Jerry
 * @date 2017年12月20日 上午10:47:19
 */
public abstract class TLBaseRequest {
    
	/**
	 * 请求类型，内部使用，非渠道需要字段  @see RequestTypeEnum
	 */
	@NotBlank
    private String requestType;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}
