package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.entity.TitanUser;

import java.util.List;

/**
 * 
 * @author luoqinglong
 * @2016年7月6日
 */
public class UserInfoPageResponse extends BaseResponseDTO{

	PaginationSupport<TitanUser> titanUserPaginationSupport;

	public PaginationSupport<TitanUser> getTitanUserPaginationSupport() {
		return titanUserPaginationSupport;
	}

	public void setTitanUserPaginationSupport(
			PaginationSupport<TitanUser> titanUserPaginationSupport) {
		this.titanUserPaginationSupport = titanUserPaginationSupport;
	}
    
}
