package com.fangcang.titanjr.dao;

import java.util.Date;
import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.entity.TitanUserBindInfo;
import com.fangcang.titanjr.entity.parameter.TitanUserBindInfoParam;

public interface TitanUserBindInfoDao {
	PaginationSupport<TitanUserBindInfo> selectForPage(TitanUserBindInfoParam condition, PaginationSupport<TitanUserBindInfo> paginationSupport) throws DaoException;
	int insert(TitanUserBindInfo entity) throws DaoException;
	int update(TitanUserBindInfo entity) throws DaoException;
	boolean delete(TitanUserBindInfoParam param) throws DaoException;
	/**
	 * 批量更新绑定状态
	 * @param isactive
	 * @param coopType 
	 * @param merchantcode 
	 * @return
	 * @throws DaoException
	 */
	int updateIsactiveBatch(Integer isactive,Integer coopType,String merchantcode,String modifior,Date modifytime) throws DaoException;
	
	List<TitanUserBindInfo> selectUserBindInfoByFcuserid(TitanUserBindInfo titanUserBindInfo)throws DaoException;
	
	/**
	 * @Description: 查询管理员的用户绑定信息 
	 *
	 * @author Jerry
	 * @date 2017年7月20日 下午5:45:58 
	 * @param @param userInfoQueryRequest
	 * @param @return 
	 * @return TitanUserBindInfo
	 */
	TitanUserBindInfo queryAdminUserBindInfo(UserInfoQueryRequest userInfoQueryRequest);
}