package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.parameter.TitanOrgParam;

import java.util.List;

public interface TitanOrgDao {
	
	PaginationSupport<TitanOrg> selectForPage(TitanOrgParam condition, PaginationSupport<TitanOrg> paginationSupport) throws DaoException;

	List<TitanOrg> queryTitanOrgList(FinancialOrganQueryRequest organQueryRequest);
	/**
	 * 结构总条数
	 * @param organQueryRequest
	 * @return
	 */
	int countOrg(FinancialOrganQueryRequest organQueryRequest);
	
	PaginationSupport<FinancialOrganDTO> queryTitanOrgForPage(FinancialOrganQueryRequest organQueryRequest, PaginationSupport<FinancialOrganDTO> paginationSupport);
	
	PaginationSupport<OrgCheckDTO> queryTitanOrgCheckForPage(FinancialOrganQueryRequest organQueryRequest, PaginationSupport<OrgCheckDTO> paginationSupport) throws DaoException;
	/**
	 * 查询一个实体
	 * @param condition
	 * @return
	 * @throws DaoException
	 */
	TitanOrg selectOne(TitanOrgParam condition) throws DaoException;
	int insert(TitanOrg entity) throws DaoException;
	int update(TitanOrg entity) throws DaoException;
}