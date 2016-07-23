package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateCheckCodeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -667166837952551271L;
	
	private Integer codeId;
	
	/**
	 * 状态
	 */
	private Integer isactive;

	
	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public Integer getIsactive() {
		return isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}

}
