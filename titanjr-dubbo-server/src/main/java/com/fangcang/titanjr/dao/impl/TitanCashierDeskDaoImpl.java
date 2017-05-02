package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCashierDeskDao;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.entity.TitanCashierDesk;

public class TitanCashierDeskDaoImpl extends GenericDAOMyBatisImpl implements TitanCashierDeskDao{

	@Override
	public int saveCashierDesk(TitanCashierDesk entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanCashierDeskDao.saveCashierDesk", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int updateCashierDesk(TitanCashierDesk entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanCashierDeskDao.updateCashierDesk", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CashierDeskDTO> queryCashierDesk(CashierDeskQueryRequest cashierDeskQueryRequest) throws Exception {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanCashierDeskDao.queryCashierDesk", cashierDeskQueryRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	@Override
	public List<CashierDeskDTO> queryNotAssociatedLoanCashierdesk() throws Exception {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanCashierDeskDao.queryNotAssociatedLoanCashierdeskInfo");
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}