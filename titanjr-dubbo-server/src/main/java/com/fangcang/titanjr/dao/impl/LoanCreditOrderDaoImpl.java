package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class LoanCreditOrderDaoImpl extends GenericDAOMyBatisImpl implements LoanCreditOrderDao {

    private static final Log log = LogFactory.getLog(LoanCreditOrderDaoImpl.class);

    @Override
    public int saveLoanCreditOrder(LoanCreditOrder loanCreditOrder) {
        try {
            return super.insertEntity("com.fangcang.titanjr.dao.LoanCreditOrderDao.insertLoanCreditOrder", loanCreditOrder);
        } catch (Exception e) {
            log.error("saveLoanCreditOrder Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<LoanCreditOrder> queryLoanCreditOrder(LoanCreditOrder loanCreditOrder) {
        try {
            return super.selectList("com.fangcang.titanjr.dao.LoanCreditOrderDao.queryLoanCreditOrder", loanCreditOrder);
        } catch (Exception e) {
            log.error("queryLoanCreditOrder Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateLoanCreditOrder(LoanCreditOrder loanCreditOrder) {
        try {
            return super.updateEntity("com.fangcang.titanjr.dao.LoanCreditOrderDao.updateLoanCreditOrder", loanCreditOrder);
        } catch (Exception e) {
            log.error("updateLoanCreditOrder Error" , e);
            throw new DaoException(e);
        }
    }
}
