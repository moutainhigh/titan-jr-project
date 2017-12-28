package com.titanjr.fop.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/27.
 */
public interface TitanAccountDao {

    List<AccountBalance> queryAccountBalanceList(AccountBalanceRequest balanceRequest) throws DaoException;

}
