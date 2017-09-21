package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrgBindinfoDao;
import com.fangcang.titanjr.dto.bean.OrgBindInfoDTO;
import com.fangcang.titanjr.entity.TitanOrgBindinfo;
import com.fangcang.titanjr.entity.parameter.TitanOrgBindinfoParam;

public class TitanOrgBindinfoDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgBindinfoDao{
	@Override
	public boolean selectForPage(TitanOrgBindinfoParam condition,
			PaginationSupport<TitanOrgBindinfo> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanOrgBindinfoDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanOrgBindinfo entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgBindinfoDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanOrgBindinfo entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrgBindinfoDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delete(TitanOrgBindinfoParam param) throws DaoException {
		try {
			super.delete("com.fangcang.titanjr.dao.TitanOrgBindinfoDao.deleteEntity", param);
			return true;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public List<TitanOrgBindinfo> selectTitanOrgBindinfoByUserid(TitanOrgBindinfo titanOrgBindinfo)
			throws DaoException {
		try {
			return getSqlSession().selectList("com.fangcang.titanjr.dao.TitanOrgBindinfoDao.selectTitanOrgBindinfoByUserid", titanOrgBindinfo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanOrgBindinfo> selectActiveTitanOrgBindinfo(
			TitanOrgBindinfo titanOrgBindinfo) throws DaoException {
		try {
			return getSqlSession().selectList("com.fangcang.titanjr.dao.TitanOrgBindinfoDao.selectActiveTitanOrgBindinfo", titanOrgBindinfo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<OrgBindInfoDTO> queryOrgBindInfoDTO(OrgBindInfoDTO orgBindDTO)throws DaoException {
		try {
			return getSqlSession().selectList("com.fangcang.titanjr.dao.TitanOrgBindinfoDao.queryOrgBindInfoDTO", orgBindDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	

	
	@Override
	public List<TitanOrgBindinfo> queryTitanOrgBindinfo(TitanOrgBindinfo titanOrgBindinfo)
			throws DaoException {
		try {
			return getSqlSession().selectList("com.fangcang.titanjr.dao.TitanOrgBindinfoDao.queryTitanOrgBindinfo", titanOrgBindinfo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}