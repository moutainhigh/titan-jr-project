package com.fangcang.titanjr.service;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.request.RecordRequest;
import com.fangcang.titanjr.dto.request.RecordTransferRequest;

/**
 * 记账
 * @author luoqinglong
 * @date 2017年12月28日
 */
public interface AccountRecordService {
	/**
	 * 充值记账
	 * @param recordRechargeRequest
	 */
	BaseResponseDTO recharge(RecordRequest recordRequest) throws GlobalServiceException;
	/***
	 * 转账记账
	 * @param recordTransferRequest
	 * @throws GlobalServiceException
	 */
	BaseResponseDTO transfer(RecordTransferRequest recordTransferRequest) throws GlobalServiceException;
	
	/**
	 * 冻结记账
	 * @param recordRequest
	 */
	BaseResponseDTO freeze(RecordRequest recordRequest) throws GlobalServiceException;
	/**
	 * 解冻记账
	 * @param recordRequest
	 */
	BaseResponseDTO unFreeze(RecordRequest recordRequest) throws GlobalServiceException;
	/**
	 * 提现记账
	 * @throws GlobalServiceException
	 */
	BaseResponseDTO withdraw(RecordRequest recordRequest) throws GlobalServiceException;
	
	/**
	 * 退款记账
	 * @throws GlobalServiceException
	 */
	BaseResponseDTO refund(RecordRequest recordRequest) throws GlobalServiceException;
}
