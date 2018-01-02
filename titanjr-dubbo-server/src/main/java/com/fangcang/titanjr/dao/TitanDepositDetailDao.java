package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanDepositDetail;

/**
 * 备付金资金变动
 * @author luoqinglong
 * @date 2017年12月28日
 */
public interface TitanDepositDetailDao {
	int insert(TitanDepositDetail entity) throws DaoException;
}
