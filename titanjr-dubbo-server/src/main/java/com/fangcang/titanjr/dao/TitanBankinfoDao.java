package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.BankInfoDTO;
import com.fangcang.titanjr.dto.request.BankInfoQueryRequest;
import com.fangcang.titanjr.entity.TitanBankinfo;
import com.fangcang.titanjr.entity.TitanCityInfo;
import com.fangcang.titanjr.entity.parameter.TitanBankinfoParam;

public interface TitanBankinfoDao {

	List<BankInfoDTO> queryBankInfoList(BankInfoQueryRequest bankInfoQueryRequest) throws DaoException;

	int insert(TitanBankinfo entity) throws DaoException;
	int update(TitanBankinfo entity) throws DaoException;
	
	int insertBatch(List<TitanBankinfo> titanBankinfo);
	
	int delete()throws DaoException;
}