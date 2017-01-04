package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanRoleDTO;

public class PermissionResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TitanRoleDTO> titanRoleDtoList;
	public List<TitanRoleDTO> getTitanRoleDtoList() {
		return titanRoleDtoList;
	}
	public void setTitanRoleDtoList(List<TitanRoleDTO> titanRoleDtoList) {
		this.titanRoleDtoList = titanRoleDtoList;
	}

}
