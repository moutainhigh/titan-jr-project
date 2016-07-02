package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
/**
 * 分页员工信息
 * @author luoqinglong
 * @2016年5月20日
 */
public class RoleUserInfoPageResponse extends BaseResponseDTO{
	
	private static final long serialVersionUID = 4447620333685110560L;
	PaginationSupport<UserInfoDTO> paginationSupport;
	
	public PaginationSupport<UserInfoDTO> getPaginationSupport() {
		return paginationSupport;
	}
	public void setPaginationSupport(
			PaginationSupport<UserInfoDTO> paginationSupport) {
		this.paginationSupport = paginationSupport;
	}
	
	
}
