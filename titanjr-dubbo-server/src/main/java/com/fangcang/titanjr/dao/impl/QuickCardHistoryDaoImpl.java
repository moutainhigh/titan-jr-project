/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName QuickCardHistoryDaoImpl.java
 * @author Jerry
 * @date 2017年8月8日 上午10:26:00  
 */
package com.fangcang.titanjr.dao.impl;

import java.util.List;
import java.util.Map;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.QuickCardHistoryDao;
import com.fangcang.titanjr.dto.bean.gateway.QuickCardHistoryDTO;

/**
 * @author Jerry
 * @date 2017年8月8日 上午10:26:00  
 */
public class QuickCardHistoryDaoImpl extends GenericDAOMyBatisImpl implements QuickCardHistoryDao {

	@Override
	public boolean selectForPage(Map<String, String> condition,
			PaginationSupport<QuickCardHistoryDTO> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.QuickCardHistoryDao.selectForPage", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(QuickCardHistoryDTO entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.QuickCardHistoryDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(QuickCardHistoryDTO entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.QuickCardHistoryDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<QuickCardHistoryDTO> selectQuickCardHistory(
			QuickCardHistoryDTO quickCardHistoryDTO) throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.QuickCardHistoryDao.selectQuickCardHistory", quickCardHistoryDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
