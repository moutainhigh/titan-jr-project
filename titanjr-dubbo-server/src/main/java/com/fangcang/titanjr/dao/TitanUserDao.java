package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.entity.parameter.TitanUserParam;

import java.util.List;

public interface TitanUserDao {
	PaginationSupport<TitanUser> selectForPage(TitanUserParam condition, PaginationSupport<TitanUser> paginationSupport) throws DaoException;
	int insert(TitanUser entity) throws DaoException;
	int update(TitanUserParam entity) throws DaoException;
	
	TitanUser selectTitanUser(Integer tfsuserid) throws DaoException;

	List<UserInfoDTO> queryTitanUserList(UserInfoQueryRequest userInfoQueryRequest) throws DaoException;
	/**
	 * 分页获取用户和角色
	 * @param condition
	 * @param paginationSupport
	 * @return
	 * @throws DaoException
	 */
	PaginationSupport<UserInfoDTO> selectForRoleUserInfoPage(UserInfoQueryRequest userInfoQueryRequest) throws DaoException;
}