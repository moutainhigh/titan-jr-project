package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanRoomPackSpecDao;
import com.fangcang.titanjr.entity.LoanRoomPackSpec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class LoanRoomPackSpecDaoImpl extends GenericDAOMyBatisImpl implements LoanRoomPackSpecDao {
    private static final Log log = LogFactory.getLog(LoanRoomPackSpecDaoImpl.class);

    @Override
    public int saveLoanRoomPackSpec(LoanRoomPackSpec loanRoomPackSpec) {
        try {
            return super.insertEntity("com.fangcang.titanjr.dao.LoanRoomPackSpecDao.insertLoanRoomPackSpec", loanRoomPackSpec);
        } catch (Exception e) {
            log.error("saveLoanRoomPackSpec Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<LoanRoomPackSpec> queryLoanRoomPackSpec(LoanRoomPackSpec loanRoomPackSpec) {
        try {
            return super.selectList("com.fangcang.titanjr.dao.LoanRoomPackSpecDao.queryLoanRoomPackSpec", loanRoomPackSpec);
        } catch (Exception e) {
            log.error("queryLoanRoomPackSpec Error" , e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateLoanRoomPackSpec(LoanRoomPackSpec loanRoomPackSpec) {
        try {
            return super.updateEntity("com.fangcang.titanjr.dao.LoanRoomPackSpecDao.updateLoanRoomPackSpec", loanRoomPackSpec);
        } catch (Exception e) {
            log.error("updateLoanRoomPackSpec Error" , e);
            throw new DaoException(e);
        }
    }
}
