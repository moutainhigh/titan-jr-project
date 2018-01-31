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
import com.fangcang.titanjr.dao.CommonPayHistoryDao;
import com.fangcang.titanjr.dto.bean.gateway.CommonPayHistoryDTO;

/**
 * @author Jerry
 * @date 2017年8月8日 上午10:26:00  
 */
public class CommonPayHistoryDaoImpl extends GenericDAOMyBatisImpl implements CommonPayHistoryDao {

	@Override
	public boolean selectForPage(Map<String, String> condition,
			PaginationSupport<CommonPayHistoryDTO> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.CommonPayHistoryDao.selectForPage", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(CommonPayHistoryDTO entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.CommonPayHistoryDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(CommonPayHistoryDTO entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.CommonPayHistoryDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CommonPayHistoryDTO> selectCommonPayHistory(
			CommonPayHistoryDTO commonPayHistoryDTO) throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.CommonPayHistoryDao.selectCommonPayHistory", commonPayHistoryDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public CommonPayHistoryDTO getCommonPayHistory(
			CommonPayHistoryDTO commonPayHistoryDTO) throws DaoException {
		try {
			return (CommonPayHistoryDTO) super.selectOne("com.fangcang.titanjr.dao.CommonPayHistoryDao.getCommonPayHistory", 
					commonPayHistoryDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int delCommonPayHistory(CommonPayHistoryDTO commonPayHistoryDTO) throws DaoException {
		try {
			return super.delete("com.fangcang.titanjr.dao.CommonPayHistoryDao.delCommonPayHistory", commonPayHistoryDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
