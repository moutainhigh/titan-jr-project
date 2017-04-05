package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.CoopDTO;
import com.fangcang.titanjr.entity.parameter.TitanCoopParam;

public interface TitanCoopDao {
	/**
	 * 查询一个实体
	 * @param param
	 * @return
	 */
	CoopDTO getEntity(TitanCoopParam param) throws DaoException;
}
