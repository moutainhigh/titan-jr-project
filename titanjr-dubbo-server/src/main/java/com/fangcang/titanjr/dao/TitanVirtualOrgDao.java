package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanVirtualOrg;
import com.fangcang.titanjr.entity.VirtualOrgRelation;

public interface TitanVirtualOrgDao {

	PaginationSupport<TitanVirtualOrg> selectForPage(TitanVirtualOrg condition, PaginationSupport<TitanVirtualOrg> paginationSupport) throws DaoException;
	
	public List<TitanVirtualOrg> queryVirtualOrgInfos(TitanVirtualOrg orgCode);

	public void addVirtualOrg(TitanVirtualOrg org);
	
	public List<VirtualOrgRelation> queryVirtualOrgRelationInfos(VirtualOrgRelation orgRelation);
	
	public List<VirtualOrgRelation> queryOrgBindCardHistoryList(VirtualOrgRelation orgRelation);
	
	public void addVirtualOrgRelation(VirtualOrgRelation orgRelation);
	
//	public void delVirtualOrgRelation(VirtualOrgRelation orgRelation);

}
