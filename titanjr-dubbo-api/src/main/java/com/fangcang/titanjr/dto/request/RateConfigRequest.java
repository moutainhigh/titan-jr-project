package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * @ClassName: RateConfigRequest
 * @Description: 查询费率配置请求类
 * @author: wengxitao
 * @date: 2016年8月15日 上午10:09:24
 */
public class RateConfigRequest extends BaseRequestDTO 
{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 1L;
	

	/** 金服的用户ID */
	private String userId;

	private String deskId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

}
