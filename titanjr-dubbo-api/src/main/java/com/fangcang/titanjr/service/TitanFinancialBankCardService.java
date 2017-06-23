package com.fangcang.titanjr.service;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.BankCardDTO;
import com.fangcang.titanjr.dto.request.BankCardRequest;
import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.DeleteBindBankRequest;
import com.fangcang.titanjr.dto.request.ModifyInvalidWithDrawCardRequest;
import com.fangcang.titanjr.dto.request.ModifyWithDrawCardRequest;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.ModifyInvalidWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.ModifyWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.entity.TitanBankcard;
import com.fangcang.titanjr.entity.parameter.TitanBankcardParam;

public interface TitanFinancialBankCardService {
	
	/**
	 * 查询融数绑卡信息
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
	
	/**
	 * 银行卡信息
	 * @param bankCardRequest
	 * @return
	 */
	public List<BankCardDTO> queryBankCardDTO(BankCardRequest bankCardRequest);
	
	/**
	 * 定时任务批量绑定银行卡
	 */
	
	public void bindBankCardBatch();
	
	/**
	 * 个人用户绑卡
	 * @param userId
	 */
	public void bindBankCardForOne(String userId);
	
	/**
	 * 绑定失败后修改提现卡
	 * @param modifyInvalidWithDrawCardRequest
	 * @return
	 */
	public ModifyInvalidWithDrawCardResponse  modifyinvalidPublicCard(ModifyInvalidWithDrawCardRequest modifyInvalidWithDrawCardRequest);

	
	/**
	 * 查询本地单个绑卡信息
	 * @author jerry
	 * @param param
	 * @return
	 */
	public TitanBankcard queryBankCardInfo(TitanBankcardParam param);
	
	
	/**
	 * 更新对私卡的所有绑卡记录
	 * <br>
	 * 先删除本地所有对私卡的绑卡记录，然后从融数查询出最新的对私卡绑卡记录批量插入本地数据库
	 * @author jerry
	 * 
	 * @return
	 */
	public BaseResponseDTO batchUpdatePersonalCard();
	
}
