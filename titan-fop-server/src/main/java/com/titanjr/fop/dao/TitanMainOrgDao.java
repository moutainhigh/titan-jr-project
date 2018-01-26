package com.titanjr.fop.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.titanjr.fop.dto.MainOrgDTO;
import com.titanjr.fop.entity.TitanMainOrg;

public interface TitanMainOrgDao {
	
	int insert(TitanMainOrg entity) throws DaoException;
	
	List<TitanMainOrg> queryList(MainOrgDTO mainOrgDTO) throws DaoException;
	
	int update(TitanMainOrg entity) throws DaoException;
}
