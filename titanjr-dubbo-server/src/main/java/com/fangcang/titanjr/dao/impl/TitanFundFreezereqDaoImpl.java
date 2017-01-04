<<<<<<< HEAD
package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanFundFreezereqDao;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.request.UnFreezeRequest;
import com.fangcang.titanjr.entity.TitanFundFreezereq;
import com.fangcang.titanjr.entity.parameter.TitanFundFreezereqParam;

public class TitanFundFreezereqDaoImpl extends GenericDAOMyBatisImpl implements TitanFundFreezereqDao{
	@Override
	public boolean selectForPage(TitanFundFreezereqParam condition,
			PaginationSupport<TitanFundFreezereq> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanFundFreezereqDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanFundFreezereq entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanFundFreezereqDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanFundFreezereq entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanFundFreezereqDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<FundFreezeDTO> queryFundFreezeDTO(
			UnFreezeRequest unFreezeRequest) throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanFundFreezereqDao.selectUnFreezeOrders", unFreezeRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<FundFreezeDTO> queryFundFreezeDTOByOrderNo(String orderno)
			throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanFundFreezereqDao.queryFreezeOrder", orderno);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
=======
package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanFundFreezereqDao;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.request.UnFreezeRequest;
import com.fangcang.titanjr.entity.TitanFundFreezereq;
import com.fangcang.titanjr.entity.parameter.TitanFundFreezereqParam;

public class TitanFundFreezereqDaoImpl extends GenericDAOMyBatisImpl implements TitanFundFreezereqDao{
	@Override
	public boolean selectForPage(TitanFundFreezereqParam condition,
			PaginationSupport<TitanFundFreezereq> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanFundFreezereqDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanFundFreezereq entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanFundFreezereqDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanFundFreezereq entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanFundFreezereqDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<FundFreezeDTO> queryFundFreezeDTO(
			UnFreezeRequest unFreezeRequest) throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanFundFreezereqDao.selectUnFreezeOrders", unFreezeRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
>>>>>>> dev_loan
}