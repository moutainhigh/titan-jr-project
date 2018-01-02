package com.fangcang.titanjr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanAccountDetailDao;
import com.fangcang.titanjr.dto.request.RecordRechargeRequest;
import com.fangcang.titanjr.dto.request.RecordTransferRequest;
import com.fangcang.titanjr.entity.TitanAccountDetail;
import com.fangcang.titanjr.entity.parameter.TitanAccountDetailParam;
import com.fangcang.titanjr.service.AccountRecordService;

public class AccountRecordServiceImpl implements AccountRecordService {
	private static final Logger LOGGER = Logger.getLogger(AccountRecordServiceImpl.class);
	 
	@Resource
	TitanAccountDetailDao accountDetailDao;
	
	
	@Override
	public void recharge(RecordRechargeRequest recordRechargeRequest) {
		// 交易验证
		TitanAccountDetailParam param = new TitanAccountDetailParam();
		param.setTransOrderId(recordRechargeRequest.getTransOrderId());
		param.setOrgCode(recordRechargeRequest.getUserId());
		param.setProductId(recordRechargeRequest.getProduct());
		param.setTradeType(1);
		
		List<TitanAccountDetail> accountDetailList = accountDetailDao.selectList(param);
		
		if(CollectionUtils.isNotEmpty(accountDetailList)){
			LOGGER.error("该笔交易已经记账，无需重复记账，记账参数recordRechargeRequest："+Tools.gsonToString(recordRechargeRequest));
			return ;
		}
		
		TitanAccountDetail accountDetail = new TitanAccountDetail();
		//accountDetail.setAccountCode(accountCode);
		accountDetail.setTransOrderId(recordRechargeRequest.getTransOrderId());
		accountDetail.setTradeType(1);
		accountDetail.setOrgCode(recordRechargeRequest.getUserId());
		accountDetail.setProductId(recordRechargeRequest.getProduct());
		accountDetail.setCreditAmount(0L);
		accountDetail.setFrozonAmount(0L);
		accountDetail.setSettleAmount(recordRechargeRequest.getAmount());
		accountDetail.setStatus(1);
		accountDetail.setCreateTime(new Date());
		accountDetailDao.insert(accountDetail);

	}

	@Override
	public void transfer(RecordTransferRequest recordTransferRequest) {
		// TODO Auto-generated method stub

	}

	@Override
	public void freeze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unFreeze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refund() {
		// TODO Auto-generated method stub

	}

}
