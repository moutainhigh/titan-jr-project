package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.OrgBindInfoDTO;
//import com.fangcang.titanjr.common.exception.GlobalDaoException;
import com.fangcang.titanjr.entity.TitanOrgBindinfo;
import com.fangcang.titanjr.entity.parameter.TitanOrgBindinfoParam;

public interface TitanOrgBindinfoDao {
	boolean selectForPage(TitanOrgBindinfoParam condition, PaginationSupport<TitanOrgBindinfo> paginationSupport) throws DaoException;
	int insert(TitanOrgBindinfo entity) throws DaoException;
	int update(TitanOrgBindinfo entity) throws DaoException;
	boolean delete(TitanOrgBindinfoParam param) throws DaoException;
	
	List<TitanOrgBindinfo> selectTitanOrgBindinfoByUserid(TitanOrgBindinfo titanOrgBindinfo)throws DaoException;
	
	List<TitanOrgBindinfo> selectActiveTitanOrgBindinfo(TitanOrgBindinfo titanOrgBindinfo)throws DaoException;

	List<OrgBindInfoDTO> queryOrgBindInfoDTO(OrgBindInfoDTO orgBindDTO);
	
	List<TitanOrgBindinfo> queryTitanOrgBindinfo(TitanOrgBindinfo titanOrgBindinfo);
}