package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanHelpDao;
import com.fangcang.titanjr.dto.bean.HelpWordDTO;
import com.fangcang.titanjr.dto.bean.TitanHelpDTO;
import com.fangcang.titanjr.entity.TitanHelp;
import com.fangcang.titanjr.entity.parameter.TitanHelpParam;

public class TitanHelpDaoImpl extends GenericDAOMyBatisImpl implements TitanHelpDao {

	@Override
	public PaginationSupport<TitanHelpDTO> selectForPage(TitanHelpParam condition,
			PaginationSupport<TitanHelpDTO> paginationSupport) throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanHelpDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}

	@Override
	public PaginationSupport<HelpWordDTO> selectForPageSearch(
			TitanHelpParam condition,
			PaginationSupport<HelpWordDTO> paginationSupport) throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanHelpDao.searchList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}

	@Override
	public int insert(TitanHelp entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanHelpDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanHelp entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanHelpDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
