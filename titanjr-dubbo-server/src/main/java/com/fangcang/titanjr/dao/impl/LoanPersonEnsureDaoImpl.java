package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanPersonEnsureDao;
import com.fangcang.titanjr.entity.LoanPersonEnsure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class LoanPersonEnsureDaoImpl extends GenericDAOMyBatisImpl implements LoanPersonEnsureDao {
    private static final Log log = LogFactory.getLog(LoanPersonEnsureDaoImpl.class);

    @Override
    public int saveLoanPersonEnsure(LoanPersonEnsure loanPersonEnsure) {
        try {
            return super.insertEntity("com.fangcang.titanjr.dao.LoanPersonEnsureDao.insertLoanPersonEnsure", loanPersonEnsure);
        } catch (Exception e) {
            log.error("saveLoanPersonEnsure Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<LoanPersonEnsure> queryLoanPersonEnsure(LoanPersonEnsure loanPersonEnsure) {
        try {
            return super.selectList("com.fangcang.titanjr.dao.LoanPersonEnsureDao.queryLoanPersonEnsure", loanPersonEnsure);
        } catch (Exception e) {
            log.error("queryLoanPersonEnsure Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateLoanPersonEnsure(LoanPersonEnsure loanPersonEnsure) {
        try {
            return super.updateEntity("com.fangcang.titanjr.dao.LoanPersonEnsureDao.updateLoanPersonEnsure", loanPersonEnsure);
        } catch (Exception e) {
            log.error("updateLoanPersonEnsure Error" , e);
            throw new DaoException(e);
        }
    }
}
