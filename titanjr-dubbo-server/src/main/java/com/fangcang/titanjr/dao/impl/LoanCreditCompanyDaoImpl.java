package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.entity.LoanCreditCompany;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by zhaoshan on 2016/11/4.
 */
public class LoanCreditCompanyDaoImpl extends GenericDAOMyBatisImpl implements LoanCreditCompanyDao {

    private static final Log log = LogFactory.getLog(LoanCreditCompanyDaoImpl.class);

    @Override
    public int saveCreditCompany(LoanCreditCompany loanCreditCompany) {
        try {
            return super.insertEntity("com.fangcang.titanjr.dao.LoanCreditCompanyDao.saveCreditCompany", loanCreditCompany);
        } catch (Exception e) {
            log.error("save LoanCreditCompany Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<LoanCreditCompany> queryLoanCreditCompany(LoanCreditCompany loanCreditCompany) {
        try {
            return super.selectList("com.fangcang.titanjr.dao.LoanCreditCompanyDao.queryLoanCreditCompany", loanCreditCompany);
        } catch (Exception e) {
            log.error("query LoanCreditCompany Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateCreditCompany(LoanCreditCompany loanCreditCompany) {
        try {
            return super.updateEntity("com.fangcang.titanjr.dao.LoanCreditCompanyDao.updateCreditCompany", loanCreditCompany);
        } catch (Exception e) {
            log.error("update LoanCreditCompany Error" , e);
            throw new DaoException(e);
        }
    }
}
