package com.titanjr.fop.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.titanjr.fop.dao.TitanAccountDao;
import com.titanjr.fop.dto.BalanceQueryDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/27.
 */
public class TitanAccountDaoImpl extends GenericDAOMyBatisImpl implements TitanAccountDao {

    @Override
    public List<AccountBalance> queryAccountBalanceList(BalanceQueryDTO balanceQueryDTO) throws DaoException {
        try {
            return super.selectList("com.titanjr.fop.dao.TitanAccountDao.queryAccountBalanceList", balanceQueryDTO);
        } catch (Exception e) {
            logger.error("queryAccountBalanceList Error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateAccountBalance(AccountBalance accountBalance) throws DaoException {
        try {
            return super.updateEntity("com.titanjr.fop.dao.TitanAccountDao.updateAccountBalance", accountBalance);
        } catch (Exception e) {
            logger.error("accountBalance Error", e);
            throw new DaoException(e);
        }
    }
}
