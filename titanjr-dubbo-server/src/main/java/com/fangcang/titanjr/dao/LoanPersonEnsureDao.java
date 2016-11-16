package com.fangcang.titanjr.dao;

import com.fangcang.titanjr.entity.LoanPersonEnsure;

import java.util.List;

public interface LoanPersonEnsureDao {
    /**
     * 插入
     * @param loanPersonEnsure
     * @return
     */
    int saveLoanPersonEnsure(LoanPersonEnsure loanPersonEnsure);

    /**
     * 查询
     * @param loanPersonEnsure
     * @return
     */
    List<LoanPersonEnsure> queryLoanPersonEnsure(LoanPersonEnsure loanPersonEnsure);

    /**
     *
     * @param loanPersonEnsure
     * @return
     */
    int updateLoanPersonEnsure(LoanPersonEnsure loanPersonEnsure);
}
