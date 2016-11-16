package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanOrderDao;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class LoanOrderDaoImpl extends GenericDAOMyBatisImpl implements LoanOrderDao {

    private static final Log log = LogFactory.getLog(LoanOrderDaoImpl.class);

    @Override
    public int saveLoanApplyOrder(LoanApplyOrder loanApplyOrder) {
        try {
            return super.insertEntity("com.fangcang.titanjr.dao.LoanApplyOrderDao.insertLoanApplyOrder", loanApplyOrder);
        } catch (Exception e) {
            log.error("saveLoanApplyOrder Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<LoanApplyOrder> queryLoanApplyOrder(LoanApplyOrder loanApplyOrder) {
        try {
            return super.selectList("com.fangcang.titanjr.dao.LoanApplyOrderDao.queryLoanApplyOrder", loanApplyOrder);
        } catch (Exception e) {
            log.error("queryLoanApplyOrder Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateLoanApplyOrder(LoanApplyOrder loanApplyOrder) {
        try {
            return super.updateEntity("com.fangcang.titanjr.dao.LoanApplyOrderDao.updateLoanApplyOrder", loanApplyOrder);
        } catch (Exception e) {
            log.error("updateLoanApplyOrder Error" , e);
            throw new DaoException(e);
        }
    }
}
