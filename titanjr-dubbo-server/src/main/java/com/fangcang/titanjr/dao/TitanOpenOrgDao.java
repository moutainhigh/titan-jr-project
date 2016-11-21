package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.TitanOpenOrgDTO;
import com.fangcang.titanjr.entity.TitanOpenOrg;

/**
 * Created by fangdaikang on 2016/11/18.
 */
public interface TitanOpenOrgDao {

	int insert(TitanOpenOrg entity) throws DaoException;
	
	int delete(TitanOpenOrg entity) throws DaoException;
	
	List<TitanOpenOrgDTO> selectList(TitanOpenOrg entity) throws DaoException;
	
	List<String> selectMaxPrefix() throws DaoException;
}
