package com.fangcang.titanjr.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrgDao;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.parameter.TitanOrgParam;

public class TitanOrgDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgDao{
	@Override
	public PaginationSupport<TitanOrg> selectForPage(TitanOrgParam condition,
			PaginationSupport<TitanOrg> paginationSupport)
			throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanOrgDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}

	@Override
	public List<TitanOrg> queryTitanOrgList(FinancialOrganQueryRequest organQueryRequest) {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanOrgDao.queryTitanOrgList", organQueryRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int countOrg(FinancialOrganQueryRequest organQueryRequest) {
		return (Integer)super.selectOne("com.fangcang.titanjr.dao.TitanOrgDao.countOrg",organQueryRequest);
	}

	@Override
	public TitanOrg selectOne(TitanOrgParam condition) throws DaoException {
		try {
			PaginationSupport<TitanOrg> paginationSupport = new PaginationSupport<TitanOrg>();
			super.selectForPage("com.fangcang.titanjr.dao.TitanOrgDao.queryList", condition, paginationSupport);
			if(paginationSupport.getItemList()!=null&&paginationSupport.getItemList().size()>0){
				return paginationSupport.getItemList().get(0);
			}
			
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return null;
	}

	@Override
	public PaginationSupport<FinancialOrganDTO> queryTitanOrgForPage(FinancialOrganQueryRequest organQueryRequest,
										PaginationSupport<FinancialOrganDTO> paginationSupport)
			throws DaoException {

		try {
			
			PaginationSupport<Integer> orgIdPage = new PaginationSupport<Integer>();
			orgIdPage.setOrderBy(paginationSupport.getOrderBy());
			orgIdPage.setPageSize(paginationSupport.getPageSize());
			orgIdPage.setCurrentPage(paginationSupport.getCurrentPage());
			super.selectForPage("com.fangcang.titanjr.dao.TitanOrgDao.queryOrgIdList", organQueryRequest, orgIdPage);
			if(orgIdPage.getItemList().size()>0){
				List<Integer> userIdList = orgIdPage.getItemList();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("orgIdList", userIdList);
				List<FinancialOrganDTO> list = super.selectList("com.fangcang.titanjr.dao.TitanOrgDao.queryOrgCollectionList", param);
				paginationSupport.setItemList(list);
				paginationSupport.setTotalCount(orgIdPage.getTotalCount());
				paginationSupport.setTotalPage(orgIdPage.getTotalPage());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}

		return paginationSupport;
	}

	@Override
	public PaginationSupport<FinancialOrganDTO> queryBaseTitanOrgForPage(FinancialOrganQueryRequest organQueryRequest,
										PaginationSupport<FinancialOrganDTO> paginationSupport)
			throws DaoException {

		try {
			
			PaginationSupport<Integer> orgIdPage = new PaginationSupport<Integer>();
			orgIdPage.setOrderBy(paginationSupport.getOrderBy());
			orgIdPage.setPageSize(paginationSupport.getPageSize());
			orgIdPage.setCurrentPage(paginationSupport.getCurrentPage());
			super.selectForPage("com.fangcang.titanjr.dao.TitanOrgDao.queryBaseOrgIdList", organQueryRequest, orgIdPage);
			if(orgIdPage.getItemList().size()>0){
				List<Integer> userIdList = orgIdPage.getItemList();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("orgIdList", userIdList);
				List<FinancialOrganDTO> list = super.selectList("com.fangcang.titanjr.dao.TitanOrgDao.queryOrgCollectionList", param);
				paginationSupport.setItemList(list);
				paginationSupport.setTotalCount(orgIdPage.getTotalCount());
				paginationSupport.setTotalPage(orgIdPage.getTotalPage());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}

		return paginationSupport;
	}
	
	@Override
	public PaginationSupport<OrgCheckDTO> queryTitanOrgCheckForPage(
			FinancialOrganQueryRequest organQueryRequest,
			PaginationSupport<OrgCheckDTO> paginationSupport) throws DaoException{
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanOrgDao.queryOrgCheckList",
					organQueryRequest,paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}

	@Override
	public int insert(TitanOrg entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanOrg entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrgDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanOrg> queryTitanOrgListByUserId(
			FinancialOrganQueryRequest organQueryRequest) {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanOrgDao.queryTitanOrgListByUserId", organQueryRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}