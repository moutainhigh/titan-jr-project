package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.TitanVirtualOrgRelation;

public class GetVirtuaOrgBindCarListResponse  extends BaseRequestDTO {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public List<TitanVirtualOrgRelation> getvOrgRelationList() {
		return vOrgRelationList;
	}

	public void setvOrgRelationList(List<TitanVirtualOrgRelation> vOrgRelationList) {
		this.vOrgRelationList = vOrgRelationList;
	}

	private List<TitanVirtualOrgRelation> vOrgRelationList= null;

}
