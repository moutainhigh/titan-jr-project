package com.fangcang.titanjr.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanUserDao;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.entity.parameter.TitanUserParam;

public class TitanUserDaoImpl extends GenericDAOMyBatisImpl implements TitanUserDao{
	@Override
	public PaginationSupport<TitanUser> selectForPage(TitanUserParam condition,PaginationSupport<TitanUser> paginationSupport)
			throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanUserDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return paginationSupport;
	}

	@Override
	public int insert(TitanUser entity) throws DaoException {
		try {
			return	super.insertEntity("com.fangcang.titanjr.dao.TitanUserDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanUserParam entity) {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanUserDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public TitanUser selectTitanUser(Integer tfsuserid) throws DaoException {
		try {
			return  getSqlSession().selectOne("com.fangcang.titanjr.dao.TitanUserDao.selectTitanUser", tfsuserid);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<UserInfoDTO> queryTitanUserList(UserInfoQueryRequest userInfoQueryRequest) throws DaoException {
		try {
			PaginationSupport<UserInfoDTO> paginationSupport = new PaginationSupport<UserInfoDTO>();
			userInfoQueryRequest.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
			paginationSupport = this.selectForRoleUserInfoPage(userInfoQueryRequest);
			return paginationSupport.getItemList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public PaginationSupport<UserInfoDTO>  selectForRoleUserInfoPage(UserInfoQueryRequest userInfoQueryRequest)
			throws DaoException {
		PaginationSupport<UserInfoDTO> paginationSupport = new PaginationSupport<UserInfoDTO>();
		try {
			PaginationSupport<Integer> userInfoPage = new PaginationSupport<Integer>();
			userInfoPage.setOrderBy(userInfoQueryRequest.getOrderBy());
			userInfoPage.setPageSize(userInfoQueryRequest.getPageSize());
			userInfoPage.setCurrentPage(userInfoQueryRequest.getCurrentPage());
			
			paginationSupport.setOrderBy(userInfoQueryRequest.getOrderBy());
			paginationSupport.setPageSize(userInfoQueryRequest.getPageSize());
			paginationSupport.setCurrentPage(userInfoQueryRequest.getCurrentPage());
			
			super.selectForPage("com.fangcang.titanjr.dao.TitanUserDao.queryTitanUserIdList", userInfoQueryRequest, userInfoPage);
			if(userInfoPage.getItemList().size()>0){
				List<Integer> userIdList = userInfoPage.getItemList();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("tfsUserIdList", userIdList);
				List<UserInfoDTO> list = super.selectList("com.fangcang.titanjr.dao.TitanUserDao.queryTitanUserCollectionList", param);
				paginationSupport.setItemList(list);
				paginationSupport.setTotalCount(userInfoPage.getTotalCount());
				paginationSupport.setTotalPage(userInfoPage.getTotalPage());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}
	
	
}