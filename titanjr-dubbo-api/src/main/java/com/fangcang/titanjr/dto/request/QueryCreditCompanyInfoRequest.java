package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 查询授信申请公司信息
 * @author luoqinglong
 *
 */
public class QueryCreditCompanyInfoRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8566907289176937273L;
	/**
	 * 授信申请联系人
	 */
	private String contactName;
	/**
	 * 公司名称
	 */
	private String name;
	/**
	 * 状态
	 */
	private Integer status;
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
