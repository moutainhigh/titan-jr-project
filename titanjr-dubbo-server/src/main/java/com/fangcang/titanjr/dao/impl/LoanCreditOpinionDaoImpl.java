package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanCreditOpinionDao;
import com.fangcang.titanjr.entity.LoanCreditOpinion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class LoanCreditOpinionDaoImpl extends GenericDAOMyBatisImpl implements LoanCreditOpinionDao {

    private static final Log log = LogFactory.getLog(LoanCreditOpinionDaoImpl.class);

    @Override
    public int saveLoanCreditOpinion(LoanCreditOpinion loanCreditOpinion) {
        try {
            return super.insertEntity("com.fangcang.titanjr.dao.LoanCreditOpinionDao.insertLoanCreditOpinion", loanCreditOpinion);
        } catch (Exception e) {
            log.error("saveLoanCreditOpinion Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<LoanCreditOpinion> queryLoanCreditOpinion(LoanCreditOpinion loanCreditOpinion) {
        try {
            return super.selectList("com.fangcang.titanjr.dao.LoanCreditOpinionDao.queryLoanCreditOpinion", loanCreditOpinion);
        } catch (Exception e) {
            log.error("queryLoanCreditOpinion Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateLoanCreditOpinion(LoanCreditOpinion loanCreditOpinion) {
        try {
            return super.updateEntity("com.fangcang.titanjr.dao.LoanCreditOpinionDao.updateLoanCreditOpinion", loanCreditOpinion);
        } catch (Exception e) {
            log.error("updateLoanCreditOpinion Error" , e);
            throw new DaoException(e);
        }
    }
}
