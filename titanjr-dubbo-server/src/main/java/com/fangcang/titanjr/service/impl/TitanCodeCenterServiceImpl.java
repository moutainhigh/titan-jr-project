package com.fangcang.titanjr.service.impl;

import javax.annotation.Resource;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dao.TitanAccountCodeDao;
import com.fangcang.titanjr.dao.TitanCodeDao;
import com.fangcang.titanjr.dao.TitanOrgidDao;
import com.fangcang.titanjr.service.TitanCodeCenterService;

import org.springframework.stereotype.Service;

@Service("titanCodeCenterService")
public class TitanCodeCenterServiceImpl implements TitanCodeCenterService {
	@Resource
	TitanOrgidDao titanOrgidDao;
	@Resource
	TitanCodeDao titanCodeDao;
	@Resource
	TitanAccountCodeDao titanAccountCodeDao;
	
	@Override
	public String createOrgCode() {
		Long orgId = titanOrgidDao.getNextOrgId();
		if(orgId>0){
			return CommonConstant.ORG_CODE_PREFIX + orgId;
		}else {
			throw new ServiceException("机构编码生成失败");
		}
	}

	@Override
	public String createTitanCode() throws ServiceException {
		Long code = titanCodeDao.getNextTitanCode();
		if(String.valueOf(code).endsWith("4")){
			code = titanCodeDao.getNextTitanCode();
		}
		return String.valueOf(code);
	}

	@Override
	public String createTitanAccountCode() throws ServiceException {
		Long code = titanAccountCodeDao.getNextAccountCodeId();
		return CommonConstant.ACCOUNT_CODE_PREFIX + code;
	}

	
}
