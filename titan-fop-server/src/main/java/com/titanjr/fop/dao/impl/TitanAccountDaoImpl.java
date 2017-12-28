package com.titanjr.fop.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.titanjr.fop.dao.TitanAccountDao;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/27.
 */
public class TitanAccountDaoImpl extends GenericDAOMyBatisImpl implements TitanAccountDao {

    @Override
    public List<AccountBalance> queryAccountBalanceList(AccountBalanceRequest balanceRequest) throws DaoException {
        try {
            return super.selectList("com.titanjr.fop.dao.TitanAccountDao.queryAccountBalanceList",balanceRequest);
        } catch (Exception e) {
            logger.error("queryAccountBalanceList Error", e);
            throw new DaoException(e);
        }
    }
}
