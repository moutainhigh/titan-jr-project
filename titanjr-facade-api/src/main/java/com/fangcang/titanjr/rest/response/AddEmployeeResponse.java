package com.fangcang.titanjr.rest.response;

import com.fangcang.titanjr.response.BaseResponse;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AddEmployeeResponse", description = "添加员工结果")
public class AddEmployeeResponse extends BaseResponse {
	
	@ApiModelProperty(value = "金融员工id")
	private String tfsUserId;

	public String getTfsUserId() {
		return tfsUserId;
	}

	public void setTfsUserId(String tfsUserId) {
		this.tfsUserId = tfsUserId;
	}
	
	
}
