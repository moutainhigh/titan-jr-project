package com.fangcang.titanjr.rs.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.rs.dao.SysconfigDao;
import com.fangcang.titanjr.rs.entity.Sysconfig;

public class SysconfigDaoImpl extends SqlSessionDaoSupport implements
		SysconfigDao {

	@Override
	public List<Sysconfig> query(Map<String, Object> condition) {
		try {
			return getSqlSession().selectList(
					"com.fangcang.titanjr.rs.dao.SysconfigDao.query",condition);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
