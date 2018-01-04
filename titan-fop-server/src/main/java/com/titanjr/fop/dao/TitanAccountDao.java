package com.titanjr.fop.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.titanjr.fop.dto.BalanceQueryDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/27.
 */
public interface TitanAccountDao {

    /**
     * 查询账户余额
     * @param balanceQueryDTO
     * @return
     * @throws DaoException
     */
    List<AccountBalance> queryAccountBalanceList(BalanceQueryDTO balanceQueryDTO) throws DaoException;

    /**
     * 更新账户资金
     * @param accountBalance
     * @return
     * @throws DaoException
     */
    int updateAccountBalance(AccountBalance accountBalance) throws DaoException;
}
