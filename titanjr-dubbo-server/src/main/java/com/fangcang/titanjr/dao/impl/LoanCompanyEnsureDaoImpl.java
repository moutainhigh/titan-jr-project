package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanCompanyEnsureDao;
import com.fangcang.titanjr.entity.LoanCompanyEnsure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by zhaoshan on 2016/11/4.
 */
public class LoanCompanyEnsureDaoImpl extends GenericDAOMyBatisImpl implements LoanCompanyEnsureDao {

    private static final Log log = LogFactory.getLog(LoanCompanyEnsureDaoImpl.class);

    @Override
    public int saveCompanyEnsure(LoanCompanyEnsure loanCompanyEnsure) {
        try {
            return super.insertEntity("com.fangcang.titanjr.dao.LoanCompanyEnsureDao.insertCompanyEnsure", loanCompanyEnsure);
        } catch (Exception e) {
            log.error("save LoanCompanyEnsure Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<LoanCompanyEnsure> queryLoanCompanyEnsure(LoanCompanyEnsure loanCompanyEnsure) {
        try {
            return super.selectList("com.fangcang.titanjr.dao.LoanCompanyEnsureDao.queryLoanCompanyEnsure", loanCompanyEnsure);
        } catch (Exception e) {
            log.error("query LoanCompanyEnsure Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateCompanyEnsure(LoanCompanyEnsure loanCompanyEnsure) {
        try {
            return super.updateEntity("com.fangcang.titanjr.dao.LoanCompanyEnsureDao.updateCompanyEnsure", loanCompanyEnsure);
        } catch (Exception e) {
            log.error("update LoanCompanyEnsure Error" , e);
            throw new DaoException(e);
        }
    }
}
