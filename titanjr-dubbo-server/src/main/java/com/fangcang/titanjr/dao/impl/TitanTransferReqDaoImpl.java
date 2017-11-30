package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanTransferReqDao;
import com.fangcang.titanjr.dto.bean.RepairTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.RepairTransferRequest;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.entity.parameter.TitanTransferReqParam;

public class TitanTransferReqDaoImpl extends GenericDAOMyBatisImpl implements TitanTransferReqDao{
	@Override
	public boolean selectForPage(TitanTransferReqParam condition,
			PaginationSupport<TitanTransferReq> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanTransferReqDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanTransferReq entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanTransferReqDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanTransferReq entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanTransferReqDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int updateTitanTransferReqByTransorderid(
			TitanTransferReq titanTransferReq) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanTransferReqDao.updateTitanTransferReqByTransorderid", titanTransferReq);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanTransferReq> queryTitanTransferReq(
			TitanTransferReqParam titanTransferReqParam) {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanTransferReqDao.selectTitanTransfer", titanTransferReqParam);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanTransferReq> queryTransferByOrderNo(String orderNo) {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanTransferReqDao.queryTransferByOrderNo", orderNo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int delete(TitanTransferReq entity) throws DaoException {
		try {
			return super.delete("com.fangcang.titanjr.dao.TitanTransferReqDao.delete", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}