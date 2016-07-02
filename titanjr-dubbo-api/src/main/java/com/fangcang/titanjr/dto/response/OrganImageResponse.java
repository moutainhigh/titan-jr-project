package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;

public class OrganImageResponse extends BaseResponseDTO{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5402593288684871689L;
	private List<OrgImageInfo> orgImageInfoList;
	public List<OrgImageInfo> getOrgImageInfoList() {
		return orgImageInfoList;
	}
	public void setOrgImageInfoList(List<OrgImageInfo> orgImageInfoList) {
		this.orgImageInfoList = orgImageInfoList;
	}
	
}
