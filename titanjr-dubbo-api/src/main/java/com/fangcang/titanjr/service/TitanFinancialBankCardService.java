package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.DeleteBindBankRequest;
import com.fangcang.titanjr.dto.request.ModifyWithDrawCardRequest;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.ModifyWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;

public interface TitanFinancialBankCardService {
	
	/**
	 * 查询所绑卡信息
	 * 确切做法应该去融数和本地同时查询？
	 * @param bankCardBindInfoRequest
	 * @return
	 * @author fangdaikang
	 */
	public QueryBankCardBindInfoResponse getBankCardBindInfo(BankCardBindInfoRequest bankCardBindInfoRequest);
	
	/**
	 * 银行卡绑定
	 * @param bankCardBindRequest
	 * @return
	 * @author fangdaikang
	 */
	public CusBankCardBindResponse bankCardBind(CusBankCardBindRequest  bankCardBindRequest);
	
	/**
	 * 删除银行卡绑定
	 * @param deleteBindBankRequest
	 * @return
	 * @author fangdaikang
	 */
	public DeleteBindBankResponse deleteBindBank(DeleteBindBankRequest deleteBindBankRequest);
	
	/**
	 * 修改提现卡
	 * @param modifyWithDrawCardRequest
	 * @return
	 * @author fangdaikang
	 */
	public ModifyWithDrawCardResponse modifyWithDrawCard(ModifyWithDrawCardRequest modifyWithDrawCardRequest);
}
