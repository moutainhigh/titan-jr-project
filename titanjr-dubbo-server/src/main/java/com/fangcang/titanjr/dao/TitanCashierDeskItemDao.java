package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;
import com.fangcang.titanjr.entity.TitanCashierDeskItem;


public interface TitanCashierDeskItemDao {

	int saveCashierDeskItem(TitanCashierDeskItem cashierDeskItem) throws DaoException;

	int updateCashierDeskItem(TitanCashierDeskItem entity) throws DaoException;
	
	List<TitanCashierDeskItem> queryCashierDeskItems(TitanCashierDeskItem entity);
	
	/**
	 * 删除所有交易平台收银台的支付方式
	 * @author Jerry
	 * @date 2017年10月26日 下午5:27:40
	 */
	public int delCashierdeskItemForTradingPlatform();
	
	/**
	 * 查询交易平台收银台deskid
	 * @author Jerry
	 * @date 2017年10月26日 下午2:33:46
	 */
	public List<CashierDeskDTO> queryTradingPlatformCashierdesk();
}